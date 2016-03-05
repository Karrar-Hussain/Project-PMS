package controller;

import model.DbSignUp;

public class UsersController 
{
	DbSignUp db_signup=new DbSignUp();
	
	
	public boolean signup_check(String fname,String lname, String gen,String  mar,String Date,int ag,String con,String typ,String add,String email,String password,String pth)
	{
		return db_signup.signup_check(fname, lname,gen,mar,Date,ag,con,typ ,add,email, password,pth);
	}
}
