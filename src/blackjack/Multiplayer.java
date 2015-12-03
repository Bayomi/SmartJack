package blackjack;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.*;
import javax.swing.*;

public class Multiplayer implements Runnable {
    
	private Server server;
	private Game myGame;
	Player opponent;
	Player me;
	private MultiView multiView;
	private boolean updated = false;
	
	private Scanner scanner = new Scanner(System.in);
	private JFrame frame;
	private final int WIDTH = 506;
	private final int HEIGHT = 527;
	private Thread thread;
	private Thread threadServer;
	private Thread threadView;

	private boolean yourTurn = false;
	private boolean oppTurn = false;
	private boolean accepted = false;
	private boolean unableToCommunicateWithOpponent = false;

	private int errors = 0;

	private String waitingString = "Waiting for another player";
	private String unableToCommunicateWithOpponentString = "Unable to communicate with opponent.";
	private String wonString = "You won!";
	private String enemyWonString = "Opponent won!";
	private String tieString = "Game ended in a tie.";

	public Multiplayer() {
		System.out.println("Please input the IP: ");
		String ip = scanner.nextLine();
		System.out.println("Please input the port: ");
		int port = scanner.nextInt();
		while (port < 1 || port > 65535) {
			System.out.println("The port you entered was invalid, please input another port: ");
			port = scanner.nextInt();
		}
        
		ip = "192.168.1.109";
		port = 2222;
		
		opponent = new Player(100);
		me = new Player(100);
		myGame = new Game(me, opponent);
		
		server = new Server(ip, port);
		boolean connected;
		
		connected = server.connect();
		if(!connected){
			server.initializeServer();
			this.yourTurn = true;
		} else {
			this.accepted = true;
			this.oppTurn = true;
		}
		
		multiView = new MultiView();
		multiView.setVisible(true);
		multiView.getHitButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hitEvent();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		multiView.getStopButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stopEvent();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		thread = new Thread(this, "BlackJack");
		thread.start();
		
		if(!yourTurn) {
			try {
				this.tick();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void run() {
		while (true) {
			
			if (!accepted) {
				try {
					server.listenForServerRequest();
					this.accepted = true;
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(!yourTurn) {
			/*	try {
					tick();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}*/
			}
			
			if(this.me.status() && this.opponent.status()) {
				this.myGame.checkStatus();
			}
		}
	}

	private void tick() throws ClassNotFoundException {
		if (errors >= 10) {
			unableToCommunicateWithOpponent = true;
		}
		
		if (!unableToCommunicateWithOpponent) {
			try {
				System.out.println("Pronto para receber");
				Object update = server.getDis().readObject();
				myGame.update((Game) update);
				myGame.checkStatus();
				this.multiView.updateView(myGame);
				this.yourTurn = isMyTurn("received");
				System.out.println("Recebido");
				if(!this.yourTurn) {
					 this.tick();
				}
			} catch (IOException e) {
				e.printStackTrace();
				errors++;
			}
		}
	}
	
	void hitEvent() throws ClassNotFoundException {
		if (accepted) {
			if (yourTurn && !unableToCommunicateWithOpponent) {
				this.myGame.advance(me, "Hit");
				updateView();
				this.multiView.repaint();
				try {
					System.out.println("Pronto para enviar");
					this.server.getDos().writeObject(this.myGame);
					this.server.getDos().reset();
					myGame.checkStatus();
					this.yourTurn = isMyTurn("sent");
				 } catch (IOException e1) {
					errors++;
					e1.printStackTrace();
				 }
				 System.out.println("Enviado.");
				 if(!this.yourTurn){
					 this.tick();
				 }
			}
		}
	}
	
	void stopEvent() throws ClassNotFoundException {
		if (accepted) {
			if (yourTurn && !unableToCommunicateWithOpponent) {
				this.myGame.advance(me, "Stop");
				this.multiView.updateView(myGame);
				 try {
					System.out.println("Pronto para enviar");
					this.server.getDos().writeObject(this.myGame);
					this.server.getDos().reset();
					myGame.checkStatus();
					this.yourTurn = isMyTurn("sent");
				 } catch (IOException e1) {
					errors++;
					e1.printStackTrace();
				 }
				 System.out.println("Enviado.");
				 if(!this.yourTurn){
					 //this.tick(); 
				 }
			}
		}
	}
	
	boolean isMyTurn(String situation) {
		if(situation == "received") {
			if(this.me.status() && this.opponent.status()){
				return true;
			} else if(!this.me.status() && this.opponent.status()){
				return true;
			} else if (this.me.status() && !this.opponent.status()){
				return false;
			} else {
				return true;
			}
		} else {
			if(this.me.status() && this.opponent.status()){
				return false;
			} else if(!this.me.status() && this.opponent.status()){
				return true;
			} else if (this.me.status() && !this.opponent.status()){
				return false;
			} else {
				return false;
			}
		}
	}
	
	synchronized void updateView() {
		System.out.println("oi");
		this.multiView.updateView(this.myGame);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Multiplayer multi = new Multiplayer();
	}


}
