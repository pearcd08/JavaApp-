package vetAppPackage;

public class Customer {
	private int customerID;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	
	public Customer(int tempCustomerID, String tempFirstName, String tempLastName, String tempEmailAddress,
			String tempPassword) {
		this.customerID = tempCustomerID;
		this.firstName = tempFirstName;
		this.lastName = tempLastName;
		this.emailAddress = tempEmailAddress;
		this.password = tempPassword;
		

	}

	// get statements for all the customer details
	public int getCustomerID() {
		return customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	
	// set statements for entering the customers details
	public void setFirstName(String tempFirstName) {
		this.firstName = tempFirstName;

	}

	public void setLastName(String tempLastName) {
		this.lastName = tempLastName;

	}

	// make email validation checker
	public void setEmailAddress(String tempEmailAddress) {
		this.emailAddress = tempEmailAddress;

	}

	public void setPassword(String tempPassword) {
		if (tempPassword.length() > 6)
			this.password = tempPassword;
		else {
			System.out.println("Password must contain atleast 6 characters");

		}
	}

	





}
