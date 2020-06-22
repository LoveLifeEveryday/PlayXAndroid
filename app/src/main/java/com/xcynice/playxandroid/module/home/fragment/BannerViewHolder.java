package com.xcynice.playxandroid.module.home.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.Banner;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1 23:59
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description Banner 的 ViewHolder
 */

public class BannerViewHolder implements MZViewHolder<Banner> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        mImageView = view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, Banner data) {
        Glide.with(context).load(data.getImagePath()).into(mImageView);
    }
}
