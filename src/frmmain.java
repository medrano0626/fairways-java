import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;

public class frmmain extends JFrame {

	private JPanel contentPane;
	JLabel lblcompany = new JLabel();
	JLabel lbluser = new JLabel();
	JLabel lblrole = new JLabel();
	JPanel panel_masterfile_1 = new JPanel();
	JPanel panel_masterfile_2 = new JPanel();
	JPanel panel_masterfile = new JPanel();
	String company1;
	private JPanel panel_1;
	private JLabel lblRole;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JTable table;
	JTable table_masterfile = new JTable();
	private JScrollPane scrollPane;
	JTextField txt_masterfile_search = new JTextField();
	Connection con;
	PreparedStatement pst;
	private JTextField txt_masterfile_lname;
	private JTextField txt_masterfile_fname;
	private JTextField txt_masterfile_mname;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					frmmain frame = new frmmain();
					
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmmain(String username, String company, String role) {
		
		setTitle("FCMC - Payroll System V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(6, 6, 882, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Company:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setBounds(18, 6, 99, 26);
		panel.add(lblNewLabel);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblUser.setBounds(302, 6, 57, 26);
		panel.add(lblUser);
		
//		lblcompany = new JLabel("Company:");
		lblcompany.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblcompany.setBounds(129, 6, 161, 26);
		panel.add(lblcompany);
		
		lbluser = new JLabel("User:");
		lbluser.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lbluser.setBounds(366, 6, 178, 26);
		panel.add(lbluser);
		
		lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblRole.setBounds(567, 6, 57, 26);
		panel.add(lblRole);
		
		lblrole = new JLabel("Role:");
		lblrole.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblrole.setBounds(636, 6, 178, 26);
		panel.add(lblrole);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(6, 57, 882, 441);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
//		Main_Tabbed_Pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(6, 6, 870, 429);
		panel_1.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("File", null, panel_2, null);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Transactions", null, panel_3, null);
		panel_3.setLayout(null);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Reports", null, panel_4, null);
		panel_4.setLayout(null);
		
		panel_5 = new JPanel();
		tabbedPane.addTab("User Account", null, panel_5, null);
		panel_5.setLayout(null);
//		End of Main_Tabbed_Pane
		panel_6 = new JPanel();
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBounds(6, 6, 99, 371);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
//		Masterfile_Tab components
		JButton btn_masterfile = new JButton("Masterfile");
		btn_masterfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_masterfile.setVisible(true);
			}
		});
		btn_masterfile.setBounds(0, 6, 93, 29);
		panel_6.add(btn_masterfile);
		
		JButton btn_sss = new JButton("SSS");
		btn_sss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_masterfile.setVisible(false);
				
			}
		});
		btn_sss.setBounds(0, 35, 93, 29);
		panel_6.add(btn_sss);
		
		
		panel_masterfile.setBounds(106, 6, 737, 371);
		panel_2.add(panel_masterfile);
		panel_masterfile.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Masterfile");
		lblNewLabel_1.setBounds(6, 6, 84, 16);
		panel_masterfile.add(lblNewLabel_1);
