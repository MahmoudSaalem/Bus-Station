import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings({ "unused", "serial" })
public class AdminLoginPage extends JFrame {


	private JPanel contentPane;
	private JTextField usernametxt;
	private JPasswordField passwordField;
	Manager m = new Manager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginPage frame = new AdminLoginPage();
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
	public AdminLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 446, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblManagerLoginForm = new JLabel("Manager Login Form");
		lblManagerLoginForm.setBackground(Color.ORANGE);
		lblManagerLoginForm.setForeground(Color.WHITE);
		lblManagerLoginForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblManagerLoginForm.setBounds(10, 10, 261, 44);
		panel.add(lblManagerLoginForm);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 51, 102));
		panel_1.setBounds(0, 70, 446, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton Login = new JButton("Login");
		Login.setForeground(Color.WHITE);
		Login.setBackground(new Color(0, 153, 204));
		Login.setBounds(288, 94, 89, 42);
		panel_1.add(Login);
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String managerUsername = usernametxt.getText();
					String managerPassword = new String(passwordField.getPassword());
					if(managerUsername.equals(m.getUserName()) && managerPassword.equals(m.getPassword()))
					{
						Managerscreen ms = new Managerscreen();
						ms.setLocationRelativeTo(null);
						dispose();
						ms.setVisible(true);
					}
					else
					{
						boolean x = m.admin.managerLogIn(managerUsername, managerPassword);
						if(x)
						{
							Managerscreen ms = new Managerscreen();
							ms.setVisible(true);
							ms.setLocationRelativeTo(null);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(contentPane,"Wrong username or password!");
						}

					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(contentPane, "Please enter all fields/Make sure inputs are correct!");
				}
			}
		});
		Login.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton Back = new JButton("Back");
		Back.setForeground(Color.WHITE);
		Back.setBackground(new Color(204, 0, 0));
		Back.setBounds(116, 94, 89, 42);
		panel_1.add(Back);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Mainscreen m = new Mainscreen();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
		Back.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 10, 96, 30);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		usernametxt = new JTextField();
		usernametxt.setForeground(Color.WHITE);
		usernametxt.setBackground(new Color(0, 51, 102));
		usernametxt.setBounds(116, 12, 273, 30);
		panel_1.add(usernametxt);
		usernametxt.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(10, 54, 96, 30);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(new Color(0, 51, 102));
		passwordField.setBounds(116, 56, 273, 30);
		panel_1.add(passwordField);
	}
}
