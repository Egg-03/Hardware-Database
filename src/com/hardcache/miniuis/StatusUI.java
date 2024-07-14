package com.hardcache.miniuis;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.ferrumx.system.logger.ErrorLog;

import java.awt.Toolkit;

public class StatusUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel hardwareLabel;
	private JLabel cpuLabel;
	private JLabel networkLabel;
	private JLabel osLabel;
	private JLabel memoryLabel;
	private JLabel mainboardLabel;
	private JLabel gpuLabel;
	private JLabel storageLabel;


	/**
	 * Create the frame.
	 */
	public StatusUI(String title, String message) {
		setResizable(false);
		SwingUtilities.invokeLater(()->{
			setTheme();
			initialize(title, message);
		});
		
	}
	
	private void setTheme() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorLog().log(" ExceptionUI Theme Load Error: "+e.getMessage());
		}
	}
	
	private void initialize(String title, String message) {
		JPanel contentPane;
		setVisible(true);
		setTitle(title);
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatusUI.class.getResource("/res/icon_main.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		statusPanel.setBounds(10, 11, 414, 139);
		getContentPane().add(statusPanel);
		statusPanel.setLayout(null);
		
		JLabel infoLabel = new JLabel(message);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(10, 11, 394, 24);
		statusPanel.add(infoLabel);
		
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
		mainboardLabel.setBounds(218, 47, 80, 20);
		statusPanel.add(mainboardLabel);
		
		gpuLabel = new JLabel("GPU");
		gpuLabel.setBounds(115, 79, 60, 20);
		statusPanel.add(gpuLabel);
		
		storageLabel = new JLabel("Storage");
		storageLabel.setBounds(218, 79, 60, 20);
		statusPanel.add(storageLabel);
	}
	
	public void setHardwareLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.hardwareLabel.setText("HWID ✓");
			
	}

	public void setCpuLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.cpuLabel.setText("CPU ✓");
	}

	public void setNetworkLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.networkLabel.setText("Network ✓");
	}

	public void setOsLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.osLabel.setText("OS ✓");
	}

	public void setMemoryLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.memoryLabel.setText("Memory ✓");
	}

	public void setMainboardLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.mainboardLabel.setText("Mainboard ✓");
	}

	public void setGpuLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.gpuLabel.setText("GPU ✓");
	}

	public void setStorageLabel(Boolean status) {
		if(Boolean.TRUE.equals(status))
			this.storageLabel.setText("Storage ✓");
	}

}
