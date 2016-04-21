
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
public class DriverScheduleGUI extends JFrame {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
Connection connection = null;
PreparedStatement preparedstatement = null;
ResultSet resulset = null;
private JPanel JPtable;
private JPanel JPbutton;
private JTable Jtable;
private JScrollPane Jscrollpane;
private JButton JBOk;
DefaultTableModel defaulttable;
Font font;
Statement statement = null;
ResultSet resultset = null;
private String userName = "root";
private String passWord = "006462001";
public DriverScheduleGUI()
{
font = new Font("Arial", Font.PLAIN,22);
JPtable = new JPanel();
JPbutton = new JPanel();

/* Jtable /
 * 
 */


Jtable = new JTable();
Jscrollpane = new JScrollPane(Jtable);
Jscrollpane.setPreferredSize(new Dimension(1000,400));
JScrollBar bar = Jscrollpane.getVerticalScrollBar();
bar.setPreferredSize(new Dimension(25,0));

/* Jtable 1/
 * 
 */
defaulttable = new DefaultTableModel();

/** use to add a row to the table /
 * 
 */

/* Schedule button
 * 
 */

try {
Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
		String querry = "select Day, Intersection,Time, RiderName, phone from driver where driverid= '" + DriverPickPassengerGUI.JTId.getText() + "'" +"";
			preparedstatement = connection.prepareStatement(querry);
			resultset = preparedstatement.executeQuery();
			Jtable.getAutoResizeMode();
			Jtable.getTableHeader().setFont(font);
			Jtable.setRowHeight(35);
			Jtable.getTableHeader().setForeground(Color.RED);
			Jtable.setModel(DbUtils.resultSetToTableModel(resultset));
			//Jtable.setEnabled(false);
			RiderScheduleGUI.setJTableColum(Jtable,1000,20,35,13,14,18);	
			} catch (ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}finally {
close();	
}
/* Cancel Button / done
 * 
 */

JBOk = new JButton("Ok");
JBOk.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
dispose();
new MainControllerGUI().setVisible(true);
}});

/** Font 
 *  
 */

Jtable.setFont(font);
JBOk.setFont(font);
setTitle("Driver Schedule");
ImageIcon imageicon = new ImageIcon("D:/Spring 2016/Res_management/carpool.png");
setIconImage(imageicon.getImage());
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS ));
JPtable.add(Jscrollpane);
JPtable.setBorder(new TitledBorder(new EtchedBorder(),"Schedule to pick Rider up"));
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleColor(Color.BLUE);
JPbutton.setBorder(new TitledBorder(new EtchedBorder(),"Menu"));
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleColor(Color.BLUE);
JPbutton.add(JBOk);
getContentPane().add(JPtable);
getContentPane().add(JPbutton);
add(JPtable);
add(JPbutton);
pack();
setResizable(false);
setSize(1050,600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
