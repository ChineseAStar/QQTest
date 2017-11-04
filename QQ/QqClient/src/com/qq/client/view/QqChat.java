/**
 * 这是与好友聊天的界面
 * 被动的由ClientConServerThread线程类调用显示聊天信息
 */
package com.qq.client.view;

import javax.swing.*;
import com.qq.client.model.*;
import com.qq.common.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import com.qq.client.tools.*;

public class QqChat extends JFrame implements ActionListener{

	JPanel jp;
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	String ownerId;
	String friendId;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qt = new QqChat("1");
	}

	public QqChat(String ownerId,String friend)
	{
		this.ownerId = ownerId;
		this.friendId = friend;
		
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp, "South");
		this.setLocation(700, 0);
		this.setTitle(friend);
		this.setIconImage(new ImageIcon("images/qq.gif").getImage());
		this.setSize(300, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	//写一个方法，让他显示消息
	public void showMessage(Message m)
	{
		String info = m.getSender()+" 说:"+"\r\n"+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jb)
		{
			//如果用户点击了发送按钮
			Message m = new Message();
			m.setMessType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			String info = m.getSender()+" 对   "+m.getGetter()+" 说   "+m.getCon()+"\r\n";
			this.jta.append(info);
			
			//发送给服务器
			ManageClientConServerThread.getClientConServerThread(ownerId).sendInfo(m);
		}
	}
}
