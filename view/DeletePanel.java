package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.DataBaseQuery;

@SuppressWarnings("serial")
public class DeletePanel extends JPanel{
	private JTextField txtPropertyId;
	public DeletePanel(MainPage uiWindow)
	{
		super();
		setBackground(new Color(240, 240, 240));
		this.setVisible(true);
		this.setSize(1000,700);
		this.setLayout(null);
		txtPropertyId = new JTextField();
		txtPropertyId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					DataBaseQuery dbq=new DataBaseQuery();
				String propertyid=txtPropertyId.getText();
				if(propertyid.matches("[0-9]*")&&!txtPropertyId.getText().isEmpty())
				{
				if(!dbq.isProperty(Integer.parseInt(propertyid)))
				{
					JOptionPane.showMessageDialog(null, "This Property Does Not Exist");
				}
				}else
					JOptionPane.showMessageDialog(null, "Error property id should be valid integer","ERROR",JOptionPane.ERROR_MESSAGE);;
				//else
					//JOptionPane.showMessageDialog(parentComponent, message);
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		txtPropertyId.setBounds(135, 126, 96, 20);
		add(txtPropertyId);
		txtPropertyId.setColumns(10);
		
		formatButtons();


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
		JLabel backwall = new JLabel("");
		backwall.setIcon(new ImageIcon(AddPanel.class.getResource("/pmsicons/panelback.png")));
		backwall.setBounds(0, 100, 900, 450);
		add(backwall);
	}
	public void formatButtons()
	{
		JLabel lblId = new JLabel("Property ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(32, 129, 86, 14);
		add(lblId);
		
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon(DeletePanel.class.getResource("/pmsicons/1446489767_free-27.png")));
		label.setBounds(311, 263, 256, 220);
		add(label);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(txtPropertyId.getText().matches("[0-9]*") && !txtPropertyId.getText().equals(""))
			{
				
				try {
					
					DataBaseQuery dbq=new DataBaseQuery();
					int propertyid=Integer.parseInt(txtPropertyId.getText().toString());
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?","Confirmation For Delete",
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.WARNING_MESSAGE)==0)
					{
						if(!dbq.isProperty(propertyid))
						{
							JOptionPane.showMessageDialog(null, "This Property Does Not Exist");
						}
						else
							if(dbq.deleteProperty(propertyid))
							JOptionPane.showMessageDialog(null, "Property Deleted Successfully!");
						}
					}
					catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			else
				JOptionPane.showMessageDialog(null, "Enter Valid Integer Value For Property ID","ERROR MSG",JOptionPane.ERROR_MESSAGE);
			}
		});
		btnDelete.setIcon(new ImageIcon(DeletePanel.class.getResource("/pmsicons/1446489787_free-27.png")));
		btnDelete.setBounds(135, 580,110, 31);
		add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Delete Property");
		lblNewLabel.setBounds(32, 35, 332, 38);
		add(lblNewLabel);
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 28));
		lblNewLabel.setIcon(new ImageIcon(DeletePanel.class.getResource("/pmsicons/1446489877_Streamline-70.png")));

	}
}
