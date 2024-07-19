package com.hardwaresnapshot.backend.database.datainsertanddelete;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

import com.ferrumx.system.hardware.HardwareID;
import com.hardwaresnapshot.backend.database.connection.DatabaseConnectivity;
import com.hardwaresnapshot.frontend.miniuis.ExceptionUI;
import com.hardwaresnapshot.frontend.miniuis.InformationUI;

//WARNING: THIS WILL WORK IFF THE HARDWARE ID OF THE CURRENT MACHINE MATCHES WITH ONE OF THE ID's STORED IN THE DB
public class DataDeletion {
	private static final String HARDWAREID = getHWID();
	
	private DataDeletion() {
		throw new IllegalStateException("Data Deletion Utility Class");
	}
	
	private static String getHWID() {
		try {
			return HardwareID.getHardwareID();
		} catch (ExecutionException | InterruptedException e) {
			new ExceptionUI("Database Dump Error", "HWID could not be retrieved\n"+e.getMessage()).setVisible(true);
			Thread.currentThread().interrupt();
			return "";
		}
	}
	
	public static final void delete() {
		Connection connect = DatabaseConnectivity.initialize();
		
		String deleteCPU = "DELETE FROM CPU WHERE CPU.HardwareId='"+HARDWAREID+"'";
		String deleteGPU = "DELETE FROM GPU WHERE GPU.HardwareId='"+HARDWAREID+"'";
		String deleteMemory = "DELETE FROM Memory WHERE Memory.HardwareId='"+HARDWAREID+"'";
		String deleteStorage = "DELETE FROM Storage WHERE Storage.HardwareId='"+HARDWAREID+"'";
		String deleteNetwork = "DELETE FROM Network WHERE Network.HardwareId='"+HARDWAREID+"'";
		String deleteOperatingSystem = "DELETE FROM OperatingSystem WHERE OperatingSystem.HardwareId='"+HARDWAREID+"'";
		String deleteMainboard = "DELETE FROM Mainboard WHERE Mainboard.HardwareId='"+HARDWAREID+"'";
		String deleteHardwareId = "DELETE FROM HardwareId WHERE HardwareId.UniqueId='"+HARDWAREID+"'";
		
		try(Statement st = connect.createStatement()) {
			st.addBatch(deleteCPU);
			st.addBatch(deleteGPU);
			st.addBatch(deleteMemory);
			st.addBatch(deleteStorage);
			st.addBatch(deleteNetwork);
			st.addBatch(deleteOperatingSystem);
			st.addBatch(deleteMainboard);
			st.addBatch(deleteHardwareId);
			
			st.executeBatch();
			new InformationUI("Deletion Success").setVisible(true);
			DatabaseConnectivity.close(connect);
			
		} catch (SQLException e) {
			new ExceptionUI("Data Delete Error", e.getMessage()).setVisible(true);
			DatabaseConnectivity.close(connect);
		}
	}
}
