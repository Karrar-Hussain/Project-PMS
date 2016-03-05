package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
/** 
 * ResidentialProperty is a class the extends Property(also user defined class) 
 * As from its name its clear the its a type of property
 * This class deals with the residential properties and their specification
 * It is the creator of specification class or specification is composed in this class
 * @author Karrar
 * @version 2.0
 */
public class ResidentialProperty extends Property{
	
	private int price;
	private boolean sold;
	private ResultSet rs;
	/** Specification class object is created here as residential property has specifications*/
	private Specification specification;
	
	private LinkedList<String> l;
	private LinkedList<String> l2;
	private LinkedList<String> l3;
	/** Constructor of the class initializing all its member variables*/
	public ResidentialProperty()
	{

		l3=new LinkedList<String>();
		l=new LinkedList<String>();
		l2=new LinkedList<String>();
	 specification=new Specification(dbq);
	}
	/** isSold is a aimple setter function that sets whether 
	 * property is sold or is available for sale
	 * @param sold is a boolean type if true; means that property is sold
	 * else property is available for sale  
	 */
	public void isSold(boolean sold)
	{
		this.sold=sold;
	}
	/** setPrice is simple setter function that sets the price of the property 
	 * 
	 * @param price is int type variable which is assigned to class variable price 
	 */
	public void setPrice(int price)
	{
		this.price=price;
	}

