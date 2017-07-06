package client;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import gameUserInterface.*;

public class ClientMain {
	
	public static void main(String[] args) {
		Facade facade;
		UserInterfaceManager userInterfaceManager;		
		facade = Facade.getFacade();
		userInterfaceManager = UserInterfaceManager.getGG();
			
		Facade.newMainActionListener();
	}	
	

}
