package client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import gameUserInterface.Facade;

public class InputThread extends Observable implements Runnable {

	Socket clientSocket;	
	String message;	
	boolean flag=false;
	String gameString;	
	static ArrayList<String> stringArrayList = new ArrayList<String>();
	
	
	public InputThread(Socket c) {
		clientSocket = c;
	}
	
	private void readMessage(String msg){
		message = msg;
	}
	
	public static ArrayList<String> getStringArrayList(){
		return stringArrayList;		
	}
	
	public void loadFile(){
		File savedGame = new File("Saved_Game.txt");
		if(savedGame.exists()){
			savedGame.delete();
		}
		try {
			savedGame.createNewFile();
			FileWriter writer = new FileWriter(savedGame.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(gameString);
			bufferedWriter.close();
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Scanner server = null;
			try {
				server = new Scanner(clientSocket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(server.hasNextLine()){
				readMessage(server.nextLine());
				if(message.compareTo("STARTLIST")==0){
					stringArrayList.clear();
					flag = true;
				}
				else if(message.compareTo("ENDLIST")==0){
					flag = false;
					setChanged();
					notifyObservers("l");
				}
				else if(message.compareTo("acceptedb")==0){
					setChanged();
					notifyObservers("ab");
				}
				else if(message.compareTo("acceptedp")==0){
					setChanged();
					notifyObservers("ap");
				}
				else if(message.compareTo("tabuleiro")==0){
					System.out.println("entrei");
					gameString = server.nextLine();
					loadFile();
					Facade.getChess('x').carregaTab();
				}
				else if(message.compareTo("END")==0){
					Client.exit();
				}
				else{
					if(flag==true){
						stringArrayList.add(message);
					}
					else{
						System.out.println(message);
					}
				}						
		}
				
		server.close();
	
	}
}
