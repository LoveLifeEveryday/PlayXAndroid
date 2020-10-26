package com.xcynice.playxandroid.util;


import android.widget.ImageView;

import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.util.glide.GlideHelper;

/**
 * @Author 许朋友爱玩
 * @Date 2020/8/1 15:50
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 图片加载辅助类
 */

public class ImageLoader {
    /**
     * 通过 url 加载 drawable 图片
     *
     * @param imageView iv
     * @param url       url
     */
    public static void image(ImageView imageView, String url) {
        GlideHelper.with(imageView.getContext())
                .errorHolder(R.drawable.image_holder)
                .placeHolder(R.drawable.image_holder)
                .cache(true)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param imageView imageView
     * @param res       资源文件
     */
    public static void loadLocalIcon(ImageView imageView, int res) {
        GlideHelper.with(imageView.getContext())
                .load(res)
                .into(imageView);
    }


    /**
     * 通过 url 加载 gif 图片
     *
     * @param imageView iv
     * @param url       url
     */
    public static void gif(ImageView imageView, String url) {
        GlideHelper.with(imageView.getContext())
                .asGif()
                .errorHolder(R.drawable.image_holder)
                .placeHolder(R.drawable.image_holder)
                .cache(true)
                .load(url)
                .into(imageView);
    }


}
