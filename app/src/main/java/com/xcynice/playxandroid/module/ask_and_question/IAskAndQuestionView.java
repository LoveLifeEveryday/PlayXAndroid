package com.xcynice.playxandroid.module.ask_and_question;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.Article;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/4 17:35
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public interface IAskAndQuestionView extends BaseView {
    /**
     * 第一次设置文章数据
     *
     * @param list 文章集合
     */
    void setArticleByFirst(BaseBean<Article> list);


    /**
     * 通过刷新设置文章数据
     *
     * @param list 文章集合
     */
    void setArticleByRefresh(BaseBean<Article> list);


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
