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
import com.xcynice.playxandroid.bean.User;
import com.xcynice.playxandroid.module.login.activity.LoginActivity;
import com.xcynice.playxandroid.module.login.presenter.RegisterPresenter;
import com.xcynice.playxandroid.module.login.view.IRegisterView;
import com.xcynice.playxandroid.module.login.widget.AccountInputView;
import com.xcynice.playxandroid.module.login.widget.PasswordInputView;
import com.xcynice.playxandroid.module.login.widget.SubmitView;
import com.xcynice.playxandroid.util.ToastUtil;
import com.xcynice.playxandroid.util.XUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description RegisterFragment
 */


public class RegisterFragment extends BaseFragment<RegisterPresenter> implements IRegisterView {


    @BindView(R.id.ll_go_login)
    LinearLayout mLlGoLogin;
    @BindView(R.id.piv_register_account)
    AccountInputView mAivRegisterAccount;
    @BindView(R.id.piv_register_password)
    PasswordInputView mPivRegisterPassword;
    @BindView(R.id.piv_register_password_again)
    PasswordInputView mPivRegisterPasswordAgain;
    @BindView(R.id.sv_register)
    SubmitView mSvRegister;

    private LoginActivity mActivity;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (LoginActivity) context;
    }


    @Override
    protected void initView() {
        mPivRegisterPassword.setOnPwdFocusChangedListener(focus -> mActivity.doEyeAnim(focus));
        mPivRegisterPasswordAgain.setOnPwdFocusChangedListener(focus -> mActivity.doEyeAnim(focus));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getSoftInputHelper().moveWith(mSvRegister,
                mAivRegisterAccount.getEditText(), mPivRegisterPassword.getEditText(), mPivRegisterPasswordAgain.getEditText());
    }

    @Override
    protected void initData() {

    }


    @Override
    public void registerSuccess(BaseBean<User> userBaseBean) {
        mActivity.changeToLogin();
    }

    @Override
    public void registerFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @OnClick({R.id.ll_go_login, R.id.sv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_go_login:
                mActivity.changeToLogin();
                break;
            case R.id.sv_register:
                XUtil.closeSoftKeyboard();
                String userName = mAivRegisterAccount.getText();
                String password = mPivRegisterPassword.getText();
                String rePassword = mPivRegisterPasswordAgain.getText();
                presenter.register(userName, password, rePassword);
                break;
            default:
                break;
        }
    }
}