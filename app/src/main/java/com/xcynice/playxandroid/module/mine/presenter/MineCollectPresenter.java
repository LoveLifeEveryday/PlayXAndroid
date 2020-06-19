package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.module.mine.view.IMineCollectView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15 23:02
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class MineCollectPresenter extends BasePresenter<IMineCollectView> {

    public MineCollectPresenter(IMineCollectView baseView) {
        super(baseView);
    }


    public void getMineCollectFirst() {
        addDisposable(apiServer.getCollectList(0), new BaseObserver<BaseBean<Article>>(baseView,true) {

            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setMineCollectFirstSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setMineCollectFirstFail(msg);
            }
        });
    }


    public void getMineCollectRefresh() {
        addDisposable(apiServer.getCollectList(0), new BaseObserver<BaseBean<Article>>(baseView) {


            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setMineCollectRefreshSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setMineCollectRefreshFail(msg);
            }
        });
    }


    public void getMineCollectMore(int page) {
        addDisposable(apiServer.getCollectList(page), new BaseObserver<BaseBean<Article>>(baseView) {


            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setMineCollectSuccessMoreSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setMineCollectSuccessMoreFail(msg);
            }
        });
    }


    public void unCollect(int id, int originId) {
        addDisposable(apiServer.unCollectInMineCollect(id, originId), new BaseObserver<BaseBean>(baseView) {


            @Override
            public void onSuccess(BaseBean bean) {
                baseView.unCollectSuccess("取消收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.unCollectFail(msg + "(°∀°)ﾉ");
            }
        });
    }


}
