import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
public class RiderScheduleGUI extends JFrame {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
Connection connection = null;
PreparedStatement preparedstatement = null;
ResultSet resulset = null;
private JPanel JPuser;
private JPanel JPtrip;
private JPanel JPintersection;
private JPanel JPtable;
private JPanel JPbutton;
private JTable Jtable;
private JScrollPane Jscrollpane;
private JLabel JLday;
private JLabel JLfrom;
private JLabel JLto;
private JLabel JLintersection;
private JLabel JLtime;
private JTextField JTId;
private JComboBox<String> JCfrom;
private JComboBox<String> JCto;
private JComboBox<String> JCday;
private JComboBox<String> JCintersection;
private JComboBox<String> JCtime;
private JButton JBadd;
private JButton JBcreate;
private JButton JBview;
private JButton JBcancel;
Font font;
Statement statement = null;
ResultSet resultset = null;
private String userName = "root";
private String passWord = "006462001";
public RiderScheduleGUI()
{
font = new Font("Arial", Font.PLAIN,22);
JPuser = new JPanel();
JPtrip = new JPanel();
JPintersection = new JPanel();
JPtable = new JPanel();
JPbutton = new JPanel();
Jtable = new JTable();
Jscrollpane = new JScrollPane(Jtable);
Jscrollpane.setPreferredSize(new Dimension(1050,250));
JScrollBar bar = Jscrollpane.getVerticalScrollBar();
bar.setPreferredSize(new Dimension(25,0));
JLday = new JLabel("Day");
JLfrom = new JLabel("From");
JLto = new JLabel("To");
JLintersection = new JLabel("Intersection");
JLtime = new JLabel("Time");
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
JCday = new JComboBox<String>();
JCday.addItem("");
JCday.addItem("Monday");
JCday.addItem("Tuesday");
JCday.addItem("Wednesday");
JCday.addItem("Thursday");
JCday.addItem("Friday");
JCday.addItem("Saturday");
JCfrom.setEditable(true);
JCintersection = new JComboBox<String>();
JCintersection.addItem("");
JCintersection.addItem("S 3rd St & E San Salvado St");
JCintersection.addItem("S 4th St & E San Salvado St");
JCintersection.addItem("S 5th St & E San Salvado St");
JCintersection.addItem("S 6th St & E San Salvado St");
JCintersection.addItem("S 7th St & E San Salvado St");
JCintersection.addItem("S 8th St & E San Salvado St");
JCintersection.addItem("S 9th St & E San Salvado St");
JCintersection.addItem("S 10th St & E San Salvado St");
JCintersection.addItem("S 11th St & E San Salvado St");
JCintersection.addItem("S 12th St& E San Salvado St");
JCintersection.addItem("S 13th St & E San Salvado St");
JCintersection.addItem("E William St & E San Salvado St");
JCintersection.addItem("E Reed St & E San Fernando St");
JCintersection.addItem("E Santa Clara St & E San Fernando St");
JCintersection.addItem("E Reed St & E William St");
JCintersection.addItem("E San Fernando St & E William St");
JCintersection.addItem("E San Salvado St & E San Antonio St");
JCintersection.addItem("E San Antonio St & E William St");
JCintersection.addItem("E St James St & E Julian St");
JCintersection.addItem("E Julian St & E Empire St");
JCintersection.addItem("Jackson St& N 18th St");
JCintersection.addItem("E Hedding St & N 14th St");
JCintersection.setEditable(true);
JCtime = new JComboBox<String>();
JCtime.addItem("");
JCtime.addItem("8:00 AM");
JCtime.addItem("8:30 AM");
JCtime.addItem("9:00 AM");
JCtime.addItem("9:30 AM");
JCtime.addItem("10:00 AM");
JCtime.addItem("10:30 AM");
JCtime.addItem("11:00 AM");
JCtime.addItem("11:30 AM");
JCtime.addItem("12:00 PM");
JCtime.addItem("12:30 PM");
JCtime.addItem("1:00 PM");
JCtime.addItem("1:30 PM");
JCtime.addItem("2:00 PM");
JCtime.addItem("2:30 PM");
JCtime.addItem("3:00 PM");
JCtime.addItem("3:30 PM");
JCtime.addItem("4:00 PM");
JCtime.addItem("4:30 PM");
JCtime.addItem("5:00 PM");
JCtime.addItem("5:30 PM");
JCtime.addItem("6:00 PM");
JCtime.addItem("6:30 PM");
JCtime.addItem("7:00 PM");
JCtime.addItem("7:30 PM");
JCtime.setFont(font);
JCtime.setEditable(true);

/* Submit Button /
 * 
 */

JBcreate = new JButton("Create Ride"); 
JBcreate.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
try {
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
	statement = connection.createStatement();
	statement.execute("delete from intersec");
	dispose();
	new MainControllerGUI().setVisible(true);
	} catch (ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}finally {
close();	
}
}	
});

/* Cancel Button /
 * 
 */

JBcancel = new JButton("Cancel");
JBcancel.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
dispose();
new MainControllerGUI().setVisible(true);
}});

