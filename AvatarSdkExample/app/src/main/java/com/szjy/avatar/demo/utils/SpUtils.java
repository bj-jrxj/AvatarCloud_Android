package com.szjy.avatar.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.szjy.avatar.demo.MyApplication;


public class SpUtils {
    public static final String FILE_NAME = "UMC_DEMO";

    public static boolean putString(String key, String value) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        return editor.putString(key, value).commit();
    }

    public static boolean putLong(String key, long value) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        return editor.putLong(key, value).commit();
    }

    public static long getLong(String key) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return settings.getLong(key, 0);
    }

    public static boolean putInt(String key, int value) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        return editor.putInt(key, value).commit();
    }

    public static int getInt(String key, int defaultValue) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    public static boolean putBool(String key, boolean value) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        return editor.putBoolean(key, value).commit();
    }

    public static boolean getBool(String key) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    public static boolean getBool(String key, boolean defaultValue) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    public static String getString(String key) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }
}
