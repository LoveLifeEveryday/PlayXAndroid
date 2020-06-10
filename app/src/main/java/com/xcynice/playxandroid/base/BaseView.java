package com.xcynice.playxandroid.base;

/**
 * Description : BaseView
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


public interface BaseView {

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载中
     */
    void hideLoading();

    /**
     * 错误
     *
     * @param bean 错误信息
     */
    @SuppressWarnings("rawtypes")
    void onErrorCode(BaseBean bean);

}
