/**
 * �������˹�����ͻ������ӵ�SerConClientThread��
 */
package com.qq.server.tools;

import java.util.*;

import com.qq.server.bnmodel.SerConClientThread;
public class ManageClientThread {

	public static HashMap hm = new HashMap<String,SerConClientThread>();
	
	//��hm�����һ���ͻ���ͨѶ�߳�
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
	
	//���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid()
	{
		//ʹ�õ��������
		Iterator it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext())
		{
			res += it.next().toString()+" ";
		}
		return res;
	}
	
}
