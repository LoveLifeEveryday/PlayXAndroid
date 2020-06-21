package com.xcynice.playxandroid.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.Coin;
import com.xcynice.playxandroid.util.XUtil;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/13 22:33
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description CoinRecordAdapter  积分记录的 adapter
 */

public class CoinRecordAdapter extends BaseQuickAdapter<Coin.DatasBean, BaseViewHolder> {
    public CoinRecordAdapter(@Nullable List<Coin.DatasBean> data) {
        super(R.layout.rv_item_coin_record, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Coin.DatasBean item) {
        String desc = item.getDesc();
        //找到第一个空格的位置
        int firstSpace = desc.indexOf(XUtil.getString(R.string.blank));
        //找到第二个空格的位置
        int secondSpace = desc.indexOf(XUtil.getString(R.string.blank), firstSpace + 1);
        String time = desc.substring(0, secondSpace);
        String title = desc.substring(secondSpace + 1)
                .replace(XUtil.getString(R.string.comma), "")
                .replace(XUtil.getString(R.string.colon), "")
                .replace(XUtil.getString(R.string.blank), "");
        helper.setText(R.id.tv_coin_count, XUtil.getString(R.string.plus) + item.getCoinCount());
        helper.setText(R.id.tv_title, title);
        helper.setText(R.id.tv_time, time);
    }
}