//		table_masterfile
		Object[] columns = {"EmpNo", "LastName", "FirstName", "MiddleName", "Position", "Location"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
						
						
						panel_masterfile_2.setBounds(6, 34, 725, 331);
						panel_masterfile_2.setVisible(false);
						panel_masterfile.add(panel_masterfile_2);
						panel_masterfile_2.setLayout(null);
						
						JButton btn_masterfile_close = new JButton("Close");
						btn_masterfile_close.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								clear_masterfile_textfield();
								panel_masterfile_2.setVisible(false);
								panel_masterfile_1.setVisible(true);
							}
						});
						btn_masterfile_close.setBounds(602, 296, 117, 29);
						panel_masterfile_2.add(btn_masterfile_close);
						
						JButton btn_masterfile_save = new JButton("Save");
						btn_masterfile_save.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btn_masterfile_save.setBounds(492, 296, 117, 29);
						panel_masterfile_2.add(btn_masterfile_save);
						
						JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
						tabbedPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
						tabbedPane_1.setBounds(6, 6, 713, 292);
						panel_masterfile_2.add(tabbedPane_1);
						
						JPanel panel_8 = new JPanel();
						tabbedPane_1.addTab("Basic Info", null, panel_8, null);
						panel_8.setLayout(null);
						
						JLabel lblNewLabel_3 = new JLabel("Last Name:");
						lblNewLabel_3.setBounds(6, 16, 78, 16);
						panel_8.add(lblNewLabel_3);
						
						txt_masterfile_lname = new JTextField();
						txt_masterfile_lname.setBounds(88, 11, 130, 26);
						panel_8.add(txt_masterfile_lname);
						txt_masterfile_lname.setColumns(10);
						
						JLabel lblNewLabel_3_1 = new JLabel("First Name:");
						lblNewLabel_3_1.setBounds(225, 16, 78, 16);
						panel_8.add(lblNewLabel_3_1);
						
						txt_masterfile_fname = new JTextField();
						txt_masterfile_fname.setColumns(10);
						txt_masterfile_fname.setBounds(307, 11, 130, 26);
						panel_8.add(txt_masterfile_fname);
						
						JLabel lblNewLabel_3_2 = new JLabel("Middle Name:");
						lblNewLabel_3_2.setBounds(449, 16, 92, 16);
						panel_8.add(lblNewLabel_3_2);
						
						txt_masterfile_mname = new JTextField();
						txt_masterfile_mname.setColumns(10);
						txt_masterfile_mname.setBounds(545, 11, 130, 26);
						panel_8.add(txt_masterfile_mname);
						
						JLabel lblNewLabel_3_3 = new JLabel("Birth Date:");
						lblNewLabel_3_3.setBounds(10, 60, 78, 16);
						panel_8.add(lblNewLabel_3_3);
						
						JDateChooser cal_birthdate = new JDateChooser();
						cal_birthdate.getJCalendar().setPreferredSize(new Dimension(300, 200));
						cal_birthdate.setDateFormatString("MM/dd/yyyy");
						cal_birthdate.setBounds(88, 60, 130, 26);
						panel_8.add(cal_birthdate);
						
						JPanel panel_7 = new JPanel();
						tabbedPane_1.addTab("ID's", null, panel_7, null);
						panel_7.setLayout(null);
						
						
						panel_masterfile_1.setBounds(6, 34, 725, 331);
						panel_masterfile.add(panel_masterfile_1);
						panel_masterfile_1.setLayout(null);
						
						JButton btn_masterfile_delete = new JButton("Delete");
						btn_masterfile_delete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected record?", "Confirm Delete",JOptionPane.YES_NO_OPTION);
								if(result == JOptionPane.YES_OPTION) {
									try {
										int row = table_masterfile.getSelectedRow();
										DefaultTableModel model = (DefaultTableModel) table_masterfile.getModel();
										String empno = model.getValueAt(row, 0).toString();
										pst = con.prepareStatement("DELETE FROM TBL_MASTERFILE WHERE empno = "+ empno);
									    pst.executeUpdate();
									    search_masterfile();
									}
									catch(SQLException f)
									{
									  System.err.println(f);
									}
									
								}
								
							}
						});
						btn_masterfile_delete.setBounds(602, 296, 117, 29);
						panel_masterfile_1.add(btn_masterfile_delete);
						
						JButton btn_masterfile_add = new JButton("Add");
						btn_masterfile_add.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								panel_masterfile_2.setVisible(true);
								panel_masterfile_1.setVisible(false);
								
							}
						});
						
								btn_masterfile_add.setBounds(493, 296, 117, 29);
								panel_masterfile_1.add(btn_masterfile_add);
								
								
								
								JLabel lblNewLabel_2 = new JLabel("Search:");
								lblNewLabel_2.setBounds(6, 9, 61, 16);
								panel_masterfile_1.add(lblNewLabel_2);
								
								txt_masterfile_search = new JTextField();
								txt_masterfile_search.addKeyListener(new KeyAdapter() {
									@Override
									public void keyReleased(KeyEvent e) {
										search_masterfile();	
									}
								});
								txt_masterfile_search.setBounds(66, 4, 130, 26);
								panel_masterfile_1.add(txt_masterfile_search);
								txt_masterfile_search.setColumns(10);
								scrollPane = new JScrollPane();
								
								scrollPane.setBounds(6, 37, 713, 258);
								panel_masterfile_1.add(scrollPane);
								
								
								table_masterfile = new JTable();
								table_masterfile.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										boolean a = table_masterfile.isEditing();
										if(a == false) {
//									JOptionPane.showMessageDialog(null, "Not");
											if (e.getClickCount() == 2 && !e.isConsumed()) {
					                    e.consume();
					                    int row = table_masterfile.getSelectedRow();
												DefaultTableModel model = (DefaultTableModel) table_masterfile.getModel();
												String empno = model.getValueAt(row, 0).toString();
					                    System.out.println(empno);
											}
										}
									}
									
								});
								scrollPane.setViewportView(table_masterfile);
								table_masterfile.setModel(model);
