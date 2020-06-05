package com.xcynice.playxandroid.module.tree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TreeGrandsonActivity
 */


public class TreeGrandsonActivity extends BaseActivity {

    public static final String CID = "com/xcynice/playxandroid/module/tree/TreeGrandsonActivity.java/CID";
    public static final String TITLE = "com/xcynice/playxandroid/module/tree/TreeGrandsonActivity.java/TITLE";
    public static final String POS = "com/xcynice/playxandroid/module/tree/TreeGrandsonActivity.java/POS";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tree_grandson;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}