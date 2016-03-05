//package MaintainessTeam;
package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.ReportProblemController;
import javax.swing.JScrollPane;
@SuppressWarnings("serial")
public class ReportProblemView extends JPanel {

		//public	MainFrame _mainframe;

	private static int ProblemNo=0;
	private String[] _items = { "Emergency", "Plumber", "Electrition","Others"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public ReportProblemView(MainPage mainPage) 
	{
		setForeground(new Color(0, 0, 0));
		setBackground(SystemColor.menu);
		setLayout(null);
		///this._mainframe=_mainframe;
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setForeground(SystemColor.inactiveCaptionText);
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 13));
		btnSubmit.setIcon(new ImageIcon(ReportProblemView.class.getResource("/pmsicons/1446290275_Check.png")));
		btnSubmit.setBounds(544, 580, 117, 30);
		add(btnSubmit);
		
		JLabel Problem_image = new JLabel("Report Problem");
		Problem_image.setForeground(Color.WHITE);
		Problem_image.setFont(new Font("Times New Roman", Font.BOLD, 28));
		//Problem_image.setIcon(new ImageIcon(ReportProblemPage.class.getResource("/images/problem2.png")));
		Problem_image.setBounds(10, 11, 472, 78);
		add(Problem_image);
		
		//DROP DOWN LIST
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Problem Type");
		comboBox.setBounds(346, 168, 136, 26);
		add(comboBox);
		for(int i=0;i<4;i++)
			comboBox.addItem(_items[i]);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(100, 149, 237), new Color(30, 144, 255), new Color(100, 149, 237)));
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Monospaced", Font.ITALIC, 15));
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(new Color(0, 139, 139));
		textArea.setBounds(338, 228, 323, 292);
		//add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(338, 228, 323, 292);
		add(scrollPane);
		// PRESS the Submit Button
		
				btnSubmit.addActionListener( new ActionListener()
				{
				    public void actionPerformed(ActionEvent e)
				    {
				    	int DwellerId=mainPage.getUserId();
				    	//Reset_Panel();
				    	ProblemNo++;
				    	String type=(String) comboBox.getSelectedItem();;
				    	String desc=textArea.getText();
				    	Date date = new Date();
				    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
				    	String time = sdf.format(date);
				    	ReportProblemController objP=ReportProblemController.getInstance();
				    	objP.setDwellerId((Integer.toString(DwellerId)));
				    	objP.setProblemNo(ProblemNo);
				    	objP.setProblemType(type);
				    	objP.setTime(time);
				    	objP.setProblemDescription(desc);
				    	objP.setStatus("Unsolved");;
				    	objP.setCondition("Unseen");
				    	objP.print();
				    	objP.SaveToDatabase();
				    	mainPage.setDewellerPage();
				    }
				});
				
				JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setIcon(null);
		lblDescription.setBounds(208, 225, 128, 32);
		add(lblDescription);
		
		JLabel lblNewLabel = new JLabel("Problem Type:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(208, 163, 128, 32);
		add(lblNewLabel);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(ReportProblemView.class.getResource("/images/newBackground.png")));
		lblImage.setBounds(0, 100, 900, 460);
		add(lblImage);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPage.setDewellerPage();
			}
		});
		btnBack.setIcon(new ImageIcon(ReportProblemView.class.getResource("/pmsicons/1446334955_back.png")));
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBounds(30, 580, 90, 30);
		add(btnBack);
		
		
	}
}
