/**
 * 这是qq服务器，他在监听，等待某个QQ客户端来连接并判断登录信息是否正确
 * 将登陆成功的SerConClientThread放入到 ManageClientThread
 */
package com.qq.server.bnmodel;

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.common.*;
import com.qq.server.bnmodel.*;
import com.qq.server.dbmodel.UserModel;
import com.qq.server.tools.ManageClientThread;
public class MyQqServer extends Thread {
	
	private boolean b = true;
	public int port;
	private ServerSocket ss;
	private Socket s;
	
	public MyQqServer(String port)
	{
		this.port = Integer.parseInt(port);		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//在9999监听
			System.out.println("我是服务器，在"+port+"监听");
			ss = new ServerSocket(port);
			while(b)
			{
				//阻塞，等待连接
				s = ss.accept();
				
				if(s != null)
				{
					System.out.println("test");
					//接收客户端发来的信息
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					User u = (User)ois.readObject();
		
					System.out.println("服务器接收到了用户ID和密码   "+ u.getUserId()+"\t"+u.getPassword());
					Message m = new Message();
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					if(new UserModel().checkUser(u.getUserId(), u.getPassword()))
					{
						//返回一个成功登陆的信息包
						m.setMessType(MessageType.message_succeed);
						oos.writeObject(m);
						
						//这里单开一个线程，与该客户端保持联系
						SerConClientThread scct = new SerConClientThread(s);
						ManageClientThread.addClientThread(u.getUserId(), scct);
						//启动与改客户端通信的线程
						scct.start();
						
						//并通知其他在线用户
						scct.noticeOther(u.getUserId());
						
					}else {
						m.setMessType(MessageType.message_login_fail);
						oos.writeObject(m);
						s.close();
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}
	
	//关闭线程
	public void closeself()
	{
		this.b = false;
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
