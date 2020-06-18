package com.xcynice.playxandroid.module.search.presenter;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseObserver;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.HotKey;
import com.xcynice.playxandroid.module.search.util.SearchHistoryUtils;
import com.xcynice.playxandroid.module.search.view.ISearchHistoryView;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SearchHistoryPresenter
 */


public class SearchHistoryPresenter extends BasePresenter<ISearchHistoryView> {


    public SearchHistoryPresenter(ISearchHistoryView baseView) {
        super(baseView);
    }


    public void getHotKeyList() {
        addDisposable(apiServer.getHotKeyList(), new BaseObserver<BaseBean<List<HotKey>>>(baseView) {


            @Override
            public void onSuccess(BaseBean<List<HotKey>> bean) {
                baseView.setHotKeyListSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.setHotKeyListFail(msg);
            }
        });
    }

    /**
     * 得到搜索历史
     *
     * @return 搜索历史
     */
    public List<String> getHistory() {
        return SearchHistoryUtils.get();
    }


    public void saveHistory(List<String> list) {
        List<String> saves = list;
        int max = 100;
        if (list != null && list.size() > max) {
            saves = list.subList(0, max - 1);
        }
        SearchHistoryUtils.save(saves);
    }
}
