package com.egg.ferrumldb.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.ferruml.system.currentuser.User;
import com.ferruml.system.hardware.HWID;
import com.ferruml.system.hardware.Win32_BIOS;
import com.ferruml.system.hardware.Win32_Baseboard;
import com.ferruml.system.hardware.Win32_DiskDrive;
import com.ferruml.system.hardware.Win32_PhysicalMemory;
import com.ferruml.system.hardware.Win32_Processor;
import com.ferruml.system.hardware.Win32_VideoController;
import com.ferruml.system.network.Win32_NetworkAdapter;
import com.ferruml.system.operatingsystem.Win32_OperatingSystem;

public class AppWindow {

	private JFrame feldbdmp;
	private JTextField hardwareIdTextField;
	private JComboBox<String> osNameChoice;
	private JComboBox<String> cpuNumberChoice;
	private JComboBox<String> gpuNumberChoice;
	private JComboBox<String> connectionIdChoice;
	private JComboBox<String> storageNameChoice;
	private JTextField cpuNameTextField;
	private JTextField osArchTextField;
	private JTextField deviceNameTextField;
	private JTextField currentUserTextField;
	private JTextField cpuSocketTextField;
	private JTextField cpuCoreTextField;
	private JTextField cpuThreadTextField;
	private JTextField memorySlotTextField;
	private JTextField totalMemoryTextField;
	private JTextField gpuNameTextField;
	private JTextField gpuVramTextField;
	private JTextField gpuDriverVersionTextField;
	private JTextField mainboardNameTextField;
	private JTextField mainboardManufacturerTextField;
	private JTextField biosVersionTextField;
	private JTextField networkDescriptionTextField;
	private JTextField networkMacTextField;
	private JTextField storageSerialTextField;
	private JTextField storageSizeTextField;
	private JTextField storageSmartTextField;

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
					window.feldbdmp.setLocationRelativeTo(null);
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
		initializeMemory();
		initializeVideoController();
		initializeMainboard();
		initializeNetwork();
		initializeStorage();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		feldbdmp = new JFrame();
		feldbdmp.setResizable(false);
		feldbdmp.setIconImage(Toolkit.getDefaultToolkit().getImage(AppWindow.class.getResource("/res/ferrum_legacy-8.png")));
		feldbdmp.setTitle("FerrumL DBDump Tool Snapshot v26062024");
		feldbdmp.setBounds(100, 100, 450, 721);
		feldbdmp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		feldbdmp.getContentPane().setLayout(null);
		
		JPanel hardwareIdPanel = new JPanel();
		hardwareIdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Unique ID", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(192, 192, 192)));
		hardwareIdPanel.setBounds(10, 11, 414, 80);
		feldbdmp.getContentPane().add(hardwareIdPanel);
		hardwareIdPanel.setLayout(null);
		
		JLabel hardwareLabel = new JLabel("Hardware ID");
		hardwareLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		hardwareLabel.setBounds(17, 18, 73, 27);
		hardwareIdPanel.add(hardwareLabel);
		
		hardwareIdTextField = new JTextField();
		hardwareIdTextField.setBackground(Color.DARK_GRAY);
		hardwareIdTextField.setForeground(Color.MAGENTA);
		hardwareIdTextField.setFont(new Font("Segoe UI Variable", Font.BOLD | Font.ITALIC, 10));
		hardwareIdTextField.setEditable(false);
		hardwareIdTextField.setBounds(102, 18, 300, 27);
		hardwareIdPanel.add(hardwareIdTextField);
		hardwareIdTextField.setColumns(10);
		
		JButton placeholder = new JButton("Placeholder");
		placeholder.setBounds(17, 48, 96, 20);
		hardwareIdPanel.add(placeholder);
		
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
		osArch.setBounds(270, 22, 64, 24);
		osPanel.add(osArch);
		
		osArchTextField = new JTextField();
		osArchTextField.setHorizontalAlignment(SwingConstants.CENTER);
		osArchTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		osArchTextField.setEditable(false);
		osArchTextField.setColumns(10);
		osArchTextField.setBounds(344, 22, 54, 24);
		osPanel.add(osArchTextField);
		
		JLabel deviceName = new JLabel("Device Name");
		deviceName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		deviceName.setBounds(12, 54, 70, 24);
		osPanel.add(deviceName);
		
