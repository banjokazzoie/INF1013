package server;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Player {
		
	String nickname;
	Player opponent;
	Thread playerThread;
	Socket playerSocket;	
	boolean gameStatus = false;
	long timestamp = 0;
	
	
	public Player(Socket s) {
		playerSocket = s;
	}
	
	public Socket getsocket(){
		return playerSocket;
	}
	public String getNickname(){
		return nickname;
	}
	public Thread getPlayerThread(){
		return playerThread;
	}
	public Player getOpponent(){
		return opponent;
	}
	public long getTimestamp(){
		return timestamp;
	}
	public boolean getGameStatus(){
		return gameStatus;
	}
	
	public void setNickname(String a){
		nickname = a;
	}
	public void setPlayerThread(Thread a){
		playerThread = a;
	}
	public void setOpponent(Player a){
		opponent = a;
	}
	public void setTimestamp(long l){
		timestamp = l;
	}
	public void setGameStatus(Boolean b){
		gameStatus = b;
	}
	
	
}
