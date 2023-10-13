package librarysystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.Author;
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade;
import librarysystem.MemberList.BackToMainListener;

public class BookList extends JFrame implements LibWindow{

	public static final BookList INSTANCE = new BookList();
	private JFrame frame;
	private JTable table;
	private DataAccessFacade db;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public void connectDB() {
		db = new DataAccessFacade();
	}
	public void generateBookList() {
		connectDB();															// Connect to Database
		HashMap<String, Book> bookList = db.readBooksMap();
		for(Entry<String, Book> item :bookList.entrySet()) {	
			List<Author> authorList = (item.getValue()).getAuthors();
			String thisAuthor ="";
			for (Author e:authorList) {
				thisAuthor = thisAuthor +e.getFirstName()+" "+e.getLastName()+"; ";
			}
			model.addRow(new Object[]{item.getValue().getIsbn(), item.getValue().getTitle(),thisAuthor,(item.getValue().getCopyNums()).size()});
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookList window = new BookList();
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
	public BookList() {
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
		
		JLabel lblNewLabel = new JLabel("Available Book List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(224, 2, 172, 23);
		frame.getContentPane().add(lblNewLabel);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setBounds(10, 36, 604, 292);
		
		// Create a couple of columns 
		model.addColumn("ISBN Number"); 
		model.addColumn("Book Title"); 
		model.addColumn("Author"); 
		model.addColumn("Copies"); 
		// Append a row 
		model.addRow(new Object[]{"ISBN Number", "Book Title","Author", "Copies"});
		
		generateBookList();
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		frame.getContentPane().add(table);
		
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
					BookList window = new BookList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
