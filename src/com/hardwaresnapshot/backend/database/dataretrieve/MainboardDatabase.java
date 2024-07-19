package com.hardwaresnapshot.backend.database.dataretrieve;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hardwaresnapshot.backend.database.connection.DatabaseConnectivity;
import com.hardwaresnapshot.frontend.miniuis.ExceptionUI;

public class MainboardDatabase {
	
	private MainboardDatabase() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Map<String, String> getMainboardProperties(String hwid){
		Connection connect = DatabaseConnectivity.initialize();
		String statement = "SELECT Name, Manufacturer, BIOSVersion FROM Mainboard WHERE Mainboard.HardwareId = '"+hwid+"'";
		Map<String, String> mainboardProperties = new LinkedHashMap<>();
		try(Statement st = connect.createStatement()){
			ResultSet rs = st.executeQuery(statement);
			
			mainboardProperties.put("Name",  rs.getString("Name"));
			mainboardProperties.put("Manufacturer", rs.getString("Manufacturer"));
			mainboardProperties.put("BIOSVersion", rs.getString("BIOSVersion"));
			return mainboardProperties;
		} catch (SQLException e) {
			new ExceptionUI("HC Database Viewer Mainboard Property Fetch Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
			return Collections.emptyMap();
		}
		finally {
			DatabaseConnectivity.close(connect);
		}
	}

}
