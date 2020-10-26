package com.xcynice.playxandroid.module.search.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.HistoryAdapter;
import com.xcynice.playxandroid.adapter.HotKeyAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.HotKey;
import com.xcynice.playxandroid.module.search.activity.SearchActivity;
import com.xcynice.playxandroid.module.search.presenter.SearchHistoryPresenter;
import com.xcynice.playxandroid.module.search.view.ISearchHistoryView;
import com.xcynice.playxandroid.module.search.wiget.TipDialog;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SearchHistoryFragment
 */


public class SearchHistoryFragment extends BaseFragment<SearchHistoryPresenter> implements ISearchHistoryView, BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;
    @BindView(R.id.tv_down)
    TextView mTvDown;
    @BindView(R.id.tv_clean)
    TextView mTvClean;
    @BindView(R.id.rv_history)
    RecyclerView mRvHistory;
    @BindView(R.id.ll_history)
    LinearLayout mLlHistory;

    private HistoryAdapter mHistoryAdapter;
    private HotKeyAdapter mHotKeyAdapter;
    private boolean mRemoveMode = false;

    @Override
    protected SearchHistoryPresenter createPresenter() {
        return new SearchHistoryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_history;
    }

    @Override
    protected void initView() {
        mRvHot.setLayoutManager(new FlexboxLayoutManager(getContext()));
        mHotKeyAdapter = new HotKeyAdapter(R.layout.item_tree_textview);
        mHotKeyAdapter.setOnItemClickListener((adapter, view, position) -> {
            HotKey item = mHotKeyAdapter.getItem(position);
            if (item != null) {
                search(item.getName());
            }
        });
        mRvHot.setAdapter(mHotKeyAdapter);

        mRvHistory.setLayoutManager(new FlexboxLayoutManager(getContext()));
        mHistoryAdapter = new HistoryAdapter(R.layout.rv_item_search_history);
        mHistoryAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!mRemoveMode) {
                String key = mHistoryAdapter.getItem(position);
                search(key);
            }
        });
        mHistoryAdapter.setOnItemLongClickListener(this);
        mHistoryAdapter.setOnItemChildClickListener(this);
        mRvHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    protected void initData() {
        presenter.getHotKeyList();
        mHistoryAdapter.setNewData(presenter.getHistory());
        changeHistoryVisible();
    }

    /**
     * 改变历史记录的显示
     */
    private void changeHistoryVisible() {
        if (mHistoryAdapter == null) {
            mLlHistory.setVisibility(View.GONE);
        } else {
            // 如果搜索历史数据为空的话，设置搜索历史不可见
            if (mHistoryAdapter.getData().isEmpty()) {
                mLlHistory.setVisibility(View.GONE);
            } else {
                mLlHistory.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 改变清除模式
     *
     * @param removeMode 清除模式 ，true 显示完成 ，false 显示清除
     */
    private void changeRemoveMode(boolean removeMode) {
        if (mRemoveMode == removeMode) {
            return;
        }
        mRemoveMode = removeMode;
        mHistoryAdapter.setRemoveModeChanging(true);
        mHistoryAdapter.setRemoveMode(mRemoveMode);
        mHistoryAdapter.notifyDataSetChanged();
        if (removeMode) {
            mTvDown.setVisibility(View.VISIBLE);
            mTvClean.setVisibility(View.GONE);
        } else {
            mTvDown.setVisibility(View.GONE);
            mTvClean.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 搜索
     *
     * @param key 关键词
     */
    private void search(String key) {
        if (getActivity() instanceof SearchActivity) {
            SearchActivity activity = (SearchActivity) getActivity();
            activity.search(key);
        }
    }

    /**
     * 添加到搜索历史
     *
     * @param key 关键词
     */
    public void addHistory(String key) {
        List<String> datas = mHistoryAdapter.getData();
        int index = datas.indexOf(key);
        if (index == 0) {
            return;
        }
        if (index > 0) {
            mHistoryAdapter.remove(index);
        }
        mHistoryAdapter.addData(0, key);
        int max = 100;
        List<String> list = mHistoryAdapter.getData();
        if (list.size() > max) {
            mHistoryAdapter.remove(list.size() - 1);
        }
        presenter.saveHistory(mHistoryAdapter.getData());
        changeHistoryVisible();
    }

    @Override
    public void setHotKeyListSuccess(BaseBean<List<HotKey>> bean) {
        mHotKeyAdapter.setNewData(bean.data);
    }

    @Override
    public void setHotKeyListFail(String msg) {
        ToastUtil.showToast(msg);

    }

    @OnClick({R.id.tv_down, R.id.tv_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_down:
                changeRemoveMode(false);
                break;
            case R.id.tv_clean:
                TipDialog.with(getContext())
                        .message("确定要清除搜索历史？")
                        .onYes(data -> {
                            mHistoryAdapter.setNewData(null);
                            changeHistoryVisible();
                            presenter.saveHistory(null);
                        })
                        .show();
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        changeRemoveMode(!mRemoveMode);
        return true;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mHistoryAdapter.remove(position);
        presenter.saveHistory(mHistoryAdapter.getData());
    }
}