package blackjack;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MultiView extends JFrame implements Runnable {

	private JPanel contentPane;
	private JLabel myCardImage;
	private JScrollPane myScrollPane;
	private JButton hitButton ;
	private JButton stopButton ;
	public JLabel myPointsLabel;
	private JLabel myMoneyLabel ;
	private JSeparator separator ;
	private JScrollPane oppScrollPane;
	private JLabel oppCardImage;
	private JLabel oppPointsLabel;
	private JLabel imageOfOpp;
	private JLabel oppMoneyLabel;
	private JTextArea myCardArea;
	private JTextArea oppCardArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiView frame = new MultiView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MultiView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		myCardImage = new JLabel("IMAGE1");
		myCardImage.setBackground(Color.WHITE);
		myCardImage.setBounds(41, 256, 171, 201);
		contentPane.add(myCardImage);
		
		myScrollPane = new JScrollPane();
		myScrollPane.setBounds(271, 256, 171, 179);
		contentPane.add(myScrollPane);
		
		myCardArea = new JTextArea();
		myCardArea.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		myCardArea.setText("");
		myScrollPane.setViewportView(myCardArea);
		
		hitButton = new JButton("Hit");
		hitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		hitButton.setBounds(482, 256, 132, 45);
		contentPane.add(hitButton);
		
		stopButton = new JButton("Stop");
		stopButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		stopButton.setBounds(482, 322, 132, 45);
		contentPane.add(stopButton);
		
		myPointsLabel = new JLabel("Points:");
		myPointsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		myPointsLabel.setBounds(492, 389, 132, 33);
		contentPane.add(myPointsLabel);
		
		myMoneyLabel = new JLabel("Money:");
		myMoneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		myMoneyLabel.setBounds(493, 422, 132, 33);
		contentPane.add(myMoneyLabel);
		
		separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(0, 232, 650, 12);
		contentPane.add(separator);
		
		oppCardImage = new JLabel("IMAGE2");
		oppCardImage.setBackground(Color.WHITE);
		oppCardImage.setBounds(271, 17, 171, 201);
		contentPane.add(oppCardImage);
		
		oppScrollPane = new JScrollPane();
		oppScrollPane.setBounds(41, 17, 171, 179);
		contentPane.add(oppScrollPane);
		
		oppCardArea = new JTextArea();
		oppCardArea.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		oppCardArea.setText("");
		oppScrollPane.setViewportView(oppCardArea);
		
		oppPointsLabel = new JLabel("Points:");
		oppPointsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		oppPointsLabel.setBounds(482, 152, 132, 33);
		contentPane.add(oppPointsLabel);
		
		imageOfOpp = new JLabel("IMAGEofopp");
		imageOfOpp.setBackground(Color.WHITE);
		imageOfOpp.setBounds(482, 6, 89, 107);
		contentPane.add(imageOfOpp);
		
		oppMoneyLabel = new JLabel("Money:");
		oppMoneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		oppMoneyLabel.setBounds(482, 187, 132, 33);
		contentPane.add(oppMoneyLabel);

	}
	
	public JButton getHitButton() {
		return this.hitButton;
	}
	
	public JButton getStopButton() {
		return this.stopButton;
	}
	
	public void updateView(Game g) {
		this.setMyPoints(g.Player1.getPoints());
		this.setOppPoints(g.Player2.getPoints());
		this.setMyMoney(g.Player1.getMoney());
		this.setOppMoney(g.Player2.getMoney());
		this.setMyCards(g.Player1.cards);
		this.setOppCards(g.Player2.cards);
	}
	
	public void setMyImage(Icon icon) {
		this.myCardImage.setIcon(icon);
	}
	
	public void setOppImage(Icon icon) {
		this.oppCardImage.setIcon(icon);
	}
	
	public void setMyPoints(int points) {
		this.myPointsLabel.setText("Points: "+points);
	}
	
	public void setOppPoints(int points) {
		this.oppPointsLabel.setText("Points: "+points);
	}
	
	public void setMyMoney(int mny) {
		this.myMoneyLabel.setText("Money: "+mny); 
	}
	
	public void setOppMoney(int mny) {
		this.oppMoneyLabel.setText("Money: "+mny); 
	}
	
	public void setMyCards(ArrayList<Card> cards) {
		String cardString = "";
		for(int i=0; i<cards.size(); i++) {
			if(i==0) {
				this.myCardArea.setText(cards.get(i).toString());
			} else {
				this.myCardArea.setText(this.myCardArea.getText() + "\n" + cards.get(i).toString());
			}
		}
	}
	
	public void setOppCards(ArrayList<Card> cards) {
		String cardString = "";
		for(int i=0; i<cards.size(); i++) {
			if(i==0) {
				this.oppCardArea.setText(cards.get(i).toString());
			} else {
				this.oppCardArea.setText(this.oppCardArea.getText() + "\n" + cards.get(i).toString());
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
