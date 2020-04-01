package broker.common;

import java.io.*;
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
        if(withLogs) {
            out.println(msg + "\n");
        }
        clientSocket.shutdownOutput();
        return in.readLine();
    }

    protected void stopConnection() throws IOException {
        if (!clientSocket.isInputShutdown()) {
            clientSocket.shutdownInput();
        }
        if(!clientSocket.isOutputShutdown()) {
            clientSocket.shutdownOutput();
        }
        clientSocket.close();
    }

    protected byte[] sendBytes(byte[] myByteArray) throws IOException {
        return sendBytes(myByteArray, 0, myByteArray.length);
    }

    protected byte[] sendBytes(byte[] myByteArray, int start, int len) throws IOException {
        if (len < 0)
            throw new IllegalArgumentException("Negative length not allowed");
        if (start < 0 || start >= myByteArray.length)
            throw new IndexOutOfBoundsException("Out of bounds: " + start);
        // Other checks if needed.

        // May be better to save the streams in the support class;
        // just like the socket variable.
        OutputStream out = clientSocket.getOutputStream();
        InputStream in = clientSocket.getInputStream();
        DataOutputStream dos = new DataOutputStream(out);
        DataInputStream dis = new DataInputStream(in);


        dos.writeInt(len);
        dos.write(myByteArray, start, len);

        int inputLength = dis.readInt();
        byte[] data = new byte[inputLength];
        dis.readFully(data);

        return data;
    }
}
