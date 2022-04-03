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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class CancelAppointment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPetName;
	private JTextField txtVetName;
	private JTextField txtDate;
	private Database db = new Database();
	private JComboBox<String> cboApt = new JComboBox<String>();
	public static String aptString;

	public CancelAppointment() {
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
		cboApt.setBounds(182, 103, 200, 25);		
		contentPane.add(cboApt);

		JLabel lblSelectAppointment = new JLabel("Select Appointment:");
		lblSelectAppointment.setForeground(new Color(255, 255, 0));
		lblSelectAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblSelectAppointment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectAppointment.setBounds(57, 108, 118, 14);
		contentPane.add(lblSelectAppointment);

		txtPetName = new JTextField();
		txtPetName.setEditable(false);
		txtPetName.setBackground(SystemColor.text);
		txtPetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtPetName.setBounds(182, 140, 200, 25);
		contentPane.add(txtPetName);
		txtPetName.setColumns(10);

		txtVetName = new JTextField();
		txtVetName.setEditable(false);
		txtVetName.setBackground(SystemColor.text);
		txtVetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtVetName.setColumns(10);
		txtVetName.setBounds(182, 177, 200, 25);
		contentPane.add(txtVetName);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBackground(SystemColor.text);
		txtDate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtDate.setColumns(10);
		txtDate.setBounds(182, 214, 200, 25);
		contentPane.add(txtDate);

		JLabel lblPetName = new JLabel("Pet Name:");
		lblPetName.setForeground(new Color(255, 255, 0));
		lblPetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPetName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPetName.setBounds(73, 145, 102, 14);
		contentPane.add(lblPetName);

		JLabel lblVetName = new JLabel("Vet Name:");
		lblVetName.setForeground(new Color(255, 255, 0));
		lblVetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblVetName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVetName.setBounds(73, 182, 102, 14);
		contentPane.add(lblVetName);

		JLabel lblAppointmentTime = new JLabel("Appointment Time:");
		lblAppointmentTime.setForeground(new Color(255, 255, 0));
		lblAppointmentTime.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblAppointmentTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAppointmentTime.setBounds(50, 219, 125, 14);
		contentPane.add(lblAppointmentTime);

		JButton btnCancelAppointment = new JButton("Cancel ");
		btnCancelAppointment.setBackground(new Color(255, 255, 0));
		btnCancelAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnCancelAppointment.setBounds(300, 300, 90, 25);
		contentPane.add(btnCancelAppointment);

		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(new Color(255, 255, 0));
		btnReturn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnReturn.setBounds(50, 300, 90, 25);
		contentPane.add(btnReturn);

		JLabel lblTitle = new JLabel("Cancel Appointment");
		lblTitle.setForeground(new Color(255, 255, 0));
		lblTitle.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(40, 10, 353, 50);
		contentPane.add(lblTitle);

		cboApt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// created public int to get the selected Appointment ID from the combo box
				for (int i = 0; i < db.appointmentList.size(); i++) // 4. loop through the appointment arrayList
					if (db.appointmentList.get(i).getAppointmentID() == getSelectedAptID()) { // 5. find the matching
																								// appointment ID in the
																								// arrayList
						int petID = db.appointmentList.get(i).getPetID(); // 6. get the pet ID from the same line
						int vetID = db.appointmentList.get(i).getVetID(); // 7. get the vetID from the same line,
																			// declare as
																			// private string
						String ftime = db.appointmentList.get(i).getTime(); // 8. get the time from the same line,
																			// declare as string
						String fday = db.appointmentList.get(i).getDay(); // 9. get the day from the same line, declare
																			// as string
						String fdate = (fday + " " + ftime); // 10. take the day and time, convert to string
						txtDate.setText(fdate); // 11. set the text field txtDate as the date string
						for (int j = 0; j < db.petList.size(); j++) // 12. loop through the petList ArrayList
							if (db.petList.get(j).getPetID() == petID) { // 13. find the matching petID in the arrayList
								String petName = db.petList.get(j).getPetName(); // 14. get the petName from the same
																					// line,
								// declare as private string
								txtPetName.setText(petName); // 15. set the text field txtPetName to the petName String
								for (int k = 0; k < db.vetList.size(); k++) { // 16. loop through the vetList ArrayList
									if (db.vetList.get(k).getVetID() == vetID) { // 17. find the matching vetID
										String vetFName = db.vetList.get(k).getVetFirstName(); // 18. get the
																								// vetFirstName and
																								// declare as string
										String vetLName = db.vetList.get(k).getVetLastName(); // 19. get the vetLastName
																								// and declare as string
										String vetString = ("Dr " + vetFName + " " + vetLName); // 20. combine the first
																								// and
										// last name, declare as
										// private string
										txtVetName.setText(vetString); // 21. set the text field txtVetName as the
																		// vetString
									}
								}
							}
					}
			}
		}

		);

		btnCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < db.appointmentList.size(); i++) { // 1. loop through the Appointmentlist

					if (db.appointmentList.get(i).getAppointmentID() == getSelectedAptID()) { // 2. if the list includes
																								// the appointment ID
						db.appointmentList.remove(i); // 3. delete that entry

					}
				}
				db.writeAppointments(); // 5. call the database writeAppointment function to write the appointmentList
										// to the text file
				JOptionPane.showMessageDialog(new JFrame(), "Appointment Cancelled! ", "Dialog",
						JOptionPane.DEFAULT_OPTION);

				MainMenu mframe = new MainMenu(); // 6. go back to main menu
				mframe.setVisible(true);
				dispose();
			}
		});

		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mframe = new MainMenu(); // 1. declare main menu as a new frame
				mframe.setVisible(true); // 2. make that frame visible
				dispose(); // 3. close the current window
			}
		});

	}

	public void fillComboApt() {
		for (int i = 0; i < db.appointmentList.size(); i++) // 1. loop through the appointment list
			if (db.appointmentList.get(i).getCustomerID() == Login.currentUser) { // 2. once it finds the current user
																					// in the list /
				int aptID = db.appointmentList.get(i).getAppointmentID(); // 3. get the Appointment ID from that record
				String day = db.appointmentList.get(i).getDay(); // 4. get the day and declare as string
				String time = db.appointmentList.get(i).getTime(); // 5. get the time and declare as string
				String cboAptString = (aptID + ". " + day + " " + time); // 6. Make a string out of aptID and day and
																			// time
				cboApt.addItem(cboAptString); // 7. add that String into the Combo Box

			}
	}

	public int getSelectedAptID() {
		String selectedAptID1 = cboApt.getSelectedItem().toString();// 1. get the full string of the selection in the
		String[] splitString = selectedAptID1.split(". ");
		// combo box
		String selectedAptID2 = splitString[0]; // 2. remove everything apart from the numbers
		int aptID2 = Integer.parseInt(selectedAptID2); // 3. convert the string of numbers to an integer
		return aptID2;
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
					CancelAppointment frame = new CancelAppointment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
