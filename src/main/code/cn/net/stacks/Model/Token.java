package cn.net.stacks.Model;

public class Token {

    private Issuer Issuer;

    private String Id;

    private String Account;

    private String Phone;

    private String Mail;

    private String Jurisdiction;

    public Token(){}

    public Token(int Expiration, String Id, String Account, String Phone, String Mail, String Jurisdiction){
        this.Issuer = new Issuer(Expiration);
        this.Id = Id;
        this.Account = Account;
        this.Phone = Phone;
        this.Mail = Mail;
        this.Jurisdiction = Jurisdiction;
    }

    public cn.net.stacks.Model.Issuer getIssuer() {
        return Issuer;
    }

    public void setIssuer(cn.net.stacks.Model.Issuer issuer) {
        Issuer = issuer;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getJurisdiction() {
        return Jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        Jurisdiction = jurisdiction;
    }
}
