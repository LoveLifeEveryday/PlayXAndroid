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
 * @Description 下拉刷新控件，修复了滑动冲突
 * 思路：
 * 1. 因为下拉刷新，只有纵向滑动的时候才有效，只需要判断此时是纵向滑动还是横向滑动就可以了。
 * 2. 纵向滑动就拦截事件，横向滑动不拦截。
 * 3. 怎么判断是纵向滑动还是横向滑动，只要判断Y轴的移动距离大于X轴的移动距离那么就判定为纵向滑动就行了
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
                // 初始化标记
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
        // 如果是 Y 轴位移大于 X 轴，事件交给 swipeRefreshLayout 处理
        return super.onInterceptTouchEvent(ev);
    }
}
