package com.xcynice.playxandroid.module.home.view;

import android.content.Intent;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.bean.Banner;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.home.IHomeView;
import com.xcynice.playxandroid.module.home.presenter.HomePresenter;
import com.xcynice.playxandroid.util.ToastUtil;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description HomeFragment
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {

    @BindView(R.id.banner_home)
    MZBannerView mBannerHome;
    @BindView(R.id.srl_home)
    SwipeRefreshLayout mSrlHome;

    private List<Banner> mBannerList = new ArrayList<>();

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBannerHome.pause();

    }

    @Override
    protected void initData() {
        presenter.getBanner();
        mSrlHome.setOnRefreshListener(() -> {
            //开始刷新
            mSrlHome.setRefreshing(true);
            presenter.getBanner();
        });
        initClick();
    }

    /**
     * 初始化 Banner 的点击事件，跳转到另一个 WebView
     */
    void initClick() {
        mBannerHome.setBannerPageClickListener((view, i) -> {
            if (mBannerList.size() != 0) {
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.WEB_URL, mBannerList.get(i).getUrl());
                intent.putExtra(ArticleDetailActivity.WEB_TITLE, mBannerList.get(i).getTitle());
                startActivity(intent);
            }
        });
    }

    @SuppressWarnings("All")
    @Override
    public void setBanner(BaseBean<List<Banner>> list) {
        mBannerList = list.data;
        mBannerHome.setPages(mBannerList, (MZHolderCreator<BannerViewHolder>) () -> new BannerViewHolder());
        mBannerHome.start();

        //结束刷新
        mSrlHome.setRefreshing(false);
    }

    @Override
    public void showBannerError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void setArticle(BaseBean<List<Article>> list) {

    }

    @Override
    public void setArticleError(String errorMsg) {

    }

    @Override
    public void setArticleDataByMore(BaseBean<Article> list) {

    }

    @Override
    public void showArticleErrorByMore(String errorMessage) {

    }

    @Override
    public void showCollectSuccess(String successMessage) {

    }

    @Override
    public void showCollectError(String errorMessage) {

    }

    @Override
    public void showUnCollectSuccess(String successMessage) {

    }

    @Override
    public void showUnCollectError(String errorMessage) {

    }
}