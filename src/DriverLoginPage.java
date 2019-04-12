import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class DriverLoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField userNametxt;
	private JPasswordField passwordField;
	Manager m = new Manager();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverLoginPage frame = new DriverLoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DriverLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 436, 249);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 447, 64);
		panel.add(panel_1);
		
		JLabel lblDriverLoginForm = new JLabel("Driver Login Form");
		lblDriverLoginForm.setForeground(Color.WHITE);
		lblDriverLoginForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDriverLoginForm.setBounds(10, 10, 261, 44);
		panel_1.add(lblDriverLoginForm);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 51, 102));
		panel_2.setLayout(null);
		panel_2.setBounds(0, 62, 447, 187);
		panel.add(panel_2);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setBackground(new Color(0, 153, 204));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String driverUsername = userNametxt.getText();
					String driverPassword = new String(passwordField.getPassword());
					boolean x = m.admin.driverLogIn(driverUsername, driverPassword);
					if(x)
					{
						DriverScreen ds = new DriverScreen();
						ds.setVisible(true);
						ds.setLocationRelativeTo(null);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(contentPane, "Wrong username or password!");
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(contentPane, "Please enter all fields/Make sure all inputs are correct!");
				}
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		loginBtn.setBounds(288, 94, 89, 42);
		panel_2.add(loginBtn);
		
		JButton backBtn = new JButton("Back");
		backBtn.setBackground(new Color(204, 0, 0));
		backBtn.setForeground(Color.WHITE);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Mainscreen ms = new Mainscreen();
				ms.setVisible(true);
				ms.setLocationRelativeTo(null);
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		backBtn.setBounds(40, 94, 89, 42);
		panel_2.add(backBtn);
		
		JLabel label_1 = new JLabel("Username:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(10, 10, 96, 30);
		panel_2.add(label_1);
		
		userNametxt = new JTextField();
		userNametxt.setBackground(new Color(0, 51, 102));
		userNametxt.setForeground(Color.WHITE);
		userNametxt.setColumns(10);
		userNametxt.setBounds(116, 12, 273, 30);
		panel_2.add(userNametxt);
		
		JLabel label_2 = new JLabel("Password:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(10, 54, 96, 30);
		panel_2.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(0, 51, 102));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(116, 56, 273, 30);
		panel_2.add(passwordField);
	}

}
