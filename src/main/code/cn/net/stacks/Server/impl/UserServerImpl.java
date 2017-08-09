package cn.net.stacks.Server.impl;

import cn.net.stacks.DataBase.UserMapper;
import cn.net.stacks.Model.User;
import cn.net.stacks.Server.ServerResponse;
import cn.net.stacks.Server.UserServer;
import cn.net.stacks.Tool.DataVerification.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * 用户服务
 */
@Service("userServer")
public class UserServerImpl implements UserServer{

    // 用户数据操作对象
    private final UserMapper userMapper;

    @Autowired
    public UserServerImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 日志对象
    private static Logger log = Logger.getLogger("UserServerImpl");

    // 用户登录
    @Override
    public ServerResponse<User> Login(String Identification, String PassWord) {
        User user;
        if (UserMessage.isPhone(Identification))
            // 使用手机号码登录
            user = userMapper.selectUserMessageForPhone(Identification);
        else{
            if (UserMessage.isMail(Identification))
                // 使用电子邮箱登录
                user = userMapper.selectUserMessageForMail(Identification);
            else{
                // 使用用户名登录
                user = userMapper.selectUserMessageForName(Identification);
            }
        }
        // 输出登录请求日志
        log.info("用户"+user.getUname()+"请求登录");
        if (user!=null){
            if (user.getUstate()==1) {
                // 正常账户 验证密码
                if (user.getUpassword().equals(PassWord)) {
                    user.setUpassword(null);
                    return ServerResponse.createSuccessResponse("登录成功", user);
                }else
                    return ServerResponse.createErrorResponse("登录失败,密码错误");
            }else if(user.getUstate()==2)
                // 封禁账户 登录失败
                return ServerResponse.createErrorResponse("登录失败,账户被封禁");
            else
                // 未激活账户 登录失败
                return ServerResponse.createErrorResponse("登录失败,账户未激活");
        }else{
            // 账户不存在 登录失败
            return ServerResponse.createErrorResponse("登录失败,账户不存在");
        }
    }

    // 注册
    @Override
    public ServerResponse Register(User user) {
        int State = userMapper.insertUser(user);
        return null;
    }
}
