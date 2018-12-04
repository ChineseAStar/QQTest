/**
 * ����һ������ͻ��˺ͷ���������ͨѶ�̵߳���
 * �ṩ��������һ������ͻ������ӷ�������ClientConServerThread�Ĺ���
 */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.model.ClientConServerThread;
public class ManageClientConServerThread {

	 private static HashMap hm = new HashMap<String,ClientConServerThread>();
	
	//�Ѵ����õ�ClientConServerThread���뵽hm
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//����ͨ��qqIdȡ�ø��߳�
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
	
	//ɾ��ClientConServerThread��ò���ò���
	public static void delClientConServerThread(String qqId)
	{
		hm.remove(qqId);
	}
	
}
