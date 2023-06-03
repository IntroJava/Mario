package display;
import java.util.ArrayList;

import processing.core.PApplet;
import sprites.Ground;
import sprites.Pipe;

public class Level1_1 implements Runnable{

	double x,y,width,height;
	private ArrayList<Ground> ground;
	private ArrayList<Integer[]> groundCombos; //startx,endx,y
	private double groundLevel; //NEW CODE
	private int relSpace, direction;
	private Pipe pipe; 
	private boolean levelComplete;
	
	public Level1_1(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.groundLevel = height - 50;
		this.relSpace = 500; //edge of the camera, starting
		this.direction = 1;
		this.groundCombos = new ArrayList<Integer[]>();  //startx,endx,y	
		this.ground = new ArrayList<Ground>();
		this.pipe = new Pipe(940,170,70,80,"sprites/pipe.png");
		this.levelComplete = false;
		
		groundCombos.add(new Integer[]{0,950,250});
		groundCombos.add(new Integer[]{400,600,100});

		for(Integer[] combo: groundCombos) {
			for(int a= combo[0]; a< combo[1]; a+=50) {
				ground.add(new Ground(a,combo[2],50,50,"sprites/ground.png"));
			}
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
				for(Integer[] combo: groundCombos) {
					combo[0] -= scrollSpeed;
					combo[1] -= scrollSpeed;
				}
				pipe.setx(pipe.getx() - scrollSpeed);
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
		pipe.draw(papp);
	}
	
	public void setScrollDir(int direction) {
		this.direction = direction;
	}
	
	public ArrayList<Ground> getGround(){
		return ground;
	}
	public ArrayList<Integer[]> getCombos(){
		return groundCombos;
	}
	public Pipe getPipe() {
		return pipe;
	}
	public boolean getLevelStat() {
		return levelComplete;
	}
	public void setLevelStat(boolean b) {
		levelComplete = b;
	}

	@Override
	public void run() {
		System.out.println("Level 1_1 is running...");
		// TODO Auto-generated method stub
		
	}
}
