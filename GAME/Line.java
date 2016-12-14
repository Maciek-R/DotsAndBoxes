package GAME;

import Constans.Constans;

public class Line {

public enum CHOSEN {NOONE, PLAYER_1, PLAYER_2, SELECTED};		//selected=mouse indicates on it
public enum DIR{POZIOM, PION};	

	private int pos_x;
	private int pos_y;
	private DIR dir;	
	private CHOSEN selected;
	private int row;//wiersz
	private int column;//kolumna
	
	public Line(int x, int y, DIR dir, int w, int k){
		pos_x = x;
		pos_y = y;
		this.dir = dir;
		selected = CHOSEN.NOONE;
		this.row = w;
		this.column = k;
	}
	public Line(Line l){
		pos_x = l.getPos_x();
		pos_y = l.getPos_y();
		dir = l.getDir();
		selected = l.getSelected();
		row = l.getRow();
		column = l.getColumn();
	}
	
	public Line checkCollisionWith(int X, int Y){
		
		if(selected == CHOSEN.PLAYER_1 || selected == CHOSEN.PLAYER_2) return null;
		
		if(dir == DIR.POZIOM)
		if(X>=pos_x && X<pos_x+Constans.GRID_WIDTH-Dot.DOT_WIDTH)
			if(Y>=pos_y && (Y < pos_y + Dot.DOT_WIDTH)){
				return this;
			}	
		
		if(dir==DIR.PION)
		if(Y>=pos_y && Y < pos_y + Constans.GRID_WIDTH-Dot.DOT_WIDTH){
			if(X>=pos_x && X<pos_x+Dot.DOT_WIDTH){
				return this;
			}
		}
		
		return null;
	}
	
	public int getPos_x() {
		return pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}
	public void setSelected(CHOSEN selected){
		this.selected = selected;
	}
	public CHOSEN getSelected(){
		return selected;
	}
	public boolean getSelectedByPlayer(){
		if(selected == CHOSEN.PLAYER_1 || selected == CHOSEN.PLAYER_2){
			return true;
		}
		return false;
	}
	public DIR getDir(){
		return dir;
	}
	public void setDir(DIR dir){
		this.dir = dir;
	}
	public boolean equals(Line line){
		if(row == line.getRow() && column == line.getColumn() && dir == line.getDir()) return true;
		return false;
	}
	public int getRow(){
		return row;
	}
	public int getColumn(){
		return column;
	}
}
