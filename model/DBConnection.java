package model;


//STEP 1. Import required packages
import java.sql.*;

import javax.swing.JOptionPane;
//db_connection db=new db_connection();
public class	DBConnection 
{
	Connection conn = null;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/pmsdb";
	private static Connection con;
	// Database credentials
	static final String USER = "root";
	static final String PASS = "12345";

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(conn==null)
			{
				return null;
			}
			else
			System.out.println("Success!");
		} catch (Exception e) 
		{
			System.out.println("There is Connection Problem check database connection!");
		}
		return conn;
	}
	public ResultSet select(String query) 
	{
		ResultSet rs = null;
		try {
			con = getConnection();
			if(con!=null)
			{
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error in Connection With Database","Error",JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in processing Query!","Connection ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return rs;
	}

	public boolean execute(String query) 
	{
		Statement stmt = null;
		conn = getConnection();
		try 
		{
			if(conn!=null)
			{
				stmt = conn.createStatement();
				return stmt.execute(query);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error in Connection to DataBase","Error",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "There is an Error Processing Query!","Connection ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return false;

	}

	public boolean login_check(String email, String password )
	{
		
		String Query="SELECT * FROM `Customer` WHERE `customer_email` = \'"+email+"\' AND `customer_password` = \'"+password+"\'";
		ResultSet re=select(Query);
		
		String id="";
			try {
				while(re.next())
					{
					 id=re.getString("first_name");	
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (id.isEmpty())
		{
			return false;
			
		}
		else
			return true;
	}
	
	public boolean signup_check(String first_name,String last_name, String customer_email,String customer_password)
	{

		String Query="INSERT INTO `pms`.`Customer` (`customer_id`, `first_name`, `last_name`, `customer_email`, `customer_password`) VALUES (NULL, \'"+first_name+"\', \'"+last_name+"\', \'"+customer_email+"\', \'"+customer_password+"\')";
		return execute(Query);
	}
	
	public boolean customer_email_verifi(String customer_email)
	{
		String Query="SELECT * FROM `customer` WHERE `customer_email` = \'"+customer_email+"\'";
		ResultSet re=select(Query);
		
		String id="";
			try
			{
				while(re.next())
					{
					 id=re.getString("first_name");	
					}
		    }
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (id.isEmpty())
		{
			return false;
			
		}
		else
			return true;
	}	
	public boolean update_password(String customer_email,String customer_password)
	{
		String Query="UPDATE `pms`.`customer` SET `customer_password` = \'"+customer_password+"\' WHERE `customer`.`customer_email` = \'"+customer_email+"\'";
		return execute(Query);
	}
}
	
