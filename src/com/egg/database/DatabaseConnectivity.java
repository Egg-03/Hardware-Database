package com.egg.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.egg.miniuis.ExceptionUI;


class DatabaseConnectivity {
	private DatabaseConnectivity() {
		throw new IllegalStateException("Utility Class");
	}
	
	protected static Connection initialize() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:database\\dumprecords");
		} catch (ClassNotFoundException | SQLException e) {
			new ExceptionUI("Database Connectivity Error", e.getClass().getSimpleName()+": "+e.getMessage()).setVisible(true);
			return null;
		}
	}
	
	protected static void close(Connection connect) {
		try {
			connect.close();
		} catch (SQLException e) {
			new ExceptionUI("Database Connection Termination Error", e.getClass().getSimpleName()+": "+e.getMessage()).setVisible(true);
		}
	}
}
