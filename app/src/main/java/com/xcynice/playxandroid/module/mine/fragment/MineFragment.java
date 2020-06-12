package com.xcynice.playxandroid.module.mine.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.makeramen.roundedimageview.RoundedImageView;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.module.login.activity.LoginActivity;
import com.xcynice.playxandroid.util.ActivityUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/9
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineFragment
 */


public class MineFragment extends BaseFragment {


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
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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
                break;
            case R.id.ll_share_mine:
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
}