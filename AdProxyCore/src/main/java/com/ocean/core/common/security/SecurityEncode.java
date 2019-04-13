package com.ocean.core.common.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityEncode {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityEncode.class);
	
	private static final String AES_TRANSFORMATION_DEFAULT = "AES/CBC/PKCS5Padding";
	
	static {
		//初始化BouncyCastle加密库支持PKCS7
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
	}

	// public static void main(String[] args) {
	// String a="123";
	// String b=encoderByDES(a, "123");
	// String c="";
	// try {
	// c = decoderByDES(b, "123");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println(a);
	// System.out.println(b);
	// System.out.println(c);
	// }

	/** */
	/**
	 * MD5方式加密字符串
	 * 
	 * @param str
	 *            要加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @author yayagepei
	 * @date 2007-10-31
	 * @comment 程序的价值体现在两个方面:它现在的价值,它未来的价值
	 */
	// public static String encoderByMd5(String str)
	// throws NoSuchAlgorithmException, UnsupportedEncodingException {
	// // 确定计算方法
	// MessageDigest md5 = MessageDigest.getInstance("MD5");
	// BASE64Encoder base64en = new BASE64Encoder();
	// // 加密后的字符串
	// String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
	// return newstr;
	// }

	/** */
	/**
	 * DES加解密
	 * 
	 * @param plainText
	 *            要处理的byte[]
	 * @param key
	 *            密钥
	 * @param mode
	 *            模式
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 * @author yayagepei
	 * @date 2008-10-8
	 */
	private static byte[] coderByDES(byte[] plainText, String key, int mode)
			throws InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			BadPaddingException, IllegalBlockSizeException,
			UnsupportedEncodingException {
		SecureRandom sr = new SecureRandom();
		byte[] resultKey = makeKey(key);
		DESKeySpec desSpec = new DESKeySpec(resultKey);
		SecretKey secretKey = SecretKeyFactory.getInstance("DES")
				.generateSecret(desSpec);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(mode, secretKey, sr);
		return cipher.doFinal(plainText);
	}
    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws InvalidAlgorithmParameterException 
     * @throws Exception 
     */
