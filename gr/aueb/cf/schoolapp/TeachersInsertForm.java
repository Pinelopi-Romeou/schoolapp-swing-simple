package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeachersInsertForm extends JFrame {
	private static final long serialVersionUID = 12345;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;

	public TeachersInsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lastnameTxt.setText("");
				firstnameTxt.setText("");
			}
		});
		setTitle("Εισαγωγή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lastnameLb_1 = new JLabel("Επώνυμο");
		lastnameLb_1.setForeground(new Color(128, 0, 0));
		lastnameLb_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLb_1.setBounds(67, 65, 66, 17);
		contentPane.add(lastnameLb_1);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(145, 66, 126, 19);
		contentPane.add(lastnameTxt);
		
		JLabel firstnameLb = new JLabel("Όνομα");
		firstnameLb.setForeground(new Color(128, 0, 0));
		firstnameLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLb.setBounds(67, 103, 66, 17);
		contentPane.add(firstnameLb);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(145, 104, 126, 19);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(46, 28, 295, 139);
		contentPane.add(panel);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO TEACHERS (LASTNAME, FIRSTNAME) VALUES (?, ?)";
				
				try {
					String firstname = lastnameTxt.getText().trim();
					String lastname = firstnameTxt.getText().trim();
					
					if (firstname.equals("") || lastname.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty firstname / lastname", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Connection connection = Menu.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, n + " row(s) affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setBounds(61, 195, 128, 32);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setEnabled(true);
				Main.getTeachersInsertForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(198, 195, 121, 32);
		contentPane.add(closeBtn);
	}
}
