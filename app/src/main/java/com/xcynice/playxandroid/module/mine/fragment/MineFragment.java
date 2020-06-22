package com.xcynice.playxandroid.module.mine.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mmkv.MMKV;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.HeadIcon;
import com.xcynice.playxandroid.bean.MessageLoginSuccessWrap;
import com.xcynice.playxandroid.bean.MessageLogoutSuccessWrap;
import com.xcynice.playxandroid.bean.SettingChangeEvent;
import com.xcynice.playxandroid.bean.UserInfo;
import com.xcynice.playxandroid.module.login.activity.LoginActivity;
import com.xcynice.playxandroid.module.mine.activity.AboutMeActivity;
import com.xcynice.playxandroid.module.mine.activity.CoinActivity;
import com.xcynice.playxandroid.module.mine.activity.MineCollectActivity;
import com.xcynice.playxandroid.module.mine.activity.MineShareActivity;
import com.xcynice.playxandroid.module.mine.activity.OpenSourceActivity;
import com.xcynice.playxandroid.module.mine.activity.SettingActivity;
import com.xcynice.playxandroid.module.mine.presenter.MinePresenter;
import com.xcynice.playxandroid.module.mine.view.IMineView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.ToastUtil;
import com.xcynice.playxandroid.util.XUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/9
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineFragment
 */


public class MineFragment extends BaseFragment<MinePresenter> implements IMineView {


    @BindView(R.id.riv_mine)
    CircleImageView mRivMine;
    @BindView(R.id.tv_username_mine)
    TextView mTvUsernameMine;
    @BindView(R.id.tv_user_id_mine)
    TextView mTvUserIdMine;
    @BindView(R.id.ll_user_id_mine)
    LinearLayout mLlUserIdMine;
    @BindView(R.id.tv_user_level_mine)
    TextView mTvUserLevelMine;
    @BindView(R.id.ll_user_level)
    LinearLayout mLlUserLevel;
    @BindView(R.id.tv_user_ranking_mine)
    TextView mTvUserRankingMine;
    @BindView(R.id.ll_user_ranking_mine)
    LinearLayout mLlUserRankingMine;
    @BindView(R.id.ll_user_level_ranking)
    LinearLayout mLlUserLevelRanking;
    @BindView(R.id.rl_mine)
    RelativeLayout mRlMine;
    @BindView(R.id.tv_coin_mine)
    TextView mTvCoinMine;
    @BindView(R.id.ll_coin_mine)
    LinearLayout mLlCoinMine;
    @BindView(R.id.ll_share_mine)
    LinearLayout mLlShareMine;
    @BindView(R.id.ll_collect_mine)
    LinearLayout mLlCollectMine;
    @BindView(R.id.ll_open_mine)
    LinearLayout mLlOpenMine;
    @BindView(R.id.ll_about_me_mine)
    LinearLayout mLlAboutMeMine;
    @BindView(R.id.ll_setting_mine)
    LinearLayout mLlSettingMine;
    @BindView(R.id.srl_mine)
    SwipeRefreshLayout mSrlMine;
    private static final int REQUEST_CODE_CHOOSE = 123;
    private RxPermissions mRxPermissions;
    private MMKV mKv = MMKV.defaultMMKV();

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mSrlMine.setColorSchemeResources(R.color.colorPrimary);
        mRxPermissions = new RxPermissions(this);
        ImmersionBar.with(this).titleBar(mRlMine).init();
        setMenuVisible();

