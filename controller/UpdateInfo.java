package controller;

import model.DbUpdateInfo;

public class UpdateInfo
{
	DbUpdateInfo d_obj=new DbUpdateInfo();	
	public boolean updateInfo(String first_name, String last_name, String gender, String maritalStatus,String birthDate, int age, String contact, String type, String user_address, String user_email,String user_password, String path,String currentEmail)
	{
		return d_obj.updateInfo(first_name, last_name, gender, maritalStatus, birthDate, age, contact, type, user_address, user_email, user_password, path,currentEmail);
	}

}
