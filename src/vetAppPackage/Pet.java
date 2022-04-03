package vetAppPackage;
//give last name that the customer has to the pet
// attach pet to customer ID

public class Pet {

	private int petID; // unique identifier for pet
	private String petFirstName;	
	private String petType; // either cat, dog, bird
	private int petAge;
	private String petGender; // either male of female
	private int customerID; // customerID of the owner
	Database db = new Database();
	
	public Pet(int tempPetID, String tempPetName, String tempPetType, int tempPetAge, String tempPetGender,
			int tempCustomerID) {

		this.petID = tempPetID;
		this.petFirstName = tempPetName;
		// this.petLastName = tempPetLName;
		this.petType = tempPetType;
		this.petAge = tempPetAge;
		this.petGender = tempPetGender;
		this.customerID = tempCustomerID;

	}

	// set statements for Pet
	public int getPetID() {
		return petID;
	}

	public String getPetName() {
		return petFirstName;
	}

	public String getPetType() {
		return petType;
	}

	public int getPetAge() {
		return petAge;
	}

	public String getPetGender() {
		return petGender;
	}

	public int getCustomerID() {
		return customerID;
	}

	// get statements for Pet
	public void setPetID(int tempPetID) {
		this.petID = tempPetID;

	}

	public void setPetName(String tempPetName) {
		this.petFirstName = tempPetName;
	}
	// only bird, cat, dog can be set as petType
	public void setPetType(String tempPetType) {
		if (tempPetType.equals("Bird") || tempPetType.equals("Cat") || tempPetType.equals("Dog")) {
			this.petType = tempPetType;
		} else {
			System.out.println("Error: Pet Type can only be Bird, Cat or Dog");
		}

	}

	public void setPetAge(int tempPetAge) {
		this.petAge = tempPetAge; // make if statement for checking if digit
	}

	public void setGender(String tempPetGender) {
		if (tempPetGender.equals("Female") || tempPetGender.equals("Male")) {
			this.petGender = tempPetGender;
		} else {
			System.out.println("Error: Pet Gender can only be Female or Male");
		}
	}
	
	// check if the id exists in the database
	public void setCustomerID(int tempCustomerID) {	
		
		for (int i = 0; i < db.customerList.size(); i++) {
			if (db.customerList.get(i).getCustomerID() == (tempCustomerID)) {
				this.customerID = tempCustomerID;
			}
			else {
				System.out.println("Error: Owner ID does not match any CustomerID in the system");
			}
		}
	}
}
