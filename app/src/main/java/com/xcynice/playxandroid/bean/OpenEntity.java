package com.xcynice.playxandroid.bean;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/16
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description OpenEntity
 */


public class OpenEntity {

    private String project;
    private String description;
    private String link;

    public OpenEntity(String project, String description, String link) {
        this.project = project;
        this.description = description;
        this.link = link;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
