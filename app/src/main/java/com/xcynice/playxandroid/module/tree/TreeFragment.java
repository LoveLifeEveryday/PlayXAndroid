package com.xcynice.playxandroid.module.tree;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.gyf.immersionbar.components.SimpleImmersionProxy;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.CommonViewPagerAdapter;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/4
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TreeFragment
 */


@SuppressWarnings("rawtypes")
public class TreeFragment extends BaseFragment implements SimpleImmersionOwner {


    @BindView(R.id.tl_tree)
    TabLayout mTlTree;
    @BindView(R.id.vp_tree)
    ViewPager mVpTree;
    private SimpleImmersionProxy mSimpleImmersionProxy = new SimpleImmersionProxy(this);

    @SuppressWarnings("rawtypes")
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    protected void initView() {
        mTlTree.setupWithViewPager(mVpTree);
    }

    @Override
    protected void initData() {
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add(getResources().getString(R.string.system));
        titleList.add(getResources().getString(R.string.navigation));
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getFragmentManager(), titleList);
        for (int i = 0; i < titleList.size(); i++) {
            // TODO: 2020/6/4 这里要用单例新建  TreeChildFragment ，以达到效果
            commonViewPagerAdapter.addFragment(new TreeChildFragment());
        }
        mVpTree.setAdapter(commonViewPagerAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mSimpleImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSimpleImmersionProxy.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mSimpleImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSimpleImmersionProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).titleBar(mTlTree).init();
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }
}