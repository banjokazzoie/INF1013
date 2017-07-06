package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.*;

public  class ClientThreadObservable extends Observable implements Runnable {
	
	Player player;
	HashMap<String, Player> playerHashMap;
	Scanner scanner;
	String nickname;
	ArrayList<String> nicknameList = new ArrayList<>();
	
	public ClientThreadObservable(Player player, HashMap<String, Player> playerHashMap){
		this.player = player;
		this.playerHashMap = playerHashMap;
		try {
			scanner = new Scanner(this.player.getsocket().getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.nickname = scanner.nextLine();
		this.player.setNickname(this.nickname);
		this.playerHashMap.put(this.nickname, this.player);
		Timer.start(this.player);
		
		
	}
	
	public void addNicknameToList(){
		nicknameList.clear();
		for (String key : playerHashMap.keySet()) {
			
            Player value = playerHashMap.get(key);
            	if(value.getGameStatus()==false){
            		System.out.println(value.getNickname());
            		nicknameList.add(value.getNickname());
            	}
		}
	}
	
	public boolean checkNickname(String nickname){
		if(player.getNickname().compareTo(nickname)!=0){
			if(playerHashMap.get(nickname)!=null && playerHashMap.get(nickname).getGameStatus()==false){
				return true;
			}
		}
		return false;
	}
	
	public static void sendTimeOutMessage(Player myPlayer){
		try {
			if(myPlayer.getsocket().isClosed()==false){
			PrintStream s = new PrintStream(myPlayer.getsocket().getOutputStream());
			s.println("END");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg){
		for (String key : playerHashMap.keySet()) {
			
            Player value = playerHashMap.get(key);
            try {
            	
				PrintStream s = new PrintStream(value.getsocket().getOutputStream());
				if(msg!=null && player.getGameStatus()==false){
					if(value.equals(playerHashMap.get(nickname))==false){
						s.println(msg);
					}
				}
				else if(msg==null && value.getGameStatus()==false){
				addNicknameToList();
				s.println("STARTLIST");
				for(int i=0; i<nicknameList.size();i++){
					s.println(nicknameList.get(i));
				}
				s.println("ENDLIST");
				}
				else{
					if(msg==null){						
					}
					
					else if(msg.compareTo("accepted")==0){
						if(value.equals(player.getOpponent())==true){
							s.println(msg + "p");
						}
						else if(value.equals(playerHashMap.get(nickname))==true){
							s.println(msg + "b");
						}
					}
					else{
					if(value.equals(player.getOpponent())==true){
						s.println(msg);
					}
					}
				}
				
            	
			} catch (IOException e) {
				e.printStackTrace();
			}
        
		}
	}
	
	
	@Override
	public void run() {
		sendMessage(null);
		String playerNickname;
		while(scanner.hasNext() && player.getsocket().isConnected()){
			String line = scanner.nextLine();
			if(line.compareTo("###")!=0){
				if(line.length()>=7){
				if(line.substring(0, 7).compareTo("select:")==0){
					playerNickname = line.substring(7);
					if(checkNickname(playerNickname)==true){
					playerHashMap.get(this.nickname).setGameStatus(true);
					playerHashMap.get(this.nickname).setOpponent(playerHashMap.get(playerNickname));
					playerHashMap.get(playerNickname).setGameStatus(true);
					playerHashMap.get(playerNickname).setOpponent(playerHashMap.get(this.nickname));
					Timer.stop(player);
					Timer.stop(playerHashMap.get(playerNickname));
					Timer.start(player);
					System.out.println("Jogo iniciado");
					sendMessage("accepted");
					sendMessage(null);
					}
					
				}
				else if(line.compareTo("tabuleiro")==0){
					sendMessage(line);
					sendMessage(scanner.nextLine());
					Timer.stop(player);
					
				}
				else{
					String broadcast = nickname +"  -  "+ line;
					System.out.println(line);
					sendMessage(broadcast);
				}
				}
			}
				
			else{
				playerHashMap.remove(nickname);
				break;
			}
		}
		playerHashMap.remove(nickname);
	}

}
