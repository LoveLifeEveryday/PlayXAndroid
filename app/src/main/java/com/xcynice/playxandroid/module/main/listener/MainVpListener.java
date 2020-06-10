package com.xcynice.playxandroid.module.main.listener;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.util.XUtil;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1 11:51
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ViewPager 的 Listener
 */

public class MainVpListener implements ViewPager.OnPageChangeListener {
    private BottomNavigationView mBottomNavigationView;
    private TextView mTvTitle;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private RelativeLayout mRlTitle;

    public MainVpListener(BottomNavigationView bottomNavigationView, TextView title, ImageView ivLeft, ImageView ivRight, RelativeLayout rlTitle) {
        mBottomNavigationView = bottomNavigationView;
        mTvTitle = title;
        mIvLeft = ivLeft;
        mIvRight = ivRight;
        mRlTitle = rlTitle;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mBottomNavigationView.getMenu().getItem(position).setChecked(true);
        switch (position) {
            case 0:
                setImageVisible(mIvLeft, R.drawable.qr_code);
                setImageVisible(mIvRight, R.drawable.search);
                initTitle(R.string.home_page);
                break;
            case 1:
                initTitle(R.string.qAndA);
                setImageInVisible(mIvLeft, mIvRight);
                break;
            case 2:
                mRlTitle.setVisibility(View.GONE);
                break;
            case 3:
                mRlTitle.setVisibility(View.VISIBLE);
                mTvTitle.setVisibility(View.INVISIBLE);
                setImageInVisible(mIvLeft);
                setImageVisible(mIvRight, R.drawable.notification);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 为 Iv 设置可见以及设置图片
     *
     * @param imageView iv
     * @param drawable  资源文件
     */
    private void setImageVisible(ImageView imageView, int drawable) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(drawable);
    }

    /**
     * 设置 iv 隐藏
     *
     * @param imageViews imageViews
     */
    private void setImageInVisible(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 初始化 title, 设置 RlTitle 可见，以及设置 title 的文字
     *
     * @param titleText title 的文字
     */
    private void initTitle(int titleText) {
        mRlTitle.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(XUtil.getApplication().getString(titleText));
    }
}