		deviceNameTextField = new JTextField();
		deviceNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		deviceNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		deviceNameTextField.setEditable(false);
		deviceNameTextField.setColumns(10);
		deviceNameTextField.setBounds(84, 54, 135, 24);
		osPanel.add(deviceNameTextField);
		
		JLabel currentUser = new JLabel("Current User");
		currentUser.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		currentUser.setBounds(222, 54, 70, 24);
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
		cpuName.setBounds(111, 20, 30, 23);
		cpuPanel.add(cpuName);
		
		cpuNameTextField = new JTextField();
		cpuNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cpuNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuNameTextField.setEditable(false);
		cpuNameTextField.setBounds(148, 20, 243, 24);
		cpuPanel.add(cpuNameTextField);
		
		JLabel cpuSocket = new JLabel("Socket");
		cpuSocket.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuSocket.setBounds(288, 49, 46, 22);
		cpuPanel.add(cpuSocket);
		
		cpuSocketTextField = new JTextField();
		cpuSocketTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cpuSocketTextField.setText((String) null);
		cpuSocketTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuSocketTextField.setEditable(false);
		cpuSocketTextField.setColumns(10);
		cpuSocketTextField.setBounds(334, 50, 57, 22);
		cpuPanel.add(cpuSocketTextField);
		
		JLabel cpuCoreCount = new JLabel("Cores");
		cpuCoreCount.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuCoreCount.setBounds(12, 49, 30, 22);
		cpuPanel.add(cpuCoreCount);
		
		cpuCoreTextField = new JTextField();
		cpuCoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cpuCoreTextField.setText((String) null);
		cpuCoreTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuCoreTextField.setEditable(false);
		cpuCoreTextField.setColumns(10);
		cpuCoreTextField.setBounds(48, 50, 30, 22);
		cpuPanel.add(cpuCoreTextField);
		
		JLabel cpuThreadCount = new JLabel("Threads");
		cpuThreadCount.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuThreadCount.setBounds(85, 49, 46, 22);
		cpuPanel.add(cpuThreadCount);
		
		cpuThreadTextField = new JTextField();
		cpuThreadTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cpuThreadTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpuThreadTextField.setEditable(false);
		cpuThreadTextField.setColumns(10);
		cpuThreadTextField.setBounds(133, 50, 30, 22);
		cpuPanel.add(cpuThreadTextField);
		
		JLabel cpuNumber = new JLabel("CPU#");
		cpuNumber.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuNumber.setBounds(12, 20, 30, 22);
		cpuPanel.add(cpuNumber);
		
		cpuNumberChoice = new JComboBox<>();
		cpuNumberChoice.setMaximumRowCount(4);
		cpuNumberChoice.setFont(new Font("Segoe UI", Font.ITALIC, 9));
		cpuNumberChoice.setEditable(false);
		cpuNumberChoice.setBounds(48, 20, 57, 22);
		cpuPanel.add(cpuNumberChoice);
		
		JPanel memoryPanel = new JPanel();
		memoryPanel.setLayout(null);
		memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		memoryPanel.setBounds(10, 265, 414, 44);
		feldbdmp.getContentPane().add(memoryPanel);
		
		JLabel memorySlots = new JLabel("Slots Used");
		memorySlots.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		memorySlots.setBounds(12, 14, 66, 22);
		memoryPanel.add(memorySlots);
		
		memorySlotTextField = new JTextField();
		memorySlotTextField.setHorizontalAlignment(SwingConstants.CENTER);
		memorySlotTextField.setText((String) null);
		memorySlotTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		memorySlotTextField.setEditable(false);
		memorySlotTextField.setColumns(10);
		memorySlotTextField.setBounds(80, 14, 46, 22);
		memoryPanel.add(memorySlotTextField);
		
		JLabel totalMemory = new JLabel("Total Memory");
		totalMemory.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		totalMemory.setBounds(207, 14, 81, 22);
		memoryPanel.add(totalMemory);
		
		totalMemoryTextField = new JTextField();
		totalMemoryTextField.setHorizontalAlignment(SwingConstants.CENTER);
		totalMemoryTextField.setText((String) null);
		totalMemoryTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		totalMemoryTextField.setEditable(false);
		totalMemoryTextField.setColumns(10);
		totalMemoryTextField.setBounds(288, 14, 100, 22);
		memoryPanel.add(totalMemoryTextField);
		
