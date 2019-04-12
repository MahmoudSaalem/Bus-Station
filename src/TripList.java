import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class TripList implements Serializable { 

	private ArrayList<Trip> tripList = new ArrayList<>();
	
	public void add(String source, String destination, String tripType, String tripStops, String driverUsername,
			String vehicle, int vehicleNum, double distance, String tripStartString) throws ParseException {
		tripList.add(new Trip(source, destination, tripType, tripStops, driverUsername,
				vehicle, vehicleNum, distance, tripStartString));
		Trip.setTripNum(Trip.getTripNum()+1);
	}
	public int remove(int tripID) {
		try {
			tripList.remove(getIndex(tripID));
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}
	public int getIndex(int tripID) {
		int i = 0;
		for(Trip x:tripList) {
			if(x.getTripID()==tripID)
				return i;
			else
				i++;
		}
		return -1;
	}
	public int isTripRound(int tripID) {
		updateTrips();
		Trip trip = getTrip(tripID);
		for(Trip t:tripList)
			if(trip.getSource().equals(t.getDestination()) && trip.getDestination().equals(t.getSource()))
				return t.getTripID();
		return -1;
	}
	public void updateTrips() {
		Date thisDate = new Date();
		int size = tripList.size();
		for(int i=0; i<size; i++) {
			if(tripList.get(i).getTripStart().before(thisDate)) {
				tripList.remove(i);
				i--;
				size--;
			}
		}
	}
	public int getSize() {
		return tripList.size();
	}
	public void printData() {
		for(Trip t:tripList)
			System.out.println(t);
	}
	public Trip getTrip(int tripID) {
		int index = getIndex(tripID);
		return tripList.get(index);
	}
	public String getVehicleType(int tripID) {
		int index = getIndex(tripID);
		return tripList.get(index).getVehicle();
	}
	public int getVehicleNum(int tripID) {
		int index = getIndex(tripID);
		return tripList.get(index).getVehicleNum();
	} 
	public String getData(int index) {
		return tripList.get(index).toString();
	}
	public ArrayList<Trip> getTripList() {
		return tripList;
	}
	public void setTripList(ArrayList<Trip> tripList) {
		this.tripList = tripList;
	}
	public double getTripPrice(int tripID) {
		return getTrip(tripID).getPrice();
	}
}
