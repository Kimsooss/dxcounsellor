package com.example.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * Created by Npinal_One on 16. 2. 24..
 */
public class PreferenceManager {
    private final String TAG = this.getClass().toString();
    private static final String PREF_NAME = "kr.co.dxline.moswms.Global";

    private static PreferenceManager preferenceManager = null;
    private static Context context;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static PreferenceManager getInstance(Context c) {
        if(preferenceManager == null) {
            preferenceManager = new PreferenceManager();
            context = c;
            preferenceManager.pref = c.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
            preferenceManager.editor = pref.edit();
        }
        return preferenceManager;
    }

    public void put(String key, String value) {
        putAES256(key, value);
//        editor.putString(key, value);
//        editor.commit();
    }

    public void remove(String key) {
        if(isEmpty(key) == false) {
            editor.remove(key);
            editor.commit();
        }
    }

    /**
     * AES256 형식으로 저장
     * @param key
     * @param value
     */
    public void putAES256(String key, String value){
        try {
            editor.putString(key, AES256Cipher.AES_Encode(value));
        } catch (UnsupportedEncodingException e) {
            Log.throwException(e);
        } catch (NoSuchAlgorithmException e) {
            Log.throwException(e);
        } catch (NoSuchPaddingException e) {
            Log.throwException(e);
        } catch (InvalidKeyException e) {
            Log.throwException(e);
        } catch (InvalidAlgorithmParameterException e) {
            Log.throwException(e);
        } catch (IllegalBlockSizeException e) {
            Log.throwException(e);
        } catch (BadPaddingException e) {
            Log.throwException(e);
        }
        editor.commit();
    }

    public String getValueAES246(String key, String dftValue){
        if(isEmpty(key)) {
            return dftValue;
        }
        try {
            return AES256Cipher.AES_Decode(pref.getString(key, AES256Cipher.AES_Encode(dftValue)));
        } catch (UnsupportedEncodingException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (NoSuchAlgorithmException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (NoSuchPaddingException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (InvalidKeyException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (InvalidAlgorithmParameterException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (IllegalBlockSizeException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (BadPaddingException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } catch (IllegalArgumentException e) {
            Log.throwException(e);
            return getValueError(key, dftValue);
        } finally {

        }
    }

    private String getValueError(String key, String dftValue) {
        // versionName 1.0.9, versionCode 5 부터 preference 저장시 aes256 사용. decode 되지 않는다면 삭제
//        remove(key);
        clear();
        return dftValue;
    }

    public void put(String key, boolean value) {
        put(key, String.valueOf(value));
    }

    public void put(String key, int value) {
        put(key, String.valueOf(value));
    }

    public String getValue(String key, String dftValue) {
        return getValueAES246(key, dftValue);
//        return pref.getString(key, dftValue);
    }

    public int getValue(String key, int dftValue) {
        try {
            return Integer.valueOf(getValue(key, String.valueOf(dftValue)));
        } catch (Exception e) {
            Log.throwException(e);
            return dftValue;
        } finally {

        }
    }

    public boolean getValue(String key, boolean dftValue) {
        try {
            return Boolean.valueOf(getValue(key, String.valueOf(dftValue)));
        } catch (Exception e) {
            Log.throwException(e);
            return dftValue;
        } finally {

        }
    }

    public boolean isEmpty(String key) {
        if(pref.contains(key) == false || pref.getString(key, "").equals("")) {
            return true;
        }
        return false;
    }

    public void clear() {
        editor.clear().commit();
    }

}
