package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import model.Db_Connection;

import org.junit.Test;

public class ProblemManagerControllerTest {
	Db_Connection _sqlquery=new Db_Connection();
	@Test
	public void testUpdateStatus() {
		String arr=" ";
		arr="UPDATE application SET Status='Solved' WHERE Time='";
		arr+="11/26/2015 6:08:38 PM";
		arr+="'";
		
		try 
		{
			   assertEquals(true,_sqlquery.ExecuteStmt(arr));
				
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Test
	public void testDELETE() {
		String sql ="DELETE  FROM application"+""+" WHERE Time='";
	      sql+="11/26/2015 6:08:27 PM'";
	      try 
			{
				   assertEquals(true,_sqlquery.ExecuteStmt(sql));
					
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Test
	public void testSELECTEDDATA() {

		String []arr=new String [2];
		arr[0]="'11/26/2015 6:08:27 PM'";
		arr[1]="11/26/2015 6:08:27 PM";
		String sql="SELECT * FROM application WHERE "+arr[0]+"="+"'"+arr[1]+"'";
		try 
		{
			assertNotNull(_sqlquery.GetResultSet(sql));
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testAddToList() {
		try 
		{
			assertNotNull(_sqlquery.View("application"));
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented");
	}

}
