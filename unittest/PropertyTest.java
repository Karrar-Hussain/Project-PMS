package unittest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import controller.Property;

public class PropertyTest {
	@Test
	public void testOneProperty() throws SQLException {
		Property p=new Property();
		ResultSet rs=p.oneProperty("130");
		assertNotNull(rs);
		if(rs.next())
		assertEquals(130,rs.getLong("propertyid"));
	}

	@Test
	public void testGetAllProperty() throws SQLException {
		Property p=new Property();
		String [] Ids=p.getAllProperty();
		//assertNull(p.getAllProperty());
		assertEquals(96, Integer.parseInt(Ids[0]));
	}

	@Test
	public void testInsertNewProperty() {
		Property p=new Property();
		p.setArea(1);
		p.setLocation("No Location");
		p.setImage("path");
		p.setStatus("Full");
		p.setType("Residential");
		
		assertEquals(false, p.insertNewProperty());
	}

}
