//* This class is User Information class to set Users and get Users *//

public abstract class Member {
protected Subject subject;	
private String lastName;
private String firstName;
private String Vehicles;
private String id;
private String phone;
private String email;
private String passWord;
public Member(String lastName, String firstName, String Vehicles, String email, String passWord, String phone, String id)
{
	this.lastName=lastName;
	this.firstName=firstName;
	this.Vehicles=Vehicles;
	this.id=id;
	this.phone=phone;
	this.email=email;
	this.passWord=passWord;
}
public void setLastName(String lastName)
{
	this.lastName=lastName;	
}
public void setFirstName(String firstName)
{
	this.firstName=firstName;	
}
public void setDoB(String Vehicles)
{
	this.Vehicles=Vehicles;
}
public void setPhone(String phone)
{
	this.phone=phone;
}

public void setemail(String email)
{
	this.email=email;
}
public void setpassWord(String passWord)
{
	this.passWord=passWord;	
}
public String getLastName()
{
	return lastName;	
}
public String getFirstName()
{
	return firstName;	
}
public String getVehicles()
{
	return Vehicles;	
}
public String getid()
{
	return id;	
}
public String getPhone()
{
	return phone;	
}
public String getemail()
{
	return email;	
}
public String getpassWord()
{
	return passWord;	
}
public abstract void notification();
public abstract void notificationByDriver();
public abstract void notificationByPassenger();
public abstract void notificationBySystem();
}
