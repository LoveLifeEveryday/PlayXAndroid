package com.xcynice.playxandroid.adapter;

import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.TreeChild;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/30.
 * Describe :
 */
public class TreeGrandsonAdapter extends BaseQuickAdapter<TreeChild.DatasBean, BaseViewHolder> {

    public TreeGrandsonAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeChild.DatasBean item) {
        if (item.isFresh()) {
            helper.setVisible(R.id.tv_article_new, true);
        } else {
            helper.setGone(R.id.tv_article_new, false);
        }

        if (TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_article_author, item.getShareUser());
        } else {
            helper.setText(R.id.tv_article_author, item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_time, item.getNiceDate());
        }
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()));

        if (TextUtils.isEmpty(item.getDesc())) {
            helper.setGone(R.id.tv_article_content, false);
        } else {
            helper.setGone(R.id.tv_article_content, true);
            helper.setText(R.id.tv_article_content, item.getDesc());
        }
        helper.setText(R.id.tv_article_chapter_name, item.getSuperChapterName() + "·" + item.getChapterName());


        //设置收藏的点击事件
        helper.addOnClickListener(R.id.iv_article_favorite);

        if (item.isCollect())
            Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.iv_article_favorite));
        else
            Glide.with(mContext).load(R.drawable.ic_like_normal).into((ImageView) helper.getView(R.id.iv_article_favorite));

    }

}

