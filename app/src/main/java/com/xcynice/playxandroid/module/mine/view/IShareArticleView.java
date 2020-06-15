package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15 19:29
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description IShareArticleView
 */

public interface IShareArticleView extends BaseView {
    /**
     * 分享文章成功
     *
     * @param msg 成功信息
     */
    void shareArticleSuccess(String msg);

    /**
     * 分享文章失败
     *
     * @param msg 失败信息
     */
    void shareArticleFail(String msg);
}
