package com.xcynice.playxandroid.module.ask_and_question;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.Article;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/4 17:38
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 问答页面的 presenter
 */

public class AskAndQuestionPresenter extends BasePresenter<IAskAndQuestionView> {
    public AskAndQuestionPresenter(IAskAndQuestionView baseView) {
        super(baseView);
    }

    /**
     * 第一次获取首页文章数据
     */
    public void getArticleListByFirst() {
        getArticleListByFirstOrRefresh(true);
    }

    /**
     * 通过刷新获取首页文章
     */
    public void getArticleListByRefresh() {
        getArticleListByFirstOrRefresh(false);
    }

    /**
     * 获得 article 列表（封装首次获取和刷新获取重复的代码）
     *
     * @param isFirst true 表示首次获取，false 表示通过刷新获取
     */
    private void getArticleListByFirstOrRefresh(boolean isFirst) {
        addDisposable(apiServer.getAskAndQuestionList(0), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> list) {
                if (isFirst) {
                    baseView.setArticleByFirst(list);
                } else {
                    baseView.setArticleByRefresh(list);
                }
            }

            @Override
            public void onError(String msg) {
                baseView.setArticleError(msg);
            }
        });
    }

    /**
     * 加载更多
     *
     * @param page 分页参数
     */
    public void getArticleListByMore(int page) {
        addDisposable(apiServer.getAskAndQuestionList(page), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setArticleDataByMore(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showArticleErrorByMore(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 收藏
     *
     * @param id 文章id
     */
    public void collect(int id) {
        //noinspection rawtypes
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showCollectSuccess("收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 取消收藏
     *
     * @param id 文章id
     */
    public void unCollect(int id) {
        //noinspection rawtypes
        addDisposable(apiServer.unCollect(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showUnCollectSuccess("取消收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showUnCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }
}
