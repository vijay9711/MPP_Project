package librarysystem;

import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import business.Author;
import business.Book;
import dataaccess.DataAccessFacade;
import librarysystem.AddMember.BackToMainListener;


import librarysystem.AddMember.BackToMainListener;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class AddBook extends JFrame implements LibWindow {
	public static final AddBook INSTANCE = new AddBook();
	private DataAccessFacade db;
	private boolean isInitialized = false;
	
	private JFrame frame;
	private JTextField bookTitle;
	private JTextField bookIsbn;
	private JTextField bookAvailability;
	private JTextField bookCopyCount;
//	private JList bookAuthorList;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField textField;
//	private String authorList[] = {};
	private static JLabel authorList;
//	DefaultListModel<String> listModel = new DefaultListModel<>();
//	 JList<String> bookAuthorList = new JList<>(listModel);
	
	 public List<JTextComponent> fieldList;
	 
	 public List<Author> authList = new ArrayList<>();
	 
	public void connectDB() {
		db = new DataAccessFacade();
	}
	private JTextField bookMaxCheckoutDays;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook window = new AddBook();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 150, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddBook = new JLabel("Add a New Book");
		lblAddBook.setBounds(245, 11, 155, 14);
		lblAddBook.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddBook.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblAddBook);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(51, 40, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		bookTitle = new JTextField();
		bookTitle.setBounds(51, 58, 536, 20);
		bookTitle.setName("Book Title");
		frame.getContentPane().add(bookTitle);
		bookTitle.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(51, 100, 76, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		bookIsbn = new JTextField();
		bookIsbn.setText("");
		bookIsbn.setName("Book ISBN");
		bookIsbn.setBounds(51, 117, 146, 20);
		frame.getContentPane().add(bookIsbn);
		bookIsbn.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Availability");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(207, 100, 89, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		bookAvailability = new JTextField();
		bookAvailability.setName("Book Availability");
		bookAvailability.setBounds(207, 117, 113, 20);
		frame.getContentPane().add(bookAvailability);
		bookAvailability.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Copy Count");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(330, 100, 76, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		bookCopyCount = new JTextField();
		bookCopyCount.setName("Book copy count");
		
		bookCopyCount.setBounds(330, 117, 113, 20);
		bookCopyCount.setText("Book Copy Count");
		frame.getContentPane().add(bookCopyCount);
		bookCopyCount.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Author List");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(109, 157, 108, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		authorList = new JLabel();
		authorList.setName("Authors");
		authorList.setBounds(53, 188, 500, 80);
		frame.getContentPane().add(authorList);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAuthorToDB();
			}
		});
		btnNewButton.setBounds(51, 148, 52, 28);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnAddBook = new JButton("Add This Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookHandler();
			}
		});
		btnAddBook.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddBook.setBounds(460, 320, 127, 23);
		frame.getContentPane().add(btnAddBook);
		
		lblNewLabel_5 = new JLabel("Max. Checkout Days");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(453, 100, 127, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		bookMaxCheckoutDays = new JTextField();
		bookMaxCheckoutDays.setText("Maximum Checkout Days");
		bookMaxCheckoutDays.setBounds(453, 117, 134, 20);
		frame.getContentPane().add(bookMaxCheckoutDays);
		bookMaxCheckoutDays.setColumns(10);
		
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addActionListener(new BackToMainListener());
		backButton.setBounds(50, 320, 135, 23);
		frame.add(backButton);
		fieldList = Arrays.asList(bookTitle,bookIsbn,bookAvailability,bookCopyCount,textField);
	}
	public AddBook() {
		initialize();
	}


	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook window = new AddBook();
					window.frame.setVisible(true);
					connectDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void addAuthorToList(ActionEvent e, Author author) {
		System.out.println("auth mname "+author.getFirstName());
		String authName = authorList.getText();
		authName = authName.trim() == "" ? author.getFirstName().trim() : authName.trim() + ", "+ author.getFirstName() ;
		AddBook.INSTANCE.authList.add(author);
		authorList.setText(authName);
		AddBook.INSTANCE.frame.repaint();
		System.out.println(authorList.getText() + " author list "+AddBook.INSTANCE.authList.toString());
	}
	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			toggleAddBookFrame(false);
			adminWindow.INSTANCE.toggleAdminFrame(true);
    		pack();
		}
	}
	
	public void AddBookHandler() {
		if(validateBookForm() && AddBook.INSTANCE.authList.size() > 0) {
			System.out.println("author list "+AddBook.INSTANCE.authList.toString());
			Book newBook = new Book(bookIsbn.getText(), bookTitle.getText(), Integer.parseInt(textField.getText()), authList, Integer.parseInt(bookCopyCount.getText()));
			StringBuilder sb = new StringBuilder();
			sb.append("New book added successfully! \n");
			sb.append("Book name: " + newBook.getTitle() + "\n");
			sb.append("ISBN: " + newBook.getIsbn());
			sb.append("No of copies: " + newBook.getNumCopies() + "\n");
			
			toggleAddBookFrame(false);
		}
	}	
	
//	add author
	public void addAuthorToDB() {
		AddAuthor.INSTANCE.init();
		AddAuthor.INSTANCE.pack();
		Util.centerFrameOnDesktop(AddAuthor.INSTANCE);
		AddAuthor.INSTANCE.setVisible(true);
		pack();
	}
	
	public void toggleAddBookFrame(boolean val) {
		AddMember.INSTANCE.setVisible(val);
		frame.setVisible(val);
	}
	public boolean validateBookForm() {
		StringBuilder st = new StringBuilder();
		fieldList.forEach((item)->{
			if(item.getText().trim().length() == 0) {
				st.append(item.getName() + "\n");
				
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
