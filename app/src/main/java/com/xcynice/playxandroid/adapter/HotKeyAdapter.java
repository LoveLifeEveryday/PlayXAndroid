package com.xcynice.playxandroid.adapter;


import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.HotKey;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/18 15:00
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 热词的 adapter
 */

public class HotKeyAdapter extends BaseQuickAdapter<HotKey, BaseViewHolder> {
    public HotKeyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotKey item) {
        helper.setText(R.id.flowlayout_tv, item.getName());
    }

}
