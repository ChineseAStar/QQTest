/**
 * 对象流
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
			
			//通过ObjectOutputStream给服务器传送对象
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			User u = new User();
			u.setName("彩云鹏");
			u.setPass("123456");
			oos.writeObject(u);
			System.out.println("ok");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
