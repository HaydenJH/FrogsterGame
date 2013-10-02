


import java.awt.*;


public class Frog {
	private static final int FROG_SPEED = FrogsterConstants.FROG_SPEED;
	private Rectangle frogRectangle,leftHalf, rightHalf, backHalf, frontHalf;
	
	public Frog(){
		frogRectangle = new Rectangle(FrogsterConstants.JPANEL_SIZE / 2 - 20,FrogsterConstants.JPANEL_SIZE - 40, 30,35 );
		leftHalf = new Rectangle(frogRectangle.x, frogRectangle.y, frogRectangle.width / 2, frogRectangle.height);
		rightHalf = new Rectangle(frogRectangle.x + frogRectangle.width / 2 , frogRectangle.y, frogRectangle.width / 2, frogRectangle.height);
		backHalf = new Rectangle(frogRectangle.x, frogRectangle.y + frogRectangle.height / 2, frogRectangle.width, frogRectangle.height/2);
		frontHalf = new Rectangle(frogRectangle.x, frogRectangle.y, frogRectangle.width, frogRectangle.height/2);
		
	}
	
	public void drawFrog(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(frogRectangle.x, frogRectangle.y, frogRectangle.width, frogRectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(frogRectangle.x, frogRectangle.y, frogRectangle.width, frogRectangle.height);
		
	}
	
	public void moveFrog(int direction){
		if(direction == 1){
			frogRectangle.y -= FROG_SPEED;
			leftHalf.y -= FROG_SPEED;
			rightHalf.y -= FROG_SPEED;
			backHalf.y -= FROG_SPEED;
			frontHalf.y -= FROG_SPEED;
		}else if(direction == 2 && frogRectangle.y + frogRectangle.height < FrogsterConstants.JPANEL_SIZE){
			frogRectangle.y += FROG_SPEED;	
			leftHalf.y += FROG_SPEED;
			rightHalf.y += FROG_SPEED;
			backHalf.y += FROG_SPEED;
			frontHalf.y += FROG_SPEED;
		}else if(direction == 3 && frogRectangle.x > 0){
			frogRectangle.x -= FROG_SPEED;
			leftHalf.x -= FROG_SPEED;
			rightHalf.x -= FROG_SPEED;
			backHalf.x -= FROG_SPEED;
			frontHalf.x -= FROG_SPEED;
		}else if(direction == 4 && frogRectangle.x + frogRectangle.width < FrogsterConstants.JPANEL_SIZE){
			frogRectangle.x += FROG_SPEED;
			leftHalf.x += FROG_SPEED;
			rightHalf.x += FROG_SPEED;
			backHalf.x += FROG_SPEED;
			frontHalf.x += FROG_SPEED;
		}
	}
	
	public Rectangle getFrogRectangle(){
		return frogRectangle;
	}
	
	public void moveOnLog(int direction, int speed){
		if(direction == 1){
			frogRectangle.x -= speed;
			leftHalf.x -= speed;
			rightHalf.x -= speed;
			backHalf.x -= speed;
			frontHalf.x -= speed;
		}else if(direction == 2){
			frogRectangle.x += speed;
			leftHalf.x += speed;
			rightHalf.x += speed;
			backHalf.x += speed;
			frontHalf.x += speed;
		}
	}
	
	public boolean frogIsOnLog(Rectangle log){
		boolean collision = log.contains(leftHalf) || log.contains(rightHalf) || log.contains(backHalf) ||
		log.contains(frontHalf);
		if(collision){
			return true;
		}else{
			return false;
		}
	}
	
}
