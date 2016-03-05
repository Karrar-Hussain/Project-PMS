package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SqlQueriesClass {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/dani";

//  Database credentials
    static final String USER = "root";
    static final String PASS = "";

//Insert Function

    	 public Connection Open_Connection() {
    		 
    		 Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	  //    System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	//      System.out.println("Connected database successfully...");
	      
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
	   //System.out.println("Goodbye!");
	return conn;
	}//end main			   
//Insert Function
		   
		   public void Insert(String[]arr1) throws SQLException 
		   {
			   System.out.println("Inserting records into the table Function...");
			   //BuildingConnections obj=new BuildingConnections();
			   Connection conn = Open_Connection();
			   Statement stmt = null;
			  int len= arr1.length;
			      // Execute a query
			      stmt = conn.createStatement();
			      
			      // Inserting Data Into Table
			      String sql ="INSERT INTO "+""+arr1[0]+""
                       + " VALUES (";
			      
			      for(int i=1;i<len;i++)
			      {
			    	  sql+="'"+arr1[i]+"'";
			    	  if(i<len-1)
			    		  sql+=",";
			            
			      }
			    	       sql+=")";
		                   System.out.println("2222222:  "+sql);
		 			       stmt.executeUpdate(sql);
			   conn.close();
			 }			   
		//////////////
		   
		   // View Class
		   
		   public ResultSet View(String arr1) throws SQLException 
		   {
			   System.out.println("VIEWING records FROM the table Function...");
			   Connection conn = Open_Connection();
			   Statement stmt = null;
			   
			   stmt = conn.createStatement();
			      
			      // Inserting Data Into Table
			      String sql ="SELECT * FROM "+""+arr1;
			      System.out.println("11111111:  "+sql);
			      
			     ResultSet rs = stmt.executeQuery(sql);
			      
			   // obj.Close_Connection(conn);
					     
			   System.out.println("Goodbye!");
			   return rs;
				
		   }
		   //////////
			
		   public ResultSet Getrs(ResultSet rs)
		   {
			   
			 return rs;	
		   }
		   // DELETE FUNCTION
		   public void Delete(String[] arr1) throws SQLException 
		   {
			   System.out.println("DELETING records from the table Function...");
			   Connection conn = Open_Connection();
			   Statement stmt = null;
			      // Execute a query
			      stmt = conn.createStatement();
			      
			      String sql ="DELETE  FROM "+""+arr1[0]+""+" WHERE ";
			      sql+=arr1[1];
			      System.out.println("11111111:  "+sql);
			      
			      stmt.executeUpdate(sql);
			      
			   System.out.println("Goodbye!");
			   conn.close();
		   }
		   //////////
			
		   
}
		
