/**
 * ������
 */
package com.test1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.common.User;

public class MyServer {

	public static void main(String[] args)
	{
		MyServer ms = new MyServer();
	}
	
	public MyServer()
	{
		try {
			
			ServerSocket ss = new ServerSocket(3456);
			System.out.println("��3456����");
			Socket s = ss.accept();
			
			//�Զ�������ȡ����
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			User u = (User)ois.readObject();
			
			//���
			System.out.println("�ӿͻ��˽��յ�  "+u.getName()+"   "+u.getPass());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
