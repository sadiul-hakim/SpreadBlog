package com.hakim.entities;

import java.sql.Timestamp;

/**
 *
 * @author ACT
 */
public class Comment {
    private int comid;
    private int pid;
    private int uid;
    private String commenttext;
    private String username;
    private String userpic;
    private Timestamp comtime;

    public Comment(int comid, int pid, int uid, String commenttext, String username, String userpic,Timestamp comtime) {
        this.comid = comid;
        this.pid = pid;
        this.uid = uid;
        this.commenttext = commenttext;
        this.username = username;
        this.userpic = userpic;
        this.comtime=comtime;
    }

    public Comment() {
    }

    public Comment(int pid, int uid, String commenttext, String username, String userpic) {
        this.pid = pid;
        this.uid = uid;
        this.commenttext = commenttext;
        this.username = username;
        this.userpic = userpic;
    }

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public Timestamp getComtime() {
        return comtime;
    }

    public void setComtime(Timestamp comtime) {
        this.comtime = comtime;
    }
    
    
}
