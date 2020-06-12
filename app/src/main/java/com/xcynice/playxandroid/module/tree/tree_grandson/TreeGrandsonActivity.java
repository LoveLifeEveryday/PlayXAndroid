package com.xcynice.playxandroid.module.tree.tree_grandson;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.CommonViewPagerAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Tree;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TreeGrandsonActivity
 */


@SuppressWarnings("rawtypes")
public class TreeGrandsonActivity extends BaseActivity {

    public static final String CID = "com/xcynice/playxandroid/module/tree/TreeGrandsonActivity.java/CID";
    public static final String TITLE = "com/xcynice/playxandroid/module/tree/TreeGrandsonActivity.java/TITLE";
    public static final String POS = "com/xcynice/playxandroid/module/tree/TreeGrandsonActivity.java/POS";
    @BindView(R.id.tb_tree_grandson)
    Toolbar mTbTreeGrandson;
    @BindView(R.id.tl_tree_grandson)
    TabLayout mTlTreeGrandson;
    @BindView(R.id.viewpager)
    ViewPager mVpTreeGrandson;
    @BindView(R.id.abl_tree_grandson)
    AppBarLayout mAblTreeGrandson;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tree_grandson;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(mTbTreeGrandson).init();
        setSupportActionBar(mTbTreeGrandson);
        if (null != getSupportActionBar()) {
            String title = getIntent().getStringExtra(TITLE);
            if (!TextUtils.isEmpty(title)) {
                getSupportActionBar().setTitle(title);
            }
            mTbTreeGrandson.setNavigationOnClickListener(view -> finish());

            mTbTreeGrandson.setNavigationIcon(R.drawable.back);
        }
        mTlTreeGrandson.setupWithViewPager(mVpTreeGrandson);
    }

    @Override
    protected void initData() {
        Tree tree = (Tree) getIntent().getSerializableExtra(CID);
        int pos = getIntent().getIntExtra(POS, 0);
        List<String> titles = new ArrayList<>();
        if (tree != null) {
            for (int i = 0; i < tree.getChildren().size(); i++) {
                titles.add(tree.getChildren().get(i).getName());
            }
        }
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), titles);
        for (int i = 0; i < titles.size(); i++) {
            if (tree != null) {
                commonViewPagerAdapter.addFragment(TreeGrandsonFragment.newInstance(tree.getChildren().get(i).getId()));
            }
        }
        mVpTreeGrandson.setAdapter(commonViewPagerAdapter);
        mVpTreeGrandson.setCurrentItem(pos);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}