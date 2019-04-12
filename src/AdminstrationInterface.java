import java.text.ParseException;

public interface AdminstrationInterface {
	
	public Bus addBus();
	public MiniBus addMiniBus();
	public Limousine addLimousine();
	public void removeVehicle(String vehicleType, int carNum);
	public Trip addTrip(String source, String destination, String tripType, String tripStops, String driverUsername,
			String vehicle, int vehicleNum, double distance, String tripStartString) throws ParseException ;
	public void removeTrip(int tripID);
	public Manager addManager(String firstName, String lastName, String userName, String password, int age);
	public Customer addCustomer(String firstName, String lastName, String userName, String password, int age);
	public boolean removeManger(String managerUsername);
	public Driver addDriver(String firstName, String lastName, String userName, String password, int age);
	public void removerDriver(String driverUsername);
	public boolean customerLogIn(String username, String password);
	public boolean driverLogIn(String username, String password);
	public boolean managerLogIn(String username, String password);
	public Ticket addTicket(int tripID, String username, String ticketType);
	public void removeTicket(int ticketID);
	public void bookTicket(String username, int tripID, String ticketType);
	public void refundTicket(String username, int tripID);
	public boolean addPassenger(String vehicleType, int vehicleNum);
	public void removerPassenger(String vehicleType, int vehicleNum);
	public String[] driverSchedule(String driverUsername);
	public void updateTrips();
	public double getTripPrice(int tripID);
}
