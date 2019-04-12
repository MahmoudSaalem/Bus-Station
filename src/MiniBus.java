import java.io.Serializable;

@SuppressWarnings("serial")
public class MiniBus extends Vehicle implements Serializable{
	
	private static int carNum = 1;
	private final int miniBusNum = carNum;
	{
		setMaxCapacity(14);
		setVehicleType("MiniBus");
	}

	public static int getCarNum() {
		return carNum;
	}
	public static void setCarNum(int carNum) {
		MiniBus.carNum = carNum;
	}
	public int getMiniBusNum() {
		return miniBusNum;
	}
	@Override
	public String toString() {
		return "MiniBus," + miniBusNum + "," + getMaxCapacity();
	}
}
