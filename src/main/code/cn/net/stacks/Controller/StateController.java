package cn.net.stacks.Controller;

import cn.net.stacks.Model.Message;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/State")
public class StateController {

    private Gson gson = new Gson();

    @RequestMapping(value = "/Hello", method = RequestMethod.GET)
    public @ResponseBody Message Hrello() {

        Message Msg = new Message();

        Msg.SuccessMessage(200,"服务正在运行");

        return Msg;
    }

}
