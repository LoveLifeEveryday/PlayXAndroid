package com.xcynice.playxandroid.module.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.MineShareAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.bean.MineShare;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.mine.presenter.MineSharePresenter;
import com.xcynice.playxandroid.module.mine.view.IMineShareView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineShareActivity 我的分享界面
 */


public class MineShareActivity extends BaseActivity<MineSharePresenter> implements IMineShareView, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.rv_mine_share)
    RecyclerView mRvMineShare;
    @BindView(R.id.srl_mine_share)
    SwipeRefreshLayout mSrlMineShare;

    private MineShareAdapter mAdapter;
    private List<MineShare.ShareArticlesBean.DatasBean> mMineShareList = new ArrayList<>();


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

    /**
     * 记录点击事件的位置，方便后面进行收藏
     */
    private int mPosition;

    @Override
    protected MineSharePresenter createPresenter() {
        return new MineSharePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_share;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setImageResource(R.drawable.back);
        mIvTitleRight.setImageResource(R.drawable.ic_add);
        mTvTitleCenter.setText("我的分享");
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mSrlMineShare.setColorSchemeResources(R.color.colorPrimary);
        mRvMineShare.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        presenter.getMineShareFirst();
        mSrlMineShare.setOnRefreshListener(() -> {
            mSrlMineShare.setRefreshing(true);
            presenter.getMineShareRefresh();
        });
    }


    @Override
    public void setShareFirstSuccess(MineShare.ShareArticlesBean list) {
        mMineShareList = list.getDatas();
        mCurrentCounter = list.getDatas().size();
        mAdapter = new MineShareAdapter(mMineShareList);
        mRvMineShare.setAdapter(mAdapter);

        mAdapter.openLoadAnimation();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnLoadMoreListener(this, mRvMineShare);
    }

    @Override
    public void setShareFirstFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void setShareMoreSuccess(MineShare.ShareArticlesBean list) {
        mCurrentCounter = list.getDatas().size();
        mAdapter.addData(list.getDatas());
        mAdapter.loadMoreComplete();
    }


    @Override
    public void setShareMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mAdapter.loadMoreFail();
    }

    @Override
    public void setShareRefreshSuccess(MineShare.ShareArticlesBean list) {
        mCurrentCounter = list.getDatas().size();
        mMineShareList = list.getDatas();
        mAdapter.setNewData(mMineShareList);
        mPage = 1;
        if (mSrlMineShare.isRefreshing()){
            mSrlMineShare.setRefreshing(false);
        }
    }


    @Override
    public void setShareRefreshFail(String msg) {
        ToastUtil.showToast(msg);
        if (mSrlMineShare.isRefreshing()){
            mSrlMineShare.setRefreshing(false);
        }
    }

    @Override
    public void showCollectSuccess(String successMessage) {
        mMineShareList.get(mPosition).setCollect(true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showUnCollectSuccess(String successMessage) {
        mMineShareList.get(mPosition).setCollect(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUnCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }


    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.iv_title_right:
                // TODO: 2020/6/15 跳转到添加文章的界面
                
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mRvMineShare.postDelayed(() -> {
            if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
                mAdapter.loadMoreEnd();
            } else {
                presenter.getMineShareMore(++mPage);
            }
        }, 1000);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.iv_article_favorite) {
            mPosition = position;
            if (mMineShareList.get(mPosition).isCollect()) {
                presenter.unCollect(mMineShareList.get(mPosition).getId());
            } else {
                presenter.collect(mMineShareList.get(mPosition).getId());
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mMineShareList.size()) {
            HashMap<String, String> hashMap = new HashMap<>(2);
            hashMap.put(ArticleDetailActivity.WEB_URL, mMineShareList.get(position).getLink());
            hashMap.put(ArticleDetailActivity.WEB_TITLE, mMineShareList.get(position).getTitle());
            ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
        }
    }
}