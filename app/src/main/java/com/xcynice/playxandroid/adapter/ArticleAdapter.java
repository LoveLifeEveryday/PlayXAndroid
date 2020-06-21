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
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.util.StringUtil;
import com.xcynice.playxandroid.util.XUtil;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/10
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ArticleAdapter 文章列表的 adapter
 */


public class ArticleAdapter extends BaseQuickAdapter<Article.DataDetailBean, BaseViewHolder> {


    private boolean mTypeIsCollect = false;

    public ArticleAdapter(int layoutResId, @Nullable List<Article.DataDetailBean> data) {
        super(layoutResId, data);
    }

    public ArticleAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setType(boolean typeIsCollect) {
        mTypeIsCollect = typeIsCollect;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, Article.DataDetailBean item) {
        if (item.fresh) {
            helper.setGone(R.id.tv_article_new, true);
        } else {
            helper.setGone(R.id.tv_article_new, false);
        }

        if (TextUtils.isEmpty(item.author)) {
            // 如果是收藏列表的话，不会返回 shareUser 字段，因此直接设为匿名
            if (mTypeIsCollect) {
                helper.setText(R.id.tv_article_author, XUtil.getString(R.string.anonymity));
            } else {
                helper.setText(R.id.tv_article_author, item.shareUser);
            }
        } else {
            helper.setText(R.id.tv_article_author, item.author);
        }
        helper.setText(R.id.tv_article_time, item.niceDate);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String title = Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY).toString();
            title = StringUtil.removeAllBank(title, 2);
            helper.setText(R.id.tv_article_title, title);
        } else {
            String title = Html.fromHtml(item.title).toString();
            title = StringUtil.removeAllBank(title, 2);
            helper.setText(R.id.tv_article_title, title);
        }

        if (TextUtils.isEmpty(item.desc)) {
            helper.setGone(R.id.tv_article_content, false);
        } else {
            helper.setGone(R.id.tv_article_content, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String desc = Html.fromHtml(item.desc, Html.FROM_HTML_MODE_LEGACY).toString();
                desc = StringUtil.removeAllBank(desc, 2);
                helper.setText(R.id.tv_article_content, desc);
            } else {
                String desc = Html.fromHtml(item.desc).toString();
                desc = StringUtil.removeAllBank(desc, 2);
                helper.setText(R.id.tv_article_content, desc);
            }
        }
        //如果是我的收藏页面，不会返回 superChapterName 字段，因此要特殊化处理
        if (mTypeIsCollect) {
            helper.setText(R.id.tv_article_chapter_name, item.chapterName);
        } else {
            helper.setText(R.id.tv_article_chapter_name, item.superChapterName + XUtil.getString(R.string.dot) + item.chapterName);
        }


        //设置收藏的点击事件
        helper.addOnClickListener(R.id.iv_article_favorite);
        //先判断类型是不是收藏列表，因为收藏列表不返回item.collect字段，所以没法判断
        if (mTypeIsCollect) {
            Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.iv_article_favorite));
        } else {
            if (item.collect) {
                Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.iv_article_favorite));
            } else {
                Glide.with(mContext).load(R.drawable.ic_like_normal).into((ImageView) helper.getView(R.id.iv_article_favorite));
            }
        }

    }
}
