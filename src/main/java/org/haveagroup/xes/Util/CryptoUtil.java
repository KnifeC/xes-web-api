package org.haveagroup.xes.Util;


import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class CryptoUtil {

    public static Key DEFAULT_KEY = null;

    public static final String DEFAULT_SECRET_KEY = "1qaz2wsx3edc$RFV%TGB^haveAAGroupPassword";

    public static final String DES = "DES";

    static {
        DEFAULT_KEY = obtainKey(DEFAULT_SECRET_KEY);
    }

    public static Key obtainKey(String key) {
        if (key == null) {
            return DEFAULT_KEY;
        }
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.init(new SecureRandom(key.getBytes()));
        Key key1 = generator.generateKey();
        generator = null;
        return key1;
    }

    public String encode(String str) {
        return encode(null, str);
    }

    public String encode(String key, String str) {
        return Base64.encodeBase64URLSafeString(obtainEncode(key, str.getBytes()));
        // return Hex.encodeHexString(obtainEncode(key, str.getBytes()));
        // 可以转化为16进制数据
    }

    public static String decode(String str) {
        return decode(null, str);
    }

    public static String decode(String key, String str) {
        return new String(obtainDecode(key, Base64.decodeBase64(str)));
        //可以转化为16进制的数据
//      try {
//          return new String(obtainDecode(key, Hex.decodeHex(str.toCharArray())));
//      } catch (DecoderException e) {
//          e.printStackTrace();
//      }
    }

    /**
     * 加密<br>
     * 以byte[]明文输入,byte[]密文输出
     */
    private static byte[] obtainEncode(String key, byte[] str) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 解密<br>
     * 以byte[]密文输入,以byte[]明文输出
     */
    private static byte[] obtainDecode(String key, byte[] str) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.DECRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

}

