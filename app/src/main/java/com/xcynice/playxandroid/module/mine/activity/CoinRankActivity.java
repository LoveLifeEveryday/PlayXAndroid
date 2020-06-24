package com.xcynice.playxandroid.module.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.CoinRankAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.bean.CoinRank;
import com.xcynice.playxandroid.module.mine.presenter.CoinRankPresenter;
import com.xcynice.playxandroid.module.mine.view.ICoinRankView;
import com.xcynice.playxandroid.util.ToastUtil;
import com.xcynice.playxandroid.util.XUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description CoinRankActivity
 */


public class CoinRankActivity extends BaseActivity<CoinRankPresenter> implements ICoinRankView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.rv_coin_rank)
    RecyclerView mRvCoinRank;

    /**
     * 上一次加载的数量，方便进行是否加载到最后一页的判断： if (mCurrentCounter < TOTAL_COUNTER)
     */
    private int mCurrentCounter;

    /**
     * 每一次加载的数量
     */
    private final static int SINGLE_PAGE_TOTAL_COUNTER = 30;

    /**
     * 记录分页，方便进行加载更多
     */
    private int mPage = 1;


    private CoinRankAdapter mCoinRankAdapter;

    @Override
    protected CoinRankPresenter createPresenter() {
        return new CoinRankPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin_rank;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mTvTitleCenter.setText(XUtil.getString(R.string.Leaderboard));
        mIvTitleLeft.setImageResource(R.drawable.back);
        mIvTitleRight.setVisibility(View.INVISIBLE);
        mRvCoinRank.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mPage = 1;
        presenter.getCoinRankFirst();
    }


    @Override
    public void setCoinRankFirstSuccess(BaseBean<CoinRank> coinRankBaseBean) {
        mCoinRankAdapter = new CoinRankAdapter(coinRankBaseBean.data.getDatas());
        mCurrentCounter = coinRankBaseBean.data.getDatas().size();
        mRvCoinRank.setAdapter(mCoinRankAdapter);
        mCoinRankAdapter.openLoadAnimation();
        mCoinRankAdapter.setOnLoadMoreListener(this, mRvCoinRank);
    }

    @Override
    public void setCoinRankFirstFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void setCoinRankMoreSuccess(BaseBean<CoinRank> coinRankBaseBean) {
        mCurrentCounter = coinRankBaseBean.data.getDatas().size();
        mCoinRankAdapter.addData(coinRankBaseBean.data.getDatas());
        mCoinRankAdapter.loadMoreComplete();
    }

    @Override
    public void setCoinRankMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mCoinRankAdapter.loadMoreFail();
    }


    @OnClick(R.id.iv_title_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
            mCoinRankAdapter.loadMoreEnd();
        } else {
            presenter.getCoinRankMore(++mPage);
        }
    }

}