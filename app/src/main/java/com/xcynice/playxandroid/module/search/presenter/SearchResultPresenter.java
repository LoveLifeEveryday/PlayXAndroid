package com.xcynice.playxandroid.module.search.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.module.search.view.ISearchResultView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17 23:15
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SearchResultPresenter
 */

public class SearchResultPresenter extends BasePresenter<ISearchResultView> {

    public SearchResultPresenter(ISearchResultView baseView) {
        super(baseView);
    }


    /**
     * 首次搜索
     *
     * @param key
     */
    public void searchFirst(String key) {
        addDisposable(apiServer.search(0, key), new BaseObserver<BaseBean<Article>>(baseView) {

            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.searchFirstSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.searchFirstFail(msg);
            }
        });
    }


    public void searchFresh(String key) {
        addDisposable(apiServer.search(0, key), new BaseObserver<BaseBean<Article>>(baseView) {

            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.searchFreshSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.searchFreshFail(msg);
            }
        });
    }


    public void searchMore(int page, String key) {
        addDisposable(apiServer.search(page, key), new BaseObserver<BaseBean<Article>>(baseView) {

            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.searchMoreSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.searchMoreFail(msg);
            }
        });
    }


    public void collect(int id) {
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {


            @Override
            public void onSuccess(BaseBean o) {
                baseView.collectSuccess("收藏成功");
            }

            @Override
            public void onError(String msg) {
                baseView.collectFail("收藏失败");
            }
        });
    }

    public void unCollect(int id) {
        addDisposable(apiServer.unCollect(id), new BaseObserver<BaseBean>(baseView) {

            @Override
            public void onSuccess(BaseBean o) {
                baseView.unCollectSuccess("收藏成功");
            }

            @Override
            public void onError(String msg) {
                baseView.unCollectFail("收藏失败");
            }
        });
    }

}