		JPanel gpuPanel = new JPanel();
		gpuPanel.setLayout(null);
		gpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Video Controller", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		gpuPanel.setBounds(10, 310, 412, 90);
		feldbdmp.getContentPane().add(gpuPanel);
		
		JLabel gpuNumber = new JLabel("GPU#");
		gpuNumber.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		gpuNumber.setBounds(12, 22, 30, 24);
		gpuPanel.add(gpuNumber);
		
		gpuNumberChoice = new JComboBox<>();
		gpuNumberChoice.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		gpuNumberChoice.setEditable(false);
		gpuNumberChoice.setBounds(48, 22, 70, 24);
		gpuPanel.add(gpuNumberChoice);
		
		JLabel gpuName = new JLabel("Name");
		gpuName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		gpuName.setBounds(125, 22, 35, 24);
		gpuPanel.add(gpuName);
		
		gpuNameTextField = new JTextField();
		gpuNameTextField.setText((String) null);
		gpuNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		gpuNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		gpuNameTextField.setEditable(false);
		gpuNameTextField.setColumns(10);
		gpuNameTextField.setBounds(166, 22, 232, 24);
		gpuPanel.add(gpuNameTextField);
		
		JLabel gpuVram = new JLabel("VRAM");
		gpuVram.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		gpuVram.setBounds(12, 54, 35, 24);
		gpuPanel.add(gpuVram);
		
		gpuVramTextField = new JTextField();
		gpuVramTextField.setText((String) null);
		gpuVramTextField.setHorizontalAlignment(SwingConstants.CENTER);
		gpuVramTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		gpuVramTextField.setEditable(false);
		gpuVramTextField.setColumns(10);
		gpuVramTextField.setBounds(48, 55, 135, 24);
		gpuPanel.add(gpuVramTextField);
		
		JLabel gpuDriverVersion = new JLabel("Driver Version");
		gpuDriverVersion.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		gpuDriverVersion.setBounds(188, 54, 90, 24);
		gpuPanel.add(gpuDriverVersion);
		
		gpuDriverVersionTextField = new JTextField();
		gpuDriverVersionTextField.setHorizontalAlignment(SwingConstants.CENTER);
		gpuDriverVersionTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		gpuDriverVersionTextField.setEditable(false);
		gpuDriverVersionTextField.setColumns(10);
		gpuDriverVersionTextField.setBounds(283, 54, 115, 24);
		gpuPanel.add(gpuDriverVersionTextField);
		
		JPanel mainboardPanel = new JPanel();
		mainboardPanel.setLayout(null);
		mainboardPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mainboard", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		mainboardPanel.setBounds(10, 402, 412, 90);
		feldbdmp.getContentPane().add(mainboardPanel);
		
		JLabel mainboardName = new JLabel("Mainboard Name");
		mainboardName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		mainboardName.setBounds(12, 21, 101, 24);
		mainboardPanel.add(mainboardName);
		
		mainboardNameTextField = new JTextField();
		mainboardNameTextField.setText((String) null);
		mainboardNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		mainboardNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mainboardNameTextField.setEditable(false);
		mainboardNameTextField.setColumns(10);
		mainboardNameTextField.setBounds(123, 22, 275, 24);
		mainboardPanel.add(mainboardNameTextField);
		
		JLabel mainboardManufacturer = new JLabel("Manufacturer");
		mainboardManufacturer.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		mainboardManufacturer.setBounds(12, 54, 75, 24);
		mainboardPanel.add(mainboardManufacturer);
		
		mainboardManufacturerTextField = new JTextField();
		mainboardManufacturerTextField.setText((String) null);
		mainboardManufacturerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		mainboardManufacturerTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mainboardManufacturerTextField.setEditable(false);
		mainboardManufacturerTextField.setColumns(10);
		mainboardManufacturerTextField.setBounds(90, 55, 173, 24);
		mainboardPanel.add(mainboardManufacturerTextField);
		
		JLabel biosVersion = new JLabel("BIOS Ver.");
		biosVersion.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		biosVersion.setBounds(268, 54, 67, 24);
		mainboardPanel.add(biosVersion);
		
		biosVersionTextField = new JTextField();
		biosVersionTextField.setText((String) null);
		biosVersionTextField.setHorizontalAlignment(SwingConstants.CENTER);
		biosVersionTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		biosVersionTextField.setEditable(false);
		biosVersionTextField.setColumns(10);
		biosVersionTextField.setBounds(338, 54, 60, 24);
		mainboardPanel.add(biosVersionTextField);
		
