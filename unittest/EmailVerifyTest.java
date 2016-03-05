package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DbVerifyEmail;

public class EmailVerifyTest
{
	DbVerifyEmail db_obj=new DbVerifyEmail();
	@Test
	public void test()
	{
		assertTrue(db_obj.user_email_verifi("karrar@gmail.com"));
	}

}
