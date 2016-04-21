
public class NotificationTo implements State{
public void notification(Context context)
{
context.setState(this);	
Subject subject = new Subject();
new Driver(subject);
new Rider(subject);
new Administrator(subject);
subject.setNotify();
}

@Override
public void notificationByDriver(Context context) {
}

@Override
public void notificationByPassenger(Context context) {
}
}
