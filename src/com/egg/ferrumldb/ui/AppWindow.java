package com.egg.ferrumldb.ui;

import java.awt.EventQueue;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.ferruml.system.currentuser.User;
import com.ferruml.system.hardware.HWID;
import com.ferruml.system.hardware.Win32_Processor;
import com.ferruml.system.operatingsystem.Win32_OperatingSystem;

import java.awt.Color;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class AppWindow {

	private JFrame feldbdmp;
	private JTextArea hardwareIdTextField;
	private JComboBox<String> osNameChoice;
	private JComboBox<String> cpuNumberChoice;
	private JTextField cpuNameTextField;
	private JTextField osArchTextField;
	private JTextField deviceNameTextField;
	private JTextField currentUserTextField;
	private JTextField cpuSocketTextField;
	private JTextField cpuCoreTextField;
	private JTextField cpuThreadTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.feldbdmp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 * @throws IOException 
	 * @throws IndexOutOfBoundsException 
	 */
	public AppWindow() throws ExecutionException, InterruptedException, IndexOutOfBoundsException, IOException {
		initialize();
		initializeHardwareId();
		initializeOs();
		initializeCpu();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		feldbdmp = new JFrame();
		feldbdmp.setIconImage(Toolkit.getDefaultToolkit().getImage(AppWindow.class.getResource("/res/ferrum_legacy-8.png")));
		feldbdmp.setTitle("FerrumL DBDump Tool Snapshot v0.0.1");
		feldbdmp.setBounds(100, 100, 450, 490);
		feldbdmp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		feldbdmp.getContentPane().setLayout(null);
		
		JPanel hardwareIdPanel = new JPanel();
		hardwareIdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Unique ID", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(192, 192, 192)));
		hardwareIdPanel.setBounds(10, 11, 414, 80);
		feldbdmp.getContentPane().add(hardwareIdPanel);
		hardwareIdPanel.setLayout(null);
		
		JLabel hardwareLabel = new JLabel("Hardware ID");
		hardwareLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		hardwareLabel.setBounds(21, 29, 73, 27);
		hardwareIdPanel.add(hardwareLabel);
		
		hardwareIdTextField = new JTextArea();
		hardwareIdTextField.setBackground(Color.DARK_GRAY);
		hardwareIdTextField.setLineWrap(true);
		hardwareIdTextField.setForeground(Color.MAGENTA);
		hardwareIdTextField.setFont(new Font("Segoe UI Variable", Font.BOLD | Font.ITALIC, 10));
		hardwareIdTextField.setEditable(false);
		hardwareIdTextField.setBounds(104, 19, 300, 50);
		hardwareIdPanel.add(hardwareIdTextField);
		hardwareIdTextField.setColumns(10);
		
		JPanel osPanel = new JPanel();
		osPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Operating System", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		osPanel.setBounds(10, 93, 412, 90);
		feldbdmp.getContentPane().add(osPanel);
		osPanel.setLayout(null);
		
		JLabel osName = new JLabel("Name");
		osName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osName.setBounds(12, 22, 30, 24);
		osPanel.add(osName);
		
		osNameChoice = new JComboBox<>();
		osNameChoice.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		osNameChoice.setEditable(false);
		osNameChoice.setBounds(48, 22, 204, 24);
		osPanel.add(osNameChoice);
		
		JLabel osArch = new JLabel("Architecture");
		osArch.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osArch.setBounds(270, 21, 64, 24);
		osPanel.add(osArch);
		
		osArchTextField = new JTextField();
		osArchTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		osArchTextField.setEditable(false);
		osArchTextField.setColumns(10);
		osArchTextField.setBounds(344, 21, 54, 24);
		osPanel.add(osArchTextField);
		
		JLabel deviceName = new JLabel("Device Name");
		deviceName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		deviceName.setBounds(12, 53, 70, 24);
		osPanel.add(deviceName);
		
		deviceNameTextField = new JTextField();
		deviceNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		deviceNameTextField.setEditable(false);
		deviceNameTextField.setColumns(10);
		deviceNameTextField.setBounds(84, 54, 135, 24);
		osPanel.add(deviceNameTextField);
		
		JLabel currentUser = new JLabel("Current User");
		currentUser.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		currentUser.setBounds(222, 55, 70, 24);
		osPanel.add(currentUser);
		
		currentUserTextField = new JTextField();
		currentUserTextField.setHorizontalAlignment(SwingConstants.CENTER);
		currentUserTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		currentUserTextField.setEditable(false);
		currentUserTextField.setColumns(10);
		currentUserTextField.setBounds(294, 54, 104, 24);
		osPanel.add(currentUserTextField);
		
		JPanel cpuPanel = new JPanel();
		cpuPanel.setLayout(null);
		cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		cpuPanel.setBounds(10, 185, 414, 80);
		feldbdmp.getContentPane().add(cpuPanel);
		
		JLabel cpuName = new JLabel("Name");
		cpuName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuName.setBounds(111, 20, 30, 20);
		cpuPanel.add(cpuName);
		
		cpuNameTextField = new JTextField();
		cpuNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuNameTextField.setEditable(false);
		cpuNameTextField.setBounds(148, 20, 243, 22);
		cpuPanel.add(cpuNameTextField);
		
		JLabel cpuSocket = new JLabel("Socket");
		cpuSocket.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuSocket.setBounds(288, 49, 46, 20);
		cpuPanel.add(cpuSocket);
		
		cpuSocketTextField = new JTextField();
		cpuSocketTextField.setText((String) null);
		cpuSocketTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuSocketTextField.setEditable(false);
		cpuSocketTextField.setColumns(10);
		cpuSocketTextField.setBounds(334, 50, 57, 22);
		cpuPanel.add(cpuSocketTextField);
		
		JLabel cpuCoreCount = new JLabel("Cores");
		cpuCoreCount.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuCoreCount.setBounds(12, 49, 30, 20);
		cpuPanel.add(cpuCoreCount);
		
		cpuCoreTextField = new JTextField();
		cpuCoreTextField.setText((String) null);
		cpuCoreTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuCoreTextField.setEditable(false);
		cpuCoreTextField.setColumns(10);
		cpuCoreTextField.setBounds(48, 50, 30, 22);
		cpuPanel.add(cpuCoreTextField);
		
		JLabel cpuThreadCount = new JLabel("Threads");
		cpuThreadCount.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuThreadCount.setBounds(85, 49, 46, 20);
		cpuPanel.add(cpuThreadCount);
		
		cpuThreadTextField = new JTextField();
		cpuThreadTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuThreadTextField.setEditable(false);
		cpuThreadTextField.setColumns(10);
		cpuThreadTextField.setBounds(133, 50, 30, 22);
		cpuPanel.add(cpuThreadTextField);
		
		JLabel cpuNumber = new JLabel("CPU#");
		cpuNumber.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuNumber.setBounds(12, 20, 30, 20);
		cpuPanel.add(cpuNumber);
		
		cpuNumberChoice = new JComboBox<String>();
		cpuNumberChoice.setMaximumRowCount(4);
		cpuNumberChoice.setFont(new Font("Segoe UI", Font.ITALIC, 9));
		cpuNumberChoice.setEditable(false);
		cpuNumberChoice.setBounds(48, 20, 57, 22);
		cpuPanel.add(cpuNumberChoice);
	}
	
	private void initializeHardwareId() throws ExecutionException, InterruptedException {
		hardwareIdTextField.setText(HWID.getHardwareID());
	}
	
	private void initializeOs() throws IndexOutOfBoundsException, IOException {
		List<String> osNames = Win32_OperatingSystem.getOSList();
		for(String osName: osNames) {
			osNameChoice.addItem(osName);
		}
		Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osNameChoice.getItemAt(osNameChoice.getSelectedIndex()));
		deviceNameTextField.setText(osProperties.get("CSName"));
		osArchTextField.setText(osProperties.get("OSArchitecture"));
		currentUserTextField.setText(User.getUsername());
		
		//add an action listener for when the user selects a different OS for multi-boot Systems
		osNameChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map<String, String> osProperties = Win32_OperatingSystem.getOSInfo(osNameChoice.getItemAt(osNameChoice.getSelectedIndex()));
					deviceNameTextField.setText(osProperties.get("CSName"));
					osArchTextField.setText(osProperties.get("OSArchitecture"));
					currentUserTextField.setText(User.getUsername());
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO I WILL DO NOTHING NOOO NOTHING NEVER EVER I WILL BECOME A FARMER
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void initializeCpu() throws IndexOutOfBoundsException, IOException {
		List<String> cpuNames = Win32_Processor.getProcessorList();
		for(String cpuName: cpuNames) {
			cpuNumberChoice.addItem(cpuName);
		}
		Map<String, String> cpuProperties = Win32_Processor.getCurrentProcessor(cpuNumberChoice.getItemAt(cpuNumberChoice.getSelectedIndex()));
		cpuNameTextField.setText(cpuProperties.get("Name"));
		cpuCoreTextField.setText(cpuProperties.get("NumberOfCores"));
		cpuThreadTextField.setText(cpuProperties.get("ThreadCount"));
		cpuSocketTextField.setText(cpuProperties.get("SocketDesignation"));
		
		//add an action listener for CPU# for when the user selects a different CPU in multi-CPU systems
		cpuNumberChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> cpuProperties;
				try {
					cpuProperties = Win32_Processor.getCurrentProcessor(cpuNumberChoice.getItemAt(cpuNumberChoice.getSelectedIndex()));
					cpuNameTextField.setText(cpuProperties.get("Name"));
					cpuCoreTextField.setText(cpuProperties.get("NumberOfCores"));
					cpuThreadTextField.setText(cpuProperties.get("ThreadCount"));
					cpuSocketTextField.setText(cpuProperties.get("SocketDesignation"));
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO KILL ME PLS (I'M LYING I DONT WANT TO DIE YET)
					e1.printStackTrace();
				}
			}
			
		});
		
	}
}
