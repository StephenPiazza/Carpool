import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CreateMemberGUI extends JFrame{
/**
	 * 
	 */
	
private static final long serialVersionUID = 1L;

Connection connection=null;
PreparedStatement preparedstatement = null;
ResultSet resultset = null;
Statement statement = null;
private String userName = "root";
private String passWord = "006462001";
private JPanel p1;
private JPanel p2;
private JPanel p3;
private JPanel p4;
private JPanel p5;
private JPanel p6;
private JPanel p7;
private JPanel p8;
private JPanel p9;
private JPanel p10;
private JPanel p11;
private JPanel p12;
private JPanel p13;
private JPanel p14;
public static int count;
private JComboBox<String> jcom;
private JLabel l1;
private JLabel l2;
private JLabel l3;
private JLabel l4;
private JLabel l5;
private JLabel l6;
private JLabel l7;
private JLabel l8;
private JLabel l9;
private JTextField textLast;
private JTextField textFirst;
private JTextField textPhone;
private String id;
private JTextField textEmail;
private JTextField textConformEmail;
public static String name;
public static String myid;
public static String email;
private String vehicles;
private JPasswordField pw1;
private JPasswordField pw2;
private JLabel s1;
private JLabel s2;
private JLabel s3;
private JLabel s4;
private JLabel s5;
private JLabel s6;
private JLabel s7;
private JLabel s8;
private JButton buttonCreate;
private JButton buttonCancel;
Font font;
Font font1;
public CreateMemberGUI()
{
font = new Font("Arial",Font.PLAIN,22);
jcom = new JComboBox<String>();
jcom.addItem("");
jcom.addItem("Yes");
jcom.addItem("No");
jcom.setFont(font);
p1 = new JPanel();	
p2 = new JPanel();
p3 = new JPanel();
p4 = new JPanel();
p5 = new JPanel();
p6 = new JPanel();
p7 = new JPanel();
p8 = new JPanel();
p9 = new JPanel();
p10 = new JPanel();
p11 = new JPanel();
p12 = new JPanel();
p13 = new JPanel();
p14 = new JPanel();
p1.setLayout(null);
p2.setLayout(null);
p3.setLayout(null);
p4.setLayout(null);
p5.setLayout(null);
p6.setLayout(null);
p7.setLayout(null);
p8.setLayout(null);
p9.setLayout(null);
p10.setLayout(null);
p11.setLayout(null);
p12.setLayout(null);
p13.setLayout(null);
p14.setLayout(null);
buttonCreate = new JButton("Create");
buttonCreate.setFont(font);
buttonCreate.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
String passConvert1 = new String(pw1.getPassword());
String passConvert2 = new String(pw2.getPassword());
if(!(textEmail.getText().equals(textConformEmail.getText()))|| !(passConvert1.equals(passConvert2)))
{
JOptionPane.showMessageDialog(new JFrame(), "Either Email or Password does not match","Alert",JOptionPane.ERROR_MESSAGE);	
return;
}	
else{	
dispose();	
try {
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
	vehicles = (String) jcom.getSelectedItem();
	String passConvert = new String(pw1.getPassword());
	String phone = textPhone.getText();
	id = textFirst.getText().substring(0, 2)+ textLast.getText().substring(0, 2)+ count;
	if(vehicles.equalsIgnoreCase("Yes")){
	
	/** Member m1 = new Driver();
	 * Member m2 = new Rider() 
	 * Bridge Pattern is generated here based on shared vehicles reference
	 * 
	 * **/	
		
	new MySQL().addNewMember(new Driver(textLast.getText(),textFirst.getText(),vehicles,textEmail.getText(),passConvert,phone,id));
	count++;
	JOptionPane.showMessageDialog(new JFrame(), "your account is sucessfully created!","Notice",JOptionPane.INFORMATION_MESSAGE);	
	}
	if(vehicles.equalsIgnoreCase("No")){
		new MySQL().addNewMember(new Rider(textLast.getText(),textFirst.getText(),vehicles,textEmail.getText(),passConvert,phone,id));
		count++;
		JOptionPane.showMessageDialog(new JFrame(), "your account is sucessfully created!","Notice",JOptionPane.INFORMATION_MESSAGE);	
		}
		String querry = "select FirstName, id, email from member where Phone ='"  + phone + "'" + "";
		preparedstatement = connection.prepareStatement(querry);
		resultset = preparedstatement.executeQuery();
		while(resultset.next()){
		name = resultset.getString("FirstName");
		myid = resultset.getString("id");
		email = resultset.getString("email");	
		}
		MySQL mysql = new MySQL();	
	    mysql.NewMemberId();
} catch (SQLException e1) {
	e1.printStackTrace();
} catch (ClassNotFoundException e1) {
	e1.printStackTrace();
}finally {
	close();	
	}
new MainControllerGUI().setVisible(true);	
}
}
});
buttonCancel = new JButton("Cancel");
buttonCancel.setFont(font);
buttonCancel.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
dispose();
new MainControllerGUI().setVisible(true);
}	
});
l1 = new JLabel("Last Name");
l1.setFont(font);
l2 = new JLabel("First Name");
l2.setFont(font);
l3 = new JLabel("Vehicles/Shared Vehicles");
l3.setFont(font);
l4 = new JLabel("Phone number");
l4.setFont(font);
l5 = new JLabel("Email address");
l5.setFont(font);
l6 = new JLabel("Re-type email");
l6.setFont(font);
l7 = new JLabel("Password");
l7.setFont(font);
l8 = new JLabel("Re-type password");
l8.setFont(font);
l9 = new JLabel("(*) Requires");
l9.setFont(font);
l9.setForeground(Color.RED);
l6.setFont(font);
textLast = new JTextField();
textLast.setFont(font);
textFirst = new JTextField();
textFirst.setFont(font);
textPhone = new JTextField("0123456789");
textPhone.setForeground(new Color(189,189,189));
textPhone.setFont(font);
textPhone.addMouseListener(new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
	textPhone.setText("");
	textPhone.setForeground(new Color(20,20,20));	
	}	
});
textEmail = new JTextField();
textEmail.setFont(font);
textConformEmail = new JTextField();
textConformEmail.setFont(font);
pw1 = new JPasswordField("");
pw1.setFont(new Font("Arial",Font.BOLD,30));
pw2 = new JPasswordField("");
pw2.setFont(new Font("Arial",Font.BOLD,30));
pw2.setBackground(new Color(225,225,225));
s1 = new JLabel ("(*)");
s1.setForeground(Color.RED);
s1.setFont(font);
s2 = new JLabel ("(*)");
s2.setForeground(Color.RED);
s2.setFont(font);
s3 = new JLabel ("(*)");
s3.setForeground(Color.RED);
s3.setFont(font);
s4 = new JLabel ("(*)");
s4.setForeground(Color.RED);
s4.setFont(font);
s5 = new JLabel ("(*)");
s5.setForeground(Color.RED);
s5.setFont(font);
s6 = new JLabel ("(*)");
s6.setForeground(Color.RED);
s6.setFont(font);
s7 = new JLabel ("(*)");
s7.setForeground(Color.RED);
s7.setFont(font);
s8 = new JLabel ("(*)");
s8.setForeground(Color.RED);
s8.setFont(font);
p1.add(l1);
p1.add(l2);
p2.add(textLast);
p2.add(s1);
p2.add(textFirst);
p2.add(s2);
p3.add(l3);
p3.add(l4);
p4.add(jcom);
p4.add(s3);
p4.add(textPhone);
p4.add(s4);
p5.add(l5);
p5.add(l6);
p6.add(textEmail);
p6.add(s5);
p6.add(textConformEmail);
p6.add(s6);
p7.add(l7);
p7.add(l8);
p8.add(pw1);
p8.add(s7);
p8.add(pw2);
p8.add(s8);
p9.add(l9);
p11.add(buttonCreate);
p11.add(buttonCancel);

