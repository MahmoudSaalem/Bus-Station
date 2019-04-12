import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Adminstration implements Serializable {
	
	private String LastCustomerLoggedIn;
	private String LastDriverLoggedIn;
	
	VehicleList vehicleList = new VehicleList();
	TripList tripList = new TripList();
	PersonList personList = new PersonList();
	TicketList ticketList = new TicketList();
	
	private final static Adminstration admin = new Adminstration();
	
	private Adminstration() {
		
	}
	public static Adminstration getInstance() {
		return admin; 
	}
    protected Object readResolve() {
        return admin;
    }
	public Bus addBus() {
		vehicleList.addBus();
		return (Bus)vehicleList.getVehicle("Bus", Bus.getCarNum()-1);
	}
	public MiniBus addMiniBus() {
		vehicleList.addMiniBus();
		return (MiniBus)vehicleList.getVehicle("MiniBus", MiniBus.getCarNum()-1);
	}
	public Limousine addLimousine() {
		vehicleList.addLimousine();
		return (Limousine)vehicleList.getVehicle("Limousine", Limousine.getCarNum()-1);
	}
	public void removeVehicle(String vehicleType, int carNum) {
		vehicleList.getVehicle(vehicleType, carNum);
		vehicleList.remove(vehicleType, carNum);
	}
	public Trip addTrip(String source, String destination, String tripType, String tripStops, String driverUsername,
			String vehicle, int vehicleNum, double distance, String tripStartString) throws ParseException {
		boolean check1 = isVehicleAvailable(vehicle, vehicleNum, tripStartString);
		boolean check2 = isDriverAvailable(driverUsername, tripStartString);
		boolean check3 = isDriver(driverUsername);
		if(check1 && check2 && check3)
			tripList.add(source, destination, tripType, tripStops, driverUsername,
					vehicle, vehicleNum, distance, tripStartString);
		else
			tripList.getTrip(-1);
		return tripList.getTrip(Trip.getTripNum()-1);
	}
	public void removeTrip(int tripID) {
		tripList.getTrip(tripID);
		if(isTripDeletable(tripID))
			tripList.remove(tripID);
	}
	public boolean isTripDeletable(int tripID) {
		ArrayList<Ticket> myList = ticketList.getTicketList();
		for(Ticket t:myList) {
			if(t.getTripID() == tripID)
				return false;
		}
		return true;
	}
	public Manager addManager(String firstName, String lastName, String userName, String password, int age) {
		personList.addManager(firstName, lastName, userName, password, age);
		return (Manager)personList.getPerson(userName);
	}
	public Customer addCustomer(String firstName, String lastName, String userName, String password, int age) {
		personList.addCustomer(firstName, lastName, userName, password, age);
		return (Customer)personList.getPerson(userName);
	}
	public boolean removeManger(String managerUsername) {
		personList.getPerson(managerUsername);
		if(isManager(managerUsername)) {
			personList.remove(managerUsername);
			return true;
		}
		return false;
	}
	public Driver addDriver(String firstName, String lastName, String userName, String password, int age) {
		personList.addDriver(firstName, lastName, userName, password, age);
		return (Driver)personList.getPerson(userName);
	}
	public void removerDriver(String driverUsername) {
		personList.getPerson(driverUsername);
		boolean flag = false;
		try {
			@SuppressWarnings("unused")
			String n = driverSchedule(driverUsername)[0];
		} catch(Exception e) {
			flag = true;
		}
		if(flag)
			personList.remove(driverUsername);
		else
			tripList.getTrip(-1);
	}
	public boolean customerLogIn(String username, String password) {
		if(personList.getPerson(username).getPassword().equals(password) && personList.getPerson(username) instanceof Customer) {
			setLastCustomerLoggedIn(username);
			return true;
		}
		return false;
	}
	public boolean driverLogIn(String username, String password) {
		if(personList.getPerson(username).getPassword().equals(password) && personList.getPerson(username) instanceof Driver) {
			setLastDriverLoggedIn(username);
			return true;
		}
		return false;
	}
	public boolean managerLogIn(String username, String password) {
		if(personList.getPerson(username).getPassword().equals(password) && personList.getPerson(username) instanceof Manager) {
			return true;
		}
		return false;
	}
	public void setLastCustomerLoggedIn(String LastCustomerLoggedIn) {
		this.LastCustomerLoggedIn = LastCustomerLoggedIn;
	}
	public String getLastCustomerLoggedIn() {
		return this.LastCustomerLoggedIn;
	}
	public void setLastDriverLoggedIn(String LastDriverLoggedIn) {
		this.LastDriverLoggedIn = LastDriverLoggedIn;
	}
	public String getLastDriverLoggedIn() {
		return this.LastDriverLoggedIn;
	}
	public Ticket addTicket(int tripID, String username, String ticketType) {
		ticketList.add(tripID, username, ticketType);
		return ticketList.getTicket(Ticket.getTicketNum()-1);
	}
	public void removeTicket(int ticketID) { 
		ticketList.getTicket(ticketID);
		ticketList.remove(ticketID);
	}
	public void bookTicket(String username, int tripID, String ticketType) {
		Customer c = (Customer)personList.getPerson(username);
		int check = -1;
		if(ticketType.replaceAll(" ", "").toLowerCase().equals("roundtrip"))
			check = isTripRound(tripID);
		if(check != -1) {
			c.bookTicket(tripID, "one way");
			c.bookTicket(check, "one way");
		}	
		else {
			c.bookTicket(tripID, "one way");
		}
	}
	public void refundTicket(String username, int tripID) {
		tripList.getTrip(tripID);
		int ticketID = ticketList.getTicketID(username, tripID);
		removeTicket(ticketID);
	}
	public boolean addPassenger(String vehicleType, int vehicleNum) {
		return vehicleList.addPassenger(vehicleType, vehicleNum);
	}
	public void removerPassenger(String vehicleType, int vehicleNum) {
		vehicleList.removerPassenger(vehicleType, vehicleNum);
	}
	public String[] driverSchedule(String driverUsername){
		ArrayList<Trip> schedule = new ArrayList<Trip>();
		ArrayList<Trip> temp = tripList.getTripList();
		for(Trip t:temp) {
			if(t.getDriverUsername().equals(driverUsername)) {
				schedule.add(t);
			}
		}
		return splitArraylist(schedule);
	}
	public String[] splitArraylist(ArrayList<Trip> al){
		int index0, i=0;
		index0 = al.size();
		String[] res = new String[index0];
		for(Trip t:al) {
			res[i++] = t.toString();
		}
		return res;
	}
	public boolean isVehicleAvailable(String vehicle, int vehicleNum, String tripStartString) throws ParseException {
		vehicleList.getVehicle(vehicle, vehicleNum);
		Date tripStart = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("H:d/M/y");
		tripStart = dateFormat.parse(tripStartString);
		ArrayList<Trip> myList = tripList.getTripList();
		for(Trip t:myList) { 
			if(tripStart.before(t.getTripEnd()) && tripStart.after(t.getTripStart()) 
					&& t.getVehicle().equals(vehicle) && t.getVehicleNum()==vehicleNum || tripStart.equals(t.getTripStart())) {
				return false;
			}
		}
		return true;
	}
	public boolean isDriverAvailable(String driverUsername, String tripStartString) throws ParseException {
		personList.getPerson(driverUsername);
		Date tripStart = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("H:d/M/y");
		tripStart = dateFormat.parse(tripStartString);
		ArrayList<Trip> myList = tripList.getTripList();
		for(Trip t:myList) { 
			if(tripStart.before(t.getTripEnd()) && tripStart.after(t.getTripStart()) 
					&& t.getDriverUsername().equals(driverUsername) || tripStart.equals(t.getTripStart())) {
				return false;
			}
		}
		return true;
	}
	public boolean isDriver(String driverUsername) {
		return personList.isDriver(driverUsername);
	}
	public boolean isManager(String managerUsername) {
		return personList.isManager(managerUsername);
	}
	public int isTripRound(int tripID) {
		return tripList.isTripRound(tripID);
	}
	public void updateTrips() {
		tripList.updateTrips();
	}
	public double getTripPrice(int tripID) {
		return tripList.getTripPrice(tripID);
	}
}
