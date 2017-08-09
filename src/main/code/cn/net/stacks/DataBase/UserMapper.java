package cn.net.stacks.DataBase;

import cn.net.stacks.Model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    // 使用手机号码获取用户信息
    User selectUserMessageForPhone(@Param("Phone") String Phone);

    // 使用电子邮箱获取用户信息
    User selectUserMessageForMail(@Param("Mail") String Mail);

    // 使用用户名称获取用户信息
    User selectUserMessageForName(@Param("Name") String Name);

    // 使用用户编号获取用户信息
    User selectUserMessageForId(@Param("Id") String Id);

    // 使用用户编号删除用户信息
    int deleteUserForId(@Param("Id") String Id);

    // 使用用户信息模型新增用户
    int insertUser(User user);
}