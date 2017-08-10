package cn.net.stacks.DataBase;

import cn.net.stacks.Model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    // 使用手机号码获取用户信息
    User selectUserMessageForPhone(@Param("Phone") String Phone);

    // 获取手机号码存在个数
    int selectUserForPhone(@Param("Phone") String Phone);

    // 使用电子邮箱获取用户信息
    User selectUserMessageForMail(@Param("Mail") String Mail);

    // 获取电子邮箱存在个数
    int selectUserForMail(@Param("Mail") String Mail);

    // 使用用户名称获取用户信息
    User selectUserMessageForName(@Param("Name") String Name);

    // 获取用户名称存在个数
    int selectUserForName(@Param("Name") String Name);

    // 使用用户编号获取用户信息
    User selectUserMessageForId(@Param("Id") String Id);

    // 获取用户编号存在个数
    int selectUserForId(@Param("Id") String Id);

    // 使用用户编号删除用户信息
    int deleteUserForId(@Param("Id") String Id);

    // 使用用户信息模型新增用户
    int insertUser(User user);
}