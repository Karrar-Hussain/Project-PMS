package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.ResultSet;

import controller.CommercialProperty;
import controller.ResidentialProperty;

@SuppressWarnings("serial")
public class ViewPanel extends JPanel
{
	protected static final String ResultSet = null;
	private JLabel lblid ;
	private JLabel lbltype ;
	private JLabel lbllocation ;
	private JLabel lblarea ;
	private JList<?> list;
	private String selectvalue;
	private ResultSet rs;
	private JScrollPane scrollPane;
	private JLabel lblImage;
	private JLabel lblType;
	private JLabel lblArea;
	private JLabel lblLocation;
	private JLabel lblPropertyId;
	private JLabel lblPrice;
	private JLabel lblCurrentStates;
	private JLabel lblprice;
	private JLabel lblcurrentstate;
	private JPanel panel_2;
	private JLabel lblResidentialSpecification;
	private JPanel panel_3;
	private JLabel lblNbedroom;
	private JLabel lblNbathroom;
	private JLabel lblNstorey;
	private JLabel lblNkitchen;
	private JLabel lblbalcony;
	private JLabel lblteriss;
	private JLabel lblSelect;
	private JLabel lblSpecificationId;
	private JLabel lblspecificationid;
	private JLabel lblStatus;
	private JLabel lblstatus;
	private ResidentialProperty rp;
	private CommercialProperty cp;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void scrollList(String [] array)
	{
		scrollPane = new JScrollPane();
		scrollPane.setLocation(33, 175);
		scrollPane.setSize(50, 165);
        setLayout(null);
		 this.add(scrollPane);
		 list = new JList(array);
		 scrollPane.setViewportView(list);
		 
		 list.setBackground(new Color(0, 128, 128));
		 list.setVisibleRowCount(15);
		 list.addListSelectionListener(new ListSelectionListener() 
		 {
		 		@Override
		 		public void valueChanged(ListSelectionEvent arg0) {
		 			selectvalue=list.getSelectedValue().toString();
		 			listSelected();
		 		}
	        });
		 list.setVisible(true);
	}

