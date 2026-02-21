public class LiveConcert extends LiveEvent {
	private int numberOfBands;
	
	public LiveConcert(int eventID, String eventName, AgeRestrictionCategory restriction, int quantityInStock, double performanceFee, double ticketPrice, int numberOfBands) {
		super(eventID,eventName,restriction,quantityInStock, performanceFee, ticketPrice, LiveEventCategory.MUSIC);
		this.numberOfBands=numberOfBands;
	}
	public int getNumberOfBands() {
		return numberOfBands;
	}
	public String toString() {
		 return String.format("Live Concert: %s | ID: %d | Bands: %d | Price: %.2f | Tickets: %d", 
	                getEventName(), getEventID(), numberOfBands, getTicketPrice(), getQuantityInStock());
	}

}
