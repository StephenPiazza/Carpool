import java.util.ArrayList;
import java.util.List;

public class Administrator extends Member{
static List<String> arr;	
public Administrator(Subject subject)
{
super("","","","","","","");	
this.subject=subject;
this.subject.attach(this);
}
public void notification() {
	 	arr = new ArrayList<String>();
		String x = "se1332016@gmail.com";
		arr.add(x);
		for(int i = 0; i < arr.size(); i++)
	    {
	    String[] to ={arr.get(i)};
	     MySQL.NotifyAdministrator ("se1332016","006462001",to); 
	    }
}
@Override
public void notificationByDriver() {
}
@Override
public void notificationByPassenger() {
}
@Override
public void notificationBySystem() {
		arr = new ArrayList<String>();
		String x = "se1332016@gmail.com";
		arr.add(x);
		for(int i = 0; i < arr.size(); i++)
	    {
	    String[] to ={arr.get(i)};
	     MySQL.NotifyByAdministrator ("se1332016","006462001",to); 
	    }
}
}