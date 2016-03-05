package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.EmailVerify;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.awt.Rectangle;

import javax.swing.SwingConstants;


@SuppressWarnings({ "serial", "rawtypes","unchecked", "unused", "deprecation" })
public class SignupPage extends JPanel 
{
	EmailVerify c_obj=new EmailVerify();
	private SecureRandom random = new SecureRandom();
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtemail;
	private JTextField txtreemail;
	private JPasswordField pwdTxtpassword;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JLabel lblImage_1;
	private int age;
	private String type="";
	private String path="Profiles\\";
	private String birthDate;
	//private String type="";
	private boolean isImage=false;
	private int imageRename=1;
	private File selectedFile;
	private File absolutFile;
	private JComboBox dd;
	
	private JComboBox mm;
	private JComboBox yyyy;
	private JLabel lblDate;
	private JButton btnsubmit;
	private JButton btnBack;
	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	public SignupPage(MainPage mainPage) 
	{
		setBounds(new Rectangle(0, 0, 900, 660));
		//pattern = Pattern.compile(EMAIL_PATTERN);
		setBackground(new Color(95, 158, 160));
		//_parent=mainPage;
		setBackground(SystemColor.control);
		setLayout(null);
		//mainPage _parent=new mainPage();
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirstName.setBounds(100, 183, 95, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastName.setBounds(100, 208, 95, 14);
		add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(100, 413, 95, 14);
		add(lblEmail);
		
		JLabel lblReenterEmail = new JLabel("Re-enter email");
		lblReenterEmail.setForeground(Color.BLACK);
		lblReenterEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReenterEmail.setBounds(100, 438, 95, 14);
		add(lblReenterEmail);
		
		JLabel lblContact = new JLabel("");
		lblContact.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lblContact.setBounds(375, 385, 46, 20);
		lblContact.setVisible(false);
		add(lblContact);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(100, 463, 95, 14);
		add(lblPassword);
		
		JLabel lblfname = new JLabel("");
		lblfname.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lblfname.setForeground(Color.RED);
		lblfname.setVisible(false);
		lblfname.setBounds(375, 180, 46, 20);
		add(lblfname);
		
		JLabel lbllname = new JLabel("");
		lbllname.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lbllname.setForeground(Color.RED);
		lbllname.setVisible(false);
		lbllname.setBounds(375, 205, 46, 20);
		add(lbllname);
		
		lblDate = new JLabel("");
		lblDate.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lblDate.setBounds(375, 255, 46, 20);
		lblDate.setVisible(false);
		add(lblDate);
		
		JLabel lblemail = new JLabel("");
		lblemail.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lblemail.setForeground(Color.RED);
		lblemail.setVisible(false);
		lblemail.setBounds(375, 413, 46, 20);
		add(lblemail);
	
		
		
		JLabel lblreemail = new JLabel("");
		lblreemail.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lblreemail.setForeground(Color.RED);
		lblreemail.setVisible(false);
		lblreemail.setBounds(375, 438, 46, 20);
		add(lblreemail);
		
		JLabel lblpassword = new JLabel("");
		lblpassword.setIcon(new ImageIcon(SignupPage.class.getResource("/images/Error_Symbol.png")));
		lblpassword.setForeground(Color.RED);
		lblpassword.setVisible(false);
		lblpassword.setBounds(375, 463, 46, 20);
		add(lblpassword);
		
		JLabel lblLoading = new JLabel("Please wait Email is Sending...");
		lblLoading.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblLoading.setIcon(new ImageIcon(SignupPage.class.getResource("/images/loder.gif")));
		lblLoading.setBounds(100, 493, 265, 55);
		add(lblLoading);
		lblLoading.setVisible(false);
		
		txtfname = new JTextField();
		txtfname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtfname.setBounds(205, 180, 160, 20);
		add(txtfname);
		txtfname.setColumns(10);
		
		txtlname = new JTextField();
		txtlname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtlname.setBounds(205, 205, 160, 20);
		add(txtlname);
		txtlname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtemail.setBounds(205, 410, 160, 20);
		add(txtemail);
		txtemail.setColumns(10);
		
		txtreemail = new JTextField();
		txtreemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtreemail.setBounds(205, 435, 160, 20);
		add(txtreemail);
		txtreemail.setColumns(10);
		
		pwdTxtpassword = new JPasswordField();
		pwdTxtpassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		pwdTxtpassword.setBounds(205, 460, 160, 20);
		add(pwdTxtpassword);
		
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAddress.setBounds(205, 308, 160, 64);
		add(txtAddress);
		txtAddress.setColumns(10);
		
		txtContact = new JTextField();
		txtContact.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtContact.setBounds(205, 384, 160, 20);
		add(txtContact);
		txtContact.setColumns(10);
		
		
		
		lblImage_1 = new JLabel("");
		lblImage_1.setIcon(new ImageIcon(SignupPage.class.getResource("/images/photo.png")));
		lblImage_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImage_1.setBounds(512, 277, 128, 128);
		add(lblImage_1);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnM.setBounds(205, 229, 39, 23);
		add(rdbtnM);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnF.setBounds(326, 229, 39, 23);
		add(rdbtnF);
		
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnM);
		group.add(rdbtnF);
		ButtonModel model=rdbtnM.getModel();
		group.setSelected(model, true);
		
		dd = new JComboBox();
		dd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0)
			{
				dd.removeItem("dd");
			}
		});
		dd.addItem("dd");
		for (int i=1;i<=31;i++)
		{
		dd.addItem(i);
		}
		dd.setBounds(205, 255, 47, 20);
		add(dd);
		
		mm = new JComboBox();
		mm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		mm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				mm.removeItem("mm");
			}
		});
		mm.addItem("mm");
		for(int j=1;j<=12;j++)
		{
			mm.addItem(j);
		}
		mm.setBounds(255, 255, 47, 20);
		add(mm);
		
		Timestamp year=new Timestamp(System.currentTimeMillis());
		int yer=year.getYear();
		
		yyyy = new JComboBox();
		yyyy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		yyyy.addFocusListener(new FocusAdapter() 
		{

			@Override
			public void focusGained(FocusEvent arg0)
			{
				yyyy.removeItem("yyyy");
			}
			@Override
			public void focusLost(FocusEvent e)
			{
				String str=yyyy.getSelectedItem().toString();
				age=yer-((Integer.parseInt(str))-1900);
			}
		});
		yyyy.setBounds(305, 255, 60, 20);
		yyyy.addItem("yyyy");
		for (int i=(1900+(yer-18));i>=((1900+yer)-100);i--)
		{
			yyyy.addItem(i);
		}
		add(yyyy);
		
		
			
		
		JRadioButton rdbtnY = new JRadioButton("Y");
		rdbtnY.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnY.setBounds(205, 279, 39, 23);
		add(rdbtnY);
		
		JRadioButton rdbtnN = new JRadioButton("N");
		rdbtnN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnN.setBounds(326, 279, 39, 23);
		add(rdbtnN);
		
		ButtonGroup group2=new ButtonGroup();
		group2.add(rdbtnY);
		group2.add(rdbtnN);
		ButtonModel model2=rdbtnY.getModel();
		group2.setSelected(model2, true);
		
			
				
				JButton btnBrowse = new JButton("Browse");
				btnBrowse.setIcon(new ImageIcon(SignupPage.class.getResource("/images/folder-search.png")));
				btnBrowse.setHorizontalAlignment(SwingConstants.LEFT);
				btnBrowse.addActionListener(new ActionListener() {
					

					public void actionPerformed(ActionEvent arg0)
					{
						JFileChooser file =new JFileChooser();
						file.setCurrentDirectory(new File(System.getProperty("user.home")));
						// file filter
						FileNameExtensionFilter filter =new FileNameExtensionFilter("*.Images", "jpg","gif","png");
						file.addChoosableFileFilter(filter);
						int result=file.showSaveDialog(null);
						// if the user click on save in jfilechooser
						if (result==JFileChooser.APPROVE_OPTION)
						{		
							isImage=true;
							File src =new  File(path);
							if (!src.exists())
							{						
								src.mkdirs();
								src.setWritable(true);
							}
							String tempPath;
							selectedFile=file.getSelectedFile();
							tempPath=path+selectedFile.getName();
							absolutFile=new File(tempPath);
							
							String temp_path=selectedFile.getAbsolutePath();
							if (absolutFile.exists())
							{
								while(absolutFile.exists())
								{
									tempPath=path+imageRename+selectedFile.getName();
									absolutFile=new File (tempPath);
									imageRename++;	
								}
								path=tempPath;
								
								absolutFile=new File(path);					
							}
							else
							{
								path=tempPath;					
							}
							lblImage_1.setIcon(ResizeImage(temp_path));					
						}
						else
							if (result==JFileChooser.CANCEL_OPTION)
						{
							isImage=false;
							JOptionPane.showMessageDialog(null, "No Image is seleted");
						}
					}
				});
				btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnBrowse.setBounds(534, 409, 110, 30);
				add(btnBrowse);
		
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(100, 308, 95, 14);
		add(lblAddress);
		
		
		JLabel lblProfilePicture = new JLabel("Profile picture");
		lblProfilePicture.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProfilePicture.setBounds(534, 255, 129, 14);
		add(lblProfilePicture);
		String[] locales=Locale.getISOCountries();
		
		
		JLabel lblMaritalStatus = new JLabel("Marital Status");
		lblMaritalStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaritalStatus.setBounds(100, 283, 95, 14);
		add(lblMaritalStatus);
		
		JLabel lblContactNo = new JLabel("Contact No#");
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContactNo.setBounds(100, 388, 95, 14);
		add(lblContactNo);
		
		
		
		
		JLabel lblMono = new JLabel("User Registration");
		lblMono.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblMono.setIcon(new ImageIcon(SignupPage.class.getResource("/images/home.png")));
		lblMono.setBounds(10, 11, 436, 78);
		add(lblMono);
		
		JLabel lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthdate.setBounds(100, 258, 95, 14);
		add(lblBirthdate);
		
	
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGender.setBounds(100, 233, 95, 14);
		add(lblGender);
		
		

		
		btnsubmit = new JButton("Next");
		btnsubmit.setIcon(new ImageIcon(SignupPage.class.getResource("/pmsicons/1446334685_go-next.png")));
		btnsubmit.setHorizontalAlignment(SwingConstants.LEFT);
		btnsubmit.setForeground(Color.BLACK);
		btnsubmit.setBackground(UIManager.getColor("Button.background"));
		btnsubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				if(txtfname.getText().isEmpty() || !(validateFirstName(txtfname.getText())) )
				{
					lblfname.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Error_Symbol.png")));
					lblfname.setVisible(true);				
				}
				else
				{
					lblfname.setVisible(false);
				}
				
				if(txtlname.getText().isEmpty() || !(validateLastName(txtlname.getText())))
				{
					lbllname.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Error_Symbol.png")));
					lbllname.setVisible(true);	
				}
				else
				{
					lbllname.setVisible(false);
				}
				
				if (dd.getSelectedItem().toString().contentEquals("dd") || mm.getSelectedItem().toString().contentEquals("mm") || yyyy.getSelectedItem().toString().contentEquals("yyyy"))
				{
					lblDate.setVisible(true);
					
				}
				else
				{
					validateDate(birthDate);	
				}
				if (validateEmail(txtemail.getText())==false)
				{
					lblemail.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Error_Symbol.png")));
					lblemail.setVisible(true);
				}
				else
				{
					lblemail.setVisible(false);
				}
				
				if (!(txtemail.getText().contentEquals(txtreemail.getText())))
				{
					lblreemail.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Error_Symbol.png")));
					lblreemail.setVisible(true);
				}
				else
				{
					lblreemail.setVisible(false);
				}
				
				if (!(validatePassword(pwdTxtpassword.getText())))
				{
					lblpassword.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Error_Symbol.png")));
					lblpassword.setVisible(true);
				}
				else
				{
					lblpassword.setVisible(false);
				}
				
				if (!validatNumber(txtContact.getText()))
				{
					lblContact.setVisible(true);
				}
				else
				{
					lblContact.setVisible(false);
				}

				
				if(!lblDate.isVisible() && !lblContact.isVisible() && !lblpassword.isVisible() && !lblreemail.isVisible() && !lblemail.isVisible() && !lbllname.isVisible() && !lblfname.isVisible())//have to change
					{
//						btnsubmit.disable();
	//					btnBack.disable();
						Thread obj =new Thread(new Runnable(){

							@Override
							public void run() 
							{

								// TODO Auto-generated method stub
								lblLoading.setVisible(true);
							}
							
						});
						
						Thread obj2 =new Thread(new Runnable(){

							@Override
							public void run()
							{

								// TODO Auto-generated method stub
								//lblLoading.setVisible(true);
								birthDate=yyyy.getSelectedItem().toString()+"-"+mm.getSelectedItem().toString()+"-"+dd.getSelectedItem().toString();
								System.out.println(birthDate);
								if (!(c_obj.user_email_verifi(txtemail.getText())))
								{
									String gen="";
									String mer="";
									if(rdbtnF.isSelected())
										gen="F";
									else
										gen="M";
									if (rdbtnN.isSelected())
										mer="N";
									else
										mer="Y";
									lblLoading.setVisible(true);
									lblemail.setVisible(false);
									if(mainPage.getUserType().contentEquals("") && isImage==true)
									{
										type="Customer";
										//_parent.set_Type(type);
										mainPage.setPath(path);
									}
									else
									{
										type=mainPage.UsergetType();
									}
									if (isImage==false)
									{
										path="";
									}
									else 
									{
										CustomerVerifier.setFile(selectedFile, absolutFile);										
										//path=path.replace("\\", "#Slash#");
									}
									CustomerVerifier.email_code(new BigInteger(130, random).toString(32),txtemail.getText());
									CustomerVerifier.sign_up(txtfname.getText(),txtlname.getText(),gen,mer,birthDate,age,type,txtContact.getText(),txtAddress.getText() ,txtemail.getText(),pwdTxtpassword.getText(),path);
									mainPage.setCustomerVerifier();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Email is already exsit", "Error", JOptionPane.ERROR_MESSAGE);
									lblemail.setVisible(true);
								}
							}
							
						});
						
						obj.start();
						obj2.start();

					}				
			}
		});
		btnsubmit.setBounds(745, 580, 110, 30);
		add(btnsubmit);
		//btnsubmit.setVisible(false);
		
		
	
		
		btnBack = new JButton("Cancel");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIcon(new ImageIcon(SignupPage.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if (mainPage.getUserType().contentEquals(""))
					try {
						mainPage.setLoginPage();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (mainPage.getUserType().contentEquals("Manager"))
					mainPage.setManagerPage();
				else if (mainPage.getUserType().contentEquals("Admin"))
					mainPage.setAdminPage();
				
			}
		});
		btnBack.setBounds(30, 580, 110, 30);
		add(btnBack);
		//btnBack.setVisible(false);
				
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(SignupPage.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);		
		
	}
	
	
