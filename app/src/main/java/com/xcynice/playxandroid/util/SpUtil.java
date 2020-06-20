package com.xcynice.playxandroid.util;


import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @Author xucanyou666
 * @Date 2020/4/27 11:38
 * email：913710642@qq.com
 */
public class SpUtil {
    private static final String FILE_NAME = "config";

    public static final String IS_LOGIN = "isLogin";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NICK_NAME = "nickName";
    public static final String COIN = "coin";
    public static final String HIDE_BANNER = "hideBanner";
    public static final String HIDE_ABOUT_ME = "hide_about_me";
    public static final String HIDE_OPEN_SOURCE = "hide_open_source";
    public static final String HEAD_ICON = "head_icon";

    private static SharedPreferences sp = XUtil.getApplication().getSharedPreferences("config", 0);

    public SpUtil() {
    }

    public static void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }


    /**
     * 保存一个普通对象（非泛型）
     *
     * @param object 普通对象
     */
    public static void setObject(Object object) {
        String key = getKey(object.getClass());
        Gson gson = new Gson();
        String json = gson.toJson(object);
        setString(key, json);
    }


    /**
     * 保存一个泛型对象
     *
     * @param object 对象
     * @param type   类型 例如： new TypeToken<List<Person>>() { }.getType()
     */
    public static void setObject(Object object, Type type) {
        String key = getKey(type);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        setString(key, json);
    }

    public static String getKey(Class<?> clazz) {
        return clazz.getName();
    }

    public static String getKey(Type type) {
        return type.toString();
    }


    /**
     * @param clazz 类名
     * @param <T>   类型
     * @return 类
     */
    public static <T> T getObject(Class<T> clazz) {
        String key = getKey(clazz);
        String json = getString(key);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }


    /**
     * 通过Type去获取一个泛型对象
     *
     * @param type 类型
     * @param <T>  类型
     * @return 对象
     */
    public static <T> T getObject(Type type) {
        String key = getKey(type);
        String json = getString(key);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }


    public static String getString(String key) {
        return sp.getString(key, "");
    }

    public static void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public static void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static void setFloat(String key, Float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public static Float getFloat(String key) {
        return sp.getFloat(key, 0.0F);
    }

    public static void setLong(String key, Long value) {
        sp.edit().putLong(key, value).apply();
    }

    public static Long getLong(String key) {
        return sp.getLong(key, 0L);
    }

    public static void removeByKey(String key) {
        sp.edit().remove(key).apply();
    }

    public static void removeAll() {
        sp.edit().clear().apply();
    }
}