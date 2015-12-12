package com.alimuya.wxlife;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alimuya.utils.ecrypt.EncryptUtils;

@Controller
@RequestMapping("/facade")
public class FacadeController {
	public static final String CHECK_TOKEN="alimuya_test_check_token";
	public Logger log=Logger.getLogger(FacadeController.class);
	
	@RequestMapping("/v1")
	@ResponseBody
	public String facade(String signature,String timestamp, String nonce, String echostr){
		boolean checkResult = this.legalCheck(signature, timestamp, nonce);
		log.info("facade checkResult : "+checkResult);
		return checkResult?echostr:null;
	}
	
	
	private boolean legalCheck(String signature,String timestamp, String nonce){
		log.info("signature : "+signature);
		log.info("timestamp : "+timestamp);
		log.info("nonce : "+nonce);
		if(signature==null || timestamp==null ||nonce==null){
			return false;
		}
		String [] ps=new String []{CHECK_TOKEN,timestamp,nonce};
		Arrays.sort(ps);
		String key=ps[0]+ps[1]+ps[2];
		String newSignature=EncryptUtils.sha1(key);
		return newSignature.equals(signature);
	}
}
