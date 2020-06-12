package com.xcynice.playxandroid.module.login.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatTextView;

import com.xcynice.playxandroid.R;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description SubmitView 设置SubmitView 监控到 EditText，如果为空，则不可输入设置隐藏
 */


public class SubmitView extends AppCompatTextView implements TextWatcher {

    private int[] mBindIds;
    private EditText[] mEditTexts;
    private boolean mHasInit = false;

    public SubmitView(Context context) {
        this(context, null);
    }

    public SubmitView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubmitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SubmitView);
        mBindIds = new int[]{
                typedArray.getResourceId(R.styleable.SubmitView_sv_bindEditText1, NO_ID),
                typedArray.getResourceId(R.styleable.SubmitView_sv_bindEditText2, NO_ID),
                typedArray.getResourceId(R.styleable.SubmitView_sv_bindEditText3, NO_ID)
        };
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initViews();
    }

    private void initViews() {
        if (mHasInit) {
            return;
        }
        mHasInit = true;
        mEditTexts = new EditText[mBindIds.length];
        for (int i = 0; i < mBindIds.length; i++) {
            View bindView = getRootView().findViewById(mBindIds[i]);
            EditText editText = null;
            if (bindView instanceof EditText) {
                editText = (EditText) bindView;
            } else if (bindView instanceof EditTextWrapper) {
                EditTextWrapper editTextWrapper = (EditTextWrapper) bindView;
                editText = editTextWrapper.getEditText();
            }
            mEditTexts[i] = editText;
            if (mEditTexts[i] != null) {
                mEditTexts[i].addTextChangedListener(this);
            }
        }
        for (EditText et : mEditTexts) {
            if (et != null) {
                //设置光标到最后一位
                et.setText(et.getText().toString());
                et.setSelection(et.getText().toString().length());
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean hasEmpty = false;
        for (EditText et : mEditTexts) {
            if (et != null && TextUtils.isEmpty(et.getText().toString())) {
                hasEmpty = true;
                break;
            }
        }
        if (hasEmpty) {
            setAlpha(0.7F);
            setEnabled(false);
        } else {
            setAlpha(1.0F);
            setEnabled(true);
        }
    }

    public interface EditTextWrapper {
        /**
         * 如果是包装类的话，得到 EditText
         *
         * @return EditText
         */
        EditText getEditText();
    }
}
