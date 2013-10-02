


import java.awt.*;


public class Log {
	private int speed, width, height, xStartingPos, yStartingPos, direction;
	private Rectangle logRectangle;
	private Color logColor;
	
	public Log(int xPos, int yPos){
	speed = (int)(Math.random() * 5 + 1);
	width = (int)(Math.random() * 190 + 85);
	height = FrogsterConstants.LOG_HEIGHT;
	logRectangle = new Rectangle(xPos, yPos, width, height); 
	logColor = new Color(139,69,1);
	xStartingPos = xPos;
	yStartingPos = yPos;
	if(xStartingPos == FrogsterConstants.JPANEL_SIZE - 140){
		direction = 1;
	}else{
		direction = 2;
	}
	}
	
	public void drawLog(Graphics g){
		g.setColor(logColor);
		g.fillRect(logRectangle.x, logRectangle.y, logRectangle.width, logRectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(logRectangle.x, logRectangle.y, logRectangle.width, logRectangle.height);
	}
	
	public void move(){
		if(xStartingPos == FrogsterConstants.JPANEL_SIZE - 140){
			logRectangle.x -= speed;
		}else{
			logRectangle.x += speed;
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public boolean hasReachedEnd(){
		if(xStartingPos == FrogsterConstants.JPANEL_SIZE - 140){
			if(logRectangle.x + logRectangle.width < 0){
				return true;
			}else{
				return false;
			}
		}else{
			if(logRectangle.x > FrogsterConstants.JPANEL_SIZE){
				return true;
			}else{
				return false;
			}
		}		
	}
	
	public int getXStart(){
		return xStartingPos;
	}
	public int getYStart(){
		return yStartingPos;
	}
	public Rectangle getLogRectangle(){
		return logRectangle;
	}
	public int getSpeed(){
		return speed;
	}
	public int getDirection(){
		return direction;
	}
}
