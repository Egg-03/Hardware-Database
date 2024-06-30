package com.egg.ferrumldb.ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.egg.errorui.ExceptionUI;
import com.ferruml.system.hardware.Win32_VideoController;

final class VideoController {
	
	private VideoController() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeVideoController(JComboBox<String> gpuNumberChoice, JTextField...gpuFields) {
		List<String> gpus;
		try {
			gpus = Win32_VideoController.getGPUID();
			for(String gpu:gpus) {
				gpuNumberChoice.addItem(gpu);
			}
			Map<String, String> gpuProperties = Win32_VideoController.getGPU(gpuNumberChoice.getItemAt(gpuNumberChoice.getSelectedIndex()));
			gpuFields[0].setText(gpuProperties.get("Name"));
			Long adapterRam = Long.parseLong(gpuProperties.get("AdapterRAM"))/(1024*1024);
			gpuFields[1].setText(String.valueOf(adapterRam)+" MB");
			gpuFields[2].setText(gpuProperties.get("DriverVersion"));
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("GPU Error", e.getMessage()).setVisible(true);
		}
		
		
		gpuNumberChoice.addActionListener(e-> {
			Map<String, String> gpuProperties;
			try {
				gpuProperties = Win32_VideoController.getGPU(gpuNumberChoice.getItemAt(gpuNumberChoice.getSelectedIndex()));
					
				gpuFields[0].setText(gpuProperties.get("Name"));
					
				Long adapterRam = Long.parseLong(gpuProperties.get("AdapterRAM"))/(1024*1024);
				gpuFields[1].setText(String.valueOf(adapterRam)+" MB");
					
				gpuFields[2].setText(gpuProperties.get("DriverVersion"));
			} catch (IndexOutOfBoundsException | IOException e1) {
				new ExceptionUI("GPU Error", e1.getMessage()).setVisible(true);
			} catch (NumberFormatException e2) {
				/*This is a unique case where removing all items from the gpuNumberChoice box triggers this action listener
				 * and since the combo box returns null string, the Long.parseLong cannot parse a null string for GPU RAM, thereby throwing this exception
				 * This is a fix since there are no specific action listeners for JComboBox*/
				gpuFields[1].setText("0 MB");
			}
		});
	}
}
