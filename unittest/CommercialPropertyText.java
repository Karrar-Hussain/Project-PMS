package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import controller.CommercialProperty;

public class CommercialPropertyText {
	private CommercialProperty cp;
	@Test
	public void testInsertNewProperty() {
		cp=new CommercialProperty();
		cp.setArea(10);
		cp.setLocation("no Location");
		cp.setImage("No Image Path");
		cp.setPrice(50005000);
		cp.setType("Commercial");
		cp.setStatus("Full");
		assertFalse(cp.insertNewProperty());
		//fail("Not yet implemented");
	}

	@Test
	public void testIsSold() {
		cp=new CommercialProperty();
		cp.isSold(true);
		//fail("Not yet implemented");
	}
	@Test
	public void testInsertCommercial() throws SQLException {
		//fail("Not yet implemented");
		cp=new CommercialProperty();
		cp.setArea(10);
		cp.setCurrentState(false);
		cp.setImage("PMS");
		cp.setStatus("Full");
		cp.setType("Commercial");
		cp.setPrice(24500000);
		assertTrue(cp.insertCommercial());	
	}

	@Test
	public void testOneCommercial() throws SQLException {
		cp=new CommercialProperty();
		cp.setStatus("ForRent");
		ResultSet rs=cp.oneCommercial(130);
		assertNotNull(rs);
		if(rs.next())
		{
			assertEquals(130,rs.getLong("cfpropertyid"));
		}	
	}

	@Test
	public void testUpdateProperty() {
		cp=new CommercialProperty();
		cp.setArea(10);
		cp.setPropertyId(130);
		cp.setCurrentState(false);
		cp.setImage("PMS");
		cp.setStatus("Full");
		cp.setType("Residential");
		cp.setPrice(400000);
		assertTrue(cp.updateProperty());
	}
}
