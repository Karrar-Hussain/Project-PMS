package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBaseQuery;

public class Specification {
private int specificationid;
private int nbedrooms;
private int nbathrooms;
private int nstoreys;
private int nkitchens;
private int balcony;
private int teriss;
private DataBaseQuery dbq;
public DataBaseQuery getDataBaseQuery()
{
	return dbq;
}
public Specification(DataBaseQuery dbq)
{
	this.dbq=dbq;
}

public void setSpecificationId(int specificationid) 
{
	this.specificationid=specificationid;
}
public int getSpecificationid()
{
	return specificationid;
}
public boolean insertResidential()
{
	try
	{
	return dbq.insertResidential(nbedrooms,this.nbathrooms,this.nkitchens,this.nstoreys,this.balcony,this.teriss);
	}
	catch(Exception ex)
	{
	ex.printStackTrace();
	return true;
	}
}
public ResultSet getResidentialSpecification() throws SQLException
{
	return dbq.getResidentialSpecification(specificationid);
}

public boolean updatePropertySpecification(int specificationid) 
{
	return dbq.updatePropertySpecificatin(specificationid,nbedrooms,this.nbathrooms,this.nkitchens,this.nstoreys,this.balcony,this.teriss);
}

public void setNBedrooms(int nbedrooms)
{
	this.nbedrooms=nbedrooms;
}
public void setNBathrooms(int nbedrooms)
{
	this.nbathrooms=nbedrooms;
}
public void setNKitchens(int nbedrooms)
{
	this.nkitchens=nbedrooms;
}
public void setNStoreys(int nbedrooms)
{
	this.nstoreys=nbedrooms;
}
public void setBalcony(int balcony)
{
	this.balcony=balcony;
}
public void setTeriss(int balcony)
{
	this.teriss=balcony;
}
public void setDataBaseQuery(DataBaseQuery dbq) {
	this.dbq =dbq;
}
}
