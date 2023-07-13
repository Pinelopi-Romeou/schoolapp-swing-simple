package gr.aueb.cf.schoolapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachersUpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 12345;
	private JPanel contentPane;
	private JTextField IDTxt;
	private JTextField lastnameTxt;
	private JTextField firstnameTxt;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public TeachersUpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
				Connection connection = Menu.getConnection();

				try {
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getTeachersSearchForm().getLastname() + "%");
					rs = ps.executeQuery();

					if (rs.next()) {
						IDTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
					} else {
						JOptionPane.showMessageDialog(null,  "No Teachers found", "Teachers", JOptionPane.WARNING_MESSAGE);
						Main.getTeachersUpdateDeleteForm().setVisible(false);
						Main.getTeachersSearchForm().setEnabled(true);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel IDLb = new JLabel("ID");
		IDLb.setForeground(new Color(128, 0, 0));
		IDLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		IDLb.setBounds(92, 43, 18, 19);
		contentPane.add(IDLb);

		IDTxt = new JTextField();
		IDTxt.setBackground(new Color(255, 251, 217));
		IDTxt.setEditable(false);
		IDTxt.setBounds(128, 42, 61, 21);
		contentPane.add(IDTxt);
		IDTxt.setColumns(10);

		JLabel firstnameLb = new JLabel("'Ονομα");
		firstnameLb.setForeground(new Color(128, 0, 0));
		firstnameLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLb.setBounds(60, 138, 50, 19);
		contentPane.add(firstnameLb);

		JLabel lastnameLb = new JLabel("Επώνυμο");
		lastnameLb.setForeground(new Color(128, 0, 0));
		lastnameLb.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLb.setBounds(44, 91, 66, 19);
		contentPane.add(lastnameLb);

		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBackground(new Color(255, 255, 255));
		lastnameTxt.setBounds(128, 90, 162, 21);
		contentPane.add(lastnameTxt);

		firstnameTxt = new JTextField();
		firstnameTxt.setColumns(10);
		firstnameTxt.setBackground(new Color(255, 255, 255));
		firstnameTxt.setBounds(128, 137, 162, 21);
		contentPane.add(firstnameTxt);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(23, 29, 332, 145);
		contentPane.add(panel);

		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						IDTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/First record.png")));
		firstBtn.setBounds(100, 184, 40, 30);
		contentPane.add(firstBtn);

		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						IDTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		prevBtn.setBounds(150, 184, 40, 30);
		contentPane.add(prevBtn);

		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						IDTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		nextBtn.setBounds(200, 184, 40, 30);
		contentPane.add(nextBtn);

		JButton lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						IDTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(TeachersUpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(250, 184, 40, 30);
		contentPane.add(lastBtn);

		JButton updateBtn = new JButton("Update");
		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE TEACHERS SET LASTNAME = ?, FIRSTNAME = ? WHERE ID = ?";
				
				
				try {
					Connection connection = Menu.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					String lastname = lastnameTxt.getText().trim();
					String firstname = firstnameTxt.getText().trim();
					String id = IDTxt.getText();

					if (lastname.equals("") || firstname.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty lastname / firstname", "Input Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					ps.setString(1, lastname);
					ps.setString(2, firstname);
					ps.setInt(3, Integer.parseInt(id));

					int n = ps.executeUpdate();

					if (n > 0) {
						JOptionPane.showMessageDialog(null, "Successful Update", "Update",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Update Error", "Error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (ps != null) {
							ps.close();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.setBounds(44, 225, 85, 28);
		contentPane.add(updateBtn);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";

				try {
					Connection connection = Menu.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(IDTxt.getText()));

					int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος-η;", "Warning",
							JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, n + " row(s) affected", "Delete",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(157, 225, 85, 28);
		contentPane.add(deleteBtn);

		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(270, 225, 85, 28);
		contentPane.add(closeBtn);
	}
}
