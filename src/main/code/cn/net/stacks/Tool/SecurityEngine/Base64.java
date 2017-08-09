package cn.net.stacks.Tool.SecurityEngine;

import java.io.UnsupportedEncodingException;

/**
 * 安全引擎 - Base64
 * Developer: Kaiser.zsk
 * Time: 2017-8-5 15:27:07
 */
public class Base64 {

    /**
     * Base64解密
     * @param DecryptData 待解密数据
     * @return 解密后数据
     * @throws UnsupportedEncodingException
     */
    public static String Decrypt(String DecryptData) throws UnsupportedEncodingException {
        if (null == DecryptData)
            return null;
        return new String(org.apache.commons.codec.binary.Base64.decodeBase64(DecryptData.getBytes("utf-8")), "utf-8");
    }

    /**
     * Base64加密
     * @param EncryptionData 待加密数据
     * @return 加密后数据
     * @throws UnsupportedEncodingException
     */
    public static String Encryption(String EncryptionData) throws UnsupportedEncodingException {
        if (null == EncryptionData)
            return null;
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(EncryptionData.getBytes("utf-8")), "utf-8");
    }

    /**
     * 测试运行
     */
    public static void main(String[] args) {
        try {
            String Encryption = Base64.Encryption("{\"Issuer\":{\"Name\": \"Stacks.Net.CN\",\"Time\": \"1438955445\",\"Expiration\": 365 },\"Id\":\"900100666\",\"Account\":\"admin\",\"Phone\":\"13249846631\",\"Mail\":\"admin@stacks.net.cn\",\"Jurisdiction\":true}");
            String Decrypt = Base64.Decrypt(Encryption);
            System.out.println("加密后: " + Encryption);
            System.out.println("解密后: " + Decrypt);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
    }

}
