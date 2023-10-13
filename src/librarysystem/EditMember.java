package librarysystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.Address;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import librarysystem.AddMember.BackToMainListener;

public class EditMember extends JFrame implements LibWindow {

	
	public static final EditMember INSTANCE = new EditMember();
	private DataAccessFacade db;
	private JTextField am_memberID;
	private JTextField am_firstName;
	private JTextField am_lastName;
	private JTextField am_street;
	private JTextField am_city;
	private JTextField am_state;
	private JTextField am_zip;
	private JTextField am_phoneNumber;
	
	private JFrame frame;
	
	public void connectDB() {
		db = new DataAccessFacade();
	}
	public DefaultComboBoxModel generateComboBoxModel() {
		connectDB();															// Connect to Database
		HashMap<String, LibraryMember> membermap = db.readMemberMap();			// Reading from Hash Map
		Vector comboBoxItems=new Vector();										// Creating a vector
		for(Entry<String, LibraryMember> item:membermap.entrySet()) {			
			//System.out.println(item.getValue().getMemberId()+ " | "+item.getValue().getFirstName()+" "+item.getValue().getLastName());
			comboBoxItems.add("["+item.getValue().getMemberId()+"] | "+item.getValue().getFirstName()+" "+item.getValue().getLastName());
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
		return model;
	}
	public LibraryMember returnMember(String temp) {										// Returns selected Library Member
		connectDB();
		HashMap<String, LibraryMember> membermap = db.readMemberMap();
		for(Entry<String, LibraryMember> item:membermap.entrySet()) {			
			if ((item.getValue().getMemberId()).equals(temp)) {
				//String memberDetails[] = {item.getValue().getMemberId(), item.getValue().getFirstName()};
				return new LibraryMember(item.getValue().getMemberId(),item.getValue().getFirstName(),item.getValue().getLastName(),item.getValue().getTelephone(),item.getValue().getAddress());
			}
			
		}
		return null; // This will not be executed
		
	}
	
	public void modifyMemberEntry() {						// Rewriting edited data to HashMap
		Address address = new Address(am_street.getText(), am_city.getText(), am_state.getText(), am_zip.getText());
		LibraryMember lb = new LibraryMember(am_memberID.getText(), am_firstName.getText(), am_lastName.getText(), am_phoneNumber.getText(), address);
		LibraryMember member = db.saveNewMember(lb);
		StringBuilder st = new StringBuilder();
		st.append("Library Member data of ID "+am_memberID.getText()+" is Modified Successfully!");
		JOptionPane.showMessageDialog(this,st.toString());
		LibrarySystem.hideAllWindows();
		toggleAddMemeberFrame(false);
		adminWindow.INSTANCE.toggleAdminFrame(true);
		pack();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMember window = new EditMember();
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
	public EditMember() {
		initialize();
	}
	
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMember window = new EditMember();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 150, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainWindowContentPanel1 = new JPanel();
		mainWindowContentPanel1.setBounds(254, 81, 630, 381);
		frame.getContentPane().add(mainWindowContentPanel1);
		mainWindowContentPanel1.setLayout(null);
		
		JLabel lblNewLabel_18 = new JLabel("Select Member");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_18.setBounds(85, 42, 109, 23);
		mainWindowContentPanel1.add(lblNewLabel_18);
				
		
		
		// populating Combobox Member list
		
		JComboBox comboBox1 = new JComboBox(generateComboBoxModel()); // Generating combo box items from generated model
		comboBox1.setBounds(188, 43, 267, 22);
		mainWindowContentPanel1.add(comboBox1);
		// Populating member list 
		
				
		JButton btnModifyMemberInitial = new JButton("Modify");
		btnModifyMemberInitial.addMouseListener(new MouseAdapter() {   				//@Add event to Modify selected member record
			@Override																
			public void mouseClicked(MouseEvent e) {
				String item = comboBox1.getSelectedItem().toString();
				String temp = item.substring(1,5);									// Need to change if Library member reaches 9999
				LibraryMember selectedMember = returnMember(temp);
				if (selectedMember!=null) {
					am_memberID.setText(selectedMember.getMemberId());
					am_firstName.setText(selectedMember.getFirstName());
					am_lastName.setText(selectedMember.getLastName());
					am_street.setText(selectedMember.getAddress().getStreet());
					am_city.setText(selectedMember.getAddress().getCity());
					am_state.setText(selectedMember.getAddress().getState());
					am_zip.setText(selectedMember.getAddress().getZip());
					am_phoneNumber.setText(selectedMember.getTelephone());
				}
				
				//System.out.println(temp);
			}
		});
		btnModifyMemberInitial.setBounds(465, 43, 97, 23);
		mainWindowContentPanel1.add(btnModifyMemberInitial);
		
		
		JLabel lblHeader = new JLabel("Edit Member Data");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHeader.setBounds(233, 11, 155, 14);
		mainWindowContentPanel1.add(lblHeader);
		
		am_memberID = new JTextField();
		am_memberID.setEditable(false);
		am_memberID.setBounds(85, 100, 182, 20);
		mainWindowContentPanel1.add(am_memberID);
		am_memberID.setColumns(10);
		
		am_firstName = new JTextField();
		am_firstName.setBounds(85, 159, 182, 20);
		mainWindowContentPanel1.add(am_firstName);
		am_firstName.setColumns(10);
		
		am_lastName = new JTextField();
		am_lastName.setBounds(85, 219, 182, 20);
		mainWindowContentPanel1.add(am_lastName);
		am_lastName.setColumns(10);
		
		am_street = new JTextField();
		am_street.setBounds(85, 277, 182, 20);
		mainWindowContentPanel1.add(am_street);
		am_street.setColumns(10);
		
		am_city = new JTextField();
		am_city.setBounds(383, 100, 182, 20);
		mainWindowContentPanel1.add(am_city);
		am_city.setColumns(10);
		
		am_state = new JTextField();
		am_state.setBounds(383, 159, 182, 20);
		mainWindowContentPanel1.add(am_state);
		am_state.setColumns(10);
		
		am_zip = new JTextField();
		am_zip.setBounds(383, 219, 182, 20);
		mainWindowContentPanel1.add(am_zip);
		am_zip.setColumns(10);
		
		am_phoneNumber = new JTextField();
		am_phoneNumber.setBounds(383, 277, 182, 20);
		mainWindowContentPanel1.add(am_phoneNumber);
		am_phoneNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Member ID");
		lblNewLabel_2.setBounds(85, 82, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("FirstName");
		lblNewLabel_3.setBounds(85, 144, 173, 14);
		mainWindowContentPanel1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		lblNewLabel_4.setBounds(85, 204, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Street");
		lblNewLabel_5.setBounds(85, 261, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("City");
		lblNewLabel_6.setBounds(383, 82, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("State");
		lblNewLabel_7.setBounds(383, 144, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Zip");
		lblNewLabel_8.setBounds(383, 204, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Phone Number");
		lblNewLabel_9.setBounds(383, 261, 182, 14);
		mainWindowContentPanel1.add(lblNewLabel_9);
		frame.getContentPane().add(mainWindowContentPanel1);
		
		// Submit Button for the Adding a new member
		JButton btnAddMemberFinal = new JButton("Edit This Member");
		btnAddMemberFinal.addMouseListener(new MouseAdapter() {							//@dip06ece: Add Member function here
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyMemberEntry();//@vijay: ###
			}
		});
		btnAddMemberFinal.setBounds(430, 327, 135, 23);
		mainWindowContentPanel1.add(btnAddMemberFinal);
		
		
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addMouseListener(new MouseAdapter() {							//@dip06ece: Add Member function here
			@Override
			public void mouseClicked(MouseEvent e) {									//@vijay: ###
			}
		});
		backButton.setBounds(85, 327, 135, 23);
		mainWindowContentPanel1.add(backButton);
		
		// back button to move back to main menu

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
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}
}
