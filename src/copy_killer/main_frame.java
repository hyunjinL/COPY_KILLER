package copy_killer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

class Main_Frame extends JFrame {
	
	private JLabel made_by = new JLabel("Made by Hyun Jin Lee, Sang Woo Han");
	private JPanel upper_panel = new JPanel();
	
	private CardLayout main_card = new CardLayout();
	private JPanel down_panel = new JPanel();	

	private ver_1 ver1_panel = new ver_1();
	private ver_2 ver2_panel = new ver_2();
	
	private JButton ver1_btn = new JButton("Ver 1");
	private JButton ver2_btn = new JButton("Ver 2");
		
	public Main_Frame() {
		// to center the frame -> Toolkit, Dimension
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		// Frame size config
		setTitle("COPY KILLER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// position Frame at the center of screen size
		setLocation(screenSize.width/2 - 512 , screenSize.height/2 - 384);
	
		setLayout(null);
		
		// top panel
		upper_panel.setLayout(null);
		upper_panel.setSize(1036, 80);
		
		upper_panel.add(ver1_btn);
		upper_panel.add(ver2_btn);
		
		ver1_btn.setBackground(Color.white);
		ver2_btn.setBackground(Color.white);
		ver1_btn.setFocusPainted(false);
		ver2_btn.setFocusPainted(false);
		
		ver1_btn.setBounds(20, 30, 100, 25);
		ver2_btn.setBounds(170, 30, 100, 25);
		
		upper_panel.add(made_by);
		made_by.setBounds(810, 0, 250, 20);
		
		// bottom panel -> using cardLayout
		down_panel.setSize(1036, 688);
		down_panel.setLayout(main_card);
		down_panel.add(ver1_panel, "ver1");
		down_panel.add(ver2_panel, "ver2");
		
		// ver1, ver2 button event
		// push ver1 btn -> show ver1 panel
		ver1_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main_card.show(down_panel, "ver1");
			}
		});
		
		// push ver2 btn -> show ver2 panel
		ver2_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main_card.show(down_panel, "ver2");
			}
		});
		
		this.add(upper_panel);
		this.add(down_panel);
		
		upper_panel.setBounds(0, 0, 1036, 80);
		down_panel.setBounds(0, 80, 1036, 688);

		setSize(1040, 797);
		setVisible(true);
		setResizable(false);
	}
}

class Main {
	public static void main(String[] args)
	{
		Main_Frame t = new Main_Frame();
	}
}