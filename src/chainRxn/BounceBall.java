package chainRxn;

import javax.swing.*;

import mechanics.Circle;
import mechanics.ExplodedBall;
import mechanics.ScoreLabels;
import panel.GameOverPanel;
import panel.GamePanel;
import panel.LevelsMenu;
import panel.NextLevelPanel;
import panel.WelcomePanel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BounceBall extends JFrame {

    //playing panel
	private GamePanel gamePanel;
    //game over panel
    private GameOverPanel gameOverPanel;
    //next level panel
    private NextLevelPanel nextLevelPanel;
    //secret menu for levels
    private LevelsMenu levelsMenu;
    //Panel at game start
    private WelcomePanel welcomePanel;
    //list for requirements for levels
    private int[] levels;
    private int level;
    private ArrayList<Circle> circles;
    private ArrayList<ExplodedBall> explosions;
    //private CollisionChecker explosions;
    private ArrayList<ScoreLabels> scoreLabels;
    //accumulated score for the level
    private int score;
    //explosion count
    private int numExplosions;
    //explosions required to complete level
    private int requiredExplosions;
    //boolean for preventing multiple click explosions
    private boolean clicked;

    // set up interface
    public BounceBall() {
        super("Chain Rxn");
        score = 0;
        numExplosions = 0;
        circles = new ArrayList<Circle>();
        explosions = new ArrayList<ExplodedBall>();
        scoreLabels = new ArrayList<ScoreLabels>();
    	
        gamePanel = new GamePanel(600, 400, this);
        gamePanel.setVisible(false);
        Thread panelThread = new Thread(gamePanel);
        panelThread.start();
        
        setSize(650, 420);
        setResizable(false);
        getContentPane().setLayout(new CardLayout());
        add(gamePanel, BorderLayout.CENTER);
        
		//JMenu
		JMenuBar bar = new JMenuBar(); 
		JMenu file = new JMenu ("Menu");
		bar.add(file);
		JMenuItem levelsBtn = new JMenuItem(new AbstractAction("Levels") {
		    public void actionPerformed(ActionEvent e) {
		        password();
		    }
		});
		file.add (levelsBtn);
		setJMenuBar(bar);
        
    	gameOverPanel = new GameOverPanel(this);
        gameOverPanel.setSize(new Dimension(600, 400));
        getContentPane().add(gameOverPanel,BorderLayout.CENTER);
        gameOverPanel.setVisible(false);
        
    	nextLevelPanel = new NextLevelPanel(this);
    	nextLevelPanel.setSize(new Dimension(600, 400));
        getContentPane().add(nextLevelPanel,BorderLayout.CENTER);
        nextLevelPanel.setVisible(false);
        
        levelsMenu = new LevelsMenu(this);
        getContentPane().add(levelsMenu,BorderLayout.CENTER);
        levelsMenu.setVisible(false);
        
        welcomePanel = new WelcomePanel(this);
        Thread welcomeThread = new Thread(welcomePanel);
        welcomeThread.start();
        getContentPane().add(welcomePanel,BorderLayout.CENTER);
        welcomePanel.setBounds(0, 300, 650, 200);
        welcomePanel.setVisible(true);
        

        setSize(630, 450);
        setVisible(true);
        createLevels();
        level = 1;
        requiredExplosions = levels[level - 1];
        createBalls(level);
    }
    
    //declares the number of explosions to complete each level
    public void createLevels(){
    	levels = new int[12];
    	levels[0] = 1;
    	levels[1] = 2;
    	levels[2] = 4;
    	levels[3] = 6;
    	levels[4] = 10;
    	levels[5] = 15;
    	levels[6] = 18;
    	levels[7] = 22;
    	levels[8] = 30;
    	levels[9] = 37;
    	levels[10] = 48;
    	levels[11] = 54;
    }
    
    //for making the gameover screen appear
    public void gameOver(){
    	gamePanel.setVisible(false);
    	gameOverPanel.setVisible(true);
    	nextLevelPanel.setVisible(false);
    	levelsMenu.setVisible(false);
    	//sets balls for background of panel
    	createBalls(5);
    }
    
    //for making the next level/success screen appear
    public void nextLevel(){
    	gamePanel.setVisible(false);
    	gameOverPanel.setVisible(false);
    	nextLevelPanel.setVisible(true);
    	levelsMenu.setVisible(false);
    	//sets balls for background of panel
    	createBalls(5);
    }
    
    //resets the canvas, removes all scores, balls, etc., and a new level is started
    public void newGame(){
        //resets game parameters
    	score = 0;
        numExplosions = 0;
        circles = new ArrayList<Circle>();
        explosions = new ArrayList<ExplodedBall>();
        scoreLabels = new ArrayList<ScoreLabels>();
        requiredExplosions = levels[level - 1];
        clicked = false;
        //makes new balls
        createBalls(level);
        gamePanel.setMouseBall(new Circle(gamePanel, this));
        circles.add(gamePanel.getMouseBall());
        //bring canvas to front
        gamePanel.setVisible(true);
        gameOverPanel.setVisible(false);
        nextLevelPanel.setVisible(false);
        levelsMenu.setVisible(false);
        welcomePanel.setVisible(false);
    }
    
    //asks for a password to see levels menu
    public void password(){
    	String password = JOptionPane.showInputDialog("Enter the password: ");
    	
    	if(password.equals("password")){
    		levelsMenu();
    	}
    	else{
    		JOptionPane.showMessageDialog(this, "Incorrect password.");
    	}
    }
    
    
    
    //bring levels menu to front
    public void levelsMenu(){
        gamePanel.setVisible(false);
        gameOverPanel.setVisible(false);
        nextLevelPanel.setVisible(false);
        welcomePanel.setVisible(false);
        levelsMenu.setVisible(true);
    }

    //creates all balls and starts their threads
    //need to change to account for levels
    public void createBalls(int level) {
        for (int i = 1; i <= level * 5; i++) {
            Circle nextBall = new Circle(this, gamePanel);
            circles.add(nextBall);
            nextBall.start();
        }
    }
    
    //Getters and Setters

    public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}

	public void setCircles(ArrayList<Circle> circles) {
		this.circles = circles;
	}
	
	public ArrayList<ExplodedBall> getExplosions() {
		return explosions;
	}

	public void setExplodedBall(ArrayList<ExplodedBall> explosions) {
		this.explosions = explosions;
	}
	
	public ArrayList<ScoreLabels> getScoreLabels() {
		return scoreLabels;
	}

	public void setScoreLabels(ArrayList<ScoreLabels> scoreLabels) {
		this.scoreLabels = scoreLabels;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNumExplosions() {
		return numExplosions;
	}

	public void setNumExplosions(int numExplosions) {
		this.numExplosions = numExplosions;
	}

	public int getRequiredExplosions() {
		return requiredExplosions;
	}

	public void setRequiredExplosions(int requiredExplosions) {
		this.requiredExplosions = requiredExplosions;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int[] getLevels() {
		return levels;
	}

	public void setLevels(int[] levels) {
		this.levels = levels;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public GameOverPanel getGameOverPanel() {
		return gameOverPanel;
	}

	public void setGameOverPanel(GameOverPanel gameOverPanel) {
		this.gameOverPanel = gameOverPanel;
	}

	public NextLevelPanel getNextLevelPanel() {
		return nextLevelPanel;
	}

	public void setNextLevelPanel(NextLevelPanel nextLevelPanel) {
		this.nextLevelPanel = nextLevelPanel;
	}

	public static void main(String args[]) {

        BounceBall application = new BounceBall();

        application.addWindowListener(
            new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}