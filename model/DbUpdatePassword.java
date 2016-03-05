package model;

public class DbUpdatePassword
{
	DBConnection obj=new  DBConnection();
	public boolean update_password(String user_email,String user_password)
	{
		String Query="UPDATE `pms`.`users` SET `user_password` = \'"+user_password+"\' WHERE `users`.`user_email` = \'"+user_email+"\'";
		return obj.execute(Query);
	}
}
