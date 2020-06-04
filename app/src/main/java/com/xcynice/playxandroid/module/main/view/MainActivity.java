package com.xcynice.playxandroid.module.main.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.module.article_detail.ArticleDetailActivity;
import com.xcynice.playxandroid.module.main.adapter.MainViewPagerAdapter;
import com.xcynice.playxandroid.module.main.listener.MainBnvListener;
import com.xcynice.playxandroid.module.main.listener.MainVpListener;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.ToastUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.devilsen.czxing.Scanner;
import me.devilsen.czxing.code.BarcodeReader;
import me.devilsen.czxing.code.CodeResult;
import me.devilsen.czxing.util.BarCodeUtil;
import me.devilsen.czxing.util.BitmapUtil;
import me.devilsen.czxing.view.ScanActivityDelegate;
import me.devilsen.czxing.view.ScanView;

/**
 * Description : MainActivity
 *
 * @author XuCanyou666
 * @date 2020/6/1
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.bnv_main)
    BottomNavigationView mBnvMain;
    private static final int CODE_SELECT_IMAGE = 1;
    private static final int OVER_TIME = 2000;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    /**
     * 保存用户按返回键的时间
     */
    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        initViewPager();
        initListener();
    }

    private void initListener() {
        MainVpListener vpListener = new MainVpListener(mBnvMain, mTvTitleCenter, mIvTitleLeft, mIvTitleRight, mRlTitle);
        MainBnvListener bnvListener = new MainBnvListener(mVpMain);
        mVpMain.addOnPageChangeListener(vpListener);
        mBnvMain.setOnNavigationItemSelectedListener(bnvListener);
    }


    private void initViewPager() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mVpMain.setOffscreenPageLimit(3);
        mVpMain.setAdapter(adapter);
    }


    /**
     * 根据当前时间节点判断是否退出,双击退出功能的设置
     */
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > OVER_TIME) {
            ToastUtil.showToast(getResources().getString(R.string.double_quit) + getResources().getString(R.string.app_name));
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityUtil.closeAllActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tb_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            ToastUtil.showToast(getResources().getString(R.string.click_search));
        }
        return true;
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


    /**
     * 开启二维码扫描
     */
    private void openScan() {
        List<Integer> scanColors = Arrays.asList(getColorFromValue(R.color.scan_side), getColorFromValue(R.color.scan_partial), getColorFromValue(R.color.scan_middle));
        Scanner.with(this)
                .setMaskColor(getColorFromValue(R.color.mask_color))
                .setBorderColor(getColorFromValue(R.color.box_line))
                .setBorderSize(BarCodeUtil.dp2px(this, 200))
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

    @SuppressWarnings("AliDeprecation")
    private void decodeImage(Intent intent) {
        Uri selectImageUri = intent.getData();
        if (selectImageUri == null) {
            return;
        }
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
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

    private String getStringFromValue(int string) {
        return getResources().getString(string);
    }


    private int getColorFromValue(int color) {
        return ContextCompat.getColor(getApplicationContext(), color);
    }
}