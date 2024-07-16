package com.hardcache.backend.database.databaseui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hardcache.backend.database.connection.DatabaseConnectivity;
import com.hardcache.frontend.miniuis.ExceptionUI;

public class HardwareIdDatabase {
	
	private HardwareIdDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static List<String> getAllUsernames(){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT HardwareId.Username FROM HardwareId";
		List<String> userNames = new ArrayList<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			while(rs.next()) {
				userNames.add(rs.getString("Username"));
			}
			return userNames;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Username Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyList();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
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
}
