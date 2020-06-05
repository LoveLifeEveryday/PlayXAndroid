package com.xcynice.playxandroid.bean;


import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/5 09:49
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class Navigation {

    /**
     * articles : [{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1848,"link":"https://developers.google.cn/","niceDate":"2018-01-07 18:59","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515322795000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"Google开发者","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1849,"link":"http://www.github.com/","niceDate":"2018-01-07 19:00","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515322817000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"Github","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1850,"link":"https://stackoverflow.com/","niceDate":"2018-01-07 19:00","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515322829000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"stackoverflow","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1851,"link":"https://juejin.im/","niceDate":"2018-01-07 19:10","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323408000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"掘金","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1852,"link":"https://www.csdn.net/","niceDate":"2018-01-07 19:10","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323423000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"CSDN","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1853,"link":"https://www.jianshu.com/","niceDate":"2018-01-07 19:10","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323438000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"简书","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1854,"link":"http://www.androidweekly.cn/","niceDate":"2018-01-07 19:12","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323568000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"开发技术周报","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1855,"link":"https://toutiao.io/","niceDate":"2018-01-07 19:13","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323607000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"开发者头条","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1856,"link":"https://segmentfault.com/t/android","niceDate":"2018-01-07 19:13","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323635000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"segmentfault","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1857,"link":"http://www.androiddevtools.cn/","niceDate":"2018-01-07 19:14","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323651000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"androiddevtools","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"gank.io","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1859,"link":"http://gank.io/","niceDate":"2018-01-07 19:15","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515323720000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"干货集中营","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1862,"link":"http://a.codekk.com/","niceDate":"2018-01-07 19:27","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515324437000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"CodeKK","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1863,"link":"https://xiaozhuanlan.com/","niceDate":"2018-01-07 19:27","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515324456000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"小专栏","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1864,"link":"http://www.wanandroid.com/article/list/0?cid=176","niceDate":"2018-01-07 19:29","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515324541000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"国内大牛","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1865,"link":"https://github.com/android-cn/android-dev-com","niceDate":"2018-01-07 19:29","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515324559000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"国外大牛","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1866,"link":"https://www.androidos.net.cn/sourcecode","niceDate":"2018-01-07 19:29","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515324594000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"Android源码","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":1868,"link":"https://leetcode.com/","niceDate":"2018-01-07 19:36","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1515325010000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"leetcode","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":2405,"link":"https://dl.google.com/dl/android/maven2/index.html","niceDate":"2018-02-25 13:48","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1519537704000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"google mvn仓库","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":2406,"link":"http://jcenter.bintray.com/","niceDate":"2018-02-25 13:48","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1519537722000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"jcenter仓库","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":3469,"link":"https://mvnrepository.com/artifact/com.google.code.gson/gson","niceDate":"2018-10-10 10:49","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1539139799000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"maven仓库","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":8104,"link":"https://maven.aliyun.com/mvn/search","niceDate":"2019-03-20 23:27","niceShareDate":"未知时间","origin":"","prefix":"","projectLink":"","publishTime":1553095634000,"selfVisible":0,"shareDate":null,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"maven仓库 阿里云托管","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":10182,"link":"http://aospxref.com/","niceDate":"2019-11-09 23:11","niceShareDate":"2019-11-09 23:11","origin":"","prefix":"","projectLink":"","publishTime":1573312287000,"selfVisible":0,"shareDate":1573312287000,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"在线Android源码(引用)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":13307,"link":"https://developer.android.google.cn/reference/kotlin/android/widget/TextView?hl=en","niceDate":"2020-05-08 16:19","niceShareDate":"2020-05-08 16:19","origin":"","prefix":"","projectLink":"","publishTime":1588925969000,"selfVisible":0,"shareDate":1588925969000,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"Android Sdk 查看","type":0,"userId":-1,"visible":0,"zan":0}]
     * cid : 272
     * name : 常用网站
     */

    private int cid;
    private String name;
    private List<ArticlesBean> articles;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * apkLink :
         * audit : 1
         * author : 小编
         * canEdit : false
         * chapterId : 272
         * chapterName : 常用网站
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : false
         * id : 1848
         * link : https://developers.google.cn/
         * niceDate : 2018-01-07 18:59
         * niceShareDate : 未知时间
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1515322795000
         * selfVisible : 0
         * shareDate : null
         * shareUser :
         * superChapterId : 0
         * superChapterName :
         * tags : []
         * title : Google开发者
         * type : 0
         * userId : -1
         * visible : 0
         * zan : 0
         */

        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int selfVisible;
        private Object shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDescMd() {
            return descMd;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public Object getShareDate() {
            return shareDate;
        }

        public void setShareDate(Object shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }

}
