package com.xcynice.playxandroid.module.login.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.CommonViewPagerAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.module.login.fragment.LoginFragment;
import com.xcynice.playxandroid.module.login.fragment.RegisterFragment;
import com.xcynice.playxandroid.module.login.widget.LogoAnimView;
import com.xcynice.playxandroid.util.SoftInputHelper;
import com.xcynice.playxandroid.util.XUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import per.goweii.percentimageview.percentimageview.PercentImageView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/22
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description LoginActivity
 */


@SuppressWarnings("rawtypes")
public class LoginActivity extends BaseActivity {


    @BindView(R.id.tb_login)
    Toolbar mTbLogin;
    @BindView(R.id.lav_login)
    LogoAnimView mLavLogin;
    @BindView(R.id.ll_logo_login)
    LinearLayout mLlLogoLogin;
    @BindView(R.id.iv_circle_2)
    PercentImageView mIvCircle2;
    @BindView(R.id.iv_circle_1)
    PercentImageView mIvCircle1;
    @BindView(R.id.vp_login)
    ViewPager mVpLogin;
    @BindView(R.id.rl_input_login)
    RelativeLayout mRlInputLogin;

    private final int mMaxMoveDistanceX = 200;
    private final int mMaxMoveDistanceY = 20;
    private Random mRandom = new Random();
    private boolean isRunning = false;
    private AnimatorSet mSet1;
    private AnimatorSet mSet2;
    private SoftInputHelper mSoftInputHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(mTbLogin).init();
        setSupportActionBar(mTbLogin);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mTbLogin.setNavigationOnClickListener(view -> finish());
            mTbLogin.setNavigationIcon(R.drawable.back);
        }

        mSoftInputHelper = SoftInputHelper.attach(this).moveBy(mRlInputLogin);
        List<String> titleList = new ArrayList<>();
        titleList.add(XUtil.getString(R.string.login));
        titleList.add(XUtil.getString(R.string.register));
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), titleList);
        commonViewPagerAdapter.addFragment(new LoginFragment());
        commonViewPagerAdapter.addFragment(new RegisterFragment());
        mVpLogin.setAdapter(commonViewPagerAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        isRunning = true;
        mSet1 = startCircleAnim(mIvCircle1);
        mSet2 = startCircleAnim(mIvCircle2);
        super.onStart();
    }

    @Override
    protected void onStop() {
        isRunning = false;
        cancelAnimatorSet(mSet1, mSet2);
        super.onStop();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mLavLogin.randomBlink();
    }


    /**
     * 开启眼睛的动画
     *
     * @param close 是否关闭动画
     */
    public void doEyeAnim(boolean close) {
        if (close) {
            mLavLogin.close(null);
        } else {
            mLavLogin.open(() -> {
                if (mLavLogin != null) {
                    mLavLogin.randomBlink();
                }
                return null;
            });
        }
    }


    /**
     * 取消 animatorSet
     *
     * @param animatorSets animatorSet 的列表
     */
    private void cancelAnimatorSet(AnimatorSet... animatorSets) {
        for (AnimatorSet animatorSet : animatorSets) {
            if (animatorSet != null) {
                animatorSet.cancel();
            }
        }
    }


    /**
     * 开启圆圈的动画
     *
     * @param target 目标
     * @return 动画
     */
    private AnimatorSet startCircleAnim(View target) {
        if (target == null) {
            return null;
        }
        float[] xy = calculateRandomXY();
        AnimatorSet set = createTranslationAnimator(target, xy[0], xy[1]);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isRunning) {
                    startCircleAnim(target);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        set.start();
        return set;
    }

    private AnimatorSet createTranslationAnimator(View target, float toX, float toY) {
        float fromX = target.getTranslationX();
        float fromY = target.getTranslationY();
        long duration = calculateDuration(fromX, fromY, toX, toY);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(target, XUtil.getString(R.string.translationX), fromX, toX);
        animatorX.setDuration(duration);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(target, XUtil.getString(R.string.translationY), fromY, toY);
        animatorY.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        return set;
    }

    private long calculateDuration(float x1, float y1, float x2, float y2) {
        float distance = (float) Math.abs(Math.sqrt(Math.pow(Math.abs((x1 - x2)), 2) + Math.pow(Math.abs((y1 - y2)), 2)));
        float maxDistance = (float) Math.abs(Math.sqrt(Math.pow(mMaxMoveDistanceX, 2) + Math.pow(mMaxMoveDistanceY, 2)));
        long mMaxMoveDuration = 10000L;
        return (long) (mMaxMoveDuration * (distance / maxDistance));
    }

    /**
     * 计算随机的 X Y 坐标
     *
     * @return x y 坐标的集合
     */
    private float[] calculateRandomXY() {
        float x = mRandom.nextInt(mMaxMoveDistanceX) - (mMaxMoveDistanceX * 0.5F);
        float y = mRandom.nextInt(mMaxMoveDistanceY) - (mMaxMoveDistanceY * 0.5F);
        return new float[]{x, y};
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
    }

    public SoftInputHelper getSoftInputHelper() {
        return mSoftInputHelper;
    }

    /**
     * 跳转到注册的 Fragment
     */
    public void changeToRegister() {
        if (mVpLogin != null) {
            mVpLogin.setCurrentItem(1);
        }
    }

    /**
     * 跳转到登陆的 Fragment
     */
    public void changeToLogin() {
        if (mVpLogin != null) {
            mVpLogin.setCurrentItem(0);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}