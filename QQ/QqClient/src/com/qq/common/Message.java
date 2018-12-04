/**
 * 信息包类
 */
package com.qq.common;

public class Message implements java.io.Serializable {

	/**
	 * 类别从MessageType接口类中获得
	 */
	private String messType;
	/**
	 * 0：服务器
	 */
	private String sender;
	private String getter;
	private String con;
	private String sendTime;

	public String getMessType() {
		return messType;
	}

	public void setMessType(String messType) {
		this.messType = messType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}
