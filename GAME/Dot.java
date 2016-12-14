package GAME;
import static Constans.Constans.*;

public class Dot {

	public enum CHOSEN {NOONE, PLAYER_1, PLAYER_2, SELECTED};		//selected=mouse indicates on it
	
	private int pos_x;
	private int pos_y;
	private CHOSEN selected;
	
	public final static int DOT_WIDTH = 10;
	
	public Dot(int x, int y){
		pos_x = x;
		pos_y = y;
		selected = CHOSEN.NOONE;
	}

	/*public Line checkCollisionWith(int X, int Y){
		
			if(X>=pos_x +DOT_WIDTH && X<pos_x+GRID_WIDTH)
				if(Y>=pos_y && Y<pos_y+DOT_WIDTH){
					selected = CHOSEN.SELECTED;
					return new Line(pos_x+DOT_WIDTH, pos_y, 0);
				}
		
			if(X>=pos_x && X<pos_x+DOT_WIDTH)
				if(Y>=pos_y + DOT_WIDTH && Y<pos_y+GRID_WIDTH){
					selected = CHOSEN.SELECTED;
					return new Line(pos_x, pos_y+DOT_WIDTH, 1);
				}
			
		
			return null;
		
		
		
	}*/
	
	public int getPos_x() {
		return pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}
	public CHOSEN getSelected(){
		return selected;
	}
	public void setSelected(CHOSEN chosen){
		selected = chosen;
	}
	
}
