package controller;

import java.sql.SQLException;

import model.Db_Connection;

public class ReportProblemController {
	private static ReportProblemController obj;
	private static int problem_no; 
    private String problem_type; 
	private String time; 
	private String problem_description ;	
	private String status ;	
	private String Dweller_Id;
	private String Condition;
	
	// Singleton Pattern
	
	public static ReportProblemController getInstance()
	{
		if(obj==null)
		{
			obj=new ReportProblemController();
		    return obj;
		}
		else return obj;
	}
	
	// Constructor
public ReportProblemController()
{
	
}
public void Report_Problem()
{
}
public void setDwellerId(String Dw)
{
	this.Dweller_Id=Dw;
}
public void setCondition(String cn)
{
	this.Condition=cn;
}
public void setStatus(String status)
{
	this.status=status;
}
public void setProblemNo(int problem_no)
{
	this.problem_no=problem_no;
}
public void setProblemType(String problem_type)
{
	this.problem_type=problem_type;
}
public void setProblemDescription(String problem_description)
{
	this.problem_description=problem_description;
}
public void setTime(String time)
{
	this.time=time;
}

//    Saving  in Database

public void SaveToDatabase()
{
String []arr=new String[8];
arr[0]="application";
arr[1]=this.Dweller_Id;
arr[2]=this.problem_type;
arr[3]=this.problem_description;
arr[4]=this.time;
arr[5]="0";
arr[6]=this.status;
arr[7]=this.Condition;
int len= arr.length;
// Execute a query
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
Db_Connection db=new Db_Connection();

//Calling the Insert Function

try {
	db.ExecuteStmt(sql);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}
//end SaveToDatabase

// Print
public void print()
{
System.out.println("Values : "+"  "+this.problem_no+"   "+this.time+"  "+this.problem_type+"  "+this.problem_description+"  ");	
}
}
