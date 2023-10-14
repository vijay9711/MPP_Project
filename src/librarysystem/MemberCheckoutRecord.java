package librarysystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.Author;
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import librarysystem.BookList.BackToMainListener;

public class MemberCheckoutRecord extends JFrame implements LibWindow {

	public static final MemberCheckoutRecord INSTANCE = new MemberCheckoutRecord();
	private JFrame frame;
	private JTable table;
	private DataAccessFacade db;
	private DefaultTableModel model;

	
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberCheckoutRecord window = new MemberCheckoutRecord();
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
	public MemberCheckoutRecord() {
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
		
		JLabel lblNewLabel = new JLabel("Memberwise Checkout Record");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(217, 2, 204, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_18 = new JLabel("Select Member");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_18.setBounds(85, 42, 109, 23);
		frame.getContentPane().add(lblNewLabel_18);
		
		// populating Combobox Member list
		
				JComboBox comboBox1 = new JComboBox(generateComboBoxModel()); // Generating combo box items from generated model
				comboBox1.setBounds(188, 43, 267, 22);
				frame.getContentPane().add(comboBox1);
				// Populating member list 
				
				JButton btnModifyMemberInitial = new JButton("Modify");
				btnModifyMemberInitial.addMouseListener(new MouseAdapter() {   				//@Add event to Modify selected member record
					@Override																
					public void mouseClicked(MouseEvent e) {
						String item = comboBox1.getSelectedItem().toString();
						String temp = item.substring(1,5);									// Need to change if Library member reaches 9999
						LibraryMember selectedMember = returnMember(temp);
						if (selectedMember!=null) {
							
						}
						
						//System.out.println(temp);
					}
				});
				btnModifyMemberInitial.setBounds(465, 43, 97, 23);
				frame.getContentPane().add(btnModifyMemberInitial);
		
		// back button to move back to main menu
				JButton backButton = new JButton("Back to Main");
				backButton.addActionListener(new BackToMainListener());
				backButton.setBounds(21, 347, 135, 23);
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
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberCheckoutRecord window = new MemberCheckoutRecord();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
