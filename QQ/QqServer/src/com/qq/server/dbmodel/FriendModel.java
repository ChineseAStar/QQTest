package com.qq.server.dbmodel;

import java.sql.ResultSet;

import com.qq.server.db.*;

public class FriendModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FriendModel bft = new FriendModel();
		for(int i = 1;i<=50;i++)
		{
			for(int j = 1;j<=50;j++)
			{
				if(j!=i)
				bft.addF(i+"",j+"",i+"的好友"+j);
			}
		}
		
	}

	//创建表格
	public boolean createT(String tablename)
	{
		SqlHelper sp = null;
		boolean b = false;
		try{
			
			//组织sql，和参数列表
			String sql = "create table "+tablename+" (friendid varchar(30) primary key,remark nvarchar(20));";
			sp = new SqlHelper();
			if(sp.exeDDL(sql))
			{
				b = true;
				System.out.println(tablename+"创建成功！");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		
		return b;
	}
	
	//删除表格
	public boolean deleteT(String tablename)
	{
		SqlHelper sp = null;
		boolean b = false;
		try{
			
			//组织sql，和参数列表
			String sql = "drop table "+tablename+";";
			sp = new SqlHelper();
			if(sp.exeDDL(sql))
			{
				b = true;
				System.out.println(tablename+"删除成功！");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		
		return b;
	}
	
	//查询好友
	public static String reqFriend(String uid)
	{
		String res = "";
		
		SqlHelper sp = null;
		
		try{
			
			//组织sql，和参数列表
			String sql = "select friendid from "+"friendlist"+uid+";";
			String paras[] = {};
			sp = new SqlHelper();
			ResultSet rs = sp.query(sql, paras);
			while(rs.next())
			{
				res = res+rs.getString(1)+" ";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		return res;
	}
	
	//添加好友
	public boolean addF(String id,String fid,String rem)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//组织sql，和参数列表
			String sql = "insert into friendlist"+id+"(friendid,remark) values(?,?);";
			String paras[] = {fid,rem};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//添加成功
				b = true;
				System.out.println(id+"  成功添加好友     "+fid+"  并备注为    "+rem);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}		
		return false;		
	}
	
}
