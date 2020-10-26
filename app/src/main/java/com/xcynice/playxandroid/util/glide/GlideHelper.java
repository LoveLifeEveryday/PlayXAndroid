package com.xcynice.playxandroid.util.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xcynice.playxandroid.util.glide.progress.OnProgressListener;
import com.xcynice.playxandroid.util.glide.progress.ProgressInterceptor;
import com.xcynice.playxandroid.util.listener.SimpleCallback;
import com.xcynice.playxandroid.util.listener.SimpleListener;


/**
 * @Author 许朋友爱玩
 * @Date 2020/8/1
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description GlideHelper Glide图片加载框架的帮助类
 */


public class GlideHelper {

    private final RequestManager mManager;
    private RequestBuilder<Drawable> mBuilder;
    private RequestBuilder<Bitmap> mBuilderBmp;
    private RequestBuilder<GifDrawable> mBuilderGif;
    private boolean mCache = true;
    private int mPlaceHolder = 0;
    private int mErrorHolder = 0;
    private OnGlideProgressListener mOnGlideProgressListener = null;
    private OnProgressListener mProgressListener = null;
    private Handler mProgressHandler = null;
    private String mImageUrl;
    private BitmapTransformation mBitmapTransformation = null;

    private GlideHelper(Context context) {
        mManager = Glide.with(context);
    }

    public static GlideHelper with(Context context) {
        return new GlideHelper(context);
    }

    public void pauseRequests() {
        mManager.pauseRequests();
    }

    public void resumeRequests() {
        mManager.resumeRequests();
    }

    public GlideHelper highQuality() {
        mManager.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        return this;
    }


    /**
     * 设置是否缓存
     *
     * @param cache true 为缓存
     * @return GlideHelper
     */
    public GlideHelper cache(boolean cache) {
        this.mCache = cache;
        return this;
    }

    public GlideHelper placeHolder(@DrawableRes int placeHolder) {
        this.mPlaceHolder = placeHolder;
        return this;
    }

    public GlideHelper errorHolder(@DrawableRes int errorHolder) {
        this.mErrorHolder = errorHolder;
        return this;
    }

    public GlideHelper transformation(BitmapTransformation transformation) {
        this.mBitmapTransformation = transformation;
        return this;
    }

