package com.egg.miniuis;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ferrumx.system.logger.ErrorLog;

public class ExceptionUI extends JFrame{
	private static final long serialVersionUID = 5951705399700376822L;
	private JTextArea exceptionArea = new JTextArea();

	public ExceptionUI(String errorName, String errorMessage) {
		super("Crash Report Engine");
		setTheme();
		initialize(errorName, errorMessage);
	}
	
	private void initialize(String errorName, String errorMessage) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExceptionUI.class.getResource("/res/icon_main.png")));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(490,190);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), errorName, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(10, 11, 454, 130);
		getContentPane().add(panel);
		panel.setLayout(null);
		exceptionArea.setForeground(new Color(0, 128, 128));
		
		exceptionArea.setLineWrap(true);
		exceptionArea.setEditable(false);
		exceptionArea.setBackground(new Color(32,32,32));
		exceptionArea.setText(errorMessage);
		
		JScrollPane scrollPane = new JScrollPane(exceptionArea);
		scrollPane.setBounds(10, 22, 434, 70);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
		
		JLabel copySuccessLabel = new JLabel();
		copySuccessLabel.setBounds(115, 98, 225, 22);
		panel.add(copySuccessLabel);
		
		JButton copyButton = new JButton("Copy Log");
		copyButton.addActionListener(copyButtonAction-> {
				exceptionArea.selectAll();
				exceptionArea.copy();
				copySuccessLabel.setText("✓ Successfully copied to clipboard");
		});
		copyButton.setBounds(10, 98, 97, 22);
		panel.add(copyButton);
	}
	
	private void setTheme() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorLog().log(" ExceptionUI Theme Load Error: "+e.getMessage());
		}
	}
}

