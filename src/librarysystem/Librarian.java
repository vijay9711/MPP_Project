package librarysystem;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

public class Librarian extends JFrame implements LibWindow {
	private Librarian() {
		
	}
	public static final Librarian INSTANCE = new Librarian();
	
	private boolean isInitialized = false;
	
	private JPanel mainPanel;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		formatContentPane();
		addComponent();
		isInitialized(true); 
		getContentPane().add(mainPanel);
		pack();
	}
	 private void formatContentPane() {
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(1,1));
			getContentPane().add(mainPanel);	
	 }
	public void addComponent() {
		
		JMenuBar admin = new JMenuBar();
		admin.setBorderPainted(false);
		admin.setBackground(new Color(255, 255, 255));
		setJMenuBar(admin);
		
		JMenu admin_1 = new JMenu("New menu");
		admin.add(admin_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Checkout");
		admin_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("logout");
		admin_1.add(mntmNewMenuItem_1);
		
		mainPanel.add(admin);
		mainPanel.add(admin_1);
	}
	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
		
	}

}
