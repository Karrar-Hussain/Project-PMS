package controller;

import model.DbUpdatePassword;

public class PasswordController
{
	
	DbUpdatePassword db_obj=new DbUpdatePassword();
	
	public boolean update_password(String customer_email,String customer_password)
	{
		return db_obj.update_password(customer_email, customer_password);
	}

}
