
package com.kira.util;




import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class encode {
    public static String toSHA1(String str) {
        String salt = "fasodhfo2foi23434@#@$%$99f910$#$*545";
        String result = null;
        str = str + salt;
        try{
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
     
        }catch(UnsupportedEncodingException | NoSuchAlgorithmException e){}
        return result;
    }
    
    public static String encryptPassword(String password) {
        try {
            // Get instance of SHA-1 algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            
            // Convert password string to bytes and update digest
            md.update(password.getBytes());
            
            // Get the hash's bytes
            byte[] digest = md.digest();
            
            // Convert the hash's bytes to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(encryptPassword("12345ggggfffrduddre64s"));
    }
}
