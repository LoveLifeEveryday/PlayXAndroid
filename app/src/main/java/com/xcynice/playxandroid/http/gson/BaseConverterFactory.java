package com.xcynice.playxandroid.http.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.util.XUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/10
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description BaseConverterFactory
 */


public class BaseConverterFactory extends Converter.Factory {

    public static BaseConverterFactory create() {
        return create(new Gson());
    }

    public static BaseConverterFactory create(Gson gson) {
        if (gson == null) {
            throw new NullPointerException(XUtil.getApplication().getString(R.string.gsonIsNull));
        }
        return new BaseConverterFactory(gson);
    }

    private final Gson gson;

    private BaseConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new BaseResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new BaseRequestBodyConverter<>(gson, adapter);
    }
}