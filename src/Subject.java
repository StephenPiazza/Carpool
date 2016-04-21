import java.util.ArrayList;
import java.util.List;

public class Subject {
/**
	 * 
	 */
private List<Member> members = new ArrayList<Member>();

public void setNotify()
{
notifyAllMember();
}
public void setNotifyByDriver()
{
notifyAllMemberByDriver();
}
public void setNotifyByPassenger()
{
notifyAllMemberByPassenger();
}
public void setNotifyBySystem()
{
notifyAllMemberBySystem();
}
public void attach(Member member)
{
members.add(member);	
}
public void notifyAllMember()
{
for(Member member: members)
{
member.notification();	
}
}
public void notifyAllMemberByDriver()
{
for(Member member: members)
{
member.notificationByPassenger();	
}
}	
public void notifyAllMemberByPassenger()
{
for(Member member: members)
{
member.notificationByDriver();	
}
}	
public void notifyAllMemberBySystem()
{
for(Member member: members)
{
member.notificationBySystem();	
}
}
}	




