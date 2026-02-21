public class DjSet extends LiveEvent{
	private int numberOfDjs;
	
	public DjSet(int eventID, String eventName, AgeRestrictionCategory restriction, int quantityInStock, double performanceFee, double ticketPrice, int numberOfDJs){
		super(eventID,eventName,restriction,quantityInStock, performanceFee, ticketPrice, LiveEventCategory.MUSIC);
		this.numberOfDjs=numberOfDjs;
		
	}
	public int getNumberOfDjs() {
		return numberOfDjs;
	}
	public String toString() {
		 return String.format("DJ Set: %s | ID: %d | DJs: %d | Price: %.2f | Tickets: %d", 
	                getEventName(), getEventID(), numberOfDjs, getTicketPrice(), getQuantityInStock());
	}

	

}
