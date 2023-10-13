package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

import javax.swing.SwingConstants;

import business.LibraryMember;
import dataaccess.DataAccessFacade;
import librarysystem.AddMember.BackToMainListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class CheckOutBook  extends JFrame implements LibWindow{
	public static final CheckOutBook INSTANCE = new CheckOutBook();
	private JFrame frame;
	private JTextField checkoutISBN;
	private JTextField checkoutMemberID;
	private JTable table;
	private boolean isInitialized = false;

	private DataAccessFacade db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOutBook window = new CheckOutBook();
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
	public CheckOutBook() {
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
		
		JLabel lblNewLabel = new JLabel("Checkout a Book");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(252, 11, 133, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN Number");
		lblNewLabel_1.setBounds(54, 53, 117, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		checkoutISBN = new JTextField();
		checkoutISBN.setBounds(54, 71, 230, 20);
		frame.getContentPane().add(checkoutISBN);
		checkoutISBN.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("MemberID");
		lblNewLabel_2.setBounds(294, 53, 104, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		checkoutMemberID = new JTextField();
		checkoutMemberID.setBounds(294, 71, 170, 20);
		frame.getContentPane().add(checkoutMemberID);
		checkoutMemberID.setColumns(10);
		
		JButton btnCheckOut = new JButton("Checkout");
		btnCheckOut.setBounds(474, 70, 120, 23);
		btnCheckOut.addActionListener(new checkoutStatus());
		frame.getContentPane().add(btnCheckOut);
		
		JLabel lblCheckOutMsg = new JLabel("Checkout Status Message");
		lblCheckOutMsg.setBounds(54, 98, 410, 23);
		frame.getContentPane().add(lblCheckOutMsg);
		
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addActionListener(new BackToMainListener());
		backButton.setBounds(54, 333, 135, 23);
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
	public void connectDB() {
		db = new DataAccessFacade();
	}
	class checkoutStatus implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			connectDB();
			business.Book b = db.getBook(checkoutISBN.getText());
			LibraryMember member = db.getMember(checkoutMemberID.getText());
			StringBuilder sb = new StringBuilder();
			if(b == null) {
				sb.append("The Book ISBN id("+checkoutISBN.getText()+") is not found in DataBase." +"\n");
			}
			if(member == null) {
				sb.append("The Member id("+checkoutISBN.getText()+") is not found in DataBase." +"\n");
			}
			if(b == null | member == null) {
				sb.append("Please try again!");
				JOptionPane.showMessageDialog(CheckOutBook.INSTANCE,sb.toString());
			}
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
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}
}
