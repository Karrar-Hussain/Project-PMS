package model;
public class DbSignUp 
{
	DBConnection obj=new DBConnection();
	public boolean signup_check(String first_name,String last_name, String gender, String maritalStatus,String birthDate,int age ,String contact ,String type,String address,String user_email,String user_password,String path)
	{
		String Query="INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `user_email`, `user_password`, `user_image`, `user_type`, `gender`, `birthdate`, `age`, `marital_status`, `contact_no`, `address`) VALUES (NULL, \'"+first_name+"\', \'"+last_name+"\', \'"+user_email+"\', \'"+user_password+"\', \'"+path+"\', \'"+type+"\', \'"+gender+"\', \'"+birthDate+"\', \'"+age+"\', \'"+maritalStatus+"\', \'"+contact+"\', \'"+address+"\');";
		return obj.execute(Query);				
	}

}
