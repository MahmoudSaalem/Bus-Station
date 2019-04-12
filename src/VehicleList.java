import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VehicleList implements Serializable {

	private ArrayList<Vehicle> vehicleList = new ArrayList<>();
	
	public void addBus() {
		vehicleList.add(new Bus());
		Bus.setCarNum(Bus.getCarNum()+1);
	}
	public void addMiniBus() {
		vehicleList.add(new MiniBus());
		MiniBus.setCarNum(MiniBus.getCarNum()+1);
	}
	public void addLimousine() {
		vehicleList.add(new Limousine());
		Limousine.setCarNum(Limousine.getCarNum()+1);
	}
	public int remove(String type, int num) {
		int index = getIndex(type, num);
		try {
			vehicleList.remove(index);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}
	public int getIndex(String type, int num) {
		int i = 0;
		switch(type.replace(" ", "").toLowerCase()) {
		case "bus":
			for(Vehicle v:vehicleList) {
				if(v instanceof Bus && ((Bus)v).getBusNum()==num)
					return i;
				i++;
			}
		case "minibus":
			for(Vehicle v:vehicleList) {
				if(v instanceof MiniBus && ((MiniBus)v).getMiniBusNum()==num)
					return i;
				i++;
			}
		case "limousine":
			for(Vehicle v:vehicleList) {
				if(v instanceof Limousine && ((Limousine)v).getLimousineNum()==num)
					return i;
				i++;
			}
		}
		return -1;
	}
	public int getSize() {
		return vehicleList.size();
	}
	public void printData() {
		for(Vehicle v:vehicleList)
			System.out.println(v);
	}
	public boolean addPassenger(String vehicleType, int vehicleNum) {
		int index = getIndex(vehicleType, vehicleNum);
		int capacity = vehicleList.get(index).getCapacity();
		int maxCapacity = vehicleList.get(index).getMaxCapacity();
		if(capacity<maxCapacity) {
			vehicleList.get(index).setCapacity(capacity+1);
			return true;
		} else { 
			return false;
		}
	}
	public void removerPassenger(String vehicleType, int vehicleNum) {
		int index = getIndex(vehicleType, vehicleNum);
		int capacity = vehicleList.get(index).getCapacity();
		vehicleList.get(index).setCapacity(capacity-1);
	}
	public Vehicle getVehicle(String vehicleType, int vehicleNum) {
		int index = getIndex(vehicleType, vehicleNum);
		return vehicleList.get(index);
	}
	public String getData(int index) {
		return vehicleList.get(index).toString();
	}
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
}
