package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import librarysystem.AddMember.BackToMainListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CheckoutRecord extends JFrame implements LibWindow{

	public static final CheckoutRecord INSTANCE = new CheckoutRecord();
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutRecord window = new CheckoutRecord();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CheckoutRecord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 150, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Checkout Record");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(235, 11, 150, 14);
		frame.getContentPane().add(lblNewLabel);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setBounds(36, 94, 559, 238);

		// Create a couple of columns 
		model.addColumn("Member ID"); 
		model.addColumn("ISBN Number"); 
		model.addColumn("CheckOut Date"); 
		model.addColumn("Return Date"); 
		model.addColumn("Return Status"); 
		// Append a row 
		model.addRow(new Object[]{"Member ID", "ISBN Number","CheckOut Date", "Return Date", "Return Status"});
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Checkout Record:");
		lblNewLabel_1.setBounds(36, 77, 143, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(36, 50, 430, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(476, 50, 119, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Select a Member");
		lblNewLabel_2.setBounds(36, 31, 92, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addActionListener(new BackToMainListener());
		backButton.setBounds(36, 348, 135, 22);
		frame.getContentPane().add(backButton);
	}
	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			LibrarySystem.hideAllWindows();
			toggleAddMemeberFrame(false);
			adminWindow.INSTANCE.toggleAdminFrame(true);
    		pack();
		}
	}
	
	public void toggleAddMemeberFrame(boolean val) {
		AddMember.INSTANCE.setVisible(val);
		frame.setVisible(val);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}

}
