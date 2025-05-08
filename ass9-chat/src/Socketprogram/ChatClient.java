package Socketprogram;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5001);
        System.out.println("Connected to server.");

        // Input/output streams
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Thread to read from server
        new Thread(() -> {
            String message;
            try {
                while ((message = input.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) break;
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Main thread to send messages
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String messageToServer;
        while (true) {
            messageToServer = console.readLine();
            output.println(messageToServer);
            if (messageToServer.equalsIgnoreCase("exit")) break;
        }

        socket.close();
    }
}

