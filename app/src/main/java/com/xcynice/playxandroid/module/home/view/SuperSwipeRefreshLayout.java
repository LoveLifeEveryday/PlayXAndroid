package com.xcynice.playxandroid.module.home.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/3 20:39
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class SuperSwipeRefreshLayout extends SwipeRefreshLayout {

    private float startX;
    private float startY;

    /**
     * 记录 viewpager 是否拖拽的标记
     */
    private boolean mIsVpDragger;
    private int mTouchSlop;

    public SuperSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public SuperSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                //初始化标记
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsVpDragger) {
                    return false;
                }
                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                if (distanceX > mTouchSlop && distanceX > distanceY) {
                    mIsVpDragger = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsVpDragger = false;
                break;
            default:
                break;
        }
        // 如果是Y轴位移大于X轴，事件交给 swipeRefreshLayout 处理
        return super.onInterceptTouchEvent(ev);
    }
}
