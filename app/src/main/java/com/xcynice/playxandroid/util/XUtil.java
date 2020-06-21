package com.xcynice.playxandroid.util;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;


/**
 * @Author xucanyou666
 * @Date 2020/4/27 09:26
 * email：913710642@qq.com
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class XUtil {
    private static Application mApplicationContext;
    private static ProgressDialog progressDialog;
    @SuppressLint("StaticFieldLeak")
    private static ProgressBar progressBar;

    public static void initialize(Application app) {
        mApplicationContext = app;
    }

    public static Application getApplication() {
        return mApplicationContext;
    }

    public static void showLoading(String msg) {
        progressDialog = ProgressDialog.show(ActivityUtil.getCurrentActivity(), "", msg, true, true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static String getString(int id) {
        return getApplication().getResources().getString(id);

    }

    public static int getColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getApplication().getResources().getColor(id, getApplication().getTheme());
        }
        return 0;
    }


    public static void showLoading(Activity activity, String msg) {
        progressDialog = ProgressDialog.show(activity, "", msg, true, true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void closeSoftKeyboard() {
        InputMethodManager inputManger = (InputMethodManager) ActivityUtil.getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManger != null) {
            inputManger.hideSoftInputFromWindow(ActivityUtil.getCurrentActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
