package GAME;

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

	public Dot checkCollisionWith(int X, int Y){
		
		if(selected == CHOSEN.NOONE || selected == CHOSEN.SELECTED){
		
			if(X>=pos_x && X<pos_x+DOT_WIDTH)
				if(Y>=pos_y && Y<pos_y+DOT_WIDTH){
					selected = CHOSEN.SELECTED;
					return this;
				}
		
			selected = CHOSEN.NOONE;
			return null;
		}
		
		return null;
	}
	
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
