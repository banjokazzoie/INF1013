  package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;

public class TimeoutCheckerObservable extends Observable implements Runnable {
	HashMap<String, Player> playerHashMap;
	
	public TimeoutCheckerObservable(HashMap<String, Player> playerHashMap) {
		this.playerHashMap = playerHashMap;
	}
	
	@Override
	public void run() {
		while(true){
			if(playerHashMap.size()!=0){
				for (String playerNickname : playerHashMap.keySet()) {
				
					Player player = playerHashMap.get(playerNickname);
	            
					if(player.getTimestamp()!=0){
						if(Timer.testTimeout(player.getTimestamp())){
							try {
								ClientThreadObservable.sendTimeOutMessage(player);
								player.getsocket().close();
								System.out.println("Timeout " + player.getNickname());
								Thread.sleep(1000);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	            		
						}
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
