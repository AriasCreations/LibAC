package dev.zontreck.ariaslib.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing
{


    /**
     * A md5 hashing function that is compatible with literally every other hashing function out there
     * @param input The string to hash
     * @return The hash
     */
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            byte[] byteData = md.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
