package Socketprogram;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5001);
        System.out.println("Server started. Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        // Input/output streams
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Thread to read from client
        new Thread(() -> {
            String message;
            try {
                while ((message = input.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) break;
                    System.out.println("Client: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Main thread to send messages
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String messageToClient;
        while (true) {
            messageToClient = console.readLine();
            output.println(messageToClient);
            if (messageToClient.equalsIgnoreCase("exit")) break;
        }

        socket.close();
        serverSocket.close();
    }
}

