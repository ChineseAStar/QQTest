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
				bft.addF(i+"",j+"",i+"�ĺ���"+j);
			}
		}
		
	}

	//�������
	public boolean createT(String tablename)
	{
		SqlHelper sp = null;
		boolean b = false;
		try{
			
			//��֯sql���Ͳ����б�
			String sql = "create table "+tablename+" (friendid varchar(30) primary key,remark nvarchar(20));";
			sp = new SqlHelper();
			if(sp.exeDDL(sql))
			{
				b = true;
				System.out.println(tablename+"�����ɹ���");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		
		return b;
	}
	
	//ɾ�����
	public boolean deleteT(String tablename)
	{
		SqlHelper sp = null;
		boolean b = false;
		try{
			
			//��֯sql���Ͳ����б�
			String sql = "drop table "+tablename+";";
			sp = new SqlHelper();
			if(sp.exeDDL(sql))
			{
				b = true;
				System.out.println(tablename+"ɾ���ɹ���");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			sp.close();
		}
		
		return b;
	}
	
	//��ѯ����
	public static String reqFriend(String uid)
	{
		String res = "";
		
		SqlHelper sp = null;
		
		try{
			
			//��֯sql���Ͳ����б�
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
	
	//��Ӻ���
	public boolean addF(String id,String fid,String rem)
	{
		boolean b = false;
		SqlHelper sp = null;
		
		try{			
			//��֯sql���Ͳ����б�
			String sql = "insert into friendlist"+id+"(friendid,remark) values(?,?);";
			String paras[] = {fid,rem};
			sp = new SqlHelper();
			if(sp.exeUpdate(sql, paras))
			{
				//��ӳɹ�
				b = true;
				System.out.println(id+"  �ɹ���Ӻ���     "+fid+"  ����עΪ    "+rem);
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
