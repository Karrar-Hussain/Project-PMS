package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbVerifyEmail
{
	DBConnection obj=new DBConnection();
	public boolean user_email_verifi(String customer_email)
	{
		String Query="SELECT * FROM `users` WHERE `user_email` = \'"+customer_email+"\'";
		ResultSet re=obj.select(Query);
		
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
}
