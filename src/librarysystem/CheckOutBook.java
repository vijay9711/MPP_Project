package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.security.KeyStore.Entry;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.BookCopy;
import business.LibraryMember;
import business.checkoutRecord;
import dataaccess.DataAccessFacade;
import librarysystem.AddMember.BackToMainListener;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class CheckOutBook  extends JFrame implements LibWindow{
	public static final CheckOutBook INSTANCE = new CheckOutBook();
	private JFrame frame;
	private JButton btnModifyMemberInitial;
	private JTextField checkoutISBN;
	private JTextField checkoutMemberID;
	private JTable table;
	private JLabel bookCopy;
	private boolean isInitialized = false;
	private DefaultTableModel model;
	private business.Book b;
	private LibraryMember member;
	private BookCopy bookC;

	private DataAccessFacade db;
	public DefaultComboBoxModel generateComboBoxModel() {
		connectDB();															// Connect to Database
		HashMap<String, LibraryMember> membermap = db.readMemberMap();			// Reading from Hash Map
		Vector comboBoxItems=new Vector();										// Creating a vector
		for(java.util.Map.Entry<String, LibraryMember> item:membermap.entrySet()) {			
			//System.out.println(item.getValue().getMemberId()+ " | "+item.getValue().getFirstName()+" "+item.getValue().getLastName());
			comboBoxItems.add("["+item.getValue().getMemberId()+"] | "+item.getValue().getFirstName()+" "+item.getValue().getLastName());
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
		return model;
	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CheckOutBook window = new CheckOutBook();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public void generateCheckoutRecord() {										// Returns selected Library Member
		
		connectDB();
//		btnModifyMemberInitial.setVisible(false);
		model = new DefaultTableModel();
		table = new JTable(model);
		
		table.setBounds(20, 136, 700, 270);

		// Create a couple of columns 
		model.addColumn("ISBN"); 
		model.addColumn("Book Title"); 
		model.addColumn("Copy No."); 
		model.addColumn("MemberName"); 
		model.addColumn("Issued on "); 
		model.addColumn("Due Date"); 
		// Append a row 
		model.addRow(new Object[]{"ISBN", "Book Title","Copy No.", "Member Name", "Issue Date","Due Date"});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		ArrayList<checkoutRecord> record = (ArrayList<checkoutRecord>) db.getAllCheckout();
		System.out.println(record);
		record.forEach(item -> {
			BookCopy bookcopy = item.getBookCopy();
			business.Book book = item.getBookCopy().getBook();
			LibraryMember member = item.getMember();
			model.addRow(new Object[] {book.getIsbn(), book.getTitle(), bookcopy.getCopyNum(), member.getFirstName(), item.getCheckoutDate().toString(), item.getDueDate().toString()});
		});
		pack();
		frame.getContentPane().add(table);
		
	}
	
	public CheckOutBook() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 150, 740, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Checkout a Book");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(272, 11, 133, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN Number");
		lblNewLabel_1.setBounds(74, 53, 117, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		checkoutISBN = new JTextField();
		checkoutISBN.setBounds(74, 71, 230, 20);
		frame.getContentPane().add(checkoutISBN);
		checkoutISBN.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("MemberID");
		lblNewLabel_2.setBounds(314, 53, 104, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		checkoutMemberID = new JTextField();
		checkoutMemberID.setBounds(314, 71, 170, 20);
		frame.getContentPane().add(checkoutMemberID);
		checkoutMemberID.setColumns(10);
		
		JButton btnCheckOut = new JButton("Checkout");
		btnCheckOut.setBounds(494, 70, 120, 23);
		btnCheckOut.addActionListener(new checkoutStatus());
		frame.getContentPane().add(btnCheckOut);
		
		JLabel lblCheckOutMsg = new JLabel("Checkout Status Message");
		lblCheckOutMsg.setBounds(24, 98, 410, 23);
		frame.getContentPane().add(lblCheckOutMsg);
		
		bookCopy = new JLabel("Checkout a Book");
		bookCopy.setHorizontalAlignment(SwingConstants.CENTER);
		bookCopy.setFont(new Font("Tahoma", Font.BOLD, 14));
		bookCopy.setBounds(104, 98, 410, 23);
		frame.getContentPane().add(lblNewLabel);
		// back button to move back to main menu
		JButton backButton = new JButton("Back to Main");
		backButton.addActionListener(new BackToMainListener());
		backButton.setBounds(74, 430, 135, 23);
		generateCheckoutRecord();
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
	public void connectDB() {
		db = new DataAccessFacade();
	}
	class checkoutStatus implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			connectDB();
			b = db.getBook(checkoutISBN.getText());
			member = db.getMember(checkoutMemberID.getText());
			StringBuilder sb = new StringBuilder();
			if(b == null) {
				sb.append("The Book ISBN id("+checkoutISBN.getText()+") is not found in DataBase." +"\n");
			}
			if(member == null) {
				sb.append("The Member id("+checkoutMemberID.getText()+") is not found in DataBase." +"\n");
			}
			if(b == null | member == null) {
				sb.append("Please try again!");
				JOptionPane.showMessageDialog(CheckOutBook.INSTANCE,sb.toString());
			}
			if(b.isAvailable()) {
				LocalDateTime checkoutDate = LocalDateTime.now(); 
				LocalDateTime dueDate = checkoutDate;
				
				dueDate = dueDate.plusDays(b.getMaxCheckoutLength());
//				checkoutDate = checkoutDate.minusDays(7);
//				dueDate = dueDate.minusDays(1);
				bookC = b.getNextAvailableCopy();
				bookC.setIsAvailable();
				checkoutRecord record = new checkoutRecord(bookC, member, dueDate, checkoutDate);
				b.updateCopies(bookC);
				member.setCheckoutRecord(record);
				db.saveNewMember(member);
				db.addNewBook(b);
				StringBuilder sb1 = new StringBuilder();
				sb1.append("Book checkout successfully!\n");
				sb1.append("Available copy:" + b.getCopyNums().toString());
				JOptionPane.showMessageDialog(CheckOutBook.INSTANCE,sb1.toString());
				if(b != null) {
					model.addRow(new Object[] {b.getIsbn(), b.getTitle(), bookC.getCopyNum(), member.getFirstName(),checkoutDate.toString(), dueDate.toString() });
					pack();
					frame.getContentPane().add(table);
				}
			}else {
				JOptionPane.showMessageDialog(CheckOutBook.INSTANCE,"No copy available for this book.");
			}
		}
		
	}
	public void toggleAddMemeberFrame(boolean val) {
		CheckOutBook.INSTANCE.setVisible(val);
		frame.setVisible(val);
	}

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOutBook window = new CheckOutBook();
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
}
