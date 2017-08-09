package cn.net.stacks.Model;

import com.google.gson.annotations.SerializedName;

/**
 * 用户信息
 */
public class User {

    // 用户编号
    @SerializedName("Id")
    private Integer uid;

    // 用户名称
    @SerializedName("Name")
    private String uname;

    // 用户昵称
    @SerializedName("NickName")
    private String unickname;

    // 用户性别
    @SerializedName("Gender")
    private Integer ugender;

    // 用户手机
    @SerializedName("Phone")
    private String uphone;

    // 用户邮箱
    @SerializedName("Mail")
    private String umail;

    // 注册时间
    @SerializedName("Time")
    private String utime;

    // 身份秘钥
    @SerializedName("Secret")
    private String usecret;

    public User(Integer uid, String uname, String unickname, Integer ugender, String uphone, String umail, String utime, String usecret) {
        this.uid = uid;
        this.uname = uname;
        this.unickname = unickname;
        this.ugender = ugender;
        this.uphone = uphone;
        this.umail = umail;
        this.utime = utime;
        this.usecret = usecret;
    }

    public User() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname == null ? null : unickname.trim();
    }

    public Integer getUgender() {
        return ugender;
    }

    public void setUgender(Integer ugender) {
        this.ugender = ugender;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone == null ? null : uphone.trim();
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail == null ? null : umail.trim();
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime == null ? null : utime.trim();
    }

    public String getUsecret() {
        return usecret;
    }

    public void setUsecret(String usecret) {
        this.usecret = usecret == null ? null : usecret.trim();
    }
}