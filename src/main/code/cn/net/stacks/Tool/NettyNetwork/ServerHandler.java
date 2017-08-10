package cn.net.stacks.Tool.NettyNetwork;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class ServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext HandlerContext, Object Message) throws Exception {
        System.err.println(new Date());
        String body = DataConversion.toString(Message);
        System.out.println(body);
        // 创建返回信息
        ByteBuf resp = Unpooled.copiedBuffer("Hello Client".getBytes());
        // 返回
        HandlerContext.write(resp);
    }
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
