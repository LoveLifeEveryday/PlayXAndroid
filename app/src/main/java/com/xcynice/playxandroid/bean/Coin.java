package com.xcynice.playxandroid.bean;


import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/6/13 22:13
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description 积分数据
 */

public class Coin {


    /**
     * curPage : 0
     * datas : [{"coinCount":26,"date":1592026574000,"desc":"2020-06-13 13:36:14 签到 , 积分：11 + 15","id":233470,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":25,"date":1591952452000,"desc":"2020-06-12 17:00:52 签到 , 积分：11 + 14","id":233094,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":24,"date":1591723179000,"desc":"2020-06-10 01:19:39 签到 , 积分：11 + 13","id":230738,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":23,"date":1591674976000,"desc":"2020-06-09 11:56:16 签到 , 积分：11 + 12","id":230291,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":22,"date":1591622606000,"desc":"2020-06-08 21:23:26 签到 , 积分：11 + 11","id":229778,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":21,"date":1591327969000,"desc":"2020-06-05 11:32:49 签到 , 积分：11 + 10","id":227761,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":20,"date":1591239768000,"desc":"2020-06-04 11:02:48 签到 , 积分：11 + 9","id":226885,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":19,"date":1591189281000,"desc":"2020-06-03 21:01:21 签到 , 积分：11 + 8","id":226360,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":18,"date":1591065619000,"desc":"2020-06-02 10:40:19 签到 , 积分：11 + 7","id":225147,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":17,"date":1590977908000,"desc":"2020-06-01 10:18:28 签到 , 积分：11 + 6","id":224224,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":16,"date":1590818353000,"desc":"2020-05-30 13:59:13 签到 , 积分：11 + 5","id":223266,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":15,"date":1589171977000,"desc":"2020-05-11 12:39:37 签到 , 积分：11 + 4","id":208493,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":14,"date":1588993598000,"desc":"2020-05-09 11:06:38 签到 , 积分：11 + 3","id":207024,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":13,"date":1588037381000,"desc":"2020-04-28 09:29:41 签到 , 积分：11 + 2","id":199548,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":12,"date":1587958965000,"desc":"2020-04-27 11:42:45 签到 , 积分：11 + 1","id":198814,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":11,"date":1586147771000,"desc":"2020-04-06 12:36:11 签到 , 积分：11 + 0","id":181172,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":39,"date":1586058650000,"desc":"2020-04-05 11:50:50 签到 , 积分：10 + 29","id":180693,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":38,"date":1585724343000,"desc":"2020-04-01 14:59:03 签到 , 积分：10 + 28","id":177984,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":37,"date":1585584133000,"desc":"2020-03-31 00:02:13 签到 , 积分：10 + 27","id":176566,"reason":"签到","type":1,"userId":43523,"userName":"19927456299"},{"coinCount":14,"date":1585371524000,"desc":"2020-03-28 12:58:44 分享文章 , 积分：10 + 4","id":174790,"reason":"分享文章","type":3,"userId":43523,"userName":"19927456299"}]
     * offset : -20
     * over : false
     * pageCount : 3
     * size : 20
     * total : 54
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
         * coinCount : 26
         * date : 1592026574000
         * desc : 2020-06-13 13:36:14 签到 , 积分：11 + 15
         * id : 233470
         * reason : 签到
         * type : 1
         * userId : 43523
         * userName : 19927456299
         */

        private int coinCount;
        private long date;
        private String desc;
        private int id;
        private String reason;
        private int type;
        private int userId;
        private String userName;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

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

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

}
