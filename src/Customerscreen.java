import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Customerscreen extends JFrame {

	private JPanel contentPane;
	DefaultTableModel tableModel;	
	private JTextField bookTripID;
	private JTable table;
	Manager m = new Manager();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton onewayRB;
	JRadioButton roundRB;
	JLabel priceLabel;
	Parser parser = new Parser();
	private JTextField refundTicket;


	public void showTripData()
	{
		for(int j = 0;j<m.admin.tripList.getSize();j++)
		{
			String s[] = m.admin.tripList.getData(j).split(",");
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
					Customerscreen frame = new Customerscreen();
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
	public Customerscreen() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(126, 10, 544, 245);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("View trips", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 519, 198);
		panel_2.add(scrollPane);
		
		
		String[] TripColumnHeader = {"Trip ID" ,"Source","Destination","Type","Stops","Vehicle","VehicleNo","Departure","Arrival"}; 
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(TripColumnHeader);
		table = new JTable(tableModel);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		showTripData();
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Book a ticket", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trip ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 103, 35);
		panel_1.add(lblNewLabel);
		
		bookTripID = new JTextField();
		bookTripID.setBounds(123, 15, 155, 28);
		panel_1.add(bookTripID);
		bookTripID.setColumns(10);
		
		
		JRadioButton onewayRB = new JRadioButton("One way");
		onewayRB.setSelected(true);
		onewayRB.setEnabled(true);
		buttonGroup.add(onewayRB);
		onewayRB.setActionCommand("One way");
		onewayRB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		onewayRB.setBounds(123, 68, 103, 21);
		panel_1.add(onewayRB);
		
		JRadioButton roundRB = new JRadioButton("Round");
		roundRB.setEnabled(false);
		buttonGroup.add(roundRB);
		roundRB.setActionCommand("Round Trip");
		roundRB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		roundRB.setBounds(123, 102, 103, 21);
		panel_1.add(roundRB);
		
		JLabel lblClickHereIf = new JLabel("Check for round trip!");
		lblClickHereIf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickHereIf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try
				{
					String tripID = bookTripID.getText();
					int x = m.admin.isTripRound(Integer.parseInt(tripID));
					if(x == -1)
					{
						roundRB.setEnabled(false);
						JOptionPane.showMessageDialog(contentPane , "There is no round ticket for this trip");
					}
					else
						roundRB.setEnabled(true);
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(contentPane, "Trip doesn't exist");
				}
			}
		});
		lblClickHereIf.setBounds(232, 104, 212, 17);
		panel_1.add(lblClickHereIf);

		
		JButton btnBookATicket = new JButton("Book a Ticket");
		btnBookATicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					String tripID = bookTripID.getText();
					String ticketType = buttonGroup.getSelection().getActionCommand();
					m.admin.bookTicket(m.admin.getLastCustomerLoggedIn(), Integer.parseInt(tripID), ticketType);
					JOptionPane.showMessageDialog(contentPane, "Trip booked successfully!");
					parser.saveData();
				}
				catch(Exception E)
				{
					JOptionPane.showMessageDialog(contentPane,"Please enter all fields/Make sure inputs are correct!");
				}
			}
		});
		btnBookATicket.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBookATicket.setBounds(351, 165, 132, 28);
		panel_1.add(btnBookATicket);
		
		JLabel priceLabel = new JLabel("");
		priceLabel.setBounds(104, 165, 86, 21);
		panel_1.add(priceLabel);
		
		JLabel lblDisplayPrice = new JLabel("Display price");
		lblDisplayPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String tripID = bookTripID.getText();
				double price = m.admin.getTripPrice(Integer.parseInt(tripID));
				if(buttonGroup.getSelection().getActionCommand().equals("Round Trip"))
					price*=1.8;
				priceLabel.setText(String.valueOf(price));
			}
		});
		lblDisplayPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDisplayPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayPrice.setBounds(72, 141, 155, 17);
		panel_1.add(lblDisplayPrice);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Refund a ticket", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Trip ID:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 10, 103, 35);
		panel_3.add(label);
		
		refundTicket = new JTextField();
		refundTicket.setColumns(10);
		refundTicket.setBounds(123, 15, 155, 28);
		panel_3.add(refundTicket);
		
		JButton btnRefundTicket = new JButton("Refund ticket");
		btnRefundTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String tripID = refundTicket.getText();
					String customerUsername = m.admin.getLastCustomerLoggedIn();
					m.admin.refundTicket(customerUsername, Integer.parseInt(tripID));
					JOptionPane.showMessageDialog(contentPane, "Ticket has been refunded!");
					parser.saveData();
				}
				catch(Exception E)
				{
					JOptionPane.showMessageDialog(contentPane, "Please enter all fields/Make sure all inputs are correct!");
				}
			}
		});
		btnRefundTicket.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefundTicket.setBounds(123, 79, 132, 28);
		panel_3.add(btnRefundTicket);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 10, 106, 245);
		contentPane.add(panel);
		
		JButton button = new JButton("Sign out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainscreen ms = new Mainscreen();
				ms.setVisible(true);
				ms.setLocationRelativeTo(null);
				dispose();
				parser.saveData();
			}
			
		});
		button.setBounds(10, 192, 85, 21);
		panel.add(button);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcome.setBounds(10, 55, 86, 21);
		panel.add(lblWelcome);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(20, 85, 63, 21);
		panel.add(label_1);
		label_1.setText(m.admin.getLastCustomerLoggedIn());


	}
}
