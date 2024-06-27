package com.egg.ferrumldb.ui;

import javax.swing.JTextField;

import java.util.concurrent.ExecutionException;

import com.ferruml.system.hardware.HWID;

final class HardwareId {
	
	private HardwareId() {
		throw new IllegalStateException("Class method to be used only in the main frame class");
	}
	
	protected static void initializeHardwareId(JTextField hardwareIdTextField) throws ExecutionException, InterruptedException {
		hardwareIdTextField.setText(HWID.getHardwareID());
		hardwareIdTextField.setCaretPosition(0);
	}
}
