
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String USER_FILE = "UserAccounts.txt";
    private static final String STOCK_FILE = "Stock.txt";

      public static List<User> loadUsers() {
        List<User> users;
        users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String id = parts[0].trim();
                    String username = parts[1].trim();
                    String name = parts[2].trim();
                    int houseNo = Integer.parseInt(parts[3].trim());
                    String postcode = parts[4].trim();
                    String city = parts[5].trim();
                    String role = parts[6].trim().toLowerCase();

                    if (role.equals("admin")) {
                        users.add(new Admin(id, username, name, houseNo, postcode, city));
                    } else if (role.equals("customer")) {
                        users.add(new Customer(id, username, name, houseNo, postcode, city));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read user file: " + e.getMessage());
        }
        return users;
    }

    public static List<LiveEvent> loadEvents() {
        List<LiveEvent> events;
        events = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(STOCK_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    
                    int id = Integer.parseInt(parts[0].trim());
                    String category = parts[1].trim();
                    String type = parts[2].trim();
                    String name = parts[3].trim();
                    AgeRestrictionCategory restriction = AgeRestrictionCategory.valueOf(parts[4].trim().toUpperCase());
                    int quantity = Integer.parseInt(parts[5].trim());
                    double fee = Double.parseDouble(parts[6].trim());
                    double price = Double.parseDouble(parts[7].trim());
                    String extra = parts[8].trim();

                    LiveEvent event = EventFactory.createEvent(id, name, restriction, quantity, fee, price, type, extra);
                    events.add(event);
                }
            }
        }  catch (IOException e) {
            System.err.println("Failed to read stock file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid stock format: " + e.getMessage());
        }
        return events;
    }
  public static boolean writeEventToStock(LiveEvent event, String eventType, String extra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STOCK_FILE, true))) {
            String line = String.format("%d,%s,%s,%s,%s,%d,%.2f,%.2f,%s",
                    event.getEventID(),
                    event.getEventCategory(),
                    eventType,
                    event.getEventName(),
                    event.getRestriction(),
                    event.getQuantityInStock(),
                    event.getPerformanceFee(),
                    event.getTicketPrice(),
                    extra
            );
            writer.newLine();
            writer.write(line);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to write to stock: " + e.getMessage());
            return false;
        }
    }
}


