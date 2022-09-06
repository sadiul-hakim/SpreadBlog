package com.hakim.entities;

import java.sql.Timestamp;

/**
 *
 * @author ACT
 */
public class Post {
    private int pid;
    private int cid;
    private int id;
    private String title;
    private String content;
    private String code;
    private String pic;
    private Timestamp post_date;

    public Post() {
    }

    public Post(int cid, int id, String title, String content, String code, String pic) {
        this.cid = cid;
        this.id = id;
        this.title = title;
        this.content = content;
        this.code = code;
        this.pic = pic;
    }

    public Post(int pid, int cid, int id, String title, String content, String code, String pic, Timestamp post_date) {
        this.pid = pid;
        this.cid = cid;
        this.id = id;
        this.title = title;
        this.content = content;
        this.code = code;
        this.pic = pic;
        this.post_date = post_date;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    @Override
    public String toString() {
        return "Post{" + "pid=" + pid + ", cid=" + cid + ", id=" + id + ", title=" + title + ", content=" + content + ", code=" + code + ", pic=" + pic + ", post_date=" + post_date + '}';
    }
    
    
}
