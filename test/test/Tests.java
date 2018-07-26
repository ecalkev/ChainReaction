package test;

import static org.junit.Assert.*;

import org.junit.Test;

import chainRxn.BounceBall;
import mechanics.Circle;
import mechanics.ExplodedBall;

public class Tests {
	

	@Test
	public void testNewGame() {
		//testing that the new game function correctly resets all game parameters
		BounceBall game = new BounceBall();
		game.setLevel(5);
		game.newGame();
		
		//26 accounts for the 25 balls in level 5 and the cursor ball
		assertTrue(game.getCircles().size() == 26);
		assertTrue(game.getExplosions().size() == 0);
		assertTrue(game.getScoreLabels().size() == 0);
		assertTrue(game.getScore() == 0);
		assertTrue(game.getNumExplosions() == 0);
		assertTrue(game.getRequiredExplosions() == 10);
		assertFalse(game.isClicked());
	}
	
	@Test
	public void testCheckCollision() {
		//testing that the check collisions works correctly
		BounceBall game = new BounceBall();
		Circle c1 = new Circle(40,30);
		Circle c2 = new Circle(34,34);
		Circle c3 = new Circle(1,1);
		Circle c4 = new Circle(40,29);
		Circle c5 = new Circle(40,40);
		ExplodedBall explosion = new ExplodedBall(game, c5);
		
		
		assertTrue(explosion.checkCollision(c1));
		assertTrue(explosion.checkCollision(c2));
		assertFalse(explosion.checkCollision(c3));
		assertFalse(explosion.checkCollision(c4));
		
	}


}
