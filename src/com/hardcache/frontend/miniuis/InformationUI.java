package com.hardcache.frontend.miniuis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.ferrumx.system.logger.ErrorLog;

public class InformationUI extends JFrame {
	private static final long serialVersionUID = -4503451098970252115L;
	
	public InformationUI(String infoLabel) {
		super("Information Dialog Box");
		setTheme();
		initialize(infoLabel);
	}
	
	private void setTheme() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacDarkLaf");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorLog().log(" InformationUI Theme Load Error: "+e.getMessage());
		}
	}
	
	private void initialize(String infoLabel) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformationUI.class.getResource("/res/icon_main.png")));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(300,140);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(10, 11, 264, 79);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		btnOk.setBounds(100, 51, 54, 17);
		btnOk.addActionListener(e-> this.dispose());
		panel.add(btnOk);
		
		JLabel informationLabel = new JLabel(infoLabel);
		informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informationLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		informationLabel.setBounds(10, 18, 244, 24);
		panel.add(informationLabel);
	}
}
