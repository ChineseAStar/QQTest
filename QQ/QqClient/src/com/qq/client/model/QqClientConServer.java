/**
 * �ͻ��˺ͷ������������̣߳���֤������Ϣ����ȷ��ַ
 * �����¼�ɹ�����һ���ͻ����߳�
 * ����½�ɹ�������Ϣ���ظ���¼����
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
	
	//����һ������
	public boolean sendInfoToServer(Object o)
	{
		boolean b = false;
		try {
			s = new Socket(ip,port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			Message ms = (Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMessType().equals(MessageType.message_succeed))
			{
				//�ʹ���һ����qq�źͷ������˱������ӵ�
				ClientConServerThread ccst = new ClientConServerThread(s);
				//������ͨѶ�߳�
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
