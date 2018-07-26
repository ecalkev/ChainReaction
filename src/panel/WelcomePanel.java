package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chainRxn.BounceBall;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class WelcomePanel extends JPanel implements Runnable {
	
	private BounceBall bounceBall;
	private boolean mainScreen;
	
	
	public WelcomePanel(BounceBall bounceBall){
		setBackground(Color.LIGHT_GRAY);
	    this.bounceBall = bounceBall;
	    mainScreen = true;
	    
	    setLayout(null);
	    
	    JLabel lblChainRxn = new JLabel("Chain RXN");
	    lblChainRxn.setForeground(new Color(255, 0, 51));
	    lblChainRxn.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
	    lblChainRxn.setBounds(217, 51, 220, 37);
	    add(lblChainRxn);
	    
	    JButton btnPlay = new JButton("PLAY");
	    btnPlay.setBounds(250, 165, 117, 29);
	    btnPlay.addActionListener(listener);
	    add(btnPlay);
	    
        //for background
	    bounceBall.createBalls(9);
	    
	 }
	
	//listener for tool buttons
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			bounceBall.newGame();
			mainScreen = false;
		}
	};
	
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
	
	public void run(){
    	while(mainScreen){
        	repaint();
    	}
	}
}
