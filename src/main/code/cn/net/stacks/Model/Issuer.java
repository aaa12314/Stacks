package cn.net.stacks.Model;

/**
 * Token签发信息
 */
public class Issuer {

    // 签发单位名称
    private String Name;

    // 签发时间
    private String Time;

    // 有效时间
    private int Expiration;

    public Issuer(){}

    public  Issuer(int Expiration){
        this.Expiration = Expiration;
        this.Name = "Stacks Net CN";
        this.Time = String.valueOf(System.currentTimeMillis());
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getExpiration() {
        return Expiration;
    }

    public void setExpiration(int expiration) {
        Expiration = expiration;
    }
}
