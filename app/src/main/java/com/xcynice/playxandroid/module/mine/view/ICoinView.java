package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.Coin;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/13 22:16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ICoinView
 */

public interface ICoinView extends BaseView {

    /**
     * 第一次设置用户积分列表
     *
     * @param coinBaseBean 积分列表
     */
    void setCoinFirstSuccess(BaseBean<Coin> coinBaseBean);


    /**
     * 第一次设置用户积分列表失败
     *
     * @param msg 错误信息
     */
    void setCoinFirstFail(String msg);


    /**
     * 加载更多用户积分列表成功
     *
     * @param coinBaseBean
     */
    void setCoinMoreSuccess(BaseBean<Coin> coinBaseBean);

    /**
     * 加载更多用户积分列表失败
     *
     * @param msg 错误信息
     */
    void setCoinMoreFail(String msg);

}
