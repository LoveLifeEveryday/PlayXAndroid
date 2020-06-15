package com.xcynice.playxandroid.http.gson;

import com.google.gson.TypeAdapter;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseException;
import com.xcynice.playxandroid.module.login.activity.LoginActivity;
import com.xcynice.playxandroid.util.ActivityUtil;
import com.xcynice.playxandroid.util.SpUtil;
import com.xcynice.playxandroid.util.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author yechao
 * @date 2019/11/18/018
 * Describe : 重写ResponseBodyConverter对json预处理
 */
public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    /**
     * 登陆失效
     */
    private static final int LOG_OUT_TIME = -1001;

    BaseResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);
            int code = object.getInt(XUtil.getApplication().getString(R.string.errorCode));
            if (0 != code) {
                String data;
                //错误信息
                if (code == LOG_OUT_TIME) {
                    data = XUtil.getApplication().getString(R.string.loginFail);
                    // 处理登录失效 更新
                    ActivityUtil.startActivity(LoginActivity.class);
                    SpUtil.setBoolean(SpUtil.IS_LOGIN, false);

                } else {
                    data = object.getString(XUtil.getApplication().getString(R.string.errorMsg));
                }
                //异常处理
                throw new BaseException(code, data);
            }
            //正确返回整个json
            return adapter.fromJson(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常即json格式有变动
            throw new BaseException(BaseException.PARSE_ERROR_MSG);
        } finally {
            value.close();
        }
    }
}