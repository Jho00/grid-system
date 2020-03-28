package com.sstu.carservice.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    protected Socket clientSocket;
    protected PrintWriter out;
    protected BufferedReader in;

    protected void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    protected String sendMessage(String msg) throws IOException, InterruptedException {
        return this.sendMessage(msg, true);
    }

    protected String sendMessage(String msg, boolean withLogs) throws IOException {
        if (withLogs) {
            out.println(msg);
        }
        clientSocket.shutdownOutput();
        return in.readLine();
    }

    protected void stopConnection() throws IOException {
        if (!clientSocket.isInputShutdown()) {
            clientSocket.shutdownInput();
        }
        if (!clientSocket.isOutputShutdown()) {
            clientSocket.shutdownOutput();
        }
        clientSocket.close();
    }
}