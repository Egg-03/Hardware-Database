package com.egg.ferrumldb.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferruml.system.network.Win32_NetworkAdapter;

final class Network {
	
	private Network() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeNetwork(JComboBox<String> connectionIdChoice, JTextField...networkFields) throws IndexOutOfBoundsException, IOException {
		List<String> networkAdapters = Win32_NetworkAdapter.getAdapterID();
		for(String networkAdapter: networkAdapters) {
			connectionIdChoice.addItem(networkAdapter);
		}
		Map<String, String> networkProperties = Win32_NetworkAdapter.getNetworkAdapters(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
		networkFields[0].setText(networkProperties.get("MACAddress"));
		networkFields[1].setText(networkProperties.get("Description"));
		
		connectionIdChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> networkProperties;
				try {
					networkProperties = Win32_NetworkAdapter.getNetworkAdapters(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
					networkFields[0].setText(networkProperties.get("MACAddress"));
					networkFields[1].setText(networkProperties.get("Description"));
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	

}
