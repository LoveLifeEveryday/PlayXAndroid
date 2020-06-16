package com.xcynice.playxandroid.adapter;


import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.OpenEntity;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/16 14:16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description OpenSourceAdapter
 */

public class OpenSourceAdapter extends BaseQuickAdapter<OpenEntity, BaseViewHolder> {
    public OpenSourceAdapter(@Nullable List<OpenEntity> data) {
        super(R.layout.rv_item_open, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OpenEntity item) {
        helper.setText(R.id.tv_project, item.getProject());
        if (TextUtils.isEmpty(item.getDescription())) {
            helper.setGone(R.id.tv_description, false);
        } else {
            helper.setGone(R.id.tv_description, true);
            helper.setText(R.id.tv_description, item.getDescription());
        }
    }
}
