package com.xcynice.playxandroid.module.ask_and_question;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.ArticleAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.home.view.SuperSwipeRefreshLayout;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.LogUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/4
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description AskAndQuestionFragment
 */


public class AskAndQuestionFragment extends BaseFragment<AskAndQuestionPresenter> implements IAskAndQuestionView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.rv_ask_and_question)
    RecyclerView mRvAskAndQuestion;
    @BindView(R.id.srl_ask_and_question)
    SuperSwipeRefreshLayout mSrlAskAndQuestion;


    private ArticleAdapter mArticleAdapter;
    private List<Article.DataDetailBean> mArticleList = new ArrayList<>();

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
    private int mPage = 0;

    /**
     * 记录点击事件的位置，方便后面进行收藏
     */
    private int mPosition;

    public AskAndQuestionFragment() {
    }


    @Override
    protected AskAndQuestionPresenter createPresenter() {
        return new AskAndQuestionPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ask_and_question;
    }

    @Override
    protected void initView() {
        mSrlAskAndQuestion.setColorSchemeResources(R.color.colorPrimary);
        mRvAskAndQuestion.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        presenter.getArticleListByFirst();
        mSrlAskAndQuestion.setOnRefreshListener(() -> {
            mSrlAskAndQuestion.setRefreshing(true);
            presenter.getArticleListByRefresh();
        });
    }

    @Override
    public void setArticleByFirst(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, mArticleList);
        mRvAskAndQuestion.setAdapter(mArticleAdapter);

        // 开启加载动画
        mArticleAdapter.openLoadAnimation();
        // 设置点击事件
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this, mRvAskAndQuestion);
    }

    @Override
    public void setArticleByRefresh(BaseBean<Article> list) {
        mArticleList.clear();
        mArticleList.addAll(list.data.datas);
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter.setNewData(mArticleList);
        mPage = 0;
        //结束刷新
        if (mSrlAskAndQuestion.isRefreshing()) {
            mSrlAskAndQuestion.setRefreshing(false);
        }
    }

    @Override
    public void setArticleError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void setArticleDataByMore(BaseBean<Article> list) {
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter.addData(list.data.datas);
        mArticleAdapter.loadMoreComplete();
    }

    @Override
    public void showArticleErrorByMore(String errorMessage) {
        ToastUtil.showToast(errorMessage);
        mArticleAdapter.loadMoreFail();
    }

    @Override
    public void showCollectSuccess(String successMessage) {
        mArticleList.get(mPosition).collect = false;
        //因为取消收藏成功，所以要刷新界面，以取消显示小红心
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showUnCollectSuccess(String successMessage) {
        mArticleList.get(mPosition).collect = false;
        //因为取消收藏成功，所以要刷新界面，以取消显示小红心
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUnCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
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

    /**
     * 加载更多的请求
     */
    @Override
    public void onLoadMoreRequested() {
        mRvAskAndQuestion.postDelayed(() -> {
            if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
                //数据加载完毕，没有更多的数据
                mArticleAdapter.loadMoreEnd();
            } else {
                presenter.getArticleListByMore(++mPage);
            }
        }, 1000);
    }
}