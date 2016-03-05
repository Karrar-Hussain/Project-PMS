package unittest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBaseQuery;

import org.junit.Test;

import controller.ResidentialProperty;
import controller.Specification;

public class ResidentialPropertyTest {
	private ResidentialProperty rp;
	@Test
	public void testUpdatePropertySpecification() throws SQLException {

		DataBaseQuery dbq=new DataBaseQuery();
		rp=new ResidentialProperty();
		Specification sp=new Specification(dbq);
		sp.setSpecificationId(20);
		ResultSet rs_expected=sp.getResidentialSpecification();
		rp.setSpecification(5, 5, 5, 5, 1, 1);
		rp.updatePropertySpecification(20);
		rp.setSpecificationId(20);
		ResultSet rs_actual=rp.getResidentialSpecification();
		while(rs_expected.next()&&rs_actual.next())
		{
			assertEquals(rs_actual.getLong("specificationid"),rs_expected.getLong("specificationid"));
			assertEquals(rs_actual.getLong("nobedroom"),rs_expected.getLong("nobedroom"));
			assertEquals(rs_actual.getLong("nobathroom"),rs_expected.getLong("nobathroom"));
			assertEquals(rs_actual.getLong("nokitchen"),rs_expected.getLong("nokitchen"));
			assertEquals(rs_actual.getLong("nostorey"),rs_expected.getLong("nostorey"));
			assertEquals(rs_actual.getLong("teriss"),rs_expected.getLong("teriss"));
			assertEquals(rs_actual.getLong("balcony"),rs_expected.getLong("balcony"));
		}
	}

	@Test
	public void testGetResidential() throws SQLException {
		rp=new ResidentialProperty();
		rp.setPropertyId(100);
		ResultSet rs=rp.getResidential();
		assertNotNull(rs);
		if(rs.next())
		{
			assertEquals(15,rs.getLong("specificationid"));
		}
	}

	@Test
	public void testGetResidentialSpecification() throws SQLException {
		DataBaseQuery dbq=new DataBaseQuery();
		rp=new ResidentialProperty();
		Specification sp=new Specification(dbq);
		sp.setSpecificationId(20);
		ResultSet rs_expected=sp.getResidentialSpecification();
		rp.setSpecification(5, 5, 5, 5, 1, 1);
		rp.updatePropertySpecification(20);
		rp.setSpecificationId(20);
		ResultSet rs_actual=rp.getResidentialSpecification();
		while(rs_expected.next()&&rs_actual.next())
		{
			assertEquals(rs_actual.getLong("specificationid"),rs_expected.getLong("specificationid"));
			assertEquals(rs_actual.getLong("nobedroom"),rs_expected.getLong("nobedroom"));
			assertEquals(rs_actual.getLong("nobathroom"),rs_expected.getLong("nobathroom"));
			assertEquals(rs_actual.getLong("nokitchen"),rs_expected.getLong("nokitchen"));
			assertEquals(rs_actual.getLong("nostorey"),rs_expected.getLong("nostorey"));
			assertEquals(rs_actual.getLong("teriss"),rs_expected.getLong("teriss"));
			assertEquals(rs_actual.getLong("balcony"),rs_expected.getLong("balcony"));
		}
	}

	@Test
	public void testOneResidential() throws SQLException {
		rp=new ResidentialProperty();
		rp.setStatus("ForRent");
		ResultSet rs=rp.oneResidential(100);
		assertNotNull(rs);
		if(rs.next())
		{
			assertEquals(100,rs.getLong("rrpropertyid"));
		}
	}

	@Test
	public void testInsertResidential() {
		rp=new ResidentialProperty();
		rp.setArea(10);
		rp.setCurrentState(false);
		rp.setImage("PMS");
		rp.setStatus("Full");
		rp.setType("Residential");
		rp.setPrice(400000);
		assertTrue(rp.insertResidential());
	}

	@Test
	public void testUpdateProperty() {
		rp=new ResidentialProperty();
		rp.setArea(10);
		rp.setPropertyId(131);
		rp.setCurrentState(false);
		rp.setImage("PMS");
		rp.setStatus("Full");
		rp.setType("Residential");
		rp.setPrice(400000);
		assertTrue(rp.updateProperty());
	}
	@Test
	public void testSearchProperty() throws SQLException {
		rp=new ResidentialProperty();
		String []ids=rp.searchProperty(130, 0, 0, 0, 0, "0", "0");
		assertEquals(130,Long.parseLong(ids[0]));
	}

}
