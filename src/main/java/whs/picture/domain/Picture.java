package whs.picture.domain;


import whs.user.domain.User;

public class Picture {

    private String pid;
    private String pname;
    private String purl;
    private User user;
    private Boolean pisprivate;
    private String pexpress;

    public Picture() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getPisprivate() {
        return pisprivate;
    }

    public void setPisprivate(Boolean pisprivate) {
        this.pisprivate = pisprivate;
    }

    public String getPexpress() {
        return pexpress;
    }

    public void setPexpress(String pexpress) {
        this.pexpress = pexpress;
    }


    @Override
    public String toString() {
        return "Picture{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", purl='" + purl + '\'' +
                ", pisprivate=" + pisprivate +
                ", pexpress='" + pexpress + '\'' +
                '}';
    }
}
