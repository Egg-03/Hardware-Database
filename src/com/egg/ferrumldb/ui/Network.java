package com.egg.ferrumldb.ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.egg.miniuis.ExceptionUI;
import com.ferruml.system.network.Win32_NetworkAdapter;

final class Network {
	
	private Network() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeNetwork(JComboBox<String> connectionIdChoice, JTextField...networkFields) {
		List<String> networkAdapters;
		try {
			networkAdapters = Win32_NetworkAdapter.getAdapterID();
			for(String networkAdapter: networkAdapters) {
				connectionIdChoice.addItem(networkAdapter);
			}
			Map<String, String> networkProperties = Win32_NetworkAdapter.getNetworkAdapters(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
			networkFields[0].setText(networkProperties.get("MACAddress"));
			networkFields[1].setText(networkProperties.get("Description"));
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Network Error", e.getMessage()).setVisible(true);
		}
		
		//action listener for multiple network adapter choices
		connectionIdChoice.addActionListener(e-> {
			Map<String, String> networkProperties;
			try {
				networkProperties = Win32_NetworkAdapter.getNetworkAdapters(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
				networkFields[0].setText(networkProperties.get("MACAddress"));
				networkFields[1].setText(networkProperties.get("Description"));
			} catch (IndexOutOfBoundsException | IOException e1) {
				new ExceptionUI("Network Error", e1.getMessage()).setVisible(true);
			}
		});
	}
}
