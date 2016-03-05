package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DeleteUser;

public class DeleteUserControllerTest
{
	DeleteUser obj=new DeleteUser();
	@Test
	public void test() 
	{
		assertFalse(obj.deleteUser("stu@gmail.com"));
	}

}
