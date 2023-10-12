package librarysystem;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import business.Address;
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class adminWindow extends JFrame implements LibWindow {

	public static final adminWindow INSTANCE = new adminWindow();
	private DataAccessFacade db;
	private boolean isInitialized = false;
	public String person = "default";
//	public List<Author> allAuthor;
	JLabel mainWindowHeaderPanelText;
	JPanel mainWindowContentPanel;

	private JFrame frame;
	private JTextField am_memberID;
	private JTextField am_firstName;
	private JTextField am_lastName;
	private JTextField am_street;
	private JTextField am_city;
	private JTextField am_state;
	private JTextField am_zip;
	private JTextField am_phoneNumber;
	
	
	@Override
	public void init() {
		try {
			adminWindow window = new adminWindow();
			window.frame.setVisible(true);
			connectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
		
	}
	
	public void loginPerson(String text) {
		person = text;
		
	}
	public void connectDB() {
		db = new DataAccessFacade();
	}
	public void addMember() {
		Address address = new Address("St4th", "fairfield", "IOWA", "54638");
		LibraryMember lb = new LibraryMember("120", "Vijay", "Mano", "7482749372", address);
		db.saveNewMember(lb);
	}
	
	public void addAuthor() {
		
	}
	
	public void addBook() {
//		List<Author> author = Arrays.asList();
		Book newBook = new Book("2323", "Java 5", 7, null);
		db.addNewBook(null);
	}
	
	public void checkout() {
		
	}
	/**
	 * 
	 * Launch the application.
	 */

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					adminWindow window = new adminWindow();
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
	public adminWindow() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();				 	 //@dip06ec: Calculating screen dimension
		frame.setBounds(0, 0, (int)(screenSize.width/1.5), (int)(screenSize.height/1.5));	 //@dip06ece: Setting frame size to full screen
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);									 //@dip06ece: Maximize the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.getContentPane().setLayout(null);

		JPanel mainWindowTextePanel = new JPanel();
		mainWindowTextePanel.setBounds(10, 11, 234, 59);
		frame.getContentPane().add(mainWindowTextePanel);

		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setFont(new Font("Takoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindowTextePanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(person);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		mainWindowTextePanel.add(lblNewLabel_1);

//		JLabel lblNewLabel_1 = new JLabel(person);
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
//		mainWindowTextePanel.add(lblNewLabel_1);

		JPanel mainWindowButtonePanel = new JPanel();
		mainWindowButtonePanel.setBounds(10, 81, 234, 381);
		frame.getContentPane().add(mainWindowButtonePanel);
		mainWindowButtonePanel.setLayout(null);

		JButton btnLogOutButton = new JButton("Log Out ");
		btnLogOutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {						//@dip06ece: On Mouse click program exits
				System.exit(0);
			}
		});
		btnLogOutButton.setBounds(10, 5, 214, 23);
		mainWindowButtonePanel.add(btnLogOutButton);

		JButton btnAddMember = new JButton("Add Member");
		// Adding new members to Database
		btnAddMember.addMouseListener(new MouseAdapter() {					//@dip06ece: Actions to add new member
			@Override
			public void mouseClicked(MouseEvent e) {
				mainWindowHeaderPanelText.setText("Add Member");
				sidePanelAddMember();
			}
		});
		btnAddMember.setBounds(10, 33, 214, 23);
		mainWindowButtonePanel.add(btnAddMember);

		JButton btnEditMember = new JButton("Edit Member");
		btnEditMember.setBounds(10, 63, 214, 23);
		mainWindowButtonePanel.add(btnEditMember);

		JButton btnRemoveMember = new JButton("Remove Member");
		btnRemoveMember.setBounds(10, 94, 214, 23);
		mainWindowButtonePanel.add(btnRemoveMember);

		JButton btnMemberList = new JButton("Member List");
		btnMemberList.setBounds(10, 123, 214, 23);
		mainWindowButtonePanel.add(btnMemberList);

		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(10, 153, 214, 23);
		mainWindowButtonePanel.add(btnAddBook);

		JButton btnAddCopy = new JButton("Add Copy");
		btnAddCopy.setBounds(10, 184, 214, 23);
		mainWindowButtonePanel.add(btnAddCopy);

		JButton btnBookList = new JButton("Book List");
		btnBookList.setBounds(10, 214, 214, 23);
		mainWindowButtonePanel.add(btnBookList);

		JPanel mainWindowHeaderPanel = new JPanel();
		mainWindowHeaderPanel.setBounds(254, 11, 630, 59);
		frame.getContentPane().add(mainWindowHeaderPanel);
		mainWindowHeaderPanel.setLayout(null);

		JPanel mainWindowContentPanel = new JPanel();
		mainWindowContentPanel.setBounds(254, 81, 630, 381);
		frame.getContentPane().add(mainWindowContentPanel);
		mainWindowContentPanel.setLayout(null);

		mainWindowHeaderPanelText = new JLabel("Welcome!");
		mainWindowHeaderPanelText.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindowHeaderPanelText.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainWindowHeaderPanelText.setBounds(10, 11, 610, 37);
		mainWindowHeaderPanel.add(mainWindowHeaderPanelText);

	}
	
	
	private void sidePanelAddMember() {
		frame.repaint();
		mainWindowContentPanel = new JPanel();
		mainWindowContentPanel.setBounds(254, 81, 630, 381);
		frame.getContentPane().add(mainWindowContentPanel);
		mainWindowContentPanel.setLayout(null);
		
		JButton btnAddMemberFinal = new JButton("Add This Member");
		btnAddMemberFinal.addMouseListener(new MouseAdapter() {							//@dip06ece: Add Member function here
			@Override
			public void mouseClicked(MouseEvent e) {									//@vijay: ###
			}
		});
		btnAddMemberFinal.setBounds(430, 311, 135, 23);
		mainWindowContentPanel.add(btnAddMemberFinal);
		
		am_memberID = new JTextField();
		am_memberID.setBounds(85, 60, 182, 20);
		mainWindowContentPanel.add(am_memberID);
		am_memberID.setColumns(10);
		
		am_firstName = new JTextField();
		am_firstName.setBounds(85, 118, 182, 20);
		mainWindowContentPanel.add(am_firstName);
		am_firstName.setColumns(10);
		
		am_lastName = new JTextField();
		am_lastName.setBounds(85, 177, 182, 20);
		mainWindowContentPanel.add(am_lastName);
		am_lastName.setColumns(10);
		
		am_street = new JTextField();
		am_street.setBounds(85, 240, 182, 20);
		mainWindowContentPanel.add(am_street);
		am_street.setColumns(10);
		
		am_city = new JTextField();
		am_city.setBounds(383, 60, 182, 20);
		mainWindowContentPanel.add(am_city);
		am_city.setColumns(10);
		
		am_state = new JTextField();
		am_state.setBounds(383, 118, 182, 20);
		mainWindowContentPanel.add(am_state);
		am_state.setColumns(10);
		
		am_zip = new JTextField();
		am_zip.setBounds(383, 177, 182, 20);
		mainWindowContentPanel.add(am_zip);
		am_zip.setColumns(10);
		
		am_phoneNumber = new JTextField();
		am_phoneNumber.setBounds(383, 240, 182, 20);
		mainWindowContentPanel.add(am_phoneNumber);
		am_phoneNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Member ID");
		lblNewLabel_2.setBounds(85, 45, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("FirstName");
		lblNewLabel_3.setBounds(85, 103, 173, 14);
		mainWindowContentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		lblNewLabel_4.setBounds(85, 162, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Street");
		lblNewLabel_5.setBounds(85, 224, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("City");
		lblNewLabel_6.setBounds(383, 45, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("State");
		lblNewLabel_7.setBounds(383, 103, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Zip");
		lblNewLabel_8.setBounds(383, 162, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Phone Number");
		lblNewLabel_9.setBounds(383, 224, 182, 14);
		mainWindowContentPanel.add(lblNewLabel_9);
		frame.getContentPane().add(mainWindowContentPanel);
	}
}
