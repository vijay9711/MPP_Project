package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.Author;
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import librarysystem.AddMember.BackToMainListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OverdueBookList extends JFrame implements LibWindow{

	public static final OverdueBookList INSTANCE = new OverdueBookList();
	private DataAccessFacade db;
	private JFrame frame;
	private JTable table;
	
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
	

	public void booWiseCheckOutRecord(String ISBN) {
		
		connectDB();

		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setBounds(10, 84, 725, 353);

		// Create a couple of columns 
		model.addColumn("ISBN"); 
		model.addColumn("Book Title"); 
		model.addColumn("Copy No."); 
		model.addColumn("Member"); 
		model.addColumn("Issued On"); 
		model.addColumn("Due Date");
		
		// Append a row 
		model.addRow(new Object[]{"ISBN", "Book Title","Copy No.", "Member Name", "Issue Date","Due Date"});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		//*********************************************************************//
		//Book bookToCopy = db.getBook(ISBN);// Connect to Database


//		HashMap<String, Book> bookList = db.readBooksMap();
//		for(Entry<String, Book> item :bookList.entrySet()) {	
//			List<Author> authorList = (item.getValue()).getAuthors();
//			String thisAuthor ="";
//			for (Author e:authorList) {t
//				thisAuthor = thisAuthor +e.getFirstName()+" "+e.getLastName()+"; ";
//			}
//			model.addRow(new Object[]{item.getValue().getIsbn(), item.getValue().getTitle(),thisAuthor,(item.getValue().getCopyNums()).size()});
//		}
		//*********************************************************************//
		frame.getContentPane().add(table);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OverdueBookList window = new OverdueBookList();
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
	public OverdueBookList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 150, 761, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// populating Combobox Member list
		JComboBox bookList = new JComboBox(generateComboBoxModel()); // Generating combo box items from generated model
		bookList.setBounds(10, 50, 541, 23);
		frame.getContentPane().add(bookList);
		// Populating Book 
		JButton btnCheckoutQuery = new JButton("Query");
		btnCheckoutQuery.addMouseListener(new MouseAdapter() {   				//@Add event to Modify selected member record
			@Override																
			public void mouseClicked(MouseEvent e) {
				//##########################################################//
				String item = bookList.getSelectedItem().toString();
				int start = 1;
				int stop = item.indexOf(']');
				String temp = item.substring(start,stop);	
				booWiseCheckOutRecord(temp);
			}

		});
		btnCheckoutQuery.setBounds(561, 49, 174, 25);
		frame.getContentPane().add(btnCheckoutQuery);

		// Button
		
		JLabel lblNewLabel = new JLabel("Overdue Book List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(265, 11, 182, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSelectBook = new JLabel("Select Book");
		lblSelectBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelectBook.setBounds(10, 27, 109, 23);
		frame.getContentPane().add(lblSelectBook);
		
		
		
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addActionListener(new BackToMainListener());
		backButton.setBounds(10, 439, 135, 22);
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
	public void init() {
		// TODO Auto-generated method stub
		
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
