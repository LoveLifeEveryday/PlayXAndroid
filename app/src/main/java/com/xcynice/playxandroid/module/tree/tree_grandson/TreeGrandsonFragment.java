package com.xcynice.playxandroid.module.tree.tree_grandson;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.TreeGrandsonAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.TreeChild;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity.WEB_TITLE;
import static com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity.WEB_URL;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TreeGrandsonFragment
 */


public class TreeGrandsonFragment extends BaseFragment<TreeGrandsonPresenter> implements ITreeGrandsonView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String CID = "com/xcynice/playxandroid/module/tree/TreeGrandsonFragment.java/CID";

    @BindView(R.id.rv_tree_grandson)
    RecyclerView mRvTreeGrandson;
    @BindView(R.id.srf_tree_grandson)
    SwipeRefreshLayout mSrfTreeGrandson;
    private List<TreeChild.DatasBean> mTCList;

    private int mCid;
    private int mPosition;
    private TreeGrandsonAdapter mTreeGrandsonAdapter;

    /**
     * 上一次加载的数量
     */
    private int mCurrentCounter;
    /**
     * 每一次加载的数量
     */
    private final static int TOTAL_COUNTER = 20;

    /**
     * 记录分页
     */
    private int mPage = 0;

    /**
     * 创建 Fragment
     *
     * @param cid 分类 id
     * @return TreeGrandsonFragment
     */
    public static TreeGrandsonFragment newInstance(int cid) {
        TreeGrandsonFragment categoryFragment = new TreeGrandsonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CID, cid);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected TreeGrandsonPresenter createPresenter() {
        return new TreeGrandsonPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree_grandson;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTreeChildList(mCid);
        mPage = 0;
    }

    @Override
    protected void initView() {
        mCid = getArguments().getInt(CID);
        mRvTreeGrandson.setLayoutManager(new LinearLayoutManager(mContext));
        mSrfTreeGrandson.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    protected void initData() {
        mSrfTreeGrandson.setOnRefreshListener(() -> {
            mSrfTreeGrandson.setRefreshing(true);
            presenter.getTreeChildListByRefresh(mCid);
        });
    }


    @Override
    public void setTreeChildData(BaseBean<TreeChild> list) {
        mTCList = list.data.getDatas();
        mCurrentCounter = list.data.getDatas().size();
        mTreeGrandsonAdapter = new TreeGrandsonAdapter(R.layout.item_article_list, list.data.getDatas());
        mRvTreeGrandson.setAdapter(mTreeGrandsonAdapter);
        mTreeGrandsonAdapter.openLoadAnimation();
        mTreeGrandsonAdapter.setOnItemClickListener(this);
        mTreeGrandsonAdapter.setOnItemChildClickListener(this);
        mTreeGrandsonAdapter.setOnLoadMoreListener(this, mRvTreeGrandson);
    }

    @Override
    public void showTreeChildError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showCollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mTCList.get(mPosition).setCollect(true);
        mTreeGrandsonAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showUnCollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mTCList.get(mPosition).setCollect(false);
        mTreeGrandsonAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUnCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void setTreeChildDataByLoadMore(BaseBean<TreeChild> list) {
        mCurrentCounter = list.data.getDatas().size();
        mTreeGrandsonAdapter.addData(list.data.getDatas());
        mTreeGrandsonAdapter.loadMoreComplete();
    }

    @Override
    public void setTreeChildDataByLoadMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mTreeGrandsonAdapter.loadMoreFail();
    }

    @Override
    public void setTreeChildDataByRefresh(BaseBean<TreeChild> list) {
        mTCList = list.data.getDatas();
        mCurrentCounter = list.data.getDatas().size();
        mTreeGrandsonAdapter.setNewData(mTCList);
        mPage = 0;
        if (mSrfTreeGrandson.isRefreshing()) {
            mSrfTreeGrandson.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mTCList.size()) {
            HashMap<String, String> hashMap = new HashMap<>(2);
            hashMap.put(WEB_URL, mTCList.get(position).getLink());
            hashMap.put(WEB_TITLE, mTCList.get(position).getTitle());
            ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.iv_article_favorite) {
            mPosition = position;
            if (mTCList.get(mPosition).isCollect()) {
                presenter.unCollect(mTCList.get(mPosition).getId());
            } else {
                presenter.collect(mTCList.get(mPosition).getId());
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mRvTreeGrandson.postDelayed(() -> {
            if (mCurrentCounter < TOTAL_COUNTER) {
                //数据加载完毕，没有更多的数据
                mTreeGrandsonAdapter.loadMoreEnd();
            } else {
                presenter.getTreeChildListByLoadMore(++mPage, mCid);
            }
        }, 1000);
    }
}