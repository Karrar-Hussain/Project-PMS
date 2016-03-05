package model;

import java.sql.ResultSet;

public class DbLogIn 
{
	DBConnection obj=new DBConnection();
	public ResultSet login_check(String email, String password )
	{
		
		String Query="SELECT * FROM `users` WHERE `user_email` = \'"+email+"\' AND `user_password` = \'"+password+"\'";
		ResultSet re=obj.select(Query);
		return re;
	}

}
