
public class EventFactory {
	 public static LiveEvent createEvent(int id, String name, AgeRestrictionCategory restriction,
             int quantity, double fee, double price,
             String type, String additional) throws IllegalArgumentException {
		 switch (type.toLowerCase()) {
         case "live concert":
             return new LiveConcert(id, name, restriction, quantity, fee, price, Integer.parseInt(additional));
         case "dj set":
             return new DjSet(id, name, restriction, quantity, fee, price, Integer.parseInt(additional));
         case "theatre":
             return new Theatre(id, name, restriction, quantity, fee, price, additional);
         case "stand-up comedy":
             return new StandUpComedy(id, name, restriction, quantity, fee, price, additional);
         case "magic":
             return new MagicShow(id, name, restriction, quantity, fee, price, additional);
         default:
             throw new IllegalArgumentException("Unsupported event type.");
     }
 }
}

