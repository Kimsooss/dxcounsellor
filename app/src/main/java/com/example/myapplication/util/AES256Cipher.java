package com.example.myapplication.util;

import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Npinal_One on 16. 2. 2..
 */
public class AES256Cipher {

    public static byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private static String key = "abcdefghijklmnopqrstuvwxyz123456";

//    /*
//     * public static String AES_Encode(String str, String key) throws
//     * java.io.UnsupportedEncodingException, NoSuchAlgorithmException,
//     * NoSuchPaddingException, InvalidKeyException,
//     * InvalidAlgorithmParameterException, IllegalBlockSizeException,
//     * BadPaddingException
//     */
//    public static String AES256Encode(String str, String publicKey) {
//        try {
//            byte[] textBytes = null;
//            textBytes = str.getBytes("UTF-8");
//            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
//            SecretKeySpec newKey = null;
//            newKey = new SecretKeySpec(publicKey.getBytes("UTF-8"), "AES");
//            Cipher cipher = null;
//            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
//
//            return Base64.encodeToString(cipher.doFinal(textBytes), Base64.DEFAULT);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String AES256Decode(String str, String publicKey) {
//        if (RegexHelper.isValue(str) == false)
//            return null;
//        try {
//            byte[] textBytes = Base64.decode(str, Base64.DEFAULT);
//            // byte[] textBytes = str.getBytes("UTF-8");
//            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
//            SecretKeySpec newKey = new SecretKeySpec(publicKey.getBytes("UTF-8"), "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
//            return new String(cipher.doFinal(textBytes), "UTF-8");
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String AES_Encode(String str)	throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        
        return Base64.encodeToString(cipher.doFinal(textBytes), Base64.NO_WRAP);
    }

    public static String AES_Decode(String str)	throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IllegalArgumentException {

        byte[] textBytes = Base64.decode(str, 0);
        //byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return new String(cipher.doFinal(textBytes), "UTF-8");
    }
}