		JPanel network = new JPanel();
		network.setLayout(null);
		network.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Network", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		network.setBounds(10, 492, 412, 90);
		feldbdmp.getContentPane().add(network);
		
		JLabel netConnectionId = new JLabel("Connection ID");
		netConnectionId.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		netConnectionId.setBounds(12, 21, 75, 24);
		network.add(netConnectionId);
		
		connectionIdChoice = new JComboBox<>();
		connectionIdChoice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		connectionIdChoice.setEditable(false);
		connectionIdChoice.setBounds(90, 22, 60, 24);
		network.add(connectionIdChoice);
		
		JLabel networkDescription = new JLabel("Description");
		networkDescription.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		networkDescription.setBounds(12, 54, 60, 24);
		network.add(networkDescription);
		
		networkDescriptionTextField = new JTextField();
		networkDescriptionTextField.setText((String) null);
		networkDescriptionTextField.setHorizontalAlignment(SwingConstants.CENTER);
		networkDescriptionTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		networkDescriptionTextField.setEditable(false);
		networkDescriptionTextField.setColumns(10);
		networkDescriptionTextField.setBounds(78, 55, 322, 24);
		network.add(networkDescriptionTextField);
		
		JLabel networkMac = new JLabel("MAC Address");
		networkMac.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		networkMac.setBounds(168, 21, 75, 24);
		network.add(networkMac);
		
		networkMacTextField = new JTextField();
		networkMacTextField.setText((String) null);
		networkMacTextField.setHorizontalAlignment(SwingConstants.CENTER);
		networkMacTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		networkMacTextField.setEditable(false);
		networkMacTextField.setColumns(10);
		networkMacTextField.setBounds(247, 22, 153, 24);
		network.add(networkMacTextField);
		
		JPanel storage = new JPanel();
		storage.setLayout(null);
		storage.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Storage", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		storage.setBounds(10, 583, 412, 90);
		feldbdmp.getContentPane().add(storage);
		
		JLabel storageName = new JLabel("Caption");
		storageName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageName.setBounds(12, 21, 47, 24);
		storage.add(storageName);
		
		storageNameChoice = new JComboBox<>();
		storageNameChoice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageNameChoice.setEditable(false);
		storageNameChoice.setBounds(62, 21, 186, 24);
		storage.add(storageNameChoice);
		
		JLabel storageSerial = new JLabel("Serial");
		storageSerial.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageSerial.setBounds(12, 54, 37, 24);
		storage.add(storageSerial);
		
		storageSerialTextField = new JTextField();
		storageSerialTextField.setText((String) null);
		storageSerialTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageSerialTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageSerialTextField.setEditable(false);
		storageSerialTextField.setColumns(10);
		storageSerialTextField.setBounds(52, 54, 230, 24);
		storage.add(storageSerialTextField);
		
		JLabel storageSize = new JLabel("Size");
		storageSize.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageSize.setBounds(256, 21, 26, 24);
		storage.add(storageSize);
		
		storageSizeTextField = new JTextField();
		storageSizeTextField.setText((String) null);
		storageSizeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageSizeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageSizeTextField.setEditable(false);
		storageSizeTextField.setColumns(10);
		storageSizeTextField.setBounds(289, 22, 111, 24);
		storage.add(storageSizeTextField);
		
		JLabel storageSmartStatus = new JLabel("S.M.A.R.T");
		storageSmartStatus.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageSmartStatus.setBounds(289, 54, 48, 24);
		storage.add(storageSmartStatus);
		
