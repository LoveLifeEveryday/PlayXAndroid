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
import com.xcynice.playxandroid.adapter.ArticleAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.mine.presenter.MineCollectPresenter;
import com.xcynice.playxandroid.module.mine.view.IMineCollectView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;
import com.xcynice.playxandroid.util.XUtil;

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
 * @Description MineCollectActivity
 */


public class MineCollectActivity extends BaseActivity<MineCollectPresenter> implements IMineCollectView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.rv_mine_collect)
    RecyclerView mRvMineCollect;
    @BindView(R.id.srl_mine_collect)
    SwipeRefreshLayout mSrlMineCollect;

    private ArticleAdapter mAdapter;
    private List<Article.DataDetailBean> mArticleList = new ArrayList<>();
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
    private int mPage = 0;

    /**
     * 记录点击事件的位置，方便后面进行收藏
     */
    private int mPosition;

    @Override
    protected MineCollectPresenter createPresenter() {
        return new MineCollectPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_collect;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setImageResource(R.drawable.back);
        mTvTitleCenter.setText(XUtil.getString(R.string.MyCollection));
        mIvTitleRight.setVisibility(View.INVISIBLE);
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mSrlMineCollect.setColorSchemeResources(R.color.colorPrimary);
        mRvMineCollect.setLayoutManager(new LinearLayoutManager(this));
        mRvMineCollect.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        presenter.getMineCollectFirst();
        mSrlMineCollect.setOnRefreshListener(() -> {
            mSrlMineCollect.setRefreshing(true);
            presenter.getMineCollectRefresh();
        });
    }


    @Override
    public void setMineCollectFirstSuccess(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mAdapter = new ArticleAdapter(R.layout.item_article_list, mArticleList);
        mAdapter.setType(true);
        mRvMineCollect.setAdapter(mAdapter);
        mAdapter.openLoadAnimation();
        // 设置点击事件
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnLoadMoreListener(this, mRvMineCollect);
    }

    @Override
    public void setMineCollectFirstFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void setMineCollectRefreshSuccess(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mAdapter.setNewData(mArticleList);
        mPage = 0;
        stopRefresh();
    }

    @Override
    public void setMineCollectRefreshFail(String msg) {
        ToastUtil.showToast(msg);
        stopRefresh();
    }


    private void stopRefresh() {
        if (mSrlMineCollect.isRefreshing()) {
            mSrlMineCollect.setRefreshing(false);
        }
    }

    @Override
    public void setMineCollectSuccessMoreSuccess(BaseBean<Article> list) {
        mCurrentCounter = list.data.datas.size();
        mAdapter.addData(list.data.datas);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void setMineCollectSuccessMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mAdapter.loadMoreFail();
    }

    @Override
    public void unCollectSuccess(String msg) {
        mArticleList.get(mPosition).collect = false;
        mAdapter.remove(mPosition);
        mAdapter.notifyDataSetChanged();
        if (mArticleList.size() == 0) {
            presenter.getMineCollectFirst();
        }
    }

    @Override
    public void unCollectFail(String msg) {
        ToastUtil.showToast(msg);
    }


    @OnClick(R.id.iv_title_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mArticleList.size() != 0) {
            HashMap<String, String> hashMap = new HashMap<>(2);
            hashMap.put(ArticleDetailActivity.WEB_URL, mArticleList.get(position).link);
            hashMap.put(ArticleDetailActivity.WEB_TITLE, mArticleList.get(position).title);
            ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.iv_article_favorite) {
            mPosition = position;
            presenter.unCollect(mArticleList.get(position).id, Math.max(-1, mArticleList.get(position).originId));
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mRvMineCollect.postDelayed(() -> {
            if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
                mAdapter.loadMoreEnd();
            } else {
                presenter.getMineCollectMore(++mPage);
            }
        }, 1000);
    }
}