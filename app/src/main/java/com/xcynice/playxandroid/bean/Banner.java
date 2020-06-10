package com.xcynice.playxandroid.bean;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/1 21:27
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */
@SuppressWarnings("all")
public class Banner {


    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://wanandroid.com/blogimgs/b6637de6-7173-4866-940e-3e5e2fbc7bf2.png
     * isVisible : 1
     * order : 0
     * title : Jetpack能否一统江湖？
     * type : 0
     * url : https://mp.weixin.qq.com/s/S1_RHHaKVj7QnLmz5MqWbg
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
