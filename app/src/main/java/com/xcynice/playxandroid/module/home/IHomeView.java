package com.xcynice.playxandroid.module.home;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.bean.Banner;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1 21:25
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description IHomeView 接口
 */

public interface IHomeView extends BaseView {

    /**
     * 设置轮播图
     *
     * @param list 轮播图的集合
     */
    void setBanner(BaseBean<List<Banner>> list);


    /**
     * 设置轮播图失败
     *
     * @param errorMsg 错误信息
     */
    void showBannerError(String errorMsg);


    /**
     * 设置文章数据
     *
     * @param list 文章集合
     */
    void setArticle(BaseBean<Article> list);


    /**
     * 设置文章数据失败
     *
     * @param errorMsg 错误信息
     */
    void setArticleError(String errorMsg);


    /**
     * 用加载更多的方式设置文章数据
     *
     * @param list 文章数据
     */
    void setArticleDataByMore(BaseBean<Article> list);

    /**
     * 加载更多失败
     *
     * @param errorMessage 失败信息
     */
    void showArticleErrorByMore(String errorMessage);

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
