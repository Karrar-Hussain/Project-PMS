package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingDB {
	private DBConnection db;
	public BookingDB()
	{
		db=new DBConnection();
	}
	public boolean insertBooking(int cust_id, int propertyid) {
		// TODO Auto-generated method stub
		Date date = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
		c.add(Calendar.DATE, 7);
		String d2=sdf.format(c.getTime());
		return db.execute("INSERT INTO `booking` (`userid`,`propertyid`,`bookedOn`, `bookedTill`) VALUES ("+cust_id+","+propertyid+",\""+ft.format(date)+"\",\""+d2+"\" )");
	}
	public ResultSet getBookedProperty(int cust_id) 
	{
		return db.select("SELECT * FROM `booking` WHERE `userid` = "+cust_id+"");
	}

	public ResultSet getBookingProperty(int propertyid) 
	{
		return db.select("SELECT * FROM `booking` WHERE `propertyid` = "+propertyid+"");
	}
	
	public ResultSet allBookings(String status)
	{
		if(status.equals("0"))
		return db.select("SELECT * FROM `booking` ORDER BY `bookedon`");
		else
			return db.select("SELECT * FROM `booking` WHERE `status` LIKE \""+status+"\" ORDER BY `bookedon`");
	}
	public ResultSet getBooking(int bookingid)
	{
		return db.select("SELECT * FROM `booking` WHERE `bookingid` = "+bookingid+"");
	}
	public boolean updateBookedProperty(int bookingId, int propertyId)
	{
		// TODO Auto-generated method stub
		//System.out.println("bookingId :"+bookingId+" pid: "+propertyId);
	    Date date = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
		c.add(Calendar.DATE, 7);
		String d2=sdf.format(c.getTime());
		boolean bol=db.execute("UPDATE `booking` SET `propertyid`= "+propertyId+",`bookedOn` = \""+ft.format(date)+"\" ,`bookedTill` = \""+d2+"\" WHERE `bookingid` = "+bookingId+"");
		if(bol==true)
		{
			System.out.println("execute = true");
			return true;
		}
		System.out.println("execute = false");
		return false;
	}
	public void traverseAndUpdateBookings() throws SQLException {
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String status="Canceled";
		String d=sdf.format(c.getTime());
		System.out.println(d);
		db.execute("UPDATE `booking` SET `status`= \""+status+"\" WHERE `bookedTill` < \""+d+"\"");
	}
	

}
