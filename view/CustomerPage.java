package view;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.Color;

@SuppressWarnings("serial")
public class CustomerPage extends JPanel {

	JLabel lblPhoto;
	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	public CustomerPage(MainPage mainPage)
	{
		setBounds(new Rectangle(0, 0, 900, 660));
		setBackground(SystemColor.control);
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setSize(128,128);
		label.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/Picture2.png")));
		label.setBounds(180, 113, 654, 407);
		add(label);
		
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
		lblLogout.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/Newlogout.png")));
		lblLogout.setBounds(712, 40, 54, 36);
		add(lblLogout);

		JLabel lblMap = new JLabel("");
		lblMap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMap.setToolTipText("Click to See Maps");
		lblMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPage.pushPanel("Customer");
				mainPage.setMap();
			}
		});
		lblMap.setIcon(new ImageIcon(PropertyMenu.class.getResource("/images/maps.png")));
		lblMap.setBounds(760, 0, 111, 89);
		add(lblMap);
		lblPhoto = new JLabel("");
		lblPhoto.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/profile.png")));
		lblPhoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPhoto.setBounds(39, 9, 70, 78);
		add(lblPhoto);
		if (!mainPage.getPath().isEmpty())
		{
			lblPhoto.setIcon(ResizeImage(mainPage.getPath()));
		}
		else
		{
			lblPhoto.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/profile.png")));
		}
		
		JLabel lblCustomerPage = new JLabel("Customer Page");
		lblCustomerPage.setForeground(Color.WHITE);
		//lblCustomerPage.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/user_male.png")));
		lblCustomerPage.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblCustomerPage.setBounds(119, 10, 258, 77);
		add(lblCustomerPage);
		
		JLabel lblSettings = new JLabel("");
		lblSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				mainPage.pushPanel("Customer");
				mainPage.setEditMode(true);
				mainPage.setEditAccount();
			}
		});
		lblSettings.setToolTipText("Edit Personal Info");
		lblSettings.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/settings.png")));
		lblSettings.setBounds(649, 40, 53, 36);
		add(lblSettings);
		
		JButton btnSearchProperty = new JButton("Search Property");
		btnSearchProperty.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearchProperty.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearchProperty.setIcon(new ImageIcon(CustomerPage.class.getResource("/pmsicons/1446305467_Search.png")));
		btnSearchProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				mainPage.pushPanel("Customer");
				try {
					mainPage.setSearchPanel();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSearchProperty.setBounds(50, 175, 160, 36);
		add(btnSearchProperty);
		
		JButton btnViewProperties = new JButton("View Properties");
		btnViewProperties.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewProperties.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewProperties.setIcon(new ImageIcon(CustomerPage.class.getResource("/pmsicons/1446305649_view-01.png")));
		btnViewProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				mainPage.pushPanel("Customer");
				try
				{
					mainPage.setViewPanel();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnViewProperties.setBounds(50, 222, 160, 36);
		add(btnViewProperties);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(0, 100, 900, 450);
		lblImage.setIcon(new ImageIcon(CustomerPage.class.getResource("/images/newBackground.png")));
		add(lblImage);
	}
	
	
	/*
	 * Image Resize
	 */ 
	 public ImageIcon ResizeImage(String path)
	{
		ImageIcon myImage =new ImageIcon(path);
		Image img =myImage.getImage();
		Image newImg =img.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image=new ImageIcon(newImg);
		return image;
	}
}
