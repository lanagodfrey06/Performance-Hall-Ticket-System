public class Theatre extends LiveEvent{
	private String language;
	public Theatre(int eventID, String eventName, AgeRestrictionCategory restriction, int quantityInStock, double performanceFee, double ticketPrice, String language) {
		super(eventID,eventName,restriction,quantityInStock, performanceFee, ticketPrice, LiveEventCategory.PERFORMANCE);
		this.language=language;
	}
	public String getLanguage() {
		return language;
	}
	public String toString() {
		 return String.format("Theatre: %s | ID: %d | Language: %d | Price: %.2f | Tickets: %d", 
	                getEventName(), getEventID(), language, getTicketPrice(), getQuantityInStock());
	}
}
