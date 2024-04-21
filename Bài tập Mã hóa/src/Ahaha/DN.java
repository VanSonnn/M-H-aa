package Ahaha;

import java.awt.EventQueue;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DN extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtU;
	private JPasswordField txtP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DN frame = new DN();
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
	
	private ResultSet registerUser(String username, String pass) {
	    ResultSet rs = null;
	    try {
	        Connection conn = test.getConnection();
	        String hashedPass = hashPassword(pass);
	        String sql = "SELECT * FROM ACCOUNT WHERE USERNAME=? AND PASS=?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, username);
	        statement.setString(2, hashedPass);
	        rs = statement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	private String hashPassword(String password) {
        try {
            // Tạo một đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Tính toán giá trị băm của mật khẩu
            byte[] messageDigest = md.digest(password.getBytes());

            // Chuyển đổi mảng byte thành dạng số nguyên không âm (BigInteger)
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Xử lý khi thuật toán băm không khả dụng
            e.printStackTrace();
            return null;
        }
    }
	public DN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(177, 44, 48, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setBounds(39, 79, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pass");
		lblNewLabel_2.setBounds(39, 125, 48, 14);
		contentPane.add(lblNewLabel_2);
		
		txtU = new JTextField();
		txtU.setBounds(140, 76, 167, 20);
		contentPane.add(txtU);
		txtU.setColumns(10);
		
		txtP = new JPasswordField();
		txtP.setBounds(140, 122, 167, 17);
		contentPane.add(txtP);
		
		JButton btDN = new JButton("Đăng nhập");

btDN.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String username = txtU.getText(); 
        String pass = new String(txtP.getPassword());
        ResultSet rs = registerUser(username, pass);
        try {
            if (rs.next()) {
                Welcome cc = new Welcome();
                cc.setVisible(true);
            } else {
                System.out.println("Sai tên đăng nhập hoặc mật khẩu.");
            }
            rs.close(); // Đóng ResultSet sau khi sử dụng
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
});
		btDN.setBounds(155, 183, 89, 23);
		contentPane.add(btDN);
	}

}
