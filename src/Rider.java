import java.util.ArrayList;
import java.util.List;

//* This class is User Information class to set Users and get Users *//

public class Rider extends Member{
	static List<String> arr;
public Rider(String lastName, String firstName, String Vehicles, String email, String passWord, String phone, String id)
{
	super(lastName, firstName, Vehicles, email, passWord, phone, id);
}
public Rider(Subject subject)
{
super("","","","","","","");	
this.subject=subject;
this.subject.attach(this);
}
public void notification() {
	
	 	arr = new ArrayList<String>();
		String x = DriverPickPassengerGUI.email;
		arr.add(x);
		for(int i = 0; i < arr.size(); i++)
	    {
	    String[] to ={arr.get(i)};
	     MySQL.NotifyRider("se1332016","006462001",to); ///
	    }
}
@Override
public void notificationByDriver() {
 		arr = new ArrayList<String>();
		String x = DeleteMemberGUI.EmailDriver; //NEED TO USE IF TO CONTROL EMAIL IN CASE DELETE THE MEMBER THAT IS NOT RELATED TO DRIVER
		arr.add(x);
		for(int i = 0; i < arr.size(); i++)
	    {
	    String[] to ={arr.get(i)};
	     MySQL.NotifyByRider("se1332016","006462001",to); 
	    }	
}
@Override
public void notificationByPassenger() {
}
@Override
public void notificationBySystem() {
}
}
