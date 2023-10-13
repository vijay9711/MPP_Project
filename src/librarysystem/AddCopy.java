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
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import librarysystem.AddMember.BackToMainListener;

public class AddCopy extends JFrame implements LibWindow {

	
	public static final AddCopy INSTANCE = new AddCopy();
	private DataAccessFacade db;
	private JTextField copyCount;
	
	private JFrame frame;
	
	public void connectDB() {
		db = new DataAccessFacade();
	}
	public DefaultComboBoxModel generateComboBoxModel() {
		connectDB();															// Connect to Database
		HashMap<String,Book> bookList =db.readBooksMap();						// Reading from Hash Map
		Vector comboBoxItems=new Vector();										// Creating a vector
		for(Entry<String, Book> item:bookList.entrySet()) {			
			comboBoxItems.add("["+item.getValue().getIsbn()+"] | "+item.getValue().getTitle());
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
		return model;
	}
	
	public void addBookCopy(String ISBN, int copyCount) {  						// Book copy function here
		connectDB();
		Book bookToCopy = db.getBook(ISBN);
		//System.out.println(bookToCopy.getIsbn()+" "+bookToCopy.getNumCopies() );
		for(int i=0; i<copyCount;i++) {
			bookToCopy.addCopy();
		}
		StringBuilder st = new StringBuilder();
		st.append(copyCount +" copy books"
				+"\nTitle: "+ bookToCopy.getTitle() 
				+"\nISBN "+ ISBN+ " \nis now added to Library!");
		
		JOptionPane.showMessageDialog(this,st.toString());
		//System.out.println(bookToCopy.getIsbn()+" "+bookToCopy.getNumCopies() );
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCopy window = new AddCopy();
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
	public AddCopy() {
		initialize();
	}
	
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCopy window = new AddCopy();
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
		
		JLabel lblSelectBook = new JLabel("Select Book");
		lblSelectBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelectBook.setBounds(38, 43, 109, 23);
		mainWindowContentPanel1.add(lblSelectBook);
				
		
		
		// populating Combobox Member list
		
		JComboBox bookList = new JComboBox(generateComboBoxModel()); // Generating combo box items from generated model
		bookList.setBounds(36, 66, 321, 23);
		mainWindowContentPanel1.add(bookList);
		// Populating member list 
		
				
		JButton btnAddCopy = new JButton("Add Copy");
		btnAddCopy.addMouseListener(new MouseAdapter() {   				//@Add event to Modify selected member record
			@Override																
			public void mouseClicked(MouseEvent e) {
				String item = bookList.getSelectedItem().toString();
				int CopyCount = Integer.parseInt(copyCount.getText());
				int start = 1;
				int stop = item.indexOf(']');
				
				String temp = item.substring(start,stop);	
				addBookCopy(temp,CopyCount);
			}
		});
		btnAddCopy.setBounds(452, 64, 142, 25);
		mainWindowContentPanel1.add(btnAddCopy);
		
		
		JLabel lblHeader = new JLabel("Add Copy to Existing Book");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHeader.setBounds(179, 11, 263, 23);
		mainWindowContentPanel1.add(lblHeader);
		
		copyCount = new JTextField();
		copyCount.setBounds(367, 65, 75, 25);
		mainWindowContentPanel1.add(copyCount);
		copyCount.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Count of Copy");
		lblNewLabel_8.setBounds(367, 47, 97, 14);
		mainWindowContentPanel1.add(lblNewLabel_8);
		frame.getContentPane().add(mainWindowContentPanel1);
		
		
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
