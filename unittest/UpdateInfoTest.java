package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DbUpdateInfo;

public class UpdateInfoTest 
{
	DbUpdateInfo d_obj=new DbUpdateInfo();
	@Test
	public void test() 
	{
		assertFalse(d_obj.updateInfo("Raja", "Basiq", "M", "N", "12-12-1990", 25, "0123213871728", "Admin", "Hostel", "raja123@gmail.com", "Chmod123", "Profiles#Slash#karrar.jpg", "pqr@gmail.com"));
	}

}
