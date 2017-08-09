package cn.net.stacks.Tool.SecurityEngine;

import org.apache.logging.log4j.util.Strings;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 安全引擎 - DES
 * Developer: Kaiser.zsk
 * Time: 2017-8-5 16:43:43
 */
public class DES {

    /**
     * 全局数组
     */
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7",
                                                "8", "9", "a", "b", "c", "d", "e", "f" };
    /**
     * 初始化密钥
     * @return 密钥
     */
    public static String init() {
        return init(null);
    }

    /**
     * 初始化密钥
     * @param seed 初始化参数
     * @return 密钥
     */
    private static String init(String seed) {
        SecureRandom secure;
        String str = null;
        try {
            if (null != seed) {
                secure = new SecureRandom(decryptBase64(seed));
            } else {
                secure = new SecureRandom();
            }
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(secure);
            SecretKey key = generator.generateKey();
            str = encryptBase64(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 转换密钥
     * @param key 密钥的字节数组
     * @return 转换后的密钥
     */
    private static Key byteToKey(byte[] key) {
        SecretKey secretKey = null;
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            secretKey = factory.generateSecret(dks);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * DES解密
     * @param DecryptData 待解密数据
     * @param Key 密钥
     * @return 解密后数据
     */
    public static String Decrypt(String DecryptData, String Key) {
        if (Strings.isEmpty(DecryptData))
            return "";
        byte[] bytes = Decrypt(hexString2Bytes(DecryptData), Key);
        return new String(bytes);
    }

    /**
     * DES解密
     * @param DecryptData 待解密数据
     * @param Key 密钥
     * @return 解密后数据
     */
    private static byte[] Decrypt(byte[] DecryptData, String Key) {
        byte[] bytes = null;
        try {
            Key k = byteToKey(decryptBase64(Key));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, k);
            bytes = cipher.doFinal(DecryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * DES加密
     * @param EncryptionData 待加密数据
     * @param Key 密钥
     * @return 加密后数据
     */
    public static String Encryption(String EncryptionData, String Key) {
        // 验证传入的字符串
        if (Strings.isEmpty(EncryptionData)) {
            return "";
        }
        // 调用加密方法完成加密
        byte[] bytes = Encryption(EncryptionData.getBytes(), Key);
        // 将得到的字节数组变成字符串返回
        return byteArrayToHexString(bytes);
    }

    /**
     * DES加密
     * @param EncryptionData 待加密数据
     * @param Key 密钥
     * @return 加密后数据
     */
    private static byte[] Encryption(byte[] EncryptionData, String Key) {
        byte[] bytes = null;
        try {
            Key k = byteToKey(decryptBase64(Key));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, k);
            bytes = cipher.doFinal(EncryptionData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * BASE64解密
     * @param Key 待解密数据
     * @return 解密后数据
     * @throws Exception
     */
    public static byte[] decryptBase64(String Key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(Key);
    }

    /**
     * BASE64加密
     * @param Key 待加密数据
     * @return 加密后数据
     * @throws Exception
     */
    public static String encryptBase64(byte[] Key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(Key);
    }

    /**
     * 字节转十六进制
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    /**
     * 字节数组转十六进制
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }


    /**
     * 十六进制转字节数组
     * @param hexstr 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 字符转整型
     * @param c 字符
     * @return 整型
     */
    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        String Key = DES.init();
        String encWord = Encryption("堆栈网络", Key);
        System.out.println("密钥:" + Key);
        System.out.println("加密后：" + encWord);
        System.out.println("解密后：" + Decrypt(encWord, Key));
    }

}
