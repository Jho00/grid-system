package com.sstu.carservice;

import com.sstu.carservice.server.Server;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Server server = Server.getInstance();
        server.startServer();
    }
}