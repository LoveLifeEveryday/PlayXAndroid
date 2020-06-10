package com.xcynice.playxandroid.module.tree.tree_child;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.Navigation;
import com.xcynice.playxandroid.bean.Tree;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5 09:55
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 体系主页面的接口
 */

public interface ITreeChildView extends BaseView {
    /**
     * 设置体系数据
     *
     * @param treeList 体系数据
     */
    void setTreeData(BaseBean<List<Tree>> treeList);

    /**
     * 设置体系数据失败
     *
     * @param msg 失败信息
     */
    void setTreeDataFail(String msg);

    /**
     * 设置导航数据
     *
     * @param navigationList 导航数据
     */
    void setNavigationData(BaseBean<List<Navigation>> navigationList);

    /**
     * 设置导航数据失败
     *
     * @param msg 失败信息
     */
    void setNavigationFail(String msg);
}
