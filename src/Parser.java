import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Parser implements Serializable {

	Adminstration a = Adminstration.getInstance();
	
	public void filesCheck() {
		
		File vehicleFile = new File("vehicle.ser");
		File personFile = new File("people.ser");
		File tripFile = new File("trip.ser");
		File ticketFile = new File("ticket.ser");
		File IDsFile = new File("IDs.txt");
		
		if(!vehicleFile.exists()) {
			createFile(vehicleFile);
			saveData();
		}
		if(!personFile.exists()) {
			createFile(personFile);
			saveData();
		}
		if(!tripFile.exists()) {
			createFile(tripFile);
			saveData();
		}
		if(!ticketFile.exists()) {
			createFile(ticketFile);
			saveData();
		}
		if(!IDsFile.exists()) {
			createFile(ticketFile);
			saveData();
		}
	}
	public void createFile(File f) {
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveData() {
		exportVehicles();
		exportPeople();
		exportTrips();
		exportTickets();
		
		Formatter x;
		try {
			x = new Formatter("IDs.txt");
			x.format("%s %s %s %s %s", String.valueOf(Bus.getCarNum()), String.valueOf(MiniBus.getCarNum()), 
					String.valueOf(Limousine.getCarNum()), String.valueOf(Trip.getTripNum()),
					String.valueOf(Ticket.getTicketNum()));
			x.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void loadData() {
		importVehicles();
		importPeople();
		importTrips();
		importTickets();
		
		Scanner x;
		try {
			x = new Scanner(new File("IDs.txt"));
			while(x.hasNext()) {
				Bus.setCarNum(Integer.parseInt(x.next()));
				MiniBus.setCarNum(Integer.parseInt(x.next()));
				Limousine.setCarNum(Integer.parseInt(x.next()));
				Trip.setTripNum((Integer.parseInt(x.next())));
				Ticket.setTicketNum((Integer.parseInt(x.next())));
			}
			x.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void exportVehicles() {
	    try{
		    FileOutputStream saveFile = new FileOutputStream("vehicle.ser");
		    ObjectOutputStream save = new ObjectOutputStream(saveFile);
	    	save.writeObject(a.vehicleList.getVehicleList());
	    	saveFile.close();
		    save.close();
	    }
	    catch(Exception exc){
	    	exc.printStackTrace();
	    }
	} 
	@SuppressWarnings("unchecked")
	public void importVehicles() {
	    try{
		    FileInputStream saveFile = new FileInputStream("vehicle.ser");
		    ObjectInputStream save = new ObjectInputStream(saveFile);
		    a.vehicleList.setVehicleList((ArrayList<Vehicle>) save.readObject());
		    saveFile.close();
		    save.close();
	    } catch(Exception exc){
	    	exc.printStackTrace();
	    }
	}
	public void exportPeople() {
	    try{
		    FileOutputStream saveFile = new FileOutputStream("people.ser");
		    ObjectOutputStream save = new ObjectOutputStream(saveFile);
	    	save.writeObject(a.personList.getPersonList());
	    	saveFile.close();
		    save.close();
	    }
	    catch(Exception exc){
	    	exc.printStackTrace();
	    }
	} 
	@SuppressWarnings("unchecked")
	public void importPeople() {
	    try{
		    FileInputStream saveFile = new FileInputStream("people.ser");
		    ObjectInputStream save = new ObjectInputStream(saveFile);
		    a.personList.setPersonList((ArrayList<Person>) save.readObject());
		    saveFile.close();
		    save.close();
	    } catch(Exception exc){
	    	exc.printStackTrace();
	    }
	}
	public void exportTrips() {
	    try{
		    FileOutputStream saveFile = new FileOutputStream("trip.ser");
		    ObjectOutputStream save = new ObjectOutputStream(saveFile);
	    	save.writeObject(a.tripList.getTripList());
	    	saveFile.close();
		    save.close();
	    }
	    catch(Exception exc){
	    	exc.printStackTrace();
	    }
	} 
	@SuppressWarnings("unchecked")
	public void importTrips() {
	    try{
		    FileInputStream saveFile = new FileInputStream("trip.ser");
		    ObjectInputStream save = new ObjectInputStream(saveFile);
		    a.tripList.setTripList((ArrayList<Trip>) save.readObject());
		    saveFile.close();
		    save.close();
	    } catch(Exception exc){
	    	exc.printStackTrace();
	    }
	}
	public void exportTickets() {
	    try{
		    FileOutputStream saveFile = new FileOutputStream("ticket.ser");
		    ObjectOutputStream save = new ObjectOutputStream(saveFile);
	    	save.writeObject(a.ticketList.getTicketList());
	    	saveFile.close();
		    save.close();
	    }
	    catch(Exception exc){
	    	exc.printStackTrace();
	    }
	} 
	@SuppressWarnings("unchecked")
	public void importTickets() {
	    try{
		    FileInputStream saveFile = new FileInputStream("ticket.ser");
		    ObjectInputStream save = new ObjectInputStream(saveFile);
		    a.ticketList.setTicketList((ArrayList<Ticket>)save.readObject());
		    saveFile.close();
		    save.close();
	    } catch(Exception exc){
	    	exc.printStackTrace();
	    }
	}
}