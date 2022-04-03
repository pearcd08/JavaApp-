package vetAppPackage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AddPet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPetName;
	private Database db = new Database();
	private Login li = new Login();

	public AddPet() {
		db.loadDatabase();

		setTitle("Add New Pet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> cboPet = new JComboBox<>();
		cboPet.setForeground(new Color(0, 0, 0));
		cboPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboPet.setModel(new DefaultComboBoxModel<String>(new String[] { "Bird", "Cat", "Dog" }));
		cboPet.setBounds(150, 118, 100, 25);
		cboPet.setSelectedIndex(-1);
		contentPane.add(cboPet);

		JLabel lblSelectPetType = new JLabel("Pet Type:");
		lblSelectPetType.setForeground(new Color(255, 255, 0));
		lblSelectPetType.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblSelectPetType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectPetType.setBounds(-11, 123, 156, 14);
		contentPane.add(lblSelectPetType);

		txtPetName = new JTextField();
		txtPetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtPetName.setBounds(150, 82, 220, 25);
		contentPane.add(txtPetName);
		txtPetName.setColumns(10);

		JLabel lblPetName = new JLabel("Pet Name:");
		lblPetName.setForeground(new Color(255, 255, 0));
		lblPetName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPetName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPetName.setBounds(66, 87, 79, 14);
		contentPane.add(lblPetName);

		JComboBox<String> cboPetGender = new JComboBox<>();
		cboPetGender.setForeground(new Color(0, 0, 0));
		cboPetGender.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		cboPetGender.setModel(new DefaultComboBoxModel<String>(new String[] { "Female", "Male" }));
		cboPetGender.setBounds(150, 154, 100, 25);
		cboPetGender.setSelectedIndex(-1);
		contentPane.add(cboPetGender);

		JLabel lblPetGender = new JLabel("Pet Gender:");
		lblPetGender.setForeground(new Color(255, 255, 0));
		lblPetGender.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPetGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPetGender.setBounds(66, 159, 79, 14);
		contentPane.add(lblPetGender);

		JLabel lblPetAge = new JLabel("Pet Age:");
		lblPetAge.setForeground(new Color(255, 255, 0));
		lblPetAge.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPetAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPetAge.setBounds(66, 195, 79, 14);
		contentPane.add(lblPetAge);

		JLabel lblCurrentUser = new JLabel(li.getCurrentUserString() + " Logged In");
		lblCurrentUser.setForeground(new Color(255, 255, 0));
		lblCurrentUser.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 8));
		lblCurrentUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentUser.setBounds(312, 291, 116, 14);
		contentPane.add(lblCurrentUser);

		JLabel lblTitle = new JLabel("Add New Pet");
		lblTitle.setForeground(new Color(255, 255, 0));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setBounds(55, 5, 324, 49);
		contentPane.add(lblTitle);

		JSpinner numPetAge = new JSpinner();
		numPetAge.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		numPetAge.setBackground(SystemColor.textHighlight);
		numPetAge.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		numPetAge.setBounds(150, 189, 50, 25);
		contentPane.add(numPetAge);

		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(new Color(255, 255, 0));
		btnReturn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mmframe = new MainMenu();
				mmframe.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(75, 250, 90, 25);
		contentPane.add(btnReturn);

		// method to save the newly added pet to a text file, the text file will be read
		// into the petList ArrayList from the Database class
		JButton btnSavePet = new JButton("Save Pet");
		btnSavePet.setBackground(new Color(255, 255, 0));
		btnSavePet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnSavePet.setBounds(275, 250, 90, 25);
		contentPane.add(btnSavePet);
		btnSavePet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String petName = txtPetName.getText();
				String petType = (String) cboPet.getSelectedItem();
				String petGender = (String) cboPetGender.getSelectedItem();
				String customerID = String.valueOf(Login.currentUser);
				String petAge = numPetAge.getValue().toString();
				File petTxt = new File("Pets.txt");
				if (petName.length() > 0 && petType.length() > 0 && petGender.length() > 0
						&& ifPetExists(petName) == false) {
					try {
						BufferedReader countLines = new BufferedReader(new FileReader(petTxt));
						int lineCount = 0;
						while (countLines.readLine() != null) // adds to the lineCount for every line that isn't empty.
							lineCount++;

						countLines.close();

						FileWriter addPet = new FileWriter(petTxt, true);
						Writer output = new BufferedWriter(addPet);
						int newID = lineCount + 1; // gets the total lines in the text file and adds 1 to find the new
													// pet
													// id, could also be done with counting the petList
						// converts the combo list selection into a
						// string
						// takes the current logged in customer, to
						// use
						// for the customerID

						output.write(+newID + "," + petName + "," + petType + "," + petAge + ","// writes the string
																									// to the file
								+ petGender + "," + customerID + "\n");

						output.close(); // closes the reader
						JOptionPane.showMessageDialog(new JFrame(),
								txtPetName.getText() + " has been added to the system", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
						

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block

						System.out.println("File not found");

					} catch (IOException e2) {
						// TODO Auto-generated catch block
						System.out.println("Error happened while the file being read");
					}
				}

				else if (petName.length() > 0 && petType.length() > 0 && petGender.length() > 0
						&& ifPetExists(petName) == true) {
					JOptionPane.showMessageDialog(new JFrame(), txtPetName.getText() + " is already in your account",
							"Dialog", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Fill in all details", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});

	}

	public boolean ifPetExists(String petName) {
		for (int i = 0; i < db.petList.size(); i++) {
			if (db.petList.get(i).getCustomerID() == Login.currentUser
					&& db.petList.get(i).getPetName().equals(petName)) { // search pet list for already taken name by
				return true; // customer
			}
		}
		return false;

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
					AddPet frame = new AddPet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
