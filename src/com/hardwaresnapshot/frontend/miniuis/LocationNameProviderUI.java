package com.hardwaresnapshot.frontend.miniuis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.ferrumx.system.logger.ErrorLog;
import com.hardwaresnapshot.backend.database.datainsertanddelete.DataInsertion;

//Location and Name Selector UI
//This should appear when you're dumping new data or updating it
public class LocationNameProviderUI extends JFrame {
	private static final long serialVersionUID = 8198578061033325272L;
	
	public LocationNameProviderUI() {
		super("LN Selector Window");
		setTheme();
		initialize();
	}
	
	private void setTheme() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorLog().log(" LN Selector Window Theme Load Error: "+e.getMessage());
		}
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LocationNameProviderUI.class.getResource("/res/icon_main.png")));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(490,190);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Provide a name and a location", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(10, 11, 454, 129);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel compUsername = new JLabel("Computer Username");
		compUsername.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		compUsername.setBounds(10, 22, 114, 25);
		panel.add(compUsername);
		
		JLabel compLocation = new JLabel("Computer Location");
		compLocation.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		compLocation.setBounds(10, 64, 114, 25);
		panel.add(compLocation);
		
		JTextField compUserTextField = new JTextField();
		compUserTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		compUserTextField.setBounds(134, 22, 310, 25);
		panel.add(compUserTextField);
		compUserTextField.setColumns(10);
		
		JTextField compLocationTextField = new JTextField();
		compLocationTextField.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		compLocationTextField.setColumns(10);
		compLocationTextField.setBounds(134, 64, 310, 25);
		panel.add(compLocationTextField);
		
		JLabel statusDisplay = new JLabel("");
		statusDisplay.setBounds(109, 95, 335, 23);
		panel.add(statusDisplay);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(e-> {
			if(compUserTextField.getText().isBlank() || compLocationTextField.getText().isBlank())
				statusDisplay.setText("❌ User and Location fields cannot be empty");
			else {
				this.dispose();
				DataInsertion.insert(compUserTextField.getText(), compLocationTextField.getText());
			}
		});
		btnConfirm.setBounds(10, 95, 89, 23);
		panel.add(btnConfirm);
	}
}
