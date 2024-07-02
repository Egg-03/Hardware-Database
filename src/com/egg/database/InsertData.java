package com.egg.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.egg.errorui.ExceptionUI;
import com.ferruml.system.currentuser.User;
import com.ferruml.system.hardware.HWID;
import com.ferruml.system.operatingsystem.Win32_OperatingSystem;

public class InsertData {
	
	private static final Connection connect = DatabaseConnectivity.initialize();
	private static final String HARDWAREID = getHWID();
	
	private static String getHWID() {
		try {
			return HWID.getHardwareID();
		} catch (ExecutionException | InterruptedException e) {
			new ExceptionUI("Database Dump Error", "HWID could not be retrieved\n"+e.getMessage()).setVisible(true);
			Thread.currentThread().interrupt();
			return "";
		}
	}
	
	//Populate the HardwareId Table
	private static void insertHardwareId() {
		String query = "INSERT INTO HardwareId (UniqueId) VALUES ('"+HARDWAREID+"');";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			ps.executeUpdate();
		} catch (SQLException e) {
			new ExceptionUI("Data Insertion Error", e.getMessage()).setVisible(true);
		}
	}
	
	private static void insertOperatingSystem() {
		String query = "INSERT INTO OperatingSystem (HardwareId, Name, Architecture, DeviceName, CurrentUser) VALUES (?,?,?,?,?);";
		
		try(PreparedStatement ps = connect.prepareStatement(query)){
			List<String> operatingSystems = Win32_OperatingSystem.getOSList();
			for(String operatingSystem: operatingSystems) {
				Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(operatingSystem);
				ps.setString(1, HARDWAREID);
				ps.setString(2, operatingSystem);
				ps.setString(3, osProperties.get("OSArchitecture"));
				ps.setString(4, osProperties.get("CSName"));
				ps.setString(5, User.getUsername());
				
				ps.addBatch();
				ps.executeBatch();
			}
		} catch (SQLException e) {
			new ExceptionUI("Database Dump Error", "OS info could not be dumped\n"+e.getMessage()).setVisible(true);
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Database Dump Error", "OS info could not be retrieved\n"+e.getMessage()).setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		insertHardwareId();
		insertOperatingSystem();
		DatabaseConnectivity.close(connect);
	}
			
}
