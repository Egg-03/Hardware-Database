package com.hardcache.frontend.mainui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;

import com.ferrumx.system.hardware.Win32_PhysicalMemory;
import com.hardcache.frontend.miniuis.ExceptionUI;

final class Memory {
	
	private Memory() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static boolean initializeMemory(JTextField...memoryFields){
		List<String> memoryList;
		try {
			memoryList = Win32_PhysicalMemory.getTag();
			Integer slotCount = memoryList.size();
			Long totalSize = 0L;
			memoryFields[0].setText(Integer.toString(slotCount));
			
			Map<String, String> memoryProperties = Collections.emptyMap();
			for(String memory: memoryList) {
				memoryProperties = Win32_PhysicalMemory.getMemory(memory);
				totalSize+= Long.parseLong(memoryProperties.get("Capacity"));
			}
			
			memoryFields[1].setText(String.valueOf(totalSize/(1024*1024))+" MB");
			return true;
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Memory Error", e.getMessage()).setVisible(true);
			return false;
		}
		
	}

}
