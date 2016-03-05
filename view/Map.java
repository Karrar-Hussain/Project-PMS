package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

@SuppressWarnings("serial")
public class Map extends JPanel 
{

	/**
	 * Create the panel.
	 * @param mainPage 
	 */
	public Map(MainPage mainPage)
	{
		System.setProperty("teamdev.license.info", "true");
        final Browser browser = new Browser();
        setLayout(null);
        //this.add(toolBar, BorderLayout.SOUTH);
        //this.setSize(900, 660);
        browser.loadURL("https://www.google.com/maps/place/Public+House/@40.7503919,-73.9783797,17z/data=!3m1!4b1!4m2!3m1!1s0x89c25903c30d7a19:0xaea428e288ea7098");
        //      this.setLocation(null);
              
              JButton lblBack = new JButton("Back");
              lblBack.setHorizontalAlignment(SwingConstants.LEFT);
              lblBack.setFont(new Font("Tahoma", Font.BOLD, 11));
              lblBack.setBackground(new Color(30, 144, 255));
              //lblBack.setBounds(0, 0, 48, 48);
              
              add(lblBack);
              lblBack.addMouseListener(new MouseAdapter() {
              	@Override
              	public void mousePressed(MouseEvent arg0)
              	{
              		mainPage.setSize(900,660);
					mainPage.setLocation(100,50);
					mainPage.switchPanel();
              	}
              });
              lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              lblBack.setToolTipText("Go to back");
              lblBack.setIcon(new ImageIcon(Map.class.getResource("/images/Bracket_Left.png")));
              
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      		int width = (int) screenSize.getWidth();
      		int height = (int) screenSize.getHeight();
              
      		lblBack.setBounds(width-140, 14, 120, 34);
      		
        BrowserView browserView = new BrowserView(browser);
        browserView.setBounds(0, 0, width, height-100);
        add(browserView);
        this.setVisible(true);
        //"http://maps.google.com"
        // Provide the correct full path to the map.html file, e.g. D:\\map.html
        
	}
}
