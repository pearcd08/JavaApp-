package vetAppPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class AddAppointment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String selectedPetType;
	private int petID;
	private int vetID;
	private Database db = new Database(); // links to the database form, where all the arrayLists are stored
	private JComboBox<String> cboPet = new JComboBox<>();
	private JComboBox<String> cboVet = new JComboBox<>();	
	DefaultListModel<String> DefaultComboBoxModel1, DefaultComboBoxModel2;	

	public AddAppointment() {
		setTitle("Book Appointment");

		db.loadDatabase();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 425);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cboPet.setBackground(SystemColor.text);
		cboPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboPet.setBounds(172, 90, 175, 25);
		fillComboPet();
		cboPet.setSelectedIndex(-1);

		// calls the public void to fill in all the pets the currentUser has
		contentPane.add(cboPet);
		cboVet.setBackground(SystemColor.text);
		cboVet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboVet.setSelectedIndex(-1); // show combo box as empty on load

		cboVet.setBounds(172, 126, 175, 25);
		contentPane.add(cboVet);
		// gets vets that have the same specialty as the selected pet's animal type form
		// the pet combo box.

		JLabel lblPet = new JLabel("Select Pet:");
		lblPet.setForeground(new Color(255, 255, 0));
		lblPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPet.setBounds(75, 95, 87, 14);
		contentPane.add(lblPet);

		JLabel lvlVet = new JLabel("Select Vet:");
		lvlVet.setForeground(new Color(255, 255, 0));
		lvlVet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lvlVet.setHorizontalAlignment(SwingConstants.RIGHT);
		lvlVet.setBounds(75, 131, 87, 14);
		contentPane.add(lvlVet);

		JTextArea txtNotes = new JTextArea();
		txtNotes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtNotes.setBounds(172, 162, 175, 50);
		contentPane.add(txtNotes);

		JLabel lblNewLabel = new JLabel("Enter notes:");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(85, 164, 77, 14);
		contentPane.add(lblNewLabel);

		JButton btnSaveAppointment = new JButton("Book");
		btnSaveAppointment.setBackground(new Color(255, 255, 0));
		btnSaveAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnSaveAppointment.setBounds(275, 325, 90, 25);
		contentPane.add(btnSaveAppointment);

		// return to main menu
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(new Color(255, 255, 0));
		btnReturn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnReturn.setBounds(75, 325, 90, 25);
		contentPane.add(btnReturn);

	

		JComboBox<String> cboDay = new JComboBox<String>();
		cboDay.setBackground(SystemColor.text);
		cboDay.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));
		cboDay.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboDay.setBounds(172, 223, 100, 25);
		cboDay.setSelectedIndex(-1);
		contentPane.add(cboDay);

		JComboBox<String> cboTime = new JComboBox<String>();
		cboTime.setBackground(SystemColor.text);
		cboTime.setModel(new DefaultComboBoxModel<String>(
				new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));
		cboTime.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboTime.setBounds(172, 259, 100, 25);
		cboTime.setSelectedIndex(-1);
		contentPane.add(cboTime);

		JLabel lblHour = new JLabel("Select Hour:");
		lblHour.setForeground(new Color(255, 255, 0));
		lblHour.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblHour.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHour.setBounds(75, 264, 87, 14);
		contentPane.add(lblHour);

		JLabel lblDay = new JLabel("Select Day:");
		lblDay.setForeground(new Color(255, 255, 0));
		lblDay.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblDay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDay.setBounds(75, 228, 87, 14);
		contentPane.add(lblDay);

		JLabel lblTitle = new JLabel("Book Appointment");
		lblTitle.setForeground(new Color(255, 255, 0));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setBounds(40, 10, 354, 50);
		contentPane.add(lblTitle);

		cboPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPet = cboPet.getSelectedItem().toString(); // 1. get the string from cboPet
				String selectedPetID = selectedPet.replaceAll("[^0-9]", "");
				int selectedPetID2 = Integer.parseInt(selectedPetID);

				for (int i = 0; i < db.petList.size(); i++)
					if (db.petList.get(i).getPetID() == (selectedPetID2)) {
						selectedPetType = db.petList.get(i).getPetType();
						cboVet.removeAllItems();
						fillComboVet();
					}
			}
		}

		);

		btnSaveAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int newAptID = db.appointmentList.size() + 1;  //1. Declare all the relevant details as variables
				int tempCustomerID = Login.currentUser;
				int tempPetID = getTempPetID();
				int tempVetID = getTempVetID();
				String tempDay = (String) cboDay.getSelectedItem().toString();
				String tempTime = (String) cboTime.getSelectedItem().toString();
				String tempNotes = txtNotes.getText().trim();

				if (tempPetID > 0 && tempVetID > 0 && tempDay.length() > 0 && tempTime.length() > 0         //2. If all the variables are correct
						&& tempNotes.length() > 0 && checkVetTime(tempVetID, tempDay, tempTime) == true) {    //3. And the vet has not bookings at that time
					Appointment apt = new Appointment(newAptID, tempCustomerID, tempPetID, tempVetID, tempDay, tempTime, //4. create a new appointment
							tempNotes);
					db.appointmentList.add(apt); //5. add that appointment to the appointment list
					db.writeAppointments();      //6. call on the database to write the arraylist to text file
					JOptionPane.showMessageDialog(new JFrame(),
							"Appointment Booked! See you at " + tempTime + " on " + tempDay, "Dialog", //7. show message dialog
							JOptionPane.DEFAULT_OPTION);
					MainMenu mframe = new MainMenu();  //8. Open main menu
					mframe.setVisible(true);
					dispose();

				}

				else if (tempPetID > 0 && tempVetID > 0 && tempDay.length() > 0 && tempTime.length() > 0 ///9. if no notes, put "N/A" into the notes 
						&& tempNotes.length() == 0 && checkVetTime(tempVetID, tempDay, tempTime) == true) {
					Appointment apt = new Appointment(newAptID, tempCustomerID, tempPetID, tempVetID, tempDay, tempTime,
							"N/A");
					db.appointmentList.add(apt);
					db.writeAppointments();
					JOptionPane.showMessageDialog(new JFrame(),
							"Appointment Booked! See you at " + tempTime + " on " + tempDay, "Dialog",
							JOptionPane.DEFAULT_OPTION);
					MainMenu mframe = new MainMenu();
					mframe.setVisible(true);
					dispose();

				}

				else if (tempPetID > 0 && tempVetID > 0 && tempDay.length() > 0 && tempTime.length() > 0  //10. A vet is already booked for that slot
						&& checkVetTime(tempVetID, tempDay, tempTime) == false) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Vet is already booked for that time, please choose another day or time ", "Dialog",
							JOptionPane.DEFAULT_OPTION);
					cboDay.requestFocus(); //11. put the cursor on the day list
				}

				else {
					JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields correctly ", "Dialog",
							JOptionPane.DEFAULT_OPTION);

				}

			}
		});

		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mframe = new MainMenu();  //open main menu window
				mframe.setVisible(true);
				dispose();
			}
		});

	}

	public void fillComboPet() {
		for (int i = 0; i < db.petList.size(); i++) // 1. go through the petList line by line
			if (db.petList.get(i).getCustomerID() == Login.currentUser) { // 2. where the customerID in petList matches
																			// the current logged in

				petID = db.petList.get(i).getPetID(); // 3. get the associated pet id
				String petName = db.petList.get(i).getPetName(); // 4. get the associated pet Name
				String petComboString = (petID + ". " + petName); // 5. create a string out of pet id and pet name
				cboPet.addItem(petComboString); // 6. add the created string to the pet combo box

			}
	}

	public void fillComboVet() {

		for (int i = 0; i < db.vetList.size(); i++)
			if (db.vetList.get(i).getSpecialty().equals(selectedPetType)) {
				vetID = db.vetList.get(i).getVetID();
				String vetFName = db.vetList.get(i).getVetFirstName();
				String vetLName = db.vetList.get(i).getVetLastName();
				String vetComboString = (vetID + ". " + vetFName + " " + vetLName);
				cboVet.addItem(vetComboString);

			}

	}

	public int getTempPetID() {

		String selectedPet = cboPet.getSelectedItem().toString();    //1. get the string from cboPet
		String selectedPetID = selectedPet.replaceAll("[^0-9]", ""); //2.extracts just the number out of  the string	
		int tempPetID = Integer.parseInt(selectedPetID);             //3.convert that string int using parseint
		return tempPetID;                                            //4. return the tempPetID

	}

	public int getTempVetID() {

		String selectedVet = cboVet.getSelectedItem().toString();   //1. same as above but with vet
		String selectedVetID = selectedVet.replaceAll("[^0-9]", ""); 												
		int tempVetID = Integer.parseInt(selectedVetID);
		return tempVetID;

	}

	public boolean checkVetTime(int vetID, String day, String time) {
		for (int i = 0; i < db.appointmentList.size(); i++) {               //1. loop through the appointment list
			if (db.appointmentList.get(i).getVetID() == vetID &&            //2. if an appoinment contains the selected vet
					db.appointmentList.get(i).getDay().equals(day)          //3. and contains the selected day
					&& db.appointmentList.get(i).getTime().equals(time)) {  //4. and contains the selected time
				return false;                                               //5. then return false, the vet is busy
			}
		}
		return true;                                                        //6. otherwise the vet is available, return true

	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAppointment frame = new AddAppointment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
