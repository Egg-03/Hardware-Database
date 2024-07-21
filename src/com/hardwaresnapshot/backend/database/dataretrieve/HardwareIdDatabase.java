package com.hardwaresnapshot.backend.database.dataretrieve;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hardwaresnapshot.backend.database.connection.DatabaseConnectivity;
import com.hardwaresnapshot.frontend.miniuis.ExceptionUI;

public class HardwareIdDatabase {
	
	private HardwareIdDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getAllLocations(){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT HardwareId.Location FROM HardwareId";
		List<String> locations = new ArrayList<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			while(rs.next()) {
				String location = rs.getString("Location");
				if(!locations.contains(location))
					locations.add(location);
			}
			return locations;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Location Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
	
	public static List<String> getAllUsernameWhereLocation(String location){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT HardwareId.Username FROM HardwareId WHERE HardwareId.Location = '"+location+"'";
		List<String> usernames = new ArrayList<>();
		
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			while(rs.next()) {
				String username = rs.getString("Username");
				if(!usernames.contains(username))
					usernames.add(username);
			}
			return usernames;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Selective Username Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		} 
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
	
	public static List<String> getAllHardwareIdWhere(String location, String username){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT HardwareId.UniqueId FROM HardwareId WHERE HardwareId.Location = '"+location+"' AND HardwareId.Username = '"+username+"'";
		List<String> hardwareId = new ArrayList<>();
		
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			while(rs.next()) {
				hardwareId.add(rs.getString("UniqueId"));
			}
			return hardwareId;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Selective HWID Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		} 
		finally {
			DatabaseConnectivity.close(connect);
		}
	}
}
