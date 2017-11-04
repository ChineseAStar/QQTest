/**
 * 服务器和某个客户端的通信线程
 * 功能：1.接受所有来自于对应客户端的信息包进行处理
 * 		2.发布好友在线信息包
 */
package com.qq.server.bnmodel;

import java.net.*;
import java.util.*;

import com.qq.common.*;
import com.qq.server.dbmodel.FriendModel;
import com.qq.server.tools.ManageClientThread;
import com.qq.server.view.*;

import java.io.*;
public class SerConClientThread extends Thread {

	Socket s;
	
	
	public SerConClientThread(Socket s)
	{
		this.s = s;
	}
	

	//让该线程去通知其他用户
	public void noticeOther(String iam)
	{
		//得到所有在线的人的线程
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			//取出在线人的id
			String onLineUserId = it.next().toString();
			try {
				Message m = new Message();
				m.setCon(iam);
				m.setMessType(MessageType.message_ret_onlineFriend);
		
				ObjectOutputStream oos =new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				m.setSender("0");
				oos.writeObject(m);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public void run()
	{
		while(true)
		{
			//这里该线程就可以接收客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();

				if(ms.getMessType().equals("0"))
				{
					ManageClientThread.delClientThread(ms.getSender(), this);
					this.stop();
				}			
				
				System.out.println(ms.getSender()+" 给  "+ms.getGetter()+" 说   "+ms.getCon());
				//对从客户端取得的消息进行类型判断，然后做相应的处理
				if(ms.getMessType().equals(MessageType.message_comm_mes))
				{
					//转发
					//取得接收人的通讯线程
					SerConClientThread sc = ManageClientThread.getClientThread(ms.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(ms);
				}else if(ms.getMessType().equals(MessageType.message_req_onlineFriend))
				{
					//把在服务器的好友给客户端返回
					String res = ManageClientThread.getAllOnLineUserid();
					Message m2 = new Message();
					m2.setMessType(MessageType.message_ret_onlineFriend);
					m2.setCon(res);
					m2.setGetter(ms.getSender());
					m2.setSender("0");
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}else if(ms.getMessType().equals(MessageType.message_req_Friend))
				{
					//把在服务器的好友给客户端返回
					String res = FriendModel.reqFriend(ms.getSender());
					Message m2 = new Message();
					m2.setMessType(MessageType.message_ret_Friend);
					m2.setCon(res);
					m2.setGetter(ms.getSender());
					m2.setSender("0");
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
