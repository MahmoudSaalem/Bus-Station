import java.io.Serializable;

@SuppressWarnings("serial")
abstract public class Vehicle implements Serializable{
	
	private int capacity;
	private int maxCapacity;
	private String vehicleType;

	public int getCapacity() {
		return capacity;
	} 
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
