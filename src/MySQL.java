import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class MySQL {
static ArrayList<String> array = new ArrayList<String>(); 
private Connection connection = null;
private Statement statement = null;
private PreparedStatement preparedstatement = null; 
private String userName = "root";
private String passWord = "006462001";
public void addNewMember(Member users) throws SQLException
{
try {
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
	statement = connection.createStatement();
	statement.execute("insert into Member values ('" + users.getLastName() + "','" + users.getFirstName() + "','" + users.getVehicles() + "','" + users.getemail() + "','" + users.getpassWord() + "','" + users.getPhone() + "', '" + users.getid() + "')" + "");
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
}
public void createTable() throws SQLException
{
try {
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",userName,passWord);
	DatabaseMetaData dbmdcust = connection.getMetaData();
    ResultSet table = dbmdcust.getTables(null, null, "Member", null);
	if(!table.next()) // Check if not exiting employees tables.
	{
	String createTable = "create table Member(LastName varchar(50) not null, firstName varchar(50) not null, DoB varchar(50) not null, email varchar(50) not null, passWord varchar(50) not null, phone varchar(50) not null, id varchar(50) NOT NULL primary key);";//Orderdate datetime NOT NULL DEFAULT GETDATE()	
	preparedstatement = connection.prepareStatement(createTable + "");	
	preparedstatement.executeUpdate();	
	}
}	
 catch (ClassNotFoundException e)
{
e.printStackTrace();	 
} finally {
close();		
}
}
public static boolean NotifyRider(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);
Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("Group 9 SE133 Spring 2016");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Hi " + DriverPickPassengerGUI.name + ",\n\n" +
    		DriverPickPassengerGUI.drive +" will pick you up from " + DriverPickPassengerGUI.from + " to " + DriverPickPassengerGUI.to + "\n\n" + "at Intersection: " + DriverPickPassengerGUI.intersection + "\n\n" + "at: " + DriverPickPassengerGUI.time + "\n\n" + "On: " + DriverPickPassengerGUI.day + "\n\n"
    + "Please contact Driver at " + DriverPickPassengerGUI.phone + "\n\n" + "Thank you ! \n\n\n\n" + "UC04-Process Notification to Rider-Do not reply this email" + "\n\n\n");
    Multipart multipart = new MimeMultipart();
     multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}

public static boolean NotifyByRider(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);
Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("Group 9 SE133 Spring 2016");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Hi, My name is " + DeleteMemberGUI.name + ",\n\n" +
    "I would like to cancel the trip. Please find another passenger." + "\n\n" + "Thank you ! \n\n\n\n" + "UC04-Process Notification by Rider-Do not reply this email" + "\n\n\n"); 
     // Create a multipar message
    Multipart multipart = new MimeMultipart();

     // Set text message part. this only includes text in body. 
     multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}

public static boolean NotifyDriver(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);
Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("Group 9 SE133 Spring 2016");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Hi " + DriverPickPassengerGUI.drive + ",\n\n" +
     "You will pick up " + DriverPickPassengerGUI.name + " from " + DriverPickPassengerGUI.from + " to " + DriverPickPassengerGUI.to + "\n\n" + "at Intersection: " + DriverPickPassengerGUI.intersection + "\n\n" + "at: " + DriverPickPassengerGUI.time + "\n\n" + "On: " + DriverPickPassengerGUI.day + "\n\n"
    + "Please contact Passenger at " + DriverPickPassengerGUI.RiderPhone + "\n\n" + "Thank you ! \n\n\n\n" + "UC04-Process Notification to Driver-Do not reply this email" + "\n\n\n");
     
     // Create a multipar message
    Multipart multipart = new MimeMultipart();

     // Set text message part. this only includes text in body. 
     multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}

public static boolean NotifyByDriver(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);

Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("Group 9 SE133 Spring 2016");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Hi, My name is " + DeleteMemberGUI.name + ",\n\n" 
    + "I would like to cancel to pick you up. Please find another Driver." + "\n\n" + "Thank you ! \n\n\n\n" + "UC04-Process Notification by Driver-Do not reply this email" + "\n\n\n");
     
     // Create a multipar message
    Multipart multipart = new MimeMultipart();

     // Set text message part. this only includes text in body. 
     multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}


