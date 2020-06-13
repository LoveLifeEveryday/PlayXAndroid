package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.UserInfo;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12 17:47
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description IMineView
 */

public interface IMineView extends BaseView {

    /**
     * 设置用户信息
     *
     * @param userInfo 用户信息
     */
    void setUserInfoSuccess(BaseBean<UserInfo> userInfo);

    /**
     * 设置用户信息失败
     * @param msg 失败信息
     */
    void setUserInfoFail(String msg);

}
