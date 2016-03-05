package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

@SuppressWarnings("serial")
public class ManagerPage extends JPanel {

	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	
	JLabel lblPhoto;
	JLabel lblAdminPage;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManagerPage(MainPage mainPage) {
		setLayout(null);
		
		JLabel lblFrontImg = new JLabel("");
		lblFrontImg.setSize(128,128);
		lblFrontImg.setBounds(237, 131, 580, 374);
		lblFrontImg.setIcon(new ImageIcon(ManagerPage.class.getResource("/pmsicons/Picture3.png")));
		add(lblFrontImg);
		
		JLabel lblAdminPage = new JLabel("Manager Page");
		lblAdminPage.setForeground(Color.WHITE);
		lblAdminPage.setIcon(new ImageIcon(ManagerPage.class.getResource("/images/manager.png")));
		lblAdminPage.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblAdminPage.setBounds(10, 11, 503, 78);
		add(lblAdminPage);
		if (mainPage.getPath().contains("\\"))
		{
			lblAdminPage.setIcon(ResizeImage(mainPage.getPath(),lblAdminPage.getWidth()-430,lblAdminPage.getHeight()));
		}
		JLabel lblLogout = new JLabel("");
		lblLogout.setToolTipText("Log Out");
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				try {
					mainPage.setLoginPage();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mainPage.setUserType("");
			}
		});
		lblLogout.setIcon(new ImageIcon(ManagerPage.class.getResource("/images/Newlogout.png")));
		lblLogout.setBounds(851, 40, 39, 36);
		add(lblLogout);
		
		lblPhoto = new JLabel("");
		lblPhoto.setIcon(new ImageIcon(ManagerPage.class.getResource("/images/profile.png")));
		lblPhoto.setBounds(713, 11, 79, 87);
		//add(lblPhoto);
		
		/*JComboBox comboProperty = new JComboBox();
		comboProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mainPage.pushPanel("Manager");
				if (!comboProperty.getSelectedItem().toString().contentEquals("Property Menu"))
				{
					if (comboProperty.getSelectedItem().toString().contentEquals("View Properties"))
					{
						try 
						{
							mainPage.setViewPanel();
						} 
						catch (SQLException e1)
						{
							e1.printStackTrace();
						}
					
					}
					else if (comboProperty.getSelectedItem().toString().contentEquals("Search Property"))
					{
						try {
							mainPage.setSearchPanel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if (comboProperty.getSelectedItem().toString().contentEquals("Update Property"))
					{
						mainPage.setEditPanel();
					}
					else if (comboProperty.getSelectedItem().toString().contentEquals("Add Properties"))
					{
						mainPage.setAddPanel();
					}
					else if (comboProperty.getSelectedItem().toString().contentEquals("Delete Property"))
					{
						mainPage.setDeletePanel();
					}
				}
			}
		});
		comboProperty.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboProperty.setBounds(50, 175, 177, 39);
		add(comboProperty);
		comboProperty.addItem("Property Menu");
		comboProperty.addItem("View Properties");
		comboProperty.addItem("Search Property");
		comboProperty.addItem("Update Property");
		comboProperty.addItem("Add Properties");
		comboProperty.addItem("Delete Property");
		*/
		
		JComboBox comboNewusers = new JComboBox();
		comboNewusers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (!comboNewusers.getSelectedItem().toString().contentEquals("Add New Users"))
				{
					mainPage.pushPanel("Manager");
					mainPage.UserSetType(comboNewusers.getSelectedItem().toString());
					System.out.println(comboNewusers.getSelectedItem().toString());
					mainPage.setSignupPage();
				}
			}
		});
		
		comboNewusers.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboNewusers.setBounds(50, 149, 177, 40);
		add(comboNewusers);
		comboNewusers.addItem("Add New Users");
		comboNewusers.addItem("Deweller");
		comboNewusers.addItem("Plumber");
		comboNewusers.addItem("Electrician");
		
		JButton btnSearchUser = new JButton("Search User");
		btnSearchUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearchUser.setIcon(new ImageIcon(ManagerPage.class.getResource("/pmsicons/1446305467_Search.png")));
		btnSearchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					mainPage.pushPanel("Manager");
					mainPage.setSearchUser();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearchUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearchUser.setBounds(50, 251, 177, 39);
		add(btnSearchUser);
		
		JButton btnViewBookings = new JButton("View Bookings");
		btnViewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mainPage.pushPanel("Manager");
					mainPage.setBookingPanel();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnViewBookings.setIcon(new ImageIcon(ManagerPage.class.getResource("/pmsicons/1446305649_view-01.png")));
		btnViewBookings.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewBookings.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewBookings.setBounds(50, 301, 177, 39);
		add(btnViewBookings);
		
		JButton btnNewButton = new JButton("View Applications");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPage.pushPanel("Manager");
				mainPage.setManagerPane();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setIcon(new ImageIcon(ManagerPage.class.getResource("/images/list.png")));
		btnNewButton.setBounds(50, 354, 177, 39);
		add(btnNewButton);
		
		JButton btnPropertyMenu = new JButton("Property Menu");
		btnPropertyMenu.setHorizontalAlignment(SwingConstants.LEFT);
		btnPropertyMenu.setIcon(new ImageIcon(ManagerPage.class.getResource("/pmsicons/1446336016_todo_list.png")));
		btnPropertyMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPage.pushPanel("Manager"); 
			mainPage.setPropertyMenu();
			}
		});
		btnPropertyMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPropertyMenu.setBounds(50, 201, 177, 39);
		add(btnPropertyMenu);
			
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(ManagerPage.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		JLabel lblSettings = new JLabel("");
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				mainPage.setEditMode(true);
				mainPage.setEditAccount();
			}
		});
		lblSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSettings.setToolTipText("Edit Personal Info");
		lblSettings.setIcon(new ImageIcon(ManagerPage.class.getResource("/images/settings.png")));
		lblSettings.setBounds(802, 40, 39, 36);
		add(lblSettings);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboNewusers, btnPropertyMenu, btnSearchUser, btnViewBookings, btnNewButton}));
	}
	
	/*
	 * Image Resize
	 */ 
	 public ImageIcon ResizeImage(String path,int width,int height)
	{
		ImageIcon myImage =new ImageIcon(path);
		Image img =myImage.getImage();
		Image newImg =img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);
		return image;
	}
}
