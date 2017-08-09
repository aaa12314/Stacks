package cn.net.stacks.Server.impl;

import cn.net.stacks.DataBase.UserMapper;
import cn.net.stacks.Model.User;
import cn.net.stacks.Server.ServerResponse;
import cn.net.stacks.Server.UserServer;
import cn.net.stacks.Tool.DataVerification.UserMessage;
import cn.net.stacks.Tool.SecurityEngine.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Describe: 用户服务接口实现
 *
 * @author Kaiser.zsk
 * @version 1.0
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

    // 用户注册
    @Override
    public ServerResponse Register(String Name, String PassWord, String Phone, String Mail) {
        // 判断是否有必填参数为空
        if (!Name.equals("") && !PassWord.equals("") && !Phone.equals("") && !Mail.equals("")){
            // 构造用户信息模型
            User user = new User();
            // 装载用户编号
            user.setUid(getRandom());
            // 装载用户名称
            user.setUname(Name);
            // 装载用户昵称
            user.setUnickname(Name);
            // 装载MD5密码
            user.setUpassword(MD5.Encryption(PassWord));
            // 装载手机号码
            user.setUphone(Phone);
            // 装载电子邮箱
            user.setUmail(Mail);
            // 装载生成注册时间
            user.setUtime(String.valueOf(System.currentTimeMillis()));
            // 装载用户性别
            user.setUgender(0);
            // 装载用户状态
            user.setUstate(0);
            // 执行新增操作
            if (userMapper.insertUser(user)>=1)
                // 新增影响行数大于等于1
                return ServerResponse.createSuccessResponse("注册成功");
            else
                // 新增影响行数小于1
                return ServerResponse.createErrorResponse("注册失败,请检查输入的信息");
        }else
            // 信息未填写完整
            return ServerResponse.createErrorResponse("注册失败,必填信息不能为空");
    }

    private static int getRandom(){
        Random random = new Random();
        return random.nextInt(9999) % (9999 - 1000 + 1) + 1000;

    }

}