        HeadIcon headIcon = mKv.decodeParcelable(SpUtil.HEAD_ICON, HeadIcon.class);
        if (headIcon != null) {
            Glide.with(MineFragment.this).load(headIcon.getHeadIcon()).into(mRivMine);
        }
    }

    @Override
    protected void initData() {

        mSrlMine.setOnRefreshListener(() -> {
            mSrlMine.setRefreshing(true);
            initUserData();
        });
        initUserData();
    }

    /**
     * 初始化用户数据
     */
    private void initUserData() {
        //如果已经登陆
        if (SpUtil.getBoolean(SpUtil.IS_LOGIN)) {
            mTvUsernameMine.setText(SpUtil.getString(SpUtil.NICK_NAME));
            mLlUserIdMine.setVisibility(View.VISIBLE);
            mLlUserLevelRanking.setVisibility(View.VISIBLE);
            mTvCoinMine.setVisibility(View.VISIBLE);
            presenter.getUserInfo();
        } else {
            mTvUsernameMine.setText(XUtil.getString(R.string.PleaseLogIn));
            mLlUserIdMine.setVisibility(View.INVISIBLE);
            mLlUserLevelRanking.setVisibility(View.INVISIBLE);
            mTvCoinMine.setVisibility(View.INVISIBLE);
            if (mSrlMine.isRefreshing()) {
                mSrlMine.setRefreshing(false);
            }
        }
    }

    @SuppressLint("CheckResult")
    @OnClick({R.id.riv_mine, R.id.ll_coin_mine, R.id.ll_share_mine, R.id.ll_collect_mine, R.id.ll_open_mine, R.id.ll_about_me_mine, R.id.ll_setting_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_mine:
                if (SpUtil.getBoolean(SpUtil.IS_LOGIN)) {
                    //noinspection ResultOfMethodCallIgnored
                    mRxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) {
                                    Matisse.from(MineFragment.this)
                                            .choose(MimeType.ofImage())
                                            .countable(false)
                                            .maxSelectable(1)
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                            .thumbnailScale(0.85f)
                                            .imageEngine(new GlideEngine())
                                            .showPreview(true)
                                            .forResult(REQUEST_CODE_CHOOSE);
                                }
                            });
                } else {
                    ActivityUtil.startActivity(LoginActivity.class);
                }
                break;
            case R.id.ll_coin_mine:
                checkLogin(CoinActivity.class);
                break;
            case R.id.ll_share_mine:
                checkLogin(MineShareActivity.class);
                break;
            case R.id.ll_collect_mine:
                checkLogin(MineCollectActivity.class);
                break;
            case R.id.ll_open_mine:
                ActivityUtil.startActivity(OpenSourceActivity.class);
                break;
            case R.id.ll_about_me_mine:
                ActivityUtil.startActivity(AboutMeActivity.class);
                break;
            case R.id.ll_setting_mine:
                ActivityUtil.startActivity(SettingActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                List<Uri> mHeadIcon = Matisse.obtainResult(data);
                Glide.with(MineFragment.this).load(mHeadIcon.get(0)).into(mRivMine);
                HeadIcon headIcon = new HeadIcon(mHeadIcon.get(0));
                // 将头像存入 mmkv
                mKv.encode(SpUtil.HEAD_ICON, headIcon);
            }

        }
    }

    /**
     * 检测登陆后自动跳转到对应的界面
     *
     * @param toClass 跳转到的界面
     */
    private void checkLogin(@SuppressWarnings("rawtypes") Class toClass) {
        if (SpUtil.getBoolean(SpUtil.IS_LOGIN)) {
            ActivityUtil.startActivity(toClass);
        } else {
            ActivityUtil.startActivity(LoginActivity.class);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLoginMsg(MessageLoginSuccessWrap messageWrap) {
        if (messageWrap.getMsg().equals(XUtil.getString(R.string.refreshUser))) {
            initUserData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logoutSuccess(MessageLogoutSuccessWrap messageLogoutSuccessWrap) {
        if (messageLogoutSuccessWrap.isLogoutSuccess()) {
            initUserData();
            Glide.with(MineFragment.this).load(R.drawable.image_holder).into(mRivMine);
            mKv.clearAll();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettingChangeEvent(SettingChangeEvent event) {
        if (isDetached()) {
            return;
        }
        // 如果有其中一个配置发生改变
        if (event.isHideAboutMeChanged() || event.isHideOpenChanged() || event.isShowBannerChanged()) {
            setMenuVisible();
        }
    }


    /**
     * 设置菜单项的显示
     */
    private void setMenuVisible() {
        if (SpUtil.getBoolean(SpUtil.HIDE_ABOUT_ME)) {
            mLlAboutMeMine.setVisibility(View.GONE);
        } else {
            mLlAboutMeMine.setVisibility(View.VISIBLE);
        }
        if (SpUtil.getBoolean(SpUtil.HIDE_OPEN_SOURCE)) {
            mLlOpenMine.setVisibility(View.GONE);
        } else {
            mLlOpenMine.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setUserInfoSuccess(BaseBean<UserInfo> userInfo) {
        mTvUserIdMine.setText(userInfo.data.getUserId() + "");
        mTvUserLevelMine.setText(userInfo.data.getLevel() + "");
        mTvUserRankingMine.setText(userInfo.data.getRank());
        mTvCoinMine.setText(userInfo.data.getCoinCount() + "");
        SpUtil.setInt(SpUtil.COIN, userInfo.data.getCoinCount());
        if (mSrlMine.isRefreshing()) {
            mSrlMine.setRefreshing(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserInfoFail(String msg) {
        ToastUtil.showToast(msg);
        if (mSrlMine.isRefreshing()) {
            mSrlMine.setRefreshing(false);
        }
    }
}