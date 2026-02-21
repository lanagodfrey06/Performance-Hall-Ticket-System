public class Address {
	private String houseNo;
	private String postcode;
	private String city;
	
	public Address(String houseNo, String postcode, String city) {
		this.houseNo=houseNo;
		this.postcode=postcode;
		this.city=city;
	}
	public String toString() {
		return houseNo +", "+postcode+", "+city;
		
	}

}
// The Address class represents a physical address with a house number, postcode, and city.
// It provides a constructor to initialize these fields and a toString method to return the address as a formatted string.
// The class is used in the processPayment method of the PaymentMethod interface implementations (CreditCard and PayPal) to include the billing address in the payment receipt.