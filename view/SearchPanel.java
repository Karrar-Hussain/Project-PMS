package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import controller.Booking;
import controller.CommercialProperty;
import controller.ResidentialProperty;
@SuppressWarnings("serial")
public class SearchPanel extends JPanel{
	private JTextField txtPropertyId;
	private JTextField txtAreaFrom;
	private JTextField txtToArea;
	private JTextField txtPriceFrom;
	private JTextField txtToPrice;
	private JLabel lblid ;
	private JLabel lblstatus;
	private JLabel lbltype ;
	private JLabel lbllocation ;
	private JLabel lblarea ;
	private JButton btnSearch;
	private JList<?> list;
	private ResultSet rs;
	public String selectvalue;
	private JRadioButton rdbtnCommercial ;
	private JRadioButton rdbtnResidential ;
	private JRadioButton rdbtnRental ;
	private JRadioButton rdbtnPurchase ;
	private JScrollPane scrollPane;
	private JLabel lblcurrentstate ;
	private JLabel lblprice;
	private JLabel lblImage ;
	private ResidentialProperty rp;
	private CommercialProperty cp;
	private JButton btnBookProperty;
	private JLabel lblSuccessMsg;
	private JButton btnEditProperty;
	private Booking bookP;
	public SearchPanel(MainPage mainPage) throws SQLException
	{
		super();
		bookP=new Booking();
		rp=new ResidentialProperty();
		cp=new CommercialProperty();
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(240, 240, 240));
		this.setVisible(true);
		this.setSize(1000,700);
		setLayout(null);
		scrollPane= new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setLocation(60, 319);
	    setLayout(null);
		scrollPane.setSize(50, 202);
		this.add(scrollPane);
		JLabel lblPropertyId = new JLabel("Property ID");
		lblPropertyId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPropertyId.setBounds(50, 121, 68, 14);
		add(lblPropertyId);
		
		JLabel lblAreaFrom = new JLabel("Area From");
		lblAreaFrom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAreaFrom.setBounds(50, 146, 68, 14);
		add(lblAreaFrom);
		
		JLabel lblToArea = new JLabel("To");
		lblToArea.setBounds(231, 146, 33, 14);
		add(lblToArea);
		
		txtPropertyId = new JTextField();
		txtPropertyId.setBounds(128, 118, 86, 20);
		txtPropertyId.setColumns(10);
		txtPropertyId.setText("0");
		add(txtPropertyId);
		
		txtAreaFrom = new JTextField();
		txtAreaFrom.setBounds(128, 143, 86, 20);
		add(txtAreaFrom);
		txtAreaFrom.setText("0");
		txtAreaFrom.setColumns(10);
		
		txtToArea = new JTextField();
		txtToArea.setText("0");
		txtToArea.setBounds(264, 143, 86, 20);
		add(txtToArea);
		txtToArea.setColumns(10);
		
		rdbtnResidential = new JRadioButton("Residential");
		
		rdbtnResidential.setBounds(128, 195, 109, 23);
		add(rdbtnResidential);
		
		rdbtnCommercial = new JRadioButton("Commercial");
		rdbtnCommercial.setBounds(241, 195, 109, 23);
		add(rdbtnCommercial);
		ButtonGroup typegroup=new ButtonGroup();
		typegroup.add(rdbtnCommercial);
		typegroup.add(rdbtnResidential);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setBounds(50, 199, 46, 14);
		add(lblType);
		
		JLabel lblPrice = new JLabel("Price From");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(50, 174, 68, 14);
		add(lblPrice);
		
		JLabel lblToPrice = new JLabel("To");
		lblToPrice.setBounds(231, 174, 33, 14);
		add(lblToPrice);
		
		txtPriceFrom = new JTextField();
		txtPriceFrom.setText("0");
		txtPriceFrom.setColumns(10);
		txtPriceFrom.setBounds(128, 171, 86, 20);
		add(txtPriceFrom);
		
		txtToPrice = new JTextField();
		txtToPrice.setText("0");
		txtToPrice.setColumns(10);
		txtToPrice.setBounds(264, 171, 86, 20);
		add(txtToPrice);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategory.setBounds(50, 224, 68, 14);
		add(lblCategory);
		
		rdbtnRental = new JRadioButton("Rental");
		rdbtnRental.setBounds(128, 221, 109, 23);
		add(rdbtnRental);
		
