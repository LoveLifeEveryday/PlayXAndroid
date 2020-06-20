package com.xcynice.playxandroid.module.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.bean.MessageLogoutSuccessWrap;
import com.xcynice.playxandroid.bean.SettingChangeEvent;
import com.xcynice.playxandroid.module.mine.presenter.SettingPresenter;
import com.xcynice.playxandroid.module.mine.view.ISettingView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xcynice.playxandroid.util.SpUtil.COIN;
import static com.xcynice.playxandroid.util.SpUtil.IS_LOGIN;
import static com.xcynice.playxandroid.util.SpUtil.NICK_NAME;
import static com.xcynice.playxandroid.util.SpUtil.PASSWORD;
import static com.xcynice.playxandroid.util.SpUtil.USERNAME;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/19
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SettingActivity
 */


public class SettingActivity extends BaseActivity<SettingPresenter> implements ISettingView {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.tv_hide_banner_title)
    TextView mTvHideBannerTitle;
    @BindView(R.id.tv_show_banner_desc)
    TextView mTvShowBannerDesc;
    @BindView(R.id.sc_hide_banner)
    SwitchCompat mScShowBanner;
    @BindView(R.id.tv_hide_about_me_title)
    TextView mTvHideAboutMeTitle;
    @BindView(R.id.tv_hide_about_me_desc)
    TextView mTvHideAboutMeDesc;
    @BindView(R.id.sc_hide_about_me)
    SwitchCompat mScHideAboutMe;
    @BindView(R.id.tv_hide_open_title)
    TextView mTvHideOpenTitle;
    @BindView(R.id.tv_hide_open_desc)
    TextView mTvHideOpenDesc;
    @BindView(R.id.sc_hide_open)
    SwitchCompat mScHideOpen;
    @BindView(R.id.tv_curr_version)
    TextView mTvCurrVersion;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;
    @BindView(R.id.ll_logout)
    LinearLayout mLlLogout;


    boolean mShowBanner;
    boolean mHideAboutMe;
    boolean mHideOpenSource;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setImageResource(R.drawable.back);
        mTvTitleCenter.setText("设置");
        mIvTitleRight.setVisibility(View.INVISIBLE);
        ImmersionBar.with(this).titleBar(mRlTitle).init();

        mShowBanner = SpUtil.getBoolean(SpUtil.HIDE_BANNER);
        mHideAboutMe = SpUtil.getBoolean(SpUtil.HIDE_ABOUT_ME);
        mHideOpenSource = SpUtil.getBoolean(SpUtil.HIDE_OPEN_SOURCE);
        mScShowBanner.setChecked(mShowBanner);
        mScHideAboutMe.setChecked(mHideAboutMe);
        mScHideOpen.setChecked(mHideOpenSource);

        mScShowBanner.setOnCheckedChangeListener((compoundButton, b) -> SpUtil.setBoolean(SpUtil.HIDE_BANNER, b));
        mScHideAboutMe.setOnCheckedChangeListener((compoundButton, b) -> SpUtil.setBoolean(SpUtil.HIDE_ABOUT_ME, b));
        mScHideOpen.setOnCheckedChangeListener((compoundButton, b) -> SpUtil.setBoolean(SpUtil.HIDE_OPEN_SOURCE, b));

        if (!SpUtil.getBoolean(IS_LOGIN)) {
            mLlLogout.setVisibility(View.INVISIBLE);
        } else {
            mLlLogout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {

    }


    @Override
    public void logoutSuccess(String msg) {
        ToastUtil.showToast(msg);
        SpUtil.setBoolean(IS_LOGIN, false);
        SpUtil.setString(USERNAME, "");
        SpUtil.setString(PASSWORD, "");
        SpUtil.setString(NICK_NAME, "");
        SpUtil.setInt(COIN, 0);

        //通知刷新界面
        MessageLogoutSuccessWrap event = new MessageLogoutSuccessWrap();
        event.setLogoutSuccess(true);
        EventBus.getDefault().post(event);

        finish();
    }

    @Override
    public void logoutFail(String msg) {
        ToastUtil.showToast(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        postSettingChangedEvent();
    }


    private void postSettingChangedEvent() {
        boolean showBannerChanged = mShowBanner != SpUtil.getBoolean(SpUtil.HIDE_BANNER);
        boolean hideAboutMeChanged = mHideAboutMe != SpUtil.getBoolean(SpUtil.HIDE_ABOUT_ME);
        boolean hideOpenChanged = mHideOpenSource != SpUtil.getBoolean(SpUtil.HIDE_OPEN_SOURCE);

        if (showBannerChanged || hideAboutMeChanged || hideOpenChanged) {
            SettingChangeEvent event = new SettingChangeEvent();
            event.setShowBannerChanged(showBannerChanged);
            event.setHideAboutMeChanged(hideAboutMeChanged);
            event.setHideOpenChanged(hideOpenChanged);
            EventBus.getDefault().post(event);
        }

    }

    @OnClick({R.id.iv_title_left, R.id.ll_about, R.id.ll_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.ll_about:
                ActivityUtil.startActivity(AboutUsActivity.class);
                break;
            case R.id.ll_logout:
                presenter.logout();
                break;
            default:
                break;
        }
    }
}