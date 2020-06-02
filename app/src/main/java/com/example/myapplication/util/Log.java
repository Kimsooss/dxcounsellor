package com.example.myapplication.util;

/**
 * Created by Npinal_One on 16. 1. 27..
 */
public class Log {
    public static boolean DEBUGGING_MODE = false;

    public static void v(String tag, String msg) {
        if (DEBUGGING_MODE) {
            try {
                throw new Exception("go go logger");
            } catch (Exception e) {
                String className = e.getStackTrace()[1].getClassName();
                className = className.substring(className.lastIndexOf(".") + 1, className.length());
                android.util.Log.v(tag, className + "." + e.getStackTrace()[1].getMethodName() + "() --- " + msg);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUGGING_MODE) {
            try {
                throw new Exception("go go logger");
            } catch (Exception e) {
                String className = e.getStackTrace()[1].getClassName();
                className = className.substring(className.lastIndexOf(".") + 1, className.length());
                android.util.Log.d(tag, className + "." + e.getStackTrace()[1].getMethodName() + "() --- " + msg);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUGGING_MODE) {
            try {
                throw new Exception("go go logger");
            } catch (Exception e) {
                String className = e.getStackTrace()[1].getClassName();
                className = className.substring(className.lastIndexOf(".") + 1, className.length());
                android.util.Log.i(tag, className + "." + e.getStackTrace()[1].getMethodName() + "() --- " + msg);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUGGING_MODE) {
            try {
                throw new Exception("go go logger");
            } catch (Exception e) {
                String className = e.getStackTrace()[1].getClassName();
                className = className.substring(className.lastIndexOf(".") + 1, className.length());
                android.util.Log.w(tag, className + "." + e.getStackTrace()[1].getMethodName() + "() --- " + msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUGGING_MODE) {
            try {
                throw new Exception("go go logger");
            } catch (Exception e) {
                String className = e.getStackTrace()[1].getClassName();
                className = className.substring(className.lastIndexOf(".") + 1, className.length());
                android.util.Log.e(tag, className + "." + e.getStackTrace()[1].getMethodName() + "() --- " + msg);
            }
        }
    }

    public static void throwException(Exception ex) {
        if (DEBUGGING_MODE) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
