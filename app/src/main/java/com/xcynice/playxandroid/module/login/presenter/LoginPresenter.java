package com.xcynice.playxandroid.module.login.presenter;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.User;
import com.xcynice.playxandroid.module.login.view.ILoginView;
import com.xcynice.playxandroid.util.XUtil;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/11 12:51
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    public LoginPresenter(ILoginView baseView) {
        super(baseView);
    }

    public void login(String username, String password) {
        addDisposable(apiServer.login(username, password), new BaseObserver<BaseBean<User>>(baseView) {
            @Override
            public void onSuccess(BaseBean<User> bean) {
                baseView.loginSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.loginFail(msg + XUtil.getString(R.string.surprise));
            }
        });
    }
}
