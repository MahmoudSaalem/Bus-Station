import java.io.Serializable;

@SuppressWarnings("serial")
public class Manager extends Person implements Serializable {
	
	Adminstration admin = Adminstration.getInstance();
	
	public Manager() {
		setUserName("admin");
		setPassword("admin");
	}
	public Manager(String firstName, String lastName, String userName, String password, int age) {
		setFirstName(format(firstName));
		setLastName(format(lastName));
		setUserName(userName);
		setPassword(password);
		setAge(age);
	}
	@Override
	public String toString() {
		return "Manager," + getFirstName() + "," + getLastName() + ","
				+ getUserName() + "," + getAge();
	}
}
