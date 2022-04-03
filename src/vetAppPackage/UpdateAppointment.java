package vetAppPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;

public class UpdateAppointment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Database db = new Database();
	private JComboBox<String> cboApt = new JComboBox<String>();
	public static String aptString;
	private String petName;
	private String fdate;
	public int vetID;
	private int petID;
	private int aptID;

	public UpdateAppointment() {
		db.loadDatabase(); // call the load database void from database class
		fillComboApt(); // call the void to fill the appointment combo box

		setTitle("Cancel Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cboApt.setBackground(SystemColor.text);
		cboApt.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboApt.setBounds(174, 93, 200, 25);
		contentPane.add(cboApt);

		JLabel lblSelectAppointment = new JLabel("Select Appointment:");
		lblSelectAppointment.setForeground(new Color(255, 255, 0));
		lblSelectAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblSelectAppointment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectAppointment.setBounds(36, 98, 128, 14);
		contentPane.add(lblSelectAppointment);

		JButton btnUpdateAppointment = new JButton("Update");
		btnUpdateAppointment.setBackground(new Color(255, 255, 0));
		btnUpdateAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnUpdateAppointment.setBounds(275, 300, 90, 25);
		contentPane.add(btnUpdateAppointment);

		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(new Color(255, 255, 0));
		btnReturn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnReturn.setBounds(75, 300, 90, 25);
		contentPane.add(btnReturn);

		JTextPane txtCurrentAppointment = new JTextPane();
		txtCurrentAppointment.setEditable(false);
		txtCurrentAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtCurrentAppointment.setBounds(174, 130, 200, 25);
		contentPane.add(txtCurrentAppointment);

		JLabel lblCurrentAppointment = new JLabel("Current Appointment:");
		lblCurrentAppointment.setForeground(new Color(255, 255, 0));
		lblCurrentAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblCurrentAppointment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentAppointment.setBounds(38, 137, 128, 14);
		contentPane.add(lblCurrentAppointment);

		JComboBox<String> cboTime = new JComboBox<String>();
		cboTime.setModel(new DefaultComboBoxModel<String>(
				new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));
		cboTime.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboTime.setBackground(SystemColor.text);
		cboTime.setBounds(174, 204, 100, 25);
		cboTime.setSelectedIndex(-1);
		contentPane.add(cboTime);

		JLabel lblHour = new JLabel("Select Hour:");
		lblHour.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHour.setForeground(new Color(255, 255, 0));
		lblHour.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblHour.setBounds(77, 209, 87, 14);
		contentPane.add(lblHour);

		JComboBox<String> cboDay = new JComboBox<String>();
		cboDay.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));
		cboDay.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboDay.setBackground(SystemColor.text);
		cboDay.setBounds(174, 167, 100, 25);
		cboDay.setSelectedIndex(-1);
		contentPane.add(cboDay);

		JLabel lblDay = new JLabel("Select Day:");
		lblDay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDay.setForeground(new Color(255, 255, 0));
		lblDay.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblDay.setBounds(77, 172, 87, 14);
		contentPane.add(lblDay);

		JLabel lblUpdateAppointment = new JLabel("Update Appointment");
		lblUpdateAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateAppointment.setForeground(Color.YELLOW);
		lblUpdateAppointment.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblUpdateAppointment.setBounds(36, 10, 353, 50);
		contentPane.add(lblUpdateAppointment);

		cboApt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < db.appointmentList.size(); i++) //1. loop through appointment list
					if (db.appointmentList.get(i).getAppointmentID() == getSelectedAptID()) { //2. find appointment with same aptID
						String fday = db.appointmentList.get(i).getDay();                    //3. get the day and declare string
						String ftime = db.appointmentList.get(i).getTime();                  //3. same with time
						fdate = (fday + " " + ftime);                                        //4. create a string
						txtCurrentAppointment.setText(fdate);                                //5. set string into text box

					}
			}
		}

		);

		btnUpdateAppointment.addActionListener(new ActionListener() { // appointment file reader in the database class
			public void actionPerformed(ActionEvent e) {

				// Updated values to replace old line with.
				String tempDay = (String) cboDay.getSelectedItem().toString(); // 1. declare the selected day from combo
																				// box a string
				String tempTime = (String) cboTime.getSelectedItem().toString();// 2. do the same for time
				if (tempDay.length() > 0 && tempTime.length() > 0 && checkVetTime(vetID, tempDay, tempTime) == true) { //3. if time and day are selected and vet available
					for (Appointment apt : db.appointmentList)
					if (apt.getAppointmentID() == getSelectedAptID()) // 4. loop through the appointment list to
																			// find the selected appointment

						{
							apt.setDay(tempDay); // 5. change the day to new day
							apt.setTime(tempTime); // 6. change the time to new time
							db.writeAppointments(); // 7. call on the database to write to file
							JOptionPane.showMessageDialog(new JFrame(),
									"Appointment Updated! See you at " + tempTime + " on " + tempDay, "Dialog",
									JOptionPane.DEFAULT_OPTION);
							cboDay.requestFocus();

							MainMenu mframe = new MainMenu();
							mframe.setVisible(true);
							dispose();
						}
					
				} else if (tempDay.length() == 0 && tempTime.length() == 0
						&& checkVetTime(vetID, tempDay, tempTime) == true) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a Day and Time", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					cboDay.requestFocus(); // 8 day and time is not selected
				}

				else if (tempDay.length() > 0 && tempTime.length() > 0 // 9. The vet is booked for that time slot
						&& checkVetTime(vetID, tempDay, tempTime) == false) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Vet is already booked that time, please choose another time!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					cboDay.requestFocus();

				}

			}
		}

		);

		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mframe = new MainMenu();
				mframe.setVisible(true);
				dispose();
			}
		});

	}

	public void fillComboApt() {
		for (int i = 0; i < db.appointmentList.size(); i++) // 1. Loop through appointment list
			if (db.appointmentList.get(i).getCustomerID() == Login.currentUser) { // 2. until you find the current users
																					// customerID
				aptID = db.appointmentList.get(i).getAppointmentID(); // 3. declare aptID from using getAppointmentID
				petID = db.appointmentList.get(i).getPetID(); // 4. same with PetID

				for (int j = 0; j < db.petList.size(); j++) { // 5. loop through the petList
					if (db.petList.get(j).getPetID() == petID) { // 6. if a pet has the same petID from the appointment
						petName = db.petList.get(j).getPetName(); // 7. get their name and set to string
						String cboAptString = (aptID + ". " + petName); // 8. join an integer and a string to make new
																		// string
						cboApt.addItem(cboAptString); // 9 add that string to the combo box
					}
				}
			}

	}

	public boolean checkVetTime(int vetID, String day, String time) { // 1. see add appointment

		for (int i = 0; i < db.appointmentList.size(); i++) {
			if (db.appointmentList.get(i).getVetID() == vetID && db.appointmentList.get(i).getDay().equals(day)
					&& db.appointmentList.get(i).getTime().equals(time)) {
				return false;
			}

		}
		return true;

	}

	public int getSelectedAptID() {
		String selectedAptID1 = cboApt.getSelectedItem().toString(); // 1. get the full string of the selection in the
		String[] splitString = selectedAptID1.split(". "); // combo box
		String selectedAptID2 = splitString[0]; // 2. remove everything apart from the numbers
		int aptID = Integer.parseInt(selectedAptID2); // 3. convert the string of numbers to an integer
		return aptID;
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
			
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAppointment frame = new UpdateAppointment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
