package gameUserInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import client.Client;
import client.UserInterfaceManager;
import client.ClientMain;

public class NicknameActionListener implements ActionListener {
	
	JFrame jFrame = new JFrame("Socket Chess");
	JPanel jPanel = new JPanel();
	JTextField nicknameJtextField = new JTextField();
	JLabel nicknameJLabel = new JLabel("Nickname:");
	JButton startJButton = new JButton("Start");
	String nicknameString;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolkit.getScreenSize();
    int width = 360, height = 100;
	int x = (dimension.width/2)-(width/2), y = (dimension.height/2)-(height/2);	
	
	Client client;
	
	public NicknameActionListener(){
		
		jFrame.add(jPanel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setBounds(x, y, width, height);
		jPanel.setLayout(null);
		jPanel.add(nicknameJLabel);
		nicknameJLabel.setBounds(10, 20, 150, 20);
		jPanel.add(nicknameJtextField);
		nicknameJtextField.setBounds(120, 20, 100, 20);
		nicknameJtextField.grabFocus();
		jPanel.add(startJButton);
		startJButton.setBounds(230, 20, 100, 20);
		startJButton.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startJButton){
			nicknameString = nicknameJtextField.getText();
			if(nicknameString.length()>=8 && nicknameString.length()<=12){
				jFrame.setVisible(false);
				
				UserInterfaceManager.setNick(nicknameString);
				UserInterfaceManager.getCliente();
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Your nickname must have between 8 and 12 characters.");
			}
		}
		
	}

	
}
