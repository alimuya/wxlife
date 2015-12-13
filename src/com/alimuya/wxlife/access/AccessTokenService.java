package com.alimuya.wxlife.access;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author lixin
 *
 */
@Service
public class AccessTokenService implements IAccessTokenService {
	private static final Logger logger=Logger.getLogger(AccessTokenService.class);
	
	private static final String APPID="wx8a4888c6bfeb54ad";
	private static final String APPSECRET="d4624c36b6795d1d99dcf0547af5443d";
	private static final String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
	
	private ExecutorService executorService=Executors.newFixedThreadPool(1);
	private volatile String accessTokenStr;
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	private Runnable runnable=new Runnable() {
		private long MIN_SLEEP_TIME=10; //单位秒;
		@Override
		public void run() {
			logger.info("timer runnable starting....");
			while(true){
				try{
					AccessToken token = AccessTokenService.this.getTokenFromWeixin();
					logger.info("get token : "+token+"--"+Thread.currentThread().getId());
					if(token==null){
						Thread.sleep(MIN_SLEEP_TIME*1000);
					}else{
						AccessTokenService.this.lock.lock();
						AccessTokenService.this.accessTokenStr=token.getToken();
						AccessTokenService.this.condition.signalAll();
						AccessTokenService.this.lock.unlock();
						int expire = (int)(token.getExpire()*0.8);//当距离过期时间20%的时候，开始重试 
						long sleepTime=expire>MIN_SLEEP_TIME?expire:MIN_SLEEP_TIME;
						Thread.sleep(sleepTime*1000);
					}
				}catch(InterruptedException e){
					logger.info("InterruptedException");
					break;
				}catch(Exception e){
					logger.error("timer runnable run exception", e);
				}
			}
			logger.info("timer runnable ending....");
		}
	};
	
	@PostConstruct
	public void onStartup(){
		executorService.execute(runnable);
	}
	
	@PreDestroy
	public void onDestroy(){
		executorService.shutdownNow();
	}
	
	private AccessToken getTokenFromWeixin(){
		try {
			RestTemplate  template=new RestTemplate();
			AccessTokenProtocol protocol = template.getForObject(url, AccessTokenProtocol.class);
			if(protocol!=null){
				return new  AccessToken(protocol.getAccess_token(), protocol.getExpires_in());
			}
		} catch (Exception e) {
			logger.error("getTokenFromWeixin error", e);
		}
		return null;
	}

	@Override
	public String getAccessTokenStr(){
		return this.accessTokenStr;
	}
	@Override
	public String takeAccessTokenStr(long timeout){
		if(this.accessTokenStr!=null){
			return this.accessTokenStr;
		}
		try{
			lock.lock();
			if(timeout>0){
				boolean waitResult=condition.await(timeout, TimeUnit.MILLISECONDS);
				if(waitResult){
					return null;
				}
			}else{
				condition.await();
			}
			return this.accessTokenStr;
		} catch (InterruptedException e) {
			logger.error("takeAccessTokenStr Interrupted,return null", e);
			return null;
		}finally{
			lock.unlock();
		}
	}
	
	private static class AccessTokenProtocol{
		private String access_token;
		private int expires_in;
		public String getAccess_token() {
			return access_token;
		}
		@SuppressWarnings("unused")
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		public int getExpires_in() {
			return expires_in;
		}
		@SuppressWarnings("unused")
		public void setExpires_in(int expires_in) {
			this.expires_in = expires_in;
		}
	}
}
