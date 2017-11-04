/**
 * ����qq�����������ڼ������ȴ�ĳ��QQ�ͻ��������Ӳ��жϵ�¼��Ϣ�Ƿ���ȷ
 * ����½�ɹ���SerConClientThread���뵽 ManageClientThread
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
			//��9999����
			System.out.println("���Ƿ���������"+port+"����");
			ss = new ServerSocket(port);
			while(b)
			{
				//�������ȴ�����
				s = ss.accept();
				
				if(s != null)
				{
					System.out.println("test");
					//���տͻ��˷�������Ϣ
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					User u = (User)ois.readObject();
		
					System.out.println("���������յ����û�ID������   "+ u.getUserId()+"\t"+u.getPassword());
					Message m = new Message();
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					if(new UserModel().checkUser(u.getUserId(), u.getPassword()))
					{
						//����һ���ɹ���½����Ϣ��
						m.setMessType(MessageType.message_succeed);
						oos.writeObject(m);
						
						//���ﵥ��һ���̣߳���ÿͻ��˱�����ϵ
						SerConClientThread scct = new SerConClientThread(s);
						ManageClientThread.addClientThread(u.getUserId(), scct);
						//������Ŀͻ���ͨ�ŵ��߳�
						scct.start();
						
						//��֪ͨ���������û�
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
	
	//�ر��߳�
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
