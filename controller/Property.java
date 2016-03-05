package controller;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBaseQuery;
public class Property 
{
protected int propertyid;
protected String location;
protected String type;
protected String prevstate;
protected String  status ;
protected int area;
protected ResultSet rs;
protected DataBaseQuery dbq;
protected static String image;

public Property()
{
	dbq=new DataBaseQuery();
}

@SuppressWarnings("static-access")
public void setImage(String image) 
{
	this.image=image;
}
public void setPropertyId(int propertyid)
{
	this.propertyid=propertyid;
}
public void setPreviousState(String prevstate)
{
	this.prevstate=prevstate;
}

public void setType(String type)
{
	this.type=type;
}
public void setStatus(String status)
{
	this.status=status;
}

public void setLocation(String location)
{
	this.location=location;
}
public void setArea(int area)
{
	this.area=area;
}

public ResultSet oneProperty(String propertyid) {
	
	// TODO Auto-generated method stub
	return dbq.oneProperty(propertyid);
}
public String[] getAllProperty() throws SQLException {
	// TODO Auto-generated method stub
	return dbq.getAllProperty();
}
/** insertNewProperty() is function which class insertNewProperty of DBclass where querys are executed
 * 
 * @return boolean true is inserted else false 
 */
public boolean insertNewProperty()
{
	try
	{	
	return dbq.insertNewProperty(type, status, area, location,image);
	}
	catch(Exception ex)
	{
	ex.getMessage();
	return true;
	}
}

}
