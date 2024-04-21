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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dk extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtU;
	private JTextField txtG;
	private JPasswordField txtP;
	private JPasswordField txtPP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dk frame = new Dk();
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
	private void registerUser(String username, String gmail, String pass, String confirm) {
	    try {
	        Connection conn = test.getConnection();
	        String hashedPass = hashPassword(pass); // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
	        String sql = "INSERT INTO ACCOUNT (username, gmail, pass, confirm) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, username);
	        statement.setString(2, gmail);
	        statement.setString(3, hashedPass); // Sử dụng mật khẩu đã được mã hóa
	        statement.setString(4, confirm);
	        statement.executeUpdate();
	        conn.close();
	        // Xử lý sau khi đăng ký thành công
	    } catch (SQLException e) {
	        // Xử lý khi có lỗi SQL
	        e.printStackTrace();
	    }
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
	public Dk() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng ký");
		lblNewLabel.setBounds(157, 28, 48, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setBounds(10, 57, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gmail");
		lblNewLabel_2.setBounds(10, 96, 48, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Passwork");
		lblNewLabel_2_1.setBounds(10, 129, 48, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("ConfimP");
		lblNewLabel_2_2.setBounds(10, 173, 48, 14);
		contentPane.add(lblNewLabel_2_2);
		
		txtU = new JTextField();
		txtU.setBounds(97, 53, 203, 20);
		contentPane.add(txtU);
		txtU.setColumns(10);
		
		txtG = new JTextField();
		txtG.setColumns(10);
		txtG.setBounds(97, 93, 203, 20);
		contentPane.add(txtG);
		
		txtP = new JPasswordField();
		txtP.setBounds(97, 126, 203, 20);
		contentPane.add(txtP);
		
		txtPP = new JPasswordField();
		txtPP.setBounds(97, 170, 203, 20);
		contentPane.add(txtPP);
		
		JButton btlogin = new JButton("Login");
		btlogin.setBounds(39, 207, 89, 23);
		contentPane.add(btlogin);
		
		JButton btdk = new JButton("Đăng ký");
		btdk.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thông tin từ các trường nhập liệu
		        String username = txtU.getText();
		        String gmail = txtG.getText(); // Lấy giá trị email từ JTextField txtG
		        String pass = new String(txtP.getPassword());
		        String confirm = new String(txtPP.getPassword());
		        
		        // Gọi phương thức để đăng ký người dùng
		        registerUser(username, gmail, pass, confirm);
		    }
		});

		btdk.setBounds(216, 207, 89, 23);
		contentPane.add(btdk);
	}
}
