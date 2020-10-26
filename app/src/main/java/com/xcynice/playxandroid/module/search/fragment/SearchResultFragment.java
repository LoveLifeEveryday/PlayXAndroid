package com.xcynice.playxandroid.module.search.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.ArticleAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.search.presenter.SearchResultPresenter;
import com.xcynice.playxandroid.module.search.view.ISearchResultView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SearchResultFragment
 */


public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements ISearchResultView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl_search_result)
    SwipeRefreshLayout mSrlSearchResult;


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
    private String mKey;
    private ArticleAdapter mAdapter;

    @Override
    protected SearchResultPresenter createPresenter() {
        return new SearchResultPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void initView() {
        mSrlSearchResult.setColorSchemeResources(R.color.colorPrimary);
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ArticleAdapter(R.layout.item_article_list);
        mAdapter.openLoadAnimation();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnLoadMoreListener(this, mRv);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mSrlSearchResult.setOnRefreshListener(() -> {
            mSrlSearchResult.setRefreshing(true);
            presenter.searchFresh(mKey);
        });
    }


    /**
     * 搜索
     *
     * @param key 关键词
     */
    public void search(String key) {
        mAdapter.setNewData(null);
        mKey = key;
        presenter.searchFirst(key);
    }


    @Override
    public void searchFirstSuccess(BaseBean<Article> bean) {
        mArticleList = bean.data.datas;
        mCurrentCounter = mArticleList.size();
        mAdapter.setNewData(mArticleList);
        mPage = 0;
    }

    @Override
    public void searchFirstFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void searchFreshSuccess(BaseBean<Article> bean) {
        mArticleList = bean.data.datas;
        mCurrentCounter = mArticleList.size();
        mAdapter.setNewData(mArticleList);
        mPage = 0;
        if (mSrlSearchResult.isRefreshing()) {
            mSrlSearchResult.setRefreshing(false);
        }
    }

    @Override
    public void searchFreshFail(String msg) {
        ToastUtil.showToast(msg);
        if (mSrlSearchResult.isRefreshing()) {
            mSrlSearchResult.setRefreshing(false);
        }
    }

    @Override
    public void searchMoreSuccess(BaseBean<Article> bean) {
        mCurrentCounter = bean.data.datas.size();
        mAdapter.addData(bean.data.datas);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void searchMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mAdapter.loadMoreFail();
    }

    @Override
    public void collectSuccess(String msg) {
        mArticleList.get(mPosition).collect = true;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void collectFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void unCollectSuccess(String msg) {
        mArticleList.get(mPosition).collect = false;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void unCollectFail(String msg) {
        ToastUtil.showToast(msg);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mArticleList.size()) {
            HashMap<String, String> hashMap = new HashMap<>(3);
            hashMap.put(ArticleDetailActivity.WEB_URL, mArticleList.get(position).link);
            hashMap.put(ArticleDetailActivity.WEB_TITLE, mArticleList.get(position).title);
            ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.iv_article_favorite) {
            mPosition = position;
            if (mArticleList.get(mPosition).collect) {
                presenter.unCollect(mArticleList.get(mPosition).id);
            } else {
                presenter.collect(mArticleList.get(mPosition).id);
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
            //数据加载完毕，没有更多的数据
            mAdapter.loadMoreEnd();
        } else {
            presenter.searchMore(++mPage, mKey);
        }
    }


}