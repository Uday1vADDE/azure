package desk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Demo {

	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo window = new Demo();
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
	public Demo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		t1 = new JTextField();
		t1.setBounds(39, 106, 260, 19);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(39, 184, 260, 19);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String n=t1.getText();
			String m=t2.getText();
			try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3309/MREC","root","Uday@123");
			String qurey="insert into student values('"+n+"','"+m+"')";
			Statement st=con.createStatement();
			st.executeUpdate(qurey);
			con.close();
			}
			catch(Exception exception){
				
			}
			//JOptionPane.showMessageDialog((btnNewButton, "Successfully updated");
			JOptionPane.showMessageDialog(btnNewButton, "Successfully updated");
			
			}
		});
		btnNewButton.setBounds(113, 258, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(432, 108, 147, 98);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnNewButton_1 = new JButton("DISPLAY");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
//			try {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3309/MREC","root","Uday@123");
					String q2="select * from student";
					Statement st2=con1.createStatement();
					ResultSet rs=st2.executeQuery(q2);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<cols;i++) 
				    colName[i]=rsmd.getColumnName(i+1); 
				
					  model.setColumnIdentifiers(colName);
					  String name,marks;
					  while(rs.next()) {
						  name=rs.getString(1);
						  marks =rs.getString(2);
						  String[] row= {name,marks};
						  model.addRow(row);
							  
					  
				  }
				}
				  
				  
				  
				  catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			//}
//			catch(ClassNOtFoundException e1) {
//				e1.printStackTrace();
//			}
				
			
			
			
			}
		});
		btnNewButton_1.setBounds(442, 288, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("CLEAR DATA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_2.setBounds(442, 355, 109, 21);
		frame.getContentPane().add(btnNewButton_2);
	}
}
