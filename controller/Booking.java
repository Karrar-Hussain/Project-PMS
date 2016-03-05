package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import model.BookingDB;

import com.mysql.jdbc.ResultSet;

public class Booking {
	private Date bookedOn;
	private BookingDB dbq;
	public Booking()
	{
		dbq=new BookingDB();
		bookedOn=new Date();
	}
	public Date getBookedOn() 
	{
		return bookedOn;
	}
	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}	
	public boolean insertBooking(int cust_id,int propertyid)
	{
		return dbq.insertBooking(cust_id, propertyid);
	}
	public boolean updateBookedProperty(int propertyId,int cust_id) throws SQLException
	{
		ResultSet rs;
		int bookingId=0;
		rs=(ResultSet) dbq.getBookedProperty(cust_id);
		if(rs!=null)
		{
			if(rs.next())
			{
				bookingId=rs.getInt("bookingid");
				System.out.println("bid: "+bookingId);
			return !dbq.updateBookedProperty(bookingId,propertyId);
			}
		}
		return false;
	}
	
	public String[][] allBookings(String status) throws SQLException
	{
		ResultSet rs=(ResultSet) dbq.allBookings(status);
		//LinkedList<String> l1=new LinkedList<String>();
		LinkedList<String> l2=new LinkedList<String>();
		LinkedList<String> l3=new LinkedList<String>();
		LinkedList<String> l4=new LinkedList<String>();
		LinkedList<String> l5=new LinkedList<String>();
		
		if(rs!=null)
		{
			while(rs.next())
			{
				//l1.add(rs.getString("bookingid"));
				l2.add(rs.getString("userid"));
				l3.add(rs.getString("propertyid"));
				l4.add(rs.getString("bookedon"));
				l5.add(rs.getString("status"));
			}
		}
		//l1.addAll((Collection<? extends String>) r1);
		String[][] result=new String[l2.size()][4];
		int j=0;
		while(l2.size()>j)
		{
			//result[j][0]=l1.get(j);
			result[j][0]=l2.get(j);
			result[j][1]=l3.get(j);
			result[j][2]=l4.get(j);
			result[j][3]=l5.get(j);
			/*System.out.println("Booking ids: "+l1.get(j)+" = "+result[j][0]);
			System.out.println("user ids: "+l2.get(j)+" = "+result[j][1]);
			System.out.println("prop ids: "+l3.get(j)+" = "+result[j][2]);
			System.out.println("date: "+l4.get(j)+" = "+result[j][3]);*/
			j++;
		}
		//l1.clear();
		l2.clear();
		l3.clear();
		l4.clear();
		l5.clear();
		return result;
	}
	/*public static void main(String args[]) throws SQLException
	{
		Booking b=new Booking();
		b.allBookings();
	}*/
	public boolean isBookedProperty(int propertyid) throws SQLException
	{
		if(propertyid!=0)
		{
			ResultSet rs=(ResultSet) dbq.getBookingProperty(propertyid);
			if(rs!=null)
			if(rs.next())
			{
				return true;
			}
			else
				return false;
		}
		return false;
	}
	public boolean hasBookedProperty(int cust_id) throws SQLException 
	{
		int pid=0;
		ResultSet rs=null;
		if(cust_id!=0)
		{
			rs=(ResultSet) dbq.getBookedProperty(cust_id);
			if(rs!=null)
			{
				while(rs.next())
				{
					pid=rs.getInt("propertyid");
					if(this.isBooked(pid))
						return true;
					System.out.println("pid : "+pid);
				}
				
			}
			else
			return false;
		}
		else
		{
			System.out.println("Cust_id 0");
		}
		return false;
	}
	public boolean isBooked(int propertyid) throws SQLException 
	{
		String status="null";
		ResultSet rs=(ResultSet) dbq.getBookingProperty(propertyid);
		if(rs!=null)
		{
			while(rs.next())
				{
					status=rs.getString("status");
					if(status.equals("Booked"))
					{
						return true;
					}
				}
		}
		return false;
	}
	public void traverseBooking() throws SQLException {
		dbq.traverseAndUpdateBookings();
	}
	
}
