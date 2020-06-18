package com.xcynice.playxandroid.module.search.util;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xcynice.playxandroid.util.SpUtil;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17 22:02
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class SearchHistoryUtils {
    private static final String SP_NAME = "search_history";
    private static final String KEY_HISTORY = "KEY_HISTORY";
    private static final Gson GSON = new Gson();

    public static void save(List<String> history) {
        String json = GSON.toJson(history);
        SpUtil.setString(KEY_HISTORY, json);
    }

    public static List<String> get() {
        String json = SpUtil.getString(KEY_HISTORY);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return GSON.fromJson(json, new TypeToken<List<String>>() {
        }.getType());
    }
}
