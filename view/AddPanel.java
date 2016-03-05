package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.CommercialProperty;
import controller.ResidentialProperty;
@SuppressWarnings("serial")
public class AddPanel extends JPanel{
	private ResidentialProperty rp;
	private CommercialProperty cp;
	private JTextField txtArea;
	private JTextField txtPrice;
	private JTextField txtLocation;
	private JTextField txtRent;
	private ButtonGroup rdTypeBtnGoup;
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
	private JLabel lblSuccessMsg;
	private JPanel panel_2;
	private JTextField txtNoBedrooms;
	private JTextField txtNoBathrooms;
	private JTextField txtNoStoreys;
	private JTextField txtNoKetchens;
	private JCheckBox chckbxTeriss;
	private JCheckBox chckbxBalcony;
	private JLabel label_1;
	private JLabel lblError1;
	private JLabel lblError2;
	private JLabel lblError3;
	private JButton btnFinish ;
	private JLabel lblNumberOfBadrooms;
	private JLabel lblNumberOfBathrooms;
	private JLabel lblNumberOfStoreys;
	private JLabel lblNumberOfKetchens;
	private JLabel lblBalcony;
	private JLabel lblTeriss;
	private JPanel panel_3;
	private JLabel lblResidentialPropertySpecification;
	private JLabel lblNewProperty1;
	private JLabel lblType;
	private JLabel lblStatus;
	private JLabel lblArea;
	private JLabel lblLocation;
	private JButton btnNext;
	private JButton btnAddNew ;
	private JLabel label;
AddPanel(MainPage mainPage)
{
	super();
	setForeground(new Color(255, 255, 255));
	setBackground(new Color(240, 240, 240));
	rp=new ResidentialProperty();
	cp=new CommercialProperty();
	this.setPanelsettings();
	txtArea = new JTextField();
	txtArea.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent arg0) {
			 if(!txtArea.getText().matches("[0-9]*"))
			 {
				JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
				lblError1.setVisible(true);
			 }
			 else
					lblError1.setVisible(false);
			 
		}
	});
	//mainPage.getExitBtn().setBounds(765, 10, 102, 31);

	JButton btnBack = new JButton("Back");
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		mainPage.switchPanel();
		}
	});
	btnBack.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446334955_back.png")));
	btnBack.setToolTipText("Next for filling specification field of above property type");
	btnBack.setHorizontalAlignment(SwingConstants.LEFT);
	btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnBack.setBounds(30, 580, 90, 30);
	add(btnBack);
	txtArea.setToolTipText("Marla:  1, 2, 3, ... etc ");
	txtArea.setBounds(116, 129, 163, 20);
	add(txtArea);
	txtArea.setColumns(10);
	rdbtnOnRent = new JRadioButton("On Rent");
	rdbtnOnRent.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(rdbtnSold.isSelected())
				rdbtnSold.setSelected(false);
		}
	});
	rdbtnOnRent.setBackground(new Color(240, 240, 240));
	
	lblType = new JLabel("Type");
	lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblType.setBounds(22, 191, 61, 14);
	add(lblType);
	
	rdbtnCommercial = new JRadioButton("Commercial");
	rdbtnCommercial.setBackground(new Color(240, 240, 240));
	rdTypeBtnGoup=new ButtonGroup();
	rdTypeBtnGoup.add(rdbtnCommercial);
	this.radiobtnpropertytype();
	
	lblStatus = new JLabel("Status");
	lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblStatus.setBounds(22, 218, 61, 14);
	add(lblStatus);
	
	lblArea = new JLabel("Area(Marla)");
	lblArea.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblArea.setBounds(22, 132, 84, 14);
	add(lblArea);
	
	lblPrice = new JLabel("Price");
	lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblPrice.setBounds(22, 243, 72, 14);
	//add(lblPricePerUnit);
	
	txtPrice = new JTextField();
	txtPrice.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			 if(!txtArea.getText().matches("[0-9]*"))
			 {
				JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
				lblError2.setVisible(true);
			 }
			 else
				lblError2.setVisible(false); 
		}
	});
	txtPrice.setBounds(116, 237, 134, 20);
	//add(txtPricePerSqrFeet);
	txtPrice.setColumns(10);
	
	lblLocation = new JLabel("Location");
	lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblLocation.setBounds(22, 166, 72, 14);
	add(lblLocation);
	
	txtLocation = new JTextField();
	txtLocation.setBounds(116, 160, 203, 20);
	add(txtLocation);
	txtLocation.setColumns(10);
	ButtonGroup rdstatusgroup=new ButtonGroup();
	
	lblRentPerMonth = new JLabel("Rent Per Month");
	lblRentPerMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblRentPerMonth.setBounds(22, 268, 99, 14);
	
	txtRent = new JTextField();
	txtRent.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			 if(!txtArea.getText().matches("[0-9]*"))
			 {
				JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
				lblError3.setVisible(true);
			 }
			 else
				lblError3.setVisible(false); 
		}
	});
	txtRent.setBounds(116, 265, 134, 20);
	txtRent.setColumns(10);
	
	rdbtnForRent = new JRadioButton("ForRent");
	rdbtnForRent.setBackground(new Color(240, 240, 240));
	rdbtnForRent.setBounds(202, 214, 77, 23);
	add(rdbtnForRent);
	rdstatusgroup.add(rdbtnForRent);
	rdbtnForsale = new JRadioButton("ForSale");
	rdbtnForsale.setBackground(new Color(240, 240, 240));
	rdbtnForsale.setBounds(116, 214, 99, 23);
	add(rdbtnForsale);
	
	rdbtnFull = new JRadioButton("Full");
	rdbtnFull.setBackground(new Color(240, 240, 240));
	rdbtnFull.setBounds(274, 214, 47, 23);
	add(rdbtnFull);
	
	rdstatusgroup.add(rdbtnFull);
	rdstatusgroup.add(rdbtnForsale);
	
	lblRentPerMonth.setVisible(false);
	txtRent.setVisible(false);
	add(lblRentPerMonth);
	add(txtRent);
	
	lblPrice.setVisible(false);
	txtPrice.setVisible(false);
	add(lblPrice);
	add(txtPrice);
	btnAddNew = new JButton("Add New");
	btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnAddNew.setForeground(Color.BLACK);
	btnAddNew.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		mainPage.setAddPanel();
		}
	});
	btnAddNew.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446290233_Undo.png")));
	btnAddNew.setBackground(new Color(240, 240, 240));
	btnAddNew.setHorizontalAlignment(SwingConstants.LEFT);
	btnAddNew.setBounds(740, 580, 110, 30);
	add(btnAddNew);

		endComponents();
		radiobtnpropertystatus();
	}//////////Constructor
	private void endComponents()
	{
		btnNext = new JButton("Next ");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNext.setHorizontalAlignment(SwingConstants.LEFT);
		btnNext.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446334685_go-next.png")));
		btnNext.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(lblError1.isVisible()||lblError2.isVisible()||lblError3.isVisible())
					JOptionPane.showMessageDialog(null, "Enter Integer Value","ERROR",JOptionPane.ERROR_MESSAGE); 
			else
			{
				if(rdbtnCommercial.isSelected()||rdbtnResidential.isSelected())
				{
					try
					{	
				String type="0",status="0",price="0",area="0",location="0",image="";
				if(!path.equals("pms\\"))
					{
					 image=path;
					 System.out.println("image: "+image+" :"+"pms\\");
					}
				boolean sold=false,bol=false;
				System.out.println("Hello: "+image);
				if(rdbtnForsale.isSelected())
					status="ForSale";
				else if(rdbtnForRent.isSelected())
					status="ForRent";
				else if(rdbtnFull.isSelected())
					status="Full";
				if(txtPrice.isVisible())
					price=txtPrice.getText();
				else if(txtRent.isVisible())
					price=txtRent.getText();
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
					if(image.contains("pms\\"))
						rp.setImage(image);
					else
						rp.setImage("");
					rp.insertNewProperty();
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
					if(image.contains("pms\\"))
					{
					System.out.println("Add\t"+image);
					rp.setImage(image);
					}
					else
						rp.setImage("");
					bol=cp.insertNewProperty();
					}
				
			if(!bol)
			{
				btnFinish.setEnabled(true);
				txtPrice.setEditable(false);
				txtRent.setEditable(false);
				if(type.equals("Residential"))
				{
					btnNext.setEnabled(false);
					panel_2.setVisible(true);	
				}
				else
				{
					btnNext.setEnabled(false);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Property is not added","Error",JOptionPane.ERROR_MESSAGE);
			btnNext.setEnabled(false);
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Type of Property","Uncheck",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	});
	btnNext.setToolTipText("Next for filling specification field of above property type");
	btnNext.setBounds(142, 580, 90, 30);
	add(btnNext);
		

		rdbtnSold = new JRadioButton("Sold");
		rdbtnSold.setBackground(new Color(240, 240, 240));
		rdbtnSold.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnOnRent.isSelected())
					rdbtnOnRent.setSelected(false);
				
			}
		});
		rdbtnSold.setBounds(202, 292, 84, 23);
		add(rdbtnSold);
		rdbtnSold.setVisible(false);
	
		rdbtnOnRent.setBounds(116, 292, 84, 23);
		add(rdbtnOnRent);
		rdbtnOnRent.setVisible(false);

	}
	private boolean takeResidentialSpecification() throws SQLException
	{
		int nbedrooms=0,nbathrooms=0,nkitchens=0,nstoreys=0;
		boolean b=false;
		int balcony=0,teriss=0;
		
		if(txtNoBathrooms.getText().matches("[0-9]*")&& txtNoBedrooms.getText().matches("[0-9]*") && txtNoKetchens.getText().matches("[0-9]*")&& txtNoStoreys.getText().matches("[0-9]*"))
		{
			if(rdbtnCommercial.isSelected()||rdbtnResidential.isSelected())
			{
				int price=0;
				if(rdbtnForsale.isSelected())
				price=Integer.parseInt(txtPrice.getText());
				else if(rdbtnForRent.isSelected())
					price=Integer.parseInt(txtRent.getText());
				rp.setPrice(price);
				if(rdbtnSold.isSelected()||rdbtnOnRent.isSelected())
				rp.setCurrentState(true);
				else
				rp.setCurrentState(false);
		if(rdbtnCommercial.isSelected())
			{
			b=cp.insertCommercial();
			return b;
			}
		else if(rdbtnResidential.isSelected())
			{
			nbedrooms=Integer.parseInt(this.txtNoBathrooms.getText());
			nbathrooms=Integer.parseInt(this.txtNoBedrooms.getText());
			nkitchens=Integer.parseInt(this.txtNoKetchens.getText());
			nstoreys=Integer.parseInt(this.txtNoStoreys.getText());
			if(chckbxBalcony.isSelected())
				balcony=1;
			if(chckbxTeriss.isSelected())
				teriss=1;
			rp.setSpecification(nbedrooms, nbathrooms, nkitchens, nstoreys, balcony, teriss);
			if(price!=0)
			b=rp.insertResidential();
			else
				JOptionPane.showMessageDialog(null, "You haven checked any status or Put Price/Rent");
			}
		}
		else
			{
				JOptionPane.showMessageDialog(null, "Select The Type!","ERROR",JOptionPane.ERROR_MESSAGE);
			}
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
		lblPkr.setBounds(260, 243, 33, 14);
		add(lblPkr);
		lblPkr.setVisible(false);
		
		JLabel lblPkr2 = new JLabel("PKR");
		lblPkr2.setBounds(260, 268, 33, 14);
		add(lblPkr2);
		lblPkr2.setVisible(false);
		
		
		JLabel lblCurrentState = new JLabel("Current State");
		lblCurrentState.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentState.setBounds(22, 296, 99, 14);
		add(lblCurrentState);
		
		btnFinish = new JButton("Submit");
		btnFinish.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFinish.setEnabled(false);
		btnFinish.setHorizontalAlignment(SwingConstants.LEFT);
		btnFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!takeResidentialSpecification())
						{
						lblSuccessImg.setVisible(true);
						lblSuccessMsg.setVisible(true);
						btnFinish.setEnabled(false);
						}
					} catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
		});
		btnFinish.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446290275_Check.png")));
		btnFinish.setBounds(242, 580, 102, 30);
		add(btnFinish);
		
		
		lblError1 = new JLabel(" ");
		lblError1.setIcon(new ImageIcon(AddPanel.class.getResource("/images/Error_Symbol.png")));
		lblError1.setBounds(289, 129, 39, 20);
		lblError1.setVisible(false);
		add(lblError1);
		
		lblError3 = new JLabel(" ");
		lblError3.setIcon(new ImageIcon(AddPanel.class.getResource("/images/Error_Symbol.png")));
		lblError3.setBounds(292, 265, 36, 20);
		lblError3.setVisible(false);
		add(lblError3);
		
		lblError2 = new JLabel(" ");
		lblError2.setBounds(292, 243, 39, 20);
		add(lblError2);
		lblError2.setIcon(new ImageIcon(AddPanel.class.getResource("/images/Error_Symbol.png")));
		
		lblNewProperty1 = new JLabel("New Property");
		lblNewProperty1.setBounds(24, 31, 255, 42);
		add(lblNewProperty1);
		lblNewProperty1.setForeground(Color.WHITE);
		lblNewProperty1.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446290185_Add.png")));
		lblNewProperty1.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewProperty1.setBackground(Color.LIGHT_GRAY);
		lblSuccessImg = new JLabel("");
		lblSuccessImg.setBounds(109, 340, 128, 128);
		add(lblSuccessImg);
		lblSuccessImg.setVisible(false);
		lblSuccessImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSuccessImg.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446314201_ok.png")));
		lblSuccessMsg = new JLabel("New Property Added Successfully!");
		lblSuccessMsg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuccessMsg.setBounds(88, 479, 198, 27);
		add(lblSuccessMsg);
		lblSuccessMsg.setVisible(false);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setHorizontalAlignment(SwingConstants.LEFT);
		btnBrowse.setIcon(new ImageIcon(AddPanel.class.getResource("/images/folder-search.png")));
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			browseForImage();
			}
		});
		btnBrowse.setBounds(690, 405, 110, 30);
		add(btnBrowse);
		
		label_1 = new JLabel("");
		label_1.setBounds(580, 150, 300, 250);
		add(label_1);
		label_1.setBackground(UIManager.getColor("RadioButton.interiorBackground"));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(ResizeImage("src\\pmsicons\\add-property-md.png"));
		label_1.revalidate();
		label_1.repaint();

		label = new JLabel("");
		label.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/panelback.png")));
		label.setBounds(0, 100, 900, 450);
		add(label);
		
		lblError2.setVisible(false);
		
		lblCurrentState.setVisible(false);
		rdbtnForRent.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
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
		rdbtnForsale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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

	rdbtnFull.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
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
	private String path="pms\\";
	public void browseForImage()
	{
		int imageRename=1;
		JFileChooser file =new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter =new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		file.addChoosableFileFilter(filter);
		int result=file.showSaveDialog(null);
		if (result==JFileChooser.APPROVE_OPTION)
		{		
			label_1.revalidate();
			label_1.repaint();
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
			label_1.setIcon(ResizeImage(temp_path));
		}
		else
			if (result==JFileChooser.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "No Image is seleted");
			path=" ";
		}
		//JOptionPane.showMessageDialog(null, "image:"+path);
		
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
	Image newImg =img.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon image=new ImageIcon(newImg);
	return image;
}
private void radiobtnpropertytype()
{
	rdbtnResidential = new JRadioButton("Residential");
	rdbtnResidential.setBackground(new Color(240, 240, 240));
	rdTypeBtnGoup.add(rdbtnResidential);
	rdbtnResidential.setBounds(202, 187, 117, 23);
	add(rdbtnResidential);
	
	rdbtnCommercial.setBounds(116, 187, 116, 23);
	add(rdbtnCommercial);
}
public JPanel getSpecificationPanel()
{
	return panel_2;
}
public void setSpecificationPanel()
{
	panel_2 = new JPanel();
	panel_2.setBackground(new Color(211, 211, 211));
	panel_2.setBounds(329, 99, 231, 329);
	add(panel_2);
	panel_2.setLayout(null);
	panel_2.setVisible(false);
	lblNumberOfBadrooms = new JLabel("Number of bedrooms");
	lblNumberOfBadrooms.setBounds(10, 75, 122, 24);
	panel_2.add(lblNumberOfBadrooms);
	
	lblNumberOfBathrooms = new JLabel("Number of bathrooms");
	lblNumberOfBathrooms.setBounds(10, 108, 122, 24);
	panel_2.add(lblNumberOfBathrooms);
	
	lblNumberOfStoreys = new JLabel("Number of storeys");
	lblNumberOfStoreys.setBounds(10, 138, 122, 24);
	panel_2.add(lblNumberOfStoreys);
	
	lblNumberOfKetchens = new JLabel("Number of ketchens");
	lblNumberOfKetchens.setBounds(10, 168, 122, 24);
	panel_2.add(lblNumberOfKetchens);
	
	lblBalcony = new JLabel("Balcony");
	lblBalcony.setBounds(10, 198, 122, 24);
	panel_2.add(lblBalcony);
	
	chckbxBalcony = new JCheckBox("Having");
	chckbxBalcony.setBounds(129, 199, 92, 23);
	panel_2.add(chckbxBalcony);
	
	lblTeriss = new JLabel("Teriss");
	lblTeriss.setBounds(10, 228, 122, 24);
	panel_2.add(lblTeriss);
	
	chckbxTeriss = new JCheckBox("Having");
	chckbxTeriss.setBounds(129, 225, 92, 23);
	panel_2.add(chckbxTeriss);
	
	txtNoBedrooms = new JTextField();
	txtNoBedrooms.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent arg0) {
		
		}
	});
	txtNoBedrooms.setBounds(129, 75, 92, 24);
	panel_2.add(txtNoBedrooms);
	txtNoBedrooms.setColumns(10);
	
	txtNoBathrooms = new JTextField();
	txtNoBathrooms.setColumns(10);
	txtNoBathrooms.setBounds(129, 108, 92, 24);
	panel_2.add(txtNoBathrooms);
	
	txtNoStoreys = new JTextField();
	txtNoStoreys.setColumns(10);
	txtNoStoreys.setBounds(129, 138, 92, 24);
	panel_2.add(txtNoStoreys);
	
	txtNoKetchens = new JTextField();
	txtNoKetchens.setColumns(10);
	txtNoKetchens.setBounds(129, 168, 92, 24);
	panel_2.add(txtNoKetchens);
	
	panel_3 = new JPanel();
	panel_3.setBackground(new Color(0, 139, 139));
	panel_3.setBounds(0, 11, 221, 57);
	panel_2.add(panel_3);
	panel_3.setLayout(null);
	
	lblResidentialPropertySpecification = new JLabel("Specification ");
	lblResidentialPropertySpecification.setForeground(Color.WHITE);
	lblResidentialPropertySpecification.setFont(new Font("Modern No. 20", Font.BOLD, 18));
	lblResidentialPropertySpecification.setBackground(Color.LIGHT_GRAY);
	lblResidentialPropertySpecification.setBounds(10, 11, 139, 42);
	panel_3.add(lblResidentialPropertySpecification);
	
}
private void setPanelsettings()
{
	setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), new Color(0, 0, 0), null));
	setLayout(null);
	this.setVisible(true);
	setSize(1000,700);
	setSpecificationPanel();
	
}
}
