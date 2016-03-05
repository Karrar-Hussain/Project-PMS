package controller;

import model.DeleteUser;

public class DeleteUserController
{
	DeleteUser obj=new DeleteUser();
	public boolean deleteUser (String email)
	{
		return obj.deleteUser(email);
	}
}
