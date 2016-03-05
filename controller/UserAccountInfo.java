package controller;

import java.sql.ResultSet;

import model.DbUserAccountInfo;

public class UserAccountInfo
{
	DbUserAccountInfo db_obj=new DbUserAccountInfo();
	public ResultSet userAcconutInfo(String customer_email)
	{
		return db_obj.userAcconutInfo(customer_email);
	}
}
