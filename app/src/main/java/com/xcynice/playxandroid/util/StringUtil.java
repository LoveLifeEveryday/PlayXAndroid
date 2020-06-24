package com.xcynice.playxandroid.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/4 18:12
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description StringUtil
 */

public class StringUtil {

    private static Pattern REMOVE_BLANK = Pattern.compile("\\s*|\t|\r|\n");


    /**
     * 清除所有空格
     *
     * @param str 字符串
     * @return 转换后的字符串
     */
    public static String removeAllBlank(String str) {
        String s = "";
        if (str != null) {
            Matcher m = REMOVE_BLANK.matcher(str);
            s = m.replaceAll("");
        }
        return s;
    }

    /**
     * 去除指定数目的空格
     *
     * @param str   字符串
     * @param count 空格数目
     * @return 转换后的字符串
     */
    public static String removeBlank(String str, int count) {
        String s = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s{" + count + ",}|\t|\r|\n");
            Matcher m = p.matcher(str);
            s = m.replaceAll(" ");
        }
        return s;
    }

}