    @SuppressLint("HandlerLeak")
    public GlideHelper onProgressListener(OnGlideProgressListener listener) {
        mOnGlideProgressListener = listener;
        mProgressHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (mOnGlideProgressListener != null) {
                    float progress = (float) msg.obj;
                    mOnGlideProgressListener.onProgress(progress);
                }
            }
        };
        return this;
    }

    public enum As {
        /**
         * DRAWABLE ： drawable,
         * GIF: gif,
         * BITMAP: bitmap
         */
        DRAWABLE, GIF, BITMAP
    }

    private As as = As.DRAWABLE;

    public GlideHelper asDrawable() {
        as = As.DRAWABLE;
        return this;
    }

    public GlideHelper asGif() {
        as = As.GIF;
        return this;
    }

    public GlideHelper asBitmap() {
        as = As.BITMAP;
        return this;
    }

    public GlideHelper load(String url) {
        this.mImageUrl = url;
        switch (as) {
            case DRAWABLE:
                mBuilder = getBuilder().load(url);
                break;
            case GIF:
                mBuilderGif = getGifBuilder().load(url);
                break;
            case BITMAP:
                mBuilderBmp = getBmpBuilder().load(url);
                break;
            default:
        }
        return this;
    }

    public GlideHelper load(Uri uri) {
        switch (as) {
            case DRAWABLE:
                mBuilder = getBuilder().load(uri);
                break;
            case GIF:
                mBuilderGif = getGifBuilder().load(uri);
                break;
            case BITMAP:
                mBuilderBmp = getBmpBuilder().load(uri);
                break;
            default:
        }
        return this;
    }

    public GlideHelper load(int resId) {
        switch (as) {
            case DRAWABLE:
                mBuilder = getBuilder().load(resId);
                break;
            case GIF:
                mBuilderGif = getGifBuilder().load(resId);
                break;
            case BITMAP:
                mBuilderBmp = getBmpBuilder().load(resId);
                break;
            default:
        }
        return this;
    }

    public GlideHelper load(Bitmap bitmap) {
        switch (as) {
            case DRAWABLE:
                mBuilder = getBuilder().load(bitmap);
                break;
            case GIF:
                mBuilderGif = getGifBuilder().load(bitmap);
                break;
            case BITMAP:
                mBuilderBmp = getBmpBuilder().load(bitmap);
                break;
            default:
        }
        return this;
    }

    public void into(ImageView imageView) {
        if (mOnGlideProgressListener != null && mProgressHandler != null && mImageUrl != null) {
            mProgressListener = this::notifyProgressChanged;
            ProgressInterceptor.addProgressListener(mImageUrl, mProgressListener);
        }
        switch (as) {
            case DRAWABLE:
                getBuilder().apply(getOptions()).into(new ImageViewTarget<Drawable>(imageView) {
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        notifyLoadStarted();
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        notifyResourceReady();
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        notifyLoadFailed();
                    }
                });
                break;
            case GIF:
                getGifBuilder().apply(getOptions()).into(new ImageViewTarget<GifDrawable>(imageView) {
                    @Override
                    protected void setResource(@Nullable GifDrawable resource) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        notifyLoadStarted();
                    }

                    @Override
                    public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                        super.onResourceReady(resource, transition);
                        notifyResourceReady();
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        notifyLoadFailed();
                    }
                });
                break;
            case BITMAP:
                getBmpBuilder().apply(getOptions()).into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        notifyLoadStarted();
                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(resource, transition);
                        notifyResourceReady();
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        notifyLoadFailed();
                    }
                });
                break;
            default:
        }
    }

    private void notifyProgressChanged(float progress) {
        if (mProgressHandler != null) {
            Message msg = mProgressHandler.obtainMessage();
            msg.obj = progress;
            mProgressHandler.sendMessage(msg);
        }
    }

    private void notifyLoadStarted() {
        notifyProgressChanged(0F);
    }

    private void notifyResourceReady() {
        notifyProgressChanged(1F);
        if (mProgressListener != null) {
            ProgressInterceptor.removeProgressListener(mProgressListener);
        }
    }

    private void notifyLoadFailed() {
        notifyProgressChanged(-1F);
        if (mProgressListener != null) {
            ProgressInterceptor.removeProgressListener(mProgressListener);
        }
    }

    /**
     * 预加载
     */
    public void preload() {
        switch (as) {
            case DRAWABLE:
                getBuilder().apply(getOptions()).preload();
                break;
            case GIF:
                getGifBuilder().apply(getOptions()).preload();
                break;
            case BITMAP:
                getBmpBuilder().apply(getOptions()).preload();
                break;
            default:
        }
    }

    public void getGif(final SimpleCallback<GifDrawable> callback) {
        getGif(callback, null);
    }

    public void getGif(final SimpleCallback<GifDrawable> callback, final SimpleListener onFail) {
        getGifBuilder().apply(getOptions()).into(new CustomTarget<GifDrawable>() {
            @Override
            public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                if (callback != null) {
                    callback.onResult(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                if (onFail != null) {
                    onFail.onResult();
                }
            }
        });
    }

    public void getDrawable(final SimpleCallback<Drawable> callback) {
        getDrawable(callback, null);
    }

    public void getDrawable(final SimpleCallback<Drawable> callback, final SimpleListener onFail) {
        getBuilder().apply(getOptions()).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (callback != null) {
                    callback.onResult(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (onFail != null) {
                    onFail.onResult();
                }
            }
        });
    }

    public void getBitmap(final SimpleCallback<Bitmap> callback) {
        getBitmap(callback, null);
    }

    public void getBitmap(final SimpleCallback<Bitmap> onSuccess, final SimpleListener onFail) {
        getBmpBuilder().apply(getOptions()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if (onSuccess != null) {
                    onSuccess.onResult(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (onFail != null) {
                    onFail.onResult();
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private RequestOptions getOptions() {
        RequestOptions options = new RequestOptions();
        if (mPlaceHolder > 0) {
            //设置资源 id
            options.placeholder(mPlaceHolder);
        }
        if (mErrorHolder > 0) {
            //设置资源在加载失败的时候展示
            options.error(mErrorHolder);
        }
        if (mCache) {
            //设置缓存
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            options.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        if (mBitmapTransformation != null) {
            options.transform(mBitmapTransformation);
        }
        return options;
    }


    private RequestBuilder<Bitmap> getBmpBuilder() {
        if (mBuilderBmp == null) {
            mBuilderBmp = mManager.asBitmap();
        }
        return mBuilderBmp;
    }

    private RequestBuilder<GifDrawable> getGifBuilder() {
        if (mBuilderGif == null) {
            mBuilderGif = mManager.asGif();
        }
        return mBuilderGif;
    }

    private RequestBuilder<Drawable> getBuilder() {
        if (mBuilder == null) {
            mBuilder = mManager.asDrawable();
        }
        return mBuilder;
    }

    public interface OnGlideProgressListener {
        /**
         * 图片加载进度监听
         *
         * @param progress 加载进度
         */
        void onProgress(float progress);
    }
}
