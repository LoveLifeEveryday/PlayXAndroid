package com.xcynice.playxandroid.bean;


import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/14 14:11
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description CoinRank
 */

public class CoinRank {


    /**
     * curPage : 2
     * datas : [{"coinCount":7308,"level":74,"rank":"31","userId":30114,"username":"E**an_Jin"},{"coinCount":7270,"level":73,"rank":"32","userId":6095,"username":"W**derfulMtf"},{"coinCount":7233,"level":73,"rank":"33","userId":3825,"username":"请**娃哈哈"},{"coinCount":7206,"level":73,"rank":"34","userId":7541,"username":"l**64301766"},{"coinCount":7182,"level":72,"rank":"35","userId":9180,"username":"c**2018"},{"coinCount":7108,"level":72,"rank":"36","userId":863,"username":"m**qitian"},{"coinCount":7108,"level":72,"rank":"37","userId":7075,"username":"c**ndroid"},{"coinCount":7023,"level":71,"rank":"38","userId":23244,"username":"a**ian"},{"coinCount":7002,"level":71,"rank":"39","userId":29103,"username":"9**889276@qq.com"},{"coinCount":6943,"level":70,"rank":"40","userId":20567,"username":"v**0123"},{"coinCount":6910,"level":70,"rank":"41","userId":4662,"username":"1**71599512"},{"coinCount":6861,"level":69,"rank":"42","userId":29390,"username":"L**ing"},{"coinCount":6843,"level":69,"rank":"43","userId":1580,"username":"k**od21"},{"coinCount":6843,"level":69,"rank":"44","userId":30006,"username":"星**tar"},{"coinCount":6777,"level":68,"rank":"45","userId":7365,"username":"l**kad"},{"coinCount":6777,"level":68,"rank":"46","userId":14839,"username":"x**y_sjc"},{"coinCount":6759,"level":68,"rank":"47","userId":29398,"username":"l**bwstory"},{"coinCount":6738,"level":68,"rank":"48","userId":5899,"username":"贝**的黑夜"},{"coinCount":6629,"level":67,"rank":"49","userId":9296,"username":"j**123456"},{"coinCount":6534,"level":66,"rank":"50","userId":9778,"username":"1**11985351"},{"coinCount":6500,"level":65,"rank":"51","userId":29030,"username":"s**世界"},{"coinCount":6470,"level":65,"rank":"52","userId":28454,"username":"c**xzxzc"},{"coinCount":6469,"level":65,"rank":"53","userId":28457,"username":"w**dla"},{"coinCount":6342,"level":64,"rank":"54","userId":14854,"username":"z**23456"},{"coinCount":6305,"level":64,"rank":"55","userId":6142,"username":"c**huah"},{"coinCount":6305,"level":64,"rank":"56","userId":16064,"username":"1**30703051"},{"coinCount":6250,"level":63,"rank":"57","userId":1260,"username":"于**家的吴蜀黍"},{"coinCount":6167,"level":62,"rank":"58","userId":21873,"username":"L**gSh1z"},{"coinCount":6074,"level":61,"rank":"59","userId":27602,"username":"l**hehan"},{"coinCount":6035,"level":61,"rank":"60","userId":20375,"username":"z**hailong"}]
     * offset : 30
     * over : false
     * pageCount : 1363
     * size : 30
     * total : 40886
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
         * coinCount : 7308
         * level : 74
         * rank : 31
         * userId : 30114
         * username : E**an_Jin
         */

        private int coinCount;
        private int level;
        private String rank;
        private int userId;
        private String username;
        public boolean anim;

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
}
