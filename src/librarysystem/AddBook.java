package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class AddBook {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JList list;
	private JLabel lblNewLabel_4;

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
	public AddBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 420);
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
		
		textField = new JTextField();
		textField.setBounds(51, 58, 536, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(51, 100, 76, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setBounds(51, 117, 194, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Availability");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(277, 100, 89, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(277, 117, 127, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Copy Count");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(438, 100, 76, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(438, 117, 149, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		String week[]= { "Monday","Tuesday","Wednesday",
                "Thursday","Friday","Saturday","Sunday"};
		list = new JList(week) ;
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(51, 179, 536, 128);
		frame.getContentPane().add(list);
		

		
		lblNewLabel_4 = new JLabel("Author List");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(109, 157, 108, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(51, 148, 52, 28);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add This Book");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(460, 320, 127, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
	}
}
