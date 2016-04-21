import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
public class DeleteMemberGUI extends JFrame {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
Connection connection = null;
PreparedStatement preparedstatement = null;
ResultSet resulset = null;
private String a;
private String b;
private String day;
private String from;
private String to;
private String time;
private String intersection;
public static String EmailDriver;
public static String EmailPassenger;
public static String name;
private JPanel JPuser;
private JPanel JPbutton;
private JPanel JPradio;
public static JTextField JTId;
private ButtonGroup group;
private JRadioButton RadioDriver; 
private JRadioButton RadioRider;
private JButton JBapply;
private JButton JBcancel;
Font font;
Statement statement = null;
ResultSet resultset = null;
private String userName = "root";
private String passWord = "006462001";
public DeleteMemberGUI()
{
font = new Font("Arial", Font.PLAIN,22);
JPuser = new JPanel();
JPradio = new JPanel();
JPbutton = new JPanel();
JTId = new JTextField();
JTId.setColumns(12);

JBapply = new JButton("Apply");
JBapply.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try {
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
	String querry = "select phone,firstName from Member where id= '" + JTId.getText() + "'" +"";
	preparedstatement = connection.prepareStatement(querry);
	resultset = preparedstatement.executeQuery();
	while(resultset.next()){
	a=resultset.getString("phone");
	name=resultset.getString("firstName");
	}
	if(iSId())
	{
		if(RadioRider.isSelected())
		{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);			
		String querry5 = "select email from Member,driver where Member.id=driver.driverid and driver.phone= '" + a + "'" +"";
		preparedstatement = connection.prepareStatement(querry5);
		resultset = preparedstatement.executeQuery();
		while(resultset.next()){
		EmailDriver =resultset.getString("email");
		}
		String querry1 = "delete from mytrip where id= '" + JTId.getText() + "'" +"";
		String querry2 = "delete from driver where phone= '" + a + "'" +"";
		String querry3 = "delete from member where id= '" + JTId.getText() + "'" +"";
		preparedstatement = connection.prepareStatement(querry1);
		preparedstatement.execute();
		preparedstatement = connection.prepareStatement(querry2);
		preparedstatement.execute();
		preparedstatement = connection.prepareStatement(querry3);
		preparedstatement.execute();	
		Context context = new Context();
		NotificationBy notificationby = new NotificationBy();
		notificationby.notificationByDriver(context);
		notificationby.notificationBySystem(context);
		}
		if(RadioDriver.isSelected())
		{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);		
		String querry0= "select id from member, driver where member.phone = driver.phone";
		preparedstatement = connection.prepareStatement(querry0);
		resultset = preparedstatement.executeQuery();
		while(resultset.next()){
		b = resultset.getString("id"); 	
		}	
		String querry1= "select day,tripfrom,tripto, intersection, time from driver";
		preparedstatement = connection.prepareStatement(querry1);
		resultset = preparedstatement.executeQuery();
		while (resultset.next())
		{
		day = resultset.getString("day");
		from = resultset.getString("tripfrom");
		to = resultset.getString("tripto");
		intersection = resultset.getString("intersection");
		time = resultset.getString("time");
		String querry2 = "update mytrip set status = 'Available' where id = '" + b + "' and day ='" + day + "' and tripfrom = '" + from + "' and tripto = '" + to + "' and intersection = '" + intersection + "' and time = '" + time + "'" + "";
		preparedstatement = connection.prepareStatement(querry2);
		preparedstatement.execute();
		}
		String querry6= "select email from Member,driver where Member.phone=driver.phone and driver.driverid= '" + JTId.getText() + "'" +"";
		preparedstatement = connection.prepareStatement(querry6);
		resultset = preparedstatement.executeQuery();
		while (resultset.next())
		{
		EmailPassenger = resultset.getString("email");
		}
		String querry3 = "delete from driver where driverid= '" + JTId.getText() + "'" +"";
		String querry4 = "delete from member where id= '" + JTId.getText() + "'" +"";
		preparedstatement = connection.prepareStatement(querry3);
		preparedstatement.execute();
		preparedstatement = connection.prepareStatement(querry4);
		preparedstatement.execute();
		Context context = new Context();
		NotificationBy notificationby = new NotificationBy();
		notificationby.notificationByPassenger(context);
		notificationby.notificationBySystem(context);
		}
	}
	else
	{
	JOptionPane.showMessageDialog(new JFrame(), "Invalid ID");	
	}
} catch (ClassNotFoundException e1) {
	e1.printStackTrace();
} catch (SQLException e1) {
	e1.printStackTrace();
}finally
{
close();	
}	
}	
});
group = new ButtonGroup();
RadioDriver = new JRadioButton("Driver");
RadioRider = new JRadioButton("Rider");
group.add(RadioDriver);
group.add(RadioRider);

/* Cancel Button /
 * 
 */

JBcancel = new JButton("Cancel");
JBcancel.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
dispose();
new MainControllerGUI().setVisible(true);
}});

/** Font 
 *  
 */

JTId.setFont(font);
JBapply.setFont(font);
RadioDriver.setFont(font);
RadioRider.setFont(font);
JBcancel.setFont(font);
setTitle("Delete Member");
ImageIcon imageicon = new ImageIcon("D:/Spring 2016/Res_management/carpool.png");
setIconImage(imageicon.getImage());
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS ));
JPuser.add(JTId);
JPuser.setBorder(new TitledBorder(new EtchedBorder(),"Member ID"));
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleColor(Color.BLUE);
JPradio.setBorder(new TitledBorder(new EtchedBorder(),"Member as Driver or Rider"));
((javax.swing.border.TitledBorder) JPradio.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPradio.getBorder()).setTitleColor(Color.BLUE);
JPbutton.setBorder(new TitledBorder(new EtchedBorder(),""));
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleFont(font);
JPradio.add(RadioDriver);
JPradio.add(RadioRider);
JPbutton.add(JBapply);
JPbutton.add(JBcancel);
getContentPane().add(JPuser);
getContentPane().add(JPradio);
getContentPane().add(JPbutton);
getRootPane().setDefaultButton(JBcancel);
getRootPane().setDefaultButton(JBapply);
add(JPuser);
add(JPradio);
add(JPbutton);
pack();
setSize(600,255);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
private boolean iSId() throws SQLException, ClassNotFoundException
{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
		String querry = "select id from member";
		preparedstatement = connection.prepareStatement(querry);
		resultset = preparedstatement.executeQuery();
		while(resultset.next()){
		String a = resultset.getString("Id");
		if(a.equalsIgnoreCase(JTId.getText()))
		{
		return true;
		}
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally
	{
			close();
	}
	return false;
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
    } catch (Exception e) {

    }
  }
}
