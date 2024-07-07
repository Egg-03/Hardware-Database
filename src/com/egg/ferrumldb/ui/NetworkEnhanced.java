package com.egg.ferrumldb.ui;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.egg.miniuis.ExceptionUI;

public final class NetworkEnhanced {
	
	private NetworkEnhanced() {
		throw new IllegalStateException("Utility Class");
	}

	public static Map<String, String> getInterfaceAddress() {
		Map<String, String> inetDetails = new HashMap<>();
		Enumeration<NetworkInterface> adapters;
		try {
			adapters = NetworkInterface.getNetworkInterfaces();
			while(adapters.hasMoreElements()) {
				NetworkInterface currentAdapter = adapters.nextElement();
				if(!currentAdapter.isLoopback() && currentAdapter.isUp() && !currentAdapter.isVirtual()) {
					Enumeration<InetAddress> ip = currentAdapter.getInetAddresses();
					while(ip.hasMoreElements()) {
						InetAddress currentInet = ip.nextElement();
						if(!currentInet.isLoopbackAddress() && currentInet instanceof Inet4Address) {
							inetDetails.put(currentAdapter.getDisplayName(), currentInet.getHostAddress());
						}
					}
				}
			}
		} catch (SocketException e) {
			new ExceptionUI("Network IP Fetch Error", e.getMessage()).setVisible(true);
			return Collections.emptyMap();
		}
		return inetDetails;
	}
}
