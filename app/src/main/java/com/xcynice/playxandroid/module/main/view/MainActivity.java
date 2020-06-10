package com.xcynice.playxandroid.module.main.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.module.main.adapter.MainViewPagerAdapter;
import com.xcynice.playxandroid.module.main.listener.MainBnvListener;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    /**
     * 保存用户按返回键的时间
     */
    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        initListener();
    }

    private void initListener() {
        MainBnvListener bnvListener = new MainBnvListener(mVpMain);
        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBnvMain.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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


}