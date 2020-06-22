//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xcynice.playxandroid.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description : ActivityUtil
 *
 * @author XuCanyou666
 * @date 2020/4/27
 */

@TargetApi(14)
public class ActivityUtil {
    private static Stack<Activity> activityStack = new Stack<>();
    private static final ActivityUtil.MyActivityLifecycleCallbacks INSTANCE = new ActivityUtil.MyActivityLifecycleCallbacks();

    public ActivityUtil() {
    }

    public static ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return INSTANCE;
    }

    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }

    }


    /**
     * 不用 finish 当前 Activity 时直接调用此方法
     *
     * @param classes 需要跳转到的 activity
     */
    public static void startActivity(@SuppressWarnings("rawtypes") Class classes) {
        startActivity(classes, false);
    }


    /**
     * 需要 finish 当前 Activity 时调用此方法，布尔值参数传入 true
     *
     * @param classes  需要打开的 activity
     * @param isFinish 是否 finish 当前 activity
     */
    public static void startActivity(@SuppressWarnings("rawtypes") Class classes, boolean isFinish) {
        Activity currentActivity = getCurrentActivity();
        Intent intent = new Intent(currentActivity, classes);
        currentActivity.startActivity(intent);
        if (isFinish) {
            finishActivity(currentActivity);
        }
    }


    /**
     * 启动 activity， 带上参数
     *
     * @param classes 需要打开的 activity
     * @param hashMap 需要传递的参数
     */
    public static void startActivity(@SuppressWarnings("rawtypes") Class classes, HashMap<String, String> hashMap) {
        startActivity(classes, hashMap, false);
    }

    /**
     * 启动 activity， 可以设置是否关闭当前 activity
     *
     * @param classes  需要打开的 activity
     * @param hashMap  需要传递的参数
     * @param isFinish 是否关闭当前 activity
     */
    public static void startActivity(@SuppressWarnings("rawtypes") Class classes, HashMap<String, String> hashMap, boolean isFinish) {
        Activity currentActivity = getCurrentActivity();
        Intent intent = new Intent(currentActivity, classes);
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        currentActivity.startActivity(intent);
        if (isFinish) {
            finishActivity(currentActivity);
        }
    }


    /**
     * 关闭所有 Activity
     */
    public static void closeAllActivity() {
        while (true) {
            Activity activity = getCurrentActivity();
            if (null == activity) {
                return;
            }
            finishActivity(activity);
        }
    }

    /**
     * 得到当前的 Activity
     *
     * @return 当前 Activity
     */
    public static Activity getCurrentActivity() {
        Activity activity = null;
        if (!activityStack.isEmpty()) {
            activity = activityStack.peek();
        }
        return activity;
    }


    private static class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        private MyActivityLifecycleCallbacks() {
        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
            ActivityUtil.activityStack.remove(activity);
            ActivityUtil.activityStack.push(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            ActivityUtil.activityStack.remove(activity);
        }
    }
}
