package Test;

import static org.junit.Assert.*;

import GAME.Controller.TURN;
import GAME.Line;
import GAME.Line.CHOSEN;
import GAME.Line.DIR;
import GAME.State;

public class Test {

	@org.junit.Test
	public void test() {
		State state = new State();
		Line line = new Line(0,0,DIR.POZIOM, 0, 0);
		//boolean m = state.move(line, TURN.PLAYER_2);
		line = new Line(0,0,DIR.POZIOM, 1, 0);
		//m = state.move(line, TURN.PLAYER_2);
		line = new Line(0,0,DIR.PION, 0, 0);
		//m = state.move(line, TURN.PLAYER_2);
		line = new Line(0,0,DIR.PION, 0, 1);
		//m = state.move(line, TURN.PLAYER_1);
		
		//boolean p = state.checkFullSquare(line, TURN.PLAYER_2);
	
		for(int i=0; i<state.getHorLines().length; ++i){
			for(int j=0; j<state.getHorLines()[i].length; ++j){
				System.out.println(state.getHorLines()[i][j].getSelected());
			}
		}
		System.out.println();
		for(int i=0; i<state.getVerLines().length; ++i){
			for(int j=0; j<state.getVerLines()[i].length; ++j){
				System.out.println(state.getVerLines()[i][j].getSelected());
			}
		}
	//	line.setSelected(CHOSEN.PLAYER_2);
		
		
		
		
		//System.out.println(p);
		
		System.out.println(state.h());
	}

}
