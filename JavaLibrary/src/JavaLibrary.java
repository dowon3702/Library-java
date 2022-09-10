import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaLibrary {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaLibrary window = new JavaLibrary();
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
	public JavaLibrary() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/javalibrary", "root","");
					
		}catch(ClassNotFoundException ex) {
			
		}catch(SQLException ex) {
			
		}
	}
	
	public void table_load() {
		try {
			pst= con.prepareStatement("select * from book");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(550, 100, 700, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Store");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(214, 30, 216, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 100, 345, 213);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 42, 80, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 85, 61, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(10, 137, 61, 22);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(111, 46, 167, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(111, 88, 167, 19);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(111, 140, 167, 19);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			String bname,edition,price;
			
			bname = txtbname.getText();
			edition = txtedition.getText();
			price= txtprice.getText();
			
		
			
			
			try {
				pst = con.prepareStatement("insert into book(name,edition,price) value(?,?,?)");
				pst.setString(1, bname);
				pst.setString(2, edition);
				pst.setString(3, price);
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Successfully Added");
				table_load();
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
			}catch(SQLException el){
				el.printStackTrace();
			}
				
			}
			
		});
		btnNewButton.setBounds(10, 348, 111, 66);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(128, 348, 111, 66);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
			}
		});
		btnClear.setBounds(249, 348, 111, 66);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(376, 99, 300, 315);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 424, 350, 79);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 24, 61, 22);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id=txtbid.getText();
					pst=con.prepareStatement("select name,edition,price from book where id = ?");
					pst.setString(1, id);
					ResultSet rs=pst.executeQuery();
				if(rs.next()==true){
					String name=rs.getString(1);
					String edition =rs.getString(2);
					String price=rs.getString(3);
					
					txtbname.setText(name);
					txtedition.setText(edition);
					txtprice.setText(edition);
				     }else {
				    	 txtbname.setText("");
				    	 txtedition.setText("");
				    	 txtprice.setText("");
				     }
				}catch(SQLException ex) {
					 
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(76, 26, 167, 19);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price=txtprice.getText();
				bid=txtbid.getText();
				
				try {
					pst = con.prepareStatement("update book set name = ?, edition = ?, price = ? where id = ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Updated successfully");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				}catch(SQLException el){
					el.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(400, 437, 111, 66);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				
				bid=txtbid.getText();
				
				try {
					pst = con.prepareStatement("delete from book where id = ?");
					
					pst.setString(1, bid);
					
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				}catch(SQLException el){
					el.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setBounds(536, 437, 111, 66);
		frame.getContentPane().add(btnDelete);
	}
}
