package cn.net.stacks.Tool.Token;

import cn.net.stacks.Model.Token;
import cn.net.stacks.Tool.SecurityEngine.Base64;
import cn.net.stacks.Tool.SecurityEngine.DES;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class Core {

    private static Gson gson = new Gson();

    public static String Issue(int Expiration,String Id,String Name,String Phone,String Mail,String Jurisdiction) throws UnsupportedEncodingException {
        Token token = new Token(Expiration,Id,Name,Phone,Mail,Jurisdiction);
        String des = DES.Encryption(gson.toJson(token), "nXpRhq743NM=");
        return Base64.Encryption(des);
    }

    public static void Verification(String token) throws UnsupportedEncodingException {

        String base64 = Base64.Decrypt(token);
        String des = DES.Decrypt(base64,"nXpRhq743NM=");
        Token tokenClass = gson.fromJson(des,Token.class);
        System.out.println(tokenClass.getAccount());
    }

    public static void main(String[] args) {
        try {
            Verification("NmVjYmQ1ZTliNDc5ZTA5NjA1MTg3MDNkYmRlZjJlMjMwNTczOGU1MGFiMjM2YWEwZGU3ZGQxN2EzMDZmNjg3NWY4ZGJiN2QzZmE1NjY0ZTFhZTA4OTcwY2VhMzk4NzViYzU4ODdkNTQ0ZWZlZDQwZDAzYzk2MDI5ZWU3MzFiOWIzMDkxNGM1YzEwODBhMzc1MGNmNWYyMDkwNzlhNWQwZGEwODgyNzdkZjUyMzc3YmIyY2Y4ODI3Njc3MWQwODM4MDRjMjZmYmZkY2JjZjg4MmY4NzRjMzVlZjZkNzQ0MTkwYWRmYTkyYzgwY2JiMjhiOTQwZTBkOGM1NjlkYWQ2N2Y2NDAwYWNlNDliZjIxMjhkMzg4YWQ2MTliYmExMGYyOWU4YzMyNWQyYWRmZDhkZTY5NzdlMzU5YTdmMTdkNWRiMzQ4Y2MwZGFiMWFjNmZmNjRhNGNjOWNmODNkOWE3NjgyMGRhYWJkY2E5ZWY5NmE=");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
