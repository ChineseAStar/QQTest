/**
 * ���ǿͻ��˺ͷ���˱���ͨѶ���߳�
 * ���ܣ��������з�������������Ϣ���б𲢴������ͬ����
 */
package com.qq.client.model;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.client.view.*;
import com.qq.common.*;
public class ClientConServerThread extends Thread {

	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
	//���캯��
	public ClientConServerThread(Socket s)
	{
		this.s = s;
	}
	
	//�ж����������Ƿ�����
	public boolean checkLine()
	{
		try{
            s.sendUrgentData(0xFF);
            return true;
        }catch(Exception e){
        	JOptionPane.showInputDialog("���������ӣ�", this);
        	this.stop();
            return false;
        }
	}
	
	//������Ϣ����
	public void sendInfo(Message ms)
	{
		if(checkLine())
		{
			try {
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(ms);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//�̺߳���
	public void run()
	{
		while(checkLine())
		{
			//��ͣ�ض�ȡ�ӷ������˷�������Ϣ
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				System.out.println("��ȡ���ӷ���������Ϣ"+m.getSender()+" ��   "
				+m.getGetter()+" ����   "+m.getCon());
				
				if(m.getMessType().equals(MessageType.message_comm_mes))
				{
					//�Ѵӷ�������õ���Ϣ����ʾ������ʾ���������
					QqChat qqchat = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					qqchat.showMessage(m);
				}else if(m.getMessType().equals(MessageType.message_ret_onlineFriend))
				{
					String getter = m.getGetter();
					//�޸���Ӧ�ĺ����б�
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
					
					//�������ߺ���
					if(qqFriendList!=null)
					{
						qqFriendList.updateFriend(m);
					}
				}else if(m.getMessType().equals(MessageType.message_ret_Friend))
				{
					String getter = m.getGetter();
					//�޸���Ӧ�ĺ����б�
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
					
					//�������ߺ���
					if(qqFriendList!=null)
					{
						qqFriendList.cFriendList(m);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
