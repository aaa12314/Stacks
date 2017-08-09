package cn.net.stacks.Tool.SecurityEngine;

import java.security.MessageDigest;

/**
 * 安全引擎 - MD5
 * Developer: Kaiser.zsk
 * Time: 2017-8-5 15:27:07
 */
public class MD5 {

    /**
     * MD5加密
     * @param EncryptionData 待加密数据
     * @return 加密后数据
     */
    public static String Encryption(String EncryptionData) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }
        char[] charArray = EncryptionData.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 测试运行
     */
    public static void main(String args[]) {
        String Data = new String("堆栈网络");
        System.out.println("加密前: " + Data);
        System.out.println("加密后: " + Encryption(Data));
    }

}
