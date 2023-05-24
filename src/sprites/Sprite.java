package sprites;

import processing.core.PApplet;

public class Sprite {

	private float x, y;
	private double width, height;
	private String img;
	private float groundLevel;
	private int direction; //0 = stopped 1 = right 2 = left
	
	private static float windowWidth, windowHeight;

	public Sprite(float x, float y, double width, double height, String img){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.img = img;
		this.groundLevel = 250;
		this.direction = 1;
	}

	//CHANGE THIS DRAW METHOD ON CAYDENS
	public void draw(PApplet papp) {
		papp.image(papp.loadImage(img),  this.x, this.y, (float)this.getwidth(), (float)this.getheight());
	}
	
	//METHODS
	public boolean isIntersecting(Sprite s) { //check for intersection between two sprites
		return false;
		
	}

	public boolean isOnPlatform(float x1, float y1, int length) {
		float spriteBottom = (float)(this.y + this.height);
		if(this.x + this.width >= x1 && this.x + this.width <= x1 + length) {
			if(spriteBottom <= y1) {
				return true;
			}
		}

		if(this.x >= x1 && this.x <= x1 + length) {
			if(spriteBottom <= y1) {
				return true;
			}
		}
	

		return false;
	}
	
	// GET METHODS
	public String getImage() {
		return img;
	}
	public String getType() {
		try {
			return img.substring(7, img.indexOf('.'));
		}
		catch(NullPointerException n) {
			System.out.println("type is null");
			return "no type";
		}
	}
	public double getwidth() {
		return width;
	}
	public double getheight() {
		return height;
	}
	public float getx() {
		return x;
	}
	public float gety() {
		return y;
	}
	public float getGround() {
		return groundLevel;
	}
	public float getWindowWidth() {
		return windowWidth;
	}
	public float getWindowHeight() {
		return windowHeight;
	}
	public int getDirection() {
		return direction;
	}
	
	// SET METHODS
	public void setimg(String img) {
		this.img = img;
	}
	public void setx(float x) {
		this.x = x;
	}
	public void sety(float y) {
		this.y = y;
	}
	public void setwidth(double width) {
		this.width = width;
	}
	public void setheight(double height) {
		this.height = height;
	}
	public void setGround(float ground) {
		groundLevel = ground;
	}
	public void setWindowWidth(float width) {
		windowWidth = width;
	}
	public void setWindowHeight(float height) {
		windowHeight = height;
	}
	public void setDirection(int d) {
		direction = d;
	}
}
