package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProblemManagerController;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings({ "serial", "unused" })
public class ManagerPEEM extends JPanel {
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox; 
	private JTextField txtSearch;
	private JButton btnSearch;
	//private DeleteView objDeleteView=new DeleteView();
	
//Variables
	private ProblemManagerController objPMC = ProblemManagerController.getInstance();
	private ResultSet rs=null;
	public String []data1=new String[5];
	private JLabel lblImage;
	private static boolean onetimeshow=true,bupdate=false;
	private static String combo=" ",txt=" ";
	private String []update=new String[4];
	/**
	 * Create the panel.
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ManagerPEEM(MainPage objMainFrame) {
		super();
		setBounds(new Rectangle(0, 0, 900, 660));
		setLayout(null);
		//TABLE
		//combo="  ";
		//txt="  ";
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setForeground(new Color(255, 255, 224));
		table.setBounds(0, 25, 630, 363);
		table.setBackground(new Color(0, 128, 128));
		//table.setGridColor(UIManager.getColor("CheckBox.light"));
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(100, 149, 237), new Color(100, 149, 237)));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Dweller_Id","Time", "ProblemNo", "Status","Description"
			}
		));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
		scrollPane.setBounds(238, 154, 632, 351);
		add(scrollPane);
//COMBOBOX		
		comboBox = new JComboBox();
		comboBox.setBounds(44, 154, 167, 33);
		add(comboBox);
//TXTSEARCH		
		txtSearch = new JTextField();
		txtSearch.setBounds(44, 198, 167, 33);
		add(txtSearch);
		txtSearch.setColumns(10);
//BTNSEARCH		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setBounds(101, 242, 110, 30);
		btnSearch.setIcon(new ImageIcon(ManagerPEEM.class.getResource("/images/search.png")));
		add(btnSearch);
		
		JLabel lblLogout = new JLabel("");
		lblLogout.setToolTipText("Log Out");
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				try 
				{
					bupdate=false;
					onetimeshow=true;
					combo=" ";
					txt=" ";
					if(!update.equals(null))
					objPMC.UpdateStatus(update);
					update=null;
					objMainFrame.setLoginPage();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objMainFrame.setUserType("");
			}
		});
		lblLogout.setIcon(new ImageIcon(ManagerPage.class.getResource("/Images/Newlogout.png")));
		lblLogout.setBounds(851, 40, 39, 36);
		add(lblLogout);
		
		
		lblImage = new JLabel("");
		lblImage.setBounds(0, 100, 900, 450);
		lblImage.setIcon(new ImageIcon(ManagerPEEM.class.getResource("/images/newBackground.png")));
		add(lblImage);
		
		JLabel lblMaintenancePage = new JLabel("Maintenance Page");
		lblMaintenancePage.setForeground(new Color(255, 255, 255));
		lblMaintenancePage.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblMaintenancePage.setBounds(20, 11, 426, 78);
		add(lblMaintenancePage);
		
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBack.setIcon(new ImageIcon(ManagerPEEM.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//combo="Condition";
				//txt="Unseen";
			    objMainFrame.setManagerPage();
			}
		});
		btnBack.setBounds(30, 580, 90, 30);
		add(btnBack);
		String[] items = { "  ","Dweller_Id", "ProblemNo", "Electrition",
				"Plumber", "Emergency", "Others","Solved","Unsolved","Today" };
		for (int i = 0; i < 9; i++) {
			comboBox.addItem(items[i]);
		}
		
		String []data=new String [4];
			
//Adding when Delete or Back is Pressed
		if(onetimeshow==false)
		{
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			int rowCount = dm.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) 
			{
			    dm.removeRow(i);
			}
			//
			
			//String []data=new String[3];
			data[0]="application";
			if (combo.equals("Dweller_Id")|| combo.equals("ProblemNo"))
			{
				data[1]=combo;
				data[2]=txt;
			}
			else
			{
				if (combo.equals("Solved")|| combo.equals("Unsolved"))
					data[1]="Status";
				else data[1]="Application_Type";
			data[2]=combo;
			}
			rs=null;
			if(bupdate==true)
			{
				data[1]="Condition";
				data[2]="Unseen";
			}
			rs = objPMC.SELECTEDDATA(data);			
		}
//ALL DATA TO Table
		else
		{
			bupdate=true;
		System.out.println("UNSEEN IS HERE");
		onetimeshow=false;
		data[0]="application";
		data[1]="Condition";
		data[2]="Unseen";
		rs=null;
		rs = objPMC.SELECTEDDATA(data);
		ResultSet rs1 = objPMC.SELECTEDDATA(data);
		ResultSet rs2 = objPMC.SELECTEDDATA(data);
		try {
			if(!rs2.next())
			{
			JOptionPane.showMessageDialog(null, "No New Problem Added!");
			combo=" ";
			txt=" ";
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
			}
		
// Adding Data to Table
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {
			
		    while(rs.next())
			{
				Object[] row = {rs.getString("Dweller_Id"), rs.getString("Time"), rs.getString("ProblemNo"), rs.getString("Status"),rs.getString("Description")};
				model.addRow(row);
				update[0]="application";
				update[1]=" `Condition`='Seen' ";
				update[2]="Time";
				update[3]=rs.getString("Time");
				//data1[3]=rs.getString("Time");
			}
			} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
/////////////////////////////////////////////
		
//COMBOBOX ACTIONLISTENER		
		
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if((comboBox.getSelectedItem().toString().equals("Dweller_Id")||comboBox.getSelectedItem().toString().equals("ProblemNo")))
				{
					txtSearch.setVisible(true);
				}
				else txtSearch.setVisible(false);
				}
		});
		
//btnSEARCH ACTIONLISTENER		
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				bupdate=false;
				if(!update.equals(null))
				objPMC.UpdateStatus(update);
				
				combo=comboBox.getSelectedItem().toString();
			    txt=txtSearch.getText().toString();
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				data[0]="application";
				if (comboBox.getSelectedItem().toString().equals("Dweller_Id")|| comboBox.getSelectedItem().toString().equals("ProblemNo"))
				{
					data[1]=comboBox.getSelectedItem().toString();
					data[2]=txtSearch.getText().toString();
				}
				else
				{
					if (comboBox.getSelectedItem().toString().equals("Solved")|| comboBox.getSelectedItem().toString().equals("Unsolved"))
						data[1]="Status";
					else data[1]="Application_Type";
				data[2]=comboBox.getSelectedItem().toString();
				}
				rs=null;
				rs = objPMC.SELECTEDDATA(data);
				
//Showing Message Box				
				ResultSet rs1 = objPMC.SELECTEDDATA(data);
				
				try {
					if(!rs1.next())
					{
					JOptionPane.showMessageDialog(null, "No Data to Show Now!");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			///////
		/*try {
					if(rs.wasNull())
					{
					JOptionPane.showMessageDialog(null, "No Data to Show Now!");
					}
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}*/

					DefaultTableModel model = (DefaultTableModel) table.getModel();
							try {
								while(rs.next())
								{
									System.out.println("btn search............daa in table");
									Object[] row = {rs.getString("Dweller_Id"), rs.getString("Time"), rs.getString("ProblemNo"), rs.getString("Status"),rs.getString("Description")};
									model.addRow(row);
								
								}
								} 
							catch (SQLException e)
							{
								e.printStackTrace();
							}
							txtSearch.setText(" ");
							
			}
		});
		
//ENTER KEY LISTNER
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					data1[0]=(String) table.getValueAt(table.getSelectedRow(), 0);
					data1[1]=(String) table.getValueAt(table.getSelectedRow(), 1);
					data1[2]=(String) table.getValueAt(table.getSelectedRow(), 2);
					data1[3]=(String) table.getValueAt(table.getSelectedRow(), 3);
					data1[4]=(String) table.getValueAt(table.getSelectedRow(), 4);
					DeleteManagerView objDeleteView=new DeleteManagerView(objMainFrame,data1);
					objDeleteView.delete(objMainFrame, data1);
					//objDeleteView.delete(objMainFrame);
					}
				
			}
		});
	
		
		
	}
}
