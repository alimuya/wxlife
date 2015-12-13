package com.alimuya.wxlife.access;

/**
 * @author alimuya
 * @date 2015年12月13日 下午7:21:46 
 */
public interface IAccessTokenService {

	/** 获取AccessToken ,如果测试AccessToken没有从服务器获取,则返回空
	 * @return
	 */
	String getAccessTokenStr();

	/**获取AccessToken ,如果测试AccessToken没有从服务器获取,则阻塞
	 * @param timeout 阻塞时长.单位毫秒.  如果timeout<=0. 则直到阻塞到服务器获取到位置. 如果设置了超时时间大于0,则在等到超时时间结束时且没有获取到AccessToken,返回null
	 * @return
	 */
	String takeAccessTokenStr(long timeout);

}