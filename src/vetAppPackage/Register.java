
package vetAppPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
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
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Register extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmailAddress;
	private JTextField txtPassword;
	private JTextField txtFirstName;
	private JTextField txtLastName;

	public Register() {
		setTitle("Register New Customer");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtEmailAddress = new JTextField();
		txtEmailAddress.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtEmailAddress.setBounds(153, 142, 220, 25);
		contentPane.add(txtEmailAddress);
		txtEmailAddress.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtPassword.setColumns(10);
		txtPassword.setBounds(153, 173, 220, 25);
		contentPane.add(txtPassword);

		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(153, 80, 220, 25);
		contentPane.add(txtFirstName);

		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtLastName.setColumns(10);
		txtLastName.setBounds(153, 111, 220, 25);
		contentPane.add(txtLastName);

		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setForeground(new Color(255, 255, 0));
		lblEmailAddress.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblEmailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailAddress.setBounds(61, 147, 92, 14);
		contentPane.add(lblEmailAddress);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 0));
		lblPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(78, 178, 75, 14);
		contentPane.add(lblPassword);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(new Color(255, 255, 0));
		lblFirstName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(47, 85, 106, 14);
		contentPane.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(new Color(255, 255, 0));
		lblLastName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(78, 116, 75, 14);
		contentPane.add(lblLastName);

		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnRegister.setBackground(new Color(255, 255, 0));
		btnRegister.setBounds(283, 250, 90, 25);
		contentPane.add(btnRegister);

		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnReturn.setBackground(new Color(255, 255, 0));
		btnReturn.setBounds(61, 250, 90, 25);
		contentPane.add(btnReturn);

		JLabel lblTitle = new JLabel("Register New Customer");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setBounds(35, 6, 363, 45);
		contentPane.add(lblTitle);

		// ACTION LISTENERS

		// Save the new customer
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File customerTxt = new File("Customers.txt");

				try {
					BufferedReader countLines = new BufferedReader(new FileReader(customerTxt));
					int lineCount = 0;
					while (countLines.readLine() != null) // adds to the lineCount for every line that isnt empty.
						lineCount++;
					countLines.close();

					FileWriter addCustomer = new FileWriter(customerTxt, true);
					Writer output = new BufferedWriter(addCustomer);
					int tempID = lineCount + 1;
					String tempFirstName = txtFirstName.getText();
					String tempLastName = txtLastName.getText();
					String tempEmailAddress = txtEmailAddress.getText();
					String tempPassword = txtPassword.getText();

					if (isEmailValid(tempEmailAddress) == true && tempPassword.length() > 6
							&& tempFirstName.length() > 0 && tempLastName.length() > 0) {
						output.write(tempID + "," + tempFirstName + "," + tempLastName + "," + tempEmailAddress + ","
								+ tempPassword + "\n");
						output.close();
						JOptionPane.showMessageDialog(new JFrame(), "Welcome to VetApp " + tempFirstName + "!",
								"Dialog", JOptionPane.DEFAULT_OPTION);
						Login lframe = new Login();
						lframe.setVisible(true);
						dispose();
					} else if (isEmailValid(tempEmailAddress) == false && tempPassword.length() > 6
							&& tempFirstName.length() > 0 && tempLastName.length() > 0) {
						JOptionPane.showMessageDialog(new JFrame(), "Email is not valid!", "Dialog",
								JOptionPane.ERROR_MESSAGE);
						txtEmailAddress.requestFocus();

					} else if (isEmailValid(tempEmailAddress) == true && tempPassword.length() < 6
							&& tempFirstName.length() > 0 && tempLastName.length() > 0) {
						JOptionPane.showMessageDialog(new JFrame(), "Password must be more than 6 characters", "Dialog",
								JOptionPane.ERROR_MESSAGE);
						txtPassword.requestFocus();
					}

					else {
						JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields", "Dialog",
								JOptionPane.ERROR_MESSAGE);

					}

				} catch (FileNotFoundException e1) {

					System.out.println("File not found");

				} catch (IOException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error happened while the file being read");
				}

			}

		}

		);

		btnReturn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Login lframe = new Login();
				lframe.setVisible(true);
				// close current window
				dispose();

			}
		});

	}

	public boolean isEmailValid(String tempEmail) {
		String emailChar = "^(.+)@(\\S+)$";

		return Pattern.compile(emailChar).matcher(tempEmail).matches();

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
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
