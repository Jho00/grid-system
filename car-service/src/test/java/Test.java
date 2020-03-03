import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8080));
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
}
