package com.xcynice.playxandroid.module.login.widget;

import android.content.Context;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.xcynice.playxandroid.R;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description PasswordInputView
 */


public class PasswordInputView extends InputView {

    private ImageView mIvPasswordIcon;
    private ImageView mIvDeleteIcon;
    private ImageView mIcEyeIcon;

    private boolean isHidePwdMode = true;
    private OnPwdFocusChangedListener mOnPwdFocusChangedListener = null;

    public PasswordInputView(Context context) {
        super(context);
    }

    public PasswordInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnPwdFocusChangedListener(OnPwdFocusChangedListener onPwdFocusChangedListener) {
        mOnPwdFocusChangedListener = onPwdFocusChangedListener;
    }

    @Override
    protected void initViews(AttributeSet attrs) {
        super.initViews(attrs);
        getEditText().setHint("请输入密码");
        changeFocusMode(false);
        changePwdHideMode(true);
    }

    @Override
    protected ImageView[] getLeftIcons() {
        mIvPasswordIcon = new ImageView(getContext());
        mIvPasswordIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIvPasswordIcon.setImageResource(R.drawable.ic_password_normal);
        return new ImageView[]{mIvPasswordIcon};
    }

    @Override
    protected ImageView[] getRightIcons() {
        int paddingDelete = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getContext().getResources().getDisplayMetrics());
        mIvDeleteIcon = new ImageView(getContext());
        mIvDeleteIcon.setVisibility(INVISIBLE);
        mIvDeleteIcon.setPadding(paddingDelete, paddingDelete, paddingDelete, paddingDelete);
        mIvDeleteIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIvDeleteIcon.setImageResource(R.drawable.ic_delete);
        mIvDeleteIcon.setOnClickListener(v -> getEditText().setText(""));
        int paddingEye = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics());
        mIcEyeIcon = new ImageView(getContext());
        mIcEyeIcon.setVisibility(INVISIBLE);
        mIcEyeIcon.setPadding(paddingEye, paddingEye, paddingEye, paddingEye);
        mIcEyeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIcEyeIcon.setImageResource(R.drawable.ic_eye_normal);
        mIcEyeIcon.setOnClickListener(v -> changePwdHideMode(!isHidePwdMode));
        return new ImageView[]{mIcEyeIcon, mIvDeleteIcon};
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        changeFocusMode(hasFocus);
        if (mOnPwdFocusChangedListener != null) {
            mOnPwdFocusChangedListener.onFocusChanged(hasFocus);
        }
    }

    private void changeFocusMode(boolean focus) {
        if (focus) {
            if (isEmpty()) {
                mIvDeleteIcon.setVisibility(INVISIBLE);
            } else {
                mIvDeleteIcon.setVisibility(VISIBLE);
            }
            mIcEyeIcon.setVisibility(VISIBLE);
            mIvPasswordIcon.setColorFilter(mViewColorFocus);
        } else {
            mIvDeleteIcon.setVisibility(INVISIBLE);
            mIcEyeIcon.setVisibility(INVISIBLE);
            mIvPasswordIcon.setColorFilter(mViewColorNormal);
        }
    }

    private void changePwdHideMode(boolean isHidePwdMode) {
        this.isHidePwdMode = isHidePwdMode;
        if (isHidePwdMode) {
            //隐藏密码
            getEditText().setTransformationMethod(PasswordTransformationMethod.getInstance());
            mIcEyeIcon.setColorFilter(mViewColorNormal);
        } else {
            //显示密码
            getEditText().setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mIcEyeIcon.setColorFilter(mViewColorFocus);
        }
        getEditText().setText(getEditText().getText().toString());
        getEditText().setSelection(getEditText().getText().toString().length());
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        if (isEmpty()) {
            mIvDeleteIcon.setVisibility(INVISIBLE);
        } else {
            mIvDeleteIcon.setVisibility(VISIBLE);
        }
    }

    public interface OnPwdFocusChangedListener {
        void onFocusChanged(boolean focus);
    }
}
