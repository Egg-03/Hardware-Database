package com.egg.ferrumldb.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.ferruml.system.hardware.Win32_Processor;

final class Cpu {
	
	private Cpu() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeCpu(JComboBox<String> cpuNumberChoice, JTextField...cpuFields) throws IndexOutOfBoundsException, IOException {
		List<String> cpuNames = Win32_Processor.getProcessorList();
		for(String cpuName: cpuNames) {
			cpuNumberChoice.addItem(cpuName);
		}
		Map<String, String> cpuProperties = Win32_Processor.getCurrentProcessor(cpuNumberChoice.getItemAt(cpuNumberChoice.getSelectedIndex()));
		
		cpuFields[0].setText(cpuProperties.get("Name"));
		cpuFields[1].setText(cpuProperties.get("NumberOfCores"));
		cpuFields[2].setText(cpuProperties.get("ThreadCount"));
		cpuFields[3].setText(cpuProperties.get("SocketDesignation"));
		
		//add an action listener for CPU# for when the user selects a different CPU in multi-CPU systems
		cpuNumberChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> cpuProperties;
				try {
					cpuProperties = Win32_Processor.getCurrentProcessor(cpuNumberChoice.getItemAt(cpuNumberChoice.getSelectedIndex()));
					cpuFields[0].setText(cpuProperties.get("Name"));
					cpuFields[1].setText(cpuProperties.get("NumberOfCores"));
					cpuFields[2].setText(cpuProperties.get("ThreadCount"));
					cpuFields[3].setText(cpuProperties.get("SocketDesignation"));
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO KILL ME PLS (I'M LYING I DONT WANT TO DIE YET)
					e1.printStackTrace();
				}
			}
			
		});
		
	}

}
