package com.xcynice.playxandroid.module.main.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.module.main.adapter.MainViewPagerAdapter;
import com.xcynice.playxandroid.module.main.listener.MainBnvListener;
import com.xcynice.playxandroid.module.main.listener.MainVpListener;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description : MainActivity
 *
 * @author XuCanyou666
 * @date 2020/6/1
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.bnv_main)
    BottomNavigationView mBnvMain;

    private static final int OVER_TIME = 2000;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    /**
     * 保存用户按返回键的时间
     */
    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        initViewPager();
        initListener();
    }

    private void initListener() {
        MainVpListener vpListener = new MainVpListener(mBnvMain, mTvTitleCenter, mIvTitleLeft, mIvTitleRight, mRlTitle);
        MainBnvListener bnvListener = new MainBnvListener(mVpMain);
        mVpMain.addOnPageChangeListener(vpListener);
        mBnvMain.setOnNavigationItemSelectedListener(bnvListener);
    }


    private void initViewPager() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mVpMain.setOffscreenPageLimit(3);
        mVpMain.setAdapter(adapter);
    }


    /**
     * 根据当前时间节点判断是否退出,双击退出功能的设置
     */
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > OVER_TIME) {
            ToastUtil.showToast(getResources().getString(R.string.double_quit) + getResources().getString(R.string.app_name));
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityUtil.closeAllActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tb_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            ToastUtil.showToast(getResources().getString(R.string.click_search));
        }
        return true;
    }

    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                ToastUtil.showToast("你点击了左边的按钮");
                break;
            case R.id.iv_title_right:
                ToastUtil.showToast("你点击了右边的按钮");
                break;
            default:
                break;
        }
    }
}