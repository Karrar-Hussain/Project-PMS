package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.ProblemManagerController;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

@SuppressWarnings({ "serial", "unused" })
public class DeleteMaintainessView extends JPanel {
	JLabel txtDwellerId;
	JLabel txtTime;
	JLabel txtProblemNo;
	JLabel txtStatus;
	
//VARIABLES
	private ProblemManagerController objPMC = ProblemManagerController.getInstance();
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DeleteMaintainessView(MainPage objMainFrame,String []arr) {
		setLayout(null);
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setIcon(new ImageIcon(DeleteMaintainessView.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setBounds(30, 580, 90, 30);
		add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setIcon(new ImageIcon(DeleteMaintainessView.class.getResource("/images/update.png")));
		btnUpdate.setBounds(155, 580, 109, 30);
		add(btnUpdate);
		
		JLabel lblDwellerId = new JLabel("Dweller Id");
		lblDwellerId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDwellerId.setBounds(56, 191, 96, 37);
		add(lblDwellerId);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTime.setBounds(56, 230, 96, 37);
		add(lblTime);
		
		JLabel lblProblemNo = new JLabel("Problem No");
		lblProblemNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProblemNo.setBounds(56, 278, 96, 37);
		add(lblProblemNo);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(56, 326, 85, 37);
		add(lblStatus);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDesc.setBounds(346, 157, 200, 30);
		add(lblDesc);
		
		txtDwellerId = new JLabel("New label");
		txtDwellerId.setBounds(191, 191, 135, 37);
		add(txtDwellerId);
		
		txtTime = new JLabel("New label");
		txtTime.setBounds(191, 230, 135, 37);
		add(txtTime);
		
		txtProblemNo = new JLabel("New label");
		txtProblemNo.setBounds(191, 278, 135, 37);
		add(txtProblemNo);
		
		txtStatus = new JLabel("New label");
		txtStatus.setBounds(191, 326, 135, 37);
		add(txtStatus);
		
		DefaultListModel model = new DefaultListModel();
		JList lstDesc = new JList(model);
		lstDesc.setForeground(SystemColor.text);
		lstDesc.setBackground(new Color(0, 139, 139));
		lstDesc.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
		lstDesc.setBounds(0, 0, 365, 281);
		//add(lstDesc);
		JScrollPane scrollPane = new JScrollPane(lstDesc);
		scrollPane.setBounds(346, 198, 365, 281);
		add(scrollPane);
//btnBACK ACTIONLISTENER
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				objMainFrame.MaintainessPanel( );
			}
		});
//btnDELETE ACTIONLISTENER
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String []data=new String[4];
				data[0]="application";
				data[1]=" Status='Solved' ";
				data[2]="Time";
				data[3]=arr[1];
				objPMC.UpdateStatus(data);
				lblStatus.setText("Solved");
				objMainFrame.MaintainessPanel();
			}
		});
		
//CODING
		txtDwellerId.setText(arr[0]);
		System.out.println("Dweler Id : "+txtDwellerId.getText().toString());
		model.addElement(arr[4]);
		txtTime.setText(arr[1]);
		txtProblemNo.setText(arr[2]);
		txtStatus.setText(arr[3]);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(DeleteMaintainessView.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 450);
		add(lblImage);
		
		JLabel lblNewLabel = new JLabel("Update Status ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewLabel.setBounds(33, 30, 231, 48);
		add(lblNewLabel);
		
		
		
		
	}
	public void delete(MainPage obj,String []arr)
	{
		obj.DeleteMaintainessPanel(arr);
		
	}

}
