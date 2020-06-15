package com.xcynice.playxandroid.adapter;


import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.MineShare;
import com.xcynice.playxandroid.util.StringUtil;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15 18:08
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineShareAdapter
 */

public class MineShareAdapter extends BaseQuickAdapter<MineShare.ShareArticlesBean.DatasBean, BaseViewHolder> {
    public MineShareAdapter(@Nullable List<MineShare.ShareArticlesBean.DatasBean> data) {
        super(R.layout.item_article_list, data);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, MineShare.ShareArticlesBean.DatasBean item) {
        if (item.isFresh()) {
            helper.setGone(R.id.tv_article_new, true);
        } else {
            helper.setGone(R.id.tv_article_new, false);
        }

        if (TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_article_author, item.getShareUser());
        } else {
            helper.setText(R.id.tv_article_author, item.getAuthor());
        }
        helper.setText(R.id.tv_article_time, item.getNiceDate());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String title = Html.fromHtml(item.getTitle(), Html.FROM_HTML_MODE_LEGACY).toString();
            title = StringUtil.removeAllBank(title, 2);
            helper.setText(R.id.tv_article_title, title);
        } else {
            String title = Html.fromHtml(item.getTitle()).toString();
            title = StringUtil.removeAllBank(title, 2);
            helper.setText(R.id.tv_article_title, title);
        }

        if (TextUtils.isEmpty(item.getDesc())) {
            helper.setGone(R.id.tv_article_content, false);
        } else {
            helper.setGone(R.id.tv_article_content, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String desc = Html.fromHtml(item.getDesc(), Html.FROM_HTML_MODE_LEGACY).toString();
                desc = StringUtil.removeAllBank(desc, 2);
                helper.setText(R.id.tv_article_content, desc);
            } else {
                String desc = Html.fromHtml(item.getDesc()).toString();
                desc = StringUtil.removeAllBank(desc, 2);
                helper.setText(R.id.tv_article_content, desc);
            }
        }
        helper.setText(R.id.tv_article_chapter_name, item.getSuperChapterName() + "·" + item.getChapterName());

        //设置收藏的点击事件
        helper.addOnClickListener(R.id.iv_article_favorite);

        if (item.isCollect()) {
            Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.iv_article_favorite));
        } else {
            Glide.with(mContext).load(R.drawable.ic_like_normal).into((ImageView) helper.getView(R.id.iv_article_favorite));
        }

    }
}
