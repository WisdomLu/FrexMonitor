package com.ocean.persist.api.proxy.wanyueV2;
public class SignUtil {

    public static String sign(String json, String secretKey) {
        return DigestUtil.sha1Hex(json + secretKey);
    }

    public static boolean verify(String json, String secretKey, String sign) {
        String mySign = sign(json, secretKey);
        return mySign.equals(sign);
    }

}
