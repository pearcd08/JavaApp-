//Database handles all the records of the Vet App, will read write text files and store in arraylists. 
package vetAppPackage;

import java.io.*;

import java.util.ArrayList;

//convert to io file system, access txt files. 

public class Database {

	File customerTxt = new File("Customers.txt");
	File petTxt = new File("Pets.txt");
	File vetTxt = new File("Vets.txt");
	File appointmentTxt = new File("Appointments.txt");
	// BufferedReader input = null;
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<Vet> vetList = new ArrayList<Vet>();
	ArrayList<Pet> petList = new ArrayList<Pet>();
	ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();

	public Database() {

	}

	public void loadDatabase() {
		loadCustomers();
		loadPets();
		loadVets();
		loadAppointments();
	}

	// read the customers text file to fill the customerList
	public void loadCustomers() {
		try {
			BufferedReader customerReader = new BufferedReader(new FileReader(customerTxt));

			String customerLine;
			while ((customerLine = customerReader.readLine()) != null) {
				String[] customerVar = customerLine.split(",");// split the variables of each line by the comma sign
				int id = Integer.parseInt(customerVar[0]); // convert the first split [0] from string to integer.
				customerList.add(new Customer(id, customerVar[1], customerVar[2], customerVar[3], customerVar[4]));
			}
			customerReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadPets() {
		try {
			BufferedReader petReader = new BufferedReader(new FileReader(petTxt));

			String petLine;
			while ((petLine = petReader.readLine()) != null) {
				String[] petVar = petLine.split(",");// split the variables of each line by the comma sign
				int petID = Integer.parseInt(petVar[0]); // convert the first split [0] from string to integer.
				int age = Integer.parseInt(petVar[3]);
				int customerID = Integer.parseInt(petVar[5]);
				petList.add(new Pet(petID, petVar[1], petVar[2], age, petVar[4], customerID));
			}
			petReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadVets() {
		try {
			BufferedReader vetReader = new BufferedReader(new FileReader(vetTxt));

			String vetLine;
			while ((vetLine = vetReader.readLine()) != null) {
				String[] vetVar = vetLine.split(",");// split the variables of each line by the comma sign
				int vetID = Integer.parseInt(vetVar[0]); // convert the first split [0] from string to integer.
				vetList.add(new Vet(vetID, vetVar[1], vetVar[2], vetVar[3]));
			}
			vetReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadAppointments() {
		try {
			BufferedReader appointmentReader = new BufferedReader(new FileReader(appointmentTxt));

			String appointmentLine;
			while ((appointmentLine = appointmentReader.readLine()) != null) {
				String[] aptVar = appointmentLine.split(",");// split the variables of each line by the comma sign
				int aptID = Integer.parseInt(aptVar[0]);
				int customerID = Integer.parseInt(aptVar[1]);
				int petID = Integer.parseInt(aptVar[2]);
				int vetID = Integer.parseInt(aptVar[3]);
				// convert the first split [0] from string to integer.
				appointmentList.add(new Appointment(aptID, customerID, petID, vetID, aptVar[4], aptVar[5], aptVar[6]));
			}

			appointmentReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeAppointments() {

		File file2 = new File("TempApt.txt");
		StringBuilder sb = new StringBuilder();
		if (appointmentTxt.delete()) {
			System.out.print("File deleted");
		}

		for (int i = 0; i < appointmentList.size(); i++) {
			Appointment apt = appointmentList.get(i);
			sb.append(apt.getAppointmentID() + "," + apt.getCustomerID() + "," + apt.getPetID() + "," + apt.getVetID()
					+ "," + apt.getDay() + "," + apt.getTime() + "," + apt.getNotes() + "\r\n");
		}

		try {
			PrintStream writer = new PrintStream(new FileOutputStream(file2));
			{
				writer.print(sb.toString());
			}
			writer.close();

			file2.renameTo(appointmentTxt);

		} catch (Exception e) {
		}
	}

	// access the people class

}
