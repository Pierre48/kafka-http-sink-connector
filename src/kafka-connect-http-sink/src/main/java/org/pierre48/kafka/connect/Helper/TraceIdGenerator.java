package org.pierre48.kafka.connect.Helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TraceIdGenerator {

    public static String generateTraceId(String hashString) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(hashString.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
// Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