JBview = new JButton("Rider Schedule");
JBview.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
		String querry = "select Day, Tripfrom, Tripto, Intersection,Time,Status from mytrip where id='" + JTId.getText() + "'" + "";
		preparedstatement = connection.prepareStatement(querry);
		resultset = preparedstatement.executeQuery();
	Jtable.getAutoResizeMode();
	Jtable.getTableHeader().setFont(font);
	Jtable.setRowHeight(35);
	Jtable.getTableHeader().setForeground(Color.BLUE);
	Jtable.setModel(DbUtils.resultSetToTableModel(resultset));
	setJTableColum(Jtable,1050,15,18,17,30,10,10);	
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
/* Add Button /
 * 
 */

JBadd = new JButton("Add >>");
JBadd.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e){
try {
	String subid = JTId.getText();
	String subday = (String) JCday.getSelectedItem();
	String subfrom = (String) JCfrom.getSelectedItem();
	String subto = (String) JCto.getSelectedItem();
	String subintersection = (String) JCintersection.getSelectedItem();
	String subtime = (String) JCtime.getSelectedItem();
	if(iSId())
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
	statement = connection.createStatement();
	statement.execute("insert into mytrip values ('" + subday + "', '" + subfrom + "', '" + subto + "', '" + subintersection + "', '" + subtime + "', '" + subid + "', 'Available')"  + "");
	statement.execute("insert into intersec values ('" + subday + "', '" + subfrom + "', '" + subto + "', '" + subintersection + "', '" + subtime + "')" + "");
		String querry = "select Day, Tripfrom,Tripto, Intersection,Time from intersec";
		preparedstatement = connection.prepareStatement(querry);
		resultset = preparedstatement.executeQuery();
		Jtable.getAutoResizeMode();
		Jtable.getTableHeader().setFont(font);
		Jtable.setRowHeight(35);
		Jtable.getTableHeader().setForeground(Color.BLUE);
		Jtable.setModel(DbUtils.resultSetToTableModel(resultset));
		//Jtable.setEnabled(false);
		setJTableColum(Jtable,1050,20,17,17,35,10);	
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

/** Font 
 *  
 */

JLday.setFont(font);
JTId.setFont(font);
JLfrom.setFont(font);
JLto.setFont(font);
JLday.setFont(font);
JLintersection.setFont(font);
JCintersection.setFont(font);
JCfrom.setFont(font);
JCto.setFont(font);
JCday.setFont(font);
JLtime.setFont(font);
JCtime.setFont(font);
Jtable.setFont(font);
JBadd.setFont(font);
JBcreate.setFont(font);
JBview.setFont(font);
JBcancel.setFont(font);
setTitle("Rider");
ImageIcon imageicon = new ImageIcon("D:/Spring 2016/Res_management/carpool.png");
setIconImage(imageicon.getImage());
setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS ));

//JPuser.add(JLday);
JPuser.add(JTId);
JPtrip.add(JLday);
JPtrip.add(JCday);
JPtrip.add(JLfrom);
JPtrip.add(JCfrom);
JPtrip.add(JLto);
JPtrip.add(JCto);
JPintersection.add(JLintersection);
JPintersection.add(JCintersection);
JPintersection.add(JLtime);
JPintersection.add(JCtime);
JPintersection.add(JBadd);
JPtable.add(Jscrollpane);
JPintersection.setBorder(new TitledBorder(new EtchedBorder(),"Intersection and Time"));
((javax.swing.border.TitledBorder) JPintersection.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPintersection.getBorder()).setTitleColor(Color.BLUE);
JPtrip.setBorder(new TitledBorder(new EtchedBorder(),"Trip"));
((javax.swing.border.TitledBorder) JPtrip.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPtrip.getBorder()).setTitleColor(Color.BLUE);
JPuser.setBorder(new TitledBorder(new EtchedBorder(),"Rider ID"));
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPuser.getBorder()).setTitleColor(Color.BLUE);
JPtable.setBorder(new TitledBorder(new EtchedBorder(),"View"));
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleFont(font);
((javax.swing.border.TitledBorder) JPtable.getBorder()).setTitleColor(Color.BLUE);
JPbutton.setBorder(new TitledBorder(new EtchedBorder(),""));
((javax.swing.border.TitledBorder) JPbutton.getBorder()).setTitleFont(font);
JPbutton.add(JBcreate);
JPbutton.add(JBview);
JPbutton.add(JBcancel);
getContentPane().add(JPuser);
getContentPane().add(JPtrip);
getContentPane().add(JPintersection);
getContentPane().add(JPtable);
getContentPane().add(JPbutton);
getRootPane().setDefaultButton(JBcreate);
getRootPane().setDefaultButton(JBview);
getRootPane().setDefaultButton(JBcancel);
add(JPuser);
add(JPtrip);
add(JPintersection);
add(JPtable);
add(JPbutton);
pack();
setSize(1100,650);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public static void setJTableColum(JTable jtable, int width, double...percentages  )
{
double total =0;
for(int i=0;i<jtable.getColumnModel().getColumnCount();i++)
{
total+=percentages[i];
}
for(int i=0;i<jtable.getColumnModel().getColumnCount();i++)
{
TableColumn tablecolumn = jtable.getColumnModel().getColumn(i);	
tablecolumn.setPreferredWidth((int) (width*(percentages[i]/total)));
}
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
