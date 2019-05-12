package whs.user.domain;


import whs.picture.domain.Picture;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String uid;
    private String uphoto;
    private String uimg;
    private String uname;
    private String upassword;
    private String usex;
    private int uage;
    private String uexpress;
    private Date ulogintime;
    private String motto;
    private Set<Picture> pictureSet=new HashSet<Picture>();



    public User() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getUname() {
        return uname;
    }


    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public String getUexpress() {
        return uexpress;
    }

    public void setUexpress(String uexpress) {
        this.uexpress = uexpress;
    }

    public Date getUlogintime() {
        return ulogintime;
    }

    public void setUlogintime(Date ulogintime) {
        this.ulogintime = ulogintime;
    }

    public Set<Picture> getPictureSet() {
        return pictureSet;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public void setPictureSet(Set<Picture> pictureSet) {
        this.pictureSet = pictureSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", uphoto='" + uphoto + '\'' +
                ", uimg='" + uimg + '\'' +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", usex='" + usex + '\'' +
                ", uage=" + uage +
                ", uexpress='" + uexpress + '\'' +
                ", ulogintime=" + ulogintime +
                '}';
    }

}
