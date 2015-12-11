package com.alimuya.wxlife.access;

public class AccessToken {
	private int expire;
	private String token;
	
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
