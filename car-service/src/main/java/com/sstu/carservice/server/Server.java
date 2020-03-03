package com.sstu.carservice.server;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server {
    public static final int PORT = 8080;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private static BufferedReader in;
    private static PrintWriter out;

    private static volatile Server server;

    private Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public static Server getInstance() throws IOException {
        log.info("Server obtained via getInstance method.");
        return server == null ? new Server() : server;
    }

    public void startServer() {
        try {
            while (true) {
                clientSocket = serverSocket.accept();

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.write("PRIVET");


                InputStream input = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(input);

                OutputStream output = clientSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(output);

                int inputLength = dis.readInt();
                byte[] data = new byte[inputLength];
                dis.readFully(data);
                dos.writeInt(inputLength);
                dos.write(data, 0, inputLength);

                String greeting = in.readLine();
                out.println("test");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
