package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

import controller.Email;
import controller.UpdateInfo;
import controller.UsersController;

import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CustomerVerifier extends JPanel 
{
	static UsersController sign_obj=new UsersController();
	private JTextField txtcode;
	
	private static MainPage _parent;
	private static String code="12345";
	private SecureRandom random = new SecureRandom();
	private static String user_email;
	private static String first_name;
	private static String last_name;
	private static String user_password;
	private static String gender;
	private static String maritalStatus;
	private static String contact;
	private static String birthDate;
	private static int age;
	private static String user_address;
	private static String type;
	private static String path;
	private JLabel lblCodeIsIncorrect;
	private static File source;
	private static File dest;
	
	/**
	 * Create the panel.
	 */
	public CustomerVerifier(MainPage mainPage)
	{
		_parent=mainPage;
		UpdateInfo u_obj=new UpdateInfo();
		setBackground(SystemColor.control);
		setLayout(null);
	
		JButton btnVerifiEmail = new JButton("Submit");
		btnVerifiEmail.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerifiEmail.setIcon(new ImageIcon(CustomerVerifier.class.getResource("/pmsicons/1446290275_Check.png")));
		btnVerifiEmail.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (code.contentEquals(txtcode.getText()))
				{
					lblCodeIsIncorrect.setVisible(false);
					
					if (mainPage.getEditMode())
					{
						if (!u_obj.updateInfo(first_name,last_name,gender,maritalStatus,birthDate,age,contact, type,user_address,user_email,user_password,path,mainPage.getEmail()))
						{						
							//path=path.replace("#Slash#", "\\");
							mainPage.setEmail(user_email);
							mainPage.setEditMode(false);
							mainPage.setPath(path);
							copyFile();
							
							//System.out.println(path);
							if (mainPage.getUserType().equals("Customer"))
								mainPage.setCustomerPage();
							else if (mainPage.getUserType().equals("Admin"))
								mainPage.setAdminPage();
							else if (mainPage.getUserType().equals("Manager"))
								mainPage.setManagerPage();
							else if (mainPage.getUserType().equals("Deweller"))
								mainPage.setDewellerPage();
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Query Not Executed Successfull", "ERROR", JOptionPane.ERROR_MESSAGE);
						}						
					}
					else if (!sign_obj.signup_check(first_name,last_name,gender,maritalStatus,birthDate,age,contact, type,user_address,user_email,user_password,path))
					{
						if (!path.equals(""))
						{							
							copyFile();
						}
						_parent.setUserType(type);
						if (mainPage.getUserType().contentEquals("Customer"))
							mainPage.setCustomerPage();
						else if (mainPage.getUserType().contentEquals("Admin"))
							mainPage.setAdminPage();
						else if (mainPage.getUserType().contentEquals("Manager"))
							mainPage.setManagerPage();
					}	
					else
						JOptionPane.showMessageDialog(null, "Query Not Executed Successfull", "ERROR", JOptionPane.ERROR_MESSAGE);					
				}
				else
				{
					lblCodeIsIncorrect.setVisible(true);
				}
			}
		});
		btnVerifiEmail.setBackground(UIManager.getColor("Button.background"));
		btnVerifiEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVerifiEmail.setBounds(740, 580, 110, 30);
		add(btnVerifiEmail);
	//	btnVerifiEmail.setVisible(false);
		
		
		
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCode.setBounds(224, 295, 46, 14);
		add(lblCode);
		
		JLabel lblLoding = new JLabel("");
		lblLoding.setIcon(new ImageIcon(CustomerVerifier.class.getResource("/images/loder.gif")));
		lblLoding.setBounds(469, 324, 55, 55);
		add(lblLoding);
		lblLoding.setVisible(false);
		
		lblCodeIsIncorrect = new JLabel("");
		lblCodeIsIncorrect.setIcon(new ImageIcon(CustomerVerifier.class.getResource("/images/Error_Symbol.png")));
		lblCodeIsIncorrect.setForeground(Color.RED);
		lblCodeIsIncorrect.setVisible(false);
		lblCodeIsIncorrect.setBounds(413, 295, 46, 20);
		add(lblCodeIsIncorrect);
		
		JLabel lblEnterTheCode = new JLabel("Enter the code we Send on your email acoount");
		lblEnterTheCode.setBounds(224, 253, 271, 14);
		add(lblEnterTheCode);
		
		JLabel lblMono = new JLabel("Email Verification");
		lblMono.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblMono.setIcon(new ImageIcon(CustomerVerifier.class.getResource("/images/home.png")));
		lblMono.setBounds(0, 11, 498, 78);
		add(lblMono);
		
		txtcode = new JTextField();
		txtcode.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcode.setBounds(313, 295, 90, 20);
		add(txtcode);
		txtcode.setColumns(10);
		
		
		
		
		JButton btnResendCode = new JButton("Resend Code");
		btnResendCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				lblCodeIsIncorrect.setVisible(false);
				email_code(new BigInteger(130, random).toString(32),user_email);
			}
		});
		btnResendCode.setBackground(UIManager.getColor("Button.background"));
		btnResendCode.setForeground(Color.BLACK);
		btnResendCode.setBounds(341, 335, 118, 32);
		add(btnResendCode);
		
		// mainpage back buuton
		
		mainPage.btnBack.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(mainPage.getUserType().equals(""))
					mainPage.setSignupPage();
				else 
					mainPage.setEditAccount();
					
			}
		});
		
		
		/*JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(mainPage.getUserType().equals(""))
					mainPage.setSignupPage();
				else 
					mainPage.setEditAccount();
					
			}
		});
		btnBack.setBackground(UIManager.getColor("Button.background"));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(661, 594, 80, 23);
		add(btnBack);
		btnBack.setVisible(false);*/
		
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(CustomerVerifier.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		
		
		

	}
	
	@SuppressWarnings("unused")
	public static void email_code(String cod,String email)
	{
		code="12345";//cod;
		user_email=email;
		String subject="PMS Customer email Verification Code:";
		String message="Your Account Verification Code is ::\t"+code;
		Email mail=new Email(email,subject,message);
	}
	
	
	
	
	public static void sign_up(String fname,String lname, String gen, String mer,String birth,int ag,String typ ,String con, String add,String email,String password,String pth)
	{
		first_name=fname;
		last_name=lname;
		user_email=email;
		user_password=password;
		gender=gen;
		maritalStatus=mer;
		contact=con;
		birthDate=birth;
		age=ag;
		type=typ;
		user_address=add;
		// setting type of user
		System.out.println("sign_up page"+pth);
		path=pth;
	}
	
	
	public static void setFile(File src,File dst)
	{
		System.out.println("setFile\t"+src+"\t"+ dst);
		source=src;
		dest=dst;
	}
	
	/*
	 *Copy file 
	 */		
		private static void copyFile() 
		{
			try
			{
				System.out.println("source\t"+source+"dest\t"+dest);
				Files.copy(source.toPath(),dest.toPath());			
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Error in copy file","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
	
}
