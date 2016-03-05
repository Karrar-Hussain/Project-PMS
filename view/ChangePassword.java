package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Email;
import controller.EmailVerify;
import controller.PasswordController;

import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ChangePassword extends JPanel 
{
	PasswordController pass_obj=new PasswordController();
	EmailVerify email_obj=new EmailVerify();
	private MainPage _parent;
	private JTextField txtemail;
	private JTextField txtcode;
	//private static String customer_email;
	private static String code=" ";
	//private SecureRandom random = new SecureRandom();
	private JPasswordField pwdTxtpassword;
	/**
	 * Create the panel.
	 */
	public ChangePassword(MainPage mainPage)
	{
		setBounds(new Rectangle(0, 0, 900, 660));
		_parent=mainPage;
		setLayout(null);
		JLabel lblEmail = new JLabel("Enter email Id");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(250, 195, 82, 14);
		add(lblEmail);
		

		JLabel lblWrongEmialId = new JLabel("");
		lblWrongEmialId.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/Error_Symbol.png")));
		lblWrongEmialId.setForeground(Color.RED);
		lblWrongEmialId.setVisible(false);
		lblWrongEmialId.setBounds(532, 186, 36, 26);
		add(lblWrongEmialId);
		
		JLabel lblpassword = new JLabel("");
		lblpassword.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/Error_Symbol.png")));
		lblpassword.setForeground(Color.RED);
		lblpassword.setVisible(false);
		lblpassword.setBounds(532, 340, 36, 20);
		add(lblpassword);
		
		JLabel lblcode = new JLabel("");
		lblcode.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/Error_Symbol.png")));
		lblcode.setForeground(Color.RED);
		lblcode.setVisible(false);
		lblcode.setBounds(532, 263, 36, 20);
		add(lblcode);
		
		pwdTxtpassword = new JPasswordField();
		pwdTxtpassword.setBounds(392, 340, 130, 20);
		pwdTxtpassword.setVisible(false);
		add(pwdTxtpassword);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtemail.setBounds(342, 192, 180, 20);
		add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblLoding = new JLabel("");
		lblLoding.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/loder.gif")));
		lblLoding.setBounds(558, 209, 55, 55);
		add(lblLoding);
		lblLoding.setVisible(false);
		
		JButton btnSendCode = new JButton("Send Code");
		btnSendCode.setHorizontalAlignment(SwingConstants.LEFT);
		btnSendCode.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/email-send.png")));
		btnSendCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				
				
			}
		});
		btnSendCode.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) 
			{
				Thread obj =new Thread(new Runnable(){
					@Override
					public void run() 
					{
						lblLoding.setVisible(true);
					}
					
				});
				
				Thread obj2 =new Thread (new Runnable() {
					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						if (email_obj.user_email_verifi(txtemail.getText()))
						{
							lblWrongEmialId.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/tick.png")));
							lblWrongEmialId.setVisible(true);
							code="12345";//new BigInteger(130, random).toString(32);
							String subject="PMS Customer email Verification Code:";
							String message="Your Account Verification Code is ::\t"+code;
							Email mail=new Email(txtemail.getText(),subject, message);
							lblLoding.setVisible(false);
						}
						else
						{
							lblLoding.setVisible(false);
							lblWrongEmialId.setVisible(true);
							final JPanel panel = new JPanel();
							JOptionPane.showMessageDialog(panel, "Enter correct email address", "Error", JOptionPane.ERROR_MESSAGE);
						}	
					}	
				});
				obj.start();
				obj2.start();
//				try 
//				{
//					obj.join();
//					obj2.join();
//				} 
//				catch (InterruptedException e1)
//				{
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
			}
		});
		btnSendCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSendCode.setBounds(392, 223, 130, 30);
		add(btnSendCode);
		
		JLabel lblEnterVerificationCode = new JLabel("Enter verification Code");
		lblEnterVerificationCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterVerificationCode.setBounds(250, 263, 132, 14);
		add(lblEnterVerificationCode);
		
		txtcode = new JTextField();
		txtcode.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcode.setBounds(392, 263, 130, 20);
		add(txtcode);
		txtcode.setColumns(10);
	
		
		JLabel lblMono = new JLabel("Change Password");
		lblMono.setForeground(Color.WHITE);
		lblMono.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblMono.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/1449096631_application-pgp-signature.png")));
		lblMono.setBounds(34, 11, 442, 78);
		add(lblMono);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterNewPassword.setBounds(250, 343, 120, 14);
		lblEnterNewPassword.setVisible(false);
		add(lblEnterNewPassword);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setIcon(new ImageIcon(ChangePassword.class.getResource("/pmsicons/1446334685_go-next.png")));
		btnUpdate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{
				if (pwdTxtpassword.getText().length()>8)
				{
					
					lblpassword.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/tick.png")));
					lblpassword.setVisible(true);
					// password update query
					pass_obj.update_password(txtemail.getText(), pwdTxtpassword.getText());
					_parent.setCustomerPage();
				}
				else
				{
					lblpassword.setVisible(true);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(159, 580, 110, 30);
		btnUpdate.setVisible(false);
		add(btnUpdate);
		
		JButton btnResendCode = new JButton("Resend code");
		btnResendCode.setHorizontalAlignment(SwingConstants.LEFT);
		btnResendCode.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) 
			{
				code="12345";//new BigInteger(130, random).toString(32);
				String subject="PMS Customer email Verification Code:";
				String message="Your Account Verification Code is ::\t"+code;
				Email mail=new Email(txtemail.getText(),subject, message);				
			}
		});
		btnResendCode.setForeground(Color.GRAY);
		btnResendCode.setBounds(250, 294, 120, 30);
		add(btnResendCode);
		
		
		
		JButton btnVerifyCode = new JButton("Verify Code");
		btnVerifyCode.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/Accept.png")));
		btnVerifyCode.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerifyCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (code.contentEquals(txtcode.getText()))
				{
					lblcode.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/tick.png")));
					lblcode.setVisible(true);
					lblEnterNewPassword.setVisible(true);
					pwdTxtpassword.setVisible(true);
					btnUpdate.setVisible(true);
					pwdTxtpassword.setVisible(true);
				}
				else
				{
					lblcode.setVisible(true);
				}
				
			}
		});
		btnVerifyCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerifyCode.setBounds(392, 294, 130, 30);
		add(btnVerifyCode);
		
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIcon(new ImageIcon(ChangePassword.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					_parent.setLoginPage();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(30, 580, 90, 30);
		add(btnBack);
		
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(ChangePassword.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);	

	}

}
