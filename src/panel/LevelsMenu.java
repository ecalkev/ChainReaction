package panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import chainRxn.BounceBall;

import java.awt.Font;

public class LevelsMenu extends JPanel {
	
	private BounceBall bounceBall;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;
	
	public LevelsMenu(BounceBall bounceBall) {
		this.bounceBall = bounceBall;
		
		setBackground(Color.DARK_GRAY);
		
		setSize(650,420);
		setLayout(null);
		
		JLabel lblLevels = new JLabel("Levels");
		lblLevels.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblLevels.setForeground(Color.RED);
		lblLevels.setBounds(294, 6, 100, 41);
		add(lblLevels);
		
		btn1 = new JButton("1");
		btn1.setBounds(50, 46, 95, 95);
		btn1.addActionListener(listener);
		add(btn1);
		btn2 = new JButton("2");
		btn2.setBounds(200, 46, 95, 95);
		btn2.addActionListener(listener);
		add(btn2);
		btn3 = new JButton("3");
		btn3.setBounds(350, 46, 95, 95);
		btn3.addActionListener(listener);
		add(btn3);
		btn4 = new JButton("4");
		btn4.setBounds(500, 46, 95, 95);
		btn4.addActionListener(listener);
		add(btn4);
		btn5 = new JButton("5");
		btn5.setBounds(50, 177, 95, 95);
		btn5.addActionListener(listener);
		add(btn5);
		btn6 = new JButton("6");
		btn6.setBounds(200, 177, 95, 95);
		btn6.addActionListener(listener);
		add(btn6);
		btn7 = new JButton("7");
		btn7.setBounds(350, 177, 95, 95);
		btn7.addActionListener(listener);
		add(btn7);
		btn8 = new JButton("8");
		btn8.setBounds(500, 177, 95, 95);
		btn8.addActionListener(listener);
		add(btn8);
		btn9 = new JButton("9");
		btn9.setBounds(50, 310, 95, 95);
		btn9.addActionListener(listener);
		add(btn9);
		btn10 = new JButton("10");
		btn10.setBounds(200, 310, 95, 95);
		btn10.addActionListener(listener);
		add(btn10);
		btn11 = new JButton("11");
		btn11.setBounds(350, 310, 95, 95);
		btn11.addActionListener(listener);
		add(btn11);
		btn12 = new JButton("12");
		btn12.setBounds(500, 310, 95, 95);
		btn12.addActionListener(listener);
		add(btn12);
		
		
	}
	
	//listener for tool buttons
	ActionListener listener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == btn1){
				bounceBall.setLevel(1);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn2){
				bounceBall.setLevel(2);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn3){
				bounceBall.setLevel(3);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn4){
				bounceBall.setLevel(4);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn5){
				bounceBall.setLevel(5);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn6){
				bounceBall.setLevel(6);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn7){
				bounceBall.setLevel(7);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn8){
				bounceBall.setLevel(8);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn9){
				bounceBall.setLevel(9);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn10){
				bounceBall.setLevel(10);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn11){
				bounceBall.setLevel(11);
				bounceBall.newGame();
			}
			else if(e.getSource() == btn12){
				bounceBall.setLevel(12);
				bounceBall.newGame();
			}
		}
	};
	

}
