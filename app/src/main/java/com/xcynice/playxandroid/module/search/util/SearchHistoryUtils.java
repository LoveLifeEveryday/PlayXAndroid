package com.xcynice.playxandroid.module.search.util;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.XUtil;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17 22:02
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SearchHistoryUtils 搜索历史的工具类
 */

public class SearchHistoryUtils {


    private static final Gson GSON = new Gson();

    /**
     * 使用 json 存搜索历史
     *
     * @param history 搜索历史
     */
    public static void save(List<String> history) {
        String json = GSON.toJson(history);
        SpUtil.setString(XUtil.getString(R.string.KEY_HISTORY), json);
    }

    /**
     * 得到搜索历史
     *
     * @return 搜索历史 List
     */
    public static List<String> get() {
        String json = SpUtil.getString(XUtil.getString(R.string.KEY_HISTORY));
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return GSON.fromJson(json, new TypeToken<List<String>>() {
        }.getType());
    }
}
