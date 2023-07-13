package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeachersSearchForm extends JFrame {
	private static final long serialVersionUID = 12345;
	private JPanel contentPane;
	private JTextField txtLastname;
	private String lastname = "";

	public TeachersSearchForm() {
		setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτικού");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TeachersSearchForm.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersInsertForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		
		insertBtn.setForeground(new Color(0, 128, 192));
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		insertBtn.setBounds(164, 188, 105, 27);
		contentPane.add(insertBtn);
		
		txtLastname = new JTextField();
		txtLastname.setColumns(10);
		txtLastname.setBounds(135, 78, 170, 27);
		contentPane.add(txtLastname);
		
		JLabel lbLastname = new JLabel("Επώνυμο");
		lbLastname.setForeground(new Color(128, 0, 0));
		lbLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbLastname.setBounds(186, 39, 73, 20);
		contentPane.add(lbLastname);
		
		JButton searchBtn = new JButton("Αναζήτηση");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastname = txtLastname.getText();
				Main.getTeachersUpdateDeleteForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		searchBtn.setForeground(new Color(0, 128, 192));
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		searchBtn.setBounds(164, 133, 105, 27);
		contentPane.add(searchBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(81, 28, 280, 143);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(81, 181, 280, 39);
		contentPane.add(panel_1);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(true);
				Main.getTeachersSearchForm().setVisible(false);
			}
		});
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		closeBtn.setForeground(new Color(0, 128, 192));
		closeBtn.setBounds(276, 232, 85, 21);
		contentPane.add(closeBtn);
	}

	public String getLastname() {
		return lastname;
	}
	
}