public static boolean NotifyAdministrator(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);

Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("Group 9 SE133 Spring 2016");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText(
    		DriverPickPassengerGUI.drive +" will pick up " + DriverPickPassengerGUI.name + " from " + DriverPickPassengerGUI.from + " to " + DriverPickPassengerGUI.to + "\n\n" + "at Intersection: " + DriverPickPassengerGUI.intersection + "\n\n" + "at: " + DriverPickPassengerGUI.time + "\n\n" + "On: " + DriverPickPassengerGUI.day + "\n\n"
    + "Driver phone: " + DriverPickPassengerGUI.phone + "\n\n" + "Passenger phone: " + DriverPickPassengerGUI.RiderPhone + "\n\n" + "Thank you ! \n\n\n\n" + "UC04-Process Notification to System-Do not reply this email" + "\n\n\n");
     
     // Create a multipar message
    Multipart multipart = new MimeMultipart();

     // Set text message part. this only includes text in body. 
     multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}

public static boolean NotifyByAdministrator(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);
Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("Group 9 SE133 Spring 2016");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText(DeleteMemberGUI.name + " canceled the member." + "\n\n" + "Thank you ! \n\n\n\n" + "UC04-Process Notification by System-Do not reply this email" + "\n\n\n");
     
     // Create a multipar message
    Multipart multipart = new MimeMultipart();

     // Set text message part. this only includes text in body. 
     multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}

public static boolean SendId(final String from, final String pass, String to[])
{
String host = "smtp.gmail.com";
Properties pros = System.getProperties();
pros.put("mail.smtp.starttls.enable", "true");
pros.put("mail.smtp.ssl.trust", host);
pros.put("mail.smtp.host","smtp.gmail.com");
pros.put("mail.smtp.user",from);
pros.put("mail.smtp.password",pass);
pros.put("mail.smtp.auth","true");
pros.put("mail.smtp.port",587);
MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
CommandMap.setDefaultCommandMap(mc);
Session session = Session.getDefaultInstance(pros,new Authenticator()
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(from, pass);
	}
}
		);
session.setDebug(true);
MimeMessage mine = new MimeMessage(session);
try{
	mine.setFrom(new InternetAddress(from));
	InternetAddress[] add = new InternetAddress[to.length];
	for (int i=0;i<to.length;i++){
		add[i] = new InternetAddress(to[i]);}
	for(int i = 0;i<add.length;i++)
	{
	mine.addRecipient(RecipientType.TO, add[i]);
	}
	mine.setSubject("CarPool System");
	BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Hi " + CreateMemberGUI.name + ",\n\n" +
    "Congratulations!, You have become a member of CarPool System. This is your ID: " + CreateMemberGUI.myid + ". You will use your ID for later on doing things with Carpool System" +  
    ". Please contact us at se1332016@gmail.com if you have any question.\n\n\n\n\n" + "Thank you ! \n\n"+ "Group 9 - SE133-Spring 2016\n\n\n" + "http://engineering.sjsu.edu/");
     
     // Create a multipar message
     Multipart multipart = new MimeMultipart();

     // Set text message part. this only includes text in body. 
     multipart.addBodyPart(messageBodyPart);

     // Part two is attachment. This is for a file attachment.
     messageBodyPart = new MimeBodyPart();
     String filename = "Patterns.pdf";
     DataSource source = new FileDataSource(filename);
     messageBodyPart.setDataHandler(new DataHandler(source));
     messageBodyPart.setFileName(filename);
     multipart.addBodyPart(messageBodyPart);
     
    // Send the complete message parts. Either for attach file or not we still need this.  
     mine.setContent(multipart );
     Transport tran = session.getTransport("smtps");
     tran.connect("smtp.gmail.com",from, pass);
     tran.sendMessage(mine, mine.getRecipients(Message.RecipientType.TO));         
     tran.close();
     return true;
}catch(MessagingException e)
{
e.getStackTrace();
}
return false;
}

public void NewMemberId()
{
	String x = CreateMemberGUI.email;
	array.add(x);
	for(int i = 0; i < array.size(); i++)
    {
    String[] to ={array.get(i)};
    
    if( SendId ("se1332016","006462001",to)) 
    JOptionPane.showMessageDialog(new JFrame(),"Your ID has been sent to your email","Notice",JOptionPane.INFORMATION_MESSAGE); 
   
    else {
     	JOptionPane.showMessageDialog(new JFrame(), "Opps...! Your email is not stored in our system, Please try with another.","Alert",JOptionPane.ERROR_MESSAGE);        
    }	  
    } 
}





















private void close() {
    try {
          if (statement != null) {
        statement.close();
      }
      if (connection != null) {
        connection.close();
      }
    } catch (Exception e) {
    }
  }
}
