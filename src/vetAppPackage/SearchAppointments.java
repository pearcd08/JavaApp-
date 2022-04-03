 package vetAppPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextArea;

public class SearchAppointments extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCustomerSearch;
	private Database db = new Database();
	
	public SearchAppointments() {
		db.loadDatabase(); // call the load database void from database class

		setTitle("Search Appointments");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtCustomerSearch = new JTextField();
		txtCustomerSearch.setBounds(197, 97, 193, 25);
		txtCustomerSearch.setBackground(SystemColor.text);
		txtCustomerSearch.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		contentPane.add(txtCustomerSearch);
		txtCustomerSearch.setColumns(10);

		JLabel lblPetName = new JLabel("Enter Customer's Name:");
		lblPetName.setBounds(50, 102, 142, 14);
		lblPetName.setForeground(new Color(255, 255, 0));
		lblPetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPetName.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblPetName);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(300, 300, 90, 25);
		btnSearch.setBackground(new Color(255, 255, 0));
		btnSearch.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		contentPane.add(btnSearch);

		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(50, 300, 90, 25);
		btnReturn.setBackground(new Color(255, 255, 0));
		btnReturn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		contentPane.add(btnReturn);

		JLabel lblTitle = new JLabel("Search Appointments");
		lblTitle.setBounds(40, 10, 353, 50);
		lblTitle.setForeground(new Color(255, 255, 0));
		lblTitle.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textArea.setBounds(50, 139, 340, 131);
		contentPane.add(textArea);

		btnSearch.addActionListener(new ActionListener() { // appointment file reader in the database class
			public void actionPerformed(ActionEvent e) {
				

				String customerName = txtCustomerSearch.getText();  //1. Gets the fullname from text box
				String[] splitName = customerName.split(" ");       //2. splits the name into a String[] seperated by a space
				String firstName = splitName[0];                    //3. before the space is first name
				String lastName = splitName[1];                     //4. after the space is last name

				for (int i = 0; i < db.customerList.size(); i++) { //5. loop through the customerList
					
					if (db.customerList.get(i).getFirstName().equals(firstName)           //6. find the record that contains the first and last name
							&& db.customerList.get(i).getLastName().equals(lastName)) {  
						int custID = db.customerList.get(i).getCustomerID();              //7. get the customerID of that customer
						for (int j = 0; j < db.appointmentList.size(); j++) {             //8. loop through the appointmentlist
							if (db.appointmentList.get(j).getCustomerID() == custID) {    //9. find the records that match the customer ID
								int petID = db.appointmentList.get(j).getPetID();         //10. get the petID and declare as int
								int vetID = db.appointmentList.get(j).getVetID();         //11. get the vetID and declare as int
								String time = db.appointmentList.get(j).getTime();        //12 get the time and declare as string
								String day = db.appointmentList.get(j).getDay();          //13. get the day and declare as string								    
								for (int k = 0; k < db.petList.size(); k++) {             //14. loop through the petList
									if (db.petList.get(k).getPetID() == petID) {          //15. find the record that matches the petid
										String petName = db.petList.get(k).getPetName();  //16. declare petname as string										
										for (int l = 0; l < db.vetList.size(); l++) {     //17. loop through the vetlist
											if (db.vetList.get(l).getVetID() == vetID) {  //18. get the records that match the vetID
												String vetF = db.vetList.get(l).getVetFirstName();   //19. get the vets first name and declare as string
												String vetL = db.vetList.get(l).getVetLastName();    //20. get the vets lat name and declare as string
												
												String listString = (petName + ", " + day + " at " + time + " with Dr. " //21. create a string out of all the info
														+ vetF + " " + vetL);												
												textArea.append(listString + "\n");   //22. append that string to the text area

												

											}

										}
									}
								}
							}

						}

					}

				}

			}

		});

		btnReturn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				AdminMenu amFrame = new AdminMenu();
				amFrame.setVisible(true);
				dispose();
			}
		});

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
					SearchAppointments frame = new SearchAppointments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
