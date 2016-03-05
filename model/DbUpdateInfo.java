package model;
public class DbUpdateInfo 
{
	DBConnection obj=new DBConnection();
	public boolean updateInfo(String first_name,String last_name, String gender, String maritalStatus,String birthDate,int age ,String contact ,String type,String address,String user_email,String user_password,String path,String currentEmail)
	{
		String Query="UPDATE `pms`.`users` SET `first_name` = \'"+first_name+"\', `last_name` = \'"+last_name+"\', `user_email` = \'"+user_email+"\', `user_password` = \'"+user_password+"\',`user_image` = \'"+path+"\',`user_type` = \'"+type+"\', `gender` = \'"+gender+"\', `birthdate` = \'"+birthDate+"\', `age` = \'"+age+"\', `marital_status` = \'"+maritalStatus+"\', `contact_no` =\'"+contact+"\', `address` = \'"+address+"\' WHERE `users`.`user_email` = \'"+currentEmail+"\';";
		return obj.execute(Query);				
	}
}
