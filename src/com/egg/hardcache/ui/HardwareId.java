package com.egg.hardcache.ui;

import javax.swing.JTextField;

import java.util.concurrent.ExecutionException;

import com.egg.miniuis.ExceptionUI;
import com.ferrumx.system.hardware.HardwareID;

final class HardwareId {
	
	private HardwareId() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static boolean initializeHardwareId(JTextField hardwareIdTextField){
		try {
			hardwareIdTextField.setText(HardwareID.getHardwareID());
			hardwareIdTextField.setCaretPosition(0);
			return true;
		} catch (ExecutionException | InterruptedException e) {
			new ExceptionUI("HWID Error", e.getMessage()).setVisible(true);
			Thread.currentThread().interrupt();
			return false;
		}
	}
}
