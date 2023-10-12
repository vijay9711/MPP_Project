package librarysystem;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class adminWindow extends JFrame implements LibWindow {

	public static final adminWindow INSTANCE = new adminWindow();

	private boolean isInitialized = false;
	private JFrame frame;
	public String person = "default";
	/**
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






	}
	@Override
	public void init() {
		try {
			adminWindow window = new adminWindow();
			window.frame.setVisible(true);
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
}
