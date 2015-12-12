package com.alimuya.wxlife;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alimuya
 * @date 2015年12月13日 上午12:41:54 
 */
public class ErrorProtocol {
	private int errcode;
	private String errmsg;
	
	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public String getErrorCodeDoc(){
		String doc=errorCodes.get(this.errcode);
		return doc!=null?doc:"未知errorCode.暂时无文档,请到微信公共号官网查询最新错误码文档";
	}
	
	private final static Map<Integer, String> errorCodes = new HashMap<Integer, String>();
	static {
		errorCodes.put(-1, "系统繁忙，此时请开发者稍候再试");
		errorCodes.put(0, "请求成功");
		errorCodes.put(40001,
						"获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口");
		errorCodes.put(40002, "不合法的凭证类型");
		errorCodes.put(40003,
				"不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID");
		errorCodes.put(40004, "不合法的媒体文件类型");
		errorCodes.put(40005, "不合法的文件类型");
		errorCodes.put(40006, "不合法的文件大小");
		errorCodes.put(40007, "不合法的媒体文件id");
		errorCodes.put(40008, "不合法的消息类型");
		errorCodes.put(40009, "不合法的图片文件大小");
		errorCodes.put(40010, "不合法的语音文件大小");
		errorCodes.put(40011, "不合法的视频文件大小");
		errorCodes.put(40012, "不合法的缩略图文件大小");
		errorCodes.put(40013, "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写");
		errorCodes.put(40014,
						"不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口");
		errorCodes.put(40015, "不合法的菜单类型");
		errorCodes.put(40016, "不合法的按钮个数");
		errorCodes.put(40017, "不合法的按钮个数");
		errorCodes.put(40018, "不合法的按钮名字长度");
		errorCodes.put(40019, "不合法的按钮KEY长度");
		errorCodes.put(40020, "不合法的按钮URL长度");
		errorCodes.put(40021, "不合法的菜单版本号");
		errorCodes.put(40022, "不合法的子菜单级数");
		errorCodes.put(40023, "不合法的子菜单按钮个数");
		errorCodes.put(40024, "不合法的子菜单按钮类型");
		errorCodes.put(40025, "不合法的子菜单按钮名字长度");
		errorCodes.put(40026, "不合法的子菜单按钮KEY长度");
		errorCodes.put(40027, "不合法的子菜单按钮URL长度");
		errorCodes.put(40028, "不合法的自定义菜单使用用户");
		errorCodes.put(40029, "不合法的oauth_code");
		errorCodes.put(40030, "不合法的refresh_token");
		errorCodes.put(40031, "不合法的openid列表");
		errorCodes.put(40032, "不合法的openid列表长度");
		errorCodes.put(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符");
		errorCodes.put(40035, "不合法的参数");
		errorCodes.put(40038, "不合法的请求格式");
		errorCodes.put(40039, "不合法的URL长度");
		errorCodes.put(40050, "不合法的分组id");
		errorCodes.put(40051, "分组名字不合法");
		errorCodes.put(40117, "分组名字不合法");
		errorCodes.put(40118, "media_id大小不合法");
		errorCodes.put(40119, "button类型错误");
		errorCodes.put(40120, "button类型错误");
		errorCodes.put(40121, "不合法的media_id类型");
		errorCodes.put(40132, "微信号不合法");
		errorCodes.put(40137, "不支持的图片格式");
		errorCodes.put(41001, "缺少access_token参数");
		errorCodes.put(41002, "缺少appid参数");
		errorCodes.put(41003, "缺少refresh_token参数");
		errorCodes.put(41004, "缺少secret参数");
		errorCodes.put(41005, "缺少多媒体文件数据");
		errorCodes.put(41006, "缺少media_id参数");
		errorCodes.put(41007, "缺少子菜单数据");
		errorCodes.put(41008, "缺少oauth code");
		errorCodes.put(41009, "缺少openid");
		errorCodes.put(42001,
						"access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明");
		errorCodes.put(42002, "refresh_token超时");
		errorCodes.put(42003, "oauth_code超时");
		errorCodes.put(43001, "需要GET请求");
		errorCodes.put(43002, "需要POST请求");
		errorCodes.put(43003, "需要HTTPS请求");
		errorCodes.put(43004, "需要接收者关注");
		errorCodes.put(43005, "需要好友关系");
		errorCodes.put(44001, "多媒体文件为空");
		errorCodes.put(44002, "POST的数据包为空");
		errorCodes.put(44003, "图文消息内容为空");
		errorCodes.put(44004, "文本消息内容为空");
		errorCodes.put(45001, "多媒体文件大小超过限制");
		errorCodes.put(45002, "消息内容超过限制");
		errorCodes.put(45003, "标题字段超过限制");
		errorCodes.put(45004, "描述字段超过限制");
		errorCodes.put(45005, "链接字段超过限制");
		errorCodes.put(45006, "图片链接字段超过限制");
		errorCodes.put(45007, "语音播放时间超过限制");
		errorCodes.put(45008, "图文消息超过限制");
		errorCodes.put(45009, "接口调用超过限制");
		errorCodes.put(45010, "创建菜单个数超过限制");
		errorCodes.put(45015, "回复时间超过限制");
		errorCodes.put(45016, "系统分组，不允许修改");
		errorCodes.put(45017, "分组名字过长");
		errorCodes.put(45018, "分组数量超过上限");
		errorCodes.put(46001, "不存在媒体数据");
		errorCodes.put(46002, "不存在的菜单版本");
		errorCodes.put(46003, "不存在的菜单数据");
		errorCodes.put(46004, "不存在的用户");
		errorCodes.put(47001, "解析JSON/XML内容错误");
		errorCodes.put(48001, "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限");
		errorCodes.put(50001, "用户未授权该api");
		errorCodes.put(50002, "用户受限，可能是违规后接口被封禁");
		errorCodes.put(61451, "参数错误(invalid parameter)");
		errorCodes.put(61452, "无效客服账号(invalid kf_account)");
		errorCodes.put(61453, "客服帐号已存在(kf_account exsited)");
		errorCodes.put(61454,"客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)");
		errorCodes.put(61455,"客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)");
		errorCodes.put(61456, "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)");
		errorCodes.put(61457, "无效头像文件类型(invalid file type)");
		errorCodes.put(61450, "系统错误(system error)");
		errorCodes.put(61500, "日期格式错误");
		errorCodes.put(61501, "日期范围错误");
		errorCodes.put(9001001, "POST数据参数不合法");
		errorCodes.put(9001002, "远端服务不可用");
		errorCodes.put(9001003, "Ticket不合法");
		errorCodes.put(9001004, "获取摇周边用户信息失败");
		errorCodes.put(9001005, "获取商户信息失败");
		errorCodes.put(9001006, "获取OpenID失败");
		errorCodes.put(9001007, "上传文件缺失");
		errorCodes.put(9001008, "上传素材的文件类型不合法");
		errorCodes.put(9001009, "上传素材的文件尺寸不合法");
		errorCodes.put(9001010, "上传失败");
		errorCodes.put(9001020, "帐号不合法");
		errorCodes.put(9001021, "已有设备激活率低于50%，不能新增设备");
		errorCodes.put(9001022, "设备申请数不合法，必须为大于0的数字");
		errorCodes.put(9001023, "已存在审核中的设备ID申请");
		errorCodes.put(9001024, "一次查询设备ID数量不能超过50");
		errorCodes.put(9001025, "设备ID不合法");
		errorCodes.put(9001026, "页面ID不合法");
		errorCodes.put(9001027, "页面参数不合法");
		errorCodes.put(9001028, "一次删除页面ID数量不能超过10");
		errorCodes.put(9001029, "页面已应用在设备中，请先解除应用关系再删除");
		errorCodes.put(9001030, "一次查询页面ID数量不能超过50");
		errorCodes.put(9001031, "时间区间不合法");
		errorCodes.put(9001032, "保存设备与页面的绑定关系参数错误");
		errorCodes.put(9001033, "门店ID不合法");
		errorCodes.put(9001034, "设备备注信息过长");
		errorCodes.put(9001035, "设备申请参数不合法");
		errorCodes.put(9001036, "查询起始值begin不合法");
	}

}