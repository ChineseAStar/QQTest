package com.qq.server.dbmodel;

import java.sql.*;

import com.qq.server.db.*;

public class UserModel {

	
	public boolean checkUser(String uid,String p)
	{
		boolean b = false;
		
		SqlHelper sp = null;
		
		try{
			
			//组织sql，和参数列表
			String sql = "select pass from account where userid = ?;";
			String paras[] = {uid};
			sp = new SqlHelper();
			ResultSet rs = sp.query(sql, paras);
			if(rs.next())
			{
				if(rs.getString(1).equals(p))
				{
					b = true;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		return b;
	}
	
	//修改用户
	public boolean updateUser(String uid,String np)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//组织sql，和参数列表
			String sql = "update account set pass = ? where userid = ?;";
			String paras[] = {np,uid};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//修改成功
				b = true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		return b;
	}
	
	//添加用户
	public boolean addUser(String uid,String p)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//组织sql，和参数列表
			String sql = "insert into account(userid,pass) values(?,?);";
			String paras[] = {uid,p};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//添加成功
				b = true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		return b;
	}
	
	//注销用户
	public boolean delUser(String uid,String p)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//组织sql，和参数列表
			String sql = "delete from account where userid = ? and pass = ?;";
			String paras[] = {uid,p};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//注销成功
				b = true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		return b;
	}
}
