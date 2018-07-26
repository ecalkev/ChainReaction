package mechanics;

import java.awt.Color;
import java.awt.Graphics2D;

import chainRxn.BounceBall;
import panel.GamePanel;

public class ExplodedBall extends Thread {
	
    //for getting list of balls
	private BounceBall bounceBall;
	private int xCentre, yCentre;
    private boolean ballMax;
    private int diameter = 10;
    private Color colour;
    //describes how far along the chain the explosion occurred
    private int chainNum;
    
    //Constructor for initial explosion
    public ExplodedBall(BounceBall bounceBall, Circle circle) {
    	this.bounceBall = bounceBall;
    	chainNum = 1;
        // Obtains characteristics of ball that is blown up
        xCentre = circle.getXCentre();
        yCentre = circle.getYCentre();
        colour = circle.getColour();
    }
    
    //Constructor for chain explosions
    public ExplodedBall(BounceBall bounceBall, Circle circle, int chainNum) {
    	this.bounceBall = bounceBall;
    	this.chainNum = chainNum + 1;
        // Obtains characteristics of ball that is blown up
        xCentre = circle.getXCentre();
        yCentre = circle.getYCentre();
        colour = circle.getColour();
    }

    //for drawing circle
    public void draw(Graphics2D g2d) {
        g2d.setColor(colour);
        g2d.fillOval(xCentre - diameter/2, yCentre - diameter/2, diameter, diameter);
    }
    
    //blows ball up
    synchronized public void expand(){
        diameter++;
    }
    
    //shrinks the ball
    synchronized public void shrink(){
        diameter--;
    }
    
    //checks collisions
    public void checkCollisions(){
    	synchronized(bounceBall.getCircles()){
            for(int i = 0; i < bounceBall.getCircles().size(); i++){
            		Circle circle = bounceBall.getCircles().get(i);
        			synchronized(circle){
                		if(checkCollision(circle)){
                			circle.grow(this);
                		}
        			}
            	}
    	}
    }
    
    //compares an explosion with a circle to check for a collision
    public boolean checkCollision(Circle circle){
    	double distance = Math.sqrt(Math.pow(xCentre - circle.getXCentre(), 2) + Math.pow(yCentre - circle.getYCentre(), 2));
		if(diameter/2 + 5 >= distance){
			return true;
		}
		else{
			return false;
		}
    }
    
	public void run() {
        //before ball has reached its max, keep increasing in size
		if(!ballMax){
			while (diameter < 70){
	        	try {
	                // To free up processor time
	                Thread.sleep(10);
	            } 
	            catch (InterruptedException e) {
	                System.out.println("Woke up prematurely");
	            }
	        	
	        	//checkCollisions();
	            
	            expand();
	        }
			ballMax = true;
			
		
		//when ball has reached max, shrink until it is removed (diameter = 0)
		if(ballMax){
			//creates a pause in growth for explosion to hold a size for a few seconds
    		try {
                // To free up processor time
                Thread.sleep(1400);
            } 
            catch (InterruptedException e) {
                System.out.println("Woke up prematurely");
            }
        	while(diameter > 0){
        		try {
	                Thread.sleep(5);
	            } 
	            catch (InterruptedException e) {
	                System.out.println("Woke up prematurely");
	            }
	            
	            shrink();
        	}
        	bounceBall.getExplosions().remove(this);
        	if(bounceBall.getExplosions().size() < 1){
        		if(bounceBall.getNumExplosions() < bounceBall.getRequiredExplosions()){
        			bounceBall.gameOver();
        			bounceBall.getGameOverPanel().setLabelText("You exploded " + bounceBall.getNumExplosions() + " out of " + (bounceBall.getLevel() * 5) + " balls");
        		}
        		else{
        			if(bounceBall.getLevel() < 12){
        				bounceBall.nextLevel();
        				String explosionScore = "You exploded " + bounceBall.getNumExplosions() + " out of " + (bounceBall.getLevel() * 5) + " balls";
        				String score = "And got a score of " + bounceBall.getScore();
        				bounceBall.getNextLevelPanel().setLabelText(score, explosionScore);
        			}
        			else{
        				bounceBall.levelsMenu();
        			}
        			
        		}
        	}
        }
    }}

	
	//Getters and Setters
	public int getXCentre() {
		return xCentre;
	}

	public void setXCentre(int xCentre) {
		this.xCentre = xCentre;
	}

	public int getYCentre() {
		return yCentre;
	}

	public void setYCentre(int yCentre) {
		this.yCentre = yCentre;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	public int getChainNum() {
		return chainNum;
	}

	public void setChainNum(int chainNum) {
		this.chainNum = chainNum;
	}
	
}
