package com.hardwaresnapshot.backend.database.mainui;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.hardwaresnapshot.backend.database.connection.DatabaseConnectivity;
import com.hardwaresnapshot.frontend.miniuis.ExceptionUI;
import com.hardwaresnapshot.frontend.miniuis.InformationUI;

//WARNING: THIS WILL WORK IFF THE HARDWARE ID OF THE CURRENT MACHINE MATCHES WITH ONE OF THE ID's STORED IN THE DB
public class DataDeletion {
	
	private DataDeletion() {
		throw new IllegalStateException("Data Deletion Utility Class");
	}
	
	public static final void delete(String hardwareId) {
		Connection connect = DatabaseConnectivity.initialize();
		
		String deleteCPU = "DELETE FROM CPU WHERE CPU.HardwareId='"+hardwareId+"'";
		String deleteGPU = "DELETE FROM GPU WHERE GPU.HardwareId='"+hardwareId+"'";
		String deleteMemory = "DELETE FROM Memory WHERE Memory.HardwareId='"+hardwareId+"'";
		String deleteStorage = "DELETE FROM Storage WHERE Storage.HardwareId='"+hardwareId+"'";
		String deleteNetwork = "DELETE FROM Network WHERE Network.HardwareId='"+hardwareId+"'";
		String deleteOperatingSystem = "DELETE FROM OperatingSystem WHERE OperatingSystem.HardwareId='"+hardwareId+"'";
		String deleteMainboard = "DELETE FROM Mainboard WHERE Mainboard.HardwareId='"+hardwareId+"'";
		String deleteHardwareId = "DELETE FROM HardwareId WHERE HardwareId.UniqueId='"+hardwareId+"'";
		
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
