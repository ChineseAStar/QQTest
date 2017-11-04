/**
 * �������������Ľ���
 * ��������ClientConServerThread�߳��������ʾ������Ϣ
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
		jb = new JButton("����");
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
	
	//дһ��������������ʾ��Ϣ
	public void showMessage(Message m)
	{
		String info = m.getSender()+" ˵:"+"\r\n"+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jb)
		{
			//����û�����˷��Ͱ�ť
			Message m = new Message();
			m.setMessType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			String info = m.getSender()+" ��   "+m.getGetter()+" ˵   "+m.getCon()+"\r\n";
			this.jta.append(info);
			
			//���͸�������
			ManageClientConServerThread.getClientConServerThread(ownerId).sendInfo(m);
		}
	}
}
