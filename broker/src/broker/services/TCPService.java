package broker.services;

import broker.common.TCPClient;
import broker.entity.netinteraction.Action;

import java.io.IOException;

public class TCPService extends TCPClient {
    private String host;
    private int port;

    public TCPService(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String sendCommand(Action action) throws IOException, InterruptedException {
        this.startConnection(host, port);
        String result = this.sendMessage(action.toString());
        this.stopConnection();

        return result;
    }
}
