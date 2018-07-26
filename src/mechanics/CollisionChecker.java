package mechanics;

import java.util.ArrayList;

import chainRxn.BounceBall;

//This class has the role of running through each of the exploded balls and checking has another circle collided with it
public class CollisionChecker extends Thread {
	
	private BounceBall bounceBall;
	
	public CollisionChecker(BounceBall bounceBall){
		this.bounceBall = bounceBall;
	}
	
	public void run() {
        while (bounceBall.getExplosions().size() > 0){
        	ArrayList<ExplodedBall> explosions = bounceBall.getExplosions();
        	int i = 0;
            while(i <= explosions.size() - 1){
            	try{
            		explosions.get(i).checkCollisions();
                	explosions = bounceBall.getExplosions();
                	i++;
            	}
            	catch(IndexOutOfBoundsException e){
            	}
            	catch(NullPointerException e){
            	}
            }
        }
    }

}
