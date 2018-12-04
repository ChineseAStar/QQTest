/**
 * ��������ĳ���ͻ��˵�ͨ���߳�
 * ���ܣ�1.�������������ڶ�Ӧ�ͻ��˵���Ϣ�����д���
 * 		2.��������������Ϣ��
 */
package com.qq.server.bnmodel;

import java.net.*;
import java.util.*;

import com.qq.common.*;
import com.qq.server.dbmodel.FriendModel;
import com.qq.server.tools.ManageClientThread;

import java.io.*;
public class SerConClientThread extends Thread {

	Socket s;
	
	
	public SerConClientThread(Socket s)
	{
		this.s = s;
	}
	

	//�ø��߳�ȥ֪ͨ�����û�
	public void noticeOther(String iam)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			//ȡ�������˵�id
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
			//������߳̾Ϳ��Խ��տͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();

				if(ms.getMessType().equals("0"))
				{
					ManageClientThread.delClientThread(ms.getSender(), this);
					this.stop();
				}			
				
				System.out.println(ms.getSender()+" ��  "+ms.getGetter()+" ˵   "+ms.getCon());
				//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if(ms.getMessType().equals(MessageType.message_comm_mes))
				{
					//ת��
					//ȡ�ý����˵�ͨѶ�߳�
					SerConClientThread sc = ManageClientThread.getClientThread(ms.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(ms);
				}else if(ms.getMessType().equals(MessageType.message_req_onlineFriend))
				{
					//���ڷ������ĺ��Ѹ��ͻ��˷���
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
					//���ڷ������ĺ��Ѹ��ͻ��˷���
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