//		end of table_masterfile
		lblcompany.setText(company);
		lbluser.setText(username);
		lblrole.setText(role);
		firstload();
	}


	public void firstload() {
		try 
		{	
		   String username = lbluser.getText();
		   String role = lblrole.getText();;	
		   String company = lblcompany.getText();
	       con = DriverManager.getConnection("jdbc:mysql://localhost:3308/fcmc","root","");
	       pst = con.prepareStatement("SELECT * FROM TBL_MASTERFILE WHERE status_ = 'ACTIVE' AND company = '"+company+"' ORDER BY lname");
	       ResultSet rs = pst.executeQuery();
	       Object[] columns = {"EmpNo", "LastName", "FirstName", "MiddleName", "Position", "Location"};
	       DefaultTableModel model = (DefaultTableModel) table_masterfile.getModel();
	       model.setColumnIdentifiers(columns);
	       String empno, lname, fname, mname, position, location;
	       while (rs.next()) {
	    	   empno = rs.getString("empno");
	    	   lname = rs.getString("lname");
	    	   fname = rs.getString("fname");
	    	   mname = rs.getString("mname");
	    	   position = rs.getString("position");
	    	   location = rs.getString("location_");
	    	   String row[] = {empno, lname, fname, mname, position, location};
	    	   model.addRow(row);
	       }
	       pst.close();
		}	
		catch(SQLException e)
		{
		  System.err.println(e);
		}
	}
	public void search_masterfile() {
		try 
		{	
		   String company = lblcompany.getText();
		   String searchstring = txt_masterfile_search.getText();
			   pst = con.prepareStatement("SELECT * FROM TBL_MASTERFILE WHERE status_ = 'ACTIVE' AND company = '"+company+"' "
	       		+ "AND (lname like '%"+searchstring+"%' OR fname like '%"+searchstring+"%' OR mname like '%"+searchstring+"%'  "
	       				+ "OR position like '%"+searchstring+"%' OR location_ like '%"+searchstring+"%'  OR username like '%"+searchstring+"%') ORDER BY lname");
	       ResultSet rs = pst.executeQuery();
	       Object[] columns = {"EmpNo", "LastName", "FirstName", "MiddleName", "Position", "Location"};
	       DefaultTableModel model = (DefaultTableModel) table_masterfile.getModel();
	       model.setColumnIdentifiers(columns);
	       String empno, lname, fname, mname, position, location;
	       model.setRowCount(0);
	       while (rs.next()) {
	    	   empno = rs.getString("empno");
	    	   lname = rs.getString("lname");
	    	   fname = rs.getString("fname");
	    	   mname = rs.getString("mname");
	    	   position = rs.getString("position");
	    	   location = rs.getString("location_");
	    	   String row[] = {empno, lname, fname, mname, position, location};
	    	   model.addRow(row);
	       }
	       pst.close();
		}	
		catch(SQLException f)
		{
		  System.err.println(f);
		}
	}
	public void clear_masterfile_textfield() {
		txt_masterfile_lname.setText("");
		txt_masterfile_fname.setText("");
		txt_masterfile_mname.setText("");
	}
}
