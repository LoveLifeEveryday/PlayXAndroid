package com.xcynice.playxandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.XUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description : BaseFragment
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


@SuppressWarnings("rawtypes")
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    private Unbinder unbinder;
    protected Context mContext;

    protected P presenter;


    /**
     * 创建 presenter
     *
     * @return presenter
     */
    protected abstract P createPresenter();

    /**
     * 得到布局文件 id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        //得到context,在后面的子类Fragment中都可以直接调用
        mContext = ActivityUtil.getCurrentActivity();
        presenter = createPresenter();
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //do something
        unbinder.unbind();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initListener() {
    }

    @Override
    public void onErrorCode(BaseBean bean) {
    }

    /**
     * 显示加载中
     */
    @Override
    public void showLoading() {
        XUtil.showLoading(ActivityUtil.getCurrentActivity(), XUtil.getString(R.string.loading));
    }

    /**
     * 隐藏加载中
     */
    @Override
    public void hideLoading() {
        XUtil.dismissLoading();
    }
}
