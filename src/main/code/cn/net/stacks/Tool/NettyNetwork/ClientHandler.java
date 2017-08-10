package cn.net.stacks.Tool.NettyNetwork;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class ClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf firstMessage;

    public ClientHandler() {
        byte[] req = "Hello Server".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }
    /*连接成功后，自动发送消息*/
    public void channelActive(ChannelHandlerContext HandlerContext) {
        HandlerContext.writeAndFlush(firstMessage);
    }
    /*有消息返回时，自动调用该函数读取*/
    public void channelRead(ChannelHandlerContext HandlerContext, Object Message) throws Exception {
        System.err.println(new Date());
        String body = DataConversion.toString(Message);
        System.out.println(body);
    }
    /*发生异常时，自动调用*/
    public void exceptionCaught(ChannelHandlerContext HandlerContext, Throwable cause) {
        HandlerContext.close();
    }
}
