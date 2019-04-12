import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class TicketList implements Serializable {

	private ArrayList<Ticket> ticketList = new ArrayList<>();
	
	public void add(int tripID, String username, String ticketType) {
		ticketList.add(new Ticket(tripID, username, ticketType));
		Ticket.setTicketNum(Ticket.getTicketNum()+1);
	}
	public int remove(int ticketID) {
		try {
			ticketList.remove(getIndex(ticketID));
			return 1;
		} catch(Exception e) { 
			return 0;
		}
	}
	public int getIndex(int ticketID) {
		int i = 0;
		for(Ticket t:ticketList) {
			if(t.getTicketID() == ticketID) {
				return i;
			}
			i++;
		}
		return -1;
	}
	public void printData() {
		for(Ticket t:ticketList)
			System.out.println(t);
	}
	public int getSize() {
		return ticketList.size();
	}
	public Ticket getTicket(int ticketID) {
		int index = getIndex(ticketID);
		return ticketList.get(index);
	}
	public int getTicketID(String username, int tripID) {
		for(Ticket t:ticketList) {
			if(t.getUsername().equals(username) && t.getTripID()==tripID)
				return t.getTicketID();
		}
		return -1;
	}
	public String getData(int index) {
		return ticketList.get(index).toString();
	}
	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(ArrayList<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
}
