package com.egg.ferrumldb.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

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
			Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
			
			storageFields[0].setText(diskProperties.get("SerialNumber"));
			storageFields[0].setCaretPosition(0);
			
			storageFields[1].setText(diskProperties.get("Status"));
			storageFields[2].setText(String.valueOf(diskSize)+" GB");
		} catch (IndexOutOfBoundsException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		storageNameChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> diskProperties;
				try {
					diskProperties = Win32_DiskDrive.getDrive(storageNameChoice.getItemAt(storageNameChoice.getSelectedIndex()));
					Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
					
					storageFields[0].setText(diskProperties.get("SerialNumber"));
					storageFields[0].setCaretPosition(0);
					
					storageFields[1].setText(diskProperties.get("Status"));
					storageFields[2].setText(String.valueOf(diskSize)+" GB");
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					/*This is a unique case where removing all items from the storageNameChoice box triggers this action listener
					 * and since the combo box returns null string, the Long.parseLong cannot parse a null string for disk size
					 * causing this exception to be thrown
					 * This should handle the exception gracefully*/
					storageFields[2].setText("0 GB");
				}
			}
		});
		
	}

}
