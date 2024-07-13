package com.egg.hardcache.ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.egg.miniuis.ExceptionUI;
import com.ferrumx.system.currentuser.User;
import com.ferrumx.system.operating_system.Win32_OperatingSystem;

final class OperatingSystem {
	
	private OperatingSystem() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static boolean initializeOs(JComboBox<String> osNameChoice, JTextField deviceNameTextField, JTextField osArchTextField, JTextField currentUserTextField ) {
		List<String> osNames;
		try {
			osNames = Win32_OperatingSystem.getOSList();
			for(String osName: osNames) {
				osNameChoice.addItem(osName);
			}
			Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osNameChoice.getItemAt(osNameChoice.getSelectedIndex()));
			deviceNameTextField.setText(osProperties.get("CSName"));
			osArchTextField.setText(osProperties.get("OSArchitecture"));
			currentUserTextField.setText(User.getUsername());
			
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("OS Error", e.getMessage()).setVisible(true);
			return false;
		}
		
		
		//add an action listener for when the user selects a different OS for multi-boot Systems
		osNameChoice.addActionListener(e-> {
			try {
				Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osNameChoice.getItemAt(osNameChoice.getSelectedIndex()));
				deviceNameTextField.setText(osProperties.get("CSName"));
				osArchTextField.setText(osProperties.get("OSArchitecture"));
				currentUserTextField.setText(User.getUsername());
			} catch (IndexOutOfBoundsException | IOException e1) {
				new ExceptionUI("OS Error", e1.getMessage()).setVisible(true);
			}
		});
		return true;
	}
}
