package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.CoinRank;
import com.xcynice.playxandroid.module.mine.view.ICoinRankView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 14:41
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description CoinRankPresenter 积分排行榜列表的 Presenter
 */

public class CoinRankPresenter extends BasePresenter<ICoinRankView> {
    public CoinRankPresenter(ICoinRankView baseView) {
        super(baseView);
    }


    /**
     * 首次获取积分排行榜列表
     */
    public void getCoinRankFirst() {
        addDisposable(apiServer.getCoinRankList(1), new BaseObserver<BaseBean<CoinRank>>(baseView,true) {


            @Override
            public void onSuccess(BaseBean<CoinRank> bean) {
                baseView.setCoinRankFirstSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setCoinRankFirstFail(msg);
            }
        });
    }


    /**
     * 加载更多积分排行榜列表
     *
     * @param page 页数
     */
    public void getCoinRankMore(int page) {
        addDisposable(apiServer.getCoinRankList(page), new BaseObserver<BaseBean<CoinRank>>(baseView) {


            @Override
            public void onSuccess(BaseBean<CoinRank> bean) {
                baseView.setCoinRankMoreSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setCoinRankMoreFail(msg);
            }
        });
    }
}
