package com.hardcache.frontend.mainui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferrumx.system.hardware.Win32_NetworkAdapter;
import com.ferrumx.system.networking.Win32_NetworkAdapterConfiguration;
import com.ferrumx.system.networking.Win32_NetworkAdapterSetting;
import com.hardcache.frontend.miniuis.ExceptionUI;

final class Network {
	
	private Network() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	
	protected static boolean initializeNetwork(JComboBox<String> connectionIdChoice, JTextField...networkFields) {
		List<String> networkAdapters;
		try {
			networkAdapters = Win32_NetworkAdapter.getDeviceIDList();
			for(String networkAdapter: networkAdapters) {
				connectionIdChoice.addItem(networkAdapter);
			}
			
			String networkIndex = Win32_NetworkAdapterSetting.getIndex(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
			Map<String, String> networkProperties = Win32_NetworkAdapter.getNetworkAdapters(networkIndex);
			Map<String, String> networkAddress = Win32_NetworkAdapterConfiguration.getAdapterConfiguration(networkIndex);
			networkFields[0].setText(networkProperties.get("MACAddress"));
			networkFields[1].setText(networkProperties.get("Description"));
			networkFields[2].setText(networkAddress.get("IPAddress"));
				
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Network Error", e.getMessage()).setVisible(true);
			return false;
		}
		
		//action listener for multiple network adapter choices
		connectionIdChoice.addActionListener(e-> {
			try {
				String networkIndex = Win32_NetworkAdapterSetting.getIndex(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
				Map<String, String> networkProperties = Win32_NetworkAdapter.getNetworkAdapters(networkIndex);
				Map<String, String> networkAddress = Win32_NetworkAdapterConfiguration.getAdapterConfiguration(networkIndex);
				networkFields[0].setText(networkProperties.get("MACAddress"));
				networkFields[1].setText(networkProperties.get("Description"));
				networkFields[2].setText(networkAddress.get("IPAddress"));	
			} catch (IndexOutOfBoundsException | IOException e1) {
				new ExceptionUI("Network Error", e1.getMessage()).setVisible(true);
			}
		});
		return true;
	}
}
