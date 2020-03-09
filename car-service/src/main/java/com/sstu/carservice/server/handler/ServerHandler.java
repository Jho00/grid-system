package com.sstu.carservice.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import com.sstu.carservice.appconfig.ApplicationConfig;
import com.sstu.carservice.appconfig.ConfigModel;
import com.sstu.carservice.car.management.CarManager;
import com.sstu.carservice.model.ResponseModel;
import com.sstu.carservice.request.ConnectRequestHandler;
import com.sstu.carservice.request.DisconnectRequestHandler;
import com.sstu.carservice.request.ExecuteTaskRequestHandler;
import com.sstu.carservice.task.ConnectionTask;
import com.sstu.carservice.task.ExecuteTask;
import com.sstu.carservice.task.distribution.TaskDistributor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import lombok.extern.slf4j.Slf4j;

import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private final ConfigModel configModel = ApplicationConfig.getConfig();

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TaskDistributor taskDistributor = new TaskDistributor();
    private final CarManager carManager = new CarManager();

    private final ConnectRequestHandler connectRequestHandler = new ConnectRequestHandler(carManager);
    private final DisconnectRequestHandler disconnectRequestHandler = new DisconnectRequestHandler(carManager);
    private final ExecuteTaskRequestHandler executeTaskRequestHandler = new ExecuteTaskRequestHandler(taskDistributor);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;

        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + received);

        Object task;
        try {
            task = objectMapper.readValue(received, ConnectionTask.class);
        } catch (UnrecognizedPropertyException exception) {
            task = objectMapper.readValue(received, ExecuteTask.class);
        }

        log.info("Task received - {}", task);

        if (task instanceof ConnectionTask) {
            ConnectionTask connectionTask = (ConnectionTask) task;
            switch (connectionTask.getAction()) {
                case "connect":
                    connectRequestHandler.handle(connectionTask);
                    break;
                case "disconnect":
                    disconnectRequestHandler.handle(connectionTask);
                    break;
                default:
                    break;
            }
        } else if (task instanceof ExecuteTask) {
            ExecuteTask executeTask = (ExecuteTask) task;
            ResponseModel response = executeTaskRequestHandler.handle(executeTask);

            String responseJson = objectMapper.writeValueAsString(response);

            InetSocketAddress socketAddress = new InetSocketAddress(configModel.getBrokerHost(),
                    Integer.parseInt(configModel.getBrokerPort()));
            Socket socket = new Socket();
            socket.connect(socketAddress, 1);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(responseJson);
            dataOutputStream.flush();
        }
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
