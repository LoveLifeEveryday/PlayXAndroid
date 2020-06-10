package com.xcynice.playxandroid.adapter;


import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.bean.Navigation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5 10:34
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description Navigation 界面的 Adapter
 */

public class NavigationAdapter extends BaseQuickAdapter<Navigation, BaseViewHolder> {
    private LayoutInflater mInflater = null;
    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();
    private OnItemClickListener mOnItemClickListener = null;

    public NavigationAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Navigation item) {
        helper.setText(R.id.tv_name, item.getName());
        FlexboxLayout fbl = helper.getView(R.id.fbl);
        for (int i = 0; i < item.getArticles().size(); i++) {
            TextView child = createOrGetCacheFlexItemTextView(fbl);
            child.setText(item.getArticles().get(i).getTitle());
            int finalI = i;
            child.setOnClickListener(view -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(item, finalI);
                }
            });
            fbl.addView(child);
        }
        helper.itemView.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(item, 0);
            }
        });
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        FlexboxLayout fbl = holder.getView(R.id.fbl);
        for (int i = 0; i < fbl.getChildCount(); i++) {
            mFlexItemTextViewCaches.offer((TextView) fbl.getChildAt(i));
        }
        fbl.removeAllViews();
    }

    private TextView createOrGetCacheFlexItemTextView(FlexboxLayout fbl) {
        TextView tv = mFlexItemTextViewCaches.poll();
        if (tv != null) {
            return tv;
        }
        return createFlexItemTextView(fbl);
    }

    private TextView createFlexItemTextView(FlexboxLayout fbl) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(fbl.getContext());
        }
        return (TextView) mInflater.inflate(R.layout.item_tree_textview, fbl, false);
    }


    public interface OnItemClickListener {
        /**
         * 点击事件
         *
         * @param navigation 点击的项
         * @param pos        位置
         */
        void onClick(Navigation navigation, int pos);
    }
}
