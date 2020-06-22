package com.xcynice.playxandroid.module.login.presenter;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.User;
import com.xcynice.playxandroid.module.login.view.IRegisterView;
import com.xcynice.playxandroid.util.XUtil;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12 15:41
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public RegisterPresenter(IRegisterView baseView) {
        super(baseView);
    }


    public  void register(String username, String password, String rePassword) {
        addDisposable(apiServer.register(username, password, rePassword), new BaseObserver<BaseBean<User>>(baseView) {
            @Override
            public void onSuccess(BaseBean<User> bean) {
                baseView.registerSuccess(bean);

            }

            @Override
            public void onError(String msg) {
                baseView.registerFail(msg + XUtil.getString(R.string.surprise));
            }
        });
    }
}
