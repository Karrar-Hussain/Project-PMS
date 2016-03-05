package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DbLogIn;

public class LogInControllerTest 
{

	DbLogIn db_login=new DbLogIn();
	@Test
	public void test()
	{
		assertNotNull(db_login.login_check("karrar@gmail.com", "Chmod123"));
		//fail("Not yet implemented");
	}

}
