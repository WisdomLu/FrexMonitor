package com.ocean.core.common.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
/**
 * DES对称加密算法
 * @author kongqz
 * */
public class DESCoder {
	
	 /** 
     * 密钥算法 
     * java支持56位密钥，bouncycastle支持64位 
     * */  
    public static final String KEY_ALGORITHM = "DES";  
      
    /** 
     * 加密/解密算法/工作模式/填充方式 
     * */  
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    
	/**
     * 根据参数生成Key;
     * 
     * @param strKey
     */
    private static Key getKey(String strKey) {
        Key key = null;
        try {
            KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
            generator.init(new SecureRandom(strKey.getBytes()));
            key = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }
 
    /**
     * 获得一次3DES加密后的密文
     * 
     * @param
     * @return strMi
     */
    public static String getEncString(String strMing, String strKey) {
       
        byte[] byteMing = strMing.getBytes();
        Key key = getKey(strKey);
        try {
        	Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteMi = cipher.doFinal(byteMing);
            return Base64.encodeBase64String(byteMi);
		} catch (Exception e) {
			throw new RuntimeException("加密异常：" + e.getMessage());
		}
    }
    
    /**
     * 获得一次3DES解密后的明文
     * 
     * @param
     * @return strMing
     */
    public static String getDecString(String strMi, String strKey) {
        byte[] byteMi = Base64.decodeBase64(strMi);
        Key key = getKey(strKey);
        try {
        	Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] byteFina = cipher.doFinal(byteMi);
            return new String(byteFina);
		} catch (Exception e) {
			throw new RuntimeException("解密密异常：" + e.getMessage());
		}
    }
 
    public static void main(String[] args) {
//        Key k = getKey("aca910e079b89a7584e83de1cb914eae");
//        System.out.println("获得的密钥key是:" + k);
//        String encyStr = getEncString("Test", "Key");
//        System.out.println("一次加密后的密文是:" + encyStr);
//        System.out.println(SecurityEncode.encoderByDES("Test", "aca910e079b89a7584e83de1cb914eae"));
//        String decyStr = getDecString(encyStr, "Key");
//        System.out.println("一次解密后的明文是:" + decyStr);
    	
    	String ua = Base64.encodeBase64String("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36".getBytes());
    	System.out.println(ua);
    }
}