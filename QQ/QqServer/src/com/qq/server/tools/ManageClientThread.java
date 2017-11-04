/**
 * 服务器端管理与客户端链接的SerConClientThread类
 */
package com.qq.server.tools;

import java.util.*;

import com.qq.server.bnmodel.SerConClientThread;
public class ManageClientThread {

	public static HashMap hm = new HashMap<String,SerConClientThread>();
	
	//向hm中添加一个客户端通讯线程
	public static void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid,ct);
	}
	
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	public static void delClientThread(String uid,SerConClientThread ct)
	{
		hm.remove(uid, ct);
	}
	
	//返回当前在线的人的情况
	public static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext())
		{
			res += it.next().toString()+" ";
		}
		return res;
	}
	
}
