package librarysystem;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import business.Address;
import business.LibraryMember;
import dataaccess.DataAccessFacade;

public class AddMember extends JFrame implements LibWindow {

	public static final AddMember INSTANCE = new AddMember();
	private DataAccessFacade db;
	private boolean isInitialized = false;
	private JFrame frame;
	
	
	public void connectDB() {
		db = new DataAccessFacade();
	}
	public void addMember() {
		Address address = new Address("St4th", "fairfield", "IOWA", "54638");
		LibraryMember lb = new LibraryMember("120", "Vijay", "Mano", "7482749372", address);
		db.saveNewMember(lb);
	}

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember window = new AddMember();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddMember window = new AddMember();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AddMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainWindowContentPanel1 = new JPanel();
		mainWindowContentPanel1.setBounds(254, 81, 630, 381);
		frame.getContentPane().add(mainWindowContentPanel1);
		mainWindowContentPanel1.setLayout(null);
		
		JLabel lblHeader = new JLabel("Add Member");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHeader.setBounds(233, 11, 155, 14);
		mainWindowContentPanel1.add(lblHeader);
		
		JTextField am_memberID = new JTextField();
		am_memberID.setBounds(85, 60, 182, 20);
		mainWindowContentPanel1.add(am_memberID);
		am_memberID.setColumns(10);
		
		JTextField am_firstName = new JTextField();
		am_firstName.setBounds(85, 118, 182, 20);
		mainWindowContentPanel1.add(am_firstName);
		am_firstName.setColumns(10);
		
		JTextField am_lastName = new JTextField();
		am_lastName.setBounds(85, 177, 182, 20);
		mainWindowContentPanel1.add(am_lastName);
		am_lastName.setColumns(10);
		
		JTextField am_street = new JTextField();
		am_street.setBounds(85, 240, 182, 20);
		mainWindowContentPanel1.add(am_street);
		am_street.setColumns(10);
		
		JTextField am_city = new JTextField();
		am_city.setBounds(383, 60, 182, 20);
		mainWindowContentPanel1.add(am_city);
		am_city.setColumns(10);
		
		JTextField am_state = new JTextField();
		am_state.setBounds(383, 118, 182, 20);
		mainWindowContentPanel1.add(am_state);
		am_state.setColumns(10);
		
		JTextField am_zip = new JTextField();
		am_zip.setBounds(383, 177, 182, 20);
		mainWindowContentPanel1.add(am_zip);
		am_zip.setColumns(10);
		
		JTextField am_phoneNumber = new JTextField();
		am_phoneNumber.setBounds(383, 240, 182, 20);
		mainWindowContentPanel1.add(am_phoneNumber);
		am_phoneNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Member ID");
		lblNewLabel_2.setBounds(85, 45, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("FirstName");
		lblNewLabel_3.setBounds(85, 103, 173, 14);
		mainWindowContentPanel1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		lblNewLabel_4.setBounds(85, 162, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Street");
		lblNewLabel_5.setBounds(85, 224, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("City");
		lblNewLabel_6.setBounds(383, 45, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("State");
		lblNewLabel_7.setBounds(383, 103, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Zip");
		lblNewLabel_8.setBounds(383, 162, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Phone Number");
		lblNewLabel_9.setBounds(383, 224, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_9);
		frame.getContentPane().add(mainWindowContentPanel1);
		
		// Submit Button for the Adding a new member
		JButton btnAddMemberFinal = new JButton("Add This Member");
		btnAddMemberFinal.addMouseListener(new MouseAdapter() {							//@dip06ece: Add Member function here
			@Override
			public void mouseClicked(MouseEvent e) {									//@vijay: ###
			}
		});
		btnAddMemberFinal.setBounds(430, 311, 135, 23);
		mainWindowContentPanel1.add(btnAddMemberFinal);
		
		
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addActionListener(new BackToMainListener());
		backButton.setBounds(100, 311, 135, 23);
		mainWindowContentPanel1.add(backButton);
	}
	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			LibrarySystem.hideAllWindows();
			adminWindow.INSTANCE.toggleAdminFrame(true);
    		pack();
		}
	}
}
