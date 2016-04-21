import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
public class DriverPickPassengerGUI extends JFrame {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
Connection connection = null;
Connection connection1 = null;
PreparedStatement preparedstatement = null;
ResultSet resulset = null;
PreparedStatement preparedstatement1 = null;
ResultSet resulset1 = null;
public static String name = null;
public static String drive = null;
public static String DriverEmail = null;
public static String email = null;
public static String phone = null;
public static String RiderPhone = null;
public static String from = null;
public static String to = null;
public static String intersection = null;
public static String time = null;
public static String day = null;
private JPanel JPuser;
private JPanel JPtrip;
private JPanel JPtable;
private JPanel JPbutton;
private JTable Jtable;
private JScrollPane Jscrollpane;
private JTable Jtable1;
private JScrollPane Jscrollpane1;
private JLabel JLfrom;
private JLabel JLto;
private JLabel JLDaily;
public static JTextField JTId;
private JComboBox<String> JCfrom;
private JComboBox<String> JCto;
private JComboBox<String> JCDaily;
private JButton JBAdd;
private JButton JBSubmit;
private JButton JBView;
private JButton JBSchedule;
private JButton JBCancel;
DefaultTableModel defaulttable;
Font font;
Statement statement = null;
private String userName = "root";
private String passWord = "006462001";
public DriverPickPassengerGUI()
{
font = new Font("Arial", Font.PLAIN,22);
JPuser = new JPanel();
JPtrip = new JPanel();
JPtable = new JPanel();
JPbutton = new JPanel();

/* Jtable /
 * 
 */
Jtable = new JTable();
Jscrollpane = new JScrollPane(Jtable);
Jscrollpane.setPreferredSize(new Dimension(690,300));
JScrollBar bar = Jscrollpane.getVerticalScrollBar();
bar.setPreferredSize(new Dimension(25,0));

/* Jtable 1/
 * 
 */
defaulttable = new DefaultTableModel();
Jtable1 = new JTable(defaulttable);
defaulttable.addColumn("Intersection");
defaulttable.addColumn("Time");
defaulttable.addColumn("Status");

/** use to add a row to the table /
 * 
 */

Jtable1.setFont(font);
Jtable1.getAutoResizeMode();
Jtable1.getTableHeader().setFont(font);
Jtable1.setRowHeight(35);
//Jtable1.getTableHeader().setForeground(Color.RED);
RiderScheduleGUI.setJTableColum(Jtable1,690,60,22,18);
Jscrollpane1 = new JScrollPane(Jtable1);
Jscrollpane1.setPreferredSize(new Dimension(690,300));
JScrollBar bar1 = Jscrollpane.getVerticalScrollBar();
bar1.setPreferredSize(new Dimension(25,0));
JLfrom = new JLabel("From");
JLto = new JLabel("To");
JLDaily = new JLabel("Day");
JTId = new JTextField();
JTId.setColumns(12);
JCfrom = new JComboBox<String>();
JCfrom.addItem("");
JCfrom.addItem("SJSU");
JCfrom.addItem("Fremont");
JCfrom.addItem("San Francisco");
JCfrom.addItem("Sunnyvale");
JCfrom.addItem("Morgan Hill");
JCfrom.addItem("Mountain View");
JCfrom.addItem("San Mateo");
JCfrom.addItem("Pleasanton");
JCfrom.addItem("Campbell");
JCfrom.addItem("Milpitas");
JCfrom.addItem("Saratoga");
JCfrom.addItem("Los Gatos");
JCfrom.setEditable(true);
JCto = new JComboBox<String>();
JCto.addItem("");
JCto.addItem("SJSU");
JCto.addItem("Fremont");
JCto.addItem("San Francisco");
JCto.addItem("Sunnyvale");
JCto.addItem("Morgan Hill");
JCto.addItem("Mountain View");
JCto.addItem("San Mateo");
JCto.addItem("Pleasanton");
JCto.addItem("Campbell");
JCto.addItem("Milpitas");
JCto.addItem("Saratoga");
JCto.addItem("Los Gatos");
JCto.setEditable(true);
JCDaily = new JComboBox<String>();
JCDaily.addItem("");
JCDaily.addItem("Monday");
JCDaily.addItem("Tuesday");
JCDaily.addItem("Wednesday");
JCDaily.addItem("Thursday");
JCDaily.addItem("Friday");
JCDaily.addItem("Saturday");
JCDaily.setEditable(false);

/* User Search Button 
 * 
 */

JBAdd = new JButton("Add >>");
JBAdd.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
try {
	if(iSId()){
	int row = Jtable.getSelectedRow();
	String table_click = (Jtable.getModel().getValueAt(row, 0).toString());
	Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
			String querry = "select Intersection, Time, Status,day,phone, email,LastName,tripfrom,tripto from mytrip,member where Intersection ='" + table_click + "' and day= '" + JCDaily.getSelectedItem() + "' and tripfrom = '" + JCfrom.getSelectedItem() + "' and tripto = '" + JCto.getSelectedItem() + "' and member.id = mytrip.id " + "";
			preparedstatement = connection.prepareStatement(querry);
			resulset = preparedstatement.executeQuery();
			String querry1 = "select phone,lastname,email from member where  id = '" + JTId.getText() + "'" + "";
			preparedstatement1 = connection.prepareStatement(querry1);
			resulset1 = preparedstatement1.executeQuery();
			while(resulset1.next()){
			phone = resulset1.getString("phone");
			drive = resulset1.getString("lastname");
			DriverEmail=resulset1.getString("email");
			}
			while(resulset.next()){
			intersection = resulset.getString("Intersection");
			time = resulset.getString("Time");
			String c = resulset.getString("Status");
			RiderPhone = resulset.getString("phone");
			name = resulset.getString("LastName");
			email = resulset.getString("email");
			from = resulset.getString("tripfrom");
			to = resulset.getString("tripto");
			day = resulset.getString("day");
			defaulttable.addRow(new Object[]{intersection,time,c} );
			String querry2 = "update mytrip set status ='Unavailable' where day = '" + day + "' and tripfrom = '" + from + "' and tripto ='" + to + "' and intersection ='" + intersection + "' and time ='" + time + "' and status = 'Available'" + "";
			preparedstatement1 = connection.prepareStatement(querry2);
			preparedstatement1.execute();
			String querry3 = "insert into driver values ('" + JTId.getText() + "', '" + day +"', '" + from + "', '" + to + "', '" + intersection +"', '" + time + "', '" + name + "', '" + RiderPhone + "')" + "";
			preparedstatement1 = connection.prepareStatement(querry3);
			preparedstatement1.execute();
			}
			Context context = new Context();
			NotificationTo notificationto = new NotificationTo();
			notificationto.notification(context);
			}
	else
	{
	JOptionPane.showMessageDialog(new JFrame(), "Not a valid ID");	
	}
	} catch (ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}finally {
close();	
}
}	
});

