package model;

public class DeleteUser 
{
	DBConnection obj=new DBConnection();
	public boolean deleteUser(String email)
	{
		String Query="DELETE FROM `users` WHERE `user_email`=\'"+email+"\'";
		return obj.execute(Query);
	}
}
