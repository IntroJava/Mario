package display;
import java.util.ArrayList;
import processing.core.PApplet;
import sprites.Ground;
import sprites.Mario;

public class Game {

	double x,y,width,height;
	private Mario mario;
	private double groundLevel; //NEW CODE
	private boolean scrolling;
	
	public Game(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.groundLevel = height - 50;
		this.scrolling = false;
		
		mario = new Mario(0,(float)(groundLevel - 75),50,75,"sprites/Mario/mario-right.png");
	}
	
	public void draw(PApplet papp){
	
		if(mario.getx() <= 450 && mario.getx() >= 0) {
			if(mario.isRunning()) {
				mario.runningAnim();
				if(mario.getDirection() == 1 && !scrolling)
					mario.setx(mario.getx()+10);
				else if(mario.getDirection() == 2 && !scrolling)
					mario.setx(mario.getx()-10);
			}
			else if(mario.isWalking()) {
				mario.walkingAnim();
				if(mario.getDirection() == 1 && !scrolling)
					mario.setx(mario.getx()+2);
				else if(mario.getDirection() == 2 && !scrolling)
					mario.setx(mario.getx()-2);
			}	
		}
		else if(mario.getx() > 450) mario.setx(449);
		else if(mario.getx() < 0) mario.setx(1);
		
		mario.moveY();
		
		mario.draw(papp);
	}
	
	public void setScrolling(boolean scroll) {
		scrolling = scroll;
	}
	public Mario getMario() {
		return mario;
	}

	
}