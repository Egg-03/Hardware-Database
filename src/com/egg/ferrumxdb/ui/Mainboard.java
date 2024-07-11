package com.egg.ferrumxdb.ui;

import java.io.IOException;
import java.util.Map;

import javax.swing.JTextField;

import com.egg.miniuis.ExceptionUI;
import com.ferrumx.system.hardware.Win32_BIOS;
import com.ferrumx.system.hardware.Win32_Baseboard;

final class Mainboard {
	
	private Mainboard() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeMainboard(JTextField...mainboardFields) {
		Map<String, String> mainboard;
		try {
			mainboard = Win32_Baseboard.getMotherboard();
			Map<String, String> bios = Win32_BIOS.getPrimaryBIOS();
			
			mainboardFields[0].setText(mainboard.get("Product"));
			mainboardFields[1].setText(mainboard.get("Manufacturer"));
			mainboardFields[2].setText(bios.get("Caption"));
			System.out.println("Mainboard Success");
		} catch (IndexOutOfBoundsException | IOException e) {
			new ExceptionUI("Mainboard Error", e.getMessage()).setVisible(true);
		}
	}
}
