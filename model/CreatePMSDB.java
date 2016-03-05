package model;

import javax.swing.JOptionPane;

public class CreatePMSDB {
	DBConnection db;
	public CreatePMSDB()
	{
		db=new DBConnection();
		
	}
	public boolean isDbExist()
	{
		String query="0";
		
		if(db.getConnection()==null)
		{
			query="CREATE `pmsdb` IF NOT EXISTS `pmsdb`";
			if(!db.execute(query))
				{
				if(createTables())
				JOptionPane.showMessageDialog(null, "DataBase Created Successfully!");
				return true;
				}
			else
				return false;
		}
		else
			JOptionPane.showMessageDialog(null, "DataBase Exist Found and is Connected!");
		return false;
	}
	public boolean createTables()
	{
		/*
		 * Data BAse Tables querys EXecution Funtion
		 * 
		 */
		return true;
	}
	
	CreatePMSDB pms=new CreatePMSDB();
	assertFalse(pms.isDbExist());

}
