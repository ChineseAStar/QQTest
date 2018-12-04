/**
 * 1：登录成功
 * 2：登录失败
 */
package com.qq.common;

public class User implements java.io.Serializable {

	private String userId;
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
