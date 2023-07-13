package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	
	private static final long serialVersionUID = 12345;
	private JPanel contentPane;
	private static Connection connection;

	public Menu() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String username = "pinelopirom";
				String password = System.getenv("SCHOOL_DB_USER");
				String url = "jdbc:mysql://localhost:3306/schooldb?serverTimezone=UTC";
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection(url, username, password);
					System.out.println("Connection established");
					
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resources/eduv2.png")));
		setBackground(new Color(239, 239, 239));
		setTitle("Μενού Διαχείρησης Σχολικού Συστήματος");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.setBounds(25, 162, 45, 45);
		contentPane.add(studentsBtn);
		
		JLabel lbStudents = new JLabel("Εκπαιδευόμενοι");
		lbStudents.setForeground(new Color(128, 0, 0));
		lbStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbStudents.setBounds(93, 174, 136, 20);
		contentPane.add(lbStudents);
		
		JLabel lbTeachers = new JLabel("Εκπαιδευτές");
		lbTeachers.setForeground(new Color(128, 0, 0));
		lbTeachers.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTeachers.setBounds(93, 102, 105, 20);
		contentPane.add(lbTeachers);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setVisible(true);
				Main.getMenu().setEnabled(false);
			}
		});
		
		teachersBtn.setBounds(25, 88, 45, 45);
		contentPane.add(teachersBtn);
		
		JLabel lbEduQuality1 = new JLabel("Ποιότητα στην εκπαίδευση");
		lbEduQuality1.setForeground(new Color(0, 128, 128));
		lbEduQuality1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbEduQuality1.setBounds(74, 31, 282, 25);
		contentPane.add(lbEduQuality1);
		
		JLabel lbEduQuality2 = new JLabel("Ποιότητα στην εκπαίδευση");
		lbEduQuality2.setForeground(new Color(0, 0, 0));
		lbEduQuality2.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbEduQuality2.setBounds(75, 32, 282, 25);
		contentPane.add(lbEduQuality2);
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
