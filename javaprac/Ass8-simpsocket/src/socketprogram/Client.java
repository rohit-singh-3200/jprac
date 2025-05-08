package socketprogram;

import java.io.*;
import java.net.*;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            // Send message to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from client!");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
