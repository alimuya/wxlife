package com.alimuya.utils.ecrypt;


import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * @author alimuya
 * @date 2015年12月2日 下午9:21:37 
 */
public class EncryptUtils {
	private static final String CHARSET_NAME = "utf-8";
	
	public static String sha1(byte[] byteArray) {
		if (byteArray == null) {
			throw new NullPointerException();
		}
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");
			byte[] shaBytes = sha.digest(byteArray);
			StringBuilder hexValue = new StringBuilder();
			for (int i = 0; i < shaBytes.length; i++) {
				int val = ((int) shaBytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (Exception e) {
			throw new EcryptRuntimeException(e);
		}
	}
	
	public static String sha1(String content) {
		if(content==null){
			throw new NullPointerException();
		}
		try{
			return sha1(content.getBytes(CHARSET_NAME));
		}catch(Exception e){
			throw new EcryptRuntimeException(e);
		}
	}
	
	public static String md5(byte[] byteArray) {
		if(byteArray==null){
			throw new NullPointerException();
		}
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuilder hexValue = new StringBuilder();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		}catch(Exception e){
			throw new EcryptRuntimeException(e);
		}
	}
	
	public static String md5(String content) {
		if(content==null){
			throw new NullPointerException();
		}
		try{
			return md5(content.getBytes(CHARSET_NAME));
		}catch(Exception e){
			throw new EcryptRuntimeException(e);
		}
	}
	
	public static String base64Encode(byte[] bytes){
		return bytes==null?null:Base64.encodeBase64String(bytes);
	}
	
	public static byte[] base64Decode(String base64Code) throws Exception{
		return base64Code==null ? null : Base64.decodeBase64(base64Code);
	}
	
	
	public static byte[] AESEncrypt(byte[] content, byte[] encryptKey)  {
		if(content==null || encryptKey==null){
			throw new NullPointerException();
		}
		try{
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(encryptKey));
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			return cipher.doFinal(content);
		}catch(Exception e){
			throw new EcryptRuntimeException(e);
		}
	}

	public static String AESEncrypt(String content, String encryptKey) {
		if (content == null || encryptKey == null) {
			throw new NullPointerException();
		}
		try {
			byte[] bs = AESEncrypt(content.getBytes(CHARSET_NAME),encryptKey.getBytes(CHARSET_NAME));
			return base64Encode(bs);
		} catch (Exception e) {
			throw new EcryptRuntimeException(e);
		}
	}
	
	
	
    
	
	
	public static byte[] AESDecrypt(byte[] encryptBytes, byte[] decryptKey){
		if(encryptBytes==null || decryptKey==null){
			throw new NullPointerException();
		}
		try{
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(decryptKey));
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			byte[] decryptBytes = cipher.doFinal(encryptBytes);
			return decryptBytes;
		}catch(Exception e){
			throw new EcryptRuntimeException(e);
		}
		
	}
	
	public static String AESDecode(String encryptStr, String decryptKey) {
		if(encryptStr==null || decryptKey==null){
			throw new NullPointerException();
		}
		try{
			byte[] bs = AESDecrypt(base64Decode(encryptStr), decryptKey.getBytes(CHARSET_NAME));
			return new String(bs,CHARSET_NAME);
		}catch(Exception e){
			throw new EcryptRuntimeException(e);
		}
	}
	
	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}

