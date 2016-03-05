package controller;

//import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

//import org.junit.Test;
//import static org.junit.Assert.*;

//import org.junit.Test;
import model.DbLogIn;

public class LogInController
{
	DbLogIn db_login=new DbLogIn();
	public ResultSet login_check(String email,String password )
	{
	
		
		return db_login.login_check(email, password);	
	}
//	@Test
//	public void test(String email,String password)
//	{
//		assertEquals("karrar@gmail.com",email);
//		//fail("Not yet implemented");
//	}
	
}
