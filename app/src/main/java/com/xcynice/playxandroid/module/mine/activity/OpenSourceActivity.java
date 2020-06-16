package com.xcynice.playxandroid.module.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.OpenSourceAdapter;
import com.xcynice.playxandroid.base.BaseActivity;
import com.xcynice.playxandroid.base.BasePresenter;
import com.xcynice.playxandroid.bean.OpenEntity;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.util.ActivityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description OpenSourceActivity
 */


public class OpenSourceActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.rv_open_source)
    RecyclerView mRvOpenSource;

    private OpenSourceAdapter mAdapter;
    private List<OpenEntity> mList = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_source;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setImageResource(R.drawable.back);
        mTvTitleCenter.setText("开源项目");
        mIvTitleRight.setVisibility(View.INVISIBLE);
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mRvOpenSource.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        addList("square/retrofit", "A type-safe HTTP client for Android and the JVM", "https://github.com/square/retrofit");
        addList("ReactiveX/RxJava2", "RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.", "https://github.com/ReactiveX/RxJava/tree/2.x");
        addList("google/gson", "A Java serialization/deserialization library to convert Java Objects into JSON and back", "https://github.com/google/gson");
        addList("ReactiveX/RxAndroid", "RxJava bindings for Android", "https://github.com/ReactiveX/RxAndroid");
        addList("square/okhttp", "Square’s meticulous HTTP client for Java and Kotlin. ", "https://github.com/square/okhttp");
        addList("bumptech/glide", "An image loading and caching library for Android focused on smooth scrolling", "https://github.com/bumptech/glide");
        addList("JakeWharton/butterknife", "Bind Android views and callbacks to fields and methods.", "https://github.com/JakeWharton/butterknife");
        addList("CymChad/BaseRecyclerViewAdapterHelper", "BRVAH:Powerful and flexible RecyclerAdapter", "https://github.com/CymChad/BaseRecyclerViewAdapterHelper");
        addList("gyf-dev/ImmersionBar", "android 4.4以上沉浸式状态栏和沉浸式导航栏管理，适配横竖屏切换、刘海屏、软键盘弹出等问题，可以修改状态栏字体颜色和导航栏图标颜色，以及不可修改字体颜色手机的适配，适用于Activity、Fragment、DialogFragment、Dialog，PopupWindow，一句代码轻松实现，以及对bar的其他设置，详见README。", "https://github.com/gyf-dev/ImmersionBar");
        addList("pinguo-zhouwei/MZBannerView", "仿魅族BannerView,图片轮播控件,支持多种模式切换：普通ViewPager使用，普通Banner使用，仿魅族Banner使用。", "https://github.com/pinguo-zhouwei/MZBannerView");
        addList("devilsen/CZXing", "C++ port of ZXing for Android,底层使用C++来处理图像及解析二维码，并且加入了OpenCV来解析图像，可以在更远的距离识别出更加复杂的二维码。", "https://github.com/devilsen/CZXing");
        addList("google/flexbox-layout", "Flexbox for Android", "https://github.com/google/flexbox-layout");
        addList("vinc3m1/RoundedImageView", "A fast ImageView that supports rounded corners, ovals, and circles.", "https://github.com/vinc3m1/RoundedImageView");
        addList("greenrobot/EventBus", "Event bus for Android and Java that simplifies communication between Activities, Fragments, Threads, Services, etc. Less code, better quality. ", "https://github.com/greenrobot/EventBus");
        addList("JetBrains/kotlin", "The Kotlin Programming Language ", "https://github.com/JetBrains/kotlin");
        addList("goweii/PercentImageView", "自由指定宽高比的ImageView ", "https://github.com/goweii/PercentImageView");
        mAdapter = new OpenSourceAdapter(mList);
        mAdapter.setOnItemClickListener(this);
        mRvOpenSource.setAdapter(mAdapter);
    }


    private void addList(String project, String description, String link) {
        mList.add(new OpenEntity(project, description, link));
    }


    @OnClick(R.id.iv_title_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mList.size() != 0) {
            @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") HashMap<String, String> hashMap = new HashMap<>(1);
            hashMap.put(ArticleDetailActivity.WEB_URL, mList.get(position).getLink());
            ActivityUtil.startActivity(ArticleDetailActivity.class,hashMap);
        }
    }
}