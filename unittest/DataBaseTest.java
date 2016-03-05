package unittest;

import static org.junit.Assert.*;
import model.CreatePMSDB;

import org.junit.Test;

public class DataBaseTest {

	@Test
	public void testCreatePMSDB() {
		CreatePMSDB pms=new CreatePMSDB();
		assertFalse(pms.isDbExist());
	}

	@Test
	public void testIsDbExist() {
		CreatePMSDB pms=new CreatePMSDB();
		assertFalse(pms.isDbExist());
	}
	@Test
	public void testCreateTables() {
		CreatePMSDB pms=new CreatePMSDB();
		assertFalse(pms.isDbExist());
	}

}
