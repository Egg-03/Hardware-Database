package com.egg.errorui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ExceptionUI extends JFrame{
	private static final long serialVersionUID = 5951705399700376822L;
	private JTextArea exceptionArea = new JTextArea();

	public ExceptionUI(String msg) {
		super("Crash Report Engine");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExceptionUI.class.getResource("/res/ferrum_legacy.png")));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(490,190);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "FerrumL v1.2.5 Report Engine", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(10, 11, 454, 130);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		exceptionArea.setLineWrap(true);
		exceptionArea.setEditable(false);
		exceptionArea.setBackground(new Color(32,32,32));
		
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
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(()->new ExceptionUI("Default Message").setVisible(true));
	}
}

