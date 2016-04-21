
public class NotificationBy implements State{
public void notificationByDriver(Context context)
{
context.setState(this);	
Subject subject = new Subject();
new Rider(subject);
subject.setNotifyByPassenger();
}

@Override
public void notification(Context context) {
}

@Override
public void notificationByPassenger(Context context) {
	context.setState(this);	
	Subject subject = new Subject();
	new Driver(subject);
	subject.setNotifyByDriver();
}
public void notificationBySystem(Context context) {
	context.setState(this);	
	Subject subject = new Subject();
	new Administrator(subject);
	subject.setNotifyBySystem();	
}
}
