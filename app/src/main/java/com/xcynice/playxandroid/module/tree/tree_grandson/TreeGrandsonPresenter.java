package com.xcynice.playxandroid.module.tree.tree_grandson;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.TreeChild;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5 16:55
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TreeGrandsonPresenter 体系二级界面的 presenter ，如：里面的文章列表展示
 */

public class TreeGrandsonPresenter extends BasePresenter<ITreeGrandsonView> {
    public TreeGrandsonPresenter(ITreeGrandsonView baseView) {
        super(baseView);
    }

    /**
     * 第一次加载文章数据
     *
     * @param cid 分类 id
     */
    public void getTreeChildList(int cid) {
        addDisposable(apiServer.getTreeChildList(0, cid), new BaseObserver<BaseBean<TreeChild>>(baseView) {

            @Override
            public void onSuccess(BaseBean<TreeChild> bean) {
                baseView.setTreeChildData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showTreeChildError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 通过刷新加载文章数据
     *
     * @param cid 分类 id
     */
    public void getTreeChildListByRefresh(int cid) {
        addDisposable(apiServer.getTreeChildList(0, cid), new BaseObserver<BaseBean<TreeChild>>(baseView) {
            @Override
            public void onSuccess(BaseBean<TreeChild> bean) {
                baseView.setTreeChildDataByRefresh(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showTreeChildError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 通过加载更多得到文章数据
     *
     * @param page 页数
     * @param cid  分类 id
     */
    public void getTreeChildListByLoadMore(int page, int cid) {
        addDisposable(apiServer.getTreeChildList(page, cid), new BaseObserver<BaseBean<TreeChild>>(baseView) {
            @Override
            public void onSuccess(BaseBean<TreeChild> bean) {
                baseView.setTreeChildDataByLoadMore(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setTreeChildDataByLoadMoreFail(msg + "(°∀°)ﾉ");
            }
        });
    }

    public void collect(int id) {
        //noinspection rawtypes
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
