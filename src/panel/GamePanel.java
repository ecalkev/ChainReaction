package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import chainRxn.BounceBall;
import mechanics.Circle;
import mechanics.CollisionChecker;
import mechanics.ExplodedBall;

// Define a class to be a panel on which the balls are drawn
public class GamePanel extends JPanel implements Runnable {

    private int prefwid, prefht;
    //for referencing
    private BounceBall bounceBall;
    private Circle mouseBall;
    private boolean gameOn;


    public GamePanel(int pwid, int pht, BounceBall bounceBall) {
        prefwid = pwid;
        prefht = pht;
        this.bounceBall = bounceBall;
        gameOn = true;
        mouseBall = new Circle(this, bounceBall);
        bounceBall.getCircles().add(mouseBall);


	    // add expodedBall when mouse is clicked
	    addMouseListener(
	       new MouseAdapter() {
	    	   public void mousePressed(MouseEvent e) {
	    		   if(!getBounceBall().isClicked()){
		    		   createExplosion();
		    		   CollisionChecker collisionChecker = new CollisionChecker(getBounceBall());
		    		   collisionChecker.start();
		    		   getBounceBall().setClicked(true);
	    		   }
	           }
	    });
	    
		//listener for moving mouse, ie to show shapes as they are being drawn
		addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseMoved(MouseEvent me){
					mouseBall.setXCentre(me.getX());
					mouseBall.setYCentre(me.getY());
		      }
		});
    }

    //creates the initial explosion on click of mouse
    public void createExplosion() {
        ExplodedBall initialExplosion = new ExplodedBall(bounceBall, mouseBall);
        initialExplosion.start();
    	bounceBall.getExplosions().add(initialExplosion);
    	synchronized(bounceBall.getCircles()){
    		bounceBall.getCircles().remove(mouseBall);
    	}
    }

    //paints the playing panel
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        String scoreLabel = "Score: " + bounceBall.getScore();
        String explosionsLabel = (bounceBall.getRequiredExplosions() - bounceBall.getNumExplosions()) + " Balls Left.. ";
        g2d.setColor(Color.white);
        g2d.drawString(scoreLabel, 520, 380);
        if(bounceBall.getRequiredExplosions() - bounceBall.getNumExplosions() > 1){
            g2d.drawString(explosionsLabel, 20, 20);
        }
        else if(bounceBall.getRequiredExplosions() - bounceBall.getNumExplosions() > 0){
            g2d.drawString("Last Ball...", 20, 20);
        }
        synchronized(bounceBall.getCircles()){
            for (int i = 0; i < bounceBall.getCircles().size(); i++) {
                (bounceBall.getCircles().get(i)).draw(g2d);
            }
        }
        synchronized(bounceBall.getExplosions()){
            for (int i = 0; i < bounceBall.getExplosions().size(); i++) {
                (bounceBall.getExplosions().get(i)).draw(g2d);
            }
        }
        synchronized(bounceBall.getScoreLabels()){
            for (int i = 0; i < bounceBall.getScoreLabels().size(); i++){
            	(bounceBall.getScoreLabels().get(i)).draw(g2d);
            }	
        }
    }
	
    
    public void run(){
    	while(gameOn){
        	repaint();
    	}
    }
    
    //Getters and Setters
    
    public Dimension getPreferredSize() {
        return new Dimension(prefwid, prefht);
    }
    
    public BounceBall getBounceBall(){
    	return bounceBall;
    }

    public Circle getMouseBall() {
		return mouseBall;
	}


	public void setMouseBall(Circle mouseBall) {
		this.mouseBall = mouseBall;
	}
    
} 