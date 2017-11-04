package com.qq.server.db;

import java.sql.*;

public class SqlHelper {


	//定义需要的对象
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection ct = null;
	String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=QQ";
	String user = "sa";
	String passwd = "1234";
	
	//构造函数,初始化ct
	public SqlHelper()
	{
		try {
			//加载驱动
			Class.forName(driver);
			ct = DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//关闭资源
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
	
	//把对数据库的增删改
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

	//对数据库的查询
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
	
	//对数据库的ddl操作语句
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
