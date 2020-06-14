package com.xcynice.playxandroid.module.mine.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.CoinRank;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 14:37
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public interface ICoinRankView extends BaseView {

    /**
     * 第一次获取积分排行榜数据成功
     *
     * @param coinRankBaseBean 积分排行榜
     */
    void setCoinRankFirstSuccess(BaseBean<CoinRank> coinRankBaseBean);

    /**
     * 第一次获取积分排行榜数据失败
     *
     * @param msg 失败信息
     */
    void setCoinRankFirstFail(String msg);


    /**
     * 加载更多积分排行榜数据成功
     *
     * @param coinRankBaseBean 积分排行榜
     */
    void setCoinRankMoreSuccess(BaseBean<CoinRank> coinRankBaseBean);

    /**
     * v积分排行榜数据失败
     *
     * @param msg 失败信息
     */
    void setCoinRankMoreFail(String msg);
}