		storageSmartTextField = new JTextField();
		storageSmartTextField.setText((String) null);
		storageSmartTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageSmartTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageSmartTextField.setEditable(false);
		storageSmartTextField.setColumns(10);
		storageSmartTextField.setBounds(342, 54, 58, 24);
		storage.add(storageSmartTextField);
	}
	
	private void initializeHardwareId() throws ExecutionException, InterruptedException {
		hardwareIdTextField.setText(HWID.getHardwareID());
		hardwareIdTextField.setCaretPosition(0);
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
	
	private void initializeMemory() throws IndexOutOfBoundsException, IOException {
		List<String> memoryList = Win32_PhysicalMemory.getTag();
		Integer slotCount = memoryList.size();
		Long totalSize = 0L;
		memorySlotTextField.setText(Integer.toString(slotCount));
		
		Map<String, String> memoryProperties = Collections.emptyMap();
		for(String memory: memoryList) {
			memoryProperties = Win32_PhysicalMemory.getMemory(memory);
			totalSize+= Long.parseLong(memoryProperties.get("Capacity"));
		}
		
		totalMemoryTextField.setText(String.valueOf(totalSize/(1024*1024))+" MB");
	}
	
	private void initializeVideoController() throws IndexOutOfBoundsException, IOException {
		List<String> gpus = Win32_VideoController.getGPUID();
		for(String gpu:gpus) {
			gpuNumberChoice.addItem(gpu);
		}
		Map<String, String> gpuProperties = Win32_VideoController.getGPU(gpuNumberChoice.getItemAt(gpuNumberChoice.getSelectedIndex()));
		gpuNameTextField.setText(gpuProperties.get("Name"));
		Long adapterRam = Long.parseLong(gpuProperties.get("AdapterRAM"))/(1024*1024);
		gpuVramTextField.setText(String.valueOf(adapterRam)+" MB");
		gpuDriverVersionTextField.setText(gpuProperties.get("DriverVersion"));
		
		gpuNumberChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> gpuProperties;
				try {
					gpuProperties = Win32_VideoController.getGPU(gpuNumberChoice.getItemAt(gpuNumberChoice.getSelectedIndex()));
					gpuNameTextField.setText(gpuProperties.get("Name"));
					Long adapterRam = Long.parseLong(gpuProperties.get("AdapterRAM"))/(1024*1024);
					gpuVramTextField.setText(String.valueOf(adapterRam)+" MB");
					gpuDriverVersionTextField.setText(gpuProperties.get("DriverVersion"));
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO How long before I end up doing something stupid ?
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void initializeMainboard() throws IndexOutOfBoundsException, IOException {
		Map<String, String> mainboard = Win32_Baseboard.getMotherboard();
		Map<String, String> bios = Win32_BIOS.getPrimaryBIOS();
		
		mainboardNameTextField.setText(mainboard.get("Product"));
		mainboardManufacturerTextField.setText(mainboard.get("Manufacturer"));
		biosVersionTextField.setText(bios.get("Caption"));
	}
	
	private void initializeNetwork() throws IndexOutOfBoundsException, IOException {
		List<String> networkAdapters = Win32_NetworkAdapter.getAdapterID();
		for(String networkAdapter: networkAdapters) {
			connectionIdChoice.addItem(networkAdapter);
		}
		Map<String, String> networkProperties = Win32_NetworkAdapter.getNetworkAdapters(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
		networkMacTextField.setText(networkProperties.get("MACAddress"));
		networkDescriptionTextField.setText(networkProperties.get("Description"));
		
		connectionIdChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> networkProperties;
				try {
					networkProperties = Win32_NetworkAdapter.getNetworkAdapters(connectionIdChoice.getItemAt(connectionIdChoice.getSelectedIndex()));
					networkMacTextField.setText(networkProperties.get("MACAddress"));
					networkDescriptionTextField.setText(networkProperties.get("Description"));
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void initializeStorage() throws IndexOutOfBoundsException, IOException {
		List<String> disks = Win32_DiskDrive.getDriveID();
		for(String disk: disks) {
			storageNameChoice.addItem(disk);
		}
		Map<String, String> diskProperties = Win32_DiskDrive.getDrive(storageNameChoice.getItemAt(storageNameChoice.getSelectedIndex()));
		Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
		
		storageSerialTextField.setText(diskProperties.get("SerialNumber"));
		storageSerialTextField.setCaretPosition(0);
		storageSmartTextField.setText(diskProperties.get("Status"));
		storageSizeTextField.setText(String.valueOf(diskSize)+" GB");
		
		storageNameChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> diskProperties;
				try {
					diskProperties = Win32_DiskDrive.getDrive(storageNameChoice.getItemAt(storageNameChoice.getSelectedIndex()));
					Long diskSize = Long.parseLong(diskProperties.get("Size"))/(1024*1024*1024);
					
					storageSerialTextField.setText(diskProperties.get("SerialNumber"));
					storageSmartTextField.setText(diskProperties.get("Status"));
					storageSizeTextField.setText(String.valueOf(diskSize)+" GB");
				} catch (IndexOutOfBoundsException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
