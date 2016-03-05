package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBaseQuery;

import org.junit.Test;

import controller.ResidentialProperty;
import controller.Specification;

public class SpecificationTest {
	private Specification spec;
	@Test
	public void testGetDataBaseQuery() {
		DataBaseQuery dbq=new DataBaseQuery();
		spec=new Specification(dbq);
		assertEquals(dbq,spec.getDataBaseQuery());
		//fail("Not yet implemented");
	}

	@Test
	public void testSetSpecificationId() {
		DataBaseQuery dbq=new DataBaseQuery();
		spec=new Specification(dbq);
		spec.setSpecificationId(100);
		assertEquals(100,spec.getSpecificationid());
	}

	@Test
	public void testGetSpecificationid() {
		DataBaseQuery dbq=new DataBaseQuery();
		spec=new Specification(dbq);
		spec.setSpecificationId(100);
		assertEquals(100,spec.getSpecificationid());
	}

	@Test
	public void testInsertResidential() throws SQLException {
		ResidentialProperty rp=new ResidentialProperty();
		rp.setArea(10);
		rp.setCurrentState(false);
		rp.setImage("PMS");
		rp.setStatus("Full");
		rp.setType("Residential");
		rp.setPrice(400000);
		assertTrue(rp.insertResidential());	
	}

	@Test
	public void testGetResidentialSpecification() throws SQLException {
	
		DataBaseQuery dbq=new DataBaseQuery();
		spec=new Specification(dbq);
		spec.setSpecificationId(20);
		
		ResultSet rs=spec.getResidentialSpecification();
		assertNotNull(rs);
		if(rs.next())
		{
			assertEquals(20,rs.getLong("specificationid"));
		}
	}

	@Test
	public void testUpdatePropertySpecification() throws SQLException {

		DataBaseQuery dbq=new DataBaseQuery();
		ResidentialProperty rp=new ResidentialProperty();
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
}
