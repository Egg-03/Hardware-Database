package backend.database.database_viewer_ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import backend.database.connection.DatabaseConnectivity;
import frontend.extra_ui.ExceptionUI;

public class MemoryDatabase {
	
	private MemoryDatabase() {
		throw new IllegalStateException("Utility Class");
	}
		
		public static Map<String, String> getMemoryProperties(String hwid){
			Connection connect = DatabaseConnectivity.initialize();
			String statement = "SELECT SlotsUsed, TotalMemory FROM Memory WHERE Memory.HardwareId = '"+hwid+"'";
			Map<String, String> memoryProperties = new LinkedHashMap<>();
			try(Statement st = connect.createStatement()){
				ResultSet rs = st.executeQuery(statement);
				
				memoryProperties.put("SlotsUsed",  Integer.toString(rs.getInt("SlotsUsed")));
				memoryProperties.put("TotalMemory", rs.getString("TotalMemory"));
				return memoryProperties;
			} catch (SQLException e) {
				new ExceptionUI("HC Database Viewer Memory Property Fetch Error", e.getMessage()).setVisible(true);
				DatabaseConnectivity.close(connect);
				return Collections.emptyMap();
			}
			finally {
				DatabaseConnectivity.close(connect);
			}
		}
}
