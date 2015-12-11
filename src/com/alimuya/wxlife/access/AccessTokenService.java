package com.alimuya.wxlife.access;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author lixin
 *
 */
@Service
public class AccessTokenService implements IAccessTokenService {
	private static final Logger logger=Logger.getLogger(AccessTokenService.class);
	private ExecutorService executorService=Executors.newFixedThreadPool(1);
	private volatile String accessTokenStr;
	private Runnable runnable=new Runnable() {
		private long MIN_SLEEP_TIME=10*1000;
		@Override
		public void run() {
			logger.info("timer runnable starting....");
			while(true){
				try{
					AccessToken token = AccessTokenService.this.getTokenFromWeixin();
					logger.info("get token : "+token+"--"+Thread.currentThread().getId());
					if(token==null){
						Thread.sleep(MIN_SLEEP_TIME);
					}else{
						int expire = token.getExpire(); 
						long sleepTime=expire>MIN_SLEEP_TIME?expire:MIN_SLEEP_TIME;
						AccessTokenService.this.accessTokenStr=token.getToken();
						Thread.sleep(sleepTime);
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
			
		} catch (Exception e) {
			logger.error("getTokenFromWeixin error", e);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.alimuya.wxlife.access.IAccessTokenService#getAccessTokenStr()
	 */
	@Override
	public String getAccessTokenStr(){
		return this.accessTokenStr;
	}
	
}
