package com.egg.ferrumldb.ui;

import java.awt.EventQueue;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.ferruml.system.hardware.HWID;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.WindowConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AppWindow {

	private JFrame frame;
	private JTextArea hardwareIdTextField;
	private JTextField osNameTextField;
	private JTextField textField;
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
	 */
	public AppWindow() throws ExecutionException, InterruptedException {
		initialize();
		initializeHardwareId();
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
		osPanel.setBounds(12, 93, 410, 72);
		frame.getContentPane().add(osPanel);
		osPanel.setLayout(null);
		
		JLabel osName = new JLabel("Name");
		osName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osName.setBounds(12, 22, 30, 15);
		osPanel.add(osName);
		
		osNameTextField = new JTextField();
		osNameTextField.setEditable(false);
		osNameTextField.setBounds(48, 22, 204, 15);
		osPanel.add(osNameTextField);
		osNameTextField.setColumns(10);
		
		JLabel osArch = new JLabel("Architecture");
		osArch.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		osArch.setBounds(270, 21, 64, 15);
		osPanel.add(osArch);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(344, 21, 54, 15);
		osPanel.add(textField);
		
		JLabel deviceName = new JLabel("Device Name");
		deviceName.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		deviceName.setBounds(12, 49, 70, 15);
		osPanel.add(deviceName);
		
		deviceNameTextField = new JTextField();
		deviceNameTextField.setEditable(false);
		deviceNameTextField.setColumns(10);
		deviceNameTextField.setBounds(85, 49, 167, 15);
		osPanel.add(deviceNameTextField);
		
		JLabel currentUser = new JLabel("Current User");
		currentUser.setFont(new Font("Segoe UI Variable", Font.BOLD, 11));
		currentUser.setBounds(270, 48, 67, 15);
		osPanel.add(currentUser);
		
		currentUserTextField = new JTextField();
		currentUserTextField.setEditable(false);
		currentUserTextField.setColumns(10);
		currentUserTextField.setBounds(344, 48, 54, 15);
		osPanel.add(currentUserTextField);
	}
	
	private void initializeHardwareId() throws ExecutionException, InterruptedException {
		hardwareIdTextField.setText(HWID.getHardwareID());
	}
}
