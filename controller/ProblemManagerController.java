package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Db_Connection;

public class ProblemManagerController {
	Db_Connection _sqlquery=new Db_Connection();
	ReportProblemController objProblem;
	private static ProblemManagerController obj;
	// Singleton Pattern
	
		public static ProblemManagerController getInstance()
		{
			if(obj==null)
			{
				obj=new ProblemManagerController();
			    return obj;
			}
			else return obj;
		}
		
	public void Insert(String []arr)
	{
		int len=arr.length;
		String sql ="INSERT INTO "+""+arr[0]+""
			     + " VALUES (";
			for(int i=1;i<len;i++)
			{
				  sql+="'"+arr[i]+"'";
				  if(i<len-1)
					  sql+=",";
			}
				       sql+=")";
			System.out.println("sql :"+sql);
           try {
			    _sqlquery.ExecuteStmt(sql);
		       } 
           catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateStatus(String []arr)
	{
		String sql=" ";
		sql="UPDATE "+arr[0]+ " SET " +arr[1];
		sql+="WHERE `"+arr[2]+"`='";
		sql+=arr[3];
		sql+="'";
		System.out.println("sql :"+sql);
		try {
			_sqlquery.ExecuteStmt(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean DELETE(String[] arr )
	{
		String sql ="DELETE  FROM "+""+arr[0]+""+" WHERE ";
	      sql+=arr[1];
	      try {
			_sqlquery.ExecuteStmt(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return true; 
		
	}
	public ResultSet SELECTEDDATA(String []arr)
	{
		ResultSet rs;
		String sql="";
		sql="SELECT * FROM "+arr[0]+" WHERE `"+arr[1]+"`="+"'"+arr[2]+"'";
		//if(arr[0].equals("application"))
		//sql+=" ORDER BY Time DESC";
		System.out.println("SELECTEDDATA : "+sql);
		try {
			rs=_sqlquery.GetResultSet(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/*public ResultSet AddToList()
	{
			try {
				ResultSet	rs = _sqlquery.View("application");
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }
	*/
}