//** The Absolute positioning of JLabel and JTextField **//

Insets insets = this.getInsets();
Dimension size = l1.getPreferredSize();
l1.setBounds(35 + insets.left, 15 + insets.top, size.width, size.height);
size = textLast.getPreferredSize();
textLast.setBounds(35 + insets.left, 10 + insets.top, 350, 35);
size = s1.getPreferredSize();
s1.setBounds(400 + insets.left,5 + insets.top, 50, 35);
size = l2.getPreferredSize();
l2.setBounds(500 + insets.left, 15 + insets.top, size.width, size.height);
size = textFirst.getPreferredSize();
textFirst.setBounds(500 + insets.left, 10 + insets.top, 350, 35);
size = s2.getPreferredSize();
s2.setBounds(860 + insets.left,5 + insets.top, 50, 35);
size = l3.getPreferredSize();
l3.setBounds(35 + insets.left, 10 + insets.top, size.width, size.height);
size = jcom.getPreferredSize();
jcom.setBounds(35 + insets.left, 10 + insets.top, 350, 35);
size = s3.getPreferredSize();
s3.setBounds(400 + insets.left,10 + insets.top, 200, 35);
size = l4.getPreferredSize();
l4.setBounds(500 + insets.left, 15 + insets.top, size.width, size.height);
size = textPhone.getPreferredSize();
textPhone.setBounds(500 + insets.left, 10 + insets.top, 350, 35);
size = s4.getPreferredSize();
s4.setBounds(860 + insets.left,10 + insets.top, 350, 35);
size = l5.getPreferredSize();
l5.setBounds(35 + insets.left, 10 + insets.top, size.width, size.height);
size = l6.getPreferredSize();
l6.setBounds(500 + insets.left, 10 + insets.top,size.width, size.height);
size = textEmail.getPreferredSize();
textEmail.setBounds(35 + insets.left,10 + insets.top, 350, 35);
size = s5.getPreferredSize();
s5.setBounds(400 + insets.left,10 + insets.top, 50, 35);
size = textConformEmail.getPreferredSize();
textConformEmail.setBounds(500 + insets.left,10 + insets.top, 350, 35);
size = s6.getPreferredSize();
s6.setBounds(860 + insets.left,10 + insets.top, 350, 35);
size = l7.getPreferredSize();
l7.setBounds(35 + insets.left, 10 + insets.top, size.width, size.height);
size = l8.getPreferredSize();
l8.setBounds(500 + insets.left, 10 + insets.top,size.width, size.height);
size = pw1.getPreferredSize();
pw1.setBounds(35 + insets.left, 10 + insets.top, 350, 35);
size = s7.getPreferredSize();
s7.setBounds(400 + insets.left,10 + insets.top, 50, 35);
size = pw2.getPreferredSize();
pw2.setBounds(500 + insets.left, 10 + insets.top, 350, 35);
size = s8.getPreferredSize();
s8.setBounds(860 + insets.left,10 + insets.top, 350, 35);
size = l9.getPreferredSize();
l9.setBounds(35 + insets.left, 15 + insets.top, size.width, size.height);
size = buttonCreate.getPreferredSize();
buttonCreate.setBounds(330 + insets.left, insets.top, 140, 53);
size = buttonCancel.getPreferredSize();
buttonCancel.setBounds(470 + insets.left, insets.top, 140, 53);
setTitle("New Member");
ImageIcon imageicon = new ImageIcon("D:/Spring 2016/Res_management/carpool.png");
setIconImage(imageicon.getImage());
setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
p11.setBorder(new TitledBorder(new EtchedBorder(),""));
((javax.swing.border.TitledBorder) p11.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) p11.getBorder()).setTitleColor(Color.BLUE);
getContentPane().add(p1);
getContentPane().add(p2);
getContentPane().add(p3);
getContentPane().add(p4);
getContentPane().add(p5);
getContentPane().add(p6);
getContentPane().add(p7);
getContentPane().add(p8);
getContentPane().add(p9);
getContentPane().add(p11);
setSize(900 + insets.left+ insets.right,650 + insets.top + insets.bottom);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(false);
}
private void close() {
    try {
          if (statement != null) {
        	  statement.close();
      }
          if (preparedstatement != null) {
        	  preparedstatement.close();
            }
          if (connection != null) {
    	  connection.close();
          }
          if (resultset != null) {
    	  resultset.close();
        }
    } catch (Exception e) {

    }
  }

}
