package com.egg.ferrumldb.hardwareid;

import java.util.concurrent.ExecutionException;

import com.ferruml.system.hardware.HWID;

public class HardwareId {

	public static void main(String[] args) {
		try {
			System.out.println(HWID.getHardwareID());
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
