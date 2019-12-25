/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.management;

/**
 *
 * @author vitorc
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import client.front.LoginWindow;

public class ClientManager {

	private static Socket socket = null;
	private static final int PORTA = 12345;
	private static InetAddress serverAddress;
	private static String strSeverAddress = "localhost";
	
	public static void main(String[] args) {
//		try {
//			serverAddress = InetAddress.getByName(strSeverAddress);
//		} catch (UnknownHostException uhEx) {
//			System.out.println("Host ID not found!");
//			System.exit(1);
//		}
//		accessServer();
        LoginWindow login = new LoginWindow();
        login.showWindow();
        //MainWindow clientFront = new MainWindow();
        //clientFront.showWindow();

	}
	
	private static void accessServer() {
		try {
			socket = new Socket(serverAddress, PORTA);			
			Scanner input = new Scanner(socket.getInputStream());
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			
			Scanner userInput = new Scanner(System.in);
			
			String message, response;
			
			do {
				System.out.println("Enter message: ");
				message = userInput.nextLine();
				output.println(message);
				response = input.nextLine();
                System.out.println("Response: " + response);
			} while (message.equals("***CLOSE***"));
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				System.out.println("Closing connection...");
				socket.close();
			} catch (IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}