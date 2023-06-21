import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpassword;
	Connection con;
	PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("FCMC - LogIn");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 278, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setBounds(19, 20, 92, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(24, 48, 92, 16);
		panel.add(lblNewLabel_1);
		
		txtusername = new JTextField();
		txtusername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtusername.setBounds(110, 15, 157, 26);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtpassword.setBounds(111, 43, 156, 26);
		panel.add(txtpassword);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 93, 278, 43);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnlogin = new JButton("LogIn");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginuser();
			}
		});
		btnlogin.setBounds(72, 6, 101, 29);
		panel_1.add(btnlogin);
		
		JButton btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnclose.setBounds(167, 6, 101, 29);
		panel_1.add(btnclose);
	}
	public void loginuser() {
	try 
	{	
	   String username = txtusername.getText();
	   String password = String.valueOf(txtpassword.getPassword());
	   if (username.isEmpty() || password.isEmpty()) {
	       JOptionPane.showMessageDialog(this,
	             "Please enter all fields",
	             "Try again",
	             JOptionPane.ERROR_MESSAGE);
	       return;
	    }
//	   Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost:3308/fcmc","root","");
       pst=con.prepareStatement("SELECT * FROM TBL_USER WHERE username = ? and pass_word = ?");
       pst.setString(1,username);
       pst.setString(2,password);
       ResultSet rs=pst.executeQuery();
       if(rs.next( )== false)
       {
    	   JOptionPane.showMessageDialog(this,
  	             "Couldn't LogIn, Invalid credentials",
  	             "Try again",
  	             JOptionPane.ERROR_MESSAGE);
  	       return;
       }
       else
       {
    	   new frmmain().setVisible(true);

           this.dispose();
       }
	}	
//	catch(ClassNotFoundException e)
//	{
//	  System.err.println(e);
//	}
	
	catch(SQLException e)
	{
	  System.err.println(e);
	}    
	}
}
