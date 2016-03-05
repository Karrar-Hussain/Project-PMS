package view;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.mysql.jdbc.Connection;

import controller.Booking;

@SuppressWarnings("serial")
public class BookingPanel extends JPanel{
	private JTable table;
	private Booking booking;
	private DefaultTableModel model;
	private String status;
	public BookingPanel(MainPage uiWindow) throws SQLException
	{
		super();
		setBackground(new Color(240, 240, 240));
		this.setVisible(true);
		this.setSize(1000,700);
		this.setLayout(null);
		booking=new Booking();
		formatButtons();
		status="booked";
		JButton btnFixMeeting = new JButton("Fix Meeting");
		btnFixMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String []data1=new String[5];
				data1[0]=(String) table.getValueAt(table.getSelectedRow(), 0);
				data1[1]=(String) table.getValueAt(table.getSelectedRow(), 1);
				data1[2]=(String) table.getValueAt(table.getSelectedRow(), 2);
				data1[3]=(String) table.getValueAt(table.getSelectedRow(), 3);
				data1[4]=(String) table.getValueAt(table.getSelectedRow(), 4);
				uiWindow.setFixMeeting(data1);
			}
		});
		btnFixMeeting.setBounds(490, 580, 101, 30);
		add(btnFixMeeting);

		String [] colNames={"Customer ID","Property ID","Booked On","Status"};
		//this.colNames=colNames;
		JButton btnViewAll = new JButton("View All");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				status="0";
				addRowsTable(status);
			}
		});
		btnViewAll.setToolTipText("View All Booked Properties");
		btnViewAll.setBounds(130, 580, 101, 30);
		add(btnViewAll);
		
		JLabel lblBookingInformationSelect = new JLabel("Booking Information: Select booking to fix Meeting");
		lblBookingInformationSelect.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBookingInformationSelect.setBounds(43, 141, 392, 30);
		add(lblBookingInformationSelect);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 182, 406, 276);
		add(scrollPane);
		model=new DefaultTableModel(booking.allBookings("booked"),colNames);
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setForeground(new Color(255, 255, 255));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(new Color(0, 139, 139));
		scrollPane.setViewportView(table);
		
		JButton btnViewBooked = new JButton("View Booked");
		btnViewBooked.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewBooked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				status="Booked";
				addRowsTable(status);
			}
		});
		btnViewBooked.setToolTipText("View All Booked Properties");
		btnViewBooked.setBounds(236, 580, 101, 30);
		add(btnViewBooked);
		
		JButton btnViewCanceled = new JButton("Canceled");
		btnViewCanceled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				status="Canceled";
				addRowsTable(status);
			}
		});
		btnViewCanceled.setToolTipText("View All Booked Properties");
		btnViewCanceled.setBounds(343, 580, 95, 30);
		add(btnViewCanceled);
		JLabel backwall = new JLabel("");
		backwall.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/panelback.png")));
		backwall.setBounds(0, 100, 900, 450);
		JButton btnBack=new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			uiWindow.switchPanel();
			}
		});
		btnBack.setText("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIcon(new ImageIcon(SearchPanel.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setBounds(35, 580, 90, 30);
		add(btnBack);
		add(backwall);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			viewReport("booking.jrxml","E:\\booking.pdf");
			}
		});
		btnReport.setBounds(601, 580, 101, 30);
		add(btnReport);
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
		catch(Exception e1){
		    JOptionPane.showMessageDialog(null, e1);
		}
	}
	public void addRowsTable(String status)
	{
		try 
		{
			int rows=model.getRowCount();
			for(int i=0;i<rows;i++)
			{
				model.removeRow(0);
			}
			String[][] str=booking.allBookings(status);
			for(int i=0;i<str.length;i++)
			{
				model.addRow(str[i]);	
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	public void formatButtons()
	{
		JLabel lblNewLabel = new JLabel("View Bookings");
		lblNewLabel.setBounds(32, 25, 348, 64);
		add(lblNewLabel);
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewLabel.setIcon(new ImageIcon(BookingPanel.class.getResource("/images/edit-user.png")));

	}
}
