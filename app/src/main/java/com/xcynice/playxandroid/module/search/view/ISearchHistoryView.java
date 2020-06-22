package com.xcynice.playxandroid.module.search.view;


import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseView;
import com.xcynice.playxandroid.bean.HotKey;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/17
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ISearchHistoryView
 */


public interface ISearchHistoryView extends BaseView {

    /**
     * 得到搜索热词成功
     *
     * @param data 搜索热词
     */
    void setHotKeyListSuccess(BaseBean<List<HotKey>> data);

    /**
     * 得到搜索热词失败
     *
     * @param msg 失败信息
     */
    void setHotKeyListFail(String msg);
}
