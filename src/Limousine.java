import java.io.Serializable;

@SuppressWarnings("serial")
public class Limousine extends Vehicle implements Serializable{
	
	private static int carNum = 1;
	private final int limousineNum = carNum;
	{
		setMaxCapacity(1);
		setVehicleType("Limousine");
	}

	public static int getCarNum() {
		return carNum;
	}
	public static void setCarNum(int carNum) {
		Limousine.carNum = carNum;
	}
	public int getLimousineNum() {
		return limousineNum;
	}
	@Override
	public String toString() {
		return "Limousine," + limousineNum + "," + getMaxCapacity();
	}
}
