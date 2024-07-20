package com.hardwaresnapshot.frontend.databaseui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;

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

import com.ferrumx.system.logger.ErrorLog;
import com.hardwaresnapshot.backend.database.dataretrieve.CpuDatabase;
import com.hardwaresnapshot.backend.database.dataretrieve.GpuDatabase;
import com.hardwaresnapshot.backend.database.dataretrieve.HardwareIdDatabase;
import com.hardwaresnapshot.backend.database.dataretrieve.MainboardDatabase;
import com.hardwaresnapshot.backend.database.dataretrieve.MemoryDatabase;
import com.hardwaresnapshot.backend.database.dataretrieve.NetworkDatabase;
import com.hardwaresnapshot.backend.database.dataretrieve.StorageDatabase;

public class HardwareSnapshotDatabaseViewer {

	private JFrame frmHsnapDatabaseViewer;
	
	private JComboBox<String> locationComboBox;
	private JComboBox<String> hwidComboBox;
	
	private JComboBox<String> cpuChoiceBox;
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
	private JTextField biosVersionTextField;
	
	private JComboBox<String> connectIdChoice;
	private JTextField netDescriptiontextField;
	private JTextField networkMacTextField;
	private JTextField networkIpTextField;
	private JTextField networkSubnetTextField;
	private JTextField networkDnsTextField;
	private JTextField netDefaultGatewayTextField;
	
