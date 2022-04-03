package vetAppPackage;

import java.util.Scanner;

public class Vet {

	private int vetID;
	private String vetFirstName;
	private String vetLastName;
	private String specialty;
	
	// array to store appointments

	public Vet(int tempVetID, String tempVetFirstName, String tempVetLastName, String tempSpecialty) {
		this.vetID = tempVetID;
		this.vetFirstName = tempVetFirstName;
		this.vetLastName = tempVetLastName;
		this.specialty = tempSpecialty;

	}
	
	

	// get statements
	public int getVetID() {
		return vetID;
	}

	public String getVetFirstName() {
		return vetFirstName;
	}

	public String getVetLastName() {
		return vetLastName;
	}

	public String getSpecialty() {
		return specialty;
	}

	// set statements
	public void setVetID(int tempVetID) {
		this.vetID = tempVetID;
	}

	public void setVetFirstName(String tempVetFirstName) {
		this.vetFirstName = tempVetFirstName;
	}

	public void setVetLastName(String tempVetLastName) {
		this.vetLastName = tempVetLastName;
	}

	public void setSpeciality(String tempSpecialty) {
		if (tempSpecialty.equals("Bird") || tempSpecialty.equals("Cat") || tempSpecialty.equals("Dog")) {
			this.specialty = tempSpecialty;
		} else {
			System.out.println("Error: Specialty can only be Bird, Cat or Dog");
		}

	}

	public void addNewVet() {
		Scanner vetScanner = new Scanner(System.in);
		System.out.println("Enter First Name: ");
		setVetFirstName(vetScanner.nextLine());
		System.out.println("Enter Last Name: ");
		setVetFirstName(vetScanner.nextLine());
		System.out.println("Enter Speciality: ");
		setVetFirstName(vetScanner.nextLine());
		vetScanner.close();

	}
	
	//see if vet is available for booking
	public boolean checkVetTime(int vetID, String day, String time) {
		Database db = new Database();
		for (int i = 0; i < db.appointmentList.size(); i++) {
			if (db.appointmentList.get(i).getVetID() == vetID && db.appointmentList.get(i).getDay().equals(day)
					&& db.appointmentList.get(i).getTime().equals(time)) {
				return false;
			}

		}
		return true;

	}


	public void printVets() {
		Database db = new Database();

		System.out.printf("ID", "First Name", "Last Name", "Specialty");
		for (Vet vet : db.vetList) {
			System.out.println(
					vet.getVetID() + " " + vet.getVetFirstName() + " " + vet.getVetFirstName() + vet.getSpecialty());
		}
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.loadDatabase();
		
		System.out.printf("ID\tFirst Name\tLast Name\tSpecialty");
		for (Vet vet : db.vetList) {
			System.out.println("\n" + vet.getVetID() + "\t" + vet.getVetFirstName() + "\t" + vet.getVetLastName() + "\t"
					+ vet.getSpecialty());
		}

		
		 for(int i = 0;i< db.vetList.size(); i++){ //add each element of the splits array to the myColumns ArrayList
	            if(db.vetList.get(i).getVetID() == 1) {
	            	db.vetList.remove(i);
	            	}
	            }
	            	
		System.out.printf("ID\tFirst Name\tLast Name\tSpecialty");
		for (Vet vet : db.vetList) {
			System.out.println("\n" + vet.getVetID() + "\t" + vet.getVetFirstName() + "\t" + vet.getVetLastName() + "\t"
					+ vet.getSpecialty());
		}

	}

}
