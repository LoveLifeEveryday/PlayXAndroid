package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.Article;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15 22:57
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description IMineCollectView
 */

public interface IMineCollectView extends BaseView {
    /**
     * 首次设置我的收藏列表成功
     *
     * @param list 我的收藏列表
     */
    void setMineCollectFirstSuccess(BaseBean<Article> list);

    /**
     * 首次设置我的收藏列表失败
     *
     * @param msg 失败信息
     */
    void setMineCollectFirstFail(String msg);


    /**
     * 刷新我的收藏列表成功
     *
     * @param list 我的收藏列表
     */
    void setMineCollectRefreshSuccess(BaseBean<Article> list);


    /**
     * 刷新我的收藏列表失败
     *
     * @param msg 失败信息
     */
    void setMineCollectRefreshFail(String msg);

    /**
     * 加载更多我的收藏列表成功
     *
     * @param list 我的收藏列表
     */
    void setMineCollectSuccessMoreSuccess(BaseBean<Article> list);


    /**
     * 加载更多我的收藏列表失败
     *
     * @param msg 失败信息
     */
    void setMineCollectSuccessMoreFail(String msg);


    /**
     * 取消收藏成功
     *
     * @param msg 成功信息
     */
    void unCollectSuccess(String msg);

    /**
     * 取消收藏失败
     *
     * @param msg 失败信息
     */
    void unCollectFail(String msg);

}
