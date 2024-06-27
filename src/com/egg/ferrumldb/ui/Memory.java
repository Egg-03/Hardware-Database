package com.egg.ferrumldb.ui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;

import com.ferruml.system.hardware.Win32_PhysicalMemory;

final class Memory {
	
	private Memory() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeMemory(JTextField...memoryFields){
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
		} catch (IndexOutOfBoundsException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
