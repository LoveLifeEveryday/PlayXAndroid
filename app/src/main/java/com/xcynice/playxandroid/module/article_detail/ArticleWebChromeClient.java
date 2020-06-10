package com.xcynice.playxandroid.module.article_detail;


import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/2 11:19
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ArticleWebChromeClient 设置加载进度条
 */

public class ArticleWebChromeClient extends WebChromeClient {

    private static final int LOADING_SUCCESS = 100;
    private ProgressBar mProgressBar;

    public ArticleWebChromeClient(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == LOADING_SUCCESS) {
            mProgressBar.setVisibility(View.INVISIBLE);
        } else {
            if (View.INVISIBLE == mProgressBar.getVisibility()) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            mProgressBar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }
}
