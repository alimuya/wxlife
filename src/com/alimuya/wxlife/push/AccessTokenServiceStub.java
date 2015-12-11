package com.alimuya.wxlife.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimuya.wxlife.access.AccessTokenService;
import com.alimuya.wxlife.access.IAccessTokenService;
@Service
public class AccessTokenServiceStub implements IAccessTokenService{
	@Autowired
	private AccessTokenService service;
	
	@Override
	public String getAccessTokenStr() {
		return this.service.getAccessTokenStr();
	}

}
