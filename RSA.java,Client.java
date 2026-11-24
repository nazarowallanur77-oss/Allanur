import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

            System.out.print("Habaryňy ýaz: ");
            String message = scanner.nextLine();

            output.println(message);

            System.out.println("Server jogaby: " + input.readLine());

            socket.close();
        } catch (Exception e) {
            System.out.println("Ýalňyşlyk: " + e.getMessage());
        }
    }
}
