package vetAppPackage;

public class Appointment {
	private int appointmentID;
	private int customerID;
	private int petID;
	private int vetID;
	private String day;
	private String time;
	private String notes;

	public Appointment(int tempAppointmentID, int tempCustomerID, int tempPetID, int tempVetID, String tempDay,
			String tempTime, String tempNotes) {
		this.appointmentID = tempAppointmentID;
		this.customerID = tempCustomerID;
		this.petID = tempPetID;
		this.vetID = tempVetID;
		this.day = tempDay;
		this.time = tempTime;
		this.notes = tempNotes;

	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getPetID() {
		return petID;
	}

	public int getVetID() {
		return vetID;
	}

	public String getDay() {
		return day;
	}

	public String getTime() {
		return time;

	}

	public String getNotes() {
		return notes;
	}

	public void setAppointmentID(int tempAppointmentID) {
		this.appointmentID = tempAppointmentID;

	}

	public void setCustomerID(int tempCustomerID) {
		this.customerID = tempCustomerID;

	}

	public void setPetID(int tempPetID) {
		this.petID = tempPetID;

	}

	public void setVetID(int tempVetID) {
		this.vetID = tempVetID;
	}

	public void setDay(String tempDay) {
		this.day = tempDay;
	}

	public void setTime(String tempTime) {
		this.time = tempTime;
	}

	public void setNotes(String tempNotes) {
		this.notes = tempNotes;
	}
	
	 

}
