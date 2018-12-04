package com.qq.client.model;

import com.qq.common.*;

public class QqClientUser {

	private String ip;
	private int port;
	
	public QqClientUser(String ip,int port)
	{
		this.ip = ip;
		this.port = port;
	}
	
	public boolean checkUser(User u)
	{		
		return new QqClientConServer(ip,port).sendInfoToServer(u);
	}
	
}
