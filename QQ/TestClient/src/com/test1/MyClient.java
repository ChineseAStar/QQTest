/**
 * ������
 */
package com.test1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.common.User;

public class MyClient {

	public static void main(String[] args)
	{
		MyClient mc = new MyClient();
	}
	
	public MyClient()
	{
		try {
			
			Socket s = new Socket("127.0.0.1",3456);
			
			//ͨ��ObjectOutputStream�����������Ͷ���
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			User u = new User();
			u.setName("������");
			u.setPass("123456");
			oos.writeObject(u);
			System.out.println("ok");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
