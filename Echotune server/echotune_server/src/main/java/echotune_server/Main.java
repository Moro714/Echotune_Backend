package echotune_server;

import java.net.*;
import java.io.*;

import echotune_server.db.UserQueryHandler;
import echotune_server.entities.User;

public class Main {
	private Socket socket;
	private ServerSocket serverSocket;
	private DataInputStream inputStream;

	public Main(int port) {
		try {
			serverSocket = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for client");
 
            socket = serverSocket.accept();
            System.out.println("Client accepted" + socket);
 
            // takes input from the client socket
            inputStream = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
 
            String line = "";
 
            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = inputStream.readUTF();
                    System.out.println(line);
 
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            inputStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		Main server = new Main(8080);
	}
}
