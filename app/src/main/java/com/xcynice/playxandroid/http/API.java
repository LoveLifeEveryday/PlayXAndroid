package com.xcynice.playxandroid.http;


import com.xcynice.playxandroid.R;
import com.xcynice.playxandroid.base.BaseBean;
import com.xcynice.playxandroid.bean.Article;
import com.xcynice.playxandroid.bean.Banner;
import com.xcynice.playxandroid.bean.Navigation;
import com.xcynice.playxandroid.bean.Tree;
import com.xcynice.playxandroid.bean.TreeChild;
import com.xcynice.playxandroid.bean.User;
import com.xcynice.playxandroid.bean.UserInfo;
import com.xcynice.playxandroid.util.XUtil;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description : API
 * 接口的管理类
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


public class API {

    static final String BASE_URL = XUtil.getApplication().getString(R.string.baseUrl);

    public interface IWAZApi {

        //-----------------------【首页相关】----------------------


        /**
         * 首页 banner
         *
         * @return banner数据
         */
        @GET("banner/json")
        Observable<BaseBean<List<Banner>>> getBanner();


        /**
         * 首页文章列表 这里的{}是填入页数
         *
         * @param page 页数
         * @return 文章列表
         */
        @GET("article/list/{page}/json")
        Observable<BaseBean<Article>> getArticleList(@Path("page") Integer page);


        //-----------------------【登录注册】----------------------


        @FormUrlEncoded
        @POST("user/login")
        Observable<BaseBean<User>> login(@Field("username") String username, @Field("password") String password);


        @FormUrlEncoded
        @POST("user/register")
        Observable<BaseBean<User>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


        //-----------------------【  问答  】----------------------

        @GET("wenda/list/{page}/json")
        Observable<BaseBean<Article>> getAskAndQuestionList(@Path("page") Integer page);


        //-----------------------【  收藏  】----------------------

        /**
         * 收藏站内文章
         *
         * @param id 文章的 id
         * @return 文章数据
         */
        @POST("lg/collect/{id}/json")
        Observable<BaseBean> collectIn(@Path("id") Integer id);

        //取消收藏---文章列表

        /**
         * 取消收藏 -- 文章列表
         *
         * @param id 文章 id
         * @return 是否取消收藏成功
         */
        @POST("lg/uncollect_originId/{id}/json")
        Observable<BaseBean> unCollect(@Path("id") Integer id);

        //-----------------------【  体系  】----------------------

        /**
         * 得到体系数据
         *
         * @return 体系数据
         */
        @GET("tree/json")
        Observable<BaseBean<List<Tree>>> getTreeList();


        /**
         * 得到知识体系下的文章
         *
         * @param page 页数
         * @param cid  分类的 id
         * @return 知识体系文章
         */
        @GET("article/list/{page}/json?")
        Observable<BaseBean<TreeChild>> getTreeChildList(@Path("page") Integer page, @Query("cid") Integer cid);


        //-----------------------【  导航  】----------------------

        /**
         * 得到导航数据
         *
         * @return 导航数据
         */
        @GET("navi/json")
        Observable<BaseBean<List<Navigation>>> getNavigationList();


        //-----------------------【获取个人信息】----------------------


        /**
         * 得到用户的积分信息
         *
         * @return 用户的积分信息
         */
        @GET("lg/coin/userinfo/json")
        Observable<BaseBean<UserInfo>> getUserCoin();
    }

}