		rdbtnPurchase = new JRadioButton("Purchase");
		rdbtnPurchase.setBounds(241, 221, 109, 23);
		add(rdbtnPurchase);

		ButtonGroup statusgroup=new ButtonGroup();
		statusgroup.add(rdbtnPurchase);
		statusgroup.add(rdbtnRental);
		
		JLabel lblPropertyId1 = new JLabel("Property ID");
		lblPropertyId1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPropertyId1.setBounds(128, 308, 75, 20);
		add(lblPropertyId1);
		
		JLabel lblType1 = new JLabel("Type");
		lblType1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType1.setBounds(128, 339, 75, 19);
		add(lblType1);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArea.setBounds(128, 366, 75, 19);
		add(lblArea);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(128, 396, 75, 19);
		add(lblLocation);
		
		 lblid = new JLabel("ID");
		 lblid.setForeground(new Color(0, 0, 0));
		lblid.setBounds(231, 308, 75, 20);
		add(lblid);
		
		 lbltype = new JLabel("Commercial or Residential");
		 lbltype.setForeground(new Color(0, 0, 0));
		lbltype.setBounds(231, 339, 136, 19);
		add(lbltype);
		
		lblarea = new JLabel("area(marla)");
		lblarea.setForeground(new Color(0, 0, 0));
		lblarea.setBounds(231, 366, 123, 19);
		add(lblarea);
		
		lbllocation = new JLabel("location");
		lbllocation.setForeground(new Color(0, 0, 0));
		lbllocation.setBounds(231, 396, 185, 19);
		add(lbllocation);

