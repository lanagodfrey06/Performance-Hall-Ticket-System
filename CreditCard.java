public class CreditCard implements PaymentMethod{
	private String cardNumber; //6 digits
	private String securityCode; //3 digits
	
	CreditCard(String cardNumber, String securityCode){
		if(cardNumber.length() !=6 || securityCode.length() !=3) {
			throw new IllegalArgumentException("Invalid card details!");
		}
		
		this.cardNumber=cardNumber;
		this.securityCode=securityCode;
	}
	
	public Receipt processPayment(double amount, Address fullAddress) {
		String receiptText= String.format("%.2f paid by Credit Card using %s on %s, and the billing address is %s",
				amount, maskCardNumber(), java.time.LocalDate.now(), fullAddress.toString());
	
	return new Receipt(receiptText);
	
}
private String maskCardNumber() {
	return "****" +cardNumber.substring(2);

}

}
