package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddAuthor {

	private JFrame frame;
	private JTextField authorFirstName;
	private JTextField authorLastName;
	private JTextField authorStreet;
	private JTextField authorCity;
	private JTextField authorAddressState;
	private JTextField AuthorAddressZip;
	private JTextField authorAddressPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public AddAuthor() {
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
		authorFirstName.setBounds(55, 68, 175, 20);
		frame.getContentPane().add(authorFirstName);
		authorFirstName.setColumns(10);
		
		authorLastName = new JTextField();
		authorLastName.setBounds(264, 68, 175, 20);
		frame.getContentPane().add(authorLastName);
		authorLastName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(264, 49, 88, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox authorCredentials = new JComboBox();
		authorCredentials.setBounds(476, 67, 88, 22);
		authorCredentials.addItem("True");
		authorCredentials.addItem("False");
		frame.getContentPane().add(authorCredentials);
		
		JLabel lblNewLabel_3 = new JLabel("Credentials");
		lblNewLabel_3.setBounds(476, 49, 75, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setBounds(55, 116, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		authorStreet = new JTextField();
		authorStreet.setBounds(55, 132, 370, 20);
		frame.getContentPane().add(authorStreet);
		authorStreet.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setBounds(450, 116, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		authorCity = new JTextField();
		authorCity.setBounds(450, 132, 114, 20);
		frame.getContentPane().add(authorCity);
		authorCity.setColumns(10);
		
		authorAddressState = new JTextField();
		authorAddressState.setBounds(55, 200, 114, 20);
		frame.getContentPane().add(authorAddressState);
		authorAddressState.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setBounds(55, 183, 63, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		AuthorAddressZip = new JTextField();
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
		authorAddressPhone.setBounds(352, 200, 212, 20);
		frame.getContentPane().add(authorAddressPhone);
		authorAddressPhone.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Short Bio.");
		lblNewLabel_9.setBounds(55, 252, 114, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		JTextArea authorBio = new JTextArea();
		authorBio.setBounds(53, 268, 511, 59);
		frame.getContentPane().add(authorBio);
		
		JButton btnAddAuthor = new JButton("Add This Author");
		btnAddAuthor.setBounds(426, 347, 138, 23);
		frame.getContentPane().add(btnAddAuthor);
	}
}
