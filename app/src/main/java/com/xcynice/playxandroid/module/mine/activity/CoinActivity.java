package com.xcynice.playxandroid.module.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.CoinRecordAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.bean.Coin;
import com.xcynice.playxandroid.module.mine.presenter.CoinPresenter;
import com.xcynice.playxandroid.module.mine.view.ICoinView;
import com.xcynice.playxandroid.util.AnimatorUtils;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinActivity extends BaseActivity<CoinPresenter> implements ICoinView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.tv_coin)
    TextView mTvCoin;
    @BindView(R.id.rv_coin)
    RecyclerView mRvCoin;
    @BindView(R.id.title_coin)
    RelativeLayout mRlTitle;

    private CoinRecordAdapter mCoinRecordAdapter = null;

    /**
     * 上一次加载的数量，方便进行是否加载到最后一页的判断： if (mCurrentCounter < TOTAL_COUNTER)
     */
    private int mCurrentCounter;

    /**
     * 每一次加载的数量
     */
    private final static int SINGLE_PAGE_TOTAL_COUNTER = 20;

    /**
     * 记录分页，方便进行加载更多
     */
    private int mPage = 1;

    private List<Coin.DatasBean> mCoinList = new ArrayList<>();


    @Override
    protected CoinPresenter createPresenter() {
        return new CoinPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mTvTitleCenter.setText("我的积分");
        mIvTitleLeft.setImageResource(R.drawable.back);
        mIvTitleLeft.setOnClickListener(view -> finish());
        mIvTitleRight.setImageResource(R.drawable.ic_rank);
        mIvTitleRight.setOnClickListener(view -> {
            // TODO: 2020/6/13 打开积分排行榜界面
        });
        mRvCoin.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void initData() {
        mTvCoin.setText("0");
        AnimatorUtils.doIntAnim(mTvCoin, SpUtil.getInt(SpUtil.COIN), 1000);
        //初始化页数
        mPage = 1;
        presenter.getCoinFirst();
    }


    @Override
    public void setCoinFirstSuccess(BaseBean<Coin> coinBaseBean) {
        mCoinRecordAdapter = new CoinRecordAdapter(coinBaseBean.data.getDatas());
        mCoinList = coinBaseBean.data.getDatas();
        mCurrentCounter = coinBaseBean.data.getSize();
        mRvCoin.setAdapter(mCoinRecordAdapter);
        mCoinRecordAdapter.openLoadAnimation();
        mCoinRecordAdapter.setOnLoadMoreListener(this, mRvCoin);

    }

    @Override
    public void setCoinFirstFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void setCoinMoreSuccess(BaseBean<Coin> coinBaseBean) {
        mCurrentCounter = coinBaseBean.data.getDatas().size();
        mCoinRecordAdapter.addData(coinBaseBean.data.getDatas());
        mCoinRecordAdapter.loadMoreComplete();
    }

    @Override
    public void setCoinMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mCoinRecordAdapter.loadMoreFail();
    }


    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                break;
            case R.id.iv_title_right:
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mRvCoin.postDelayed(() -> {
            if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
                mCoinRecordAdapter.loadMoreEnd();
            } else {
                presenter.getCoinMore(++mPage);
            }
        }, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}