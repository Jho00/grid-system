package com.sstu.carservice.server.handler;

import java.io.IOException;

public class TCPService extends TCPClient {
    private String host;
    private int port;

    public TCPService(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String sendCommand(String action) throws IOException, InterruptedException {
        this.startConnection(host, port);
        String result = this.sendMessage(action);
        this.stopConnection();

        return result;
    }
}
