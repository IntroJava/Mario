package display;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import sprites.Ground;
import sprites.Mario;

public class DrawingSurface extends PApplet{
	
	private Level1_1 level1_1;
	private HashMap<String, Integer> keysPressed; //NEW CODE
	private ArrayList<Ground> ground;
	private ArrayList<Integer[]> groundCombos;
	
	private Mario mario;
	private double groundLevel; //NEW CODE
	private boolean scrolling;
	
	private Thread level, mar;
	
	//INITIALIZE ALL VARIABLES
	public DrawingSurface() {
		level1_1 = new Level1_1(0,0,1000,300);
		keysPressed = new HashMap<String, Integer>();
		level = new Thread(level1_1);
		this.groundLevel = level1_1.height - 50;
		this.scrolling = false;
		
		ground = level1_1.getGround();
		groundCombos = level1_1.getCombos();
		mario = new Mario(0,(float)(groundLevel - 75),50,75,"sprites/Mario/mario-right.png");
		mar = new Thread(mario);
		
		level.start();
		mar.start();
	}
	
	//FRAMES
	public void setup() {
		frameRate(30);
	}
	public void mario() {
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
		
		for(Integer[] g: groundCombos) {
			if(mario.isOnPlatform(g[0],g[1],g[2])) {
				groundLevel = g[2];
//				System.out.println("Ground: " + groundLevel);
			}
			else {
				if(mario.isOnPipe(level1_1.getPipe()) && !level1_1.getLevelStat()) {
					mario.setGround(level1_1.getPipe().gety());
					level1_1.setLevelStat(true);

					try {
						mar.sleep(250);	
					} catch (InterruptedException e) {
						System.out.println("Err: could not sleep");
						e.printStackTrace();
					}
					mario.endjump(15);
					mario.pipeAnim();	
				}
				else if (!level1_1.getLevelStat()){
					mario.setGround((float)(level1_1.height - 50));
					groundLevel = mario.getGround();
				}	
			}
			mario.bounceOffTop(g[0], g[1], g[2]);
		}
		
		mario.moveY();
		mario.draw(this);
	}
	public void update() {
//		System.out.print("Running?: " + game.getMario().isRunning());
//		System.out.print(" Walking?: " + game.getMario().isWalking());

		if(keysPressed.containsKey("Right")) {
			if(keysPressed.containsKey("Shift")){
				mario.setRunning(true);
				setScrolling( level1_1.scroll(mario.getx(), 10) ) ;
			}
			else {
				mario.setWalking(true);
				setScrolling( level1_1.scroll(mario.getx(), 5) ) ;
			}
		}
		if(keysPressed.containsKey("Left")) {
			
			if(keysPressed.containsKey("Shift")){
				mario.setRunning(true);
				setScrolling( level1_1.scroll(mario.getx(), -10) ) ;
			}
			else {
				mario.setWalking(true);
				setScrolling( level1_1.scroll(mario.getx(), -5) ) ;
			}
		}
	}

	//DRAW ALL COMPONENTS HERE
	public void draw() {
		level1_1.draw(this);
		update();
		mario();
	}
	
	//USER INTERACTION: NEW CODE
	public void keyPressed() {
//		System.out.println(keysPressed.keySet());
		if(keyCode == KeyEvent.VK_SHIFT) {
			keysPressed.put("Shift", 1);
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			keysPressed.put("Space", 1);
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			keysPressed.put("Right", 1);
			mario.setDirection(1);
			level1_1.setScrollDir(1);	
		}
		if(keyCode == KeyEvent.VK_LEFT) {
			keysPressed.put("Left", 1);
			mario.setDirection(2);
			level1_1.setScrollDir(2);	
		}	
	}
	
	public void keyReleased() {
		if(keyCode == KeyEvent.VK_RIGHT) {
			keysPressed.remove("Right", 1);
			mario.setWalking(false);
			mario.endAnim();
		}
		if(keyCode == KeyEvent.VK_LEFT) {
			keysPressed.remove("Left", 1);
			mario.setWalking(false);
			mario.endAnim();
		}
		if(keyCode == KeyEvent.VK_SHIFT) {
			keysPressed.remove("Shift", 1);
			mario.setRunning(false);
			mario.endAnim();
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			mario.jump(20);
			keysPressed.remove("Space", 1);
		}
		
	}
	
	public void setScrolling(boolean scroll) {
		scrolling = scroll;
	}
}
