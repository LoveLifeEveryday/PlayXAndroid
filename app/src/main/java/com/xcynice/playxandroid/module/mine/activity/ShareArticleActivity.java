package com.xcynice.playxandroid.module.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.mine.presenter.ShareArticlePresenter;
import com.xcynice.playxandroid.module.mine.view.IShareArticleView;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/15
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description ShareArticleActivity
 */


public class ShareArticleActivity extends BaseActivity<ShareArticlePresenter> implements IShareArticleView {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_refresh)
    TextView mTvRefresh;
    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.tv_link)
    TextView mTvLink;
    @BindView(R.id.tv_open)
    TextView mTvOpen;
    @BindView(R.id.et_link)
    EditText mEtLink;
    @BindView(R.id.tv_share)
    TextView mTvShare;
    @BindView(R.id.tv_tip)
    TextView mTvTip;

    @Override
    protected ShareArticlePresenter createPresenter() {
        return new ShareArticlePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_article;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setImageResource(R.drawable.back);
        mTvTitleCenter.setText("分享文章");
        mIvTitleRight.setVisibility(View.INVISIBLE);
        ImmersionBar.with(this).titleBar(mRlTitle).init();

    }

    @Override
    protected void initData() {

    }


    @Override
    public void shareArticleSuccess(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void shareArticleFail(String msg) {
        ToastUtil.showToast(msg);
    }


    @OnClick({R.id.iv_title_left, R.id.tv_refresh, R.id.tv_open, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.tv_refresh:
                mEtTitle.setText("");
                break;
            case R.id.tv_open:
                if (checkUrl(mEtLink.getText().toString().trim())) {
                    HashMap<String, String> hashMap = new HashMap<>(1);
                    hashMap.put(ArticleDetailActivity.WEB_URL, mEtLink.getText().toString().trim());
                    ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
                } else {
                    ToastUtil.showToast("输入的网站格式不正确");
                }
                break;
            case R.id.tv_share:
                presenter.shareArticle(mEtTitle.getText().toString().trim(), mEtLink.getText().toString().trim());
                break;
            default:
                break;
        }
    }


    /**
     * 测试网站是否符合格式
     *
     * @param url 网站 url
     * @return 网站是否符合格式
     */
    private boolean checkUrl(String url) {
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }
}