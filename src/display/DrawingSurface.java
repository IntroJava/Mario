package display;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class DrawingSurface extends PApplet{
	
	private Game game;
	private Level1_1 level1_1;
	private HashMap<String, Integer> keysPressed; //NEW CODE
	
	//INITIALIZE ALL VARIABLES
	public DrawingSurface() {
		game = new Game(0,0,500,300);
		level1_1 = new Level1_1(0,0,1000,300);
		keysPressed = new HashMap<String, Integer>();
	}
	
	//FRAMES
	public void setup() {
		frameRate(30);
	}
	
	public void update() {
//		System.out.print("Running?: " + game.getMario().isRunning());
//		System.out.print(" Walking?: " + game.getMario().isWalking());
		
		if(keysPressed.containsKey("Right")) {
			if(keysPressed.containsKey("Shift")){
				game.getMario().setRunning(true);
				game.setScrolling( level1_1.scroll(game.getMario().getx(), 10) ) ;
			}
			else {
				game.getMario().setWalking(true);
				game.setScrolling( level1_1.scroll(game.getMario().getx(), 5) ) ;
			}
		}
		if(keysPressed.containsKey("Left")) {
			
			if(keysPressed.containsKey("Shift")){
				game.getMario().setRunning(true);
				game.setScrolling( level1_1.scroll(game.getMario().getx(), -10) ) ;
			}
			else {
				game.getMario().setWalking(true);
				game.setScrolling( level1_1.scroll(game.getMario().getx(), -5) ) ;
			}
		}

		
	}

	//DRAW ALL COMPONENTS HERE
	public void draw() {
		level1_1.draw(this);
		update();
		game.draw(this);
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
			game.getMario().setDirection(1);
			level1_1.setScrollDir(1);	
		}
		if(keyCode == KeyEvent.VK_LEFT) {
			keysPressed.put("Left", 1);
			game.getMario().setDirection(2);
			level1_1.setScrollDir(2);	
		}	
	}
	
	public void keyReleased() {
		if(keyCode == KeyEvent.VK_RIGHT) {
			keysPressed.remove("Right", 1);
			game.getMario().setWalking(false);
			game.getMario().endAnim();
		}
		if(keyCode == KeyEvent.VK_LEFT) {
			keysPressed.remove("Left", 1);
			game.getMario().setWalking(false);
			game.getMario().endAnim();
		}
		if(keyCode == KeyEvent.VK_SHIFT) {
			keysPressed.remove("Shift", 1);
			game.getMario().setRunning(false);
			game.getMario().endAnim();
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			game.getMario().jump(15);
			keysPressed.remove("Space", 1);
		}
		
	}
}
