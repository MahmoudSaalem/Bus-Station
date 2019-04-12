import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class DriverScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel;
	Manager m = new Manager();
	Parser parser = new Parser();
	
	
	public void updateDriverData()
	{	
		String driverUsername = m.admin.getLastDriverLoggedIn();
		tableModel.setRowCount(0);
		for(int i=0;i<m.admin.driverSchedule(driverUsername).length;i++)
		{
			String s[] = m.admin.driverSchedule(driverUsername)[0].split(",");
			Object obj[] = {s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[8],s[9]};
			tableModel.addRow(obj);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverScreen frame = new DriverScreen();
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
	public DriverScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 10, 595, 241);
		contentPane.add(scrollPane);
		
		String[] TripColumnHeader = {"Trip ID" ,"Source","Destinaion","Type","Stops","Vehicle","VehicleNo","Departure","Arrival"}; 
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(TripColumnHeader);
		table = new JTable(tableModel);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		updateDriverData();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 103, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("Sign out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parser.saveData();
				Mainscreen ms = new Mainscreen();
				ms.setVisible(true);
				ms.setLocationRelativeTo(null);
				dispose();
				updateDriverData();
			}
		});
		button.setBounds(10, 191, 83, 21);
		panel.add(button);
		
		JLabel label = new JLabel("Welcome");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 57, 86, 21);
		panel.add(label);
		
		JLabel label_1 = new JLabel((String) null);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(20, 87, 63, 21);
		panel.add(label_1);
		label_1.setText(m.admin.getLastDriverLoggedIn());
	}
}
