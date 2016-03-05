package controller;
//import java.net.PasswordAuthentication;
import java.util.Properties;



import javax.mail.Authenticator;
//import mail-1.4.7.jar;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Email
{
final String senderEmailID = "Your Email ID For gmail";
final String senderPassword = "password";
final String emailSMTPserver = "smtp.gmail.com";
final String emailServerPort = "465";

String receiverEmailID = null;
String emailSubject = null;
String emailBody = null;

public Email(String receiverEmailID, String emailSubject, String emailBody)
{
this.receiverEmailID=receiverEmailID;
this.emailSubject=emailSubject;
this.emailBody=emailBody;


Properties props = new Properties();
props.put("danashdullu@gmail.com",senderEmailID);
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port", 587);
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.auth", "true");
// props.put("mail.smtp.debug", "true");
//props.put("mail.smtp.socketFactory.port", emailServerPort);
//props.put("mail.smtp.socketFactory.class",
//"javax.net.ssl.SSLSocketFactory");
//props.put("mail.smtp.socketFactory.fallback", "false");

//SecurityManager security = System.getSecurityManager();

try
{
Authenticator auth = new SMTPAuthenticator();
Session session = Session.getInstance(props, auth);
// session.setDebug(true);

MimeMessage msg = new MimeMessage(session);
msg.setText(emailBody);
msg.setSubject(emailSubject);
msg.setFrom(new InternetAddress("nazir.ahmed7010@gmail.com"));
msg.addRecipient(Message.RecipientType.TO,
new InternetAddress(receiverEmailID));
Transport.send(msg);
}
catch (Exception mex)
{
	final JPanel panel = new JPanel();
	JOptionPane.showMessageDialog(panel, "Check your Internet Connection", "Error", JOptionPane.ERROR_MESSAGE);
	//mex.printStackTrace();
}


}
public class SMTPAuthenticator extends javax.mail.Authenticator
{
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication("nazir.ahmed7010@gmail.com", "wanttobahacker");
	}
}


}