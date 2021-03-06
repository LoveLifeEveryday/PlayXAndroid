package com.xcynice.playxandroid.module.search.presenter;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.module.search.view.ISearchResultView;
import com.xcynice.playxandroid.util.XUtil;

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
     * @param key 关键词
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


    /**
     * 刷新搜索结果
     * @param key 关键词
     */
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
                baseView.collectSuccess(XUtil.getString(R.string.collectSuccess));
            }

            @Override
            public void onError(String msg) {
                baseView.collectFail(XUtil.getString(R.string.collectFail));
            }
        });
    }

    public void unCollect(int id) {
        //noinspection rawtypes
        addDisposable(apiServer.unCollect(id), new BaseObserver<BaseBean>(baseView) {

            @Override
            public void onSuccess(BaseBean o) {
                baseView.unCollectSuccess(XUtil.getString(R.string.collectSuccess));
            }

            @Override
            public void onError(String msg) {
                baseView.unCollectFail(XUtil.getString(R.string.collectFail));
            }
        });
    }

}
