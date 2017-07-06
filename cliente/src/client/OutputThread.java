package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class OutputThread implements Runnable, Observer{
	
	static PrintStream printStream;
	static Socket clientSocket;
	String nicknameString;	
	String msg;
	
	public OutputThread(Socket socket, String nickname) {
		clientSocket = socket;
		this.nicknameString = nickname;
		try {
		printStream = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMessage(String msg){
		try {
			PrintStream s = new PrintStream(clientSocket.getOutputStream());
			s.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendGameFile(){
		
		try {
			Scanner scanner = new Scanner(new File("Saved_Game.txt"));
			PrintStream s = new PrintStream(clientSocket.getOutputStream());
			s.println("tabuleiro");
			while(scanner.hasNextLine()){
				s.print(scanner.nextLine());
			}
			s.println();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		Scanner scannerInput = new Scanner(System.in);
			msg = nicknameString;
			while(true){
				printStream.println(msg);
				msg = scannerInput.nextLine();
				if(msg.compareTo("###")==0){
					break;
				}
		}
		scannerInput.close();
	}

	@Override
	public void update(Observable o, Object arg) {		
		
	}

}
