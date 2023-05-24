package sprites;
public class Animation {

	private String[] frames;
	private Sprite s;
	private int frame;
	private String defaultFrame;
	
	public Animation(Sprite s, String[] frames, String defaultFrame) {
		this.s = s;
		this.frames = frames;
		this.frame = 0;
		this.defaultFrame = defaultFrame;
	}
	
	public String getFrame() {
		return frames[frame];
	}
	
	public int getFrameNum() {
		return frame;
	}
	
	public void nextFrame() {
		if(frame > frames.length-1 || frame == -1) {
			frame = 0;
		}
		else {
			s.setimg(frames[frame]);
			frame++;
		}
		
	}
	
	public void resetFrame() {
		s.setimg(defaultFrame);
		frame = -1;
	}
	
	public void setDefault(String frame) {
		this.defaultFrame = frame;
	}
	
	public String getDefault() {
		return defaultFrame;
	}
	
}
