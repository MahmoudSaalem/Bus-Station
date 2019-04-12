import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PersonList implements Serializable {

	private ArrayList<Person> personList = new ArrayList<>();
	
	public void addManager(String firstName, String lastName, String userName, String password, int age) {
		if(usernameDoublication(userName)==0) {
			personList.add(new Manager(firstName, lastName, userName, password, age));
		}
		else
			personList.get(-1); // aywa ana elly 3ayzo yedrab 3ashan a7otaha f try catch fl gui
	}
	public void addCustomer(String firstName, String lastName, String userName, String password, int age) {
		if(usernameDoublication(userName)==0) {
			personList.add(new Customer(firstName, lastName, userName, password, age));
		}
		else
			personList.get(-1);
	}
	public void addDriver(String firstName, String lastName, String userName, String password, int age) {
		if(usernameDoublication(userName)==0) {
			personList.add(new Driver(firstName, lastName, userName, password, age));
		}
		else  
			personList.get(-1);
	}
	public int remove(String username) {
		try {
			personList.remove(getIndex(username));
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}
	public int getIndex(String username) {
		int i = 0;
		for(Person p:personList) {
			if(p.getUserName().equals(username)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	public int getSize() {
		return personList.size();
	}
	public String getData(int index) {
		return personList.get(index).toString();
	}
	public void printData() {
		for(Person p:personList)
			System.out.println(p);
	}
	public int usernameDoublication(String username) {
		for(Person p:personList) {
			if(p.getUserName().equals(username)) {
				return 1;
			}
		}
		return 0;
	}
	public Person getPerson(String username) {
		int index = getIndex(username);
		return personList.get(index);
	}
	public boolean isDriver(String driverUsername) {
		Person p = getPerson(driverUsername);
		if(p instanceof Driver)
			return true;
		return false;
	}
	public boolean isManager(String managerUsername) {
		Person p = getPerson(managerUsername);
		if(p instanceof Manager)
			return true;
		return false;
	}
	public ArrayList<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}
}
