import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);

            System.out.println("Server işe başlady!");

            Socket socket = server.accept();

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);

            String message = input.readLine();

            System.out.println("Client habary: " + message);

            output.println("Habar kabul edildi!");

            socket.close();
            server.close();

        } catch (Exception e) {
            System.out.println("Ýalňyşlyk: " + e.getMessage());
        }
    }
}
