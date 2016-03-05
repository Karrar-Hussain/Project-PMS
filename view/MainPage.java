package view;
import static org.junit.Assert.assertFalse;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import model.CreatePMSDB;

import com.google.zxing.Dimension;

import controller.Booking;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;

@SuppressWarnings("serial")
public class MainPage extends JFrame {
public JPanel footer ;
public JPanel header ;
private String userEmail;
private boolean editMode;
private static String type="";
private static String setType="";
private static String path="";
private Stack<String> panelstack;
public JButton btnBack;
private int userid;
/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try
		{
			MainPage frame = new MainPage();
			frame.setVisible(true);
			frame.setLoginPage();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainPage() throws SQLException 
	{
		super();
		panelstack=new Stack<String>();
		setBounds(new Rectangle(0, 0, 900, 660));
		try
		{
			UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Your UI Manger is not load", "UI Manger", JOptionPane.ERROR_MESSAGE);
		}
		Booking bookP=new Booking();
		bookP.traverseBooking();

		CreatePMSDB pms=new CreatePMSDB();
		assertFalse(pms.isDbExist());
		btnBack = new JButton("Back");
		getContentPane().setVisible(true);
		header = new JPanel();
		header.setBorder(new LineBorder(new Color(0, 0, 0)));
		header.setBounds(0, 0, 900, 100);
		header.setBackground(new Color(95, 158, 160));
		getContentPane().add(header);
		header.setLayout(null);
		footer = new JPanel();
		footer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		footer.setBounds(0, 550, 900, 80);
		footer.setBackground(new Color(95, 158, 160));
		getContentPane().add(footer);
		footer.setLayout(null);
		this.setHeaderFooter(null);
		setTitle("Property Managment System");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/pmsicons/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 660);
		setLocation(50, 50);
		setSize(900, 660);
		getContentPane().setLayout(null);
		this.footer.setVisible(true);
		this.header.setVisible(true);
	}
	public void pushPanel(String pane)
	{
		panelstack.push(pane);
	}

	public void switchPanel()
	{
		String switchpanel="0";
		if(!panelstack.isEmpty())
		 switchpanel=panelstack.pop();
	        switch (switchpanel) 
	        {
	            case "Manager":  
	     System.out.println("ManagerPage Poped");
	            	this.setManagerPage();
	                     break;
	            case "Dweller":  
	       	     System.out.println("ManagerPage Poped");
	            	this.setDewellerPage();
	                     break;
	            case "PropertyMenu": this.setPropertyMenu();
       	     System.out.println("PropertyMenu Poped");
	                     break;
	            case "Customer":  this.setCustomerPage();
	                     break;
	            case "Admin": 	
           	     System.out.println("Admin Page Poped");
	            	this.setAdminPage();
	                     break;
	            case "MManager":  this.setManagerPane();
       	     System.out.println("ManagerPeem  Poped");
	                     break;
	        }
	}
	public void setPanel(JPanel jpane)
	{
		this.setContentPane(jpane);	
	}

	public void UserSetType(String typ)
	{
		setType=typ;
	}
	public String UsergetType()
	{
		return setType;
	}
	public void setUserId(int userid)
	{
		this.userid=userid;
	}
	public int getUserId()
	{
		return userid;
	}
	public void setUserType(String typ)
	{
		type=typ;
	}
	
	public String getUserType()
	{
		return type;
	}
	
