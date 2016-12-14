package GAME;


import GAME.Controller.TURN;
import GAME.Line.CHOSEN;

public class Square {

	public enum ID{NOONE, PLAYER_1, PLAYER_2};
	
	private Line LineUp    = null;
	private Line LineDown  = null;
	private Line LineLeft  = null;
	private Line LineRight = null;
	
	private ID id;	
	
	public Square(Line up, Line down, Line left, Line right){
		this.LineUp    = up;
		this.LineDown  = down;
		this.LineLeft  = left;
		this.LineRight = right;
		id = ID.NOONE;
	}
	
	/*public Square checkCollisionWith(int X, int Y){
		
		if(Y > LineUp.getPos_y() +Dot.DOT_WIDTH && Y < LineDown.getPos_y()){
			if(X > LineLeft.getPos_x()+Dot.DOT_WIDTH && X < LineRight.getPos_x()){
				return this;
			}
		}
		
		return null;	
	}*/
	
	public boolean checkFull(){
		
		if(id!=ID.NOONE) return false;
		
		if ((LineUp.getSelected() == CHOSEN.PLAYER_1 || LineUp.getSelected() == CHOSEN.PLAYER_2) &&
			(LineDown.getSelected() == CHOSEN.PLAYER_1 || LineDown.getSelected() == CHOSEN.PLAYER_2) &&
			(LineLeft.getSelected() == CHOSEN.PLAYER_1 || LineLeft.getSelected() == CHOSEN.PLAYER_2)  &&
			(LineRight.getSelected() == CHOSEN.PLAYER_1 || LineRight.getSelected() == CHOSEN.PLAYER_2)){
			
			return true;
		}
			
		return false;

	}
	
	public void setToPaint(TURN turn){
		if(turn == TURN.PLAYER_1){
			id = ID.PLAYER_1;
		}
		else{
			id = ID.PLAYER_2;
		}
	}
	
	public Line getLineUp() {
		return LineUp;
	}
	public Line getLineDown() {
		return LineDown;
	}
	public Line getLineLeft() {
		return LineLeft;
	}
	public Line getLineRight() {
		return LineRight;
	}
	public void setLineUp(Line up){
		this.LineUp = up;
	}
	public void setLineDown(Line down){
		this.LineDown = down;
	}
	public void setLineLeft(Line left){
		this.LineLeft = left;
	}
	public void setLineRight(Line right){
		this.LineRight = right;
	}
	public ID getID(){
		return id;
	}
	public void setID(ID id){
		this.id = id;
	}
}
