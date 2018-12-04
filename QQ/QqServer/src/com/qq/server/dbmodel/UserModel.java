package com.qq.server.dbmodel;

import java.sql.*;

import com.qq.server.db.*;

public class UserModel {

	
	public boolean checkUser(String uid,String p)
	{
		boolean b = false;
		
		SqlHelper sp = null;
		
		try{
			
			//��֯sql���Ͳ����б�
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
	
	//�޸��û�
	public boolean updateUser(String uid,String np)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//��֯sql���Ͳ����б�
			String sql = "update account set pass = ? where userid = ?;";
			String paras[] = {np,uid};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//�޸ĳɹ�
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
	
	//����û�
	public boolean addUser(String uid,String p)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//��֯sql���Ͳ����б�
			String sql = "insert into account(userid,pass) values(?,?);";
			String paras[] = {uid,p};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//��ӳɹ�
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
	
	//ע���û�
	public boolean delUser(String uid,String p)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//��֯sql���Ͳ����б�
			String sql = "delete from account where userid = ? and pass = ?;";
			String paras[] = {uid,p};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//ע���ɹ�
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
