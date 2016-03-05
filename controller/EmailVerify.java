package controller;

import model.DbVerifyEmail;

public class EmailVerify
{
	DbVerifyEmail db_obj=new DbVerifyEmail();
	public boolean user_email_verifi(String user_email)
	{
		return db_obj.user_email_verifi(user_email);
	}
}
