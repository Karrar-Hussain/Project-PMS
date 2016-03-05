package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DbUpdatePassword;

public class PasswordControllerTest
{
	DbUpdatePassword db_obj=new DbUpdatePassword();
	@Test
	public void test() 
	{
		assertFalse(db_obj.update_password("talha@gmail.com", "Password123"));
	}

}
