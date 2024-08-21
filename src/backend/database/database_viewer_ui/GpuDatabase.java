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

public class GpuDatabase {
	
	private GpuDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getAllGpuIds(String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT GPU.DeviceId FROM GPU WHERE GPU.HardwareId = '"+hwid+"'";
		List<String> gpus = new ArrayList<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			while(rs.next()) {
				String gpu = rs.getString("DeviceId");
				gpus.add(gpu);
			}
			return gpus;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer GPUID Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
	
	public static Map<String, String> getGpuProperties(String deviceId, String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT GpuName, VRAM, DriverVersion FROM GPU WHERE Gpu.HardwareId = '"+hwid+"' AND Gpu.DeviceId = '"+deviceId+"'";
		Map<String, String> gpuProperties = new LinkedHashMap<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			gpuProperties.put("GpuName",  rs.getString("GpuName"));
			gpuProperties.put("VRAM", rs.getString("VRAM"));
			gpuProperties.put("DriverVersion", rs.getString("DriverVersion"));
			return gpuProperties;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer GPU Property Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyMap();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
}
