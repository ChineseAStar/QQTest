/**
 * 客户端和服务器的链接线程，保证发送信息到正确地址
 * 如果登录成功开启一个客户端线程
 * 将登陆成功与否的信息发回给登录界面
 */
package com.qq.client.model;

import java.io.*;
import java.net.*;
import java.util.*;

import com.qq.client.tools.*;
import com.qq.common.*;

public class QqClientConServer {

	public Socket s;
	private String ip;
	private int port;
	
	public QqClientConServer(String ip,int port)
	{
		this.ip = ip;
		this.port = port;
	}
	
	//发送一次请求
	public boolean sendInfoToServer(Object o)
	{
		boolean b = false;
		try {
			s = new Socket(ip,port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			Message ms = (Message)ois.readObject();
			//这里就是验证用户登录的地方
			if(ms.getMessType().equals(MessageType.message_succeed))
			{
				//就创建一个该qq号和服务器端保持连接的
				ClientConServerThread ccst = new ClientConServerThread(s);
				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
				b = true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return b;
	}
	
}
