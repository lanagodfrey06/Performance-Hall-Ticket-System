
    public abstract class User {
	private String userID;
	private String username;
	private String name;
	private int houseNo;
	private String postcode;
	private String city;
	
	public User(String userId, String username, String name, int houseNo, String postcode, String city) {
		this.userID=userId;
		this.username=username;
		this.name=name;
		this.houseNo=houseNo;
		this.postcode=postcode;
		this.city=city;
	}
	public String getUsername() {
		return username;
		
	}
	public String getName() {
		return name;
		
	}
	public String getFullAddress() {
		return houseNo+", "+postcode+", "+city;
	}
	public abstract void display();


public String toString() {
    return "ID: " + userID + ", Username: " + username + ", Name: " + name +
           ", House No: " + houseNo + ", Postcode: " + postcode +
           ", City: " + city + ", Role: " + getClass().getSimpleName();
}
}

