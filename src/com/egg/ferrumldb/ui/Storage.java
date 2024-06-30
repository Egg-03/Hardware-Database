package com.egg.ferrumldb.ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.egg.errorui.ExceptionUI;
import com.ferruml.system.hardware.Win32_DiskDrive;

final class Storage {
	
	private Storage() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeStorage(JComboBox<String> storageNameChoice, JTextField...storageFields) {
		List<String> disks;
		try {
			disks = Win32_DiskDrive.getDriveID();
			for(String disk: disks) {
				storageNameChoice.addItem(disk);
			}
			Map<String, String> diskProperties = Win32_DiskDrive.getDrive(storageNameChoice.getItemAt(storageNameChoice.getSelectedIndex()));
			storageFields[0].setText(diskProperties.get("Caption"));
			
			storageFields[1].setText(diskProperties.get("SerialNumber"));
			storageFields[1].setCaretPosition(0);
			
			storageFields[2].setText(diskProperties.get("Status"));
			
			Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
			storageFields[3].setText(String.valueOf(diskSize)+" GB");
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Storage Error", e.getMessage()).setVisible(true);
		}
		
		storageNameChoice.addActionListener(e-> {
			Map<String, String> diskProperties;
			try {
				diskProperties = Win32_DiskDrive.getDrive(storageNameChoice.getItemAt(storageNameChoice.getSelectedIndex()));
				storageFields[0].setText(diskProperties.get("Caption"));
					
				storageFields[1].setText(diskProperties.get("SerialNumber"));
				storageFields[1].setCaretPosition(0);
					
				storageFields[2].setText(diskProperties.get("Status"));
					
				Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
				storageFields[3].setText(String.valueOf(diskSize)+" GB");
			} catch (IndexOutOfBoundsException | IOException e1) {
				new ExceptionUI("Storage Error", e1.getMessage()).setVisible(true);
			} catch (NumberFormatException e2) {
				/*This is a unique case where removing all items from the storageNameChoice box triggers this action listener
				 * and since the combo box returns null string, the Long.parseLong cannot parse a null string for disk size
				 * causing this exception to be thrown
				 * This should handle the exception gracefully*/
				storageFields[2].setText("0 GB");
			}
		});
	}
}
