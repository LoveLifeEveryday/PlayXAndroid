package com.xcynice.playxandroid.app;

import android.app.Application;

import com.tencent.mmkv.MMKV;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.LogUtil;
import com.xcynice.playxandroid.util.XUtil;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/6
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description App
 */


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        XUtil.initialize(this);
        //初始化 MMKV
        MMKV.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }

}
