package com.xcynice.playxandroid.module.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.util.ActivityUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description AboutMeActivity
 */


public class AboutMeActivity extends BaseActivity {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.iv_blur)
    ImageView mIvBlur;
    @BindView(R.id.riv_icon)
    CircleImageView mRivIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sign)
    TextView mTvSign;
    @BindView(R.id.tv_github)
    TextView mTvGithub;
    @BindView(R.id.ll_github)
    LinearLayout mLlGithub;
    @BindView(R.id.tv_jeujin)
    TextView mTvJeujin;
    @BindView(R.id.ll_juejin)
    LinearLayout mLlJuejin;
    @BindView(R.id.tv_jianshu)
    TextView mTvJianshu;
    @BindView(R.id.ll_jianshu)
    LinearLayout mLlJianshu;
    @BindView(R.id.tv_qq)
    TextView mTvQq;
    @BindView(R.id.ll_qq)
    LinearLayout mLlQq;
    @BindView(R.id.rl_info)
    RelativeLayout mRlInfo;

    HashMap<String, String> mHashMap = new HashMap<>(1);

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setImageResource(R.drawable.back);
        mTvTitleCenter.setText("关于作者");
        mIvTitleRight.setVisibility(View.INVISIBLE);
        //noinspection AliDeprecation
        mRlTitle.setBackgroundColor(getResources().getColor(R.color.background));
        ImmersionBar.with(this).titleBar(mRlTitle).init();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_title_left, R.id.ll_github, R.id.ll_juejin, R.id.ll_jianshu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.ll_github:

                mHashMap.put(ArticleDetailActivity.WEB_URL, mTvGithub.getText().toString());
                ActivityUtil.startActivity(ArticleDetailActivity.class, mHashMap);
                break;
            case R.id.ll_juejin:
                mHashMap.clear();
                mHashMap.put(ArticleDetailActivity.WEB_URL, mTvJeujin.getText().toString());
                ActivityUtil.startActivity(ArticleDetailActivity.class, mHashMap);
                break;
            case R.id.ll_jianshu:
                mHashMap.clear();
                mHashMap.put(ArticleDetailActivity.WEB_URL, mTvJianshu.getText().toString());
                ActivityUtil.startActivity(ArticleDetailActivity.class, mHashMap);
                break;
            default:
                break;
        }
    }
}