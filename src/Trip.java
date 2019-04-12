import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Trip implements Serializable {
	
	private static int tripNum = 1;
	private final int tripID = tripNum;
	private String source;
	private String destination;
	private String tripType; // internal or external
	private String tripStops; // direct, 1 stop or many stops
	private String driverUsername;
	private String vehicle;
	private String tripStartString;
	private int vehicleNum;
	private double distance;
	private double price;
	private Date tripStart = null;
	private Date tripEnd = null;
	SimpleDateFormat dateForm = new SimpleDateFormat("H:d/M/y");

	public Trip() {
		
	}
	public Trip(String source, String destination, String tripType, String tripStops, String driverUsername,
			String vehicle, int vehicleNum, double distance, String tripStartString) throws ParseException {
		super();
		this.source = format(source);
		this.destination = format(destination);
		this.tripType = tripType;
		this.tripStops = tripStops;
		this.driverUsername = driverUsername;
		this.vehicle = vehicle;
		this.vehicleNum = vehicleNum;
		this.distance = distance;
		this.tripStartString = tripStartString;
		setPrice();
		setDate(tripStartString);
	}
	@SuppressWarnings("deprecation")
	public void setDate(String tripStartString) throws ParseException {
		tripStart = dateForm.parse(tripStartString);
		tripEnd = dateForm.parse(tripStartString);
		tripEnd.setHours((int)(tripEnd.getHours()+distance/100));
	}
	public void setPrice() {
		this.price = distance/5;
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
		switch(vehicle.replace(" ", "").toLowerCase()) {
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getTripStops() {
		return tripStops;
	}
	public void setTripStops(String tripStops) {
		this.tripStops = tripStops;
	}
	public static int getTripNum() {
		return tripNum;
	}
	public static void setTripNum(int tripNum) {
		Trip.tripNum = tripNum;
	}
	public int getTripID() {
		return tripID;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public int getVehicleNum() {
		return vehicleNum;
	}
	public void setVehicleNum(int vehicleNum) {
		this.vehicleNum = vehicleNum;
	}
	public String getDriverUsername() {
		return driverUsername;
	}
	public void setDriverUsername(String driverUsername) {
		this.driverUsername = driverUsername;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Date getTripStart() {
		return tripStart;
	} 
	public void setTripStart(Date tripStart) {
		this.tripStart = tripStart;
	}
	public Date getTripEnd() {
		return tripEnd;
	}
	public void setTripEnd(Date tripEnd) {
		this.tripEnd = tripEnd;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getTripStartString() {
		return tripStartString;
	}
	public void setTripStartString(String tripStartString) {
		this.tripStartString = tripStartString;
	}
	public String format(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return tripID + "," + source + "," + destination + ","
				+ tripType + "," + tripStops + "," + vehicle + "," + vehicleNum
				+ "," + driverUsername + "," + dateForm.format(tripStart) + "," + dateForm.format(tripEnd);
	}
}
