/**
 * ��Ϣ����
 */
package com.qq.common;

public class Message implements java.io.Serializable {

	/**
	 * ����MessageType�ӿ����л��
	 */
	private String messType;
	/**
	 * 0��������
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
