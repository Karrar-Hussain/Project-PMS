package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import controller.DeleteUserController;
import controller.SearchUserCont;
import controller.UserAccountInfo;

@SuppressWarnings("serial")
public class SearchUser extends JPanel
{
	UserAccountInfo info_obj=new UserAccountInfo();
	private JTextField txtEmilid;
	private JTextField txtName;
	private JTextField txtAddress;
	private JLabel lblProfile_1;
	ResultSet re;
	SearchUserCont c_obj=new SearchUserCont();
	private JCheckBox rdbtnUserId;
	private JCheckBox rdbtnUserName;
	private JCheckBox rdbtnUserType;
	private JLabel lblUserName;
	private JLabel lblUserType;
	private JLabel lblContactNo;
	private JLabel lblUserAddress;
	private JLabel lblProfile;
	private JButton btnDelete;
	private MainPage _parent;
	private DeleteUserController d_obj=new DeleteUserController();
	
	ResultSet re2;
	@SuppressWarnings("rawtypes")
	DefaultListModel model = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private JComboBox comboType;
	@SuppressWarnings("rawtypes")
	JList list;
	/**
	 * Create the panel.
	 * @param mainPage 
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchUser(MainPage mainPage) throws SQLException 
	{
		setBounds(new Rectangle(0, 0, 900, 660));
		setLayout(null);
		_parent=mainPage;
		
		list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String selectedvalue="0";
				if(list.getSelectedValue()!=null)
				selectedvalue=list.getSelectedValue().toString();
				if(selectedvalue!=null)
					listSelected(selectedvalue);
			}
		});
		list.setForeground(Color.WHITE);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.activeCaption, SystemColor.textHighlight, SystemColor.activeCaption));
		list.setBackground(new Color(0, 139, 139));
		list.setVisible(false);
		list.setBounds(43, 336, 260, 180);
		add(list);
		
		
		lblProfile = new JLabel("");
		lblProfile.setIcon(new ImageIcon(SearchUser.class.getResource("/images/profile.png")));
		lblProfile.setBounds(20, 11, 70, 78);
		add(lblProfile);
		
		//System.out.println("asdfghj"+mainPage.getPath());
		
		if (!mainPage.getPath().isEmpty())
		{
			lblProfile.setIcon(profileImage(mainPage.getPath()));
		}
		
		JLabel lblSearchuser = new JLabel("Search User");
		lblSearchuser.setForeground(Color.WHITE);
		lblSearchuser.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblSearchuser.setBounds(100, 11, 187, 78);
		add(lblSearchuser);
		
		lblProfile_1 = new JLabel("");
		lblProfile_1.setIcon(new ImageIcon(SearchUser.class.getResource("/images/user_male.png")));
		lblProfile_1.setBounds(553, 190, 100, 120);
		add(lblProfile_1);
		
		lblUserName = new JLabel("User Name:  ");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserName.setBounds(553, 321, 200, 14);
		add(lblUserName);
		
		lblContactNo = new JLabel("Contact No#");
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContactNo.setBounds(553, 339, 200, 14);
		add(lblContactNo);
		
		lblUserType = new JLabel("User Type:");
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserType.setBounds(553, 361, 200, 14);
		add(lblUserType);
		
		lblUserAddress = new JLabel("User Address");
		lblUserAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserAddress.setBounds(553, 386, 141, 14);
		add(lblUserAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
		txtAddress.setEditable(false);
		txtAddress.setBounds(553, 411, 141, 45);
		add(txtAddress);
		txtAddress.setColumns(10);
		
		txtEmilid = new JTextField();
		txtEmilid.setBounds(162, 175, 141, 23);
		add(txtEmilid);
		txtEmilid.setColumns(10);
		
		
		txtName = new JTextField();
		txtName.setBounds(162, 209, 141, 23);
		add(txtName);
		txtName.setColumns(10);
		
		rdbtnUserId = new JCheckBox("User Email");
		rdbtnUserId.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnUserId.setBounds(43, 175, 109, 23);
		add(rdbtnUserId);
		
		rdbtnUserName = new JCheckBox("User Name");
		rdbtnUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnUserName.setBounds(43, 209, 109, 23);
		add(rdbtnUserName);
		
		rdbtnUserType = new JCheckBox("User Type");
		rdbtnUserType.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnUserType.setBounds(43, 243, 109, 23);
		add(rdbtnUserType);
		
		
		comboType = new JComboBox();
		comboType.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboType.setBounds(162, 243, 141, 22);
		add(comboType);
		comboType.addItem("Select User Type");
		comboType.addItem("Admin");
		comboType.addItem("Manager");
		comboType.addItem("Customer");
		comboType.addItem("Deweller");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(SearchUser.class.getResource("/pmsicons/1446305467_Search.png")));
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(list!=null)
				list.revalidate();
				try 
				{
					searachUsr();
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(193, 289, 110, 30);
		add(btnSearch);

		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(SearchUser.class.getResource("/pmsicons/1446489787_free-27.png")));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				deleteAccount();	
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(553, 580, 110, 30);
		add(btnDelete);
		btnDelete.setVisible(false);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(SearchUser.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		JButton btnBack = new JButton("Back");
		btnBack.setIcon(new ImageIcon(SearchUser.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				mainPage.switchPanel();
//				if (mainPage.getUserType().contentEquals("Manager"))
//				{
//					
//					mainPage.setManagerPage();
//				}
//				else if (mainPage.getUserType().contentEquals("Admin"))
//				{
//					mainPage.setAdminPage();
//				}
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(43, 580, 90, 30);
		add(btnBack);
		btnBack.setVisible(false);
		JButton btnJasper = new JButton("Reports");
		btnJasper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewReport("user.jrxml","E:\\user.pdf");
			}
		});
		btnJasper.setBounds(214, 580, 90, 30);
		add(btnJasper);

	}
	public void viewReport(String jasperfile,String outputfile)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pmsdb", "root", "12345");  
		    InputStream input= new FileInputStream(jasperfile);
		    JasperDesign jd  = JRXmlLoader.load(input);
		    JasperReport jr = JasperCompileManager.compileReport(jd);
		    JasperPrint  jp = JasperFillManager.fillReport(jr, new HashMap<String, Object>(),con);
		    JasperViewer.viewReport(jp);
		    JasperExportManager.exportReportToPdfFile(jp, outputfile);
			}
		catch(Exception e1){
		    JOptionPane.showMessageDialog(null, e1);
		}
	}
	@SuppressWarnings({ "unchecked" })
	public void searachUsr() throws SQLException
	{
		if(model!=null)
		model.clear();
		String []arr;
		String email="null",name="null",type="null";
		if (rdbtnUserId.isSelected() || rdbtnUserName.isSelected() || rdbtnUserType.isSelected())
		{
			if(rdbtnUserId.isSelected())
				email=txtEmilid.getText();
			if(rdbtnUserName.isSelected())
				name=txtName.getText();
			if(rdbtnUserType.isSelected())
				type=comboType.getSelectedItem().toString();		
			arr=c_obj.searchUser(email,name,type);
			if (arr!=null)
			{
				btnDelete.setVisible(true);
				for(String val :arr)
				{
					model.addElement(val);
				}
				list.setVisible(true);
			}
			else 
			{
				
				JOptionPane.showMessageDialog(null, "Result Not Found","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Select Search Specifier","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String[] array(ResultSet re) throws SQLException
	{		
		int size=0;
		@SuppressWarnings("rawtypes")
		LinkedList l1=new LinkedList();
		while(re.next())
		{
			size++;
			l1.add(re.getString("user_email"));			
		}
		String []arr=new String[size];
		l1.toArray(arr);
		return arr;
	}
	
	/*
	 * Image Resize
	 */ 
	 public ImageIcon ResizeImage(String path)
	{
		ImageIcon myImage =new ImageIcon(path);
		Image img =myImage.getImage();
		Image newImg =img.getScaledInstance(lblProfile_1.getWidth(), lblProfile_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);
		return image;
	}
		/*
		 * Image Resize2
		 */ 
		 public ImageIcon profileImage(String path)
		{
			ImageIcon myImage =new ImageIcon(path);
			Image img =myImage.getImage();
			Image newImg =img.getScaledInstance(lblProfile.getWidth(), lblProfile.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon image=new ImageIcon(newImg);
			return image;
		}
	
	@SuppressWarnings("rawtypes")
	public void JList(ListModel model)
	{
	}
	public void listSelected(String selectedvalue)
	{
		if(!selectedvalue.equals("0"))
		{
				try
			
			{
				if(list!=null && info_obj!=null)
				re2=(ResultSet) info_obj.userAcconutInfo(selectedvalue);
				if(re2!=null)
				{	lblUserName.setText("Name:     "+re2.getString("first_name")+" "+re2.getString("last_name"));
					lblContactNo.setText("ContactNo#"+re2.getString("contact_no"));
					lblUserType.setText("User Type: "+re2.getString("user_type"));
					lblProfile_1.setIcon(ResizeImage(re2.getString("user_image")));
					lblProfile_1.revalidate();
					txtAddress.setText(re2.getString("address"));
				}
			} 
				catch (SQLException e1) 
			{
				e1.printStackTrace();
				System.out.println(""+e1.getMessage());
			}
		}
	}
	
	public void deleteAccount()
	{
		if (!list.isSelectionEmpty())
		{
			System.out.println(list.getSelectedValue().toString());
			String email=list.getSelectedValue().toString();
			System.out.println(_parent.getUserType()+"\t"+email);
			if (list.getSelectedValue().toString().equals(_parent.getEmail()) || list.getSelectedValue().toString().equals("Customer") || (list.getSelectedValue().toString().equals("Admin") && _parent.getUserType().equals("Manager")))
			{
				JOptionPane.showMessageDialog(null, "You are not allowed to DELETE this Account","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				int status=JOptionPane.showConfirmDialog(null, "Are You Realy Want to DELETE Account");
				if (status==0)
				{
					try
					{	
						d_obj.deleteUser(email);
						searachUsr();
						list.revalidate();
						list.repaint();
						list.setOpaque(true);
						lblProfile_1.setIcon(new ImageIcon(SearchUser.class.getResource("/images/user_male.png")));
						lblUserName.setText("User Name:\t");
						lblUserType.setText("User Type:\t");
						lblContactNo.setText("Contact No#\t");
						txtAddress.setText("");
					} 
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}




