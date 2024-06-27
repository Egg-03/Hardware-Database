package com.egg.ferrumldb.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferruml.system.currentuser.User;
import com.ferruml.system.operatingsystem.Win32_OperatingSystem;

final class OperatingSystem {
	
	private OperatingSystem() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeOs(JComboBox<String> osNameChoice, JTextField deviceNameTextField, JTextField osArchTextField, JTextField currentUserTextField ) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//add an action listener for when the user selects a different OS for multi-boot Systems
		osNameChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osNameChoice.getItemAt(osNameChoice.getSelectedIndex()));
					deviceNameTextField.setText(osProperties.get("CSName"));
					osArchTextField.setText(osProperties.get("OSArchitecture"));
					currentUserTextField.setText(User.getUsername());
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO I WILL DO NOTHING NOOO NOTHING NEVER EVER I WILL BECOME A FARMER
					e1.printStackTrace();
				}
			}
		});
	}
}
