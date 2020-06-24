# 玩 Android 软件设计总结

> 前言：
>
> - 每做一个东西，都要进行总结归纳，以便得以吸收最大的效果，让自己的努力发挥出最大的价值
> - 总结一个东西，可以锻炼你的思维，增长你对知识的理解，有助于你吃透整个知识体系

![image-20200623110554472](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202006/23/110556-928480.png)

## 一.完成的功能

> 项目地址：[PlayXAndroid](https://github.com/LoveLifeEveryday/PlayXAndroid)

- 登陆
- 注册
- 退出登陆
- 扫描二维码
- 搜索

> - 历史搜索
> - 搜索热门

- 首页文章的展示

> - 下拉刷新
> - 上拉加载更多
> - 收藏

- 轮播图

- 问答

- 体系数据的展示

- 导航列表的展示

- 我的界面

>- 积分展示 （运用了滚动式的积分数字展示动画）
>- 积分排行榜
>- 我的分享
>- 我的收藏
>- 开源项目
>- 关于作者
>- 系统设置

## 二.项目的截图

> - 项目界面和功能自参考 [goweii/WanAndroid](https://github.com/goweii/WanAndroid) 和 [yechaoa/wanandroid_java](https://github.com/yechaoa/wanandroid_java)
>
> - 接口用的是：https://www.wanandroid.com/blog/show/2

![image-20200622225906627](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202006/22/225908-822621.png)

 ![image-20200623103300212](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202006/23/103403-539310.png)

![image-20200622230125667](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202006/23/103006-886891.png)

![image-20200622230525592](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202006/22/230528-255458.png)

![image-20200622230918800](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202006/22/230922-773426.png)

## 三.项目的值得学习的地方

### 3.1 使用自己开发的 `MVP` 框架

### 3.2 `String` 类放到 `string.xml` 中

> 在工具类中写了一个获取字符串的方法

```java
  public static String getString(int id) {
        return getApplication().getResources().getString(id);
    }
```

### 3.3 `BRVAH` 的简单使用

- 继承的时候需要指定泛型

```java
public class OpenSourceAdapter extends BaseQuickAdapter<OpenEntity, BaseViewHolder>
    //这里的 OpenEntity 是必须特殊指定的
```

- `setNewData` 后不用 `notify` ，因为 `setNewData` 内部已经自动 `notify` 了
- 如果有一些需要差异化处理的，可以用 `setType` 指定

```java
public class ArticleAdapter extends BaseQuickAdapter<Article.DataDetailBean, BaseViewHolder> {


    private boolean mTypeIsCollect = false;
    public void setType(boolean typeIsCollect) {
        mTypeIsCollect = typeIsCollect;
    }
     protected void convert(@NonNull BaseViewHolder helper, Article.DataDetailBean item) {
    if (TextUtils.isEmpty(item.author)) {
            // 如果是收藏列表的话，不会返回 shareUser 字段，因此直接设为匿名
            if (mTypeIsCollect) {
                helper.setText(R.id.tv_article_author, XUtil.getString(R.string.anonymity));
            } else {
                helper.setText(R.id.tv_article_author, item.shareUser);
            }
        } 
        }
        }
```

- 如果实现收藏功能的话，并且没有直接改变 `Adapter`，只是改变 `Adapter` 中的 `List`，记得 `noptify`

```java
    @Override
    public void showCollectSuccess(String successMessage) {
        mArticleList.get(mPosition).collect = true;
        mArticleAdapter.notifyDataSetChanged();
    }
```

### 3.4 `WebView` 的使用

- 记得设置 `WebViewClient` ，以免跳转出浏览器
- 记得针对 `HTTP` 进行特殊化处理，否则不能访问

```xml
<network-security-config>
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

```xml
 <application
        android:name=".app.App"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
              <!--这句非常重要，指定访问规则-->
        android:networkSecurityConfig="@xml/network_security_config"/>
```

- 设置加载进度条的 `WebView` 

### 3.5 `RecyclerView` 滑动冲突的处理

- `RecyclerView` ，`Banner` ，下拉刷新，`ScrollView` 的冲突处理

如果是给 `RecyclerView`  头部添加 `Banner` 的话，建议使用 `addHeader` 的形式添加

```java
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取到 Banner
        View mViewBanner = inflater.inflate(R.layout.layout_banner, container, false);
        mBannerHome = mViewBanner.findViewById(R.id.banner_home);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

{
    
     mArticleAdapter.addHeaderView(mBannerHome);
}
```

解决 `SwipeRefreshLayout` 嵌套 `banner` 滑动冲突方式：自定义下拉刷新控件

> 其实也有另外一种实现方案，就是用 `BRVAN` 的方式

```java

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/3 20:39
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 下拉刷新控件，修复了滑动冲突
 * 思路：
 * 1. 因为下拉刷新，只有纵向滑动的时候才有效，只需要判断此时是纵向滑动还是横向滑动就可以了。
 * 2. 纵向滑动就拦截事件，横向滑动不拦截。
 * 3. 怎么判断是纵向滑动还是横向滑动，只要判断Y轴的移动距离大于X轴的移动距离那么就判定为纵向滑动就行了
 */

public class SuperSwipeRefreshLayout extends SwipeRefreshLayout {

    private float startX;
    private float startY;

    /**
     * 记录 viewpager 是否拖拽的标记
     */
    private boolean mIsVpDragger;
    private int mTouchSlop;

    public SuperSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public SuperSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                // 初始化标记
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsVpDragger) {
                    return false;
                }
                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                if (distanceX > mTouchSlop && distanceX > distanceY) {
                    mIsVpDragger = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsVpDragger = false;
                break;
            default:
                break;
        }
        // 如果是 Y 轴位移大于 X 轴，事件交给 swipeRefreshLayout 处理
        return super.onInterceptTouchEvent(ev);
    }
}

