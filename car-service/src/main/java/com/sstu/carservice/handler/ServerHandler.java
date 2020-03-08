package com.sstu.carservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sstu.carservice.task.TaskModel;
import com.sstu.carservice.task.distribution.TaskDistributor;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    ObjectMapper objectMapper = new ObjectMapper();
    TaskDistributor taskDistributor = new TaskDistributor();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;

        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + received);

        TaskModel taskModel = objectMapper.readValue(received, TaskModel.class);
        log.info("Task received - {}", taskModel);

        taskDistributor.accept(taskModel);


        ctx.write(Unpooled.copiedBuffer("Hello " + received, CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
