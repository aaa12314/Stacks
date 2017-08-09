package cn.net.stacks.Controller;

import cn.net.stacks.Server.ServerResponse;
import cn.net.stacks.Server.UserServer;
import cn.net.stacks.Server.impl.UserServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/")
public class UserController {

    private final UserServer userServer;

    @Autowired
    public UserController(UserServer userServer) {
        this.userServer = userServer;
    }

    @CrossOrigin("http://demo.org/")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody ServerResponse Login(String Identification, String PassWord) {
        if (Identification.equals("")){
            return ServerResponse.createErrorResponse("登录失败,账号不能为空");
        } else if (PassWord.equals("")){
            return ServerResponse.createErrorResponse("登录失败,密码不能为空");
        }
        return userServer.Login(Identification,PassWord);
    }

}
