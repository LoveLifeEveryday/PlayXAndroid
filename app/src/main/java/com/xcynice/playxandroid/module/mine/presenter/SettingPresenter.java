package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.module.mine.view.ISettingView;
import com.xcynice.playxandroid.util.XUtil;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/19 17:32
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SettingPresenter 设置界面对应的 presenter
 */

public class SettingPresenter extends BasePresenter<ISettingView> {
    public SettingPresenter(ISettingView baseView) {
        super(baseView);
    }


    public void logout() {
        //noinspection rawtypes
        addDisposable(apiServer.logout(), new BaseObserver<BaseBean>(baseView) {


            @Override
            public void onSuccess(BaseBean bean) {
                baseView.logoutSuccess(XUtil.getString(R.string.LogoutSuccess));
            }

            @Override
            public void onError(String msg) {
                baseView.logoutFail(XUtil.getString(R.string.LogoutFail));
            }
        });
    }

}
