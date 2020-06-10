package com.xcynice.playxandroid.module.mine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import com.xcynice.playxandroid.R;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/9
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MineFragment
 */


public class MineFragment extends Fragment {


    public MineFragment() {
        // Required empty public constructor
    }



    private void initView(){
        RotateAnimation animation=new RotateAnimation(0,167,123,0f);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
}