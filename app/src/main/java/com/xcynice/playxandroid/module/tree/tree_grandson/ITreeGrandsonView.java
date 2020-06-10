package com.xcynice.playxandroid.module.tree.tree_grandson;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.TreeChild;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5 16:56
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ITreeGrandsonView
 */

public interface ITreeGrandsonView extends BaseView {

    void setTreeChildData(BaseBean<TreeChild> list);

    void showTreeChildError(String errorMessage);

    void showCollectSuccess(String successMessage);

    void showCollectError(String errorMessage);

    void showUnCollectSuccess(String successMessage);

    void showUnCollectError(String errorMessage);

    void setTreeChildDataByLoadMore(BaseBean<TreeChild> list);

    void setTreeChildDataByLoadMoreFail(String msg);

    void setTreeChildDataByRefresh(BaseBean<TreeChild> list);
}