	public void setHeaderFooter(JPanel jpane)
	{
		header.setBorder(new LineBorder(new Color(0, 0, 0)));
		header.setBounds(0, 0, 900, 100);
		header.setBackground(new Color(95, 158, 160));
		header.setLayout(null);
		
		footer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		footer.setBounds(0, 550, 900, 80);
		footer.setBackground(new Color(95, 158, 160));

		footer.setLayout(null);
		header.setLayout(null);
		footer.setVisible(true);
		header.setVisible(true);
		
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIcon(new ImageIcon(SearchPanel.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setBounds(30, 30, 90, 30);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				switchPanel();
//				if (getUserType().equals("Customer"))
//					setCustomerPage();
//				else if(getUserType().equals("Admin"))
//					setAdminPage();
//				else if (getUserType().equals("Manager"))
//					setManagerPage();
//				else if (getUserType().equals("Deweller"))
//					setDewellerPage();
			}
		});
		footer.add(btnBack);
		if(jpane!=null)
		{
			jpane.add(header);
			jpane.add(footer);
		}
	}
	public void setMap()
	{
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		Map propertyMap=new Map(this);
		propertyMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		propertyMap.setSize(width,height-50);
		propertyMap.setLocation(0,0);
		this.setSize(width,height-50);
		this.setContentPane(propertyMap);
		this.setLocationRelativeTo(null);
	}
	public void setLoginPage() throws SQLException
	{
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		this.btnBack.setVisible(false);
		LoginPage loginpane=new LoginPage(this);
		loginpane.setBackground(new Color(255, 255, 240));
		loginpane.setBorder(new LineBorder(new Color(0, 0, 0)));
		loginpane.setSize(900,660);
		loginpane.setLocation(0,0);
		this.setHeaderFooter(loginpane);
		this.setContentPane(loginpane);
	}

	public void setAddPanel()
	{
		panelstack.push("PropertyMenu");
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		AddPanel addpane=new AddPanel(this);
		addpane.setSize(900,660);
		this.setHeaderFooter(addpane);
		this.setContentPane(addpane);
	}
	public void setEditPanel()
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		EditPanel editpane=new EditPanel(this);
		editpane.setSize(900,660);
		this.setHeaderFooter(editpane);
		this.setContentPane(editpane);
	}
	public void setViewPanel() throws SQLException
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		ViewPanel viewpane = new ViewPanel(this);
		viewpane.setSize(900,660);
		this.setHeaderFooter(viewpane);
		this.setContentPane(viewpane);
	}		
	public void setSearchPanel() throws SQLException
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		SearchPanel searchpane = new SearchPanel(this);
			searchpane.setSize(900,660);
			this.setHeaderFooter(searchpane);
			this.setContentPane(searchpane);
	}
	public void setDeletePanel()
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		DeletePanel deletepane=new DeletePanel(this);
		deletepane.setSize(900,660);
		deletepane.setLocation(0,0);
		this.setHeaderFooter(deletepane);
		
		this.setContentPane(deletepane);
	}

	public void setBookingPanel() throws SQLException {
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		BookingPanel bookingpane=new BookingPanel(this);
		bookingpane.setSize(900,660);
		bookingpane.setLocation(0,0);
		this.setHeaderFooter(bookingpane);
		this.setContentPane(bookingpane);
	}	
	public void setSignupPage()
	{	

		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		SignupPage signuppane=new SignupPage(this);
		signuppane.setSize(900,660);
		signuppane.setLocation(0,0);
		this.setHeaderFooter(signuppane);
		this.setContentPane(signuppane);
	}
	
	public void setCustomerPage()
	{ 
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		CustomerPage customerpane=new CustomerPage(this);
		customerpane.setSize(900,660);
		customerpane.setLocation(0,0);
		this.setHeaderFooter(customerpane);
		this.setContentPane(customerpane);
	}
	
	public void setCustomerVerifier()
	{
		 
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		CustomerVerifier customerverifierpane=new CustomerVerifier(this);
		customerverifierpane.setSize(900,660);
		customerverifierpane.setLocation(0,0);
		this.setHeaderFooter(customerverifierpane);
		this.setContentPane(customerverifierpane);
	}
	
	public void setChangePassword()
	{
		 
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		ChangePassword chgpasspane=new ChangePassword(this);
		chgpasspane.setSize(900,660);
		chgpasspane.setLocation(0,0);
		this.setHeaderFooter(chgpasspane);
		this.setContentPane(chgpasspane);
	}
	public void setManagerPage()
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		ManagerPage managerpane=new ManagerPage(this);
		managerpane.setSize(900,660);
		managerpane.setLocation(0,0);
		this.setHeaderFooter(managerpane);	
		this.setContentPane(managerpane);	
	}
	public void setPropertyMenu()
	{	
		this.btnBack.setVisible(false);
		///if(count!=0)
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		PropertyMenu propertymenu=new PropertyMenu(this);
		propertymenu.setSize(900,660);
		this.setContentPane(propertymenu);
	}

	public void setAdminPage()
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		AdminPage adminpane=new AdminPage(this);
		adminpane.setSize(900,660);
		adminpane.setLocation(0,0);
		this.setHeaderFooter(adminpane);
		this.setContentPane(adminpane);
	}
	
	void setDewellerPage()
	{
		panelstack.push("Dweller"); 	 
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		DewellerPage dwellerpane=new DewellerPage(this);
		dwellerpane.setSize(900,660);
		dwellerpane.setLocation(0,0);
		this.setHeaderFooter(dwellerpane);
		this.setContentPane(dwellerpane);
	}

	public void setEditAccount()
	{
		btnBack.setVisible(false);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		EditUserAccount edituserpane=new EditUserAccount(this);
		edituserpane.setSize(900,660);
		edituserpane.setLocation(0,0);
		this.setHeaderFooter(edituserpane);
		this.setContentPane(edituserpane);
	}
	
	public void setSearchUser() throws SQLException
	{
		btnBack.setVisible(true);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();

		SearchUser searchuserpane = new SearchUser(this);
			searchuserpane.setSize(900,660);
			searchuserpane.setLocation(0,0);
			this.setHeaderFooter(searchuserpane);
			this.setContentPane(searchuserpane);
		
	}
	
	public  void setP1()
	{
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		ReportProblemView reportprobpane = new ReportProblemView(this);
		this.setContentPane(reportprobpane);
		this.revalidate();
		this.repaint();
		this.setHeaderFooter(reportprobpane);
		this.setContentPane(reportprobpane);
	}
	public void loadPanels()
	{

	}
	public void setManagerPane()
	{
		panelstack.push("MManager"); 
		btnBack.setVisible(true);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		ManagerPEEM managerpeempane = new ManagerPEEM(this);
		this.setContentPane(managerpeempane);
		this.revalidate();
		this.repaint();
		this.setHeaderFooter(managerpeempane);
		this.setContentPane(managerpeempane);
	}
	public void setP4(String []arr)
	{
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		DeleteManagerView deletemanagerpane = new DeleteManagerView(this,arr);
		this.setContentPane(deletemanagerpane);
		this.revalidate();
		this.repaint();
		this.setHeaderFooter(deletemanagerpane);
		this.setContentPane(deletemanagerpane);
	}
	public void setFixMeeting(String []arr)
	{
		//btnBack.setVisible(true);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		/*FixMeetingView maintenancepane = new FixMeetingView(this,arr);
		this.setContentPane(maintenancepane);
		this.revalidate();
		this.repaint();
		this.setHeaderFooter(maintenancepane);
		this.setContentPane(maintenancepane);*/
	}
	public void MaintainessPanel()
	{
		//btnBack.setVisible(true);
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		MaintainessView maintenancepane = new MaintainessView(this);
		this.setContentPane(maintenancepane);
		this.revalidate();
		this.repaint();
		this.setHeaderFooter(maintenancepane);
		this.setContentPane(maintenancepane);
	}
	public void DeleteMaintainessPanel(String []arr)
	{
		getRootPane().getContentPane().removeAll();
		getRootPane().getContentPane().revalidate();
		getRootPane().getContentPane().repaint();
		this.header.removeAll();
		this.header.revalidate();
		this.header.repaint();
		this.footer.removeAll();
		this.footer.revalidate();
		this.footer.repaint();
		DeleteMaintainessView delmaintenancepane = new DeleteMaintainessView(this,arr);
		this.setContentPane(delmaintenancepane);
		this.revalidate();
		this.repaint();
		this.setHeaderFooter(delmaintenancepane);
		this.setContentPane(delmaintenancepane);
	}
	
	public void setPath(String pth)
	{
		path=pth;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setEmail(String email)
	{
		userEmail=email;
	}
	
	public String getEmail()
	{
		return userEmail;
	}
	
	public void setEditMode(boolean mode)
	{
		editMode=mode;
	}
	
	public boolean getEditMode()
	{
		return editMode;
	}
}
