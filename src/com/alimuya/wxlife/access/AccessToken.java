package com.alimuya.wxlife.access;

/**
 * @author alimuya
 * @date 2015年12月12日 下午11:55:29 
 */
public class AccessToken {
	private String token;
	
	public AccessToken(String token, int expire) {
		this.token = token;
		this.expire = expire;
	}

	public AccessToken(){}
	
	private int expire;
	
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "AccessToken [expire=" + expire + ", token=" + token + "]";
	}
	
}
