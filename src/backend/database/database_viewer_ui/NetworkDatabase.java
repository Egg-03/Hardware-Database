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

public class NetworkDatabase {
	
	private NetworkDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getAllNetworkAdapterIds(String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT Network.DeviceId FROM Network WHERE Network.HardwareId = '"+hwid+"'";
		List<String> adapterIds = new ArrayList<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			while(rs.next()) {
				String cpu = rs.getString("DeviceId");
				adapterIds.add(cpu);
			}
			return adapterIds;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Network Adapter ID Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
	
	public static Map<String, String> getNetworkProperties(String deviceId, String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT Description, MACAddress, IPAddress, IPSubnet, DefaultIPGateway, DNSServerSearchOrder FROM Network WHERE Network.HardwareId = '"+hwid+"' AND Network.DeviceId = '"+deviceId+"'";
		Map<String, String> networkProperties = new LinkedHashMap<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			networkProperties.put("Description",  rs.getString("Description"));
			networkProperties.put("MACAddress", rs.getString("MACAddress"));
			networkProperties.put("IPAddress", rs.getString("IPAddress"));
			networkProperties.put("IPSubnet", rs.getString("IPSubnet"));
			networkProperties.put("DefaultIPGateway", rs.getString("DefaultIPGateway"));
			networkProperties.put("DNSServerSearchOrder", rs.getString("DNSServerSearchOrder"));
			return networkProperties;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Network Property Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyMap();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
}
