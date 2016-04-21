import java.util.ArrayList;
import java.util.List;

public class Driver extends Member{
static List<String> arr;	
public Driver(String lastName, String firstName, String Vehicles, String email, String passWord, String phone, String id)
{
	super(lastName, firstName, Vehicles, email, passWord, phone, id);
}	
public Driver(Subject subject)
{
super("","","","","","","");	
this.subject=subject;
this.subject.attach(this);
}
public void notification() {
	 	arr = new ArrayList<String>();
		String x = DriverPickPassengerGUI.DriverEmail;
		arr.add(x);
		for(int i = 0; i < arr.size(); i++)
	    {
	    String[] to ={arr.get(i)};
	     MySQL.NotifyDriver ("se1332016","006462001",to); 
	    }
}
@Override
public void notificationByDriver() {	
}
@Override
public void notificationByPassenger() {
	arr = new ArrayList<String>();
	String x = DeleteMemberGUI.EmailPassenger; // NEED TO USE IF TO CONTROL EMAIL IN CASE DELETE THE MEMBER THAT IS NOT RELATED TO PASSENGER
	arr.add(x);
	for(int i = 0; i < arr.size(); i++)
    {
    String[] to ={arr.get(i)};
     MySQL.NotifyByDriver ("se1332016","006462001",to); 
    }
}
@Override
public void notificationBySystem() {
	}
}