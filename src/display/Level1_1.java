package display;
import java.util.ArrayList;

import processing.core.PApplet;
import sprites.Ground;

public class Level1_1 {

	double x,y,width,height;
	private ArrayList<Ground> ground;
	private double groundLevel; //NEW CODE
	private int relSpace, direction;
	
	public Level1_1(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.groundLevel = height - 50;
		this.relSpace = 500; //edge of the camera, starting
		this.direction = 1;
		
		ground = new ArrayList<Ground>();
		
		for(int i= 0; i<20; i++) {
			ground.add(new Ground(i*50, 250, 50,50,"sprites/ground.png"));
		}
		for(int j=0; j<3; j++) {
			ground.add(new Ground(j*50 + 400, 120, 50, 50, "sprites/ground.png"));
		}
	}
	
	public boolean scroll(double marioLoc, int scrollSpeed) {
//		System.out.println(relSpace);
		if(marioLoc >= 200  && marioLoc<= 250) { //mario is in middle
			if((direction == 1 && relSpace <= width) || (direction == 2 && relSpace >= 500)) {
				relSpace += scrollSpeed;
				for(Ground g: ground) {
					g.setx(g.getx() - scrollSpeed);
				}
				return true;
			}
		}
		return false;
		
	}
	
	public void draw(PApplet papp){
		papp.background(38,181,234);
	
		for(Ground g: ground) {
			g.draw(papp);
		}

	}
	
	public void setScrollDir(int direction) {
		this.direction = direction;
	}
	
}
