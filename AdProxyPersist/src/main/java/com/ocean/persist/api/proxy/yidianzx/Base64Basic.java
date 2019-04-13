package com.ocean.persist.api.proxy.yidianzx;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Base64Basic {
    public static final String UTF8 = "UTF-8";
    public static String encodeWithHmacSha1(String key, String value) {
        String result = null;
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());


            result = Base64.getUrlEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encode(byte[] textByte, char[] toBase64) {
        String encodedText = null;
        try {
            Base64TableJDK.Encoder encoder = Base64TableJDK.getEncoder().withoutPadding();
            encodedText = new String(encoder.encode(textByte, toBase64), UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedText;
    }

}
