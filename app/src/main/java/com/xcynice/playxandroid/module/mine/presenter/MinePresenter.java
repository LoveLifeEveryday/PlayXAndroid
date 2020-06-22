package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.UserInfo;
import com.xcynice.playxandroid.module.mine.view.IMineView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12 17:46
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MinePresenter 我的界面的 presenter
 */

public class MinePresenter extends BasePresenter<IMineView> {
    public MinePresenter(IMineView baseView) {
        super(baseView);
    }

    /**
     * 得到用户信息
     */
    public void getUserInfo() {
        addDisposable(apiServer.getUserCoin(), new BaseObserver<BaseBean<UserInfo>>(baseView) {

            @Override
            public void onSuccess(BaseBean<UserInfo> bean) {
                baseView.setUserInfoSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setUserInfoFail(msg);
            }
        });
    }
}
