package com.xcynice.playxandroid.util.listener;


import android.view.View;

import com.xcynice.playxandroid.util.ClickHelper;

/**
 * @Author 许朋友爱玩
 * @Date 2020/7/27 15:16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public abstract class OnClickListener2 implements View.OnClickListener {

    @Override
    public final void onClick(final View v) {
        ClickHelper.onlyFirstSameView(v, this::onClick2);
    }

    public abstract void onClick2(View v);
}
