package cn.net.stacks.Tool.DataVerification;

import java.util.regex.Pattern;

public class UserMessage {

    // 手机校验正则表达式
    public static final String RE_PHONE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    // 邮箱校验正则表达式
    public static final String RE_MAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    // 校验数据是否为手机号码
    public static boolean isPhone(String Message){
        return Pattern.matches(RE_PHONE, Message);
    }

    // 校验数据是否为电子邮箱
    public static boolean isMail(String Message){
        return Pattern.matches(RE_MAIL, Message);
    }

}
