package blackjack;

import java.awt.image.BufferedImage;
import java.util.*;

public class Card implements java.io.Serializable {
	private BufferedImage myPicture;
	private int ranking;
	private int rankingDraw;
	private String naipe;
	private String valor;
	private String name;
	static ArrayList<String> Naipes = new ArrayList<String>(Arrays.asList("Ouro", "Espada", "Copas", "Paus"));
	static ArrayList<String> Valores = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J","Q", "K","A"));
	
	public Card(String valor, String naipe){
		this.setValor(valor);
		this.setNaipe(naipe);
		this.setName();
		this.setRanking();
	}
	
	public void setNaipe(String n) {
		this.naipe = n;
	}
	
	public String getNaipe() {
		return this.naipe;
	}
	
	public void setValor(String v) {
		this.valor = v;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public void setName() {
		this.name = this.valor + " de " + this.naipe;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setRanking() {
		if(this.valor == "J") {
			this.ranking = 10;
		} else if(this.valor == "Q") {
			this.ranking = 10;
		} else if(this.valor == "K") {
			this.ranking = 10;
		} else if (this.valor == "A") {
			this.ranking = 11;
		} else {
			this.ranking = Valores.indexOf(this.valor) + 2;
		}
	}
	
	public int getRanking() {
		return this.ranking;
	}
	
	public int getRankingDraw() {
		return this.rankingDraw;
	}
	
	public String toString() {
        return this.getName() + " - Ranking: "+ this.getRanking();
    }	
}