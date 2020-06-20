package com.xcynice.playxandroid.bean;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/20 18:49
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class HeadIcon implements Parcelable {
    public Uri headIcon;

    protected HeadIcon(Parcel in) {
        headIcon = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<HeadIcon> CREATOR = new Creator<HeadIcon>() {
        @Override
        public HeadIcon createFromParcel(Parcel in) {
            return new HeadIcon(in);
        }

        @Override
        public HeadIcon[] newArray(int size) {
            return new HeadIcon[size];
        }
    };

    public Uri getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(Uri headIcon) {
        this.headIcon = headIcon;
    }

    public HeadIcon(Uri headIcon) {
        this.headIcon = headIcon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(headIcon, i);
    }
}
