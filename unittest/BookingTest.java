package unittest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import controller.Booking;

public class BookingTest {
	private Booking btest;

	@Test
	public void testInsertBooking() {

		btest=new Booking();
		assertFalse(btest.insertBooking(45,100));
		//assertTrue(btest.insertBooking());
	}

	@Test
	public void testUpdateBookedProperty() throws SQLException {

		btest=new Booking();
		assertTrue(btest.updateBookedProperty(103,42));
	}

	@Test
	public void testGetBookingProperty() throws SQLException {
		btest=new Booking();
		assertTrue(btest.isBookedProperty(103));
	}

	@Test
	public void testHasBookedProperty() throws SQLException {
		btest=new Booking();
		assertTrue(btest.hasBookedProperty(45));
		//fail("Not yet implemented");
	}
	@Test
	public void testisBooked() throws SQLException
	{
		btest=new Booking();
		assertTrue(btest.isBooked(103));
	}
	@Test
	public void testTraverseBooking() throws SQLException
	{
		btest=new Booking();
		btest.traverseBooking();
	}

}
