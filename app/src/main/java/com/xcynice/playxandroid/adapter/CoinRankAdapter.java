package com.xcynice.playxandroid.adapter;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.CoinRank;
import com.xcynice.playxandroid.util.XUtil;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 14:23
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description CoinRankAdapter 积分排名的 adapter
 */

public class CoinRankAdapter extends BaseQuickAdapter<CoinRank.DatasBean, BaseViewHolder> {
    private final int mScale = 1000;
    private int mMax = 0;


    public CoinRankAdapter(@Nullable List<CoinRank.DatasBean> data) {
        super(R.layout.rv_item_coin_rank, data);
        if (data != null && !data.isEmpty()) {
            mMax = data.get(0).getCoinCount();
        }
    }

    private void cancelProgressAnim(final ProgressBar pb) {
        Object obj = pb.getTag();
        if (obj instanceof Animator) {
            Animator animator = (Animator) obj;
            animator.cancel();
        }
        pb.setTag(null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CoinRank.DatasBean item) {
        ProgressBar pb = helper.getView(R.id.pb);
        pb.setMax(mMax * mScale);
        cancelProgressAnim(pb);
        if (!item.anim) {
            item.anim = true;
            doProgressAnim(pb, item.getCoinCount());
        } else {
            pb.setProgress(item.getCoinCount() * mScale);
        }
        int index = helper.getAdapterPosition() + 1;
        helper.setText(R.id.tv_index, "" + index);
        helper.setText(R.id.tv_user_name, item.getUsername());
        helper.setText(R.id.tv_coin_count, "" + item.getCoinCount());
        ImageView ivIndex = helper.getView(R.id.iv_index);
        TextView tvIndex = helper.getView(R.id.tv_index);
        if (index == 1) {
            ivIndex.setImageResource(R.drawable.ic_rank_1);
            tvIndex.setTextColor(XUtil.getColor(R.color.first_rank));
            tvIndex.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvIndex.getContext().getResources().getDimension(R.dimen.text_auxiliary));
        } else if (index == 2) {
            ivIndex.setImageResource(R.drawable.ic_rank_2);
            tvIndex.setTextColor(XUtil.getColor(R.color.second_rank));
            tvIndex.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvIndex.getContext().getResources().getDimension(R.dimen.text_auxiliary));
        } else if (index == 3) {
            ivIndex.setImageResource(R.drawable.ic_rank_3);
            tvIndex.setTextColor(XUtil.getColor(R.color.third_rank));
            tvIndex.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvIndex.getContext().getResources().getDimension(R.dimen.text_auxiliary));
        } else {
            ivIndex.setImageResource(R.color.transparent);
            tvIndex.setTextColor(ContextCompat.getColor(tvIndex.getContext(), R.color.authorTextColor));
            tvIndex.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvIndex.getContext().getResources().getDimension(R.dimen.text_content));
        }
    }

    private void doProgressAnim(final ProgressBar pb, int to) {
        ValueAnimator animator = ValueAnimator.ofInt(0, to);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> pb.setProgress((int) animation.getAnimatedValue() * mScale));
        pb.setTag(animator);
        animator.start();
    }
}
