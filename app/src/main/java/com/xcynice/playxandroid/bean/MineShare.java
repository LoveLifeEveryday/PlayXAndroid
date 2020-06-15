package com.xcynice.playxandroid.bean;


import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 19:10
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class MineShare {


    /**
     * coinInfo : {"coinCount":1028,"level":11,"rank":"707","userId":43523,"username":"1**27456299"}
     * shareArticles : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12658,"link":"https://juejin.im/post/5e7d62f1e51d4546df7375dd","niceDate":"2020-03-28 12:58","niceShareDate":"2020-03-28 12:58","origin":"","prefix":"","projectLink":"","publishTime":1585371524000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1585371524000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"一文洞悉JVM内存管理机制","type":0,"userId":43523,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12309,"link":"https://juejin.im/post/5e6712356fb9a07cd323e7a2","niceDate":"2020-03-10 15:01","niceShareDate":"2020-03-10 15:01","origin":"","prefix":"","projectLink":"","publishTime":1583823687000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1583823687000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Thread之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12213,"link":"https://juejin.im/post/5e61bf2de51d4526ea7f00bd","niceDate":"2020-03-06 14:31","niceShareDate":"2020-03-06 14:31","origin":"","prefix":"","projectLink":"","publishTime":1583476279000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1583476279000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Handler之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12177,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:42","origin":"","prefix":"","projectLink":"","publishTime":1583249253000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1583235761000,"shareUser":"许朋友爱玩","superChapterId":81,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12178,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:43","origin":"","prefix":"","projectLink":"","publishTime":1583249244000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1583235821000,"shareUser":"许朋友爱玩","superChapterId":81,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12179,"link":"https://juejin.im/post/5e5c74c1e51d4526dc7be184","niceDate":"2020-03-03 19:44","niceShareDate":"2020-03-03 19:44","origin":"","prefix":"","projectLink":"","publishTime":1583235894000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1583235894000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的四大组件之旅","type":0,"userId":43523,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12131,"link":"https://juejin.im/post/5e520c925188254903693f61","niceDate":"2020-02-28 19:35","niceShareDate":"2020-02-28 19:35","origin":"","prefix":"","projectLink":"","publishTime":1582889715000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1582889715000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Drawable之旅","type":0,"userId":43523,"visible":1,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":7}
     */

    private CoinInfoBean coinInfo;
    private ShareArticlesBean shareArticles;

    public CoinInfoBean getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoBean coinInfo) {
        this.coinInfo = coinInfo;
    }

    public ShareArticlesBean getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(ShareArticlesBean shareArticles) {
        this.shareArticles = shareArticles;
    }

    public static class CoinInfoBean {
        /**
         * coinCount : 1028
         * level : 11
         * rank : 707
         * userId : 43523
         * username : 1**27456299
         */

        private int coinCount;
        private int level;
        private String rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class ShareArticlesBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12658,"link":"https://juejin.im/post/5e7d62f1e51d4546df7375dd","niceDate":"2020-03-28 12:58","niceShareDate":"2020-03-28 12:58","origin":"","prefix":"","projectLink":"","publishTime":1585371524000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1585371524000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"一文洞悉JVM内存管理机制","type":0,"userId":43523,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12309,"link":"https://juejin.im/post/5e6712356fb9a07cd323e7a2","niceDate":"2020-03-10 15:01","niceShareDate":"2020-03-10 15:01","origin":"","prefix":"","projectLink":"","publishTime":1583823687000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1583823687000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Thread之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12213,"link":"https://juejin.im/post/5e61bf2de51d4526ea7f00bd","niceDate":"2020-03-06 14:31","niceShareDate":"2020-03-06 14:31","origin":"","prefix":"","projectLink":"","publishTime":1583476279000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1583476279000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Handler之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12177,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:42","origin":"","prefix":"","projectLink":"","publishTime":1583249253000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1583235761000,"shareUser":"许朋友爱玩","superChapterId":81,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12178,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:43","origin":"","prefix":"","projectLink":"","publishTime":1583249244000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1583235821000,"shareUser":"许朋友爱玩","superChapterId":81,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12179,"link":"https://juejin.im/post/5e5c74c1e51d4526dc7be184","niceDate":"2020-03-03 19:44","niceShareDate":"2020-03-03 19:44","origin":"","prefix":"","projectLink":"","publishTime":1583235894000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1583235894000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的四大组件之旅","type":0,"userId":43523,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12131,"link":"https://juejin.im/post/5e520c925188254903693f61","niceDate":"2020-02-28 19:35","niceShareDate":"2020-02-28 19:35","origin":"","prefix":"","projectLink":"","publishTime":1582889715000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1582889715000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Drawable之旅","type":0,"userId":43523,"visible":1,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 7
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author :
             * canEdit : false
             * chapterId : 494
             * chapterName : 广场
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : false
             * id : 12658
             * link : https://juejin.im/post/5e7d62f1e51d4546df7375dd
             * niceDate : 2020-03-28 12:58
             * niceShareDate : 2020-03-28 12:58
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1585371524000
             * realSuperChapterId : 493
             * selfVisible : 0
             * shareDate : 1585371524000
             * shareUser : 许朋友爱玩
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : 一文洞悉JVM内存管理机制
             * type : 0
             * userId : 43523
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
            private int realSuperChapterId;
            private int selfVisible;
            private long shareDate;
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

            public int getRealSuperChapterId() {
                return realSuperChapterId;
            }

            public void setRealSuperChapterId(int realSuperChapterId) {
                this.realSuperChapterId = realSuperChapterId;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
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

}
