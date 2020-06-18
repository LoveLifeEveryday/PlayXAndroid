package com.xcynice.playxandroid.module.search;

import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.util.XUtil;

import butterknife.BindView;
import per.goweii.actionbarex.common.ActionBarSearch;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SearchActivity
 */


public class SearchActivity extends BaseActivity {

    @BindView(R.id.abs)
    ActionBarSearch mAbs;
    @BindView(R.id.fl)
    FrameLayout mFl;

    private SearchHistoryFragment mSearchHistoryFragment;
    private SearchResultFragment mSearchResultFragment;
    private FragmentManager mFragmentManager;
    private boolean mIsResultPage = false;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(mAbs).init();

        mAbs.setOnLeftIconClickListener(v -> {
            // 如果现在是结果的页面的话，按返回键，显示的是历史的 Fragment
            if (mIsResultPage) {
                showHistoryFragment();
            } else {
                finish();
            }
        });

        mAbs.setOnRightTextClickListener(v -> {
            String key = mAbs.getEditTextView().getText().toString();
            search(key);
        });
        mAbs.getEditTextView().setSingleLine();
        mAbs.getEditTextView().setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mAbs.getEditTextView().setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if ((actionId == EditorInfo.IME_ACTION_SEARCH)) {
                String key = mAbs.getEditTextView().getText().toString();
                search(key);
                return true;
            }
            return false;
        });
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        // 初始化搜索历史的 Fragment
        Fragment searchHistoryFragment = mFragmentManager.findFragmentByTag(SearchHistoryFragment.class.getName());
        if (searchHistoryFragment == null) {
            mSearchHistoryFragment = new SearchHistoryFragment();
            transaction.add(R.id.fl, mSearchHistoryFragment, SearchHistoryFragment.class.getName());
        } else {
            mSearchHistoryFragment = (SearchHistoryFragment) searchHistoryFragment;
        }

        // 初始化搜索结果的 Fragment
        Fragment searchResultFragment = mFragmentManager.findFragmentByTag(SearchResultFragment.class.getName());
        if (searchResultFragment == null) {
            mSearchResultFragment = new SearchResultFragment();
            transaction.add(R.id.fl, mSearchResultFragment, SearchResultFragment.class.getName());
        } else {
            mSearchResultFragment = (SearchResultFragment) searchResultFragment;
        }

        transaction.show(mSearchHistoryFragment);
        transaction.hide(mSearchResultFragment);
        transaction.commit();
    }

    /**
     * 搜索
     *
     * @param key 关键词
     */
    public void search(String key) {
        //隐藏虚拟键盘
        XUtil.closeSoftKeyboard();
        mAbs.getEditTextView().clearFocus();
        if (TextUtils.isEmpty(key)) {
            if (mIsResultPage) {
                showHistoryFragment();
            }
        } else {
            setTextWithSelection(mAbs.getEditTextView(), key);
            if (!mIsResultPage) {
                showResultFragment();
            }
            mSearchHistoryFragment.addHistory(key);
            mSearchResultFragment.search(key);
        }
    }

    public void setTextWithSelection(EditText editText, CharSequence text) {
        editText.setText(text);
        editText.setSelection(editText.getText().toString().length());
    }

    /**
     * 显示搜索结果界面
     */
    private void showResultFragment() {
        mIsResultPage = true;
        FragmentTransaction t = mFragmentManager.beginTransaction();
        //隐藏搜索历史，显示搜索结果
        t.hide(mSearchHistoryFragment);
        t.show(mSearchResultFragment);
        t.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        if (mIsResultPage) {
            showHistoryFragment();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 显示搜索历史界面
     */
    private void showHistoryFragment() {
        mIsResultPage = false;
        FragmentTransaction t = mFragmentManager.beginTransaction();
        //隐藏搜索结果，显示搜索历史
        t.hide(mSearchResultFragment);
        t.show(mSearchHistoryFragment);
        t.commit();
    }
}