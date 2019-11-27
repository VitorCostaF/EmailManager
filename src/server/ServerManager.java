/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author vitorc
 */
public class ServerManager {
    
    private static ServerSocket serverSocket;
    private static final int PORT = 12345;
    private static Socket socket = null;
    private static Scanner input;
    private static PrintWriter output;
    private static String message;
    
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioEx) {
            System.out.println("ServerSocket couldn't open");
            System.exit(1);
        }
        handleClient();
    }

    private static void handleClient() {
        System.out.println("Server started");
        try {
            socket = serverSocket.accept();
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            
            
            message = input.nextLine();
            
            while (!message.equals("***CLOSE***")) {
                System.out.println("Message received: " + message);
                output.println("Message: " + message);
                message = input.nextLine();
            }
            
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally {
            try {
                System.out.println("Closing connection");
                socket.close();
            } catch (Exception e) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
