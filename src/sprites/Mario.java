package sprites;

public class Mario extends Sprite implements Runnable{

	private boolean walking, running; 
	private Animation walkR, runR, walkL, runL;
	private String[] walkFramesR, runFramesR, walkFramesL, runFramesL;
	private static final int GRAVITY_CONSTANT = 2;
	private float yVelocity;
	private int numJumps;
	
	public Mario(float x, float y, double width, double height, String img) {
		super(x, y, width, height, img);
		this.walking = false;
		this.running = false;
		this.walkFramesR = new String[]{"sprites/Mario/mario-right.png", "sprites/Mario/mario-walkR.png"};
		this.walkFramesL = new String[]{"sprites/Mario/mario-left.png", "sprites/Mario/mario-walkL.png"};
		this.walkR = new Animation((Sprite)this, walkFramesR, "sprites/Mario/mario-right.png");
		this.walkL = new Animation((Sprite)this, walkFramesL, "sprites/Mario/mario-left.png");
		this.runFramesR = new String[] {"sprites/Mario/mario-runR1.png",
				"sprites/Mario/mario-runR2.png",
				"sprites/Mario/mario-runR3.png",
				"sprites/Mario/mario-runR4.png",
				"sprites/Mario/mario-runR5.png",
				"sprites/Mario/mario-runR6.png"};
		this.runFramesL = new String[] {"sprites/Mario/mario-runL1.png",
				"sprites/Mario/mario-runL2.png",
				"sprites/Mario/mario-runL3.png",
				"sprites/Mario/mario-runL4.png",
				"sprites/Mario/mario-runL5.png",
				"sprites/Mario/mario-runL6.png"};
		this.runR = new Animation((Sprite)this, runFramesR, "sprites/Mario/mario-right.png");
		this.runL = new Animation((Sprite)this, runFramesL, "sprites/Mario/mario-left.png");
		this.numJumps = 0;
	}
	
	public void jump(int velocity) {
		numJumps++;
		if(numJumps <= 2)
			this.yVelocity += velocity;
	}
	public void endjump(int velocity) {
		
			this.yVelocity += velocity;
	}
	
	public void moveY() { //constantly running
		if(yVelocity != 0 || this.gety() != this.getGround()) { //if moving
			this.sety(this.gety() - yVelocity);
			gravity();
		}
	}
	private void gravity() {
		if(this.gety() + this.getheight() <= this.getGround()) //if on or above ground
			yVelocity -= GRAVITY_CONSTANT;
		else if(this.gety() + this.getheight() > this.getGround()) {
			this.sety((float)(this.getGround() - this.getheight()));
			numJumps = 0;
			yVelocity = 0;
		}
	}
	
	public boolean bounceOffTop(int startx, int endx, int y) {
		float mid = (float)(this.getx() + this.getwidth()/2);

		if((mid >= startx) && mid <= endx) {
			if(this.gety() >= y + 40  && this.gety() <= y + 60) {
				yVelocity = Math.abs(yVelocity)*-1;
				return true;
			}
		}	
		return false;

	}
	
	public boolean isWalking() {
		return walking;
	}
	public boolean isRunning() {
		return running;
	}
	public void setWalking(boolean move) {
		walking = move;
	}
	public void setRunning(boolean move) {
		running = move;
	}

	
	//ANIMATION METHODS
	public void walkingAnim() {
		if(this.getDirection() == 1) {
			walkR.nextFrame();
		}
		else if(this.getDirection() == 2)
			walkL.nextFrame();
	}
	
	public void runningAnim() {
		if(this.getDirection() == 1)
			runR.nextFrame();
		else if(this.getDirection() == 2)
			runL.nextFrame();
	}
	
	public void endAnim() {
		walkR.resetFrame();
		walkL.resetFrame();
		runR.resetFrame();
		runL.resetFrame();
		
		if(this.getDirection() == 1) {
			this.setimg(walkR.getDefault());
		}
		else if(this.getDirection() == 2) {
			this.setimg(walkL.getDefault());
		}
	}
	
	public void pipeAnim() {
		this.setGround(400);
		this.setimg("sprites/Mario/mario-hoorah.png");
	}
	
	//INTERSECTION METHODS
	public boolean isOnPipe(Pipe p) {
		float bottom = (float)(this.gety() + this.getwidth());	
		if(this.getx() + this.getwidth()/2 >= p.getx() && this.getx() + this.getwidth()/2 <= p.getx() + p.getwidth()) {
			if(bottom >= p.gety() - 10 && bottom <= p.gety() + 10) {
				return true;	
			}
		}		
			return false;
	}
	
	public boolean isOnGoomba(Goomba g) {
		float bottom = (float)(this.gety() + this.getwidth());	
		if(this.getx() >= g.getx() && this.getx() + this.getwidth()/2 <= p.getx() + p.getwidth()) {
			if(bottom >= g.gety() - 10 && bottom <= g.gety() + 10) {
				return true;	
			}
		}		
			return false;
	}

	@Override
	public void run() {
		System.out.println("Mario is running...");
		// TODO Auto-generated method stub
		
	}
	public float getYVelocity() {
		return this.yVelocity;
	}

}
