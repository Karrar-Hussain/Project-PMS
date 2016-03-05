package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.EmailVerify;
import controller.UserAccountInfo;

@SuppressWarnings("serial")
public class EditUserAccount extends JPanel {

	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	
	
	EmailVerify c_obj=new EmailVerify();
	UserAccountInfo c_obj2=new UserAccountInfo();
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtemail;
	private JTextField txtreemail;
	private JPasswordField pwdTxtpassword;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JLabel lblImage_1;
	private int age;
	private String path="Profiles\\";
	private String birthDate;
	private boolean isImage=false;
	private java.sql.ResultSet re;
	private int imageRename=1;
	private File selectedFile;
	private File absolutFile;
	private String[] splitDate={"","",""};
	
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public EditUserAccount(MainPage mainPage) 
	{
		setBounds(new Rectangle(0, 0, 900, 660));
		setBackground(new Color(95, 158, 160));
		setBackground(SystemColor.control);
		setLayout(null);
		re= c_obj2.userAcconutInfo(mainPage.getEmail());
		
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
		
		JLabel lblDate = new JLabel("");
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
		lblLoading.setIcon(new ImageIcon(EditUserAccount.class.getResource("/images/loder.gif")));
		lblLoading.setBounds(100, 488, 265, 55);
		add(lblLoading);
		lblLoading.setVisible(false);
		
		txtfname = new JTextField();
		txtfname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtfname.setBounds(205, 180, 160, 20);
		add(txtfname);
		txtfname.setColumns(10);
		try
		{
			txtfname.setText(re.getString("first_name"));
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		
		txtlname = new JTextField();
		txtlname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtlname.setBounds(205, 205, 160, 20);
		add(txtlname);
		txtlname.setColumns(10);
		try 
		{
			txtlname.setText(re.getString("last_name"));
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtemail.setBounds(205, 410, 160, 20);
		add(txtemail);
		txtemail.setColumns(10);
		try 
		{
			txtemail.setText(re.getString("user_email"));
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		txtreemail = new JTextField();
		txtreemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtreemail.setBounds(205, 435, 160, 20);
		add(txtreemail);
		txtreemail.setColumns(10);
		try 
		{
			txtreemail.setText(re.getString("user_email"));
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		pwdTxtpassword = new JPasswordField();
		pwdTxtpassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		pwdTxtpassword.setBounds(205, 460, 160, 20);
		add(pwdTxtpassword);
		try 
		{
			pwdTxtpassword.setText(re.getString("user_password"));
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAddress.setBounds(205, 308, 160, 64);
		add(txtAddress);
		txtAddress.setColumns(10);
		try 
		{
			txtAddress.setText(re.getString("address"));
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		txtContact = new JTextField();
		txtContact.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtContact.setBounds(205, 384, 160, 20);
		add(txtContact);
		txtContact.setColumns(10);
		try
		{
			txtContact.setText(re.getString("contact_no"));
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		
		lblImage_1 = new JLabel("");
		lblImage_1.setIcon(new ImageIcon(SignupPage.class.getResource("/images/photo.png")));
		lblImage_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImage_1.setBounds(512, 277, 128, 128);
		add(lblImage_1);
		String pth="";
		try 
		{
			pth = re.getString("user_image");
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		//pth=pth.replace("#Slash#", "\\");
		if (!pth.isEmpty())
		lblImage_1.setIcon(ResizeImage(pth));
		
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnM.setBounds(205, 229, 39, 23);
		add(rdbtnM);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnF.setBounds(326, 229, 39, 23);
		add(rdbtnF);
		
		String gdr="";
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnM);
		group.add(rdbtnF);
		try
		{
			gdr=re.getString("gender");
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		if (gdr.contentEquals("M"))
		{
			ButtonModel model=rdbtnM.getModel();
			group.setSelected(model, true);
		}
		else
		{
			ButtonModel model=rdbtnF.getModel();
			group.setSelected(model, true);
		}
		
		try 
		{
			String date=re.getString("birthdate");
			splitDate=date.split("-");
		}
		catch (SQLException e2) 
		{
			e2.printStackTrace();
		}
		
		JComboBox dd = new JComboBox();
		dd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e)
			{
				dd.removeItem(splitDate[2]);
			}
		});
		dd.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		dd.addItem(splitDate[2]);
		
		for (int i=1;i<=31;i++)
		{
			dd.addItem(i);
		}
		dd.setBounds(205, 255, 47, 20);
		add(dd);
		
		
		JComboBox mm = new JComboBox();
		mm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0)
			{
				mm.removeItem(splitDate[1]);
			}
		});
		mm.setFont(new Font("Tahoma", Font.BOLD, 11));
		mm.addItem(splitDate[1]);
		
		for(int j=1;j<=12;j++)
		{
			mm.addItem(j);
		}
		mm.setBounds(255, 255, 47, 20);
		add(mm);
		
		
		Timestamp year=new Timestamp(System.currentTimeMillis());
		int yer=year.getYear();
		
		JComboBox yyyy = new JComboBox();
		yyyy.setFont(new Font("Tahoma", Font.BOLD, 11));
		yyyy.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				yyyy.removeItem(splitDate[0]);
			}
			@Override
			public void focusLost(FocusEvent e)
			{
				String str=yyyy.getSelectedItem().toString();
				age=yer-((Integer.parseInt(str))-1900);
			}
		});
		
