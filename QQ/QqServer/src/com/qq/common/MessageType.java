/**
 * 定义包的中类
 */
package com.qq.common;

public interface MessageType {
	String message_succeed = "1";//表明是登录成功
	String message_login_fail = "2";//表明登录失败
	String message_comm_mes = "3";//普通信息包
	String message_req_onlineFriend = "4";//要求在线好友
	String message_ret_onlineFriend = "5";//返回在线好友
	String message_req_Friend = "6";//要求好友信息
	String message_ret_Friend = "7";//返回好友信息
}
