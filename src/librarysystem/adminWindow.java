package librarysystem;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	JLabel mainWindowHeaderPanelText;
	JPanel mainWindowContentPanel1;
	JPanel mainWindowContentPanel2;

	private JFrame frame;
	private JTextField am_memberID;
	private JTextField am_firstName;
	private JTextField am_lastName;
	private JTextField am_street;
	private JTextField am_city;
	private JTextField am_state;
	private JTextField am_zip;
	private JTextField am_phoneNumber;

	private JTextField am_memberID2;
	private JTextField am_firstName2;
	private JTextField am_lastName2;
	private JTextField am_street2;
	private JTextField am_city2;
	private JTextField am_state2;
	private JTextField am_zip2;
	private JTextField am_phoneNumber2;


	@Override
	public void init() {
		try {
			adminWindow window = new adminWindow();
			window.frame.setVisible(true);
			pack();
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
		btnEditMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainWindowHeaderPanelText.setText("Modify Member Data");
				sidePanelModifyMember();
			}
		});
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
		pack();

	}

        private void sidePanelModifyMember() {
			if(mainWindowContentPanel1!=null)
				frame.remove(mainWindowContentPanel1);
			// Working on Edit member
			mainWindowContentPanel2 = new JPanel();
			mainWindowContentPanel2.setBounds(254, 81, 630, 381);
			frame.getContentPane().add(mainWindowContentPanel2);
			mainWindowContentPanel2.setLayout(null);

			JButton btnModifyMemberFinal = new JButton("Modify This Member");
			btnModifyMemberFinal.addMouseListener(new MouseAdapter() {							//@dip06ece: Modify Member function here
				@Override
				public void mouseClicked(MouseEvent e) {										//@vijay: ###
				}
			});
				btnModifyMemberFinal.setBounds(430, 311, 135, 23);
				mainWindowContentPanel2.add(btnModifyMemberFinal);

				am_memberID2 = new JTextField();
				am_memberID2.setEditable(false);
				am_memberID2.setBounds(85, 60, 182, 20);
				mainWindowContentPanel2.add(am_memberID2);
				am_memberID2.setColumns(10);

				am_firstName2 = new JTextField();
				am_firstName2.setBounds(85, 118, 182, 20);
				mainWindowContentPanel2.add(am_firstName2);
				am_firstName2.setColumns(10);

				am_lastName2 = new JTextField();
				am_lastName2.setBounds(85, 177, 182, 20);
				mainWindowContentPanel2.add(am_lastName2);
				am_lastName2.setColumns(10);

				am_street2 = new JTextField();
				am_street2.setBounds(85, 240, 182, 20);
				mainWindowContentPanel2.add(am_street2);
				am_street2.setColumns(10);

				am_city2 = new JTextField();
				am_city2.setBounds(383, 60, 182, 20);
				mainWindowContentPanel2.add(am_city2);
				am_city2.setColumns(10);

				am_state2 = new JTextField();
				am_state2.setBounds(383, 118, 182, 20);
				mainWindowContentPanel2.add(am_state2);
				am_state2.setColumns(10);

				am_zip2 = new JTextField();
				am_zip2.setBounds(383, 177, 182, 20);
				mainWindowContentPanel2.add(am_zip2);
				am_zip2.setColumns(10);

				am_phoneNumber2 = new JTextField();
				am_phoneNumber2.setBounds(383, 240, 182, 20);
				mainWindowContentPanel2.add(am_phoneNumber2);
				am_phoneNumber2.setColumns(10);

				JLabel lblNewLabel_10 = new JLabel("Member ID");
				lblNewLabel_10.setBounds(85, 45, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_10);

				JLabel lblNewLabel_11 = new JLabel("FirstName");
				lblNewLabel_11.setBounds(85, 103, 173, 14);
				mainWindowContentPanel2.add(lblNewLabel_11);

				JLabel lblNewLabel_12 = new JLabel("Last Name");
				lblNewLabel_12.setBounds(85, 162, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_12);

				JLabel lblNewLabel_13 = new JLabel("Street");
				lblNewLabel_13.setBounds(85, 224, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_13);

				JLabel lblNewLabel_14 = new JLabel("City");
				lblNewLabel_14.setBounds(383, 45, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_14);

				JLabel lblNewLabel_15 = new JLabel("State");
				lblNewLabel_15.setBounds(383, 103, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_15);

				JLabel lblNewLabel_16 = new JLabel("Zip");
				lblNewLabel_16.setBounds(383, 162, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_16);

				JLabel lblNewLabel_17 = new JLabel("Phone Number");
				lblNewLabel_17.setBounds(383, 224, 182, 14);
				mainWindowContentPanel2.add(lblNewLabel_17);
				frame.getContentPane().add(mainWindowContentPanel2);

				JLabel lblNewLabel_18 = new JLabel("Select Member");
				lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_18.setBounds(85, 11, 109, 23);
				mainWindowContentPanel2.add(lblNewLabel_18);

				JComboBox comboBox1 = new JComboBox();
				comboBox1.setBounds(191, 12, 267, 22);
				mainWindowContentPanel2.add(comboBox1);

				JButton btnModifyMemberInitial = new JButton("Modify");
				btnModifyMemberInitial.addMouseListener(new MouseAdapter() {   				//@Add event to Modify selected member record
					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
				btnModifyMemberInitial.setBounds(468, 11, 97, 23);
				mainWindowContentPanel2.add(btnModifyMemberInitial);
				mainWindowContentPanel2.revalidate();
				mainWindowContentPanel2.repaint();
				frame.revalidate();
				frame.repaint();
				pack();
					// Working on Edit member Done
		}

	private void sidePanelAddMember() {
		if (mainWindowContentPanel2!=null)
			frame.remove(mainWindowContentPanel2);
		mainWindowContentPanel1 = new JPanel();
		mainWindowContentPanel1.setBounds(254, 81, 630, 381);
		frame.getContentPane().add(mainWindowContentPanel1);
		mainWindowContentPanel1.setLayout(null);
		
		JButton btnAddMemberFinal = new JButton("Add This Member");
		btnAddMemberFinal.addMouseListener(new MouseAdapter() {							//@dip06ece: Add Member function here
			@Override
			public void mouseClicked(MouseEvent e) {									//@vijay: ###
			}
		});
		btnAddMemberFinal.setBounds(430, 311, 135, 23);
		mainWindowContentPanel1.add(btnAddMemberFinal);
		
		am_memberID = new JTextField();
		am_memberID.setBounds(85, 60, 182, 20);
		mainWindowContentPanel1.add(am_memberID);
		am_memberID.setColumns(10);
		
		am_firstName = new JTextField();
		am_firstName.setBounds(85, 118, 182, 20);
		mainWindowContentPanel1.add(am_firstName);
		am_firstName.setColumns(10);
		
		am_lastName = new JTextField();
		am_lastName.setBounds(85, 177, 182, 20);
		mainWindowContentPanel1.add(am_lastName);
		am_lastName.setColumns(10);
		
		am_street = new JTextField();
		am_street.setBounds(85, 240, 182, 20);
		mainWindowContentPanel1.add(am_street);
		am_street.setColumns(10);
		
		am_city = new JTextField();
		am_city.setBounds(383, 60, 182, 20);
		mainWindowContentPanel1.add(am_city);
		am_city.setColumns(10);
		
		am_state = new JTextField();
		am_state.setBounds(383, 118, 182, 20);
		mainWindowContentPanel1.add(am_state);
		am_state.setColumns(10);
		
		am_zip = new JTextField();
		am_zip.setBounds(383, 177, 182, 20);
		mainWindowContentPanel1.add(am_zip);
		am_zip.setColumns(10);
		
		am_phoneNumber = new JTextField();
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

		mainWindowContentPanel1.revalidate();
		mainWindowContentPanel1.repaint();
		frame.revalidate();
		frame.repaint();
		pack();
	}
}
