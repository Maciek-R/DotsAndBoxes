package GAME;

public class Dot {
	
	private int pos_x;
	private int pos_y;
	
	public final static int DOT_WIDTH = 10;
	
	public Dot(int x, int y){
		pos_x = x;
		pos_y = y;
	}

	
	public int getPos_x() {
		return pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}
	
}
