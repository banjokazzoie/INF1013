package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import board.Board;
import gameUserInterface.Facade;
import gameUserInterface.NicknameActionListener;
import gameUserInterface.OpponentListActionListener;

public class UserInterfaceManager extends Observable implements Observer {
	static Client client;
	static UserInterfaceManager userInterfaceManager;
	static String nick;
	
	public UserInterfaceManager() {
		
	}
	
	public static UserInterfaceManager getUserInterfaceManager(){
		if(userInterfaceManager==null){
			userInterfaceManager = new UserInterfaceManager();
		}
		return userInterfaceManager;
	}
	
	public static Client getCliente(){
		if(client==null){
			try {
				client = new Client(nick);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;

	}
	
	public static void setNick(String n){
		nick = n;
	}
	
	public static void sendMessageOutputThread(String msg){
		OutputThread.sendMessage(msg);
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
}