package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.MineShare;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 19:15
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description IMineShareView
 */

public interface IMineShareView extends BaseView {

    /**
     * 第一次设置我的分享数据成功
     *
     * @param list 分享的数据
     */
    void setShareFirstSuccess(MineShare.ShareArticlesBean list);


    /**
     * 第一次设置我的分享数据失败
     *
     * @param msg 失败信息
     */
    void setShareFirstFail(String msg);


    /**
     * 加载更多我的分享数据成功
     *
     * @param list 我的分享数据
     */
    void setShareMoreSuccess(MineShare.ShareArticlesBean list);


    /**
     * 加载更多我的分享数据失败
     *
     * @param msg 失败信息
     */
    void setShareMoreFail(String msg);


    /**
     * 刷新我的分享数据成功
     *
     * @param list 我的分享数据
     */
    void setShareRefreshSuccess(MineShare.ShareArticlesBean list);


    /**
     * 刷新我的分享数据失败
     *
     * @param msg 失败信息
     */
    void setShareRefreshFail(String msg);


    /**
     * 显示收藏成功
     *
     * @param successMessage 成功信息
     */
    void showCollectSuccess(String successMessage);

    /**
     * 显示收藏失败
     *
     * @param errorMessage 失败信息
     */
    void showCollectError(String errorMessage);

    /**
     * 显示未收藏成功
     *
     * @param successMessage 成功信息
     */
    void showUnCollectSuccess(String successMessage);

    /**
     * 显示未收藏失败
     *
     * @param errorMessage 失败信息
     */
    void showUnCollectError(String errorMessage);
}
