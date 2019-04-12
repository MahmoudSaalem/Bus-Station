import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Ticket implements Serializable { 
	
	private double price;
	private int tripID;
	private static int ticketNum = 1;
	private final int ticketID = ticketNum;
	private String username;
	private String ticketType;
	private String vehicleType;
	private int vehicleNum;
	private Date tripStart = null;
	private Date tripEnd = null;

	private transient Adminstration a = Adminstration.getInstance();
	SimpleDateFormat dateForm = new SimpleDateFormat("H:d/M/y");
	
	public Ticket() {
		
	}
	public Ticket(int tripID, String username, String ticketType) {
		super();
		this.tripID = tripID;
		this.username = username;
		this.ticketType = ticketType;
		setPrice();
		setVariables();
	}
	public int getTripID() {
		return tripID;
	}
	public void setTripID(int tripID) {
		this.tripID = tripID;
	}
	public static int getTicketNum() {
		return ticketNum;
	}
	public static void setTicketNum(int ticketNum) {
		Ticket.ticketNum = ticketNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTicketID() {
		return ticketID;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public void setPrice() {
		Trip t = a.tripList.getTrip(tripID);
		String tripType = t.getTripType();
		String tripStops = t.getTripStops();
		String vehicleType = t.getVehicle();
		this.price = ticketType.replaceAll(" ", "").toLowerCase().equals("oneway") ?  t.getDistance()/5 : t.getDistance()*1.8/5;
		this.price = tripType.replaceAll(" ", "").toLowerCase().equals("internal") ? this.price*2 : this.price*2.5;
		switch(tripStops.replace(" ", "").toLowerCase()) {
		case "one":
			this.price *= 0.9;
			break;
		case "many":
			this.price *= 0.75;
			break;
		default:
			break;
		}
		switch(vehicleType.replace(" ", "").toLowerCase()) {
		case "bus":
			break;
		case "minibus":
			this.price *= 0.95;
			break;
		default:
			this.price *= 1.2;
			break;
		}
	}
	public void setVariables() {
		Trip t = a.tripList.getTrip(tripID);
		this.vehicleType = t.getVehicle();
		this.vehicleNum = t.getVehicleNum();
		this.tripStart = t.getTripStart();
		this.tripEnd = t.getTripEnd();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return tripID + "," + ticketID + "," + price + "," + username
				+ "," + vehicleType + "," + vehicleNum + "," + dateForm.format(tripStart)
				+ "," + dateForm.format(tripEnd);
	}
}
