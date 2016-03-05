package controller;

import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.ResultSet;

import model.DbSearchUser;

public class SearchUserCont
{

	private LinkedList<String> l;
	private LinkedList<String> l2;
	private LinkedList<String> l3;
	private String str;
	private ResultSet rs;
	DbSearchUser db_obj=new DbSearchUser();
	public SearchUserCont()
	{
		l3=new LinkedList<String>();
		l=new LinkedList<String>();
		l2=new LinkedList<String>();
	}
	public void resultSetToArray(ResultSet rs) throws SQLException
	{
		String propertyid;
		boolean bol=true;
		if(l.isEmpty())
			bol=true;
		else
			bol=false;
		if(rs!=null)
		{
			while(rs.next())
			{
				propertyid=rs.getString("user_email");
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
	public String[] searchUser(String email,String name,String type) throws SQLException
	{
		if(!l3.isEmpty()||!l.isEmpty()||!l2.isEmpty())
		{
		l.clear();
		l2.clear();
		l3.clear();
		}
		String []useremails;
		if(!email.equals("null"))
			{
				rs=db_obj.searchByEmail(email);
				if(rs!=null)
				this.resultSetToArray(rs);
			}
		if(!name.equals("null"))
			{
			rs=db_obj.searchByName(name);
			if(rs!=null)
			this.resultSetToArray(rs);
			}
		if(!type.equals("null"))
			{
				rs=db_obj.searchByType(type);
				if(rs!=null)
				this.resultSetToArray(rs);
			}
		if(l3.isEmpty())
		{
			useremails=new String[l.size()];
			l.toArray(useremails);
		}
		else
		{
			useremails=new String[l3.size()];
		l3.toArray(useremails);
		}
		return useremails;
	}

}
