package Class;

public class BookTicket {
	private String departDate;
	private String departStation;
	private String arrive;
	private String seatType;
	private String ticketAmount;
	 
	public BookTicket(String departDate, String departStation, String arrive, String seatType, String ticketAmount) {
		this.departDate = departDate;
		this.departStation = departStation;
		this.arrive = arrive;
		this.seatType = seatType;
		this.ticketAmount = ticketAmount;
	}
	
	public BookTicket() {
	}
	 
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public String getDepartStation() {
		return departStation;
	}
	public void setDepartStation(String departStation) {
		this.departStation = departStation;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
}
