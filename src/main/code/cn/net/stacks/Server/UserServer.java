package cn.net.stacks.Server;

import cn.net.stacks.Model.User;

/**
 * Describe: 用户服务接口
 *
 * @author Kaiser.zsk
 * @version 1.0
 */
public interface UserServer {

    /**
     * Describe: 用户登录接口
     *
     * @param Identification 用户标识(用户编号/名称/手机/邮箱)
     * @param PassWord 用户账户密码
     * @return 登录响应 - 包含用户信息
     */
    ServerResponse<User> Login(String Identification, String PassWord);

    /**
     * Describe: 用户注册接口
     *
     * @param User 用户信息模型
     * @return 注册响应
     */
    ServerResponse Register(User User);

}
