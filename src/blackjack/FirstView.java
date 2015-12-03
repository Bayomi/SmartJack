package blackjack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FirstView {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstView window = new FirstView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton singlePlayerButton = new JButton("Single Player");
		singlePlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ChooseView chooseView = new ChooseView();
				chooseView.setVisible(true);
			}
		});
		singlePlayerButton.setBounds(157, 71, 136, 47);
		frame.getContentPane().add(singlePlayerButton);
		
		JButton multiPlayerButton = new JButton("Multiplayer");
		multiPlayerButton.setForeground(Color.BLACK);
		multiPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ConnectView connectView = new ConnectView();
				connectView.setVisible(true);
			}
		});
		multiPlayerButton.setBounds(157, 137, 136, 47);
		frame.getContentPane().add(multiPlayerButton);
	}
}
