package unittest;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import controller.SearchUserCont;

public class SearchUserContTest
{
	SearchUserCont db_obj=new SearchUserCont();
	@Test
	public void test() throws SQLException
	{
		assertTrue(db_obj.searchUser("karrar@gmail.com", "Talha", "Customer")!=null);
	}

}
