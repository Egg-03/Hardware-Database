package com.hardwaresnapshot.frontend.databaseui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class Insights extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cpuBrandsTextField;
	private JTextField cpuCountTextField;

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
					Insights frame = new Insights();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Insights() {
		setTitle("Insights Build Alpha v24072024");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(tabbedPane);
		
		JPanel cpuPanel = new JPanel();
		tabbedPane.addTab("CPU", null, cpuPanel, null);
		GridBagLayout gbl_cpuPanel = new GridBagLayout();
		gbl_cpuPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_cpuPanel.rowHeights = new int[]{0, 0};
		gbl_cpuPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_cpuPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		cpuPanel.setLayout(gbl_cpuPanel);
		
		JLabel cpuCount = new JLabel("Total CPUs in Database");
		GridBagConstraints gbc_cpuCount = new GridBagConstraints();
		gbc_cpuCount.insets = new Insets(0, 0, 0, 5);
		gbc_cpuCount.anchor = GridBagConstraints.EAST;
		gbc_cpuCount.gridx = 0;
		gbc_cpuCount.gridy = 0;
		cpuPanel.add(cpuCount, gbc_cpuCount);
		
		cpuCountTextField = new JTextField();
		GridBagConstraints gbc_cpuCountTextField = new GridBagConstraints();
		gbc_cpuCountTextField.insets = new Insets(0, 0, 0, 5);
		gbc_cpuCountTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuCountTextField.gridx = 1;
		gbc_cpuCountTextField.gridy = 0;
		cpuPanel.add(cpuCountTextField, gbc_cpuCountTextField);
		cpuCountTextField.setColumns(10);
		
		JLabel cpuBrands = new JLabel("CPU Brands");
		GridBagConstraints gbc_cpuBrands = new GridBagConstraints();
		gbc_cpuBrands.insets = new Insets(0, 0, 0, 5);
		gbc_cpuBrands.anchor = GridBagConstraints.EAST;
		gbc_cpuBrands.gridx = 2;
		gbc_cpuBrands.gridy = 0;
		cpuPanel.add(cpuBrands, gbc_cpuBrands);
		
		cpuBrandsTextField = new JTextField();
		GridBagConstraints gbc_cpuBrandsTextField = new GridBagConstraints();
		gbc_cpuBrandsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cpuBrandsTextField.gridx = 3;
		gbc_cpuBrandsTextField.gridy = 0;
		cpuPanel.add(cpuBrandsTextField, gbc_cpuBrandsTextField);
		cpuBrandsTextField.setColumns(10);
		
		JPanel gpuPanel = new JPanel();
		tabbedPane.addTab("GPU", null, gpuPanel, null);
	}
}
