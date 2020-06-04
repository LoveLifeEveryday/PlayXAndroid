package com.xcynice.playxandroid.module.home.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.bean.Banner;
import com.xcynice.playxandroid.module.home.IHomeView;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1 21:35
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description Home 的 Presenter
 */

public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView baseView) {
        super(baseView);
    }

    public void getBanner() {
        addDisposable(apiServer.getBanner(), new BaseObserver<BaseBean<List<Banner>>>(baseView) {

            @Override
            public void onSuccess(BaseBean<List<Banner>> list) {
                baseView.setBanner(list);
            }

            @Override
            public void onError(String msg) {
                baseView.showBannerError(msg);
            }
        });
    }


    /**
     * 第一次获取首页文章数据
     */
    public void getArticleListByFirst() {
        addDisposable(apiServer.getArticleList(0), new BaseObserver<BaseBean<Article>>(baseView) {

            @Override
            public void onSuccess(BaseBean<Article> list) {
                baseView.setArticleByFirst(list);
            }

            @Override
            public void onError(String msg) {
                baseView.setArticleError(msg);
            }
        });
    }


    public void getArticleListByRefresh() {
        addDisposable(apiServer.getArticleList(0), new BaseObserver<BaseBean<Article>>(baseView) {


            @Override
            public void onSuccess(BaseBean<Article> list) {
                baseView.setArticleByRefresh(list);
            }

            @Override
            public void onError(String msg) {
                baseView.setArticleError(msg);
            }
        });
    }


    /**
     * 加载更多
     *
     * @param page 分页参数
     */
    public void getArticleListByMore(int page) {
        addDisposable(apiServer.getArticleList(page), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setArticleDataByMore(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showArticleErrorByMore(msg + "(°∀°)ﾉ");
            }
        });
    }


    /**
     * 收藏
     *
     * @param id 文章id
     */
    public void collect(int id) {
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showCollectSuccess("收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 取消收藏
     *
     * @param id 文章id
     */
    public void unCollect(int id) {
        addDisposable(apiServer.unCollect(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showUnCollectSuccess("取消收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showUnCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }
}
