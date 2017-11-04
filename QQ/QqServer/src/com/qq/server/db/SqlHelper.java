package com.qq.server.db;

import java.sql.*;

public class SqlHelper {


	//������Ҫ�Ķ���
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection ct = null;
	String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=QQ";
	String user = "sa";
	String passwd = "1234";
	
	//���캯��,��ʼ��ct
	public SqlHelper()
	{
		try {
			//��������
			Class.forName(driver);
			ct = DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//�ر���Դ
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�Ѷ����ݿ����ɾ��
	public boolean exeUpdate(String sql,String []paras)
	{
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			for(int i = 0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}

	//�����ݿ�Ĳ�ѯ
	public ResultSet query(String sql,String []paras)
	{
		try {
			ps = ct.prepareStatement(sql);
			for(int i = 0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//�����ݿ��ddl�������
	public boolean exeDDL(String sql)
	{
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}
	
}
