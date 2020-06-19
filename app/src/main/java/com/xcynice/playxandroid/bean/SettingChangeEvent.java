package com.xcynice.playxandroid.bean;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/19 17:08
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 设置改变 eventBus 的实体类
 */

public class SettingChangeEvent {

    private boolean showBannerChanged;
    private boolean hideAboutMeChanged;
    private boolean hideOpenChanged;

    public boolean isShowBannerChanged() {
        return showBannerChanged;
    }

    public void setShowBannerChanged(boolean showBannerChanged) {
        this.showBannerChanged = showBannerChanged;
    }

    public boolean isHideAboutMeChanged() {
        return hideAboutMeChanged;
    }

    public void setHideAboutMeChanged(boolean hideAboutMeChanged) {
        this.hideAboutMeChanged = hideAboutMeChanged;
    }

    public boolean isHideOpenChanged() {
        return hideOpenChanged;
    }

    public void setHideOpenChanged(boolean hideOpenChanged) {
        this.hideOpenChanged = hideOpenChanged;
    }
}
