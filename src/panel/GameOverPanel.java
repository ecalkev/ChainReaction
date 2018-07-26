package panel;

import javax.swing.JPanel;

import chainRxn.BounceBall;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GameOverPanel extends JPanel {
	
	private BounceBall bounceBall;
	private JLabel lblScore;
	
	public GameOverPanel(BounceBall bounceBall) {
		this.bounceBall = bounceBall;
		
		setBackground(Color.GRAY);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(32, 76, 539, 200);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblGameOver.setForeground(Color.RED);
		lblGameOver.setBounds(208, 6, 175, 53);
		panel.add(lblGameOver);
		
		JButton btnTryAgain = new JButton("Try Again");
		btnTryAgain.setBounds(173, 123, 197, 29);
		btnTryAgain.addActionListener(listener);
		panel.add(btnTryAgain);
		
		lblScore = new JLabel();
		lblScore.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblScore.setForeground(Color.RED);
		lblScore.setBounds(148, 71, 330, 40);
		panel.add(lblScore);
	}
	
	//listener for button
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			bounceBall.newGame();
		}
	};
	
	public void setLabelText(String score){
		lblScore.setText(score);
	}
	
	
    //paints the background
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        synchronized(bounceBall.getCircles()){
            for (int i = 1; i < bounceBall.getCircles().size(); i++) {
                (bounceBall.getCircles().get(i)).draw(g2d);
            }
        }

    }

}
