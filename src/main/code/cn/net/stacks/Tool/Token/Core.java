package cn.net.stacks.Tool.Token;

import cn.net.stacks.Model.Token;
import cn.net.stacks.Tool.SecurityEngine.Base64;
import cn.net.stacks.Tool.SecurityEngine.DES;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class Core {

    private static Gson gson = new Gson();

    public static void Issue() throws UnsupportedEncodingException {

        Token token = new Token(365,"8888888","张书恺","13249846631","admin@stacks.net.cn","admin");
        String des = DES.Encryption(gson.toJson(token), "nXpRhq743NM=");
        String base64 = Base64.Encryption(des);
        System.out.println(base64);

    }

    public static void Verification(String token) throws UnsupportedEncodingException {

        String base64 = Base64.Decrypt(token);
        String des = DES.Decrypt(base64,"nXpRhq743NM=");
        Token tokenClass = gson.fromJson(des,Token.class);
        System.out.println(tokenClass.getAccount());
    }

    public static void main(String[] args) {
        try {
            Verification("NmVjYmQ1ZTliNDc5ZTA5NjA1MTg3MDNkYmRlZjJlMjMwNTczOGU1MGFiMjM2YWEwZGU3ZGQxN2EzMDZmNjg3NWY4ZGJiN2QzZmE1NjY0ZTFmMmZlYjdkZGM0MTYzNmI0MDkzYjMxMTc3ZjQxOTQzYjAzYzk2MDI5ZWU3MzFiOWJiN2I3MGNlN2QwMjc0OWNmNDY5MmE2NDNlNmU4NWY2NTExNDU3Yjc2YzIxZjg2MjkwNjc0ZmNjMTA0OTA5ZWM5MmY1MzI5OGFlNGE0NmE1ZmJmZGE1NzM2NjVkNmZlZDU5ZDgxODYzNmNhZmEyN2ZiZmY2N2M4NmE0MmUxOThhZTU3NjhkMTU2YzJlYjdmMjk5OTU2OThiNjA4NzhmMWRlMjZjOGQ5YzM1MjhjMTAwM2I2MDE4Yzc4OTEwZGRmOGEzYjg3NzQ4YWM3OWNhYTEwNDkxYjcxODg1MWFjMDI2NDNiY2ViMTBlMjBkODA0NDEzZTk3MmFhMTFiM2FlNjAy");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
