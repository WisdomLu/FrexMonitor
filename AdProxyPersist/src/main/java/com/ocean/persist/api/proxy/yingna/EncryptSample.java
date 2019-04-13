
package com.ocean.persist.api.proxy.yingna;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptSample {

    static final String algorithmStr = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     */
    public static String encode(String content) {
        return parseByte2HexStr(encrypt(content));
    }

    /**
     * 解密
     */
    public static String decode(String content, String password) {
        byte[] b = decrypt(parseHexStr2Byte(content), password);
        return new String(b);
    }

    private static byte[] encrypt(String content) {
        try {
            byte[] keyStr = getKey("wina012300000000");
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(algorithmStr);
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance(algorithmStr);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getKey(String password) {
        byte[] rByte = null;
        if (password != null) {
            rByte = password.getBytes();
        } else {
            rByte = new byte[24];
        }
        return rByte;
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        
        return result;
    }

    public static void main(String[] args) throws Exception {
        String param = "code_id=a10001&os_ver=5.1.1&app_ver=7.0&imei=867068020992938&mac=9C:99:A0:FF:E9:15&android_id=eedbdb39f66910a4&sw=1440&sh=2560&ot=1&ct=100";
        String reqUrl = "http://adtest.winasdaq.com/v/v.do?p=" + encode(param);
        System.out.println(reqUrl);
      //result:
      //http://adtest.winasdaq.com/v/v.do?p=4BFC51833EC459F5B87A07D22EBB8145DD207947F3EF624AE35AA577069790360C8FCBFB9D46B1BB8569772C075E25EAE152502DA1B97ECE909DC42B41FD49933398E4804D66DCDAF31963125FA3B81A36C60F0000E6500C246F031805EF72B899F5D9A2E28FBA51AE101C9D7D61A3A60BEFC0830F9985B4E51F64137ACBFD130139B12F654A4478DC6208F77DE3C5AE
    }
}
