package librarysystem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JFrame;

import librarysystem.EditMember.BackToMainListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import business.LibraryMember;
import dataaccess.DataAccessFacade;

import java.awt.Font;
import javax.swing.JTable;

public class MemberList  extends JFrame implements LibWindow {

	public static final MemberList INSTANCE = new MemberList();
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
	public void generateMemberList() {
		connectDB();															// Connect to Database
		HashMap<String, LibraryMember> membermap = db.readMemberMap();			// Reading from Hash Map
		for(Entry<String, LibraryMember> item:membermap.entrySet()) {			
			model.addRow(new Object[]{item.getValue().getMemberId(), item.getValue().getFirstName()+" "+item.getValue().getLastName(),item.getValue().getAddress().toString(), item.getValue().getTelephone()});
		}
	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MemberList window = new MemberList();
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
	public MemberList() {
		initialize();
	}
	
	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberList window = new MemberList();
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
		frame.getContentPane().setLayout(null);
		
		
		
		// back button to move back to main menu
				JButton backButton = new JButton("Back to Main");
				backButton.addActionListener(new BackToMainListener());
				backButton.setBounds(21, 347, 135, 23);
				frame.getContentPane().add(backButton);
				
				JLabel lblNewLabel = new JLabel("Library Member List");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(224, 2, 172, 23);
				frame.getContentPane().add(lblNewLabel);
				
				
				model = new DefaultTableModel();
				table = new JTable(model);
				table.setBounds(10, 36, 604, 292);
				
				// Create a couple of columns 
				model.addColumn("Member ID"); 
				model.addColumn("Member Name"); 
				model.addColumn("Address"); 
				model.addColumn("phone Number"); 
				// Append a row 
				model.addRow(new Object[]{"Member ID", "Member Name","Address", "Phone Number"});
				
				generateMemberList();
				table.getColumnModel().getColumn(0).setPreferredWidth(30);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				frame.getContentPane().add(table);
				
		// back button to move back to main menu
		
		
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
