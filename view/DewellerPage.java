package view;

import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.Color;

@SuppressWarnings("serial")
public class DewellerPage extends JPanel {

	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	
	JLabel lblPhoto;
	public DewellerPage(MainPage mainPage) {
		setBounds(new Rectangle(0, 0, 900, 660));
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setSize(128,128);
		label.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/Picture2.png")));
		label.setBounds(180, 113, 654, 407);
		add(label);
		
		JLabel lblDewellerPage = new JLabel("Deweller Page");
		lblDewellerPage.setForeground(Color.WHITE);
		lblDewellerPage.setIcon(null);
		lblDewellerPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDewellerPage.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblDewellerPage.setBounds(112, 11, 251, 78);
		add(lblDewellerPage);

		JLabel lblMap = new JLabel("");
		lblMap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMap.setToolTipText("Click to See Maps");
		lblMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPage.pushPanel("Dweller");
				mainPage.setMap();
			}
		});
		lblMap.setIcon(new ImageIcon(PropertyMenu.class.getResource("/images/maps.png")));
		lblMap.setBounds(760, 0, 111, 89);
		add(lblMap);
		JLabel lblLogout = new JLabel("");
		lblLogout.setToolTipText("Log Out");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				mainPage.setUserType("");
				try {
					mainPage.setLoginPage();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogout.setIcon(new ImageIcon(DewellerPage.class.getResource("/images/Newlogout.png")));
		lblLogout.setBounds(737, 42, 38, 36);
		add(lblLogout);
		
		lblPhoto = new JLabel("");
		lblPhoto.setIcon(new ImageIcon(DewellerPage.class.getResource("/images/profile.png")));
		lblPhoto.setBounds(24, 11, 70, 78);
		add(lblPhoto);
		if (!mainPage.getPath().isEmpty())
		{
			lblPhoto.setIcon(ResizeImage(mainPage.getPath()));
		}
		
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
		lblSettings.setIcon(new ImageIcon(DewellerPage.class.getResource("/images/settings.png")));
		lblSettings.setBounds(686, 42, 38, 36);
		add(lblSettings);
		
		JButton btnSearchProperty = new JButton("Search Property");
		btnSearchProperty.setIcon(new ImageIcon(DewellerPage.class.getResource("/pmsicons/1446305467_Search.png")));
		btnSearchProperty.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearchProperty.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearchProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
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
		btnViewProperties.setIcon(new ImageIcon(DewellerPage.class.getResource("/pmsicons/1446305649_view-01.png")));
		btnViewProperties.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewProperties.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					mainPage.setViewPanel();
				}
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnViewProperties.setBounds(50, 222, 160, 36);
		add(btnViewProperties);
		
		JButton btnReportProblem = new JButton("Report Problem");
		btnReportProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPage.setP1();
			}
		});
		btnReportProblem.setIcon(new ImageIcon(DewellerPage.class.getResource("/images/report problem.png")));
		btnReportProblem.setHorizontalAlignment(SwingConstants.LEFT);
		btnReportProblem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReportProblem.setBounds(50, 268, 160, 36);
		add(btnReportProblem);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(DewellerPage.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
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
