package gameUserInterface;

import java.util.Observable;
import java.util.Observer;

import board.Board;
import client.UserInterfaceManager;
import client.OutputThread;

public class Facade extends Observable implements Observer{
	static OpponentListActionListener opponentActionListener = null;
	static NicknameActionListener mainActionListener = null;
	static Board board;
	static Facade facade;
	
	public Facade() {
	}
	
	public static void newMainActionListener(){
		mainActionListener = new NicknameActionListener();
	}
	public static NicknameActionListener getMainActionListener (){
		return mainActionListener;
	}
	public static OpponentListActionListener getOpponentActionListener(){
		if(opponentActionListener==null){
			opponentActionListener = new OpponentListActionListener();
		}
		return opponentActionListener;
	}
	public static Facade getFacade(){
		if(facade==null){
			facade = new Facade();
		}
		return facade;
	}
	
	public static Board getChess(char c){
		if(board==null){
			board = new Board(c);
			board.getFrame().setVisible(true);
		}
		return board;
		
	}
	
	public static void sendMessageFacade(String msg){
		facade.sendMessageUserInterface(msg);
	}
	
	public void sendMessageUserInterface(String msg){
		UserInterfaceManager.sendMessageOutputThread(msg);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		OutputThread.sendGameFile();
		
	}
	
	
}
