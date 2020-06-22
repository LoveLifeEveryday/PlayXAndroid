package com.xcynice.playxandroid.module.login.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.MessageLoginSuccessWrap;
import com.xcynice.playxandroid.bean.User;
import com.xcynice.playxandroid.module.login.activity.LoginActivity;
import com.xcynice.playxandroid.module.login.presenter.LoginPresenter;
import com.xcynice.playxandroid.module.login.view.ILoginView;
import com.xcynice.playxandroid.module.login.widget.AccountInputView;
import com.xcynice.playxandroid.module.login.widget.PasswordInputView;
import com.xcynice.playxandroid.module.login.widget.SubmitView;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.ToastUtil;
import com.xcynice.playxandroid.util.XUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description LoginFragment
 */


public class LoginFragment extends BaseFragment<LoginPresenter> implements ILoginView {


    @BindView(R.id.ll_go_register)
    LinearLayout mLlGoRegister;
    @BindView(R.id.aiv_login_account)
    AccountInputView mAivLoginAccount;
    @BindView(R.id.piv_login_password)
    PasswordInputView mPivLoginPassword;
    @BindView(R.id.sv_login)
    SubmitView mSvLogin;


    private LoginActivity mActivity;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (LoginActivity) context;
    }

    @Override
    protected void initView() {
        mPivLoginPassword.setOnPwdFocusChangedListener(focus -> mActivity.doEyeAnim(focus));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getSoftInputHelper().moveWith(mSvLogin,
                mAivLoginAccount.getEditText(), mPivLoginPassword.getEditText());
    }

    @Override
    protected void initData() {

    }


    @Override
    public void loginSuccess(BaseBean<User> user) {
        SpUtil.setBoolean(SpUtil.IS_LOGIN, true);
        //存进sp里面
        SpUtil.setString(SpUtil.USERNAME, user.data.username);
        SpUtil.setString(SpUtil.PASSWORD, user.data.password);
        SpUtil.setString(SpUtil.NICK_NAME, user.data.nickname);
        EventBus.getDefault().post(new MessageLoginSuccessWrap(XUtil.getString(R.string.refreshUser)));

        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void loginFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @OnClick({R.id.ll_go_register, R.id.sv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_go_register:
                mActivity.changeToRegister();
                break;
            case R.id.sv_login:
                XUtil.closeSoftKeyboard();
                String username = mAivLoginAccount.getText();
                String password = mPivLoginPassword.getText();
                presenter.login(username, password);
                break;
            default:
                break;
        }
    }
}