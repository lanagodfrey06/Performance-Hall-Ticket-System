public class PayPal implements PaymentMethod {
	private String emailAddress;
	
	public PayPal(String emailAddress) {
		if(!emailAddress.contains("@")) {
			throw new IllegalArgumentException("Invalid PayPal email!);");
		}
		this.emailAddress=emailAddress;
	}
	
	public Receipt processPayment(double amount, Address fullAddress) {
		String receiptText= String.format("%.2f paid by via PayPal using %s on %s, and the billing address is %s",
				amount, emailAddress, java.time.LocalDate.now(), fullAddress.toString());
	

	return new Receipt(receiptText);
}
}
