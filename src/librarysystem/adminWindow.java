package librarysystem;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.View;

import business.Address;
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class adminWindow extends JFrame implements LibWindow {

	public static final adminWindow INSTANCE = new adminWindow("default");
	private DataAccessFacade db;
	private boolean isInitialized = false;
	public String person = "default";
	JPanel mainWindowContentPanel1;
	JPanel mainWindowContentPanel2;

	private JFrame frame;

	private JTextField am_memberID2;
	private JTextField am_firstName2;
	private JTextField am_lastName2;
	private JTextField am_street2;
	private JTextField am_city2;
	private JTextField am_state2;
	private JTextField am_zip2;
	private JTextField am_phoneNumber2;
	private JLabel lblNewLabel_1;
	
	private JButton btnLogOutButton;
	private JButton btnAddMember;
	private JButton btnEditMember;
	private JButton btnRemoveMember;
	private JButton btnMemberList;
	private JButton btnAddBook;
	private JButton btnAddCopy;
	private JButton btnBookList;
	private JButton btnCheckoutBook;
	private JButton btnCheckoutRecord;
	private JButton btnOverdueBooks;

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminWindow window = new adminWindow(person);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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


	public void checkout() {

	}
	/**
	 * Create the application.
	 */
	public adminWindow(String user) {
		initialize(user);
		buttonActivate(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void buttonActivate(String user) {
		if(user.equals("Admin")) {
			btnOverdueBooks.setVisible(false);
			btnCheckoutRecord.setVisible(false);
			btnCheckoutBook.setVisible(false);
		}
		if(user.equals("Librarian")) {
			btnAddMember.setVisible(false);
			btnEditMember.setVisible(false);
			btnRemoveMember.setVisible(false);
			btnAddBook.setVisible(false);
			btnAddCopy.setVisible(false);
		}
		
		
	}
	private void initialize(String user) {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();				 	 //@dip06ec: Calculating screen dimension
		frame.setBounds(350, 150, 280, 520);	 	//@dip06ece: Setting frame size to full screen
														//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);									 //@dip06ece: Maximize the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.getContentPane().setLayout(null);
		
		JPanel mainWindowTextePanel = new JPanel();
		mainWindowTextePanel.setBounds(10, 11, 234, 59);
		frame.getContentPane().add(mainWindowTextePanel);
		mainWindowTextePanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setBounds(8, 5, 217, 21);
		lblNewLabel.setFont(new Font("Takoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindowTextePanel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel(user);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(8, 31, 217, 15);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		mainWindowTextePanel.add(lblNewLabel_1);

		JPanel mainWindowButtonePanel = new JPanel();
		mainWindowButtonePanel.setBounds(10, 81, 234, 381);
		frame.getContentPane().add(mainWindowButtonePanel);
		mainWindowButtonePanel.setLayout(null);

		btnLogOutButton = new JButton("Log Out ");
		btnLogOutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {						//@dip06ece: On Mouse click program exits
				System.exit(0);
			}
		});
		btnLogOutButton.setBounds(10, 5, 214, 23);
		mainWindowButtonePanel.add(btnLogOutButton);

		btnAddMember = new JButton("Add Member");
		// Adding new members to Database
		btnAddMember.addMouseListener(new MouseAdapter() {					
			@Override
			public void mouseClicked(MouseEvent e) {				//@dip06ece: Actions to add new member
				sidePanelAddMember();
				
			}
		});
		btnAddMember.setBounds(10, 33, 214, 23);
		mainWindowButtonePanel.add(btnAddMember);

		btnEditMember = new JButton("Edit Member");
		btnEditMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {       //@dip06ece: Need to add functions to edit member
				sidePanelModifyMember();
			}
		});
		btnEditMember.setBounds(10, 63, 214, 23);
		mainWindowButtonePanel.add(btnEditMember);

		btnRemoveMember = new JButton("Remove Member");
		btnRemoveMember.setBounds(10, 94, 214, 23);
		mainWindowButtonePanel.add(btnRemoveMember);

		btnMemberList = new JButton("Member List");
		btnMemberList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		//@dip06ece: Need to add Member List Functionality
				sidePanelMemberList();
			}
		});
		btnMemberList.setBounds(10, 123, 214, 23);
		mainWindowButtonePanel.add(btnMemberList);

		btnAddBook = new JButton("Add Book");
		btnAddBook.addMouseListener(new MouseAdapter() {					
			@Override
			public void mouseClicked(MouseEvent e) {				//@dip06ece: Actions to add new member
				showAddBook();
				
			}
		});
		btnAddBook.setBounds(10, 153, 214, 23);
		mainWindowButtonePanel.add(btnAddBook);

		btnAddCopy = new JButton("Add Copy");
		btnAddCopy.setBounds(10, 184, 214, 23);
		mainWindowButtonePanel.add(btnAddCopy);

		btnBookList = new JButton("Book List");
		btnBookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanelBookList();
			}
		});
		btnBookList.setBounds(10, 214, 214, 23);
		mainWindowButtonePanel.add(btnBookList);
		
		JButton btnCheckoutBook = new JButton("Checkout Book");
		btnCheckoutBook.setBounds(10, 245, 214, 23);
		mainWindowButtonePanel.add(btnCheckoutBook);
		
		JButton btnCheckoutRecord = new JButton("Member Checkout Record");
		btnCheckoutRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanelMemberCheckoutRecordList();
			}
		});
		btnCheckoutRecord.setBounds(10, 276, 214, 23);
		mainWindowButtonePanel.add(btnCheckoutRecord);
		
		btnOverdueBooks = new JButton("Overdue BookList");
		btnOverdueBooks.setBounds(10, 306, 214, 23);
		mainWindowButtonePanel.add(btnOverdueBooks);
		
		
		pack();

	}

	private void showAddBook() {
		LibrarySystem.hideAllWindows();
		toggleAdminFrame(false);
		AddBook.INSTANCE.init();
		Util.centerFrameOnDesktop(AddBook.INSTANCE);
		AddBook.INSTANCE.setVisible(true);
		pack();
	}
	private void sidePanelAddMember() {
		LibrarySystem.hideAllWindows();
		toggleAdminFrame(false);
		AddMember.INSTANCE.init();
//		AddMember.INSTANCE.setData();
		Util.centerFrameOnDesktop(AddMember.INSTANCE);
		AddMember.INSTANCE.setVisible(true);
		pack();
	}
	private void sidePanelModifyMember() {
		LibrarySystem.hideAllWindows();
		toggleAdminFrame(false);
		EditMember.INSTANCE.init();
//		EditMember.INSTANCE.setData();
		Util.centerFrameOnDesktop(EditMember.INSTANCE);
		EditMember.INSTANCE.setVisible(true);
		pack();
	}
	private void sidePanelMemberList() {
		LibrarySystem.hideAllWindows();
		toggleAdminFrame(false);
		MemberList.INSTANCE.init();
//		MemberList.INSTANCE.setData();
		Util.centerFrameOnDesktop(MemberList.INSTANCE);
		MemberList.INSTANCE.setVisible(true);
		pack();
	}
	private void sidePanelBookList() {
		LibrarySystem.hideAllWindows();
		toggleAdminFrame(false);
		BookList.INSTANCE.init();
//		BookList.INSTANCE.setData();
		Util.centerFrameOnDesktop(BookList.INSTANCE);
		BookList.INSTANCE.setVisible(true);
		pack();
	}
	private void sidePanelMemberCheckoutRecordList() {
		LibrarySystem.hideAllWindows();
		toggleAdminFrame(false);
		MemberCheckoutRecord.INSTANCE.init();
//		MemberCheckoutRecord.INSTANCE.setData();
		Util.centerFrameOnDesktop(MemberCheckoutRecord.INSTANCE);
		MemberCheckoutRecord.INSTANCE.setVisible(true);
		pack();
	}
	public void toggleAdminFrame(boolean val) {
		adminWindow.INSTANCE.setVisible(val);
		frame.setVisible(val);
	}
}
