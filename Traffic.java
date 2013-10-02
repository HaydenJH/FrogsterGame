


import java.awt.*;


public class Traffic {
	private int speed,yStartingPos, r, g, b, x, y, width, height, r2, g2, b2;
	private Color trafficColor, roofColor;
	private Rectangle trafficRectangle;
	
	
	public Traffic(int yStartingPos){
	speed = (int)(Math.random() * 9 + 5);
	
	this.yStartingPos = yStartingPos;
	//randomly assign colours to each new car generated
	r = (int)(Math.random() * 255 + 1);
	g = (int)(Math.random() * 255 + 1);
	b = (int)(Math.random() * 255 + 1);		
	trafficColor = new Color(r,g,b);	
	
	r2 = (int)(Math.random() * 255 + 1);
	g2= (int)(Math.random() * 255 + 1);
	b2 = (int)(Math.random() * 255 + 1);
	roofColor = new Color(r2,g2,b2);	
	
	width = FrogsterConstants.TRAFFIC_WIDTH;
	height = FrogsterConstants.TRAFFIC_HEIGHT;
	
	if(yStartingPos == 1){
		y = 310;
		x = 0;
	}else if(yStartingPos == 2){
		y = 520;
		x = FrogsterConstants.JPANEL_SIZE - width;
	}else if(yStartingPos == 3){
		y = 450;
		x = 0;
	}else{
		y = 380;
		x = FrogsterConstants.JPANEL_SIZE - width;
	}
		
	
	trafficRectangle = new Rectangle(x,y,width,height);			
		
	}
	
	public void drawTraffic(Graphics g){
		g.setColor(trafficColor);
		g.fillRect(trafficRectangle.x,trafficRectangle.y,trafficRectangle.width,trafficRectangle.height);
		g.setColor(roofColor);
		g.fillRect(trafficRectangle.x + 10, trafficRectangle.y + 5, trafficRectangle.width - 10, trafficRectangle.height - 10);
	}
	
	public void move(){
		if(yStartingPos == 3 || yStartingPos == 1){
			trafficRectangle.x += speed;
		}else{
			trafficRectangle.x -= speed;
		}
	}
	

	
	public boolean hasReachedEnd(Traffic traffic){
		if(traffic.yStartingPos == 3 || traffic.yStartingPos == 1){
			if(trafficRectangle.x > FrogsterConstants.JPANEL_SIZE){
				return true;
			}else{
				return false;
			}
		}else{
			if(trafficRectangle.x + trafficRectangle.width < 0){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public int getYStartingPos(){
		return yStartingPos;
	}
	
	public boolean hasHitFrog(Rectangle frogRectangle){
		if(trafficRectangle.intersects(frogRectangle)){
			return true;
		}else{
			return false;
		}
	}
	
}
