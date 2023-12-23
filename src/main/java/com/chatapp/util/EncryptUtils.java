package com.chatapp.util;


import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    public final static String MD5 = "MD5";
    public final static String SHA_1 = "SHA-1";
    public final static String createMD5(String str, String algorithm) {
        MessageDigest md = null;
        String myHash = null;
        try {
            md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            myHash = null;
        }
        return myHash.toLowerCase();
    }
}
