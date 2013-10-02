

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrogsterJPanel extends JPanel implements KeyListener, ActionListener{
	private static final int AMOUNT_OF_TRAFFIC = FrogsterConstants.AMOUNT_OF_TRAFFIC;
	public static final Font LARGE_FONT = new Font("GENEVA", Font.BOLD, 20);
	private Frog froggy;
	private int direction, lives, score;
	private Timer t;
	private Traffic[] traffic;
	private Log[] log;
	private Rectangle river, farBank;
	private Color grassColor;
	
	public FrogsterJPanel(){
		addKeyListener(this);
		froggy = new Frog();
		t = new Timer(30, this);
		t.start();
		lives = 5;
		score = 0;
		grassColor = new Color(0,102,0);
		river = new Rectangle(0, 85, FrogsterConstants.JPANEL_SIZE, 200);
		farBank = new Rectangle(0, 0, FrogsterConstants.JPANEL_SIZE, 90);
		traffic = new Traffic[AMOUNT_OF_TRAFFIC];
		int count = 1;
		for(int i = 0; i < traffic.length; i++){
			traffic[i] = new Traffic(count);
			count ++;
		}
		
		log = new Log[3];
		log[0] = new Log(FrogsterConstants.JPANEL_SIZE - 140, 230);
		log[1] = new Log(0, 170);
		log[2] = new Log(FrogsterConstants.JPANEL_SIZE - 140, 100);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawRoad(g);
		drawRiver(g);
		for(int i = 0; i < log.length; i++){
			log[i].drawLog(g);
		}		
		g.setColor(grassColor);
		g.fillRect(0, FrogsterConstants.JPANEL_SIZE - 40, FrogsterConstants.JPANEL_SIZE, 40);
		for(int i = 0; i < traffic.length; i++){
			traffic[i].drawTraffic(g);
		}
		drawFarBank(g);
		g.setColor(Color.RED);
		g.setFont(LARGE_FONT);
		g.drawString("Number of lives: " + lives,5,30);
		g.drawString("Score: " + score,5,50);
		froggy.drawFrog(g);
		
	}
	
	public void keyPressed(KeyEvent e){
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			direction = 1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			direction = 2;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			direction = 3;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			direction = 4;
		}
		froggy.moveFrog(direction);
		repaint();
	}	
	
	private void drawRoad(Graphics g){
		int x,y, size;
		x = 0;
		y = FrogsterConstants.JPANEL_SIZE;
		size = FrogsterConstants.JPANEL_SIZE;
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y / 2 - 20,size, size / 2 - 20);
		g.setColor(Color.YELLOW);
		g.fillRect(x, y/2 + size/4 -15, size, 5);
		g.fillRect(x, y/2 + size/4 -25, size, 5);
		
	}
	
	private void drawRiver(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(river.x, river.y, river.width,river.height);
	}
	private void drawFarBank(Graphics g){
		g.setColor(grassColor);
		g.fillRect(farBank.x, farBank.y, farBank.width, farBank.height);
	}
	
	public void actionPerformed(ActionEvent e){

		//respawn traffic it reaches end of road and respawn frog if he dies
		for(int i = 0; i < traffic.length; i++){
			if(traffic[i].hasReachedEnd(traffic[i])){
				traffic[i] = new Traffic(traffic[i].getYStartingPos());
			}else{
				if(traffic[i].hasHitFrog(froggy.getFrogRectangle())){
					froggy = new Frog();
					lives--;
				}
				traffic[i].move();
			}
		}

		//respawn logs if they reach end of river and move frog along if he is on log
		for(int i = 0; i < log.length; i++){
			if(log[i].hasReachedEnd()){
				log[i] = new Log(log[i].getXStart(), log[i].getYStart());
			}else{
				log[i].move();
			}
			if(froggy.frogIsOnLog(log[i].getLogRectangle())){
				froggy.moveOnLog(log[i].getDirection(), log[i].getSpeed());
				if(froggy.getFrogRectangle().x < 0 || froggy.getFrogRectangle().x + froggy.getFrogRectangle().width > FrogsterConstants.JPANEL_SIZE){
					froggy = new Frog();
					lives --;
				}
			}
		}
		
		//if the frog is not on the log and he touches river, respawn frog
		if(!(froggy.getFrogRectangle().intersects(log[0].getLogRectangle()) ||
		froggy.getFrogRectangle().intersects(log[1].getLogRectangle()) || 
		froggy.getFrogRectangle().intersects(log[2].getLogRectangle()))){
			if(river.contains(froggy.getFrogRectangle())){
				froggy = new Frog();
				lives--;
			}
		}
		
		//if frog reaches far bank increase score and respawn frog
		if(farBank.contains(froggy.getFrogRectangle())){
			froggy = new Frog();
			score++;
		}
		repaint();
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}


