package model;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseQuery {
	private DBConnection db;
	private String type;
	private String status;
	private int price;
	private int bol;
	ResultSet rs;
	private int area;
	private String location;
	private String image;
	public DataBaseQuery()
	{
		db=new DBConnection();	
	}
	public boolean updateProperty(int propertyid)
	{
		return db.execute("UPDATE `property` SET `status` = \""+status+"\",`area` = "+area+",`location`=\""+location+"\", `image`=\""+image+"\" WHERE `propertyid` = "+propertyid+"");	
	}
	public boolean updateRRProperty(int propertyid,int specificationid)
	{
		return db.execute("UPDATE `residentialrentalproperty` SET `rent`="+price+",`isonrent`="+bol+" WHERE `rrpropertyid` = "+propertyid+"");
	}
	public boolean updateRFProperty(int propertyid,int specificationid)
	{
		return db.execute("UPDATE `residentialforsaleproperty` SET `price`="+price+",`sold`="+bol+" WHERE `rfpropertyid` = "+propertyid+"");
	}
	public boolean updateCRProperty(int propertyid,int specificationid)
	{
		return db.execute("UPDATE `cemmercialrentalproperty` SET `rent`="+price+",`isonrent`="+bol+" WHERE `crpropertyid` = "+propertyid+"");
	}

	public boolean updateCFProperty(int propertyid,int specificationid)
	{
		return db.execute("UPDATE `commercialforsaleproperty` SET `price`="+price+",`sold`="+bol+" WHERE `cfpropertyid` = "+propertyid+"");
	}
	
	public boolean insertResidential(int nbedrooms,int nbathrooms,int nkitchens,int nstoreys,int balcony,int teriss) throws SQLException
	{
		int specificationid=0;
		db.execute("INSERT INTO `residentialspecification`(`nobedroom`, `nobathroom`, `nokitchen`, `nostorey`, `teriss`, `balcony`) VALUES ("+nbedrooms+","+nbathrooms+","+nkitchens+","+nstoreys+","+teriss+","+balcony+")");
		rs=db.select("SELECT * FROM `residentialspecification`");
		while(rs.next())
		{
			specificationid=rs.getInt("specificationid");
		}
		rs=allProperties();
		int propid=0;
		while(rs.next())
			propid=rs.getInt("propertyid");
		if(type.equals("Residential") && status.equals("ForSale"))
		{
		return db.execute("INSERT INTO `residentialforsaleproperty` (`rfpropertyid`,`specificationid`,`price`,`sold`) VALUES ("+propid+","+specificationid+","+price+","+bol+")");
		}
		else if(type.equals("Residential") && status.equals("ForRent"))
		{
		return db.execute("INSERT INTO `residentialrentalproperty` (`rrpropertyid`,`specificationid`,`rent`,`isonrent`) VALUES ("+propid+","+specificationid+","+price+","+bol+")");
		}
		else
		return true;
	}
	public boolean insertCommercial(int propertyid)
	{
		if(type.equals("Commercial") && status.equals("ForSale"))
		{
		return db.execute("INSERT INTO `commercialforsaleproperty` (`cfpropertyid`,`price`,`sold`) VALUES ("+propertyid+","+price+","+bol+")");
		}
		else if(type.equals("Commercial") && status.equals("ForRent"))
		{
		return db.execute("INSERT INTO `cemmercialrentalproperty`(`crpropertyid`, `rent`, `isonrent`) VALUES ("+propertyid+","+price+","+bol+")");
		}
		else
			return true;
	}
	public boolean deleteProperty(int propertyid) throws SQLException
	{
		if(!db.execute("DELETE FROM `property` WHERE `propertyID`= "+propertyid+""))
		return true;
		return false;
	}
	public boolean isProperty(int propertyid) throws SQLException
	{
		rs=db.select("SELECT * FROM `property` WHERE `propertyID` = "+propertyid+"");
		if(rs!=null)
		if(rs.next())
		return true; 
		return false;
	}
	public void setType(String type)
	{
		this.type=type;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public boolean insertNewProperty(String typ,String stat,int area,String location, String image) throws SQLException
	{
		setType(typ);
		setStatus(stat);
		System.out.println("Database query\t"+image);
		if(image.equals("pms\\"))
			image="";
		return db.execute("INSERT INTO `property`( `type`,`status`, `area`, `location`, `image`) VALUES (\""+typ+"\",\""+stat+"\","+area+",\""+location+"\",\""+image+"\")");
	}
	public void setPrice(int price) {
		// TODO Auto-generated method stub
		this.price=price;
	}
	public ResultSet allProperties()
	{
		return db.select("SELECT * FROM `property`");
	}
	public ResultSet oneProperty(String propertyid)
	{
		return db.select("SELECT * FROM `property` WHERE `propertyID` = "+propertyid.toString()+"");
	}
	public ResultSet searchPropertyByArea(int areafrom, int toarea) {

		return rs= db.select("SELECT `propertyid` FROM `property` WHERE `area` BETWEEN "+areafrom+" AND "+toarea+" ");
		// TODO Auto-generated method stub
	}
	public ResultSet searchPriceRR(int pricefrom , int toprice)
	{
		return db.select("SELECT `propertyid` FROM `property` WHERE `propertyid` IN (SELECT `rrpropertyid` FROM `residentialrentalproperty` WHERE `rent` BETWEEN "+pricefrom+" AND "+toprice+" )");
	}
	public ResultSet searchPriceRF(int pricefrom , int toprice)
	{
		return db.select("SELECT `propertyid` FROM `property` WHERE `propertyid` IN (SELECT `rfpropertyid` FROM `residentialforsaleproperty` WHERE `price` BETWEEN "+pricefrom+" AND "+toprice+" )");
		}
	public ResultSet searchPriceCF(int pricefrom , int toprice)
	{
		return db.select("SELECT `propertyid` FROM `property` WHERE `propertyid` IN (SELECT `cfpropertyid` FROM `commercialforsaleproperty` WHERE `price` BETWEEN "+pricefrom+" AND "+toprice+" )");
	}
	public ResultSet searchPriceCR(int pricefrom , int toprice)
	{
		return db.select("SELECT `propertyid` FROM `property` WHERE `propertyid` IN (SELECT `crpropertyid` FROM `cemmercialrentalproperty` WHERE `rent` BETWEEN "+pricefrom+" AND "+toprice+" )");
	}
	public ResultSet searchPropertyByStatus(String status) 
	{	
		return db.select("SELECT `propertyid` FROM `property` WHERE `status` like \'%"+status+"%\'");
	}
	public ResultSet searchPropertyByType(String type) 
	{	
		return db.select("SELECT `propertyid` FROM `property` WHERE `type` like \'%"+type+"%\'");
	}
	public String[] getAllProperty() throws SQLException
	{
		List l= new List();		
		int i=0;
		rs=null;
		rs=allProperties();
		if(rs!=null)
		{
			while(rs.next())
				{
					int id=rs.getInt("propertyID");
					i++;
					l.add(id+"");
				}
		int size=i;
		i=0;
		String [] array = new String[size];
		while(i<size)
		{
			array[i]=l.getItem(i);
			i++;
		}
		return array;
		}
		else
			return null;
	}
	public ResultSet oneResidentialRental(int propertyid)
	{
		return db.select("SELECT * FROM `residentialrentalproperty` WHERE `rrpropertyid` = "+propertyid+"");
	}
	public ResultSet oneResidentialForSale(int propertyid)
	{
		return db.select("SELECT * FROM `residentialforsaleproperty` WHERE `rfpropertyid` = "+propertyid+"");	
	}
	public ResultSet oneCommercialRental(int propertyid)
	{
		return db.select("SELECT * FROM `cemmercialrentalproperty` WHERE `crpropertyid` = "+propertyid+"");
		
	}
	public ResultSet oneCommercialForSale(int propertyid)
	{
		return db.select("SELECT * FROM `commercialforsaleproperty` WHERE `cfpropertyid` = "+propertyid+"");
		
	}
	public ResultSet getResidential(int propertyid) throws SQLException
	{
		int specificationid=0;
			rs=oneResidentialForSale(propertyid);
		if(rs.next())
			specificationid=rs.getInt("specificationid");
		if(specificationid==0)
		rs=oneResidentialRental(propertyid);
		if(rs.next())
			specificationid=rs.getInt("specificationid");
		rs=this.getResidentialSpecification(specificationid);
		return rs;
	}

	public ResultSet getResidentialSpecification(int specificationid) throws SQLException
	{
		rs=db.select("SELECT * FROM `residentialspecification` where `specificationid` = "+specificationid+"");
		return rs;
	}
	public boolean updatePropertySpecificatin(int specificationid,int nbedroom,int nbathroom,int nkitchen,int nstorey,int balcony,int teriss)
	{
		// TODO Auto-generated method stub
		return db.execute("UPDATE `residentialspecification` SET `nobedroom`="+nbedroom+",`nobathroom`="+nbathroom+",`nokitchen`="+nkitchen+",`nostorey`="+nstorey+",`teriss`="+teriss+",`balcony`="+balcony+" WHERE `specificationid` = "+specificationid+"");	
	}
	public void setCurrentState(boolean sold) {
		if(sold)
			bol=1;
		else
			bol=0;
		// TODO Auto-generated method stub
		
	}
	public void setArea(int area) {
		// TODO Auto-generated method stub
		this.area=area;
	}
	public void setLocation(String location) {
		// TODO Auto-generated method stub
		this.location=location;
	}
	public void setImage(String image) {
		this.image=image;
	}
}
