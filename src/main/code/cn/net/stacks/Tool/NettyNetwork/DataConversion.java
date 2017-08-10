package cn.net.stacks.Tool.NettyNetwork;

import io.netty.buffer.ByteBuf;
import java.io.UnsupportedEncodingException;

public class DataConversion {

    public static String toString(Object Message){
        // 转换为字节码
        ByteBuf buf = (ByteBuf) Message;
        // 创建字节组
        byte[] req = new byte[buf.readableBytes()];
        // 装载数据
        buf.readBytes(req);
        // 转换为字符串
        try {
            return new String(req, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "ERROR - "+e.getMessage();
        }
    }

}
