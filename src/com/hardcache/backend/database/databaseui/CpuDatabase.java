package com.hardcache.backend.database.databaseui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hardcache.backend.database.connection.DatabaseConnectivity;
import com.hardcache.frontend.miniuis.ExceptionUI;

public class CpuDatabase {
	
	private CpuDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getAllCpus(String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT CPU.DeviceId FROM CPU WHERE CPU.HardwareId = '"+hwid+"'";
		List<String> cpus = new ArrayList<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			while(rs.next()) {
				String cpu = rs.getString("DeviceId");
				cpus.add(cpu);
			}
			return cpus;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer CPUID Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
	
	public static Map<String, String> getCpuProperties(String deviceId, String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT CpuName, CpuCores, CpuThreads, CpuSocket FROM CPU WHERE CPU.HardwareId = '"+hwid+"' AND CPU.DeviceId = '"+deviceId+"'";
		Map<String, String> cpuProperties = new LinkedHashMap<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			cpuProperties.put("CpuName",  rs.getString("CpuName"));
			cpuProperties.put("CpuCores", rs.getString("CpuCores"));
			cpuProperties.put("CpuThreads", rs.getString("CpuThreads"));
			cpuProperties.put("CpuSocket", rs.getString("CpuSocket"));
			return cpuProperties;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer CPU Property Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyMap();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
}
