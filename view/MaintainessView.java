package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProblemManagerController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MaintainessView extends JPanel {
	private JTable table;
//VARIABLES
	private ProblemManagerController objPMC = ProblemManagerController.getInstance();
	//private String SplitBehavior=" ";
	private ResultSet rs=null;
	public String []data1=new String[5];
	
	/**
	 * Create the panel.
	 */
	public MaintainessView(MainPage objMainFrame) {
		setBounds(new Rectangle(0, 0, 900, 660));
		setLayout(null);
		
		//TABLE
				table = new JTable();
				table.setForeground(Color.WHITE);
				table.setBackground(new Color(0, 128, 128));
				//table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
				table.setFont(new Font("Tahoma", Font.PLAIN, 12));
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Dweller_Id", "Time", "ProblemNo", "Status", "Description"
					}
				));
				//table.setBounds(1, 26, 433, 363);
				//add(table);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
				scrollPane.setBounds(52, 149, 632, 363);
				add(scrollPane);
				table.setFillsViewportHeight(true);
				
				JLabel lblImage = new JLabel("");
				lblImage.setIcon(new ImageIcon(MaintainessView.class.getResource("/images/newBackground.png")));
				lblImage.setBounds(0, 100, 900, 450);
				add(lblImage);
				
				JLabel lblViewApplicationList = new JLabel("View Application List");
				lblViewApplicationList.setIcon(new ImageIcon(MaintainessView.class.getResource("/images/list.png")));
				lblViewApplicationList.setForeground(new Color(255, 255, 255));
				lblViewApplicationList.setFont(new Font("Modern No. 20", Font.BOLD, 28));
				lblViewApplicationList.setBounds(24, 21, 458, 64);
				add(lblViewApplicationList);
				

				JLabel lblLogout = new JLabel("");
				lblLogout.setToolTipText("Log Out");
				lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblLogout.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0)
					{
						try {
							objMainFrame.setLoginPage();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						objMainFrame.setUserType("");
					}
				});
				lblLogout.setIcon(new ImageIcon(ManagerPage.class.getResource("/Images/Newlogout.png")));
				lblLogout.setBounds(851, 40, 39, 36);
				add(lblLogout);
		
				//SplitBehavior="Others";
				String []data=new String [3];
				data[0]="application";
				data[1]="Application_Type";
				data[2]=objMainFrame.getUserType();
				rs = objPMC.SELECTEDDATA(data);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
							try {
								while(rs.next())
								{
									Object[] row = {rs.getString("Dweller_Id"), rs.getString("Time"), rs.getString("ProblemNo"), rs.getString("Status"), rs.getString("Description")};
									model.addRow(row);
								
								}
								} 
							catch (SQLException e)
							{
								e.printStackTrace();
							}
							
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
									DeleteMaintainessView objDeleteMaintainessView=new DeleteMaintainessView(objMainFrame,data1);
									objDeleteMaintainessView.delete(objMainFrame, data1);
									}
								}
							});
							
				

	}
}
