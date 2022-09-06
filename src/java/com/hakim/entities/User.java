package com.hakim.entities;

import java.sql.Timestamp;

/**
 *
 * @author ACT
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String profile_pic;
    private String bio;
    private Timestamp rdate;

    

    public User(int id, String name, String email, String password,String profile_pic,Timestamp rdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile_pic=profile_pic;
        this.rdate=rdate;
    }

    public User() {
    }

    public User(String name, String email, String password,String profile_pic) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile_pic=profile_pic;
    }
    
    public Timestamp getDateTime() {
        return rdate;
    }

    public void setDateTime(Timestamp dateTime) {
        this.rdate = dateTime;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", profile_pic=" + profile_pic + ", bio=" + bio + ", rdate=" + rdate + '}';
    }

   
    
    
}