		yyyy.addItem(splitDate[0]);
		
		yyyy.setBounds(305, 255, 60, 20);
		for (int i=(1900+(yer-18));i>=((1900+yer)-100);i--)
		{
			yyyy.addItem(i);
		}
		add(yyyy);
		
		JRadioButton rdbtnY = new JRadioButton("Y");
		rdbtnY.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnY.setBounds(205, 279, 39, 23);
		add(rdbtnY);
		
		JRadioButton rdbtnN = new JRadioButton("N");
		rdbtnN.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnN.setBounds(326, 279, 39, 23);
		add(rdbtnN);
		
		ButtonGroup group2=new ButtonGroup();
		group2.add(rdbtnY);
		group2.add(rdbtnN);
		String marrige="";
		try
		{
			marrige=re.getString("marital_status");
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		if (marrige.contentEquals("Y"))
		{
		ButtonModel model2=rdbtnY.getModel();
		group2.setSelected(model2, true);
		}
		else
		{
			ButtonModel model2=rdbtnN.getModel();
			group2.setSelected(model2, true);
		}
		
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(100, 308, 95, 14);
		add(lblAddress);
		
		
		JLabel lblProfilePicture = new JLabel("Profile picture");
		lblProfilePicture.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProfilePicture.setBounds(534, 255, 129, 14);
		add(lblProfilePicture);
		
		
		
		JLabel lblMaritalStatus = new JLabel("Marital Status");
		lblMaritalStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaritalStatus.setBounds(100, 283, 95, 14);
		add(lblMaritalStatus);
		
		JLabel lblContactNo = new JLabel("Contact No#");
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContactNo.setBounds(100, 388, 95, 14);
		add(lblContactNo);
		
		JLabel lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthdate.setBounds(100, 258, 95, 14);
		add(lblBirthdate);
		
	
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGender.setBounds(100, 233, 95, 14);
		add(lblGender);

	
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setIcon(new ImageIcon(EditUserAccount.class.getResource("/images/folder-search.png")));
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
					try
					{
						lblImage_1.setIcon(ResizeImage(re.getString("user_image")));
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "No Image is seleted");
				}
			}
		});
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.setBounds(534, 409, 110, 30);
		add(btnBrowse);
		
		
		
		JButton btnsubmit = new JButton("Next");
		btnsubmit.setHorizontalAlignment(SwingConstants.LEFT);
		btnsubmit.setIcon(new ImageIcon(EditUserAccount.class.getResource("/pmsicons/1446334685_go-next.png")));
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
					lblDate.setVisible(false);
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
						lblLoading.setVisible(true);
						birthDate=yyyy.getSelectedItem().toString()+"-"+mm.getSelectedItem().toString()+"-"+dd.getSelectedItem().toString();
						System.out.println(birthDate);
						String gen=null;
						String mer=null;
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
						if (isImage==true)
							{
								System.out.println("setFile\t"+selectedFile+"\t"+ absolutFile);
								CustomerVerifier.setFile(selectedFile, absolutFile);
								//path=path.replace("\\", "#Slash#");
							}
							else
							{
								path=mainPage.getPath();
							}
						//CustomerVerifier.email_code(new BigInteger(130, random).toString(32),txtemail.getText());
						CustomerVerifier.sign_up(txtfname.getText(),txtlname.getText(),gen,mer,birthDate,age,mainPage.getUserType(),txtContact.getText(),txtAddress.getText() ,txtemail.getText(),pwdTxtpassword.getText(),path);
						mainPage.setCustomerVerifier();
					}				
			}
		});
		btnsubmit.setBounds(740, 580, 110, 30);
		add(btnsubmit);
		
		JButton btnBack = new JButton("Cancel");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIcon(new ImageIcon(EditUserAccount.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				mainPage.setEditMode(false);
				if (mainPage.getUserType().contentEquals("Customer"))
					mainPage.setCustomerPage();
				else if (mainPage.getUserType().contentEquals("Manager"))
					mainPage.setManagerPage();
				else if (mainPage.getUserType().contentEquals("Admin"))
					mainPage.setAdminPage();
				else if (mainPage.getUserType().equals("Deweller"))
					mainPage.setDewellerPage();
			}
		});
		btnBack.setBounds(620, 580, 110, 30);
		add(btnBack);
		
		JLabel lblEditUserInfo = new JLabel("Edit User Info");
		lblEditUserInfo.setIcon(new ImageIcon(EditUserAccount.class.getResource("/images/edit-user.png")));
		lblEditUserInfo.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblEditUserInfo.setBounds(10, 11, 401, 80);
		add(lblEditUserInfo);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(EditUserAccount.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		

	}

	

	/*
	 *Copy file 
	 */		
		private static void copyFile(File source,File dest) 
		{
				try 
				{
					System.out.println("copy fun\t"+source.toPath());
					System.out.println("copy fun\t"+dest.toPath());
					Files.copy(source.toPath(),dest.toPath());
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
	
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
