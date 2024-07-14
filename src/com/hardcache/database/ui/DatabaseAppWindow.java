package com.hardcache.database.ui;

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

public class DatabaseAppWindow {

	private JFrame frmHcDatabaseViewer;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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
					DatabaseAppWindow window = new DatabaseAppWindow();
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
	public DatabaseAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHcDatabaseViewer = new JFrame();
		frmHcDatabaseViewer.setIconImage(Toolkit.getDefaultToolkit().getImage(DatabaseAppWindow.class.getResource("/res/icon_main.png")));
		frmHcDatabaseViewer.setTitle("HC Database Viewer");
		frmHcDatabaseViewer.setBounds(100, 100, 560, 490);
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
		
		JLabel lblNewLabel = new JLabel("Current CPU");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		cpuPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox<String> cpuChoice = new JComboBox<>();
		GridBagConstraints gbc_cpuChoice = new GridBagConstraints();
		gbc_cpuChoice.insets = new Insets(0, 0, 5, 5);
		gbc_cpuChoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuChoice.gridx = 1;
		gbc_cpuChoice.gridy = 0;
		cpuPanel.add(cpuChoice, gbc_cpuChoice);
		
		JLabel cpuName = new JLabel("CPU Name");
		GridBagConstraints gbc_cpuName = new GridBagConstraints();
		gbc_cpuName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuName.insets = new Insets(0, 0, 5, 5);
		gbc_cpuName.gridx = 2;
		gbc_cpuName.gridy = 0;
		cpuPanel.add(cpuName, gbc_cpuName);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		cpuPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		cpuPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.EAST;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		cpuPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 1;
		cpuPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 1;
		cpuPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 1;
		cpuPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.weightx = 3.0;
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 5;
		gbc_textField_3.gridy = 1;
		cpuPanel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
	}
}
