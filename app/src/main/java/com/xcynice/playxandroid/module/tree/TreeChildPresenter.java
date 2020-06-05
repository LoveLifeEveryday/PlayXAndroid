package com.xcynice.playxandroid.module.tree;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Navigation;
import com.xcynice.playxandroid.bean.Tree;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5 10:01
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class TreeChildPresenter extends BasePresenter<ITreeChildView> {
    public TreeChildPresenter(ITreeChildView baseView) {
        super(baseView);
    }

    void getTreeData() {
        addDisposable(apiServer.getTreeList(), new BaseObserver<BaseBean<List<Tree>>>(baseView) {
            @Override
            public void onSuccess(BaseBean<List<Tree>> list) {
                baseView.setTreeData(list);
            }

            @Override
            public void onError(String msg) {
                baseView.setTreeDataFail(msg);
            }
        });
    }


    void getNavigationData() {
        addDisposable(apiServer.getNavigationList(), new BaseObserver<BaseBean<List<Navigation>>>(baseView) {
            @Override
            public void onSuccess(BaseBean<List<Navigation>> list) {
                baseView.setNavigationData(list);
            }

            @Override
            public void onError(String msg) {
                baseView.setNavigationFail(msg);
            }
        });
    }
}
