package com.xcynice.playxandroid.module.mine.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.MessageLoginSuccessWrap;
import com.xcynice.playxandroid.bean.UserInfo;
import com.xcynice.playxandroid.module.login.activity.LoginActivity;
import com.xcynice.playxandroid.module.mine.activity.CoinActivity;
import com.xcynice.playxandroid.module.mine.activity.MineShareActivity;
import com.xcynice.playxandroid.module.mine.presenter.MinePresenter;
import com.xcynice.playxandroid.module.mine.view.IMineView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/9
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineFragment
 */


public class MineFragment extends BaseFragment<MinePresenter> implements IMineView {


    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.riv_mine)
    RoundedImageView mRivMine;
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

        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mTvTitleCenter.setVisibility(View.INVISIBLE);
        mIvTitleLeft.setVisibility(View.INVISIBLE);
        mIvTitleRight.setImageResource(R.drawable.notification);
        mIvTitleRight.setOnClickListener(view -> {
            // TODO: 2020/6/13 设置点击事件跳转到通知界面
        });
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
            mTvUsernameMine.setText("请登陆");
            mLlUserIdMine.setVisibility(View.INVISIBLE);
            mLlUserLevelRanking.setVisibility(View.INVISIBLE);
            mTvCoinMine.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.iv_title_right, R.id.riv_mine, R.id.ll_coin_mine, R.id.ll_share_mine, R.id.ll_collect_mine, R.id.ll_open_mine, R.id.ll_about_me_mine, R.id.ll_setting_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_right:
                break;
            case R.id.riv_mine:
                ActivityUtil.startActivity(LoginActivity.class);
                break;
            case R.id.ll_coin_mine:
                if (SpUtil.getBoolean(SpUtil.IS_LOGIN)){
                    ActivityUtil.startActivity(CoinActivity.class);
                }else {
                    ActivityUtil.startActivity(LoginActivity.class);
                }
                break;
            case R.id.ll_share_mine:
                if (SpUtil.getBoolean(SpUtil.IS_LOGIN)){
                    ActivityUtil.startActivity(MineShareActivity.class);
                }else {
                    ActivityUtil.startActivity(LoginActivity.class);
                }
                break;
            case R.id.ll_collect_mine:
                break;
            case R.id.ll_open_mine:
                break;
            case R.id.ll_about_me_mine:
                break;
            case R.id.ll_setting_mine:
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLoginMsg(MessageLoginSuccessWrap messageWrap) {
        if (messageWrap.getMsg().equals("refresh user info")) {
            initUserData();
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
    }
}