package vetAppPackage;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class AdminMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Database db = new Database();
	private Login li = new Login();	
	
	public AdminMenu() {
		db.loadDatabase();
		setTitle("VetApp Main Menu");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(li.getCurrentUserString() + " Logged In");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(230, 276, 192, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNextAppointment = new JLabel("Admin Menu");
		lblNextAppointment.setForeground(new Color(255, 255, 0));
		lblNextAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		lblNextAppointment.setFont(new Font("Arial Black", Font.ITALIC, 26));
		lblNextAppointment.setBounds(36, 5, 368, 49);
		contentPane.add(lblNextAppointment);

		JButton btnSearchAppointments = new JButton("Search Appointments");
		btnSearchAppointments.setBackground(new Color(255, 255, 0));
		btnSearchAppointments.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnSearchAppointments.setBounds(127, 86, 180, 30);
		contentPane.add(btnSearchAppointments);
		btnSearchAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchAppointments scFrame = new SearchAppointments();
				scFrame.setVisible(true);
				dispose();
			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		btnLogout.setBackground(Color.YELLOW);
		btnLogout.setBounds(12, 268, 62, 30);
		contentPane.add(btnLogout);

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lframe = new Login();
				lframe.setVisible(true);
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
					AdminMenu mainMenuFrame = new AdminMenu();
					mainMenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
