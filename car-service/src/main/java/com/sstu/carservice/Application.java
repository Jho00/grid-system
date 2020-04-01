package com.sstu.carservice;

import com.sstu.carservice.appconfig.ApplicationConfig;
import com.sstu.carservice.appconfig.ConfigModel;
import com.sstu.carservice.car.management.CarManager;
import com.sstu.carservice.car.scheduling.CarScheduler;
import com.sstu.carservice.server.handler.ServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.net.InetSocketAddress;

public class Application {

    private static ConfigModel configModel = ApplicationConfig.getConfig();
    private static CarManager carManager = new CarManager();
    private static CarScheduler carScheduler = new CarScheduler();

    public static void main(String[] args) throws InterruptedException, SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(carScheduler.getJob(), carScheduler.getTrigger());
        scheduler.start();

//        carManager.initCars();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(new InetSocketAddress(configModel.getServerHost(),
                    Integer.parseInt(configModel.getServerPort())));

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast("frameDecoder", new DelimiterBasedFrameDecoder(5000, Delimiters.lineDelimiter()));
                    socketChannel.pipeline().addLast(new ServerHandler());
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}