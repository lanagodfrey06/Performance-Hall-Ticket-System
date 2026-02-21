public class StandUpComedy extends LiveEvent{
	private String language;
	public StandUpComedy(int eventID, String eventName, AgeRestrictionCategory restriction, int quantityInStock, double performanceFee, double ticketPrice, String language) {
		super(eventID,eventName,restriction,quantityInStock, performanceFee, ticketPrice, LiveEventCategory.PERFORMANCE);
		this.language=language;
	}
	public String getLanguage() {
		return language;
	}
	public String toString() {
		 return String.format("Stand Up Comedy: %s | ID: %d | Language: %d | Price: %.2f | Tickets: %d", 
	                getEventName(), getEventID(), language, getTicketPrice(), getQuantityInStock());
	}
}
