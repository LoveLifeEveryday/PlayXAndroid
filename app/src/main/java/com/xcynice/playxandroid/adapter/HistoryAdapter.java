package com.xcynice.playxandroid.adapter;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/18 15:06
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 历史记录的 adapter
 */

public class HistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private boolean mRemoveModeChanging = false;

    private boolean mRemoveMode = false;

    public HistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setRemoveMode(boolean isRemove) {
        mRemoveMode = isRemove;
    }


    public void setRemoveModeChanging(boolean isChanging) {
        mRemoveModeChanging = isChanging;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_key, item);
        helper.addOnClickListener(R.id.iv_remove);
        ImageView ivRemove = helper.getView(R.id.iv_remove);
        if (!mRemoveModeChanging) {
            helper.setVisible(R.id.iv_remove, mRemoveMode);
        } else {
            //如果要显示清除的按钮
            if (mRemoveMode) {
                //缩放动画，逐渐显示
                ScaleAnimation scaleAnimation = getScaleAnimationByMore(mRemoveMode);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        ivRemove.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mRemoveModeChanging = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                ivRemove.startAnimation(scaleAnimation);
            } else {
                //如果隐藏清除的按钮
                ScaleAnimation scaleAnimation = getScaleAnimationByMore(mRemoveMode);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mRemoveModeChanging = false;
                        ivRemove.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                ivRemove.startAnimation(scaleAnimation);
            }
        }
    }

    /**
     * 根据清除模式得到对应的缩放动画
     *
     * @param isRemove 是否是清除
     * @return 缩放动画
     */
    private ScaleAnimation getScaleAnimationByMore(boolean isRemove) {
        if (isRemove) {
            return getScaleAnimationByData(0F, 1F, 0F, 1F);
        } else {
            return getScaleAnimationByData(1F, 0F, 1F, 0F);
        }
    }



    private ScaleAnimation getScaleAnimationByData(float fromX, float toX, float fromY, float toY) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                fromX, toX, fromY, toY,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F
        );
        scaleAnimation.setDuration(300);
        return scaleAnimation;
    }
}
