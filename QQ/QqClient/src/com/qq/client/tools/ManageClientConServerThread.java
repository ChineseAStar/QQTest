/**
 * 这是一个管理客户端和服务器保持通讯线程的类
 * 提供给其他类一个管理客户端连接服务器的ClientConServerThread的工具
 */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.model.ClientConServerThread;
public class ManageClientConServerThread {

	 private static HashMap hm = new HashMap<String,ClientConServerThread>();
	
	//把创建好的ClientConServerThread放入到hm
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//可以通过qqId取得该线程
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
	
	//删除ClientConServerThread，貌似用不到
	public static void delClientConServerThread(String qqId)
	{
		hm.remove(qqId);
	}
	
}
