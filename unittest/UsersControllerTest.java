package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DbSignUp;

public class UsersControllerTest
{
	DbSignUp db_signup=new DbSignUp();
	@Test
	public void test() 
	{
		assertFalse(db_signup.signup_check("Danish", "Umar", "M", "N", "1992-12-18", 21, "01232138728", "Manager", "Hostel", "basiq123@gmail.com", "Chmod123", "Profiles#Slash#karrar.jpg"));
	}

}