	private JComboBox<String> driveIdChoice;
	private JTextField driveNameTextField;
	private JTextField driveSerialTextField;
	private JTextField driveSizeTextField;
	private JTextField driveSmartTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			HardwareSnapshotDatabaseViewer window = new HardwareSnapshotDatabaseViewer();
			window.frmHsnapDatabaseViewer.pack();
			window.frmHsnapDatabaseViewer.setVisible(true);
		});
	}

	/**
	 * Create the application.
	 */
	public HardwareSnapshotDatabaseViewer() {
		initializeUI();
		initializeLocation();
	}
	
	private void setTheme() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
			SwingUtilities.updateComponentTreeUI(frmHsnapDatabaseViewer);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorLog().log(" ExceptionUI Theme Load Error: "+e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUI() {
		frmHsnapDatabaseViewer = new JFrame();
		setTheme();
		frmHsnapDatabaseViewer.setIconImage(Toolkit.getDefaultToolkit().getImage(HardwareSnapshotDatabaseViewer.class.getResource("/res/icon_main.png")));
		frmHsnapDatabaseViewer.setTitle("Hardware Snapshot Database Viewer Beta v19072024");
		frmHsnapDatabaseViewer.setBounds(100, 100, 723, 430);
		frmHsnapDatabaseViewer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmHsnapDatabaseViewer.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		setHardwarePanel();
		setCpuPanel();
		setGpuPanel();
		setMemoryPanel();
		setMainboardPanel();
		setNetworkPanel();
		setStoragePanel();
	}
	
	private void setHardwarePanel() {
		
		JPanel hardwarePanel = new JPanel();
		hardwarePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "HardwareID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(hardwarePanel);
		GridBagLayout gblhardwarePanel = new GridBagLayout();
		gblhardwarePanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
		gblhardwarePanel.rowHeights = new int[]{0, 0, 0};
		gblhardwarePanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE, 0.0, 0.0};
		gblhardwarePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		hardwarePanel.setLayout(gblhardwarePanel);
		
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbcusernameLabel = new GridBagConstraints();
		gbcusernameLabel.insets = new Insets(0, 0, 5, 5);
		gbcusernameLabel.gridx = 0;
		gbcusernameLabel.gridy = 0;
		hardwarePanel.add(usernameLabel, gbcusernameLabel);
		
		JLabel locationLabel = new JLabel("Location");
		GridBagConstraints gbclocationLabel = new GridBagConstraints();
		gbclocationLabel.insets = new Insets(0, 0, 5, 5);
		gbclocationLabel.gridx = 1;
		gbclocationLabel.gridy = 0;
		hardwarePanel.add(locationLabel, gbclocationLabel);
		
		JLabel hwidLabel = new JLabel("HardwareID");
		GridBagConstraints gbchwidLabel = new GridBagConstraints();
		gbchwidLabel.gridwidth = 2;
		gbchwidLabel.insets = new Insets(0, 0, 5, 5);
		gbchwidLabel.gridx = 2;
		gbchwidLabel.gridy = 0;
		hardwarePanel.add(hwidLabel, gbchwidLabel);
		
		JComboBox<String> userNameComboBox = new JComboBox<>();
		userNameComboBox.addActionListener(e->{
			String uname = userNameComboBox.getItemAt(userNameComboBox.getSelectedIndex());
			String location = locationComboBox.getItemAt(locationComboBox.getSelectedIndex());
			List<String> hwid = HardwareIdDatabase.getAllHardwareIdWhere(location, uname);
			
			hwidComboBox.removeAllItems();
			for(String id:hwid) {
				hwidComboBox.addItem(id);
			}
			hwidComboBox.setEnabled(true);
		});
		userNameComboBox.setEnabled(false);
		GridBagConstraints gbcuserNameComboBox = new GridBagConstraints();
		gbcuserNameComboBox.insets = new Insets(0, 0, 0, 5);
		gbcuserNameComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbcuserNameComboBox.gridx = 0;
		gbcuserNameComboBox.gridy = 1;
		hardwarePanel.add(userNameComboBox, gbcuserNameComboBox);
		
		locationComboBox = new JComboBox<>();
		locationComboBox.addActionListener(e-> {
			String location = locationComboBox.getItemAt(locationComboBox.getSelectedIndex());
			userNameComboBox.removeAllItems();
			List<String> newUserList = HardwareIdDatabase.getAllUsernameWhereLocation(location);
			for(String newUser:newUserList) {
				userNameComboBox.addItem(newUser);
			}
			userNameComboBox.setEnabled(true);	
		});
		GridBagConstraints gbclocationComboBox = new GridBagConstraints();
		gbclocationComboBox.insets = new Insets(0, 0, 0, 5);
		gbclocationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbclocationComboBox.gridx = 1;
		gbclocationComboBox.gridy = 1;
		hardwarePanel.add(locationComboBox, gbclocationComboBox);
		
		hwidComboBox = new JComboBox<>();
		hwidComboBox.setEnabled(false);
		hwidComboBox.setEditable(false);
		GridBagConstraints gbchwidTextField = new GridBagConstraints();
		gbchwidTextField.insets = new Insets(0, 0, 0, 5);
		gbchwidTextField.gridwidth = 2;
		gbchwidTextField.weightx = 4.0;
		gbchwidTextField.fill = GridBagConstraints.HORIZONTAL;
		gbchwidTextField.gridx = 2;
		gbchwidTextField.gridy = 1;
		hardwarePanel.add(hwidComboBox, gbchwidTextField);
		
		JButton showButton = new JButton("Show");
		showButton.addActionListener(e-> {
			clearChoices(); //refresh all the choice boxes to avoid storing duplicates
			String hwid = hwidComboBox.getItemAt(hwidComboBox.getSelectedIndex());
			initializeCpu(hwid);
			initializeGpu(hwid);
			initializeMemory(hwid);
			initializeMainboard(hwid);
			initializeNetwork(hwid);
			initializeStorage(hwid);
		});
		GridBagConstraints gbcshowButton = new GridBagConstraints();
		gbcshowButton.gridwidth = 2;
		gbcshowButton.insets = new Insets(0, 0, 0, 5);
		gbcshowButton.gridx = 4;
		gbcshowButton.gridy = 1;
		hardwarePanel.add(showButton, gbcshowButton);
	}
	
	private void setCpuPanel() {
		JPanel cpuPanel = new JPanel();
		cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(cpuPanel);
		GridBagLayout gblcpuPanel = new GridBagLayout();
		gblcpuPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gblcpuPanel.rowHeights = new int[]{0, 0, 0};
		gblcpuPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gblcpuPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		cpuPanel.setLayout(gblcpuPanel);
		
		JLabel cpuChoice = new JLabel("Current CPU");
		GridBagConstraints gbccpuChoice = new GridBagConstraints();
		gbccpuChoice.fill = GridBagConstraints.HORIZONTAL;
		gbccpuChoice.insets = new Insets(0, 0, 5, 5);
		gbccpuChoice.gridx = 0;
		gbccpuChoice.gridy = 0;
		cpuPanel.add(cpuChoice, gbccpuChoice);
		
		cpuChoiceBox = new JComboBox<>();
		cpuChoiceBox.addActionListener(e->{
			String deviceId = cpuChoiceBox.getItemAt(cpuChoiceBox.getSelectedIndex());
			String hardwareId = hwidComboBox.getItemAt(hwidComboBox.getSelectedIndex());
			Map<String, String> cpuProperties = CpuDatabase.getCpuProperties(deviceId, hardwareId);
			
			cpuNameTf.setText(cpuProperties.get("CpuName"));
			cpuCoreTf.setText(cpuProperties.get("CpuCores"));
			cpuThreadTf.setText(cpuProperties.get("CpuThreads"));
			cpuSocketTf.setText(cpuProperties.get("CpuSocket"));
		});
		GridBagConstraints gbccpuChoiceBox = new GridBagConstraints();
		gbccpuChoiceBox.insets = new Insets(0, 0, 5, 5);
		gbccpuChoiceBox.fill = GridBagConstraints.HORIZONTAL;
		gbccpuChoiceBox.gridx = 1;
		gbccpuChoiceBox.gridy = 0;
		cpuPanel.add(cpuChoiceBox, gbccpuChoiceBox);
		
		JLabel cpuName = new JLabel("CPU Name");
		GridBagConstraints gbccpuName = new GridBagConstraints();
		gbccpuName.fill = GridBagConstraints.HORIZONTAL;
		gbccpuName.insets = new Insets(0, 0, 5, 5);
		gbccpuName.gridx = 2;
		gbccpuName.gridy = 0;
		cpuPanel.add(cpuName, gbccpuName);
		
		cpuNameTf = new JTextField();
		cpuNameTf.setEditable(false);
		GridBagConstraints gbccpuNameTf = new GridBagConstraints();
		gbccpuNameTf.gridwidth = 3;
		gbccpuNameTf.insets = new Insets(0, 0, 5, 5);
		gbccpuNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbccpuNameTf.gridx = 3;
		gbccpuNameTf.gridy = 0;
		cpuPanel.add(cpuNameTf, gbccpuNameTf);
		cpuNameTf.setColumns(10);
		
		JLabel cpuCores = new JLabel("CPU Core Count");
		cpuCores.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbccpuCores = new GridBagConstraints();
		gbccpuCores.fill = GridBagConstraints.HORIZONTAL;
		gbccpuCores.anchor = GridBagConstraints.EAST;
		gbccpuCores.insets = new Insets(0, 0, 0, 5);
		gbccpuCores.gridx = 0;
		gbccpuCores.gridy = 1;
		cpuPanel.add(cpuCores, gbccpuCores);
		
		cpuCoreTf = new JTextField();
		cpuCoreTf.setEditable(false);
		GridBagConstraints gbccpuCoreTf = new GridBagConstraints();
		gbccpuCoreTf.anchor = GridBagConstraints.EAST;
		gbccpuCoreTf.insets = new Insets(0, 0, 0, 5);
		gbccpuCoreTf.fill = GridBagConstraints.HORIZONTAL;
		gbccpuCoreTf.gridx = 1;
		gbccpuCoreTf.gridy = 1;
		cpuPanel.add(cpuCoreTf, gbccpuCoreTf);
		cpuCoreTf.setColumns(10);
		
		JLabel cpuThread = new JLabel("CPU Thread Count");
		GridBagConstraints gbccpuThread = new GridBagConstraints();
		gbccpuThread.fill = GridBagConstraints.HORIZONTAL;
		gbccpuThread.insets = new Insets(0, 0, 0, 5);
		gbccpuThread.gridx = 2;
		gbccpuThread.gridy = 1;
		cpuPanel.add(cpuThread, gbccpuThread);
		
		cpuThreadTf = new JTextField();
		cpuThreadTf.setEditable(false);
		GridBagConstraints gbccpuThreadTf = new GridBagConstraints();
		gbccpuThreadTf.weightx = 10.0;
		gbccpuThreadTf.insets = new Insets(0, 0, 0, 5);
		gbccpuThreadTf.fill = GridBagConstraints.HORIZONTAL;
		gbccpuThreadTf.gridx = 3;
		gbccpuThreadTf.gridy = 1;
		cpuPanel.add(cpuThreadTf, gbccpuThreadTf);
		cpuThreadTf.setColumns(10);
		
		JLabel cpuSocket = new JLabel("Socket Designation");
		GridBagConstraints gbccpuSocket = new GridBagConstraints();
		gbccpuSocket.fill = GridBagConstraints.HORIZONTAL;
		gbccpuSocket.anchor = GridBagConstraints.WEST;
		gbccpuSocket.insets = new Insets(0, 0, 0, 5);
		gbccpuSocket.gridx = 4;
		gbccpuSocket.gridy = 1;
		cpuPanel.add(cpuSocket, gbccpuSocket);
		
		cpuSocketTf = new JTextField();
		cpuSocketTf.setEditable(false);
		GridBagConstraints gbccpuSocketTf = new GridBagConstraints();
		gbccpuSocketTf.weightx = 10.0;
		gbccpuSocketTf.anchor = GridBagConstraints.WEST;
		gbccpuSocketTf.fill = GridBagConstraints.HORIZONTAL;
		gbccpuSocketTf.gridx = 5;
		gbccpuSocketTf.gridy = 1;
		cpuPanel.add(cpuSocketTf, gbccpuSocketTf);
		cpuSocketTf.setColumns(10);
	}
	
	private void setGpuPanel() {
		JPanel gpuPanel = new JPanel();
		gpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "GPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(gpuPanel);
		GridBagLayout gblgpuPanel = new GridBagLayout();
		gblgpuPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gblgpuPanel.rowHeights = new int[]{0, 0, 0};
		gblgpuPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gblgpuPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gpuPanel.setLayout(gblgpuPanel);
		
		JLabel currentGPU = new JLabel("Current GPU");
		GridBagConstraints gbccurrentGPU = new GridBagConstraints();
		gbccurrentGPU.anchor = GridBagConstraints.EAST;
		gbccurrentGPU.insets = new Insets(0, 0, 5, 5);
		gbccurrentGPU.gridx = 0;
		gbccurrentGPU.gridy = 0;
		gpuPanel.add(currentGPU, gbccurrentGPU);
		
		currentGpuChoice = new JComboBox<>();
		currentGpuChoice.addActionListener(e->{
			String deviceId = currentGpuChoice.getItemAt(currentGpuChoice.getSelectedIndex());
			String hardwareId = hwidComboBox.getItemAt(hwidComboBox.getSelectedIndex());
			Map<String, String> gpuProperties = GpuDatabase.getGpuProperties(deviceId, hardwareId);
			
			gpuNameTf.setText(gpuProperties.get("GpuName"));
			gpuVramTf.setText(gpuProperties.get("VRAM"));
			gpuDriverTf.setText(gpuProperties.get("DriverVersion"));
		});
		GridBagConstraints gbccurrentGpuChoice = new GridBagConstraints();
		gbccurrentGpuChoice.fill = GridBagConstraints.HORIZONTAL;
		gbccurrentGpuChoice.insets = new Insets(0, 0, 5, 5);
		gbccurrentGpuChoice.gridx = 1;
		gbccurrentGpuChoice.gridy = 0;
		gpuPanel.add(currentGpuChoice, gbccurrentGpuChoice);
		
		JLabel currentGpuName = new JLabel("Name");
		GridBagConstraints gbccurrentGpuName = new GridBagConstraints();
		gbccurrentGpuName.insets = new Insets(0, 0, 5, 5);
		gbccurrentGpuName.gridx = 2;
		gbccurrentGpuName.gridy = 0;
		gpuPanel.add(currentGpuName, gbccurrentGpuName);
		
		gpuNameTf = new JTextField();
		gpuNameTf.setEditable(false);
		GridBagConstraints gbcgpuNameTf = new GridBagConstraints();
		gbcgpuNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbcgpuNameTf.insets = new Insets(0, 0, 5, 0);
		gbcgpuNameTf.gridx = 3;
		gbcgpuNameTf.gridy = 0;
		gpuPanel.add(gpuNameTf, gbcgpuNameTf);
		gpuNameTf.setColumns(10);
		
		JLabel gpuVram = new JLabel("VRAM");
		GridBagConstraints gbcgpuVram = new GridBagConstraints();
		gbcgpuVram.insets = new Insets(0, 0, 0, 5);
		gbcgpuVram.gridx = 0;
		gbcgpuVram.gridy = 1;
		gpuPanel.add(gpuVram, gbcgpuVram);
		
		gpuVramTf = new JTextField();
		gpuVramTf.setEditable(false);
		GridBagConstraints gbcgpuVramTf = new GridBagConstraints();
		gbcgpuVramTf.fill = GridBagConstraints.HORIZONTAL;
		gbcgpuVramTf.insets = new Insets(0, 0, 0, 5);
		gbcgpuVramTf.gridx = 1;
		gbcgpuVramTf.gridy = 1;
		gpuPanel.add(gpuVramTf, gbcgpuVramTf);
		gpuVramTf.setColumns(10);
		
		JLabel gpuDriver = new JLabel("Driver Version");
		GridBagConstraints gbcgpuDriver = new GridBagConstraints();
		gbcgpuDriver.anchor = GridBagConstraints.EAST;
		gbcgpuDriver.insets = new Insets(0, 0, 0, 5);
		gbcgpuDriver.gridx = 2;
		gbcgpuDriver.gridy = 1;
		gpuPanel.add(gpuDriver, gbcgpuDriver);
		
		gpuDriverTf = new JTextField();
		gpuDriverTf.setEditable(false);
		GridBagConstraints gbcgpuDriverTf = new GridBagConstraints();
		gbcgpuDriverTf.fill = GridBagConstraints.HORIZONTAL;
		gbcgpuDriverTf.gridx = 3;
		gbcgpuDriverTf.gridy = 1;
		gpuPanel.add(gpuDriverTf, gbcgpuDriverTf);
		gpuDriverTf.setColumns(10);
	}
	
	private void setMemoryPanel() {
		JPanel memoryPanel = new JPanel();
		memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(memoryPanel);
		GridBagLayout gblmemoryPanel = new GridBagLayout();
		gblmemoryPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblmemoryPanel.rowHeights = new int[]{0, 0};
		gblmemoryPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gblmemoryPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		memoryPanel.setLayout(gblmemoryPanel);
		
		JLabel slotCount = new JLabel("Slots Used");
		GridBagConstraints gbcslotCount = new GridBagConstraints();
		gbcslotCount.gridheight = 2;
		gbcslotCount.insets = new Insets(0, 0, 0, 5);
		gbcslotCount.anchor = GridBagConstraints.EAST;
		gbcslotCount.gridx = 0;
		gbcslotCount.gridy = 0;
		memoryPanel.add(slotCount, gbcslotCount);
		
		totalSlotTf = new JTextField();
		totalSlotTf.setEditable(false);
		GridBagConstraints gbctotalSlotTf = new GridBagConstraints();
		gbctotalSlotTf.gridheight = 2;
		gbctotalSlotTf.weightx = 1.0;
		gbctotalSlotTf.insets = new Insets(0, 0, 0, 5);
		gbctotalSlotTf.fill = GridBagConstraints.HORIZONTAL;
		gbctotalSlotTf.gridx = 1;
		gbctotalSlotTf.gridy = 0;
		memoryPanel.add(totalSlotTf, gbctotalSlotTf);
		totalSlotTf.setColumns(10);
		
		JLabel totalMemory = new JLabel("Total Memory");
		GridBagConstraints gbctotalMemory = new GridBagConstraints();
		gbctotalMemory.gridheight = 2;
		gbctotalMemory.insets = new Insets(0, 0, 0, 5);
		gbctotalMemory.anchor = GridBagConstraints.EAST;
		gbctotalMemory.gridx = 9;
		gbctotalMemory.gridy = 0;
		memoryPanel.add(totalMemory, gbctotalMemory);
		
		totalMemoryTf = new JTextField();
		totalMemoryTf.setEditable(false);
		GridBagConstraints gbctotalMemoryTf = new GridBagConstraints();
		gbctotalMemoryTf.gridheight = 2;
		gbctotalMemoryTf.fill = GridBagConstraints.HORIZONTAL;
		gbctotalMemoryTf.gridx = 11;
		gbctotalMemoryTf.gridy = 0;
		memoryPanel.add(totalMemoryTf, gbctotalMemoryTf);
		totalMemoryTf.setColumns(10);
	}
	
	private void setMainboardPanel() {
		JPanel mainboardPanel = new JPanel();
		mainboardPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mainboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(mainboardPanel);
		GridBagLayout gblmainboardPanel = new GridBagLayout();
		gblmainboardPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gblmainboardPanel.rowHeights = new int[]{0, 0, 0};
		gblmainboardPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gblmainboardPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainboardPanel.setLayout(gblmainboardPanel);
		
		JLabel mainboardName = new JLabel("Name");
		GridBagConstraints gbcmainboardName = new GridBagConstraints();
		gbcmainboardName.insets = new Insets(0, 0, 5, 5);
		gbcmainboardName.gridx = 0;
		gbcmainboardName.gridy = 0;
		mainboardPanel.add(mainboardName, gbcmainboardName);
		
		motherboardNameTf = new JTextField();
		motherboardNameTf.setEditable(false);
		GridBagConstraints gbcmotherboardNameTf = new GridBagConstraints();
		gbcmotherboardNameTf.gridwidth = 3;
		gbcmotherboardNameTf.insets = new Insets(0, 0, 5, 5);
		gbcmotherboardNameTf.fill = GridBagConstraints.HORIZONTAL;
		gbcmotherboardNameTf.gridx = 2;
		gbcmotherboardNameTf.gridy = 0;
		mainboardPanel.add(motherboardNameTf, gbcmotherboardNameTf);
		motherboardNameTf.setColumns(10);
		
		JLabel mainboardManufacturer = new JLabel("Manufacturer");
		GridBagConstraints gbcmainboardManufacturer = new GridBagConstraints();
		gbcmainboardManufacturer.insets = new Insets(0, 0, 0, 5);
		gbcmainboardManufacturer.gridx = 0;
		gbcmainboardManufacturer.gridy = 1;
		mainboardPanel.add(mainboardManufacturer, gbcmainboardManufacturer);
		
		motherboardManufacturerTf = new JTextField();
		motherboardManufacturerTf.setEditable(false);
		GridBagConstraints gbcmotherboardManufacturerTf = new GridBagConstraints();
		gbcmotherboardManufacturerTf.insets = new Insets(0, 0, 0, 5);
		gbcmotherboardManufacturerTf.fill = GridBagConstraints.HORIZONTAL;
		gbcmotherboardManufacturerTf.gridx = 2;
		gbcmotherboardManufacturerTf.gridy = 1;
		mainboardPanel.add(motherboardManufacturerTf, gbcmotherboardManufacturerTf);
		motherboardManufacturerTf.setColumns(10);
		
		JLabel mainboardBios = new JLabel("BIOS Version");
		GridBagConstraints gbcmainboardBios = new GridBagConstraints();
		gbcmainboardBios.insets = new Insets(0, 0, 0, 5);
		gbcmainboardBios.anchor = GridBagConstraints.EAST;
		gbcmainboardBios.gridx = 3;
		gbcmainboardBios.gridy = 1;
		mainboardPanel.add(mainboardBios, gbcmainboardBios);
		
		biosVersionTextField = new JTextField();
		biosVersionTextField.setEditable(false);
		GridBagConstraints gbcbiosVersionTextField = new GridBagConstraints();
		gbcbiosVersionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcbiosVersionTextField.gridx = 4;
		gbcbiosVersionTextField.gridy = 1;
		mainboardPanel.add(biosVersionTextField, gbcbiosVersionTextField);
		biosVersionTextField.setColumns(10);
	}
	
	private void setNetworkPanel() {
		JPanel networkPanel = new JPanel();
		networkPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Network", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(networkPanel);
		GridBagLayout gblnetworkPanel = new GridBagLayout();
		gblnetworkPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblnetworkPanel.rowHeights = new int[]{0, 0, 0};
		gblnetworkPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gblnetworkPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		networkPanel.setLayout(gblnetworkPanel);
		
		JLabel connectId = new JLabel("Connection ID");
		GridBagConstraints gbcconnectId = new GridBagConstraints();
		gbcconnectId.insets = new Insets(0, 0, 5, 5);
		gbcconnectId.anchor = GridBagConstraints.EAST;
		gbcconnectId.gridx = 0;
		gbcconnectId.gridy = 0;
		networkPanel.add(connectId, gbcconnectId);
		
		connectIdChoice = new JComboBox<>();
		connectIdChoice.addActionListener(e-> {
			String deviceId = connectIdChoice.getItemAt(connectIdChoice.getSelectedIndex());
			String hardwareId = hwidComboBox.getItemAt(hwidComboBox.getSelectedIndex());
			
			Map<String, String> networkAdapterProperties = NetworkDatabase.getNetworkProperties(deviceId, hardwareId);
			netDescriptiontextField.setText(networkAdapterProperties.get("Description"));
			netDefaultGatewayTextField.setText(networkAdapterProperties.get("DefaultIPGateway"));
			networkMacTextField.setText(networkAdapterProperties.get("MACAddress"));
			networkIpTextField.setText(networkAdapterProperties.get("IPAddress"));
			networkSubnetTextField.setText(networkAdapterProperties.get("IPSubnet"));
			networkDnsTextField.setText(networkAdapterProperties.get("DNSServerSearchOrder"));
		});
		GridBagConstraints gbcconnectIdChoice = new GridBagConstraints();
		gbcconnectIdChoice.insets = new Insets(0, 0, 5, 5);
		gbcconnectIdChoice.fill = GridBagConstraints.HORIZONTAL;
		gbcconnectIdChoice.gridx = 1;
		gbcconnectIdChoice.gridy = 0;
		networkPanel.add(connectIdChoice, gbcconnectIdChoice);
		
		JLabel netDescription = new JLabel("Description");
		GridBagConstraints gbcnetDescription = new GridBagConstraints();
		gbcnetDescription.insets = new Insets(0, 0, 5, 5);
		gbcnetDescription.anchor = GridBagConstraints.EAST;
		gbcnetDescription.gridx = 2;
		gbcnetDescription.gridy = 0;
		networkPanel.add(netDescription, gbcnetDescription);
		
		netDescriptiontextField = new JTextField();
		netDescriptiontextField.setEditable(false);
		GridBagConstraints gbcnetDescriptiontextField = new GridBagConstraints();
		gbcnetDescriptiontextField.gridwidth = 3;
		gbcnetDescriptiontextField.insets = new Insets(0, 0, 5, 5);
		gbcnetDescriptiontextField.fill = GridBagConstraints.HORIZONTAL;
		gbcnetDescriptiontextField.gridx = 3;
		gbcnetDescriptiontextField.gridy = 0;
		networkPanel.add(netDescriptiontextField, gbcnetDescriptiontextField);
		netDescriptiontextField.setColumns(10);
		
		JLabel netDefaultGateway = new JLabel("Default Gateway");
		GridBagConstraints gbcnetDefaultGateway = new GridBagConstraints();
		gbcnetDefaultGateway.anchor = GridBagConstraints.EAST;
		gbcnetDefaultGateway.insets = new Insets(0, 0, 5, 5);
		gbcnetDefaultGateway.gridx = 6;
		gbcnetDefaultGateway.gridy = 0;
		networkPanel.add(netDefaultGateway, gbcnetDefaultGateway);
		
		netDefaultGatewayTextField = new JTextField();
		netDefaultGatewayTextField.setEditable(false);
		GridBagConstraints gbcnetDefaultGatewayTextField = new GridBagConstraints();
		gbcnetDefaultGatewayTextField.insets = new Insets(0, 0, 5, 0);
		gbcnetDefaultGatewayTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcnetDefaultGatewayTextField.gridx = 7;
		gbcnetDefaultGatewayTextField.gridy = 0;
		networkPanel.add(netDefaultGatewayTextField, gbcnetDefaultGatewayTextField);
		netDefaultGatewayTextField.setColumns(10);
		
		JLabel networkMac = new JLabel("MAC Address");
		GridBagConstraints gbcnetworkMac = new GridBagConstraints();
		gbcnetworkMac.anchor = GridBagConstraints.EAST;
		gbcnetworkMac.insets = new Insets(0, 0, 0, 5);
		gbcnetworkMac.gridx = 0;
		gbcnetworkMac.gridy = 1;
		networkPanel.add(networkMac, gbcnetworkMac);
		
		networkMacTextField = new JTextField();
		networkMacTextField.setEditable(false);
		GridBagConstraints gbcnetworkMacTextField = new GridBagConstraints();
		gbcnetworkMacTextField.insets = new Insets(0, 0, 0, 5);
		gbcnetworkMacTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcnetworkMacTextField.gridx = 1;
		gbcnetworkMacTextField.gridy = 1;
		networkPanel.add(networkMacTextField, gbcnetworkMacTextField);
		networkMacTextField.setColumns(10);
		
		JLabel networkIp = new JLabel("IPv4,IPv6");
		GridBagConstraints gbcnetworkIp = new GridBagConstraints();
		gbcnetworkIp.insets = new Insets(0, 0, 0, 5);
		gbcnetworkIp.gridx = 2;
		gbcnetworkIp.gridy = 1;
		networkPanel.add(networkIp, gbcnetworkIp);
		
		networkIpTextField = new JTextField();
		networkIpTextField.setEditable(false);
		GridBagConstraints gbcnetworkIpTextField = new GridBagConstraints();
		gbcnetworkIpTextField.insets = new Insets(0, 0, 0, 5);
		gbcnetworkIpTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcnetworkIpTextField.gridx = 3;
		gbcnetworkIpTextField.gridy = 1;
		networkPanel.add(networkIpTextField, gbcnetworkIpTextField);
		networkIpTextField.setColumns(10);
		
		JLabel networkSubnet = new JLabel("Subnet");
		GridBagConstraints gbcnetworkSubnet = new GridBagConstraints();
		gbcnetworkSubnet.insets = new Insets(0, 0, 0, 5);
		gbcnetworkSubnet.anchor = GridBagConstraints.EAST;
		gbcnetworkSubnet.gridx = 4;
		gbcnetworkSubnet.gridy = 1;
		networkPanel.add(networkSubnet, gbcnetworkSubnet);
		
		networkSubnetTextField = new JTextField();
		networkSubnetTextField.setEditable(false);
		GridBagConstraints gbcnetworkSubnetTextField = new GridBagConstraints();
		gbcnetworkSubnetTextField.insets = new Insets(0, 0, 0, 5);
		gbcnetworkSubnetTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcnetworkSubnetTextField.gridx = 5;
		gbcnetworkSubnetTextField.gridy = 1;
		networkPanel.add(networkSubnetTextField, gbcnetworkSubnetTextField);
		networkSubnetTextField.setColumns(10);
		
		JLabel networkDns = new JLabel("DNS Server");
		GridBagConstraints gbcnetworkDns = new GridBagConstraints();
		gbcnetworkDns.insets = new Insets(0, 0, 0, 5);
		gbcnetworkDns.anchor = GridBagConstraints.EAST;
		gbcnetworkDns.gridx = 6;
		gbcnetworkDns.gridy = 1;
		networkPanel.add(networkDns, gbcnetworkDns);
		
		networkDnsTextField = new JTextField();
		networkDnsTextField.setEditable(false);
		GridBagConstraints gbcnetworkDnsTextField = new GridBagConstraints();
		gbcnetworkDnsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcnetworkDnsTextField.gridx = 7;
		gbcnetworkDnsTextField.gridy = 1;
		networkPanel.add(networkDnsTextField, gbcnetworkDnsTextField);
		networkDnsTextField.setColumns(10);
	}
	
	private void setStoragePanel() {
		JPanel storagePanel = new JPanel();
		storagePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Storage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmHsnapDatabaseViewer.getContentPane().add(storagePanel);
		GridBagLayout gblstoragePanel = new GridBagLayout();
		gblstoragePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gblstoragePanel.rowHeights = new int[]{0, 0, 0};
		gblstoragePanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gblstoragePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		storagePanel.setLayout(gblstoragePanel);
		
		JLabel driveId = new JLabel("Drive ID");
		GridBagConstraints gbcdriveId = new GridBagConstraints();
		gbcdriveId.insets = new Insets(0, 0, 5, 5);
		gbcdriveId.gridx = 0;
		gbcdriveId.gridy = 0;
		storagePanel.add(driveId, gbcdriveId);
		
		driveIdChoice = new JComboBox<>();
		driveIdChoice.addActionListener(e-> {
			String deviceId = driveIdChoice.getItemAt(driveIdChoice.getSelectedIndex());
			String hardwareId = hwidComboBox.getItemAt(hwidComboBox.getSelectedIndex());
			
			Map<String, String> diskProperties = StorageDatabase.getDiskProperties(deviceId, hardwareId);
			driveNameTextField.setText(diskProperties.get("Name"));
			driveSerialTextField.setText(diskProperties.get("Serial"));
			driveSizeTextField.setText(diskProperties.get("Size"));
			driveSmartTextField.setText(diskProperties.get("SMART"));
			
		});
		GridBagConstraints gbcdriveIdChoice = new GridBagConstraints();
		gbcdriveIdChoice.insets = new Insets(0, 0, 5, 5);
		gbcdriveIdChoice.fill = GridBagConstraints.HORIZONTAL;
		gbcdriveIdChoice.gridx = 1;
		gbcdriveIdChoice.gridy = 0;
		storagePanel.add(driveIdChoice, gbcdriveIdChoice);
		
		JLabel driveName = new JLabel("Name");
		GridBagConstraints gbcdriveName = new GridBagConstraints();
		gbcdriveName.insets = new Insets(0, 0, 5, 5);
		gbcdriveName.gridx = 2;
		gbcdriveName.gridy = 0;
		storagePanel.add(driveName, gbcdriveName);
		
		driveNameTextField = new JTextField();
		driveNameTextField.setEditable(false);
		GridBagConstraints gbcdriveNameTextField = new GridBagConstraints();
		gbcdriveNameTextField.gridwidth = 3;
		gbcdriveNameTextField.insets = new Insets(0, 0, 5, 5);
		gbcdriveNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcdriveNameTextField.gridx = 3;
		gbcdriveNameTextField.gridy = 0;
		storagePanel.add(driveNameTextField, gbcdriveNameTextField);
		driveNameTextField.setColumns(10);
		
		JLabel driveSerial = new JLabel("Serial");
		GridBagConstraints gbcdriveSerial = new GridBagConstraints();
		gbcdriveSerial.insets = new Insets(0, 0, 0, 5);
		gbcdriveSerial.gridx = 0;
		gbcdriveSerial.gridy = 1;
		storagePanel.add(driveSerial, gbcdriveSerial);
		
		driveSerialTextField = new JTextField();
		driveSerialTextField.setEditable(false);
		GridBagConstraints gbcdriveSerialTextField = new GridBagConstraints();
		gbcdriveSerialTextField.insets = new Insets(0, 0, 0, 5);
		gbcdriveSerialTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcdriveSerialTextField.gridx = 1;
		gbcdriveSerialTextField.gridy = 1;
		storagePanel.add(driveSerialTextField, gbcdriveSerialTextField);
		driveSerialTextField.setColumns(10);
		
		JLabel driveSize = new JLabel("Size");
		GridBagConstraints gbcdriveSize = new GridBagConstraints();
		gbcdriveSize.insets = new Insets(0, 0, 0, 5);
		gbcdriveSize.gridx = 2;
		gbcdriveSize.gridy = 1;
		storagePanel.add(driveSize, gbcdriveSize);
		
		driveSizeTextField = new JTextField();
		driveSizeTextField.setEditable(false);
		GridBagConstraints gbcdriveSizeTextField = new GridBagConstraints();
		gbcdriveSizeTextField.insets = new Insets(0, 0, 0, 5);
		gbcdriveSizeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcdriveSizeTextField.gridx = 3;
		gbcdriveSizeTextField.gridy = 1;
		storagePanel.add(driveSizeTextField, gbcdriveSizeTextField);
		driveSizeTextField.setColumns(10);
		
		JLabel driveSmart = new JLabel("S.M.A.R.T");
		GridBagConstraints gbcdriveSmart = new GridBagConstraints();
		gbcdriveSmart.insets = new Insets(0, 0, 0, 5);
		gbcdriveSmart.gridx = 4;
		gbcdriveSmart.gridy = 1;
		storagePanel.add(driveSmart, gbcdriveSmart);
		
		driveSmartTextField = new JTextField();
		driveSmartTextField.setEditable(false);
		GridBagConstraints gbcdriveSmartTextField = new GridBagConstraints();
		gbcdriveSmartTextField.fill = GridBagConstraints.HORIZONTAL;
		gbcdriveSmartTextField.gridx = 5;
		gbcdriveSmartTextField.gridy = 1;
		storagePanel.add(driveSmartTextField, gbcdriveSmartTextField);
		driveSmartTextField.setColumns(10);
	}
	
	private void initializeLocation() {		
		List<String> locations = HardwareIdDatabase.getAllLocations();
		for(String loc: locations)
			locationComboBox.addItem(loc);
	}
	
	private void initializeCpu(String hwid) {
		List<String> cpus = CpuDatabase.getAllCpuIds(hwid);
		for(String cpu: cpus)
			cpuChoiceBox.addItem(cpu);
	}
	
	private void initializeGpu(String hwid) {
		List<String> gpus = GpuDatabase.getAllGpuIds(hwid);
		for(String gpu: gpus)
			currentGpuChoice.addItem(gpu);
	}
	
	private void initializeMemory(String hwid) {
		Map<String, String> memoryProperties = MemoryDatabase.getMemoryProperties(hwid);
		totalSlotTf.setText(memoryProperties.get("SlotsUsed"));
		totalMemoryTf.setText(memoryProperties.get("TotalMemory"));
	}
	
	private void initializeMainboard(String hwid) {
		Map<String, String> mainboardProperties = MainboardDatabase.getMainboardProperties(hwid);
		motherboardNameTf.setText(mainboardProperties.get("Name"));
		motherboardManufacturerTf.setText(mainboardProperties.get("Manufacturer"));
		biosVersionTextField.setText(mainboardProperties.get("BIOSVersion"));
	}
	
	private void initializeNetwork(String hwid) {
		List<String> networkAdapterIds = NetworkDatabase.getAllNetworkAdapterIds(hwid);
		for(String id:networkAdapterIds)
			connectIdChoice.addItem(id);
	}
	
	private void initializeStorage(String hwid) {
		List<String> diskIds = StorageDatabase.getDiskIds(hwid);
		for(String id: diskIds)
			driveIdChoice.addItem(id);	
	}
	
	private void clearChoices() {
		cpuChoiceBox.removeAllItems();
		currentGpuChoice.removeAllItems();
		connectIdChoice.removeAllItems();
		driveIdChoice.removeAllItems();
	}
}
