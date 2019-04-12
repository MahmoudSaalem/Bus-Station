import java.io.Serializable;

@SuppressWarnings("serial")
public class Bus extends Vehicle implements Serializable{
	
	private static int carNum = 1;
	private final int busNum = carNum;
	{
		setMaxCapacity(50);
		setVehicleType("Bus");
	}

	public static int getCarNum() {
		return carNum;
	}
	public static void setCarNum(int carNum) {
		Bus.carNum = carNum;
	}
	public int getBusNum() {
		return busNum;
	}
	@Override
	public String toString() {
		return "Bus," + busNum + "," + getMaxCapacity();
	}

}
