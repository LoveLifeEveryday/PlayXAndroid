package com.xcynice.playxandroid.module.mine.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.login.widget.LogoAnimView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.XUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/20
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description AboutUsActivity
 */


@SuppressWarnings("rawtypes")
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.lav)
    LogoAnimView mLav;
    @BindView(R.id.fl_logo)
    FrameLayout mFlLogo;
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;
    @BindView(R.id.tv_web)
    TextView mTvWeb;
    @BindView(R.id.ll_web)
    LinearLayout mLlWeb;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;
    private HashMap<String, String> mHashMap = new HashMap<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        mLav.randomBlink();
        mIvTitleLeft.setImageResource(R.drawable.back);
        mTvTitleCenter.setText(XUtil.getString(R.string.aboutUs));
        mIvTitleRight.setVisibility(View.INVISIBLE);
        ImmersionBar.with(this).titleBar(mRlTitle).init();
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.iv_title_left, R.id.ll_web, R.id.ll_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.ll_web:
                skipToArticleDetailActivity(XUtil.getString(R.string.officialWebsite));
                break;
            case R.id.ll_about:
                skipToArticleDetailActivity(XUtil.getString(R.string.officialWebsiteAbout));
                break;
            default:
        }
    }


    /**
     * 跳转到详情页
     *
     * @param url 网站 Url
     */
    private void skipToArticleDetailActivity(String url) {
        mHashMap.clear();
        mHashMap.put(ArticleDetailActivity.WEB_URL, url);
        ActivityUtil.startActivity(ArticleDetailActivity.class, mHashMap);
    }
}