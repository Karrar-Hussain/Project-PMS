package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUserAccountInfo 
{
	DBConnection obj=new DBConnection();
	public ResultSet userAcconutInfo(String customer_email)
	{
		String Query="SELECT * FROM `users` WHERE `user_email` = \'"+customer_email+"\'";
		ResultSet re=obj.select(Query);
		try {
			re.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(customer_email);
		return re;
		
	}
}
