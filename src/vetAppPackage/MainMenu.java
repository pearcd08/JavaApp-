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

public class MainMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Database db = new Database();
	private Login li = new Login();

	public MainMenu() {
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

		JButton btnBookAppointment = new JButton("Book Appointment");
		btnBookAppointment.setBackground(new Color(255, 255, 0));
		btnBookAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnBookAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAppointment aaframe = new AddAppointment();
				aaframe.setVisible(true);
				dispose();
			}
		});
		btnBookAppointment.setBounds(127, 129, 180, 30);
		contentPane.add(btnBookAppointment);

		JButton btnUpdateAppointment = new JButton("Update Appointment");
		btnUpdateAppointment.setBackground(new Color(255, 255, 0));
		btnUpdateAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnUpdateAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateAppointment uaframe = new UpdateAppointment();
				uaframe.setVisible(true);
				dispose();
			}
		});
		btnUpdateAppointment.setBounds(127, 172, 180, 30);
		contentPane.add(btnUpdateAppointment);

		JButton btnCancelAppointment = new JButton("Cancel Appointment");
		btnCancelAppointment.setBackground(new Color(255, 255, 0));
		btnCancelAppointment.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelAppointment caframe = new CancelAppointment();
				caframe.setVisible(true);
				dispose();
			}
		});
		btnCancelAppointment.setBounds(127, 215, 180, 30);
		contentPane.add(btnCancelAppointment);

		JButton btnAddPet = new JButton("Add New Pet");
		btnAddPet.setBackground(new Color(255, 255, 0));
		btnAddPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAddPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPet apframe = new AddPet();
				apframe.setVisible(true);
				dispose();
			}
		});
		btnAddPet.setBounds(127, 86, 180, 30);
		contentPane.add(btnAddPet);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lframe = new Login();
				lframe.setVisible(true);
				dispose();

			}
		});
		btnLogout.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		btnLogout.setBackground(Color.YELLOW);
		btnLogout.setBounds(12, 268, 74, 30);
		contentPane.add(btnLogout);

		JLabel lblNewLabel_1 = new JLabel("VetApp Main Menu");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel_1.setBounds(60, 6, 314, 61);
		contentPane.add(lblNewLabel_1);

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
					MainMenu mainMenuFrame = new MainMenu();
					mainMenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
