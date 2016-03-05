package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class PropertyMenu extends JPanel{
	public String icondir;
	//static MainPage ui ;
	public PropertyMenu(MainPage ui) {
		super();
		setBackground(new Color(95, 158, 160));
		icondir="/pmsicons/";
		this.setVisible(true);
		this.setSize(901,665);
		this.setLayout(null);
		this.setLocation(0,100);

		JButton btnBack = new JButton("Back");
		btnBack.setSelectedIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1449357475_hand-left.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ui.switchPanel();
			}
		});
		btnBack.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setToolTipText("To Go Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(30, 580, 90, 30);
		add(btnBack);
		
		JButton addbtn = new JButton("Add Property");
		addbtn.setSelectedIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1449360105_hand-right.png")));
		addbtn.setIcon(new ImageIcon(PropertyMenu.class.getResource(icondir+"1446290185_Add.png")));
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ui.pushPanel("PropertyMenu"); 
				ui.setAddPanel();
			}
		});
		addbtn.setBounds(48, 133, 160, 39);
		add(addbtn);
		
		JButton editbtn = new JButton("Edit Property");
		editbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.pushPanel("PropertyMenu"); 
				ui.setEditPanel();
			}
		});
		editbtn.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1446290209_Edit.png")));
		editbtn.setSelectedIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1449357467_Right.png")));
		editbtn.setBounds(48, 183, 160, 39);
		add(editbtn);
		
		JButton viewbtn = new JButton("View All Property");
		viewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				ui.pushPanel("PropertyMenu"); 
					try {
						ui.setViewPanel();
					} catch (SQLException e) 
					{
						e.printStackTrace();
					}
			}
		});
		viewbtn.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1446305649_view-01.png")));
		viewbtn.setSelectedIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1449357467_Right.png")));
		
		viewbtn.setBounds(48, 233, 160, 39);
		add(viewbtn);
		JButton searchbtn = new JButton("Search Property");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ui.pushPanel("PropertyMenu"); 
					ui.setSearchPanel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		searchbtn.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1446305467_Search.png")));
		searchbtn.setBounds(48, 283, 160, 39);
		searchbtn.setSelectedIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1449357467_Right.png")));
		
		add(searchbtn);
		
		JButton deletebtn = new JButton("Delete Property");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.pushPanel("PropertyMenu"); 
			ui.setDeletePanel();
			}
		});
		
		JButton btnMakeReport = new JButton("Make Report");
		btnMakeReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			viewReport("Property.jrxml","E:\\property.pdf");
			}
		});
		btnMakeReport.setToolTipText("To Go Back");
		btnMakeReport.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMakeReport.setBounds(130, 580, 120, 30);
		add(btnMakeReport);
		
		JLabel lblMap = new JLabel("");
		lblMap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMap.setToolTipText("Click to See Maps");
		lblMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ui.pushPanel("PropertyMenu");
				ui.setMap();
			}
		});
		lblMap.setIcon(new ImageIcon(PropertyMenu.class.getResource("/images/maps.png")));
		lblMap.setBounds(760, 0, 111, 89);
		add(lblMap);
		deletebtn.setSelectedIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1449357467_Right.png")));
		deletebtn.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/1446489877_Streamline-70.png")));
		deletebtn.setBounds(48, 333, 160, 39);
		add(deletebtn);
		
		JLabel label = new JLabel("");
		label.setSize(128,128);
		label.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/Picture2.png")));
		label.setBounds(200, 111, 665, 409);
		add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(22, 15, 868, 61);
		//add(panel);
		panel.setLayout(null);
		
		JLabel lblPropertyMenu = new JLabel("Menu");
		lblPropertyMenu.setForeground(Color.WHITE);
		lblPropertyMenu.setBounds(30, 11, 403, 77);
		add(lblPropertyMenu);
		ui.setHeaderFooter(this);
		ui.header.add(lblPropertyMenu);
		lblPropertyMenu.setIcon(new ImageIcon(PropertyMenu.class.getResource("/pmsicons/Picture4.png")));
		lblPropertyMenu.setFont(new Font("Modern No. 20", Font.BOLD, 34));
		lblPropertyMenu.setBackground(new Color(72, 209, 204));
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setIcon(new ImageIcon(PropertyMenu.class.getResource("/images/background.png")));
		lblNewLabel.setBounds(0, 100, 900, 460);
		add(lblNewLabel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{addbtn, editbtn, viewbtn, searchbtn, deletebtn, btnBack}));
		}
	public void viewReport(String jasperfile,String outputfile)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pmsdb", "root", "12345");  
		    InputStream input= new FileInputStream(jasperfile);
		    JasperDesign jd  = JRXmlLoader.load(input);
		    JasperReport jr = JasperCompileManager.compileReport(jd);
		    JasperPrint  jp = JasperFillManager.fillReport(jr, new HashMap<String, Object>(),con);
		    JasperViewer.viewReport(jp);
		    JasperExportManager.exportReportToPdfFile(jp, outputfile);
			}
		catch(Exception e1)
		{
		    JOptionPane.showMessageDialog(null, e1);
		}
	}
}
