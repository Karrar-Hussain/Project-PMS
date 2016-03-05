package view;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;

import controller.ProblemManagerController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.Rectangle;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

@SuppressWarnings({ "serial", "unused" })
public class DeleteManagerView extends JPanel {
	JLabel txtDwellerId;
	JLabel txtTime;
	JLabel txtProblemNo;
	JLabel txtStatus;
	
//VARIABLES
	private ProblemManagerController objPMC = ProblemManagerController.getInstance();
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeleteManagerView(MainPage objMainFrame,String[]arr) {
		setBounds(new Rectangle(0, 0, 900, 660));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setIcon(new ImageIcon(DeleteManagerView.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setBounds(30, 580, 90, 30);
		add(btnBack);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setIcon(new ImageIcon(DeleteManagerView.class.getResource("/images/delete.png")));
		btnDelete.setBounds(155, 580, 111, 30);
		add(btnDelete);
		
		JLabel lblDwellerId = new JLabel("Dweller Id");
		lblDwellerId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDwellerId.setBounds(58, 148, 96, 37);
		add(lblDwellerId);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTime.setBounds(58, 196, 96, 37);
		add(lblTime);
		
		JLabel lblProblemNo = new JLabel("Problem No");
		lblProblemNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProblemNo.setBounds(58, 244, 96, 37);
		add(lblProblemNo);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(58, 292, 85, 37);
		add(lblStatus);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDesc.setBounds(404, 123, 200, 37);
		add(lblDesc);
		
		txtDwellerId = new JLabel("New label");
		txtDwellerId.setBounds(193, 148, 135, 37);
		add(txtDwellerId);
		
		txtTime = new JLabel("New label");
		txtTime.setBounds(193, 196, 135, 37);
		add(txtTime);
		
		txtProblemNo = new JLabel("New label");
		txtProblemNo.setBounds(193, 244, 135, 37);
		add(txtProblemNo);
		
		txtStatus = new JLabel("New label");
		txtStatus.setBounds(193, 292, 135, 37);
		add(txtStatus);
		
		DefaultListModel model = new DefaultListModel();
		
		JLabel lblDeleteProblem = new JLabel("Delete Problem");
		lblDeleteProblem.setForeground(Color.WHITE);
		lblDeleteProblem.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblDeleteProblem.setBounds(33, 11, 365, 78);
		add(lblDeleteProblem);
		JList lstDesc = new JList(model);
		lstDesc.setForeground(SystemColor.text);
		lstDesc.setBackground(new Color(0, 139, 139));
		lstDesc.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
		lstDesc.setBounds(456, 186, 365, 281);
		//add(lstDesc);
		JScrollPane scrollPane = new JScrollPane(lstDesc);
		scrollPane.setBounds(403, 170, 365, 277);
		add(scrollPane);
//btnBACK ACTIONLISTENER
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				objMainFrame.setManagerPane();
			}
		});
//btnDELETE ACTIONLISTENER
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String []sql=new String[2];
				sql[0] = "application";
				sql[1] = "Time=";
				sql[1] += "'";
				sql[1] +=arr[1];
				sql[1] += "'";
				objPMC.DELETE(sql);
				objMainFrame.setManagerPane();
			}
		});
		
//CODING
		txtDwellerId.setText(arr[0]);
		for(int i=0;i<5;i++)
		System.out.println("arr[4]:  "+arr[i]);
		model.addElement(arr[4]);
		txtTime.setText(arr[1]);
		txtProblemNo.setText(arr[2]);
		txtStatus.setText(arr[3]);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(DeleteManagerView.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		
		
		
	}
	public void delete(MainPage obj,String []arr)
	{
		obj.setP4(arr);
		
	}
}
