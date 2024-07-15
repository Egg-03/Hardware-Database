package com.hardcache.frontend.databaseui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

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

public class HardCacheDatabaseViewer {

	private JFrame frmHcDatabaseViewer;
	private JTextField cpuNameTf;
	private JTextField cpuCoreTf;
	private JTextField cpuThreadTf;
	private JTextField cpuSocketTf;
	private JComboBox<String> currentGpuChoice;
	private JTextField gpuNameTf;
	private JTextField gpuVramTf;
	private JTextField gpuDriverTf;
	private JTextField totalSlotTf;
	private JTextField totalMemoryTf;
	private JTextField motherboardNameTf;
	private JTextField motherboardManufacturerTf;
	private JTextField textField_2;
	private JTextField netDescriptiontextField;
	private JTextField networkMacTextField;
	private JTextField networkIpTextField;
	private JTextField networkSubnetTextField;
	private JTextField networkDnsTextField;
	private JTextField driveNameTextField;
	private JTextField driveSerialTextField;
	private JTextField driveSizeTextField;
	private JTextField driveSmartTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardCacheDatabaseViewer window = new HardCacheDatabaseViewer();
					window.frmHcDatabaseViewer.pack();
					window.frmHcDatabaseViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HardCacheDatabaseViewer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHcDatabaseViewer = new JFrame();
		frmHcDatabaseViewer.setIconImage(Toolkit.getDefaultToolkit().getImage(HardCacheDatabaseViewer.class.getResource("/res/icon_main.png")));
		frmHcDatabaseViewer.setTitle("HC Database Viewer");
		frmHcDatabaseViewer.setBounds(100, 100, 723, 430);
		frmHcDatabaseViewer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmHcDatabaseViewer.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel hardwarePanel = new JPanel();
		hardwarePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "HardwareID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(hardwarePanel);
		GridBagLayout gbl_hardwarePanel = new GridBagLayout();
		gbl_hardwarePanel.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_hardwarePanel.rowHeights = new int[]{0, 0, 0};
		gbl_hardwarePanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE, 0.0};
		gbl_hardwarePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		hardwarePanel.setLayout(gbl_hardwarePanel);
		
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 0;
		hardwarePanel.add(usernameLabel, gbc_usernameLabel);
		
		JLabel locationLabel = new JLabel("Location");
		GridBagConstraints gbc_locationLabel = new GridBagConstraints();
		gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_locationLabel.gridx = 1;
		gbc_locationLabel.gridy = 0;
		hardwarePanel.add(locationLabel, gbc_locationLabel);
		
		JLabel hwidLabel = new JLabel("HardwareID");
		GridBagConstraints gbc_hwidLabel = new GridBagConstraints();
		gbc_hwidLabel.gridwidth = 2;
		gbc_hwidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_hwidLabel.gridx = 2;
		gbc_hwidLabel.gridy = 0;
		hardwarePanel.add(hwidLabel, gbc_hwidLabel);
		
		JComboBox<String> userNameComboBox = new JComboBox<>();
		GridBagConstraints gbc_userNameComboBox = new GridBagConstraints();
		gbc_userNameComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_userNameComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_userNameComboBox.gridx = 0;
		gbc_userNameComboBox.gridy = 1;
		hardwarePanel.add(userNameComboBox, gbc_userNameComboBox);
		
		JComboBox<String> locationComboBox = new JComboBox<>();
		GridBagConstraints gbc_locationComboBox = new GridBagConstraints();
		gbc_locationComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_locationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationComboBox.gridx = 1;
		gbc_locationComboBox.gridy = 1;
		hardwarePanel.add(locationComboBox, gbc_locationComboBox);
		
		JComboBox<String> hwidChoice = new JComboBox<>();
		GridBagConstraints gbc_hwidChoice = new GridBagConstraints();
		gbc_hwidChoice.insets = new Insets(0, 0, 0, 5);
		gbc_hwidChoice.gridwidth = 2;
		gbc_hwidChoice.weightx = 4.0;
		gbc_hwidChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_hwidChoice.gridx = 2;
		gbc_hwidChoice.gridy = 1;
		hardwarePanel.add(hwidChoice, gbc_hwidChoice);
		
		JButton showButton = new JButton("Show");
		GridBagConstraints gbc_showButton = new GridBagConstraints();
		gbc_showButton.gridx = 4;
		gbc_showButton.gridy = 1;
		hardwarePanel.add(showButton, gbc_showButton);
		
		JPanel cpuPanel = new JPanel();
		cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(cpuPanel);
		GridBagLayout gbl_cpuPanel = new GridBagLayout();
		gbl_cpuPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_cpuPanel.rowHeights = new int[]{0, 0, 0};
		gbl_cpuPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_cpuPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		cpuPanel.setLayout(gbl_cpuPanel);
		
		JLabel cpuChoice = new JLabel("Current CPU");
		GridBagConstraints gbc_cpuChoice = new GridBagConstraints();
		gbc_cpuChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuChoice.insets = new Insets(0, 0, 5, 5);
		gbc_cpuChoice.gridx = 0;
		gbc_cpuChoice.gridy = 0;
		cpuPanel.add(cpuChoice, gbc_cpuChoice);
		
		JComboBox<String> cpuChoiceBox = new JComboBox<>();
		GridBagConstraints gbc_cpuChoiceBox = new GridBagConstraints();
		gbc_cpuChoiceBox.insets = new Insets(0, 0, 5, 5);
		gbc_cpuChoiceBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuChoiceBox.gridx = 1;
		gbc_cpuChoiceBox.gridy = 0;
		cpuPanel.add(cpuChoiceBox, gbc_cpuChoiceBox);
		
		JLabel cpuName = new JLabel("CPU Name");
		GridBagConstraints gbc_cpuName = new GridBagConstraints();
		gbc_cpuName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuName.insets = new Insets(0, 0, 5, 5);
		gbc_cpuName.gridx = 2;
		gbc_cpuName.gridy = 0;
		cpuPanel.add(cpuName, gbc_cpuName);
		
		cpuNameTf = new JTextField();
		cpuNameTf.setEditable(false);
		GridBagConstraints gbc_cpuNameTf = new GridBagConstraints();
		gbc_cpuNameTf.gridwidth = 3;
		gbc_cpuNameTf.insets = new Insets(0, 0, 5, 5);
		gbc_cpuNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuNameTf.gridx = 3;
		gbc_cpuNameTf.gridy = 0;
		cpuPanel.add(cpuNameTf, gbc_cpuNameTf);
		cpuNameTf.setColumns(10);
		
		JLabel cpuCores = new JLabel("CPU Core Count");
		cpuCores.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_cpuCores = new GridBagConstraints();
		gbc_cpuCores.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuCores.anchor = GridBagConstraints.EAST;
		gbc_cpuCores.insets = new Insets(0, 0, 0, 5);
		gbc_cpuCores.gridx = 0;
		gbc_cpuCores.gridy = 1;
		cpuPanel.add(cpuCores, gbc_cpuCores);
		
		cpuCoreTf = new JTextField();
		cpuCoreTf.setEditable(false);
		GridBagConstraints gbc_cpuCoreTf = new GridBagConstraints();
		gbc_cpuCoreTf.anchor = GridBagConstraints.EAST;
		gbc_cpuCoreTf.insets = new Insets(0, 0, 0, 5);
		gbc_cpuCoreTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuCoreTf.gridx = 1;
		gbc_cpuCoreTf.gridy = 1;
		cpuPanel.add(cpuCoreTf, gbc_cpuCoreTf);
		cpuCoreTf.setColumns(10);
		
		JLabel cpuThread = new JLabel("CPU Thread Count");
		GridBagConstraints gbc_cpuThread = new GridBagConstraints();
		gbc_cpuThread.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuThread.insets = new Insets(0, 0, 0, 5);
		gbc_cpuThread.gridx = 2;
		gbc_cpuThread.gridy = 1;
		cpuPanel.add(cpuThread, gbc_cpuThread);
		
		cpuThreadTf = new JTextField();
		cpuThreadTf.setEditable(false);
		GridBagConstraints gbc_cpuThreadTf = new GridBagConstraints();
		gbc_cpuThreadTf.weightx = 10.0;
		gbc_cpuThreadTf.insets = new Insets(0, 0, 0, 5);
		gbc_cpuThreadTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuThreadTf.gridx = 3;
		gbc_cpuThreadTf.gridy = 1;
		cpuPanel.add(cpuThreadTf, gbc_cpuThreadTf);
		cpuThreadTf.setColumns(10);
		
		JLabel cpuSocket = new JLabel("Socket Designation");
		GridBagConstraints gbc_cpuSocket = new GridBagConstraints();
		gbc_cpuSocket.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuSocket.anchor = GridBagConstraints.WEST;
		gbc_cpuSocket.insets = new Insets(0, 0, 0, 5);
		gbc_cpuSocket.gridx = 4;
		gbc_cpuSocket.gridy = 1;
		cpuPanel.add(cpuSocket, gbc_cpuSocket);
		
		cpuSocketTf = new JTextField();
		cpuSocketTf.setEditable(false);
		GridBagConstraints gbc_cpuSocketTf = new GridBagConstraints();
		gbc_cpuSocketTf.weightx = 10.0;
		gbc_cpuSocketTf.anchor = GridBagConstraints.WEST;
		gbc_cpuSocketTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuSocketTf.gridx = 5;
		gbc_cpuSocketTf.gridy = 1;
		cpuPanel.add(cpuSocketTf, gbc_cpuSocketTf);
		cpuSocketTf.setColumns(10);
		
		JPanel gpuPanel = new JPanel();
		gpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "GPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(gpuPanel);
		GridBagLayout gbl_gpuPanel = new GridBagLayout();
		gbl_gpuPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_gpuPanel.rowHeights = new int[]{0, 0, 0};
		gbl_gpuPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_gpuPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gpuPanel.setLayout(gbl_gpuPanel);
		
		JLabel currentGPU = new JLabel("Current GPU");
		GridBagConstraints gbc_currentGPU = new GridBagConstraints();
		gbc_currentGPU.anchor = GridBagConstraints.EAST;
		gbc_currentGPU.insets = new Insets(0, 0, 5, 5);
		gbc_currentGPU.gridx = 0;
		gbc_currentGPU.gridy = 0;
		gpuPanel.add(currentGPU, gbc_currentGPU);
		
		currentGpuChoice = new JComboBox<>();
		GridBagConstraints gbc_currentGpuChoice = new GridBagConstraints();
		gbc_currentGpuChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentGpuChoice.insets = new Insets(0, 0, 5, 5);
		gbc_currentGpuChoice.gridx = 1;
		gbc_currentGpuChoice.gridy = 0;
		gpuPanel.add(currentGpuChoice, gbc_currentGpuChoice);
		
		JLabel currentGpuName = new JLabel("Name");
		GridBagConstraints gbc_currentGpuName = new GridBagConstraints();
		gbc_currentGpuName.insets = new Insets(0, 0, 5, 5);
		gbc_currentGpuName.gridx = 2;
		gbc_currentGpuName.gridy = 0;
		gpuPanel.add(currentGpuName, gbc_currentGpuName);
		
		gpuNameTf = new JTextField();
		gpuNameTf.setEditable(false);
		GridBagConstraints gbc_gpuNameTf = new GridBagConstraints();
		gbc_gpuNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_gpuNameTf.insets = new Insets(0, 0, 5, 0);
		gbc_gpuNameTf.gridx = 3;
		gbc_gpuNameTf.gridy = 0;
		gpuPanel.add(gpuNameTf, gbc_gpuNameTf);
		gpuNameTf.setColumns(10);
		
		JLabel gpuVram = new JLabel("VRAM");
		GridBagConstraints gbc_gpuVram = new GridBagConstraints();
		gbc_gpuVram.insets = new Insets(0, 0, 0, 5);
		gbc_gpuVram.gridx = 0;
		gbc_gpuVram.gridy = 1;
		gpuPanel.add(gpuVram, gbc_gpuVram);
		
		gpuVramTf = new JTextField();
		gpuVramTf.setEditable(false);
		GridBagConstraints gbc_gpuVramTf = new GridBagConstraints();
		gbc_gpuVramTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_gpuVramTf.insets = new Insets(0, 0, 0, 5);
		gbc_gpuVramTf.gridx = 1;
		gbc_gpuVramTf.gridy = 1;
		gpuPanel.add(gpuVramTf, gbc_gpuVramTf);
		gpuVramTf.setColumns(10);
		
		JLabel gpuDriver = new JLabel("Driver Version");
		GridBagConstraints gbc_gpuDriver = new GridBagConstraints();
		gbc_gpuDriver.anchor = GridBagConstraints.EAST;
		gbc_gpuDriver.insets = new Insets(0, 0, 0, 5);
		gbc_gpuDriver.gridx = 2;
		gbc_gpuDriver.gridy = 1;
		gpuPanel.add(gpuDriver, gbc_gpuDriver);
		
		gpuDriverTf = new JTextField();
		gpuDriverTf.setEditable(false);
		GridBagConstraints gbc_gpuDriverTf = new GridBagConstraints();
		gbc_gpuDriverTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_gpuDriverTf.gridx = 3;
		gbc_gpuDriverTf.gridy = 1;
		gpuPanel.add(gpuDriverTf, gbc_gpuDriverTf);
		gpuDriverTf.setColumns(10);
		
		JPanel memoryPanel = new JPanel();
		memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(memoryPanel);
		GridBagLayout gbl_memoryPanel = new GridBagLayout();
		gbl_memoryPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_memoryPanel.rowHeights = new int[]{0, 0};
		gbl_memoryPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_memoryPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		memoryPanel.setLayout(gbl_memoryPanel);
		
		JLabel slotCount = new JLabel("Slots Used");
		GridBagConstraints gbc_slotCount = new GridBagConstraints();
		gbc_slotCount.gridheight = 2;
		gbc_slotCount.insets = new Insets(0, 0, 0, 5);
		gbc_slotCount.anchor = GridBagConstraints.EAST;
		gbc_slotCount.gridx = 0;
		gbc_slotCount.gridy = 0;
		memoryPanel.add(slotCount, gbc_slotCount);
		
		totalSlotTf = new JTextField();
		totalSlotTf.setEditable(false);
		GridBagConstraints gbc_totalSlotTf = new GridBagConstraints();
		gbc_totalSlotTf.gridheight = 2;
		gbc_totalSlotTf.weightx = 1.0;
		gbc_totalSlotTf.insets = new Insets(0, 0, 0, 5);
		gbc_totalSlotTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalSlotTf.gridx = 1;
		gbc_totalSlotTf.gridy = 0;
		memoryPanel.add(totalSlotTf, gbc_totalSlotTf);
		totalSlotTf.setColumns(10);
		
		JLabel totalMemory = new JLabel("Total Memory");
		GridBagConstraints gbc_totalMemory = new GridBagConstraints();
		gbc_totalMemory.gridheight = 2;
		gbc_totalMemory.insets = new Insets(0, 0, 0, 5);
		gbc_totalMemory.anchor = GridBagConstraints.EAST;
		gbc_totalMemory.gridx = 9;
		gbc_totalMemory.gridy = 0;
		memoryPanel.add(totalMemory, gbc_totalMemory);
		
		totalMemoryTf = new JTextField();
		totalMemoryTf.setEditable(false);
		GridBagConstraints gbc_totalMemoryTf = new GridBagConstraints();
		gbc_totalMemoryTf.gridheight = 2;
		gbc_totalMemoryTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalMemoryTf.gridx = 11;
		gbc_totalMemoryTf.gridy = 0;
		memoryPanel.add(totalMemoryTf, gbc_totalMemoryTf);
		totalMemoryTf.setColumns(10);
		
		JPanel mainboardPanel = new JPanel();
		mainboardPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mainboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(mainboardPanel);
		GridBagLayout gbl_mainboardPanel = new GridBagLayout();
		gbl_mainboardPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainboardPanel.rowHeights = new int[]{0, 0, 0};
		gbl_mainboardPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainboardPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainboardPanel.setLayout(gbl_mainboardPanel);
		
		JLabel mainboardName = new JLabel("Name");
		GridBagConstraints gbc_mainboardName = new GridBagConstraints();
		gbc_mainboardName.insets = new Insets(0, 0, 5, 5);
		gbc_mainboardName.gridx = 0;
		gbc_mainboardName.gridy = 0;
		mainboardPanel.add(mainboardName, gbc_mainboardName);
		
		motherboardNameTf = new JTextField();
		motherboardNameTf.setEditable(false);
		GridBagConstraints gbc_motherboardNameTf = new GridBagConstraints();
		gbc_motherboardNameTf.gridwidth = 3;
		gbc_motherboardNameTf.insets = new Insets(0, 0, 5, 5);
		gbc_motherboardNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_motherboardNameTf.gridx = 2;
		gbc_motherboardNameTf.gridy = 0;
		mainboardPanel.add(motherboardNameTf, gbc_motherboardNameTf);
		motherboardNameTf.setColumns(10);
		
		JLabel mainboardManufacturer = new JLabel("Manufacturer");
		GridBagConstraints gbc_mainboardManufacturer = new GridBagConstraints();
		gbc_mainboardManufacturer.insets = new Insets(0, 0, 0, 5);
		gbc_mainboardManufacturer.gridx = 0;
		gbc_mainboardManufacturer.gridy = 1;
		mainboardPanel.add(mainboardManufacturer, gbc_mainboardManufacturer);
		
		motherboardManufacturerTf = new JTextField();
		motherboardManufacturerTf.setEditable(false);
		GridBagConstraints gbc_motherboardManufacturerTf = new GridBagConstraints();
		gbc_motherboardManufacturerTf.insets = new Insets(0, 0, 0, 5);
		gbc_motherboardManufacturerTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_motherboardManufacturerTf.gridx = 2;
		gbc_motherboardManufacturerTf.gridy = 1;
		mainboardPanel.add(motherboardManufacturerTf, gbc_motherboardManufacturerTf);
		motherboardManufacturerTf.setColumns(10);
		
		JLabel mainboardBios = new JLabel("BIOS Version");
		GridBagConstraints gbc_mainboardBios = new GridBagConstraints();
		gbc_mainboardBios.insets = new Insets(0, 0, 0, 5);
		gbc_mainboardBios.anchor = GridBagConstraints.EAST;
		gbc_mainboardBios.gridx = 3;
		gbc_mainboardBios.gridy = 1;
		mainboardPanel.add(mainboardBios, gbc_mainboardBios);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 1;
		mainboardPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JPanel networkPanel = new JPanel();
		networkPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Network", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(networkPanel);
		GridBagLayout gbl_networkPanel = new GridBagLayout();
		gbl_networkPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_networkPanel.rowHeights = new int[]{0, 0, 0};
		gbl_networkPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_networkPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		networkPanel.setLayout(gbl_networkPanel);
		
		JLabel connectId = new JLabel("Connection ID");
		GridBagConstraints gbc_connectId = new GridBagConstraints();
		gbc_connectId.insets = new Insets(0, 0, 5, 5);
		gbc_connectId.anchor = GridBagConstraints.EAST;
		gbc_connectId.gridx = 0;
		gbc_connectId.gridy = 0;
		networkPanel.add(connectId, gbc_connectId);
		
		JComboBox<String> connectIdChoice = new JComboBox<>();
		GridBagConstraints gbc_connectIdChoice = new GridBagConstraints();
		gbc_connectIdChoice.insets = new Insets(0, 0, 5, 5);
		gbc_connectIdChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_connectIdChoice.gridx = 1;
		gbc_connectIdChoice.gridy = 0;
		networkPanel.add(connectIdChoice, gbc_connectIdChoice);
		
		JLabel netDescription = new JLabel("Description");
		GridBagConstraints gbc_netDescription = new GridBagConstraints();
		gbc_netDescription.insets = new Insets(0, 0, 5, 5);
		gbc_netDescription.anchor = GridBagConstraints.EAST;
		gbc_netDescription.gridx = 2;
		gbc_netDescription.gridy = 0;
		networkPanel.add(netDescription, gbc_netDescription);
		
		netDescriptiontextField = new JTextField();
		netDescriptiontextField.setEditable(false);
		GridBagConstraints gbc_netDescriptiontextField = new GridBagConstraints();
		gbc_netDescriptiontextField.gridwidth = 5;
		gbc_netDescriptiontextField.insets = new Insets(0, 0, 5, 5);
		gbc_netDescriptiontextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_netDescriptiontextField.gridx = 3;
		gbc_netDescriptiontextField.gridy = 0;
		networkPanel.add(netDescriptiontextField, gbc_netDescriptiontextField);
		netDescriptiontextField.setColumns(10);
		
		JLabel networkMac = new JLabel("MAC Address");
		GridBagConstraints gbc_networkMac = new GridBagConstraints();
		gbc_networkMac.anchor = GridBagConstraints.EAST;
		gbc_networkMac.insets = new Insets(0, 0, 0, 5);
		gbc_networkMac.gridx = 0;
		gbc_networkMac.gridy = 1;
		networkPanel.add(networkMac, gbc_networkMac);
		
		networkMacTextField = new JTextField();
		networkMacTextField.setEditable(false);
		GridBagConstraints gbc_networkMacTextField = new GridBagConstraints();
		gbc_networkMacTextField.insets = new Insets(0, 0, 0, 5);
		gbc_networkMacTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_networkMacTextField.gridx = 1;
		gbc_networkMacTextField.gridy = 1;
		networkPanel.add(networkMacTextField, gbc_networkMacTextField);
		networkMacTextField.setColumns(10);
		
		JLabel networkIp = new JLabel("IPv4,IPv6");
		GridBagConstraints gbc_networkIp = new GridBagConstraints();
		gbc_networkIp.insets = new Insets(0, 0, 0, 5);
		gbc_networkIp.gridx = 2;
		gbc_networkIp.gridy = 1;
		networkPanel.add(networkIp, gbc_networkIp);
		
		networkIpTextField = new JTextField();
		networkIpTextField.setEditable(false);
		GridBagConstraints gbc_networkIpTextField = new GridBagConstraints();
		gbc_networkIpTextField.insets = new Insets(0, 0, 0, 5);
		gbc_networkIpTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_networkIpTextField.gridx = 3;
		gbc_networkIpTextField.gridy = 1;
		networkPanel.add(networkIpTextField, gbc_networkIpTextField);
		networkIpTextField.setColumns(10);
		
		JLabel networkSubnet = new JLabel("Subnet");
		GridBagConstraints gbc_networkSubnet = new GridBagConstraints();
		gbc_networkSubnet.insets = new Insets(0, 0, 0, 5);
		gbc_networkSubnet.anchor = GridBagConstraints.EAST;
		gbc_networkSubnet.gridx = 4;
		gbc_networkSubnet.gridy = 1;
		networkPanel.add(networkSubnet, gbc_networkSubnet);
		
		networkSubnetTextField = new JTextField();
		networkSubnetTextField.setEditable(false);
		GridBagConstraints gbc_networkSubnetTextField = new GridBagConstraints();
		gbc_networkSubnetTextField.insets = new Insets(0, 0, 0, 5);
		gbc_networkSubnetTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_networkSubnetTextField.gridx = 5;
		gbc_networkSubnetTextField.gridy = 1;
		networkPanel.add(networkSubnetTextField, gbc_networkSubnetTextField);
		networkSubnetTextField.setColumns(10);
		
		JLabel networkDns = new JLabel("DNS Server");
		GridBagConstraints gbc_networkDns = new GridBagConstraints();
		gbc_networkDns.insets = new Insets(0, 0, 0, 5);
		gbc_networkDns.anchor = GridBagConstraints.EAST;
		gbc_networkDns.gridx = 6;
		gbc_networkDns.gridy = 1;
		networkPanel.add(networkDns, gbc_networkDns);
		
		networkDnsTextField = new JTextField();
		networkDnsTextField.setEditable(false);
		GridBagConstraints gbc_networkDnsTextField = new GridBagConstraints();
		gbc_networkDnsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_networkDnsTextField.gridx = 7;
		gbc_networkDnsTextField.gridy = 1;
		networkPanel.add(networkDnsTextField, gbc_networkDnsTextField);
		networkDnsTextField.setColumns(10);
		
		JPanel storagePanel = new JPanel();
		storagePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Storage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHcDatabaseViewer.getContentPane().add(storagePanel);
		GridBagLayout gbl_storagePanel = new GridBagLayout();
		gbl_storagePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_storagePanel.rowHeights = new int[]{0, 0, 0};
		gbl_storagePanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_storagePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		storagePanel.setLayout(gbl_storagePanel);
		
		JLabel driveId = new JLabel("Drive ID");
		GridBagConstraints gbc_driveId = new GridBagConstraints();
		gbc_driveId.insets = new Insets(0, 0, 5, 5);
		gbc_driveId.gridx = 0;
		gbc_driveId.gridy = 0;
		storagePanel.add(driveId, gbc_driveId);
		
		JComboBox<String> driveIdChoice = new JComboBox<>();
		GridBagConstraints gbc_driveIdChoice = new GridBagConstraints();
		gbc_driveIdChoice.insets = new Insets(0, 0, 5, 5);
		gbc_driveIdChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_driveIdChoice.gridx = 1;
		gbc_driveIdChoice.gridy = 0;
		storagePanel.add(driveIdChoice, gbc_driveIdChoice);
		
		JLabel driveName = new JLabel("Name");
		GridBagConstraints gbc_driveName = new GridBagConstraints();
		gbc_driveName.insets = new Insets(0, 0, 5, 5);
		gbc_driveName.gridx = 2;
		gbc_driveName.gridy = 0;
		storagePanel.add(driveName, gbc_driveName);
		
		driveNameTextField = new JTextField();
		driveNameTextField.setEditable(false);
		GridBagConstraints gbc_driveNameTextField = new GridBagConstraints();
		gbc_driveNameTextField.gridwidth = 3;
		gbc_driveNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_driveNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_driveNameTextField.gridx = 3;
		gbc_driveNameTextField.gridy = 0;
		storagePanel.add(driveNameTextField, gbc_driveNameTextField);
		driveNameTextField.setColumns(10);
		
		JLabel driveSerial = new JLabel("Serial");
		GridBagConstraints gbc_driveSerial = new GridBagConstraints();
		gbc_driveSerial.insets = new Insets(0, 0, 0, 5);
		gbc_driveSerial.gridx = 0;
		gbc_driveSerial.gridy = 1;
		storagePanel.add(driveSerial, gbc_driveSerial);
		
		driveSerialTextField = new JTextField();
		driveSerialTextField.setEditable(false);
		GridBagConstraints gbc_driveSerialTextField = new GridBagConstraints();
		gbc_driveSerialTextField.insets = new Insets(0, 0, 0, 5);
		gbc_driveSerialTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_driveSerialTextField.gridx = 1;
		gbc_driveSerialTextField.gridy = 1;
		storagePanel.add(driveSerialTextField, gbc_driveSerialTextField);
		driveSerialTextField.setColumns(10);
		
		JLabel driveSize = new JLabel("Size");
		GridBagConstraints gbc_driveSize = new GridBagConstraints();
		gbc_driveSize.insets = new Insets(0, 0, 0, 5);
		gbc_driveSize.gridx = 2;
		gbc_driveSize.gridy = 1;
		storagePanel.add(driveSize, gbc_driveSize);
		
		driveSizeTextField = new JTextField();
		driveSizeTextField.setEditable(false);
		GridBagConstraints gbc_driveSizeTextField = new GridBagConstraints();
		gbc_driveSizeTextField.insets = new Insets(0, 0, 0, 5);
		gbc_driveSizeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_driveSizeTextField.gridx = 3;
		gbc_driveSizeTextField.gridy = 1;
		storagePanel.add(driveSizeTextField, gbc_driveSizeTextField);
		driveSizeTextField.setColumns(10);
		
		JLabel driveSmart = new JLabel("S.M.A.R.T");
		GridBagConstraints gbc_driveSmart = new GridBagConstraints();
		gbc_driveSmart.insets = new Insets(0, 0, 0, 5);
		gbc_driveSmart.gridx = 4;
		gbc_driveSmart.gridy = 1;
		storagePanel.add(driveSmart, gbc_driveSmart);
		
		driveSmartTextField = new JTextField();
		driveSmartTextField.setEditable(false);
		GridBagConstraints gbc_driveSmartTextField = new GridBagConstraints();
		gbc_driveSmartTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_driveSmartTextField.gridx = 5;
		gbc_driveSmartTextField.gridy = 1;
		storagePanel.add(driveSmartTextField, gbc_driveSmartTextField);
		driveSmartTextField.setColumns(10);
	}
}
