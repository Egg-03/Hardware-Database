package com.egg.ferrumldb.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.egg.database.DataDeletion;
import com.egg.miniuis.ConfirmationUI;
import com.egg.miniuis.ExceptionUI;
import com.egg.miniuis.LocationNameProvider;
import com.ferruml.error.ErrorLog;

public class AppWindow {

	private JFrame feldbdmp;

	private JComboBox<String> osNameChoice;
	private JComboBox<String> cpuNumberChoice;
	private JComboBox<String> gpuNumberChoice;
	private JComboBox<String> connectionIdChoice;
	private JComboBox<String> storageIndexChoice;
	
	private JTextField hardwareIdTextField;
	
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
	private JTextField ipAddressTextField;
	
	private JTextField storageSerialTextField;
	private JTextField storageSizeTextField;
	private JTextField storageSmartTextField;
	private JTextField storageNameTextField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AppWindow window = new AppWindow();
		window.feldbdmp.setLocationRelativeTo(null);
		window.feldbdmp.setVisible(true);			
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 * @throws IOException 
	 * @throws IndexOutOfBoundsException 
	 */
	public AppWindow() {
		initializeComponents();
		setTheme();
		initializeSystemInfo();
	}
	
	private void setTheme() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
			SwingUtilities.updateComponentTreeUI(feldbdmp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorLog().log(" ExceptionUI Theme Load Error: "+e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponents() {
		feldbdmp = new JFrame();
		feldbdmp.setResizable(false);
		feldbdmp.setIconImage(Toolkit.getDefaultToolkit().getImage(AppWindow.class.getResource("/res/ferrum_legacy.png")));
		feldbdmp.setTitle("FeL Dump Tool DevBuild-InternalRelease v07072024");
		feldbdmp.setBounds(100, 100, 450, 721);
		feldbdmp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		feldbdmp.getContentPane().setLayout(null);
		
		hardwareIdPanel();
		osPanel();
		cpuPanel();
		memoryPanel();
		gpuPanel();
		mainboardPanel();
		networkPanel();
		storagePanel();
	}
	
	private void hardwareIdPanel() {
		JPanel hardwareIdPanel = new JPanel();
		hardwareIdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Unique ID", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(192, 192, 192)));
		hardwareIdPanel.setBounds(10, 11, 414, 80);
		feldbdmp.getContentPane().add(hardwareIdPanel);
		hardwareIdPanel.setLayout(null);
		
		JLabel hardwareLabel = new JLabel("Hardware ID");
		hardwareLabel.setToolTipText("Hardware ID");
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
		
		JButton restartButton = new JButton("Restart");
		restartButton.setToolTipText("Restarts the application. \r\nThis should be used in case you have hot swapped a new detectable hardware. \r\nAfter a restart, the application should be able to detect the new hardware.");
		restartButton.addActionListener(e-> {
			//close app
			feldbdmp.dispose();
			//restart
			AppWindow window = new AppWindow();
			window.feldbdmp.setLocationRelativeTo(null);
			window.feldbdmp.setVisible(true);
		});
		restartButton.setBounds(17, 48, 83, 24);
		hardwareIdPanel.add(restartButton);
		
		JTextField ferrumEngineVersion = new JTextField();
		ferrumEngineVersion.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		ferrumEngineVersion.setHorizontalAlignment(SwingConstants.CENTER);
		ferrumEngineVersion.setEditable(false);
		ferrumEngineVersion.setText("FeL Core v1.2.5");
		ferrumEngineVersion.setBounds(294, 48, 108, 24);
		hardwareIdPanel.add(ferrumEngineVersion);
		ferrumEngineVersion.setColumns(10);
		
		JButton dataDumpButton = new JButton("Dump");
		dataDumpButton.setToolTipText("Dumps the visible data into a local database");
		dataDumpButton.setBounds(107, 48, 83, 24);
		dataDumpButton.addActionListener(e-> new LocationNameProvider().setVisible(true));
		hardwareIdPanel.add(dataDumpButton);
		
		JButton dataDeleteButton = new JButton("Delete");
		dataDeleteButton.setToolTipText("Deletes existing information");
		dataDeleteButton.setBounds(197, 48, 83, 24);
		dataDeleteButton.addActionListener(e->{
			ConfirmationUI warning = new ConfirmationUI();
			warning.getQuestionLabel().setText("Destructive operation ahead. Continue ?");
			warning.getBtnYes().addActionListener(e1->{
				warning.dispose();
				DataDeletion.delete();
			});
			warning.getBtnNo().addActionListener(e1->warning.dispose());
			warning.setVisible(true);
		});
		hardwareIdPanel.add(dataDeleteButton);
	}
	
	private void osPanel() {
		JPanel osPanel = new JPanel();
		osPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Operating System", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		osPanel.setBounds(10, 93, 412, 90);
		feldbdmp.getContentPane().add(osPanel);
		osPanel.setLayout(null);
		
		JLabel osName = new JLabel("Name");
		osName.setToolTipText("OS Name");
		osName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osName.setBounds(12, 22, 30, 24);
		osPanel.add(osName);
		
		osNameChoice = new JComboBox<>();
		osNameChoice.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		osNameChoice.setEditable(false);
		osNameChoice.setBounds(48, 22, 204, 24);
		osPanel.add(osNameChoice);
		
		JLabel osArch = new JLabel("Architecture");
		osArch.setToolTipText("OS Architecture");
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
		deviceName.setToolTipText("Device Name: This is usually your PC name that you provide \r\nupon first time installation.");
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
		currentUser.setToolTipText("Current User: Username of the \r\ncurrently logged in user");
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
	}
	
	private void cpuPanel() {
		JPanel cpuPanel = new JPanel();
		cpuPanel.setLayout(null);
		cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		cpuPanel.setBounds(10, 185, 414, 80);
		feldbdmp.getContentPane().add(cpuPanel);
		
		JLabel cpuName = new JLabel("Name");
		cpuName.setToolTipText("CPU Name: As provided by the manufacturer");
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
		cpuSocket.setToolTipText("CPU Socket");
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
		cpuCoreCount.setToolTipText("CPU Cores");
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
		cpuThreadCount.setToolTipText("CPU Threads");
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
		cpuNumber.setToolTipText("CPU Number: Some motherboards do support multiple CPUs");
		cpuNumber.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		cpuNumber.setBounds(12, 20, 30, 22);
		cpuPanel.add(cpuNumber);
		
		cpuNumberChoice = new JComboBox<>();
		cpuNumberChoice.setMaximumRowCount(4);
		cpuNumberChoice.setFont(new Font("Segoe UI", Font.ITALIC, 9));
		cpuNumberChoice.setEditable(false);
		cpuNumberChoice.setBounds(48, 20, 57, 22);
		cpuPanel.add(cpuNumberChoice);
	}
	
	private void memoryPanel() {
		JPanel memoryPanel = new JPanel();
		memoryPanel.setLayout(null);
		memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		memoryPanel.setBounds(10, 265, 414, 44);
		feldbdmp.getContentPane().add(memoryPanel);
		
		JLabel memorySlots = new JLabel("Slots Used");
		memorySlots.setToolTipText("Memory Slots Used");
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
		totalMemory.setToolTipText("Total Memory: Combined total allocated memory \r\nfrom all the slots");
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
	}
	
	private void gpuPanel() {
		JPanel gpuPanel = new JPanel();
		gpuPanel.setLayout(null);
		gpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Video Controller", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		gpuPanel.setBounds(10, 310, 412, 90);
		feldbdmp.getContentPane().add(gpuPanel);
		
		JLabel gpuNumber = new JLabel("GPU#");
		gpuNumber.setToolTipText("GPU Number: Shows the number of enabled GPUs.\r\nNote: If a GPU is disabled, it won't appear here.\r\nOne such case is that if your CPU has an iGPU \r\nbut you're using a dGPU, only your dGPU will show up\r\n");
		gpuNumber.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		gpuNumber.setBounds(12, 22, 30, 24);
		gpuPanel.add(gpuNumber);
		
		gpuNumberChoice = new JComboBox<>();
		gpuNumberChoice.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		gpuNumberChoice.setEditable(false);
		gpuNumberChoice.setBounds(48, 22, 70, 24);
		gpuPanel.add(gpuNumberChoice);
		
		JLabel gpuName = new JLabel("Name");
		gpuName.setToolTipText("GPU Name");
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
		gpuVram.setToolTipText("VRAM: The amount of VRAM physically available to the GPU.\r\nDoes not show the memory shared by your system RAM.\r\nIn case of iGPUs, it will only show the amount of RAM dedicated to it.");
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
		gpuDriverVersion.setToolTipText("Driver Version");
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
	}
	
	private void mainboardPanel() {
		JPanel mainboardPanel = new JPanel();
		mainboardPanel.setLayout(null);
		mainboardPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mainboard", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		mainboardPanel.setBounds(10, 402, 412, 90);
		feldbdmp.getContentPane().add(mainboardPanel);
		
		JLabel mainboardName = new JLabel("Mainboard Name");
		mainboardName.setToolTipText("Mainboard Name: The name of your system's motherboard");
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
		mainboardManufacturer.setToolTipText("Manufacturer");
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
		biosVersion.setToolTipText("BIOS Version");
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
	}
	
	private void networkPanel() {
		JPanel network = new JPanel();
		network.setLayout(null);
		network.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Network", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		network.setBounds(10, 492, 412, 90);
		feldbdmp.getContentPane().add(network);
		
		JLabel netConnectionId = new JLabel("Connection ID");
		netConnectionId.setToolTipText("Connection ID: Only Links the connections which are active.\r\nRestart the application if u have installed a new active connection to see the changes.");
		netConnectionId.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		netConnectionId.setBounds(12, 21, 75, 24);
		network.add(netConnectionId);
		
		connectionIdChoice = new JComboBox<>();
		connectionIdChoice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		connectionIdChoice.setEditable(false);
		connectionIdChoice.setBounds(90, 22, 60, 24);
		network.add(connectionIdChoice);
		
		JLabel networkDescription = new JLabel("Description");
		networkDescription.setToolTipText("Description: The name of your Network Adapter");
		networkDescription.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		networkDescription.setBounds(12, 54, 60, 24);
		network.add(networkDescription);
		
		networkDescriptionTextField = new JTextField();
		networkDescriptionTextField.setText((String) null);
		networkDescriptionTextField.setHorizontalAlignment(SwingConstants.CENTER);
		networkDescriptionTextField.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		networkDescriptionTextField.setEditable(false);
		networkDescriptionTextField.setColumns(10);
		networkDescriptionTextField.setBounds(78, 55, 153, 24);
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
		
		ipAddressTextField = new JTextField();
		ipAddressTextField.setText((String) null);
		ipAddressTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ipAddressTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		ipAddressTextField.setEditable(false);
		ipAddressTextField.setColumns(10);
		ipAddressTextField.setBounds(280, 56, 120, 24);
		network.add(ipAddressTextField);
		
		JLabel networkIp = new JLabel("IPv4");
		networkIp.setToolTipText("The Link Local IPv4 address assigned automatically/manually");
		networkIp.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		networkIp.setBounds(243, 56, 29, 20);
		network.add(networkIp);
	}
	
	private void storagePanel() {
		JPanel storage = new JPanel();
		storage.setLayout(null);
		storage.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Storage", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		storage.setBounds(10, 583, 412, 90);
		feldbdmp.getContentPane().add(storage);
		
		JLabel storageIndex = new JLabel("Index");
		storageIndex.setToolTipText("Caption: Shows the available drive counts based on their unsorted indexes\r\n");
		storageIndex.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageIndex.setBounds(12, 21, 37, 24);
		storage.add(storageIndex);
		
		storageIndexChoice = new JComboBox<>();
		storageIndexChoice.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageIndexChoice.setEditable(false);
		storageIndexChoice.setBounds(52, 21, 48, 24);
		storage.add(storageIndexChoice);
		
		JLabel storageSerial = new JLabel("Serial");
		storageSerial.setToolTipText("Serial Number");
		storageSerial.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageSerial.setBounds(12, 54, 37, 24);
		storage.add(storageSerial);
		
		storageSerialTextField = new JTextField();
		storageSerialTextField.setText((String) null);
		storageSerialTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageSerialTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageSerialTextField.setEditable(false);
		storageSerialTextField.setColumns(10);
		storageSerialTextField.setBounds(52, 54, 100, 24);
		storage.add(storageSerialTextField);
		
		JLabel storageSize = new JLabel("Size");
		storageSize.setToolTipText("Size");
		storageSize.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageSize.setBounds(159, 54, 26, 24);
		storage.add(storageSize);
		
		storageSizeTextField = new JTextField();
		storageSizeTextField.setText((String) null);
		storageSizeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageSizeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageSizeTextField.setEditable(false);
		storageSizeTextField.setColumns(10);
		storageSizeTextField.setBounds(189, 54, 59, 24);
		storage.add(storageSizeTextField);
		
		JLabel storageSmartStatus = new JLabel("S.M.A.R.T");
		storageSmartStatus.setToolTipText("S.M.A.R.T Info\r\nOperational Statuses: OK, Degraded, Pred Fail\r\nNon Operational Statuses: Error, Starting, Stopping, Service");
		storageSmartStatus.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageSmartStatus.setBounds(256, 54, 48, 24);
		storage.add(storageSmartStatus);
		
		storageSmartTextField = new JTextField();
		storageSmartTextField.setText((String) null);
		storageSmartTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageSmartTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageSmartTextField.setEditable(false);
		storageSmartTextField.setColumns(10);
		storageSmartTextField.setBounds(311, 54, 89, 24);
		storage.add(storageSmartTextField);
		
		storageNameTextField = new JTextField();
		storageNameTextField.setText((String) null);
		storageNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		storageNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		storageNameTextField.setEditable(false);
		storageNameTextField.setColumns(10);
		storageNameTextField.setBounds(159, 21, 241, 24);
		storage.add(storageNameTextField);
		
		JLabel storageName = new JLabel("Name");
		storageName.setToolTipText("Name");
		storageName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		storageName.setBounds(118, 21, 37, 24);
		storage.add(storageName);
	}
	
	private void initializeSystemInfo() {
		try(ExecutorService infoFetch = Executors.newFixedThreadPool(8)){
			// Define tasks for each function call
	        Runnable initializeHardwareId = () -> HardwareId.initializeHardwareId(hardwareIdTextField);
	        Runnable initializeOs = () -> OperatingSystem.initializeOs(osNameChoice, deviceNameTextField, osArchTextField, currentUserTextField);
	        Runnable initializeCpu = () -> Cpu.initializeCpu(cpuNumberChoice, cpuNameTextField, cpuCoreTextField, cpuThreadTextField, cpuSocketTextField);
	        Runnable initializeMemory = () -> Memory.initializeMemory(memorySlotTextField, totalMemoryTextField);
	        Runnable initializeVideoController = () -> VideoController.initializeVideoController(gpuNumberChoice, gpuNameTextField, gpuVramTextField, gpuDriverVersionTextField);
	        Runnable initializeMainboard = () -> Mainboard.initializeMainboard(mainboardNameTextField, mainboardManufacturerTextField, biosVersionTextField);
	        Runnable initializeNetwork = () -> Network.initializeNetwork(connectionIdChoice, networkMacTextField, networkDescriptionTextField, ipAddressTextField);
	        Runnable initializeStorage = () -> Storage.initializeStorage(storageIndexChoice, storageNameTextField, storageSerialTextField, storageSmartTextField, storageSizeTextField);

	        // Submit all tasks to the executor service
	        infoFetch.submit(initializeHardwareId);
	        infoFetch.submit(initializeOs);
	        infoFetch.submit(initializeCpu);
	        infoFetch.submit(initializeMemory);
	        infoFetch.submit(initializeVideoController);
	        infoFetch.submit(initializeMainboard);
	        infoFetch.submit(initializeNetwork);
	        infoFetch.submit(initializeStorage);
		} catch (RejectedExecutionException | NullPointerException e) {
			new ExceptionUI("Host Gather System Info Error", e.getMessage()).setVisible(true);
		}
	}
}
