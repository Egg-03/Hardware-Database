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
import com.ferruml.system.operatingsystem.Win32_OperatingSystem;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppWindow {

	private JFrame frame;
	private JTextArea hardwareIdTextField;
	private JComboBox<String> osNameChoice;
	private JTextField osArchTextField;
	private JTextField deviceNameTextField;
	private JTextField currentUserTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 490);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel hardwareIdPanel = new JPanel();
		hardwareIdPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Unique ID", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(192, 192, 192)));
		hardwareIdPanel.setBounds(10, 11, 414, 80);
		frame.getContentPane().add(hardwareIdPanel);
		hardwareIdPanel.setLayout(null);
		
		JLabel hardwareLabel = new JLabel("Hardware ID");
		hardwareLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		hardwareLabel.setBounds(21, 29, 73, 27);
		hardwareIdPanel.add(hardwareLabel);
		
		hardwareIdTextField = new JTextArea();
		hardwareIdTextField.setBackground(new Color(240, 240, 240));
		hardwareIdTextField.setLineWrap(true);
		hardwareIdTextField.setForeground(Color.MAGENTA);
		hardwareIdTextField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 11));
		hardwareIdTextField.setEditable(false);
		hardwareIdTextField.setBounds(104, 19, 300, 50);
		hardwareIdPanel.add(hardwareIdTextField);
		hardwareIdTextField.setColumns(10);
		
		JPanel osPanel = new JPanel();
		osPanel.setBorder(new TitledBorder(null, "Operating System", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		osPanel.setBounds(12, 93, 410, 80);
		frame.getContentPane().add(osPanel);
		osPanel.setLayout(null);
		
		JLabel osName = new JLabel("Name");
		osName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osName.setBounds(12, 22, 30, 20);
		osPanel.add(osName);
		
		osNameChoice = new JComboBox<>();
		osNameChoice.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		osNameChoice.setEditable(false);
		osNameChoice.setBounds(48, 22, 204, 20);
		osPanel.add(osNameChoice);
		
		JLabel osArch = new JLabel("Architecture");
		osArch.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osArch.setBounds(270, 21, 64, 20);
		osPanel.add(osArch);
		
		osArchTextField = new JTextField();
		osArchTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		osArchTextField.setEditable(false);
		osArchTextField.setColumns(10);
		osArchTextField.setBounds(344, 21, 54, 20);
		osPanel.add(osArchTextField);
		
		JLabel deviceName = new JLabel("Device Name");
		deviceName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		deviceName.setBounds(12, 49, 70, 20);
		osPanel.add(deviceName);
		
		deviceNameTextField = new JTextField();
		deviceNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		deviceNameTextField.setEditable(false);
		deviceNameTextField.setColumns(10);
		deviceNameTextField.setBounds(85, 49, 167, 20);
		osPanel.add(deviceNameTextField);
		
		JLabel currentUser = new JLabel("Current User");
		currentUser.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		currentUser.setBounds(270, 48, 67, 20);
		osPanel.add(currentUser);
		
		currentUserTextField = new JTextField();
		currentUserTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		currentUserTextField.setEditable(false);
		currentUserTextField.setColumns(10);
		currentUserTextField.setBounds(344, 48, 54, 20);
		osPanel.add(currentUserTextField);
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
}
