package com.xcynice.playxandroid.module.mine.presenter;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.module.mine.view.IShareArticleView;
import com.xcynice.playxandroid.util.XUtil;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15 19:31
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ShareArticlePresenter
 */

public class ShareArticlePresenter extends BasePresenter<IShareArticleView> {
    public ShareArticlePresenter(IShareArticleView baseView) {
        super(baseView);
    }

    public void shareArticle(String title, String link) {
        //noinspection rawtypes
        addDisposable(apiServer.shareArticle(title, link), new BaseObserver<BaseBean>(baseView) {

            @Override
            public void onSuccess(BaseBean bean) {
                baseView.shareArticleSuccess(XUtil.getString(R.string.ShareArticleSuccess));
            }

            @Override
            public void onError(String msg) {
                baseView.shareArticleFail(msg);
            }
        });
    }
}
