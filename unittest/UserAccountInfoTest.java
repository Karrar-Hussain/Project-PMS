package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DbUserAccountInfo;

public class UserAccountInfoTest
{
	DbUserAccountInfo db_obj=new DbUserAccountInfo();
	@Test
	public void test()
	{
		assertNotNull(db_obj.userAcconutInfo("karrar@gmail.com"));
	}

}
