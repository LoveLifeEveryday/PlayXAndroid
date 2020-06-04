package com.xcynice.playxandroid.module.tree;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
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
public class TreeFragment extends BaseFragment {


    @BindView(R.id.tl_tree)
    TabLayout mTlTree;
    @BindView(R.id.vp_tree)
    ViewPager mVpTree;

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


}