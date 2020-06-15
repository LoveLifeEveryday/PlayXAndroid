package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.MineShare;
import com.xcynice.playxandroid.module.mine.view.IMineShareView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 19:22
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineSharePresenter
 */

public class MineSharePresenter extends BasePresenter<IMineShareView> {
    public MineSharePresenter(IMineShareView baseView) {
        super(baseView);
    }

    /**
     * 第一次获取我的分享数据
     */
    public void getMineShareFirst() {
        addDisposable(apiServer.getMineShare(1), new BaseObserver<BaseBean<MineShare>>(baseView,true) {


            @Override
            public void onSuccess(BaseBean<MineShare> o) {
                baseView.setShareFirstSuccess(o.data.getShareArticles());
            }

            @Override
            public void onError(String msg) {
                baseView.setShareFirstFail(msg);
            }
        });
    }

    /**
     * 刷新我的分享数据
     */
    public void getMineShareRefresh() {
        addDisposable(apiServer.getMineShare(1), new BaseObserver<BaseBean<MineShare>>(baseView) {

            @Override
            public void onSuccess(BaseBean<MineShare> bean) {
                baseView.setShareRefreshSuccess(bean.data.getShareArticles());
            }

            @Override
            public void onError(String msg) {
                baseView.setShareRefreshFail(msg);
            }
        });
    }


    /**
     * 加载更多我的分享数据
     *
     * @param page 页数
     */
    public void getMineShareMore(int page) {
        addDisposable(apiServer.getMineShare(page), new BaseObserver<BaseBean<MineShare>>(baseView) {




            @Override
            public void onSuccess(BaseBean<MineShare> bean) {
                baseView.setShareMoreSuccess(bean.data.getShareArticles());
            }

            @Override
            public void onError(String msg) {
                baseView.setShareMoreFail(msg);
            }
        });
    }

    public void collect(int id) {
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {


            @Override
            public void onSuccess(@SuppressWarnings("rawtypes") BaseBean o) {
                baseView.showCollectSuccess("收藏成功");
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
        //noinspection rawtypes
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
