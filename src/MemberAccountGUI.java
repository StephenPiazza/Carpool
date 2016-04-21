import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
public class MemberAccountGUI extends JFrame {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
Connection connection = null;
PreparedStatement preparedstatement = null;
ResultSet resulset = null;
private JPanel JPuser;
private JPanel JPtable;
private JPanel JPbutton;
private JTable Jtable;
private JScrollPane Jscrollpane;
private JTextField JTId;
private JButton JBCreate;
private JButton JBView;
private JButton JBCancel;
DefaultTableModel defaulttable;
Font font;
Statement statement = null;
ResultSet resultset = null;
private String userName = "root";
private String passWord = "006462001";
public MemberAccountGUI()
{
font = new Font("Arial", Font.PLAIN,22);
JPuser = new JPanel();
JPtable = new JPanel();
JPbutton = new JPanel();

/* Jtable /
 * 
 */
Jtable = new JTable();
Jscrollpane = new JScrollPane(Jtable);
Jscrollpane.setPreferredSize(new Dimension(1000,300));
JScrollBar bar = Jscrollpane.getVerticalScrollBar();
bar.setPreferredSize(new Dimension(25,0));

/* Jtable 1/
 * 
 */
defaulttable = new DefaultTableModel();

/** use to add a row to the table /
 * 
 */

JTId = new JTextField();
JTId.setColumns(12);


/* Schedule button
 * 
 */

JBView = new JButton("View Member");
JBView.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
try {
Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
		if(JTId.getText().equalsIgnoreCase("")){
		return;	
		}	
		else if(JTId.getText().equalsIgnoreCase("all"))
		{	String querry = "select LastName, FirstName, Sharedvehicles, email, phone from member";
			preparedstatement = connection.prepareStatement(querry);
			resultset = preparedstatement.executeQuery();
			Jtable.getAutoResizeMode();
			Jtable.getTableHeader().setFont(font);
			Jtable.setRowHeight(35);
			Jtable.getTableHeader().setForeground(Color.GRAY);
			Jtable.setModel(DbUtils.resultSetToTableModel(resultset));
			RiderScheduleGUI.setJTableColum(Jtable,1000,17,17,16,30,20);	
		}
		else
		{
			String querry = "select LastName, FirstName, Sharedvehicles, email, phone,Password from member where id='" + JTId.getText()+ "'" + "";
			preparedstatement = connection.prepareStatement(querry);
			resultset = preparedstatement.executeQuery();
			Jtable.getAutoResizeMode();
			Jtable.getTableHeader().setFont(font);
			Jtable.setRowHeight(35);
			Jtable.getTableHeader().setForeground(Color.GREEN);
			Jtable.setModel(DbUtils.resultSetToTableModel(resultset));	
			RiderScheduleGUI.setJTableColum(Jtable,1000,12,12,16,30,15,15);		
		}		
			} catch (ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}finally {
close();	
}
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
Jtable.setFont(font);
JBCreate = new JButton("Create Member");
JBCreate.setFont(font);
JBCreate.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
dispose();
new CreateMemberGUI().setVisible(true);	
}	
});

JBView.setFont(font);
JBCancel.setFont(font);
setTitle("Member Account");
ImageIcon imageicon = new ImageIcon("D:/Spring 2016/Res_management/carpool.png");
setIconImage(imageicon.getImage());
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS ));
JPuser.add(JTId);
JPtable.add(Jscrollpane);
JPtable.setBorder(new TitledBorder(new EtchedBorder(),"Member Account"));
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleColor(Color.BLUE);
JPuser.setBorder(new TitledBorder(new EtchedBorder(),"Enter your ID or type 'all'"));
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleColor(Color.BLUE);
JPbutton.setBorder(new TitledBorder(new EtchedBorder(),"Menu"));
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleColor(Color.BLUE);
JPbutton.add(JBView);
JPbutton.add(JBCreate);
JPbutton.add(JBCancel);
getContentPane().add(JPuser);
getContentPane().add(JPtable);
getContentPane().add(JPbutton);
add(JPuser);
add(JPtable);
add(JPbutton);
pack();
setSize(1050,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(false);
}
private void close() {
    try {
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
