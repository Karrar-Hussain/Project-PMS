package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommercialProperty extends Property{
	private int price;
	private boolean sold;
	private ResultSet rs;
	public void isSold(boolean sold)
	{
		this.sold=sold;
	}
	public void setPrice(int price)
	{
		this.price=price;
	}
	public void setCurrentState(boolean sold)
	{
		this.sold=sold;
	}

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
	public boolean insertCommercial() throws SQLException 
	{
		propertyid=0;
		dbq.setType(type);
		dbq.setStatus(status);
		dbq.setCurrentState(sold);
		dbq.setPrice(price);
		rs=dbq.allProperties();
		while(rs.next())
		{
			propertyid=rs.getInt("propertyid");
		}
		if(propertyid!=0)
		{
			return dbq.insertCommercial(propertyid);
		}
		return true;
	}
	public ResultSet oneCommercial(int propertyid) throws SQLException
	{
			if(status.equals("ForSale"))
			{
				rs=dbq.oneCommercialForSale(propertyid);
			}
			else
			{
				rs=dbq.oneCommercialRental(propertyid);
			}
			return rs;
	}
	public boolean updateProperty() 
	{
		dbq.setArea(area);
		dbq.setPrice(price);
		dbq.setCurrentState(sold);
		dbq.setStatus(status);
		dbq.setLocation(location);
		if(!dbq.updateProperty(propertyid))
		{
		if(!status.equals(prevstate))
		{
			return true;
		}
		else if(type.equals("Commercial")&&status.equals("ForSale"))
		{
			return dbq.updateCFProperty(propertyid, 0);	
		}
		else if(type.equals("Commercial")&&status.equals("ForRent"))
			{
				return dbq.updateCRProperty(propertyid, 0);	
			}
		}
		return true;
	}
}
