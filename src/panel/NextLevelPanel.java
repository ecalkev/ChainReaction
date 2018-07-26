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

public class NextLevelPanel extends JPanel {
	
	private BounceBall bounceBall;
	private JLabel lblScore;
	private JLabel lblExplosion;
	
	public NextLevelPanel(BounceBall bounceBall) {
		this.bounceBall = bounceBall;

		
		setBackground(Color.GRAY);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(60, 68, 485, 200);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSuccess = new JLabel("Success");
		lblSuccess.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblSuccess.setForeground(Color.RED);
		lblSuccess.setBounds(220, 0, 170, 41);
		panel.add(lblSuccess);
		
		JButton btnNextLevel = new JButton("Next Level");
		btnNextLevel.setBounds(183, 141, 154, 29);
		btnNextLevel.addActionListener(listener);
		panel.add(btnNextLevel);
		
		lblExplosion = new JLabel();
		lblExplosion.setForeground(Color.RED);
		lblExplosion.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblExplosion.setBounds(121, 38, 295, 53);
		panel.add(lblExplosion);
		
		lblScore = new JLabel();
		lblScore.setForeground(Color.RED);
		lblScore.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblScore.setBounds(153, 87, 310, 53);
		panel.add(lblScore);
		
		
	}
	
	//listener for tool buttons
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			bounceBall.setLevel(bounceBall.getLevel() + 1);
			bounceBall.newGame();
		}
	};
	
	public void setLabelText(String score, String explosion){
		lblScore.setText(score);
		lblExplosion.setText(explosion);
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