package gameUserInterface;

import java.awt.AWTException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;

import com.sun.glass.events.KeyEvent;

import board.Board;
import client.*;
 

public class OpponentListActionListener extends JFrame implements Observer {
	
	ArrayList<String> nicknameList = new ArrayList<>();
	JList <String> nicknameJlist;
	DefaultListModel<String> listModel;
	Board board;
	
    public OpponentListActionListener() {    	
        listModel = new DefaultListModel<>();              
        nicknameJlist = new JList<>(listModel);
        nicknameJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nicknameJlist.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Facade.sendMessageFacade("select:"+nicknameJlist.getSelectedValue());
			}
		});
        add(new JScrollPane(nicknameJlist));        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Opponent List");
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);        
        nicknameJlist.setVisible(true);
    }    

	@Override
	public void update(Observable arg0, Object arg1) {
		nicknameList = InputThread.getStringArrayList();
		if(((String) arg1).compareTo("l")==0){
			listModel.clear();
		for(int i=0; i<nicknameList.size();i++){
			if(nicknameList.get(i).compareTo(Facade.getMainActionListener().nicknameString)!=0)
			listModel.addElement(nicknameList.get(i));
		}
		}
		else if(((String) arg1).compareTo("ab")==0||((String) arg1).compareTo("ap")==0){
			System.out.println("Jogo iniciado");
			board = Facade.getChess(arg1.toString().charAt(1));
			Facade.getChess('x').addObserver(Facade.getFacade());
			this.setVisible(false);
			
		}
	}
}
