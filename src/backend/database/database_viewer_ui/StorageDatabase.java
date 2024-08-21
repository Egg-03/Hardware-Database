package backend.database.database_viewer_ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import backend.database.connection.DatabaseConnectivity;
import frontend.extra_ui.ExceptionUI;

public class StorageDatabase {
	private StorageDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getDiskIds(String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT Storage.DeviceId FROM Storage WHERE Storage.HardwareId = '"+hwid+"'";
		List<String> diskIds = new ArrayList<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			while(rs.next()) {
				String cpu = rs.getString("DeviceId");
				diskIds.add(cpu);
			}
			return diskIds;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Disk ID Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
	
	public static Map<String, String> getDiskProperties(String deviceId, String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT Name, Serial, Size, SMART FROM Storage WHERE Storage.HardwareId = '"+hwid+"' AND Storage.DeviceId = '"+deviceId+"'";
		Map<String, String> diskProperties = new LinkedHashMap<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			diskProperties.put("Name",  rs.getString("Name"));
			diskProperties.put("Serial", rs.getString("Serial"));
			diskProperties.put("Size", rs.getString("Size"));
			diskProperties.put("SMART", rs.getString("SMART"));
			return diskProperties;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Disk Property Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyMap();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
}