		btnSearch = new JButton("Search...");
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.revalidate(); 
				showFoundList();
						}
		});
		btnSearch.setBounds(128, 251, 109, 23);
		add(btnSearch);
		
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
		
		btnSearch.setIcon(new ImageIcon(SearchPanel.class.getResource("/pmsicons/1446426674_icon-111-search.png")));
		this.formatButtons();
		showFoundList();
		btnBookProperty = new JButton("Book Property");
		btnBookProperty.setHorizontalAlignment(SwingConstants.LEFT);
		btnBookProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!bookP.hasBookedProperty(mainPage.getUserId()))
					{
						System.out.println("Fasle" );
						if(!bookP.isBooked(Integer.parseInt(selectvalue)))
						{
							if(bookP.insertBooking(mainPage.getUserId(),Integer.parseInt(selectvalue)))
							{
								JOptionPane.showMessageDialog(null, "Sorry There is Problem with booking New Property","Error",JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								lblSuccessMsg.setForeground(Color.BLACK);
								lblSuccessMsg.setText("Your Property is booked Successfully!");	
								lblSuccessMsg.setVisible(true);
							}
						}
						else
						{
							lblSuccessMsg.setForeground(Color.RED);
							lblSuccessMsg.setVisible(true);
							lblSuccessMsg.setText("Sorry this property is already booked!");
						}
					}
					else
					{
						lblSuccessMsg.setForeground(Color.RED);
						lblSuccessMsg.setVisible(true);
						lblSuccessMsg.setText("Sorry can't book more than one property at a time!");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBookProperty.setBounds(117, 503, 109, 23);
		add(btnBookProperty);
		JLabel backwall;
		
		lblSuccessMsg = new JLabel("Thank you, This property is booked Succesfully!");
		lblSuccessMsg.setBounds(345, 507, 486, 14);
		lblSuccessMsg.setVisible(false);
		
		btnEditProperty = new JButton("Edit Booking");
		btnEditProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int userid=mainPage.getUserId();
					if(bookP.hasBookedProperty(userid))
					{
						if(!bookP.isBooked(Integer.parseInt(selectvalue)))
						{
							if(bookP.updateBookedProperty(Integer.parseInt(selectvalue),userid))
							{
								lblSuccessMsg.setForeground(Color.BLACK);
								lblSuccessMsg.setText("This Property is booked Successfully!");	
								lblSuccessMsg.setVisible(true);
							}
							else
							{
								lblSuccessMsg.setForeground(Color.RED);
								lblSuccessMsg.setText("Sorry You cannot book this property");	
								lblSuccessMsg.setVisible(true);
							}
						}
						else
						{
							lblSuccessMsg.setForeground(Color.RED);
							lblSuccessMsg.setText("Sorry You cannot book this property, it is already booked!");	
							lblSuccessMsg.setVisible(true);
						}
					}
					else
					{
						lblSuccessMsg.setForeground(Color.RED);
						lblSuccessMsg.setText("Selected Property is currently booked!");	
						lblSuccessMsg.setVisible(true);
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditProperty.setBounds(231, 503, 109, 23);
		add(btnEditProperty);
		add(lblSuccessMsg);
		backwall = new JLabel("");
		backwall.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/panelback.png")));
		backwall.setBounds(0, 100, 900, 450);
		add(backwall);
	}
	public void showFoundList()
	{
		if(txtPriceFrom.getText().matches("[0-9]*")&&txtToPrice.getText().matches("[0-9]*")&&txtAreaFrom.getText().matches("[0-9]*")&&txtToArea.getText().matches("[0-9]*")&&txtPropertyId.getText().matches("[0-9]*"))
		{
		String[] array;
		String type="0",status="0";
		try {
		int id,pricefrom,toprice,areafrom,toarea;
		id=pricefrom=toprice=areafrom=toarea=0;
		id=Integer.parseInt(txtPropertyId.getText().toString());
		areafrom=Integer.parseInt(txtAreaFrom.getText().toString());
		toarea=Integer.parseInt(txtToArea.getText().toString());
		pricefrom=Integer.parseInt(txtPriceFrom.getText().toString());
		toprice=Integer.parseInt(txtToPrice.getText().toString());
		if(rdbtnResidential.isSelected())
			type="Residential";
		else if(rdbtnCommercial.isSelected())
			type="Commercial";
		if(rdbtnPurchase.isSelected())
			status="ForSale";
		else if(rdbtnRental.isSelected())
			status="ForRent";
			array = rp.searchProperty(id,areafrom,toarea,pricefrom,toprice,status,type);
			scrollList(array);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage().toString());
		}
		}
		else
			JOptionPane.showMessageDialog(null, "Enter Valid Integer Value For Search","ERROR MSG",JOptionPane.ERROR_MESSAGE);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		 			listSelected();
		 			lblSuccessMsg.setVisible(false);
		 		}
	        });
		list.setVisible(true);
	}
	public void listSelected()
	{
		rs=(com.mysql.jdbc.ResultSet) rp.oneProperty(selectvalue);
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
	public void formatButtons()
	{
		JLabel lblId = new JLabel("Property ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(50, 254, 68, 14);
		add(lblId);
		
		JLabel lblPropertiesFound = new JLabel("Properties Found");
		lblPropertiesFound.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPropertiesFound.setBounds(47, 285, 96, 14);
		add(lblPropertiesFound);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(128, 426, 46, 14);
		add(lblStatus);
		
		lblstatus = new JLabel("status");
		lblstatus.setForeground(new Color(0, 0, 0));
		lblstatus.setBounds(231, 426, 109, 14);
		add(lblstatus);
		
		JLabel lblCurrentStatus = new JLabel("Current Status");
		lblCurrentStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentStatus.setBounds(128, 451, 86, 14);
		add(lblCurrentStatus);
		
		lblcurrentstate = new JLabel("Current Status");
		lblcurrentstate.setForeground(new Color(0, 0, 0));
		lblcurrentstate.setBounds(231, 451, 154, 14);
		add(lblcurrentstate);
		
		JLabel lblPrice = new JLabel("Price/Rent");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(128, 478, 75, 14);
		add(lblPrice);
		
		lblprice = new JLabel("Price");
		lblprice.setForeground(new Color(0, 0, 0));
		lblprice.setBounds(231, 478, 154, 14);
		add(lblprice);
		
		
		JLabel lblNewLabel = new JLabel("Search Properties");
		lblNewLabel.setBounds(26, 37, 327, 39);
		add(lblNewLabel);
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewLabel.setIcon(new ImageIcon(SearchPanel.class.getResource("/pmsicons/1446305467_Search.png")));
		
		lblImage = new JLabel(" ");
		lblImage.setBounds(408, 174, 278, 276);
		add(lblImage);
		lblImage.setBackground(new Color(70, 130, 180));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon(SearchPanel.class.getResource("/pmsicons/home-search.png")));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtPropertyId, txtAreaFrom, txtToArea, txtPriceFrom, txtToPrice, rdbtnResidential, rdbtnCommercial, rdbtnRental, rdbtnPurchase, btnSearch}));
	}

/*
 * Image Resize
 */ 
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
