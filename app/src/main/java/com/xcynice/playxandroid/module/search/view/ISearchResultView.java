package com.xcynice.playxandroid.module.search.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.Article;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17 23:10
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ISearchResultView
 */

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface ISearchResultView extends BaseView {
    void searchFirstSuccess(BaseBean<Article> bean);

    void searchFirstFail(String msg);

    void searchFreshSuccess(BaseBean<Article> bean);

    void searchFreshFail(String msg);

    void searchMoreSuccess(BaseBean<Article> bean);

    void searchMoreFail(String msg);

    void collectSuccess(String msg);

    void collectFail(String msg);

    void unCollectSuccess(String msg);

    void unCollectFail(String msg);

}
