package model;
import com.mysql.jdbc.ResultSet;
public class DbSearchUser
{
	private DBConnection obj;
	public DbSearchUser()
	{
	obj=new DBConnection();	
	}
	public ResultSet searchByName(String name)
	{
		String Query;
		if(name!="null")
		Query="SELECT * FROM `users` WHERE `first_name` LIKE \'%"+name+"%\'";
		else
			Query="Select * From `users`";
		return (ResultSet) obj.select(Query);
		}
	public ResultSet searchByEmail(String email)
	{
		String Query;
		if(email!="null")
		Query="SELECT * FROM `users` WHERE `user_email` LIKE \'%"+email+"%\'";
		else
			Query="Select * From `users`";
		return (ResultSet) obj.select(Query);
	}
	public ResultSet searchByType(String type)
	{
		String Query;
		if(type!="null")
		Query="SELECT * FROM `users` WHERE `user_type` LIKE \'%"+type+"%\'";
		else
			Query="Select * From `users`";
		return (ResultSet) obj.select(Query);
	}
}
