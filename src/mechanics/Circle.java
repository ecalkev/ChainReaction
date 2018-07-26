package mechanics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import chainRxn.BounceBall;
import panel.GamePanel;

// Class Circle is used to create a ball and move its position
public class Circle extends Thread {

    //for getting list of balls
	private BounceBall bounceBall;
	//for drawing on gamePanel
	private GamePanel gamePanel;
	private int xCentre, yCentre;
    private boolean ballStarted;
    private int diameter = 10, speed = 50;
    private int xspeed, yspeed;         
    Random r = new Random();
    //using range 80 to 255 to eliminate dark coloured balls
    private int red = 80 + r.nextInt(165), green = 80 + r.nextInt(165), blue = 80 + r.nextInt(165);
    private Color colour = new Color(red, green, blue, 175);

    public Circle(BounceBall bounceBall, GamePanel gamePanel) {
    	this.bounceBall = bounceBall;
    	this.gamePanel = gamePanel;
        // Create new ball with random speed, start point,
        // and direction.  "Speed" is actually the amount of sleep
        // between moves.
        ballStarted = true;
        xCentre = diameter/2 + (int) (Math.random() * (600 - diameter/2));
        yCentre = diameter/2 + (int) (Math.random() * (400 - diameter/2));
        //picks a speed in the range 4 to 7 or -4 to -7
        xspeed = 4 + (int) (Math.random() * 3);
        //if statements eliminate speeds that are too slow
        if(Math.random() >= 0.5) xspeed = -1 * xspeed;
        yspeed = 4 + (int) (Math.random() * 3);
        if(Math.random() >= 0.5) yspeed = -1 * yspeed;
    }
    
    //constructor for cursor ball where initial explosion may occur
    public Circle(GamePanel gamePanel, BounceBall bounceBall) {
    	this.bounceBall = bounceBall;
    	this.gamePanel = gamePanel;
        xCentre = 40;
        yCentre = 40;
        colour = new Color(255, 255, 255, 100);
        diameter = 80;
    }
    
    //constructor made for JUnit testing check collisions function
    public Circle(int x, int y) {
        xCentre = x;
        yCentre = y;
        colour = new Color(255, 255, 255, 100);
        diameter = 10;
    }
    
    //draws the circle
    public void draw(Graphics2D g2d) {
        g2d.setColor(colour);
        g2d.fillOval(xCentre - diameter/2, yCentre - diameter/2, diameter, diameter);
    }
    
	// calculate new position and move ball
    synchronized public void updatePosition(){
        xCentre = xCentre + xspeed;
        if (xCentre + diameter/2 > gamePanel.getWidth() || xCentre - diameter/2 < 0) {
        	xspeed = -xspeed;
        }
        yCentre = yCentre + yspeed;
        if (yCentre + diameter/2 > gamePanel.getHeight() || yCentre - diameter/2 < 0) {
        	yspeed = -yspeed;
        }
    }
    
    //creates an ExplodedBall, adds score and removes the circle from the ArrayList
    synchronized public void grow(ExplodedBall sourceExplosion) {
        ExplodedBall explosion = new ExplodedBall(bounceBall, this, sourceExplosion.getChainNum());
        explosion.start();
        bounceBall.getExplosions().add(explosion);
        bounceBall.setNumExplosions(bounceBall.getNumExplosions() + 1);
        ScoreLabels score = new ScoreLabels(this, bounceBall, gamePanel, sourceExplosion.getChainNum());
        score.start();
        bounceBall.getScoreLabels().add(score);
        bounceBall.getCircles().remove(this);
    }
    
	public void run() {
        while (ballStarted) // Keeps ball moving
        {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                System.out.println("Woke up prematurely");
            }

            updatePosition();

        }
    }

    //Getters and Setters
    public int getXCentre() {
        return xCentre;
    }
    
    public void setXCentre(int x) {
        xCentre = x;
    }

    public int getYCentre() {
        return yCentre;
    }
    
    public void setYCentre(int y) {
        yCentre = y;
    }
    
    public Color getColour(){
    	return colour;
    }

    public int getDiameter() {
        return diameter;
    }

}