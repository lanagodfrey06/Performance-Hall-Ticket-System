import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<LiveEvent> eventStock = new ArrayList<>();
// store events in a list
    public Admin(String userId, String username, String name, int houseNo, String postcode, String city) {
        super(userId, username, name, houseNo, postcode, city);
    }

    public boolean addEvent(LiveEvent event, String type, String additionalInfo) {
        if (eventStock.stream().anyMatch(e -> e.getEventID() == event.getEventID())) {
            return false; // Duplicate ID
        }
        eventStock.add(event);
        // Use FileHandler to write to file
        return FileHandler.writeEventToStock(event, type, additionalInfo);
    }

    public List<LiveEvent> getEventStock() {
        return eventStock;
    }

    @Override
    public void display() {
        System.out.println("Admin Options:\n1. View Events\n2. Add Event\n");
    }
}