/*
 *Copy file 
 */		
//	private static void copyFile(File source,File dest) 
//	{
//		try
//		{
//			Files.copy(source.toPath(),dest.toPath());			
//		}
//		catch(Exception ex)
//		{
//			JOptionPane.showMessageDialog(null, "Error in copy file","ERROR",JOptionPane.ERROR_MESSAGE);
//		}
//	}
	
	
	/*
	 * Image Resize
	 */ 
	 public ImageIcon ResizeImage(String path)
	{
		ImageIcon myImage =new ImageIcon(path);
		Image img =myImage.getImage();
		Image newImg =img.getScaledInstance(lblImage_1.getWidth(), lblImage_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);
		return image;
	}
	
	 /*
	  * Validate Date
	  */	 
	public void validateDate(String date)
	{
		if ((mm.getSelectedItem().toString().contentEquals("4") || mm.getSelectedItem().toString().contentEquals("6") || mm.getSelectedItem().toString().contentEquals("9") || mm.getSelectedItem().toString().contentEquals("11")) && (Integer.parseInt(dd.getSelectedItem().toString())>30))
		{
			lblDate.setVisible(true);
		}
		else
		if (mm.getSelectedItem().toString().contentEquals("2") && (((Integer.parseInt(yyyy.getSelectedItem().toString()))%4)==0) && (Integer.parseInt(dd.getSelectedItem().toString())>29))
		{
			System.out.println("leap year");
			lblDate.setVisible(true);
		}
		else
		if ((mm.getSelectedItem().toString().contentEquals("2") && (Integer.parseInt(dd.getSelectedItem().toString())>28)) )
		{
			lblDate.setVisible(true);
		}		
		else
		{
			lblDate.setVisible(false);
		}
	}
	 
	 
	/*
	 * Email verifier
	 */
	public boolean validateEmail( String email)
	{
        return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
	
	// validate first name
	   public static boolean validateFirstName( String firstName )
	   {
	      return firstName.matches( "[A-Z][a-zA-Z]*" );
	   }
	  
	// validate last name
	   public static boolean validateLastName( String lastName )
	   {
	      return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
	   }
	   
	  // Validate Password
	   public boolean validatePassword(String password)
	   {
		  return password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}");
	   }
	   
	   // Validate mobile no#
	   public boolean validatNumber(String number)
	   {
		   return number.matches("((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})\\-?)?\\d{10,20}");
	   }
}
