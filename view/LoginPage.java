package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.CommercialProperty;
import controller.LogInController;
import controller.ResidentialProperty;


@SuppressWarnings("serial")
public class LoginPage extends JPanel
{
	private JLabel lblstatus;
	private JScrollPane scrollPane;
	private JLabel lblcurrentstate ;
	private JLabel lblprice;
	private JLabel lblImage ;
	private JLabel lblid ;
	//private JLabel lblstatus;
	private JLabel lbltype ;
	private JLabel lbllocation ;
	private JLabel lblarea ;
	private JList<?> list;
	private ResultSet rs;
	private String selectvalue;
	private ResidentialProperty rp;
	private CommercialProperty cp;
	
	LogInController log_obj=new LogInController();
	private JTextField txtemail;
	private JPasswordField pwdTxtpassword;
	//private String type;
	/**
	 * Create the panel.
	 * @param mainPage 
	 * @throws SQLException 
	 */
	public LoginPage(MainPage mainPage) throws SQLException
	{
		super();
		selectvalue="0";
		rp=new ResidentialProperty();
		cp=new CommercialProperty();
		
		this.setSize(900,660);
		setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(549, 451, 76, 14);
		add(lblEmail);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(549, 497, 76, 14);
		add(lblPassword);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblYourEmailOr = new JLabel("Your email or password is incorrect");
		lblYourEmailOr.setBounds(549, 522, 269, 20);
		add(lblYourEmailOr);
		lblYourEmailOr.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Error_Symbol.png")));
		lblYourEmailOr.setForeground(Color.RED);
		lblYourEmailOr.setVisible(false);
		
		txtemail = new JTextField();
		txtemail.setBounds(635, 449, 180, 20);
		add(txtemail);
		txtemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtemail.setColumns(10);
		//btnMenu.disable();
		
		pwdTxtpassword = new JPasswordField();
		pwdTxtpassword.setBounds(635, 491, 180, 20);
		add(pwdTxtpassword);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(724, 544, 89, 23);
		add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) 
			{
				//       Log In Test Controller 
				//test.test(txtemail.getText(), pwdTxtpassword.getText());
				ResultSet re=log_obj.login_check(txtemail.getText(), pwdTxtpassword.getText());
				if(re!=null)
				{	
				String type="";
				String path="";
				String userEmail="";
				int userid=0;
				try
				{
					while(re.next())
					{
						type=re.getString("user_type");
						path=re.getString("user_image");
						userEmail=re.getString("user_email");
						userid=re.getInt("user_id");
					}
				} 
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
				//path=path.replace("#Slash#", "\\");
				mainPage.setPath(path);
				mainPage.setUserType(type);
				mainPage.setUserId(userid);
				mainPage.setEmail(userEmail);
				if (type.contentEquals("Customer"))
					mainPage.setCustomerPage();
				else
				if(type.contentEquals("Admin"))
					mainPage.setAdminPage();
				else
				if (type.contentEquals("Manager"))
					mainPage.setManagerPage();
				else
				if (type.contentEquals("Deweller"))
					mainPage.setDewellerPage();
				else
					lblYourEmailOr.setVisible(true);
				}
			}
		});
		btnLogIn.setForeground(Color.BLACK);
		btnLogIn.setBackground(UIManager.getColor("Button.background"));
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(635, 544, 89, 23);
		add(btnSignUp);
		btnSignUp.setForeground(Color.BLACK);
		btnSignUp.setBackground(UIManager.getColor("Button.background"));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				mainPage.setSignupPage();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblId = new JLabel("Properties...");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(36, 172, 76, 14);
		add(lblId);
		
		JLabel lblPropertyId1 = new JLabel("Property ID");
		lblPropertyId1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPropertyId1.setBounds(107, 186, 75, 20);
		add(lblPropertyId1);
		
		JLabel lblType1 = new JLabel("Type");
		lblType1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType1.setBounds(107, 217, 75, 19);
		add(lblType1);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArea.setBounds(107, 244, 75, 19);
		add(lblArea);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(107, 274, 75, 19);
		add(lblLocation);
		
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(107, 304, 46, 14);
		add(lblStatus);
		
		 lblid = new JLabel("ID");
		 lblid.setForeground(new Color(0, 0, 0));
		lblid.setBounds(210, 186, 75, 20);
		add(lblid);
		
		 lbltype = new JLabel("Commercial or Residential");
		 lbltype.setForeground(new Color(0, 0, 0));
		lbltype.setBounds(210, 217, 136, 19);
		add(lbltype);
		
		lblarea = new JLabel("area(marla)");
		lblarea.setForeground(new Color(0, 0, 0));
		lblarea.setBounds(210, 244, 123, 19);
		add(lblarea);
		
		lbllocation = new JLabel("location");
		lbllocation.setForeground(new Color(0, 0, 0));
		lbllocation.setBounds(210, 274, 185, 19);
		add(lbllocation);

		
		lblstatus = new JLabel("status");
		lblstatus.setForeground(new Color(0, 0, 0));
		lblstatus.setBounds(210, 304, 109, 14);
		add(lblstatus);
		
		JLabel lblCurrentStatus = new JLabel("Current Status");
		lblCurrentStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentStatus.setBounds(107, 329, 86, 14);
		add(lblCurrentStatus);
		
		lblcurrentstate = new JLabel("Current Status");
		lblcurrentstate.setForeground(new Color(0, 0, 0));
		lblcurrentstate.setBounds(210, 329, 154, 14);
		add(lblcurrentstate);
		
		JLabel lblPrice = new JLabel("Price/Rent");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(107, 356, 75, 14);
		add(lblPrice);
		
		
		
		JButton btnNewButton = new JButton("Forget your Password");
		btnNewButton.setBounds(635, 578, 178, 23);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mainPage.setChangePassword();
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setForeground(Color.GRAY);
		
		lblprice = new JLabel("Price");
		lblprice.setForeground(new Color(0, 0, 0));
		lblprice.setBounds(210, 356, 154, 14);
		add(lblprice);
		
		lblImage = new JLabel(" ");
		lblImage.setBounds(376, 172, 298, 242);
		add(lblImage);
		lblImage.setBackground(new Color(70, 130, 180));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon(SearchPanel.class.getResource("/pmsicons/home-search.png")));
		scrollPane= new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setLocation(39, 197);
		scrollPane.setSize(50, 202);
		this.add(scrollPane);

		JLabel lblMsg = new JLabel("You Must Log on to Book Property");
		lblMsg.setForeground(Color.RED);
		lblMsg.setVisible(false);
		lblMsg.setBounds(133, 415, 185, 24);
		add(lblMsg);
		JButton btnBookProperty = new JButton("Book Property");
		btnBookProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMsg.setVisible(true);
			}
		});
		btnBookProperty.setBounds(154, 381, 131, 23);
		add(btnBookProperty);
		
		JLabel lblLogin = new JLabel("");
		lblLogin.setIcon(new ImageIcon(LoginPage.class.getResource("/images/1449014208_login.png")));
		lblLogin.setBounds(399, 452, 123, 128);
		add(lblLogin);
		
		
		JLabel lblMono = new JLabel("");
		lblMono.setIcon(new ImageIcon(LoginPage.class.getResource("/images/Mhome.png")));
		lblMono.setBounds(10, 11, 135, 135);
		add(lblMono);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(LoginPage.class.getResource("/images/LoginBackground.png")));
		lblImage.setBounds(0, 0, 1000, 670);
		add(lblImage);	
	    setLayout(null);

		String[] array = rp.searchProperty(0,0,0,0,0,"0","0");
		if(array!=null)
		scrollList(array);
		listSelected();
	//	formatButtons();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void scrollList(String [] array)
	{
		list=new JList(array);
		scrollPane.setViewportView(list);
		list.setBackground(new Color(0, 128, 128));
		list.setVisibleRowCount(15);
		list.addListSelectionListener(new ListSelectionListener() 
		 {
		 		@Override
		 		public void valueChanged(ListSelectionEvent arg0) {
		 			selectvalue = list.getSelectedValue().toString();
		 			if(!selectvalue.isEmpty())
		 			listSelected();
		 		}
	        });
		list.setVisible(true);
	}
	public void listSelected()
	{
		rs= rp.oneProperty(selectvalue);
		if(rs!=null)	
		{
			try {
				String type="null";
				String status="null",path="0";
				while(rs.next())
				{
				lblid.setText(selectvalue);
				type=rs.getString("type");
				lbltype.setText(type);
				status=rs.getString("status");
				lblstatus.setText(status);
				lblarea.setText(rs.getInt("area")+"");
				String location=rs.getString("location");
				lbllocation.setText(location);
				path=rs.getString("image");
				if(!path.equals("0"))
					lblImage.setIcon(this.ResizeImage(path));
				}
				status=lblstatus.getText();
				if(type.equals("Residential"))
				{
					rp.setStatus(status);
					rs=(com.mysql.jdbc.ResultSet) rp.oneResidential(Integer.parseInt(selectvalue));
					if(rs.next())
					{
						if(status.equals("ForRent"))
						{
							lblprice.setText(rs.getInt("rent")+"");
							if(rs.getBoolean("isonrent")==true)
								lblcurrentstate.setText("On Rent");
							else
								lblcurrentstate.setText("Available For Rent");
						}else if(status.equals("ForSale"))
						{
							lblprice.setText(rs.getInt("price")+"");
							if(rs.getBoolean("sold")==true)
								lblcurrentstate.setText("Sold");
							else
								lblcurrentstate.setText("Available For Purchase");
						}
						else
						{
							lblcurrentstate.setText("no info");
							lblprice.setText("0");
						}
					}
				}
				else
					{
					cp.setStatus(status);
					ResultSet rs3=(com.mysql.jdbc.ResultSet) cp.oneCommercial(Integer.parseInt(selectvalue));
					if(rs3.next())
					{
						if(status.equals("ForRent"))
						{
							if(rs3.getBoolean("isonrent")==true)
								lblcurrentstate.setText("On Rent");
							else
							lblcurrentstate.setText("Available For Rent");
							lblprice.setText(rs3.getInt("rent")+"");
						}
						else if(status.equals("ForSale"))
						{
							lblprice.setText(rs3.getInt("price")+"");
							if(rs3.getBoolean("sold")==true)
								lblcurrentstate.setText("Sold");
							else
								lblcurrentstate.setText("Available For Purchase");
						}
						else
						{
							lblcurrentstate.setText("no info");
							lblprice.setText("0");
						}
					}
					}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
	}
	 public ImageIcon ResizeImage(String path)
	 {
	 	// path=path.replace("__", "\\");
	 	ImageIcon myImage =new ImageIcon(path);
	 	Image img =myImage.getImage();
	 	Image newImg =img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
	 	ImageIcon image=new ImageIcon(newImg);
	 	return image;
	 }
}
