package com.xcynice.playxandroid.app;

import android.app.Application;

import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.LogUtil;
import com.xcynice.playxandroid.util.XUtil;


/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        XUtil.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }

}
