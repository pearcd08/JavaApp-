package vetAppPackage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager.*;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmailAddress;
	private JTextField txtPassword;
	static public int currentUser;
	private static String currentUserString;
	private Database db = new Database();

	public Login() {
		setTitle("Login");
		// link class to the Database class
		db.loadDatabase();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmail = new JLabel("Email Address ");
		lblEmail.setForeground(new Color(255, 255, 0));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblEmail.setBounds(6, 92, 131, 14);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel(" Password ");
		lblPassword.setForeground(new Color(255, 255, 0));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblPassword.setBounds(6, 124, 131, 14);
		contentPane.add(lblPassword);

		txtEmailAddress = new JTextField();
		txtEmailAddress.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtEmailAddress.setBounds(137, 86, 200, 25);
		contentPane.add(txtEmailAddress);
		txtEmailAddress.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtPassword.setBounds(137, 119, 200, 25);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 255, 0));
		btnLogin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnLogin.setBounds(137, 171, 80, 25);
		contentPane.add(btnLogin);

		// click login button, check if email and password exist and match
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// declares the text entered into the text boxes as String variables.
				String email = txtEmailAddress.getText();
				String password = txtPassword.getText();

				if (email.equals("admin") && password.equals("admin")) {
					AdminMenu amFrame = new AdminMenu();
					amFrame.setVisible(true);
					dispose();
				}

				else {

					for (int i = 0; i < db.customerList.size(); i++) {
						if (db.customerList.get(i).getEmailAddress().equals(email)) {
							if (db.customerList.get(i).getPassword().equals(password)) {
								// declare the ID of the matching email and password as a static variable
								currentUser = db.customerList.get(i).getCustomerID();

								// open the mainmenu
								MainMenu mframe = new MainMenu();
								mframe.setVisible(true);
								dispose();
							}

							else {
								JOptionPane.showMessageDialog(new JFrame(), "Incorrect Password", "Dialog",
										JOptionPane.ERROR_MESSAGE);
							}

						}

					}

				}
			}
		});
		// open the register Jframe on click

		// register button
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(255, 255, 0));
		btnRegister.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnRegister.setBounds(246, 171, 91, 25);
		contentPane.add(btnRegister);

		JLabel lblNewLabel = new JLabel("VetApp Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setBounds(99, 5, 223, 46);
		contentPane.add(lblNewLabel);
		// link to register form
		btnRegister.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Register rframe = new Register();
				rframe.setVisible(true);
				dispose();
			}

		});

	}

	public String getCurrentUserString() {
		for (int i = 0; i < db.customerList.size(); i++)
			if (db.customerList.get(i).getCustomerID() == Login.currentUser) {
				String fName = db.customerList.get(i).getFirstName();
				String lName = db.customerList.get(i).getLastName();
				currentUserString = (fName + " " + lName);

			}
		return currentUserString;
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
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}