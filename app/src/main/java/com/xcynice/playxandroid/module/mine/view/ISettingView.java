package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/19 17:29
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public interface ISettingView extends BaseView {
    /**
     * 退出登陆成功
     *
     * @param msg 成功信息
     */
    void logoutSuccess(String msg);

    /**
     * 退出登陆失败
     *
     * @param msg 失败信息
     */
    void logoutFail(String msg);
}
