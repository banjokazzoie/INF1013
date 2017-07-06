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
	static Client c;
	static UserInterfaceManager g;
	static String nick;
	public UserInterfaceManager() {
		
	}
	
	public static UserInterfaceManager getGG(){
		if(g==null){
			g = new UserInterfaceManager();
		}
		return g;
	}
	
	public static Client getCliente(){
		if(c==null){
			try {
				c = new Client(nick);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return c;

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