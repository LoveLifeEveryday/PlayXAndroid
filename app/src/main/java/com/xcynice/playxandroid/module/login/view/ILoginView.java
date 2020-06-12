package com.xcynice.playxandroid.module.login.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.User;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/11 12:48
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public interface ILoginView extends BaseView {
    /**
     * 登陆成功
     * @param userBaseBean 用户信息
     */
    void loginSuccess(BaseBean<User> userBaseBean);

    /**
     * 登陆失败
     * @param msg 失败信息
     */
    void loginFail(String msg);
}
