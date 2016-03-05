package unittest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.Db_Connection;

import org.junit.Test;

public class ReportProblemControllerTest {
	Db_Connection _sqlquery=new Db_Connection();
	
	@Test
	public void testSaveToDatabase() 
	{
		String []arr=new String [7];
		int len=arr.length;
		arr[0]="application";
		arr[1]="Dweller_id";
		arr[2]="Application_Type";
		arr[3]="Description";
		arr[4]="Time";
		arr[5]="ProblemNo";
		arr[6]="Status";
		String sql ="INSERT INTO "+""+arr[0]+""
			     + " VALUES (";
			for(int i=1;i<len;i++)
			{
				  sql+="'"+arr[i]+"'";
				  if(i<len-1)
					  sql+=",";
			}
				       sql+=")";
				       try 
						{
							   assertEquals(true,_sqlquery.ExecuteStmt(sql));
								
						}
						catch (SQLException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
		//fail("Not yet implemented");
	}

}