	public void formatButtons()
	{
		
				panel_2 = new JPanel();
				panel_2.setBackground(new Color(211, 211, 211));
				panel_2.setBounds(300, 102, 234, 306);
				add(panel_2);
				panel_2.setLayout(null);
				panel_2.setVisible(false);
				JLabel lblNumberOfBadrooms = new JLabel("No. of bedrooms");
				lblNumberOfBadrooms.setBounds(10, 113, 97, 24);
				panel_2.add(lblNumberOfBadrooms);
				
				JLabel lblNumberOfBathrooms = new JLabel("No. of bathrooms");
				lblNumberOfBathrooms.setBounds(10, 146, 97, 24);
				panel_2.add(lblNumberOfBathrooms);
				
				JLabel lblNumberOfStoreys = new JLabel("No. of storeys");
				lblNumberOfStoreys.setBounds(10, 176, 97, 24);
				panel_2.add(lblNumberOfStoreys);
				
				JLabel lblNumberOfKetchens = new JLabel("No. of ketchens");
				lblNumberOfKetchens.setBounds(10, 206, 97, 24);
				panel_2.add(lblNumberOfKetchens);
				
				JLabel lblBalcony = new JLabel("Balcony");
				lblBalcony.setBounds(10, 236, 97, 24);
				panel_2.add(lblBalcony);
				
				JLabel lblTeriss = new JLabel("Teriss");
				lblTeriss.setBounds(10, 266, 97, 24);
				panel_2.add(lblTeriss);
				
				panel_3 = new JPanel();
				panel_3.setBackground(new Color(60, 179, 113));
				panel_3.setBounds(-2, 11, 231, 50);
				panel_2.add(panel_3);
				panel_3.setLayout(null);
				
				lblResidentialSpecification = new JLabel("Residential Specification");
				lblResidentialSpecification.setBounds(0, 11, 237, 28);
				lblResidentialSpecification.setForeground(new Color(0, 0, 0));
				lblResidentialSpecification.setHorizontalAlignment(SwingConstants.CENTER);
				panel_3.add(lblResidentialSpecification);
				lblResidentialSpecification.setFont(new Font("Modern No. 20", Font.BOLD, 18));
				
				lblNbedroom = new JLabel("Nbedroom");
				lblNbedroom.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNbedroom.setHorizontalAlignment(SwingConstants.CENTER);
				lblNbedroom.setForeground(new Color(0, 0, 0));
				lblNbedroom.setBounds(138, 113, 82, 24);
				panel_2.add(lblNbedroom);
				
				lblNbathroom = new JLabel("Nbathroom");
				lblNbathroom.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNbathroom.setHorizontalAlignment(SwingConstants.CENTER);
				lblNbathroom.setForeground(new Color(0, 0, 0));
				lblNbathroom.setBounds(138, 146, 82, 24);
				panel_2.add(lblNbathroom);
				
				lblNstorey = new JLabel("Nstorey");
				lblNstorey.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNstorey.setHorizontalAlignment(SwingConstants.CENTER);
				lblNstorey.setForeground(new Color(0, 0, 0));
				lblNstorey.setBounds(138, 176, 82, 24);
				panel_2.add(lblNstorey);
				
				lblNkitchen = new JLabel("Nkitchen");
				lblNkitchen.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNkitchen.setHorizontalAlignment(SwingConstants.CENTER);
				lblNkitchen.setForeground(new Color(0, 0, 0));
				lblNkitchen.setBounds(138, 206, 82, 24);
				panel_2.add(lblNkitchen);
				
				lblbalcony = new JLabel("balcony");
				lblbalcony.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblbalcony.setHorizontalAlignment(SwingConstants.CENTER);
				lblbalcony.setForeground(new Color(0, 0, 0));
				lblbalcony.setBounds(138, 236, 82, 24);
				panel_2.add(lblbalcony);
				
				lblteriss = new JLabel("teriss");
				lblteriss.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblteriss.setHorizontalAlignment(SwingConstants.CENTER);
				lblteriss.setForeground(new Color(0, 0, 0));
				lblteriss.setBounds(138, 266, 82, 24);
				panel_2.add(lblteriss);
				
				lblSpecificationId = new JLabel("Specification ID");
				lblSpecificationId.setBounds(10, 86, 97, 24);
				panel_2.add(lblSpecificationId);
				
				lblspecificationid = new JLabel("ID");
				lblspecificationid.setHorizontalAlignment(SwingConstants.CENTER);
				lblspecificationid.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblspecificationid.setForeground(new Color(0, 0, 0));
				lblspecificationid.setBounds(138, 86, 82, 24);
				panel_2.add(lblspecificationid);
		
		JLabel lblId = new JLabel("Property ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(10, 159, 75, 14);
		add(lblId);
		
		lblPrice = new JLabel("Price/Rent");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(84, 256, 75, 19);
		add(lblPrice);
		
		lblCurrentStates = new JLabel("CurrentState");
		lblCurrentStates.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentStates.setBounds(84, 316, 75, 19);
		add(lblCurrentStates);
		
		lblprice = new JLabel("Price");
		lblprice.setForeground(new Color(0, 0, 0));
		lblprice.setBounds(175, 257, 135, 19);
		add(lblprice);
		
		lblcurrentstate = new JLabel("Status");
		lblcurrentstate.setForeground(new Color(0, 0, 0));
		lblcurrentstate.setBounds(175, 317, 112, 19);
		add(lblcurrentstate);
		typeResidential();
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{list}));
	}
	public void typeResidential()
	{
		lblSelect = new JLabel("Select");
		lblSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelect.setBounds(10, 141, 64, 14);
		add(lblSelect);
		
		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(84, 286, 75, 19);
		add(lblStatus);
		
		lblstatus = new JLabel("Status");
		lblstatus.setForeground(new Color(0, 0, 0));
		lblstatus.setBounds(175, 287, 112, 19);
		add(lblstatus);
		
		JLabel lblNewLabel = new JLabel("View Properties");
		lblNewLabel.setBounds(34, 29, 307, 38);
		add(lblNewLabel);
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewLabel.setIcon(new ImageIcon(ViewPanel.class.getResource("/pmsicons/1446305649_view-01.png")));
	}
	public void typeBaseView(String type) throws SQLException
	{
		if(type.equals("Residential"))
		{
			@SuppressWarnings("unused")
			int specificationid=0;
			rp.setPropertyId(Integer.parseInt(selectvalue));
			rs=(com.mysql.jdbc.ResultSet) rp.getResidential();
			if(rs.next())
			{
				specificationid=rs.getInt("specificationid");
				this.lblspecificationid.setText(rs.getInt("specificationid")+"");
				this.lblNbedroom.setText(rs.getInt("nobedroom")+"");
				this.lblNbathroom.setText(rs.getInt("nobathroom")+"");
				this.lblNkitchen.setText(rs.getInt("nokitchen")+"");
				this.lblNstorey.setText(rs.getInt("nostorey")+"");
				this.lblbalcony.setText(rs.getInt("balcony")+"");
				this.lblteriss.setText(rs.getInt("teriss")+"");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Residential Property exist");
			}
		}
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
				if(!path.contains("\\"))
					path="src\\PMSIcons\\search-home.png";
					lblImage.setIcon(this.ResizeImage(path));
				}
				status=lblstatus.getText();
				//p.setType(lbltype.getText());
				if(type.equals("Residential"))
				{
					rp.setStatus(status);
					rs=(com.mysql.jdbc.ResultSet) rp.oneResidential(Integer.parseInt(selectvalue));
					if(rs.next())
					{
						panel_2.setVisible(true);
						if(status.equals("ForRent"))
						{
							lblprice.setText(rs.getInt("rent")+"");
							if(rs.getBoolean(1)==true)
								lblcurrentstate.setText("On Rent");
							else
								lblcurrentstate.setText("Available For Rent");
						}else if(status.equals("ForSale"))
						{
							lblprice.setText(rs.getInt("price")+"");
							if(rs.getBoolean(1)==true)
								lblcurrentstate.setText("Sold");
							else
								lblcurrentstate.setText("Available For Rent");
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
					panel_2.setVisible(false);
					ResultSet rs3=(com.mysql.jdbc.ResultSet) cp.oneCommercial(Integer.parseInt(selectvalue));
					if(rs3.next())
					{
						//JOptionPane.showMessageDialog(null, ""+status);
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
								lblcurrentstate.setText("Available For Rent");
						}
						else
						{
							lblcurrentstate.setText("no info");
							lblprice.setText("0");
						}
					}
					}
					typeBaseView(type);
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
	}
	public ViewPanel(MainPage uiWindow) throws SQLException
	{
		super();
		setBackground(new Color(240, 240, 240));
		this.setLayout(null);
		this.setSize(958,661);
		this.setVisible(true);

		lblImage = new JLabel(" ");
		lblImage.setBounds(556, 126, 307, 282);
		add(lblImage);
		lblImage.setBackground(new Color(240, 240, 240));
		lblImage.setIcon(this.ResizeImage("src\\PMSIcons\\search-home.png"));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		rp=new ResidentialProperty();
		cp=new CommercialProperty();
		String [] array=rp.getAllProperty();
		if(array!=null)
		{
			this.scrollList(array);
		}
		

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
		
		lblPropertyId = new JLabel("Property ID");
		lblPropertyId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPropertyId.setBounds(84, 139, 75, 19);
		add(lblPropertyId);
		
		lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setBounds(84, 169, 75, 19);
		add(lblType);
		
		lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArea.setBounds(84, 199, 75, 16);
		add(lblArea);
		
		lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(84, 226, 75, 19);
		add(lblLocation);
		
		 lblid = new JLabel("ID");
		 lblid.setForeground(new Color(0, 0, 0));
		lblid.setBounds(175, 139, 128, 19);
		add(lblid);
		
		 lbltype = new JLabel("type");
		 lbltype.setForeground(new Color(0, 0, 0));
		lbltype.setBounds(175, 170, 128, 19);
		add(lbltype);
		
		lblarea = new JLabel("area(marla)");
		lblarea.setForeground(new Color(0, 0, 0));
		lblarea.setBounds(175, 199, 92, 19);
		add(lblarea);
		
		lbllocation = new JLabel("location");
		lbllocation.setForeground(new Color(0, 0, 0));
		lbllocation.setBounds(175, 227, 147, 19);
		add(lbllocation);
		
		this.formatButtons();

		JLabel backwall;
		backwall = new JLabel("");
		backwall.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/panelback.png")));
		backwall.setBounds(0, 100, 900, 450);
		add(backwall);
	}
 public ImageIcon ResizeImage(String path)
{
	ImageIcon myImage =new ImageIcon(path);
	Image img =myImage.getImage();
	Image newImg =img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon image=new ImageIcon(newImg);
	return image;
}
}
