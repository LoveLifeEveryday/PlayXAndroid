package com.xcynice.playxandroid.module.home.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.adapter.ArticleAdapter;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.base.BaseFragment;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.bean.Banner;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.home.IHomeView;
import com.xcynice.playxandroid.module.home.presenter.HomePresenter;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;
import com.xcynice.playxandroid.util.XUtil;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.devilsen.czxing.Scanner;
import me.devilsen.czxing.code.BarcodeReader;
import me.devilsen.czxing.code.CodeResult;
import me.devilsen.czxing.util.BarCodeUtil;
import me.devilsen.czxing.util.BitmapUtil;
import me.devilsen.czxing.view.ScanActivityDelegate;
import me.devilsen.czxing.view.ScanView;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description HomeFragment
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_home)
    RecyclerView mRvHome;

    @BindView(R.id.srl_home)
    SuperSwipeRefreshLayout mSrlHome;
    @BindView(R.id.iv_title_left)
    ImageView mIvQrCode;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvSearch;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;

    private static final int CODE_SELECT_IMAGE = 1;

    @SuppressWarnings("rawtypes")
    private MZBannerView mBannerHome;
    private ArticleAdapter mArticleAdapter;
    private List<Banner> mBannerList = new ArrayList<>();
    private List<Article.DataDetailBean> mArticleList = new ArrayList<>();

    /**
     * 上一次加载的数量，方便进行是否加载到最后一页的判断： if (mCurrentCounter < TOTAL_COUNTER)
     */
    private int mCurrentCounter;

    /**
     * 每一次加载的数量
     */
    private final static int SINGLE_PAGE_TOTAL_COUNTER = 20;

    /**
     * 记录分页，方便进行加载更多
     */
    private int mPage = 0;

    /**
     * 记录点击事件的位置，方便后面进行收藏
     */
    private int mPosition;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mSrlHome.setColorSchemeResources(R.color.colorPrimary);
        mRvHome.setLayoutManager(new LinearLayoutManager(mContext));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mViewBanner = inflater.inflate(R.layout.layout_banner, container, false);
        mBannerHome = mViewBanner.findViewById(R.id.banner_home);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBannerHome.pause();
    }

    @Override
    protected void initData() {
        presenter.getArticleListByFirst();
        presenter.getBanner();
        mSrlHome.setOnRefreshListener(() -> {
            //开始刷新
            mSrlHome.setRefreshing(true);
            presenter.getBanner();
            presenter.getArticleListByRefresh();
        });
        initBannerClick();
    }

    /**
     * 初始化 Banner 的点击事件，跳转到另一个 WebView
     */
    void initBannerClick() {
        mBannerHome.setBannerPageClickListener((view, i) -> {
            if (mBannerList.size() != 0) {
                HashMap<String, String> hashMap = new HashMap<>(3);
                hashMap.put(ArticleDetailActivity.WEB_URL, mBannerList.get(i).getUrl());
                hashMap.put(ArticleDetailActivity.WEB_TITLE, mBannerList.get(i).getTitle());
                ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
            }
        });
    }

    @SuppressWarnings("All")
    @Override
    public void setBanner(BaseBean<List<Banner>> list) {
        mBannerList = list.data;
        mBannerHome.setPages(mBannerList, (MZHolderCreator<BannerViewHolder>) () -> new BannerViewHolder());
        mBannerHome.start();
        if (mSrlHome.isRefreshing()) {
            //结束刷新
            mSrlHome.setRefreshing(false);
        }
    }

    @Override
    public void showBannerError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void setArticleByFirst(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, mArticleList);
        mArticleAdapter.addHeaderView(mBannerHome);
        mRvHome.setAdapter(mArticleAdapter);

        // 开启加载动画
        mArticleAdapter.openLoadAnimation();
        // 设置点击事件
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this, mRvHome);
    }

    @Override
    public void setArticleByRefresh(BaseBean<Article> list) {
        mArticleList = list.data.datas;
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter.setNewData(mArticleList);
        mPage = 0;
    }


    @Override
    public void setArticleError(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void setArticleDataByMore(BaseBean<Article> list) {
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter.addData(list.data.datas);
        mArticleAdapter.loadMoreComplete();
    }

    @Override
    public void showArticleErrorByMore(String errorMessage) {
        ToastUtil.showToast(errorMessage);
        mArticleAdapter.loadMoreFail();
    }

    @Override
    public void showCollectSuccess(String successMessage) {
        mArticleList.get(mPosition).collect = true;
        // 因为收藏成功，所以要刷新界面，以显示小红心
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showUnCollectSuccess(String successMessage) {
        mArticleList.get(mPosition).collect = false;
        //因为取消收藏成功，所以要刷新界面，以取消显示小红心
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUnCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    /**
     * 每一项整体的点击
     *
     * @param adapter  adapter
     * @param view     view
     * @param position 点击的选项的位置
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mArticleList.size()) {
            HashMap<String, String> hashMap = new HashMap<>(3);
            hashMap.put(ArticleDetailActivity.WEB_URL, mArticleList.get(position).link);
            hashMap.put(ArticleDetailActivity.WEB_TITLE, mArticleList.get(position).title);
            ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.iv_article_favorite) {
            mPosition = position;
            if (mArticleList.get(mPosition).collect) {
                presenter.unCollect(mArticleList.get(mPosition).id);
            } else {
                presenter.collect(mArticleList.get(mPosition).id);
            }
        }
    }


    /**
     * 加载更多的监听
     */
    @Override
    public void onLoadMoreRequested() {
        mRvHome.postDelayed(() -> {
            if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
                //数据加载完毕，没有更多的数据
                mArticleAdapter.loadMoreEnd();
            } else {
                presenter.getArticleListByMore(++mPage);
            }
        }, 1000);
    }

    /**
     * 开启二维码扫描
     */
    private void openScan() {
        List<Integer> scanColors = Arrays.asList(getColorFromValue(R.color.scan_side), getColorFromValue(R.color.scan_partial), getColorFromValue(R.color.scan_middle));
        Scanner.with(mContext)
                .setMaskColor(getColorFromValue(R.color.mask_color))
                .setBorderColor(getColorFromValue(R.color.box_line))
                .setBorderSize(BarCodeUtil.dp2px(mContext, 200))
                .setCornerColor(getColorFromValue(R.color.corner))
                .setScanLineColors(scanColors)
                .setScanMode(ScanView.SCAN_MODE_BIG)
                .setTitle(getStringFromValue(R.string.my_qr))
                .showAlbum(true)
                .setScanNoticeText(getStringFromValue(R.string.scan_qr_code))
                .setFlashLightOnText(getStringFromValue(R.string.open_flash))
                .setFlashLightOffText(getStringFromValue(R.string.close_flash))
                .setFlashLightOnDrawable(R.drawable.ic_highlight_blue_open_24dp)
                .setFlashLightOffDrawable(R.drawable.ic_highlight_white_close_24dp)
                .continuousScan()
                .setOnClickAlbumDelegate(new ScanActivityDelegate.OnClickAlbumDelegate() {
                    @Override
                    public void onClickAlbum(Activity activity) {
                        Intent albumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        activity.startActivityForResult(albumIntent, CODE_SELECT_IMAGE);
                    }

                    @Override
                    public void onSelectData(int requestCode, Intent data) {
                        if (requestCode == CODE_SELECT_IMAGE) {
                            decodeImage(data);
                        }
                    }
                })
                .setOnScanResultDelegate((activity, result, format) -> {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(ArticleDetailActivity.WEB_URL, result);
                    ActivityUtil.startActivity(ArticleDetailActivity.class, hashMap);
                }).start();
    }

    private void decodeImage(Intent intent) {
        Uri selectImageUri = intent.getData();
        if (selectImageUri == null) {
            return;
        }
        //noinspection deprecation
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
        if (cursor == null) {
            return;
        }
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        // 适当压缩图片
        Bitmap bitmap = BitmapUtil.getDecodeAbleBitmap(picturePath);
        // 这个方法比较耗时，推荐放到子线程执行
        CodeResult result = BarcodeReader.getInstance().read(bitmap);
        if (result == null) {
            Log.e("Scan >>> ", "no code");
        } else {
            Log.e("Scan >>> ", result.getText());
        }

    }

    private int getColorFromValue(int color) {
        return ContextCompat.getColor(XUtil.getApplication(), color);
    }


    private String getStringFromValue(int string) {
        return getResources().getString(string);
    }

    @OnClick({R.id.iv_title_left, R.id.iv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                openScan();
                break;
            case R.id.iv_title_right:
                ToastUtil.showToast("你点击了右边的按钮");

                break;
            default:
                break;
        }
    }
}