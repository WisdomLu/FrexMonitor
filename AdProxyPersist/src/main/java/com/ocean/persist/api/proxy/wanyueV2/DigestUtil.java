package com.ocean.persist.api.proxy.wanyueV2;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author xuezd
 */
public class DigestUtil {

	public static String md5Hex(String data) {
		//checkNotNull(data, "The Assert must not be null.");
		return DigestUtils.md5Hex(data);
	}
	
	public static String md5Hex(byte[] data) {
		//checkNotNull(data, "The Assert must not be null.");
		return DigestUtils.md5Hex(data);
	}
	
	public static String sha1Hex(String data) {
		//checkNotNull(data, "The Assert must not be null.");
		return DigestUtils.sha1Hex(data);
	}
	
	public static String sha1Hex(byte[] data) {
		//checkNotNull(data, "The Assert must not be null.");
		return DigestUtils.sha1Hex(data);
	}
	
	public static String sha256Hex(String data) {
		//checkNotNull(data, "The Assert must not be null.");
		return DigestUtils.sha256Hex(data);
	}
	
	public static String sha256Hex(byte[] data) {
		//checkNotNull(data, "The Assert must not be null.");
		return DigestUtils.sha256Hex(data);
	}
	
}
