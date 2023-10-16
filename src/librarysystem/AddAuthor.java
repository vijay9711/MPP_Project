package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import business.Address;
import business.Author;
import dataaccess.DataAccessFacade;
import librarysystem.AddBook.BackToMainListener;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddAuthor extends JFrame implements LibWindow {
	public static final AddAuthor INSTANCE = new AddAuthor();
	private DataAccessFacade db;
	private boolean isInitialized = false;

	private JFrame frame;
	private JTextField authorFirstName;
	private JTextField authorLastName;
	private JTextField authorStreet;
	private JTextField authorCity;
	private JTextField authorAddressState;
	private JTextField AuthorAddressZip;
	private JTextField authorAddressPhone;
	private JTextArea authorBio;
	private JComboBox authorCredentials;
	
	public List<JTextComponent> list; 
	/**
	 * Launch the application.
	 */
	public void connectDB() {
		db = new DataAccessFacade();
	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddAuthor window = new AddAuthor();
//					window.frame.setVisible(true);
//					AddAuthor.INSTANCE.connectDB();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AddAuthor() {
		connectDB();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add an Author");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(225, 11, 155, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(55, 49, 96, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		authorFirstName = new JTextField();
		authorFirstName.setName("First name");
		authorFirstName.setBounds(55, 68, 175, 20);
		frame.getContentPane().add(authorFirstName);
		authorFirstName.setColumns(10);
		
		authorLastName = new JTextField();
		authorLastName.setName("Last name");
		authorLastName.setBounds(264, 68, 175, 20);
		frame.getContentPane().add(authorLastName);
		authorLastName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(264, 49, 88, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		authorCredentials = new JComboBox();
		authorCredentials.setBounds(476, 67, 88, 22);
		authorCredentials.addItem("True");
		authorCredentials.addItem("False");
		authorCredentials.setSelectedItem("True");
		frame.getContentPane().add(authorCredentials);
		
		JLabel lblNewLabel_3 = new JLabel("Credentials");
		lblNewLabel_3.setBounds(476, 49, 75, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setBounds(55, 116, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		authorStreet = new JTextField();
		authorStreet.setName("street");
		authorStreet.setBounds(55, 132, 370, 20);
		frame.getContentPane().add(authorStreet);
		authorStreet.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setBounds(450, 116, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		authorCity = new JTextField();
		authorCity.setName("city");
		authorCity.setBounds(450, 132, 114, 20);
		frame.getContentPane().add(authorCity);
		authorCity.setColumns(10);
		
		authorAddressState = new JTextField();
		authorAddressState.setName("State");
		authorAddressState.setBounds(55, 200, 114, 20);
		frame.getContentPane().add(authorAddressState);
		authorAddressState.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setBounds(55, 183, 63, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		AuthorAddressZip = new JTextField();
		AuthorAddressZip.setName("Zipcode");
		AuthorAddressZip.setBounds(197, 200, 130, 20);
		frame.getContentPane().add(AuthorAddressZip);
		AuthorAddressZip.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("ZIP code");
		lblNewLabel_7.setBounds(197, 183, 96, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Phone Number");
		lblNewLabel_8.setBounds(352, 183, 163, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		authorAddressPhone = new JTextField();
		authorAddressPhone.setName("Phone");
		authorAddressPhone.setBounds(352, 200, 212, 20);
		frame.getContentPane().add(authorAddressPhone);
		authorAddressPhone.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Short Bio.");
		lblNewLabel_9.setBounds(55, 252, 114, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		authorBio = new JTextArea();
		authorBio.setBounds(53, 268, 511, 59);
		frame.getContentPane().add(authorBio);
		
		JButton backButton = new JButton("Cancel");
		backButton.addActionListener(new BackToAddBook());
		backButton.setBounds(10, 347, 135, 23);
		frame.add(backButton);
		
		JButton btnAddAuthor = new JButton("Add This Author");
		btnAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAuthorToDb(e);
			}
		});
		btnAddAuthor.setBounds(426, 347, 138, 23);
		frame.getContentPane().add(btnAddAuthor);
		list = Arrays.asList(authorFirstName,authorLastName,authorStreet,authorCity,authorAddressState,AuthorAddressZip,authorAddressPhone);
	}

	class BackToAddBook implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			toggleAddAuthorFrame(false);
		}
		
	}
	public void toggleAddAuthorFrame(boolean val) {
		AddAuthor.INSTANCE.setVisible(val);
		frame.setVisible(val);
	}
	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAuthor window = new AddAuthor();
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
	
	public void AddAuthorToDb(ActionEvent e) {
		if(validateAuthorForm()) {
			boolean cred = authorCredentials.getSelectedItem().toString() == "True" ? true : false;
			Address authorAddress = new Address(authorStreet.getText(), authorCity.getText(), authorAddressState.getText(), AuthorAddressZip.getText());
			Author newAuthor = new Author(authorFirstName.getText(), authorLastName.getText(), authorAddressPhone.getText(), authorAddress, cred, authorBio.getText());
			boolean res = db.addAuthor(newAuthor);
			System.out.println("Author submit out if " +res);
			if(res) {
				System.out.println("Author submit " +res);
				JOptionPane.showMessageDialog(AddBook.INSTANCE,"Author added successfully!");
				AddBook.INSTANCE.addAuthorToList(e, newAuthor);
				toggleAddAuthorFrame(false);
			}
		}
	}
	
	public boolean validateAuthorForm() {
		StringBuilder st = new StringBuilder();
		list.forEach((item)->{
			if(item.getText().trim().length() == 0) {
				st.append(item.getName() + "\n");
				
			}
			else if(item.getName() == "Zipcode" && item.getText().trim() != "" && !item.getText().matches("^[0-9]{5}$")) {
				st.append("zip code should be in 5 digits without special character.\n");
			}
			else if(item.getName() == "Phone" && item.getText().trim() != "" && !item.getText().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$")) {
				st.append("Phone number is not valid. Please enter 10 digit phone number. \n eg(000-000-0000) \n");
			}
		});
		if(st.toString() != "") {
			st.append("Please fill all field");
			JOptionPane.showMessageDialog(this,st.toString());
			return false;
		}
		return true;
	}
	
	
}
