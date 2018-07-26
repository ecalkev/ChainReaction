package mechanics;

import java.awt.Color;
import java.awt.Graphics2D;

import chainRxn.BounceBall;
import panel.GamePanel;
import sound.BellSound;

public class ScoreLabels extends Thread {
	
	private BounceBall bounceBall;
	private Color colour = Color.white;
	private int x, y;
	private int score;
	private String scoreLabel;
	
	public ScoreLabels(Circle circle, BounceBall bounceBall, GamePanel gamePanel, int chainNum){
		x = circle.getXCentre() - 15;
		y = circle.getYCentre();
		this.bounceBall = bounceBall;
		score = 100 * chainNum * chainNum * chainNum;
		bounceBall.setScore(bounceBall.getScore() + score);
		scoreLabel = "+" + score;
	}
	
	public void draw(Graphics2D g2d) {
        g2d.setColor(colour);
        g2d.drawString(scoreLabel, x, y);
    }
	
	public void run() {
		try {
	        String audioFilePath = "bomb.wav";
	        
	        BellSound player = new BellSound();
	        player.play(audioFilePath);
            Thread.sleep(1000);
        } 
        catch (InterruptedException e) {
            System.out.println("Woke up prematurely");
        }
		bounceBall.getScoreLabels().remove(this);
		
    }

}
