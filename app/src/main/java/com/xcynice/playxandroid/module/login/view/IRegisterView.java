package com.xcynice.playxandroid.module.login.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.User;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12 15:39
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public interface IRegisterView extends BaseView {


    /**
     * 注册成功
     *
     * @param userBaseBean 用户信息
     */
    void registerSuccess(BaseBean<User> userBaseBean);

    /**
     * 注册失败
     *
     * @param msg 失败信息
     */
    void registerFail(String msg);
}
