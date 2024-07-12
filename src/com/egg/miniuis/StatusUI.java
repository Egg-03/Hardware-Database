package com.egg.miniuis;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StatusUI extends JFrame{
	private static final long serialVersionUID = -3760065141062868042L;
	private JLabel hardwareLabel;
	private JLabel cpuLabel;
	private JLabel networkLabel;
	private JLabel osLabel;
	private JLabel memoryLabel;
	private JLabel mainboardLabel;
	private JLabel gpuLabel;
	private JLabel storageLabel;
	
	public StatusUI() {
		setResizable(false);
		setTitle("Booting Up");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatusUI.class.getResource("/res/icon_main.png")));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		statusPanel.setBounds(10, 11, 414, 139);
		getContentPane().add(statusPanel);
		statusPanel.setLayout(null);
		
		JLabel infoLabel = new JLabel("Please wait while FerrumX gathers information about your system");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(10, 11, 394, 24);
		statusPanel.add(infoLabel);
		
		addLabels(statusPanel);
	}
	
	private void addLabels(JPanel statusPanel) {
		
		hardwareLabel = new JLabel("HWID");
		hardwareLabel.setBounds(20, 47, 60, 20);
		statusPanel.add(hardwareLabel);
		
		cpuLabel = new JLabel("CPU");
		cpuLabel.setBounds(20, 79, 60, 20);
		statusPanel.add(cpuLabel);
		
		networkLabel = new JLabel("Network");
		networkLabel.setBounds(325, 47, 60, 20);
		statusPanel.add(networkLabel);
		
		osLabel = new JLabel("OS");
		osLabel.setBounds(325, 79, 60, 20);
		statusPanel.add(osLabel);
		
		memoryLabel = new JLabel("Memory");
		memoryLabel.setBounds(115, 47, 60, 20);
		statusPanel.add(memoryLabel);
		
		mainboardLabel = new JLabel("Mainboard");
		mainboardLabel.setBounds(218, 47, 60, 20);
		statusPanel.add(mainboardLabel);
		
		gpuLabel = new JLabel("GPU");
		gpuLabel.setBounds(115, 79, 60, 20);
		statusPanel.add(gpuLabel);
		
		storageLabel = new JLabel("Storage");
		storageLabel.setBounds(218, 79, 60, 20);
		statusPanel.add(storageLabel);
	}

	public void setHardwareLabel() {
		this.hardwareLabel.setText("HWID ✓");
	}

	public void setCpuLabel() {
		this.cpuLabel.setText("CPU ✓");
	}

	public void setNetworkLabel() {
		this.networkLabel.setText("Network ✓");
	}

	public void setOsLabel() {
		this.osLabel.setText("OS ✓");
	}

	public void setMemoryLabel() {
		this.memoryLabel.setText("Memory ✓");
	}

	public void setMainboardLabel() {
		this.mainboardLabel.setText("Mainboard ✓");
	}

	public void setGpuLabel() {
		this.gpuLabel.setText("GPU ✓");
	}

	public void setStorageLabel() {
		this.storageLabel.setText("Storage ✓");
	}
	
}
