package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db_Connection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/pmsdb";

//  Database credentials
    static final String USER = "root";
    static final String PASS = "12345";

//Insert Function

    	 public Connection Open_Connection() {
    		 Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	        	 conn.close();
		      }catch(SQLException se){
	      }
	      }//end try
	return conn;
	}//end main			   

    	   // View Class
		   
		   public ResultSet View(String arr1) throws SQLException 
		   {
			   Connection conn = Open_Connection();
			   Statement stmt = null;
			   stmt = conn.createStatement();
			      String sql ="SELECT * FROM "+""+arr1;
			     ResultSet rs = stmt.executeQuery(sql);
			  return rs;
			}
		   //////////
			
		   public ResultSet GetResultSet(String sql)throws SQLException 
		   {
			   Connection conn = Open_Connection();
			   Statement stmt = null;
			   stmt = conn.createStatement();
		       ResultSet rs = stmt.executeQuery(sql);
			   return rs;	
		   }
		// Execute Stmt FUNCTION
		   public boolean ExecuteStmt(String arr1) throws SQLException 
		   {
			   Connection conn = Open_Connection();
			   Statement stmt = null;
			      stmt = conn.createStatement();
			      stmt.executeUpdate(arr1);
				   conn.close();
			return true;
		   }
		   
}
		
