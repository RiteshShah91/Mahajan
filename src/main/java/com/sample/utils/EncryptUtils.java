package com.sample.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtils {

    // Retrive methods of MAFactoryHelper
    public String encodeMD5(String plaintext) {
        String sessionid = plaintext;
        byte[] defaultBytes = sessionid.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            String foo = messageDigest.toString();
            // + hexString.toString());
            sessionid = hexString + "";
        } catch (NoSuchAlgorithmException nsae) {
        }
        return sessionid;

    }

}
