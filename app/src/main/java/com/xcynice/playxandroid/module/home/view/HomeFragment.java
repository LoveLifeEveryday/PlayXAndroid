package com.xcynice.playxandroid.module.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.ArticleAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.bean.Banner;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.home.IHomeView;
import com.xcynice.playxandroid.module.home.presenter.HomePresenter;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description HomeFragment
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_home)
    RecyclerView mRvHome;

    @BindView(R.id.srl_home)
    SuperSwipeRefreshLayout mSrlHome;

    @SuppressWarnings("rawtypes")
    private MZBannerView mBannerHome;
    private ArticleAdapter mArticleAdapter;
    private List<Banner> mBannerList = new ArrayList<>();
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
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mSrlHome.setColorSchemeResources(R.color.colorPrimary);
        mRvHome.setLayoutManager(new LinearLayoutManager(mContext));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mViewBanner = inflater.inflate(R.layout.layout_banner, container, false);
        mBannerHome = mViewBanner.findViewById(R.id.banner_home);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBannerHome.pause();
    }

    @Override
    protected void initData() {
        presenter.getArticleListByFirst();
        presenter.getBanner();
        mSrlHome.setOnRefreshListener(() -> {
            //开始刷新
            mSrlHome.setRefreshing(true);
            presenter.getBanner();
            presenter.getArticleListByRefresh();
        });
        initBannerClick();
    }

    /**
     * 初始化 Banner 的点击事件，跳转到另一个 WebView
     */
    void initBannerClick() {
        mBannerHome.setBannerPageClickListener((view, i) -> {
            if (mBannerList.size() != 0) {
                HashMap<String, String> hashMap = new HashMap<>(3);
                hashMap.put(ArticleDetailActivity.WEB_URL, mBannerList.get(i).getUrl());
                hashMap.put(ArticleDetailActivity.WEB_TITLE, mBannerList.get(i).getTitle());
                ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
            }
        });
    }

    @SuppressWarnings("All")
    @Override
    public void setBanner(BaseBean<List<Banner>> list) {
        mBannerList = list.data;
        mBannerHome.setPages(mBannerList, (MZHolderCreator<BannerViewHolder>) () -> new BannerViewHolder());
        mBannerHome.start();
        if (mSrlHome.isRefreshing()) {
            //结束刷新
            mSrlHome.setRefreshing(false);
        }
    }

    @Override
    public void showBannerError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void setArticleByFirst(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, mArticleList);
        mArticleAdapter.addHeaderView(mBannerHome);
        mRvHome.setAdapter(mArticleAdapter);

        // 开启加载动画
        mArticleAdapter.openLoadAnimation();
        // 设置点击事件
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this, mRvHome);
    }

    @Override
    public void setArticleByRefresh(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter.setNewData(mArticleList);
        mPage = 0;
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
        mArticleList.get(mPosition).collect = true;
        // 因为收藏成功，所以要刷新界面，以显示小红心
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

    /**
     * 每一项整体的点击
     *
     * @param adapter  adapter
     * @param view     view
     * @param position 点击的选项的位置
     */
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
     * 加载更多的监听
     */
    @Override
    public void onLoadMoreRequested() {
        mRvHome.postDelayed(() -> {
            if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
                //数据加载完毕，没有更多的数据
                mArticleAdapter.loadMoreEnd();
            } else {
                presenter.getArticleListByMore(++mPage);
            }
        }, 1000);
    }
}