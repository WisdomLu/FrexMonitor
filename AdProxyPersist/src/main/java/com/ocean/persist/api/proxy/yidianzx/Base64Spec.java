package com.ocean.persist.api.proxy.yidianzx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by a1 on 16/10/10.
 */
public class Base64Spec {

    private static final Logger LOG = LoggerFactory.getLogger(Base64Spec.class);
	private static final int SEC_TYPE_KEY=12;
    private static final String UTF8 = "UTF-8";
    private static final Map<Integer, char[]> tableInMap = new ConcurrentHashMap<>();
    private static final Map<Integer, int[]> tableOutMap = new ConcurrentHashMap<>();


    static {
        init(SEC_TYPE_KEY, "OlVgiNyt5CaI6Ws4GFeLdj7BrSY_09nkvRTmhbHq2puXc8Dx3P-ZwKMAJQzE1fUo");
    }

    public static void init(Integer key, String table) {
        char[] tableIn = table.toCharArray();
        int[] tableOut = new int[256];
        Arrays.fill(tableOut, -1);
        for (int i = 0; i < tableIn.length; i++)
            tableOut[tableIn[i]] = i;
        tableOut['='] = -2;

        tableInMap.put(key, tableIn);
        tableOutMap.put(key, tableOut);
    }

    public static void remove(Integer key) {
        if (tableInMap.containsKey(key)) {
            tableInMap.remove(key);
        }
        if (tableOutMap.containsKey(key)) {
            tableOutMap.remove(key);
        }
    }

    public static Set<Integer> getKeySet() {
        return tableInMap.keySet();
    }

    public static String encode(String content, int key) {
        String encodedText = null;
        try {
            final byte[] textByte = content.getBytes(UTF8);
            encodedText = encode(textByte, key);
        } catch (Exception e) {
            LOG.error("error is {} ", e);
        }
        return encodedText;
    }

    public static String encode(byte[] textByte, int key) {
        String encodedText = null;
        try {
            char[] toBase64 = tableInMap.get(key);
            Base64TableJDK.Encoder encoder = Base64TableJDK.getEncoder().withoutPadding();
            encodedText = new String(encoder.encode(textByte, toBase64), UTF8);
//            encodedText = Base64TableSimple.encode(textByte, toBase64);
        } catch (Exception e) {
            LOG.error("error is {} ", e);
        }
        return encodedText;
    }


    public static String decode(String content, int key) {
        String decodedText = null;
        try {
            int[] fromBase64 = tableOutMap.get(key);
            Base64TableJDK.Decoder decoder = Base64TableJDK.getDecoder();
            decodedText = new String(decoder.decode(content, fromBase64), UTF8);
//            decodedText = new String(Base64TableSimple.decode(content, fromBase64), UTF8);
        } catch (Exception e) {
            LOG.error("error is {} ", e);
        }
        return decodedText;
    }

    public static byte[] decodeByte(String content, int key) {
        byte[] decodedText = null;
        try {
            int[] fromBase64 = tableOutMap.get(key);
            Base64TableJDK.Decoder decoder = Base64TableJDK.getDecoder();
            decodedText = decoder.decode(content, fromBase64);
//            decodedText = Base64TableSimple.decode(content, fromBase64);
        } catch (Exception e) {
            LOG.error("error is {} ", e);
        }
        return decodedText;
    }

    public static char[] getTableIn(Integer key) {
        return tableInMap.get(key);
    }

    public static int[] getTableOut(Integer key) {
        return tableOutMap.get(key);
    }


}
