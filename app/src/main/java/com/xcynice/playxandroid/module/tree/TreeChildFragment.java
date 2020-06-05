package com.xcynice.playxandroid.module.tree;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.NavigationAdapter;
import com.xcynice.playxandroid.adapter.TreeChildAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.Navigation;
import com.xcynice.playxandroid.bean.Tree;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TreeChildFragment
 */


public class TreeChildFragment extends BaseFragment<TreeChildPresenter> implements ITreeChildView {


    public static final String IS_TREE = "com/xcynice/playxandroid/module/tree/TreeChildFragment.java/isTree";
    @BindView(R.id.rv_tree_child)
    RecyclerView mRvTreeChild;
    private TreeChildAdapter mTreeChildAdapter;
    private NavigationAdapter mNavigationAdapter;
    private List<Tree> mTreeList;
    private List<Navigation> mNavigationList;

    private boolean mIsTree;

    @Override
    protected TreeChildPresenter createPresenter() {
        return new TreeChildPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree_child;
    }


    /**
     * 创建 TreeChildFragment 实例
     *
     * @param isTree true 表示是体系 Fragment, false 表示是导航 Fragment
     * @return TreeChildFragment 实例
     */
    public static TreeChildFragment newInstance(boolean isTree) {
        TreeChildFragment treeChildFragment = new TreeChildFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_TREE, isTree);
        treeChildFragment.setArguments(bundle);
        return treeChildFragment;
    }

    @Override
    protected void initView() {
        mIsTree = getArguments().getBoolean(IS_TREE);
        mRvTreeChild.setLayoutManager(new LinearLayoutManager(mContext));
        if (mIsTree) {
            mTreeChildAdapter = new TreeChildAdapter(R.layout.item_tree_list);
            mTreeChildAdapter.setEnableLoadMore(false);
            mRvTreeChild.setAdapter(mTreeChildAdapter);
        } else {
            mNavigationAdapter = new NavigationAdapter(R.layout.item_tree_list);
            mNavigationAdapter.setEnableLoadMore(false);
            mRvTreeChild.setAdapter(mNavigationAdapter);
        }
    }


    @Override
    protected void initData() {
        if (mIsTree) {
            presenter.getTreeData();
        } else {
            presenter.getNavigationData();
        }
    }


    @Override
    public void setTreeData(BaseBean<List<Tree>> treeList) {
        mTreeList = treeList.data;
        mTreeChildAdapter.setNewData(mTreeList);
        mTreeChildAdapter.setOnItemClickListener((bean, pos) -> {
            // TODO: 2020/6/5 这里的点击事件，等跳转的界面写好再说
        });
    }

    @Override
    public void setTreeDataFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void setNavigationData(BaseBean<List<Navigation>> navigationList) {
        mNavigationList = navigationList.data;
        mNavigationAdapter.setNewData(mNavigationList);
        mNavigationAdapter.setOnItemClickListener((navigation, pos) -> {
            // TODO: 2020/6/5 这里的点击事件，等跳转的界面写好再说
        });
    }

    @Override
    public void setNavigationFail(String msg) {
        ToastUtil.showToast(msg);
    }
}