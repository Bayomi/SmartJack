package blackjack;

import javax.swing.JOptionPane;

public class Game implements java.io.Serializable{
	Deck deck;
	Player Player1;
	Player Player2;
	int roundPoints = 25;
	private boolean ended = false;
	
	public Game(Player p1, Player p2) {
		this.deck = new Deck();
		this.Player1 = p1;
		this.Player2 = p2;
	}
	
	public void advance(Player player, String action) {
		if(action == "Hit"){
			player.hit(this.deck);
		} else if(action== "Stop") {
			player.stop();
		}
	}
	
	public void checkStatus() {
		System.out.println(this);
		if(this.Player1.getMoney()<=0 || this.Player2.getMoney()<=0) {
			System.out.println("Game ended");
			boolean finalCheck = this.Player1.getMoney() > 0;
			if(finalCheck) {
				System.out.println("Player 1 won");
			} else {
				System.out.println("Player 2 won");
			}
		}
		if(this.Player1.status() && this.Player2.status()) {
			if(this.Player1.getPoints() > 21 || this.Player2.getPoints() > 21) {
				if(this.Player1.getPoints() > 21 && this.Player2.getPoints() > 21){
					this.Player1.win(0);
					this.Player2.win(0);
				} else if(this.Player1.getPoints() > 21) {
					this.Player1.lose(roundPoints);
					this.Player2.win(roundPoints);
				} else {
					this.Player2.lose(roundPoints);
					this.Player1.win(roundPoints);
				}
			} else if(this.Player1.getPoints() > this.Player2.getPoints()) {
				this.Player1.win(roundPoints);
				this.Player2.lose(roundPoints);
			} else if(this.Player2.getPoints() > this.Player1.getPoints()) {
				this.Player2.win(roundPoints);
				this.Player1.lose(roundPoints);
			} else {
				this.Player1.win(0);
				this.Player2.win(0);
			}
			this.Player1.restart();
			this.Player2.restart();
			JOptionPane.showMessageDialog(null, "Round ended");
			System.out.println("Round ended.");
			if(this.Player1.getMoney()<=0 || this.Player2.getMoney()<=0) {
				boolean finalCheck = this.Player1.getMoney() > 0;
				if(finalCheck) {
					JOptionPane.showMessageDialog(null, "Game ended. Player 1 won.");
				} else {
					JOptionPane.showMessageDialog(null, "Game ended. Player 1 won.");
				}
				this.ended = true;
			}
		}
	}
	
	public boolean ended() {
		return this.ended;
	}
	
	public void restart() {
		
	}
	
	//faz a acao de advance e muda o deck, recebendo um deck e um game
	public void update(Game update) {
		this.deck = update.deck;
		
		this.Player1.setPoints(update.Player2.getPoints());
		this.Player1.cards = update.Player2.cards;
		this.Player1.stop = update.Player2.stop;
				
		this.Player2.setPoints(update.Player1.getPoints());
		this.Player2.cards = update.Player1.cards;
		this.Player2.stop = update.Player1.stop;
	}
	
	public String toString() {
		String gameStr = "";
		gameStr = "Game \n" + "Player 1: " + this.Player1.toString() + "\n" + "Player 2: " + this.Player2.toString();
		return gameStr;
    }
}
