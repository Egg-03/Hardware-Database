package backend.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import frontend.extra_ui.ExceptionUI;


public class DatabaseConnectivity {
	private DatabaseConnectivity() {
		throw new IllegalStateException("Utility Class");
	}
	
	public static Connection initialize() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:database\\ComputerRecords.db");
		} catch (ClassNotFoundException | SQLException e) {
			new ExceptionUI("Database Connectivity Error", e.getClass().getSimpleName()+": "+e.getMessage()).setVisible(true);
			return null;
		}
	}
	
	public static void close(Connection connect) {
		try {
			connect.close();
		} catch (SQLException e) {
			new ExceptionUI("Database Connection Termination Error", e.getClass().getSimpleName()+": "+e.getMessage()).setVisible(true);
		}
	}
}
