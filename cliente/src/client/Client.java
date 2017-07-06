package client;

import java.net.*;
import java.util.*;

import javax.swing.SwingUtilities;

import gameUserInterface.Facade;
import gameUserInterface.OpponentActionListener;

import java.io.*;

public class Client extends Observable {
	OpponentActionListener opponentActionListener;
	
	public Client(String nickname) throws IOException {
		Socket clientSocket = new Socket("127.0.0.1", 12345);

		InputThread inputThread = new InputThread(clientSocket);
		OutputThread outputThread = new OutputThread(clientSocket, nickname);

		opponentActionListener = Facade.getOpponentActionListener();
		inputThread.addObserver(opponentActionListener);
		Thread threadInputThread = new Thread(inputThread);
		Thread threadOutputThread = new Thread(outputThread);
		threadInputThread.start();
		threadOutputThread.start();

	}

	public static void exit() {

		System.exit(0);
	}

}
