/**
 * 对象流
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
			System.out.println("在3456监听");
			Socket s = ss.accept();
			
			//以对象流读取数据
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			User u = (User)ois.readObject();
			
			//输出
			System.out.println("从客户端接收到  "+u.getName()+"   "+u.getPass());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