JBSchedule = new JButton("Driver Schedule");
JBSchedule.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
dispose();
new DriverScheduleGUI().setVisible(true);
}	
});
JBView = new JButton("Search Rider");
JBView.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
			String querry = "select Intersection, Time, Status from mytrip where day= '" + JCDaily.getSelectedItem() + "' and tripfrom = '" + JCfrom.getSelectedItem() + "' and tripto = '" + JCto.getSelectedItem() + "'" + "";
			preparedstatement = connection.prepareStatement(querry);
			resulset = preparedstatement.executeQuery();
			Jtable.getAutoResizeMode();
			Jtable.getTableHeader().setFont(font);
			Jtable.setRowHeight(35);
			Jtable.setModel(DbUtils.resultSetToTableModel(resulset));
			RiderScheduleGUI.setJTableColum(Jtable,690,60,22,18);
			}
	 catch (ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}finally {
close();	
}
}	
});
JBSubmit = new JButton("Add Rider");
JBSubmit.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent e){
	dispose();
	new MainControllerGUI().setVisible(true);
	}	
	});

/* Cancel Button / done
 * 
 */

JBCancel = new JButton("Cancel");
JBCancel.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
dispose();
new MainControllerGUI().setVisible(true);
}});

/** Font 
 *  
 */

JTId.setFont(font);
JLfrom.setFont(font);
JLto.setFont(font);
JLDaily.setFont(font);
JCDaily.setFont(font);
JCfrom.setFont(font);
JCto.setFont(font);
Jtable.setFont(font);
JBAdd.setFont(font);
JBSubmit.setFont(font);
JBView.setFont(font);
JBSchedule.setFont(font);
JBCancel.setFont(font);
setTitle("Search Rider");
ImageIcon imageicon = new ImageIcon("D:/Spring 2016/Res_management/carpool.png");
setIconImage(imageicon.getImage());
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS ));
JPuser.add(JTId);
JPtrip.add(JLDaily);
JPtrip.add(JCDaily);
JPtrip.add(JCfrom);
JPtrip.add(JLfrom);
JPtrip.add(JCfrom);
JPtrip.add(JLto);
JPtrip.add(JCto);
JPtable.add(Jscrollpane);
JPtable.add(JBAdd);
JPtable.add(Jscrollpane1);
JPuser.setBorder(new TitledBorder(new EtchedBorder(),"Driver ID"));
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleFont(font);
JPtrip.setBorder(new TitledBorder(new EtchedBorder(),"Trip"));
((javax.swing.border.TitledBorder) JPtrip.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPtrip.getBorder()).setTitleColor(Color.BLUE);
JPtable.setBorder(new TitledBorder(new EtchedBorder(),"Rider Schedule"));
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleFont(font);
JPbutton.setBorder(new TitledBorder(new EtchedBorder(),"Menu"));
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleColor(Color.BLUE);
JPbutton.add(JBView);
JPbutton.add(JBSubmit);
JPbutton.add(JBSchedule);
JPbutton.add(JBCancel);
getContentPane().add(JPuser);
getContentPane().add(JPtrip);
getContentPane().add(JPtable);
getContentPane().add(JPbutton);
add(JPuser);
add(JPtrip);
add(JPtable);
add(JPbutton);
pack();
setResizable(false);
setSize(1550,700);
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
		resulset = preparedstatement.executeQuery();
		while(resulset.next()){
		String a = resulset.getString("Id");
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
          if (resulset != null) {
    	  resulset.close();
        }
    } catch (Exception e) {
    }
  }
}
