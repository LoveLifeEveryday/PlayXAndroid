package com.xcynice.playxandroid.bean;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/13 21:50
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MessageLoginSuccessWrap 是 EventBus 信息的包装类
 */

public class MessageLoginSuccessWrap {

    private String msg;

    public MessageLoginSuccessWrap(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
