package com.qq.server.tools;

import java.util.*;

import com.qq.server.bnmodel.*;

public class ManageServer {

	private static HashMap hm = new HashMap<String,MyQqServer>();
	
	public static void addServer(String port,MyQqServer ms)
	{
		hm.put(port, ms);
	}
	
	public static void delServer(String port)
	{
		System.out.println(hm.get(port).getClass());
		((MyQqServer) hm.get(port)).closeself();
		hm.remove(port);
	}
	
}
