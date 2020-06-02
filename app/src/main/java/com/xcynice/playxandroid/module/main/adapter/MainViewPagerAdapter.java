package com.xcynice.playxandroid.module.main.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.module.ask_and_question.AskAndQuestionFragment;
import com.xcynice.playxandroid.module.home.view.HomeFragment;
import com.xcynice.playxandroid.module.mine.MineFragment;
import com.xcynice.playxandroid.module.tree.TreeFragment;
import com.xcynice.playxandroid.util.XUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1 10:55
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description main vp 的 adapter
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final int VIEWPAGER_COUNT = 4;
    private List<String> mTitleList=new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mTitleList.add(XUtil.getApplication().getString(R.string.home_page));
        mTitleList.add(XUtil.getApplication().getString(R.string.qAndA));
        mTitleList.add(XUtil.getApplication().getString(R.string.system));
        mTitleList.add(XUtil.getApplication().getString(R.string.mine));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new AskAndQuestionFragment();
            case 2:
                return new TreeFragment();
            case 3:
                return new MineFragment();
            default:
                //noinspection ConstantConditions
                return null;
        }
    }

    @Override
    public int getCount() {
        return VIEWPAGER_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
