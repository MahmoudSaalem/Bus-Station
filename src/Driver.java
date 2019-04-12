import java.io.Serializable;

@SuppressWarnings("serial")
public class Driver extends Person implements Serializable {
	
	public Driver() {

	}
	public Driver(String firstName, String lastName, String userName, String password, int age) {
		setFirstName(format(firstName));
		setLastName(format(lastName));
		setUserName(userName);
		setPassword(password);
		setAge(age);
	}
	@Override
	public String toString() {
		return "Driver," + getFirstName() + "," + getLastName() + ","
				+ getUserName() + "," + getAge();
	}
}
