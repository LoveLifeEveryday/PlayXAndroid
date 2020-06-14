package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Coin;
import com.xcynice.playxandroid.module.mine.view.ICoinView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/13 22:16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description CoinPresenter
 */

public class CoinPresenter extends BasePresenter<ICoinView> {
    public CoinPresenter(ICoinView baseView) {
        super(baseView);
    }


    /**
     * 首次获取积分列表
     */
    public void getCoinFirst() {
        addDisposable(apiServer.getUserCoinList(1), new BaseObserver<BaseBean<Coin>>(baseView) {

            @Override
            public void onSuccess(BaseBean<Coin> bean) {
                baseView.setCoinFirstSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setCoinFirstFail(msg);
            }
        });
    }


    /**
     * 加载更多积分列表
     *
     * @param page 页数
     */
    public void getCoinMore(int page) {
        addDisposable(apiServer.getUserCoinList(page), new BaseObserver<BaseBean<Coin>>(baseView) {


            @Override
            public void onSuccess(BaseBean<Coin> bean) {
                baseView.setCoinMoreSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setCoinMoreFail(msg);
            }
        });
    }
}