```

> 扩展：
>
> - [SwipeRefreshLayout 嵌套 RecyclerView滑动冲突](https://blog.csdn.net/yechaoa/article/details/95056882)
> - [SwipeRefreshLayout 嵌套ScrollView 滑动冲突](https://blog.csdn.net/yechaoa/article/details/86609380)
> - [Android ScrollView嵌套RecyclerView滑动卡顿](https://blog.csdn.net/yechaoa/article/details/86529221)

### 3.6 刷新，加载更多的流程

### 3.7 如何改变 `Activity` 跳转的效果

- 先定义好进入和退出 `Activity` 的方法 

> 下面的 `xml` 仅仅是从下向上弹出的效果，如果想要实现其他效果的话，可以网上找下

```xml
//bottom_in.xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="300"
    android:interpolator="@android:anim/decelerate_interpolator"
    android:shareInterpolator="true">
    <translate
        android:fromXDelta="0%"
        android:fromYDelta="100%"
        android:toXDelta="0%"
        android:toYDelta="0%" />
</set>
```

```xml
//bottom_silent
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <alpha
        android:fromAlpha="1"
        android:toAlpha="0.3" />
    <translate
        android:duration="300"
        android:fromYDelta="0"
        android:toYDelta="0" />
</set>
```

- 在需要跳转到的 `Activity` 的 `onCreate` 和 `finish` 方法中， `overridePendingTransition` 

```java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
    }
```

### 3.8 `Fragment` 的基本使用

- 基本的显示和隐藏

```java
mFragmentManager = getSupportFragmentManager();
FragmentTransaction transaction = mFragmentManager.beginTransaction();
// 初始化搜索历史的 Fragment
Fragment searchHistoryFragment = mFragmentManager.findFragmentByTag(SearchHistoryFragment.class.getName());
if (searchHistoryFragment == null) {
    mSearchHistoryFragment = new SearchHistoryFragment();
    transaction.add(R.id.fl, mSearchHistoryFragment, SearchHistoryFragment.class.getName());
} else {
    mSearchHistoryFragment = (SearchHistoryFragment) searchHistoryFragment;
}

// 初始化搜索结果的 Fragment
Fragment searchResultFragment = mFragmentManager.findFragmentByTag(SearchResultFragment.class.getName());
if (searchResultFragment == null) {
    mSearchResultFragment = new SearchResultFragment();
    transaction.add(R.id.fl, mSearchResultFragment, SearchResultFragment.class.getName());
} else {
    mSearchResultFragment = (SearchResultFragment) searchResultFragment;
}

transaction.show(mSearchHistoryFragment);
transaction.hide(mSearchResultFragment);
transaction.commit();
```

- 和 `ViewPager` 协同使用

```java
CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), titleList);
commonViewPagerAdapter.addFragment(new LoginFragment());
commonViewPagerAdapter.addFragment(new RegisterFragment());
mVpLogin.setAdapter(commonViewPagerAdapter);
```

- 和 `TabLayout` , `ViewPager` 协同使用

```java
mTlTreeGrandson.setupWithViewPager(mVpTreeGrandson);
CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), titles);
or (int i = 0; i < titles.size(); i++) {
   if (tree != null) {
       commonViewPagerAdapter.addFragment(TreeGrandsonFragment.newInstance(tree.getChildren().get(i).getId()));
   }

VpTreeGrandson.setAdapter(commonViewPagerAdapter);
VpTreeGrandson.setCurrentItem(pos);
```

> 未完待续......