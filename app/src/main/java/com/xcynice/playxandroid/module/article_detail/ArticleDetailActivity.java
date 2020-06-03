package com.xcynice.playxandroid.module.article_detail;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;

import butterknife.BindView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/2
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ArticleDetailActivity 文章详情的 Activity
 */


@SuppressWarnings("rawtypes")
public class ArticleDetailActivity extends BaseActivity {

    @BindView(R.id.pb_article_detail)
    ProgressBar mPbArticleDetail;
    @BindView(R.id.wv_article_detail)
    WebView mWvArticleDetail;
    @BindView(R.id.ll_article_detail)
    LinearLayout mLlArticleDetail;

    public static final String WEB_URL = "com.xcynice.playxandroid.module.article_detail.web_url";
    public static final String WEB_TITLE = "com.xcynice.playxandroid.module.article_detail.web_title";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        //实现沉浸式
        ImmersionBar.with(this).init();

        WebSettings webSettings = mWvArticleDetail.getSettings();
        //支持javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以支持缩放
        webSettings.setSupportZoom(true);
        //设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setLoadWithOverviewMode(true);

        mWvArticleDetail.setWebChromeClient(new ArticleWebChromeClient(mPbArticleDetail));
    }

    @Override
    protected void initData() {
        String mUrl = getIntent().getStringExtra(WEB_URL);
        String mTitle = getIntent().getStringExtra(WEB_TITLE);
        mWvArticleDetail.loadUrl(mUrl);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}