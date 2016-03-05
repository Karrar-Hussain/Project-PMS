package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
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
import java.awt.Color;

@SuppressWarnings("serial")
public class AdminPage extends JPanel {

	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	
	private JLabel lblPhoto;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AdminPage(MainPage mainPage) {
		setBounds(new Rectangle(0, 0, 900, 660));
		setLayout(null);
		
		JLabel lblAdminPage = new JLabel("Admin Page");
		lblAdminPage.setForeground(Color.WHITE);
		lblAdminPage.setIcon(null);
		lblAdminPage.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblAdminPage.setBounds(118, 11, 234, 78);
		add(lblAdminPage);
		
		JLabel label = new JLabel("");
		label.setSize(128,128);
		label.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/Picture2.png")));
		label.setBounds(180, 113, 654, 407);
		add(label);
		
		lblPhoto = new JLabel("");
		lblPhoto.setIcon(new ImageIcon(AdminPage.class.getResource("/images/profile.png")));
		lblPhoto.setBounds(33, 11, 70, 78);
		add(lblPhoto);
		if (!mainPage.getPath().isEmpty())
		{
			lblPhoto.setIcon(ResizeImage(mainPage.getPath()));
		}
		
		JButton btnSearchUsers = new JButton("Search Users");
		btnSearchUsers.setIcon(new ImageIcon(AdminPage.class.getResource("/pmsicons/1446305467_Search.png")));
		btnSearchUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearchUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					mainPage.pushPanel("Admin"); 
					mainPage.setSearchUser();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSearchUsers.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearchUsers.setBounds(50, 253, 174, 39);
		add(btnSearchUsers);
		
		JLabel lblLogout = new JLabel("");
		lblLogout.setToolTipText("Log Out");
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		lblLogout.setIcon(new ImageIcon(AdminPage.class.getResource("/images/Newlogout.png")));
		lblLogout.setBounds(819, 40, 54, 36);
		add(lblLogout);

		JButton btnPropertyMenu = new JButton("Property Menu");
		btnPropertyMenu.setIcon(new ImageIcon(AdminPage.class.getResource("/pmsicons/1446336016_todo_list.png")));
		btnPropertyMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPage.pushPanel("Admin"); 
			mainPage.setPropertyMenu();
			}
		});
		btnPropertyMenu.setHorizontalAlignment(SwingConstants.LEFT);
		btnPropertyMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPropertyMenu.setBounds(47, 203, 177, 39);
		add(btnPropertyMenu);
		
		JComboBox comboNewuser = new JComboBox();
		comboNewuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (!comboNewuser.getSelectedItem().toString().contentEquals("Add New Users"))
				{
					mainPage.pushPanel("Admin"); 
					mainPage.UserSetType(comboNewuser.getSelectedItem().toString());
					//System.out.println("ADMIN PAGE User"+comboNewuser.getSelectedItem().toString());
					mainPage.setSignupPage();
				}
			}
		});
		comboNewuser.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboNewuser.setBounds(50, 150, 174, 39);
		add(comboNewuser);
	
		comboNewuser.addItem("Add New Users");
		comboNewuser.addItem("Manager");
		comboNewuser.addItem("Admin");
		
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(AdminPage.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		JLabel lblSettings = new JLabel("");
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				mainPage.setEditMode(true);
				mainPage.setEditAccount();
			}
		});
		lblSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSettings.setToolTipText("Edit Personal Info");
		lblSettings.setIcon(new ImageIcon(AdminPage.class.getResource("/images/settings.png")));
		lblSettings.setBounds(755, 40, 54, 36);
		add(lblSettings);

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
