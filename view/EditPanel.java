package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.ResultSet;

import controller.CommercialProperty;
import controller.ResidentialProperty;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class EditPanel extends JPanel{
	private JTextField txtArea;
	private JTextField txtPrice;
	private JTextField txtLocation;
	private JTextField txtRent;
	private JRadioButton rdbtnForRent;
	private JRadioButton rdbtnForsale;
	private JRadioButton rdbtnFull;
	private JRadioButton rdbtnResidential;
	private JRadioButton rdbtnCommercial;
	private JRadioButton rdbtnOnRent ;
	private JRadioButton rdbtnSold ;
	private JLabel lblRentPerMonth ;
	private JLabel lblPrice;
	private JLabel lblSuccessImg;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblSuccessMsg;
	private JPanel panel_2;
	private JTextField txtNoBedrooms;
	private JTextField txtNoBathrooms;
	private JTextField txtNoStoreys;
	private JTextField txtNoKetchens;
	private JCheckBox chckbxTeriss;
	private JCheckBox chckbxBalcony;
	private JTextField txtPropertyId;
	private JLabel lblPropertyid;
	private JButton btnSearch;
	private JButton btnNext;
	private JLabel lblImage;
	private JTextField txtspecificationid;
	private JLabel lblSpecificationId;
	private ResidentialProperty rp;
	private CommercialProperty cp;
EditPanel(MainPage uiWindow)
{
	super();
	setBackground(new Color(240, 240, 240));
	//insertSucceed();
	
	this.setPanelsettings();
	rp=new ResidentialProperty();
	cp=new CommercialProperty();
	txtArea = new JTextField();
	txtArea.setToolTipText("Marla:  1, 2, 3, ... etc ");
	txtArea.setBounds(106, 157, 96, 20);
	add(txtArea);
	txtArea.setColumns(10);
	
	JLabel lblType = new JLabel("Type");
	lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblType.setBounds(27, 228, 46, 14);
	add(lblType);
	
	rdbtnCommercial = new JRadioButton("Commercial");
	rdbtnCommercial.setBackground(new Color(240, 240, 240));
	rdbtnResidential = new JRadioButton("Residential");
	rdbtnResidential.setBackground(new Color(240, 240, 240));
	
	
	rdbtnCommercial.setBounds(106, 224, 84, 23);
	add(rdbtnCommercial);


	JButton btnBack = new JButton("Back");
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		uiWindow.switchPanel();
		}
	});
	btnBack.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446334955_back.png")));
	btnBack.setToolTipText("Next for filling specification field of above property type");
	btnBack.setHorizontalAlignment(SwingConstants.LEFT);
	btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnBack.setBounds(30, 580, 90, 30);
	add(btnBack);
	rdbtnResidential.setBounds(189, 224, 91, 23);
	add(rdbtnResidential);
	ButtonGroup rdgroup1=new ButtonGroup();
	rdgroup1.add(rdbtnResidential);
	rdgroup1.add(rdbtnCommercial);
	JLabel lblStatus = new JLabel("Status");
	lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblStatus.setBounds(27, 254, 46, 14);
	add(lblStatus);
	
	label_2 = new JLabel(" ");
	label_2.setIcon(new ImageIcon(AddPanel.class.getResource("/images/Error_Symbol.png")));
	label_2.setBounds(228, 157, 52, 20);
	label_2.setVisible(false);
	add(label_2);
	
	label_3 = new JLabel(" ");
	label_3.setIcon(new ImageIcon(AddPanel.class.getResource("/images/Error_Symbol.png")));
	label_3.setBounds(273, 285, 46, 20);
	label_3.setVisible(false);
	add(label_3);
	
	label_4 = new JLabel(" ");
	label_4.setIcon(new ImageIcon(AddPanel.class.getResource("/images/Error_Symbol.png")));
	label_4.setBounds(273, 313, 46, 20);
	label_4.setVisible(false);
	add(label_4);
	
	
	JLabel lblArea = new JLabel("Area(Marla)");
	lblArea.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblArea.setBounds(27, 160, 69, 14);
	add(lblArea);
	
	lblPrice = new JLabel("Price");
	lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblPrice.setBounds(27, 288, 57, 14);
	//add(lblPricePerUnit);
	
	txtPrice = new JTextField();
	txtPrice.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			 if(!txtArea.getText().matches("[0-9]*"))
			 {
				JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
				label_3.setVisible(true);
			 }
			 else
				label_3.setVisible(false); 
		}
	});
	txtPrice.setBounds(106, 285, 118, 20);
	//add(txtPricePerSqrFeet);
	txtPrice.setColumns(10);
	
	JLabel lblLocation = new JLabel("Location");
	lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblLocation.setBounds(27, 191, 57, 14);
	add(lblLocation);
	
	txtLocation = new JTextField();
	txtLocation.setBounds(106, 188, 211, 20);
	add(txtLocation);
	txtLocation.setColumns(10);
	
	lblRentPerMonth = new JLabel("Rent");
	lblRentPerMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblRentPerMonth.setBounds(27, 319, 74, 14);
	
	txtRent = new JTextField();
	txtRent.setBounds(106, 313, 118, 20);
	txtRent.setColumns(10);
	rdbtnForsale = new JRadioButton("ForSale");
	rdbtnForsale.setBackground(new Color(240, 240, 240));
	rdbtnForsale.setBounds(106, 250, 74, 23);
	add(rdbtnForsale);
	
	rdbtnForRent = new JRadioButton("ForRent");
	rdbtnForRent.setBackground(new Color(240, 240, 240));
	rdbtnForRent.setBounds(189, 250, 73, 23);
	add(rdbtnForRent);
	
	rdbtnFull = new JRadioButton("Full");
	rdbtnFull.setBackground(new Color(240, 240, 240));
	rdbtnFull.setBounds(264, 250, 57, 23);
	add(rdbtnFull);
	ButtonGroup rdstatus=new ButtonGroup();
	rdstatus.add(rdbtnForsale);
	rdstatus.add(rdbtnForRent);
	rdstatus.add(rdbtnFull);
	lblRentPerMonth.setVisible(true);
	txtRent.setVisible(true);
	add(lblRentPerMonth);
	add(txtRent);
	
	lblPrice.setVisible(true);
	txtPrice.setVisible(true);
	add(lblPrice);
	add(txtPrice);
	
	txtArea.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent arg0) {
			 if(!txtArea.getText().matches("[0-9]*"))
			 {
				JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
				label_2.setVisible(true);
			 }
			 else
					label_2.setVisible(false);		 
		}
	});
	
	txtRent.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			 if(!txtArea.getText().matches("[0-9]*"))
			 {
				JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
				label_4.setVisible(true);
			 }
			 else
				label_4.setVisible(false); 
		}
	});
	endComponents();
		radiobtnpropertystatus();
	}//////////Constructor
	public void editProperty(int propertyid) throws SQLException
	{
		txtPropertyId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			if(txtPropertyId.getText().matches("[0-9]*"))
			;
				else
				JOptionPane.showMessageDialog(null, "Property Id should be an positive Integer value","Error Message",JOptionPane.ERROR);
			}
		});
		txtPropertyId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtArea.setText("area");
				txtLocation.setText("location");
				txtPrice.setText("Price");
				txtRent.setText("Rent");
				rdbtnForsale.setSelected(false);
				rdbtnForRent.setSelected(false);
				rdbtnCommercial.setSelected(false);
				rdbtnResidential.setSelected(false);
				panel_2.setVisible(false);
			rdbtnSold.setSelected(false);
			rdbtnOnRent.setSelected(false);
			txtNoBedrooms.setText("nobedroom");
			txtNoBathrooms.setText("nobathroom");
			txtNoKetchens.setText("nokitchen");
			txtNoStoreys.setText("nostorey");
			chckbxBalcony.setSelected(false);
			chckbxTeriss.setSelected(false);	
			}
		});

		int specificationid=0,price=0;
		String type="type",status="status";
		ResultSet rs= (ResultSet) rp.oneProperty(propertyid+"");
		if(rs!=null)
		{
		if(rs.next())
		{
			String area=rs.getInt("area")+"";
			String location=rs.getString("location");
			txtArea.setText(area);
			txtLocation.setText(location);
			type=rs.getString("type");
			status=rs.getString("status");
			path=rs.getString("image");
			if(!path.contains("\\"))
				path="src\\PMSIcons\\search-home.png";
			//path=path.replace("__", "\\");	
			lblImage.setIcon(this.ResizeImage(path));
			rp.setType(type);
			rp.setStatus(status);
			rs=null;
			if(status.equals("ForSale"))
			{
				this.rdbtnForsale.setSelected(true);
				this.rdbtnForRent.setSelected(false);
				if(type.equals("Residential"))
				{
					panel_2.setVisible(true);
					ResultSet rsa = (ResultSet) rp.oneResidential(propertyid);
					if(rsa.next())
					{
						specificationid = rsa.getInt("specificationid");
						price =rsa.getInt("price");
						if(rsa.getBoolean("sold"))
						{
						this.rdbtnSold.setSelected(true);
						this.rdbtnOnRent.setSelected(false);
						}
					}
				}
				else
				{
					cp.setStatus(status);
					panel_2.setVisible(false);
					ResultSet rs3 = (ResultSet) cp.oneCommercial(propertyid);
					if(rs3.next())
					{
						price =rs3.getInt("price");
						if(rs3.getBoolean("sold")==true)
						{
							this.rdbtnSold.setSelected(true);
						this.rdbtnOnRent.setSelected(false);
						}
					}
				}
				this.txtPrice.setText(price+"");
			}
			else if(status.equals("ForRent"))
			{
				this.rdbtnForRent.setSelected(true);
				this.rdbtnForsale.setSelected(false);
				if(type.equals("Residential"))
				{
					panel_2.setVisible(true);
					ResultSet rs1 = (ResultSet) rp.oneResidential(propertyid);
					if(rs1.next())
					{
					specificationid = rs1.getInt("specificationid");
					price =rs1.getInt("rent");
					this.txtRent.setText(price+"");
					if(rs1.getBoolean("isonrent"));
						{
						this.rdbtnSold.setSelected(false);
						this.rdbtnOnRent.setSelected(true);
						}
					}
				}
				else
				{
					cp.setStatus(status);
					panel_2.setVisible(false);
					ResultSet rs2 = (ResultSet) cp.oneCommercial(propertyid);
					if(rs2.next())
					{
						price =rs2.getInt("rent");
						this.txtRent.setText(price+"");
						if(rs2.getBoolean("isonrent"));
							{
							this.rdbtnSold.setSelected(false);
							this.rdbtnOnRent.setSelected(true);
							}	
					}
				}
			}
			else
				JOptionPane.showMessageDialog(null, "No Property Exist!");
			if(type.equals("Commercial"))
			{
				this.rdbtnCommercial.setSelected(true);
				this.rdbtnResidential.setSelected(false);
			}
			else
			{
				this.txtspecificationid.setText(specificationid+"");
				this.rdbtnCommercial.setSelected(false);
				this.rdbtnResidential.setSelected(true);		
				rp.setSpecificationId(specificationid);
				ResultSet rs4=(ResultSet) rp.getResidentialSpecification();
				if(rs4.next())
				{
				this.txtNoBedrooms.setText(rs4.getInt("nobedroom")+"");
				this.txtNoBathrooms.setText(rs4.getInt("nobathroom")+"");
				this.txtNoKetchens.setText(rs4.getInt("nokitchen")+"");
				this.txtNoStoreys.setText(rs4.getInt("nostorey")+"");
				this.chckbxBalcony.setSelected(rs4.getBoolean("balcony"));
				this.chckbxTeriss.setSelected(rs4.getBoolean("teriss"));
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Property does not exist");
		}
	}
	}
	private void endComponents()
	{
		btnNext = new JButton("Update");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(updateProperty())
					JOptionPane.showMessageDialog(null, "Error Property edition","Error",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNext.setHorizontalAlignment(SwingConstants.LEFT);
		btnNext.setIcon(new ImageIcon(EditPanel.class.getResource("/pmsicons/1446334685_go-next.png")));
	btnNext.setToolTipText("Next for filling specification field of above property type");
	btnNext.setBounds(130, 580, 110, 30);
	add(btnNext);

		rdbtnOnRent = new JRadioButton("On Rent");
		rdbtnOnRent.setBackground(new Color(240, 240, 240));
		rdbtnOnRent.setBounds(106, 340, 84, 26);
		add(rdbtnOnRent);
		rdbtnOnRent.setVisible(true);
		rdbtnSold = new JRadioButton("Sold");
		rdbtnSold.setBackground(new Color(240, 240, 240));

		rdbtnSold.setBounds(189, 340, 61, 26);
		add(rdbtnSold);
		rdbtnSold.setVisible(true);
		ButtonGroup cstatus=new ButtonGroup();
		cstatus.add(rdbtnOnRent);
		cstatus.add(rdbtnSold);
	}
	
	private boolean updateProperty() {
		// TODO Auto-generated method stub
		if(label_2.isVisible()||label_3.isVisible()||label_4.isVisible())
			JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
		else
		{
		try
		{
			String type="0",status="0",price="0",area="0",location="0",image="0";
			boolean sold=false,bol=false;
			rp.setPropertyId(Integer.parseInt(txtPropertyId.getText()));
			btnNext.setEnabled(false);
			if(rdbtnForsale.isSelected())
				status="ForSale";
			else if(rdbtnForRent.isSelected())
				status="ForRent";
			else if(rdbtnFull.isSelected())
				status="Full";
			if(!txtPrice.getText().isEmpty()&&txtPrice.getText().matches("[0-9]*"))
				price=txtPrice.getText();
			else if(!txtRent.getText().isEmpty()&&txtRent.getText().matches("[0-9]*"))
				price=txtRent.getText();
			else
				{
				return true;
				}
			if(path.isEmpty())
				{
					System.out.println("NO Image Selected!");
				}
			image=path;
			System.out.println(image);
			area=txtArea.getText();
			location=txtLocation.getText();
			if(rdbtnSold.isSelected()||rdbtnOnRent.isSelected())
			sold=true;
			else
			sold=false;
			if(rdbtnResidential.isSelected())
				{
				type="Residential";
				rp.setType(type);
				rp.setPrice(Integer.parseInt(price));
				rp.setStatus(status);
				rp.setArea(Integer.parseInt(area));
				rp.setLocation(location);
				rp.isSold(sold);
				rp.setImage(image);
				rp.updateProperty();
				}
			else if(rdbtnCommercial.isSelected())
				{
				type="Commercial";
				cp.setType(type);
				cp.setPrice(Integer.parseInt(price));
				cp.setStatus(status);
				cp.setArea(Integer.parseInt(area));
				cp.setLocation(location);
				cp.isSold(sold);
				cp.setImage(image);
				bol=cp.updateProperty();
				}
		if(type.equals("Commercial")&&!bol)
		{
			lblSuccessImg.setVisible(true);
			lblSuccessMsg.setVisible(true);
			return bol;
		}
		else if(type.equals("Residential")&&!bol)
		{
			if(!updatePropertySpecification())
			{
				lblSuccessImg.setVisible(true);
				lblSuccessMsg.setVisible(true);
				return bol;
			}
			else
				JOptionPane.showMessageDialog(null, "Error Specification edition","Error",JOptionPane.ERROR_MESSAGE);
		}
		return bol;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		}
		return true;
	}
	private boolean updatePropertySpecification()
	{
		boolean b=true;
		if(txtNoBathrooms.getText().matches("[0-9]*")&& txtNoBedrooms.getText().matches("[0-9]*") && txtNoKetchens.getText().matches("[0-9]*")&& txtNoStoreys.getText().matches("[0-9]*"))
		{
		int nbedrooms=0,nbathrooms=0,nkitchens=0,nstoreys=0,specificationid=0;
		int balcony=0,teriss=0;
		specificationid=Integer.parseInt(this.txtspecificationid.getText());
		nbedrooms=Integer.parseInt(this.txtNoBathrooms.getText());
		nbathrooms=Integer.parseInt(this.txtNoBedrooms.getText());
		nkitchens=Integer.parseInt(this.txtNoKetchens.getText());
		nstoreys=Integer.parseInt(this.txtNoStoreys.getText());
		if(chckbxBalcony.isSelected())
			balcony=1;
		if(chckbxTeriss.isSelected())
			teriss=1;
		rp.setSpecification(nbedrooms, nbathrooms, nkitchens, nstoreys, balcony, teriss);
		b=rp.updatePropertySpecification(specificationid);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Enter Integer Valid Values","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return b;
	}
	private void radiobtnpropertystatus()
	{

		JLabel lblPkr = new JLabel("PKR");
		lblPkr.setBounds(236, 288, 26, 14);
		add(lblPkr);
		lblPkr.setVisible(false);
		
		JLabel lblPkr2 = new JLabel("PKR");
		lblPkr2.setBounds(236, 319, 33, 14);
		add(lblPkr2);
		lblPkr2.setVisible(false);
		
		
		JLabel lblCurrentState = new JLabel("Current State");
		lblCurrentState.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentState.setBounds(27, 347, 84, 14);
		add(lblCurrentState);
		
		txtPropertyId = new JTextField();
		txtPropertyId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				 if(!txtPropertyId.getText().matches("[0-9]*"))
				 {
					JOptionPane.showMessageDialog(null, "Enter Propertyid is an Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
					//label_3.setVisible(true);
				 }
				 //else
					//label_3.setVisible(false); 
			}
		});
		
		txtPropertyId.setBounds(106, 121, 94, 20);
		add(txtPropertyId);
		txtPropertyId.setColumns(10);
		
		lblPropertyid = new JLabel("PropertyID");
		lblPropertyid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPropertyid.setBounds(27, 124, 69, 14);
		add(lblPropertyid);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(txtPropertyId.getText().matches("[0-9]*") && !txtPropertyId.getText().isEmpty())
					{
						int propertyid=Integer.parseInt(txtPropertyId.getText());
						editProperty(propertyid);
					}
						else
						JOptionPane.showMessageDialog(null, "Please Enter Valid PropertyId Integer Value","ERROR Message",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(EditPanel.class.getResource("/pmsicons/1446426674_icon-111-search.png")));
		btnSearch.setBounds(210, 117, 110, 30);
		add(btnSearch);
		
		lblImage = new JLabel(" ");
		lblImage.setBounds(580, 150, 300, 250);
		add(lblImage);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(ResizeImage("src\\pmsicons\\1446666840_folder_home.png"));
		lblSuccessImg = new JLabel("");
		lblSuccessImg.setBounds(150, 377, 128, 128);
		add(lblSuccessImg);
		lblSuccessImg.setVisible(false);
		lblSuccessImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSuccessImg.setIcon(new ImageIcon(EditPanel.class.getResource("/pmsicons/1446314201_ok.png")));
		lblSuccessMsg = new JLabel("Property Updated Successfully!");
		lblSuccessMsg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuccessMsg.setBounds(135, 501, 179, 27);
		add(lblSuccessMsg);
		
		JLabel lblNewProperty1 = new JLabel("Edit Property");
		lblNewProperty1.setBounds(39, 30, 255, 42);
		add(lblNewProperty1);
		lblNewProperty1.setForeground(Color.WHITE);
		lblNewProperty1.setIcon(new ImageIcon(EditPanel.class.getResource("/pmsicons/1446290209_Edit.png")));
		lblNewProperty1.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewProperty1.setBackground(Color.LIGHT_GRAY);

		JLabel backwall;
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setHorizontalAlignment(SwingConstants.LEFT);
		btnBrowse.setIcon(new ImageIcon(EditPanel.class.getResource("/images/folder-search.png")));
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				browseForImage();
			}
		});
		btnBrowse.setBounds(682, 405, 110, 30);
		add(btnBrowse);
		backwall = new JLabel("");
		backwall.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/panelback.png")));
		backwall.setBounds(0, 100, 900, 450);
		add(backwall);
		lblSuccessMsg.setVisible(false);
		
		lblCurrentState.setVisible(true);
		rdbtnForRent.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(rdbtnFull.isSelected())
			{
				rdbtnFull.setSelected(false);
			}
			lblCurrentState.setVisible(true);
			lblPkr2.setVisible(true);
			rdbtnOnRent.setVisible(true);
			lblRentPerMonth.setVisible(true);
			txtRent.setVisible(true);
		}
		});
		rdbtnForsale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			if(rdbtnFull.isSelected())
			{
				rdbtnFull.setSelected(false);
			}
			lblCurrentState.setVisible(true);
			lblPkr.setVisible(true);
			rdbtnSold.setVisible(true);
			lblPrice.setVisible(true);
			txtPrice.setVisible(true);
			}
		});

	rdbtnFull.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
	if(rdbtnForsale.isSelected()||rdbtnForRent.isSelected())
			{
				rdbtnForsale.setSelected(false);
				rdbtnForRent.setSelected(false);
				rdbtnOnRent.setSelected(false);
				rdbtnSold.setSelected(false);
			}

			lblCurrentState.setVisible(false);
			lblPkr.setVisible(false);
			lblPkr2.setVisible(false);
			rdbtnSold.setVisible(false);
			rdbtnOnRent.setVisible(false);
			
			lblPrice.setVisible(false);
			txtPrice.setVisible(false);
			lblRentPerMonth.setVisible(false);
			txtRent.setVisible(false);
		}
	});	
	
}
private void setPanelsettings()
{
	setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), new Color(0, 0, 0), null));
	setLayout(null);
	this.setVisible(true);
	setSize(914,632);
	panel_2 = new JPanel();
	panel_2.setBackground(new Color(211, 211, 211));
	panel_2.setBounds(329, 99, 231, 329);
	add(panel_2);
	panel_2.setLayout(null);
	panel_2.setVisible(false);
	JLabel lblNumberOfBadrooms = new JLabel("Number of bedrooms");
	lblNumberOfBadrooms.setBounds(10, 102, 115, 24);
	panel_2.add(lblNumberOfBadrooms);
	
	JLabel lblNumberOfBathrooms = new JLabel("Number of bathrooms");
	lblNumberOfBathrooms.setBounds(10, 135, 115, 24);
	panel_2.add(lblNumberOfBathrooms);
	
	JLabel lblNumberOfStoreys = new JLabel("Number of storeys");
	lblNumberOfStoreys.setBounds(10, 165, 115, 24);
	panel_2.add(lblNumberOfStoreys);
	
	JLabel lblNumberOfKetchens = new JLabel("Number of ketchens");
	lblNumberOfKetchens.setBounds(10, 200, 115, 24);
	panel_2.add(lblNumberOfKetchens);
	
	JLabel lblBalcony = new JLabel("Balcony");
	lblBalcony.setBounds(10, 225, 115, 24);
	panel_2.add(lblBalcony);
	
	chckbxBalcony = new JCheckBox("Having");
	chckbxBalcony.setBounds(139, 230, 71, 23);
	panel_2.add(chckbxBalcony);
	
	JLabel lblTeriss = new JLabel("Teriss");
	lblTeriss.setBounds(10, 255, 115, 24);
	panel_2.add(lblTeriss);
	
	chckbxTeriss = new JCheckBox("Having");
	chckbxTeriss.setBounds(139, 256, 71, 23);
	panel_2.add(chckbxTeriss);
	
	txtNoBedrooms = new JTextField();
	txtNoBedrooms.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent arg0) {
		
		}
	});
	txtNoBedrooms.setBounds(139, 105, 71, 24);
	panel_2.add(txtNoBedrooms);
	txtNoBedrooms.setColumns(10);
	
	txtNoBathrooms = new JTextField();
	txtNoBathrooms.setColumns(10);
	txtNoBathrooms.setBounds(139, 138, 71, 24);
	panel_2.add(txtNoBathrooms);
	
	txtNoStoreys = new JTextField();
	txtNoStoreys.setColumns(10);
	txtNoStoreys.setBounds(139, 168, 71, 24);
	panel_2.add(txtNoStoreys);
	
	txtNoKetchens = new JTextField();
	txtNoKetchens.setColumns(10);
	txtNoKetchens.setBounds(139, 198, 71, 24);
	panel_2.add(txtNoKetchens);
	
	JPanel panel_3 = new JPanel();
	panel_3.setBackground(new Color(60, 179, 113));
	panel_3.setBounds(0, 11, 231, 57);
	panel_2.add(panel_3);
	panel_3.setLayout(null);
	
	JLabel lblResidentialPropertySpecification = new JLabel("Specifications");
	lblResidentialPropertySpecification.setForeground(Color.WHITE);
	lblResidentialPropertySpecification.setFont(new Font("Modern No. 20", Font.BOLD, 18));
	lblResidentialPropertySpecification.setBackground(Color.LIGHT_GRAY);
	lblResidentialPropertySpecification.setBounds(10, 11, 211, 42);
	panel_3.add(lblResidentialPropertySpecification);
	
	txtspecificationid = new JTextField();
	txtspecificationid.setEditable(false);
	txtspecificationid.setColumns(10);
	txtspecificationid.setBounds(139, 75, 71, 24);
	panel_2.add(txtspecificationid);
	
	lblSpecificationId = new JLabel("Specification ID");
	lblSpecificationId.setBounds(10, 72, 115, 24);
	panel_2.add(lblSpecificationId);
	panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNoBedrooms, txtNoBathrooms, txtNoStoreys, txtNoKetchens, chckbxBalcony, chckbxTeriss}));
	
}
private String path="pms\\";
public void browseForImage()
{
	path="pms\\";
	int imageRename=1;
	JFileChooser file =new JFileChooser();
	file.setCurrentDirectory(new File(System.getProperty("user.home")));
	FileNameExtensionFilter filter =new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	file.addChoosableFileFilter(filter);
	int result=file.showSaveDialog(null);
	if (result==JFileChooser.APPROVE_OPTION)
	{		
		lblImage.revalidate();
		lblImage.repaint();
		File src =new  File(path);
		if (!src.exists())
		{						
			src.mkdirs();
			src.setWritable(true);
		}
		String tempPath;
		File selectedFile=file.getSelectedFile();
		tempPath=path+selectedFile.getName();
		File absolutFile=new File(tempPath);
		
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
			File renameFile=new File(path);
			copyFile(selectedFile, renameFile);
		}
		else
		{
			path=tempPath;
			copyFile(selectedFile, absolutFile);
		}
		lblImage.setIcon(ResizeImage(temp_path));
	}
	else
		if (result==JFileChooser.CANCEL_OPTION)
	{
		JOptionPane.showMessageDialog(null, "No Image is seleted");
		path=" ";
	}
}
private static void copyFile(File source,File dest) 
{
		try {
			Files.copy(source.toPath(),dest.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in copy file","ERROR",JOptionPane.ERROR_MESSAGE);
		}			
}

/*
* Image Resize
*/ 
public ImageIcon ResizeImage(String path)
{
 System.out.println("path: "+path);
ImageIcon myImage =new ImageIcon(path);
Image img =myImage.getImage();
Image newImg =img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
ImageIcon image=new ImageIcon(newImg);
return image;
}
}
