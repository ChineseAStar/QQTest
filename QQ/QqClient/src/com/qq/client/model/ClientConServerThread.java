/**
 * 这是客户端和服务端保持通讯的线程
 * 功能：接受所有服务器发来的信息包判别并处理给不同对象
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
	
	
	//构造函数
	public ClientConServerThread(Socket s)
	{
		this.s = s;
	}
	
	//判断网络连接是否正常
	public boolean checkLine()
	{
		try{
            s.sendUrgentData(0xFF);
            return true;
        }catch(Exception e){
        	JOptionPane.showInputDialog("网络无连接！", this);
        	this.stop();
            return false;
        }
	}
	
	//发送消息函数
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
	
	//线程函数
	public void run()
	{
		while(checkLine())
		{
			//不停地读取从服务器端发来的消息
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				System.out.println("读取到从服务发来的消息"+m.getSender()+" 给   "
				+m.getGetter()+" 内容   "+m.getCon());
				
				if(m.getMessType().equals(MessageType.message_comm_mes))
				{
					//把从服务器获得的消息，显示到该显示的聊天界面
					QqChat qqchat = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					qqchat.showMessage(m);
				}else if(m.getMessType().equals(MessageType.message_ret_onlineFriend))
				{
					String getter = m.getGetter();
					//修改相应的好友列表
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
					
					//更新在线好友
					if(qqFriendList!=null)
					{
						qqFriendList.updateFriend(m);
					}
				}else if(m.getMessType().equals(MessageType.message_ret_Friend))
				{
					String getter = m.getGetter();
					//修改相应的好友列表
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
					
					//更新在线好友
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
