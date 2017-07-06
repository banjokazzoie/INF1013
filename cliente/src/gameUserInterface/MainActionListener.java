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

public class MainActionListener implements ActionListener {
	JFrame j = new JFrame("Socket Chess");
	JPanel p = new JPanel();
	JTextField apelido = new JTextField();
	JLabel texto = new JLabel("Nickname:");
	JButton start = new JButton("Start");
	String nick;
	Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
    int width = 360, height = 100;
	int x = (d.width/2)-(width/2), y = (d.height/2)-(height/2);
	
	Client c;
	
	public MainActionListener(){
		
		j.add(p);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setBounds(x, y, width, height);
		p.setLayout(null);
		p.add(texto);
		texto.setBounds(10, 20, 150, 20);
		p.add(apelido);
		apelido.setBounds(120, 20, 100, 20);
		apelido.grabFocus();
		p.add(start);
		start.setBounds(230, 20, 100, 20);
		start.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start){
			nick = apelido.getText();
			if(nick.length()>=8 && nick.length()<=12){
				j.setVisible(false);
				
				UserInterfaceManager.setNick(nick);
				UserInterfaceManager.getCliente();
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Seu apelido deve ter entre 8 e 12 caracteres");
			}
		}
		
	}

	
}
