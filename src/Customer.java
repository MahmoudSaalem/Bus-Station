import java.io.Serializable;

@SuppressWarnings("serial") 
public class Customer extends Person implements Serializable {
 
	public Customer() {
 
	}
	public Customer(String firstName, String lastName, String userName, String password, int age) {
		setFirstName(format(firstName));
		setLastName(format(lastName));
		setUserName(userName);
		setPassword(password);
		setAge(age);
	}
	public int bookTicket(int tripID, String ticketType) {
		Adminstration a = Adminstration.getInstance();
		String vehicleType = a.tripList.getVehicleType(tripID);
		int ticketID = 0;
		int vehicleNum = a.tripList.getVehicleNum(tripID);
		boolean passengerAdded = a.addPassenger(vehicleType, vehicleNum);
		if(passengerAdded)
			ticketID = a.addTicket(tripID, getUserName(), ticketType).getTicketID();
		else
			a.ticketList.getTicket(-1);
		return ticketID;
	}
	@Override
	public String toString() {
		return "Customer," + getFirstName() + "," + getLastName() + ","
				+ getUserName() + "," + getAge();
	}
}
