package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.*;

public class ClientConnectionManager extends Observable implements Runnable{
	
	ServerSocket serverSocket;
	
	public ClientConnectionManager(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	
	
	@Override
	public void run() {
		while(true){
		try {
			Socket clientSocket = serverSocket.accept();			
			Player i = new Player(clientSocket);
			setChanged();
			notifyObservers(i);
			System.out.println("New player connected:" + clientSocket.getInetAddress().getHostAddress());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		}
		}
	}

}
