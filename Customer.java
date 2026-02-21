import java.util.ArrayList;
import java.util.List;
public class Customer extends User
{
	
	//private List<LiveEvent> eventStock;
	private List<BasketItem> shoppingBasket=new ArrayList<>();
	public Customer(String userId, String username, String name, int houseNo, String postcode, String city) {
		super(userId,username,name,houseNo,postcode,city);
		//this.eventStock=eventStock;
		this.shoppingBasket=new ArrayList<>();
	}
	public void display() {
		 System.out.println("Customer Options:\n1. View Events\n2. Add to Basket\n3. View Basket\n4. Checkout\n");
		
	}
	public void viewEvents() {
		
	}
//	public List<LiveEvent> getBasket() { return shoppingBasket; }

	 public void addToBasket(LiveEvent event, int qty) {
        shoppingBasket.add(new BasketItem(event, qty));
    }
	public void viewBasket() {
		
	}

	
	public void cancelBasket() {
		shoppingBasket.clear();

}
 public List<BasketItem> getBasket() {
        return 	shoppingBasket;
    }

	public double getBasketTotal() {
        return shoppingBasket.stream().mapToDouble(item -> item.qty * item.event.getTicketPrice()).sum();
    }
	public boolean payForBasket(String method) {
        // Simulate payment logic
        if (shoppingBasket.isEmpty()) return false;
        // ...add real payment logic here...
        shoppingBasket.clear();
        return true;
    }
	public Receipt payForBasket(PaymentMethod method, Address address) {
    double total = getBasketTotal();
    if (shoppingBasket.isEmpty()) return null;
    Receipt receipt = method.processPayment(total, address);
    shoppingBasket.clear();
    return receipt;
}
}

