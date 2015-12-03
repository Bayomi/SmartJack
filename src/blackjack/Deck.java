package blackjack;

import java.util.*;

public class Deck implements java.io.Serializable {
	Queue<Card> cards = new LinkedList<Card>();
	int count;
	float percentage;
	static ArrayList<String> Naipes = new ArrayList<String>(Arrays.asList("Ouro", "Espada", "Copas", "Paus"));
	static ArrayList<String> Valores = new ArrayList<String>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
	
	public Deck() {
		Queue<Card> cards = new LinkedList<Card>();
		this.shuffle();
		this.resetCount();
	}
	
	public void shuffle(){
		ArrayList<Card> deck1 = getRandomCards();
		Queue<Card> cards = new LinkedList<Card>();
		for(int i=0; i<deck1.size(); i++){
			this.cards.add(deck1.get(i));
		}
		this.resetCount();
	}
	
	public Card draw() {
		Card drawed = cards.poll();
		cards.add(drawed);
		this.count++;
		this.setPercentage();
		return drawed;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public float getPercentage() {
		return this.percentage;
	}
	
	public void resetCount() {
		this.count = 0;
		this.setPercentage();
	}
	
	public void setPercentage() {
		this.percentage = (float)this.count/this.cards.size();
	}
	
	static ArrayList<Card> getRandomCards(){
		ArrayList<Card> rc = new ArrayList<Card>();
		
		for(int i=0; i<Valores.size(); i++){
			for(int j=0; j<Naipes.size(); j++) {
				Card card = new Card(Valores.get(i), Naipes.get(j));
				rc.add(card);
			}
		}
		
		Random generator = new Random();  
        for (int i=0; i<rc.size(); i++) {
            int randomPosition = generator.nextInt(rc.size());
            Card temp = rc.get(i);
            rc.set(i, rc.get(randomPosition));
            rc.set(randomPosition, temp);
        }
		
		return rc;
	}
	
	public String toString() {
		String deckCards = "";
		int i = 1;
		for (Card c: this.cards) {
			deckCards += i + ". " + c.toString() + " \n";
			i++;
		}
		
		return deckCards;
    }	
	
}