/*    public static String encode(String data,String key) {
    	if(data == null)
    		return null;
    	try{
	    	DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));	    	
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        //key的长度不能够小于8位字节
	        Key secretKey = keyFactory.generateSecret(dks);
	        Cipher cipher = Cipher.getInstance("DES");
	        SecureRandom sr = new SecureRandom();
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);           
	        byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));            
	        return byte2hex(bytes);
    	}catch(Exception e){
    		e.printStackTrace();
    		return data;
    	}
    }

	*//**
	 * 二行制转字符串
	 * @param b
	 * @return
	 *//*
    private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b!=null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}*/
	/** */
	/**
	 * 生产8位的key
	 * 
	 * @param key
	 *            字符串形式
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author yayagepei
	 * @date 2008-10-8
	 */
	private static byte[] makeKey(String key)
			throws UnsupportedEncodingException {
		byte[] keyByte = new byte[8];
		byte[] keyResult = key.getBytes("UTF-8");
		for (int i = 0; i < keyResult.length && i < keyByte.length; i++) {
			keyByte[i] = keyResult[i];
		}
		return keyByte;
	}

	/** */
	/**
	 * DES加密
	 * 
	 * @param plainText
	 *            明文
	 * @param key
	 *            密钥
	 * @return
	 * @author yayagepei
	 * @date 2008-10-8
	 */
	public static String encoderByDES(String plainText, String key) {
		try {
			byte[] result = coderByDES(plainText.getBytes("UTF-8"), key,
					Cipher.ENCRYPT_MODE);
			return byteArr2HexStr(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/** */
	/**
	 * DES解密
	 * 
	 * @param secretText
	 *            密文
	 * @param key
	 *            密钥
	 * @return
	 * @author yayagepei
	 * @throws Exception
	 * @date 2008-10-8
	 */
	public static String decoderByDES(String secretText, String key)
			throws Exception {
		byte[] result = coderByDES(hexStr2ByteArr(secretText), key,
				Cipher.DECRYPT_MODE);
		return new String(result, "UTF-8");
	}

	/** */
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 */
	private static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/** */
	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws NumberFormatException
	 */
	private static byte[] hexStr2ByteArr(String strIn)
			throws NumberFormatException {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
/*    public static String decode(String data,String key) {
    	if(data == null)
    		return null;
        try {
	    	DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(hex2byte(data.getBytes("UTF-8"))));
        } catch (Exception e){
    		e.printStackTrace();
    		return data;
        }
    }
    private static byte[] hex2byte(byte[] b) {
        if((b.length%2)!=0)
            throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
		    String item = new String(b,n,2);
		    b2[n/2] = (byte)Integer.parseInt(item,16);
		}
        return b2;
    }*/
	public static void main(String args[]){
		String json="{"
			+"\"channel\": 3,"
			+"\"token\": \"d0646d7945ea4cf0b1338488820b65bf\","
			+"\"imp\": {"
			   +"\"adSlotId\": 72,"
			   +"\"eventime\": 1476418318806,"
			   +"\"viewScreen\": 0,"
			   +"\"adType\": 4,"
			   +"\"keys\": \"军事小说\","
			   +"\"ext\": null"
			+"},"
			+"\"device\": {"
			            +"\"ua\": \"TW96aWxsYS81LjAgKExpbnV4OyBBbmRyb2lkIDQuNC40OyBTSzMtMDEgQnVpbGQvS1RVODRQKSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBWZXJzaW9uLzQuMCBDaHJvbWUvMzMuMC4wLjAgTW9iaWxlIFNhZmFyaS81MzcuMzY=\","
			            +"\"client_ip\": \"220.122.178.5\","
			            +"\"devicetype\": 1,"
			            +"\"brand\": \"samsung\","
			            +"\"telModel\": \"\","
			            +"\"os_version\": \"v7.0.2\","
			            +"\"device_pixel_ratio\": 1000,"
			            +"\"screen_orientation\": 0,"
			            +"\"imei\": \"869560026230923\","
			            +"\"idfa\": \"\","
			            +"\"mac\": \"8cd17bb2af55\","
			            +"\"operator\": 1,"
			            +"\"network\": 1,"
			            +"\"ext\": null"
		+"},"
		+"\"app\": {"
		      +"\"bundle\":\"com.test.cm\","
		      +"\"category\": 1001,"
		      +"\"keywords\": \"应用娱乐\""
		+"},"
		+"\"user\": {"
		      +"\"gender\": 1,"
		      +"\"interest\": \"football\","
		      +"\"age\": 19,"
		      +"\"longitude\": \"106.2170460000\","
		      +"\"latitude\": \"37.3769780000\""
		+"},"
		      +"\"isTest\": 1,"
		      +"\"userInfo\": \"this is a test user\","
		      +"\"ext\": \"\""
		+"}";
		
		String result= SecurityEncode.encoderByDES(json,"d0646d7945ea4cf0b1338488820b65bf");
		System.out.println("result:"+result);
		try {
			System.out.print("result:"+SecurityEncode.decoderByDES(result,"d0646d7945ea4cf0b1338488820b65bf"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
     * 对字符串进行aes加密
     * @param rawStr
     * @param key
     * @param iv
     * @return
	 * @throws Throwable 
     */
    public static byte[] AESEncode(byte [] rawStr, String key, String iv, String algorithm) throws Throwable {
    	if(StringUtils.isEmpty(algorithm)) {
    		algorithm = AES_TRANSFORMATION_DEFAULT;
    	}
        try {
            IvParameterSpec ivParam = null;
            if (null != iv && !iv.isEmpty()) {
                ivParam = new IvParameterSpec(generateIV(iv));
            }

            byte[] strBytes = key.getBytes("utf-8");
            SecretKeySpec cipherKey = new SecretKeySpec(strBytes, "AES");
            
            Cipher encodeCipher = Cipher.getInstance(algorithm);
            if (null != ivParam) {
                encodeCipher.init(Cipher.ENCRYPT_MODE, cipherKey, ivParam);
            } else {
                encodeCipher.init(Cipher.ENCRYPT_MODE, cipherKey);
            }
            return encodeCipher.doFinal(rawStr);
        } catch (Throwable e) {
        	logger.error("AES encode error:", e);
        	throw e;
        }
    }
    
    public static byte[] AESDecode(byte [] rawStr, String key, String iv, String algorithm) throws Throwable {
    	if(StringUtils.isEmpty(algorithm)) {
    		algorithm = AES_TRANSFORMATION_DEFAULT;
    	}
        try {  
        	IvParameterSpec ivParam = null;
            if (null != iv && !iv.isEmpty()) {
                ivParam = new IvParameterSpec(generateIV(iv));
            }

            byte[] strBytes = key.getBytes("utf-8");
            SecretKeySpec cipherKey = new SecretKeySpec(strBytes, "AES");
            Cipher decodeCipher = Cipher.getInstance(algorithm);
            if (null != ivParam) {
                decodeCipher.init(Cipher.DECRYPT_MODE, cipherKey, ivParam);
            } else {
                decodeCipher.init(Cipher.DECRYPT_MODE, cipherKey);
            }
            return decodeCipher.doFinal(rawStr);
        } catch (Throwable e) {
        	logger.error("AES decode error:", e);
        	throw e;
        } 
    }

    public static byte[] generateIV(String iv) {
        if (null == iv || iv.isEmpty()) {
            return null;
        }
        try {
            byte[] ivBytes = iv.getBytes("utf-8");
            return Arrays.copyOf(ivBytes, 16);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
	public static byte[] gzip(byte[] reqbytes) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(reqbytes);
            gzip.flush();
            gzip.close();
        } catch (Throwable e) {
        	logger.error("gzip error:", e);
        } finally {
        	if(gzip != null) {
        		try {
					gzip.close();
				} catch (IOException e) {
					logger.error("gzip close error:", e);
				}
        	}
        }
        return out.toByteArray();
	}
	
	public static byte[] degzip(byte[] bytes) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		GZIPInputStream ungzip = null;;
        try {
        	ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Throwable e) {
        	logger.error("degzip error:", e);
        } finally {
        	if(ungzip != null) {
        		try {
        			ungzip.close();
				} catch (IOException e) {
					logger.error("de gzip close error:", e);
				}
        	}
        }
        return out.toByteArray();
	}

//    public static String parseByte2HexStr(byte buf[]) {  
//        if (null == buf) {
//            return null;
//        }
//        StringBuffer sb = new StringBuffer();  
//        for (int i = 0; i < buf.length; i++) {  
//            String hex = Integer.toHexString(buf[i] & 0xFF);  
//            if (hex.length() == 1) {  
//                hex = '0' + hex;  
//            }  
//            sb.append(hex.toUpperCase());  
//        }  
//        return sb.toString();  
//    }
	
	public static String md5Encode(String inStr) {
		if(StringUtils.isEmpty(inStr)) {
			return inStr;
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = (md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (Exception e) {
			logger.error("md5 encode error: " + inStr);
			return "";
		}
	}
}