package blackjack;

import java.util.ArrayList;

public class Player implements java.io.Serializable {
	private int money;
	private int points;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public boolean stop = false;
	
	public Player(int mny) {
		this.money = mny;
		this.setPoints(0);
	}
	
	public void setPoints(int pnt) {
		this.points = pnt;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setMoney(int mny) {
		this.money = mny;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void hit(Deck deck) {
		if(!this.status()) {
			Card last = deck.draw();
			cards.add(last);
			this.points += last.getRanking();
			if(this.points>21) {
				if(last.getValor() == "A") {
					this.points -= 10;
				} else {
					this.stop();
				}
			} else if(this.points == 21) {
				this.stop();
			}
		}
	}
	
	public void stop() {
		this.stop = true;
	}
	
	public void restart() {
		this.stop = false;
	}
	
	public boolean status() {
		return this.stop;
	}
	
	public void win(int win) {
		this.restart();
		this.setPoints(0);
		this.setMoney(this.getMoney() + win);
		this.cards.clear();
	}
	
	public void lose(int lost) {
		this.restart();
		this.setPoints(0);
		this.setMoney(this.getMoney() - lost);
		this.cards.clear();
	}
	
	public String toString() {
		String playerStr = "";
		
		playerStr += "Money: " + this.getMoney() + ", ";
		playerStr += "Cards: " + this.cards.size() + ", ";
		playerStr += "Points: " + this.getPoints() + ", ";
		playerStr += "Stopped: " + this.status() + ". ";
		
		return playerStr;
    }
	
}