	/** setCurrentState is a aimple setter function that sets whether 
	 * property is sold or is available for sale
	 * @param sold is a boolean type if true; means that property is sold
	 * else property is available for sale  
	 */
	public void setCurrentState(boolean sold)
	{
		this.sold=sold;
	}
	/** updatePropertySpecification(id); this function updates the specification of Residential property
	 * it takes specificationid as parameter and class 
	 * updatePropertySpecification(id) of specification class where
	 * real updation is done 
	 * 
	 * @param specificationid is identifier(primary key) of the property specification in database
	 * @return boolean false if no error occured and updatation succeed else true is returned
	 */
	public boolean updatePropertySpecification(int specificationid)
	{
		return specification.updatePropertySpecification(specificationid);
	}
	/** getResidential() is simple function that returns 
	 * property attributes of the property whose property id is provided 
	 * 
	 * @return ResultSet which will contain the rows from residential property whose id is propertyid
	 * @throws SQLException 
	 */
	public ResultSet getResidential() throws SQLException
	{
		return dbq.getResidential(propertyid);
	}
	/** getResidentialSpecification() is simple function that returns 
	 * Specification of Residential Property whose property id is provided 
	 * 
	 * @return ResultSet which will contain the rows from Specifcation table whose id is propertyid
	 * @throws SQLException 
	 */
	public ResultSet getResidentialSpecification() throws SQLException 
	{
		return specification.getResidentialSpecification();
	}
	/** oneResidential() is simple function that returns 
	 * row from Residential property whose property id is provided 
	 * 
	 * @param propertyid is int type that is to specify property in database
	 * @return ResultSet which will contain the rows from residential property whose id is propertyid
	 * @throws SQLException 
	 */
	public ResultSet oneResidential(int propertyid) throws SQLException
	{
			if(status.equals("ForSale"))
			{
				return dbq.oneResidentialForSale(propertyid);
			}
			else
			{
				return dbq.oneResidentialRental(propertyid);
			}	
	}

/** insertResidential() is function which class insertResidentailProperty of DBclass where querys are executed
 * 
 * @return boolean true is inserted else false 
 */
	public boolean insertResidential()
	{
		propertyid=0;
		dbq.setType(type);
		dbq.setStatus(status);
		dbq.setCurrentState(sold);
		dbq.setPrice(price);
		return specification.insertResidential();
	}
	/** setSpecificationId(specificationid) is simple setter function to specify specification id 
	 * 
	 * @param specificationid specify specification of the residential property
	 */
	public void setSpecificationId(int specificationid) 
	{
		specification.setSpecificationId(specificationid);
	}
	/** updateProperty() is the function that updates the residential property it calls 
	 *  updateProperty() of dbClass to update property and residential property type and specification 
	 * 
	 * @return boolean true if updated successfully else false
	 */
	public boolean updateProperty() 
	{
		int specificationid=specification.getSpecificationid();
		dbq.setArea(area);
		dbq.setPrice(price);
		dbq.setCurrentState(sold);
		dbq.setStatus(status);
		dbq.setLocation(location);
		dbq.setImage(image);
		if(!dbq.updateProperty(propertyid))
		{
			if(!status.equals(prevstate))
				{
					return true;
				}
			else if(type.equals("Commercial")&&status.equals("ForSale"))
				{
					return dbq.updateCFProperty(propertyid, 0);	
				}
			else if(type.equals("Commercial")&&status.equals("ForRent"))
				{
					return dbq.updateCRProperty(propertyid, 0);	
				}
			else if(type.equals("Residential")&&status.equals("ForRent"))
				{
					return dbq.updateRRProperty(propertyid, specificationid);	
				}
			else if(type.equals("Residential")&&status.equals("ForSale"))
				{
					return dbq.updateRFProperty(propertyid, specificationid);	
				}
		}
		return true;
	}
	/** setSpecification is simple setter that takes specification attributes as parameter and class
	 * setter of specification class for each of them
	 * to set specifcation for insertion or updatation
	 * 
	 * @param nbedrooms Number of bedrooms in Residential Property (House, Plot etc.)
	 * @param nbathrooms Number of bathrooms in Residential Property (House, Plot etc.)
	 * @param nkitchens Number of Kitchens in Residential Property (House, Plot etc.)
	 * @param nstoreys Number of storeys in Residential Property (House, Plot etc.)
	 * @param balcony if residential property has balcony or not
	 * @param teriss if terace is in property or not
	 */
	public void setSpecification(int nbedrooms,int nbathrooms,int nkitchens,int nstoreys,int balcony,int teriss)
	{
		specification.setNBedrooms(nbedrooms);
		specification.setNBathrooms(nbathrooms);
		specification.setNKitchens(nkitchens);
		specification.setNStoreys(nstoreys);
		specification.setBalcony(balcony);
		specification.setTeriss(teriss);
	}
	private String str;
	/** This function simpling implements the Search operation on different bases
	 * this function gets the result set of every time it is called add it to linked lists
	 * and finally puts the common of all list in linked list l3 which we will show 
	 * @param rs is the ResultSet that is to be added to linked list
	 * @throws SQLException
	 */
	public void resultSetToArray(ResultSet rs) throws SQLException
	{
		int propertyid=0;
		boolean bol=true;
		if(l.isEmpty())
			bol=true;
		else
			bol=false;
		if(rs!=null)
		{
			while(rs.next())
			{
				propertyid=rs.getInt("propertyid");
					if(bol)
						l.add(propertyid+"");
					else
						l2.add(propertyid+"");		
			}
			if(!l.isEmpty()&&!l2.isEmpty())
			{
				if(l3.isEmpty())
				{
					while(l2.size()>0)
					{
						str = l2.getFirst();
						if(l.contains(str))
						{
							l3.add(str);
						}
						l2.removeFirst();
					}
				}
				else
				{
					int k=0;
					while(k<l3.size())
					{
						str=l3.get(k);
						if(!l2.contains(str))
							l3.remove(k);
						k++;
					}
					l2.clear();
				}
			}
		}
	}
	/** SearchProperty() This is search functionality for properties search on different base
	 * May be multiple bases
	 * if Multiple base to search on it will call the above function and add 
	 * result sets on different linked lists 
	 * and finally convert the linked list which contains all those property into array
	 * 
	 * @param propertyid propertyid is if only by propertyid is searched
	 * @param areafrom to specify the search property area from this
	 * @param toarea to specify property area between areafrom and toarea this 
	 * @param pricefrom to specify the search property price from this 
	 * @param topriceto specify property a between pricefrom and toprice this 
	 * @param status status specify to search by status too
	 * @param type To specify search by type of property
	 * @return In the end it returns the array of property ids of all found properties
	 * @throws SQLException
	 */
	public String[] searchProperty(int propertyid,int areafrom,int toarea,int pricefrom,int toprice,String status,String type) throws SQLException
	{
		if(!l3.isEmpty()||!l.isEmpty()||!l2.isEmpty())
		{
		l.clear();
		l2.clear();
		l3.clear();
		}
		String []propertyids;
		if(propertyid!=0)
			{
				rs=dbq.oneProperty(propertyid+"");
				this.resultSetToArray(rs);
			}
		else if( areafrom==0 &&toarea==0 && pricefrom==0 && toprice==0 &&type.equals("0")&&status.equals("0"))
			{
				rs=dbq.allProperties();
				this.resultSetToArray(rs);
			}
		else 
		{
			if(areafrom==0 && toarea==0);
			else
			{
				rs=dbq.searchPropertyByArea(areafrom,toarea);
				this.resultSetToArray(rs);
			}
			if(pricefrom!=0||toprice!=0)
			{
				rs=dbq.searchPriceCF(pricefrom, toprice);
				if(rs!=null)
				this.resultSetToArray(rs);
				rs=dbq.searchPriceCR(pricefrom, toprice);
				if(rs!=null)
				this.resultSetToArray(rs);
				rs=dbq.searchPriceRF(pricefrom, toprice);
				if(rs!=null)
				this.resultSetToArray(rs);
				rs=dbq.searchPriceRR(pricefrom, toprice);
				if(rs!=null)
				this.resultSetToArray(rs);
				
			}
			if(!type.equals("0"))
			{
				rs=dbq.searchPropertyByType(type);
				this.resultSetToArray(rs);
			}
			if(!status.equals("0"))
			{
				rs=dbq.searchPropertyByStatus(status);
				this.resultSetToArray(rs);
			}
		}
		if(l3.isEmpty())
		{
			propertyids=new String[l.size()];
			l.toArray(propertyids);
		}
		else
		{
			propertyids=new String[l3.size()];
		l3.toArray(propertyids);
		}
		return propertyids;
	}
}
