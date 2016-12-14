package GAME;

import java.util.Vector;

import GAME.Controller.TURN;
import GAME.Line.CHOSEN;

public class Utils{

	public static int minmax(State state, TURN turn, int g, int root) {		//root == 0 czyli korzen
		
		Line[][] verLines = state.getVerLines();
		Line[][] horLines = state.getHorLines();
		
		Vector<Line> lines = new Vector<>();		//przechowuje referencje do linii niewybranych
		
		for(int i=0; i<verLines.length; ++i){			
			for(int j=0; j<verLines[i].length; ++j){
				if(!verLines[i][j].getSelectedByPlayer())	//wybieranie lini niewybranych przez graczy
					lines.add((verLines[i][j]));
			}
		}
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				if(!horLines[i][j].getSelectedByPlayer())
					lines.add((horLines[i][j]));
			}
		}
		
		if(g<=0) return state.h();			//zwraca wartosc stanu gdy nie moze przeszukiwac dalej(glebokosc za duza)
		
		if(lines.size()==0) return state.h();	//podobnie, gdy nie ma juz wiecej ruchow do wyboru mimo glebokosci
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		int x = 0;
		boolean m;
		
		int i=0;
		int index = 0;
		for(Line line:lines){
			State s = new State(state);
			Line l = new Line(line);
			
			if(turn == TURN.PLAYER_1)
				l.setSelected(CHOSEN.PLAYER_1);
			else
				l.setSelected(CHOSEN.PLAYER_2);
			
			s.markLine(l);
			m = s.checkFullSquare(l, turn);
			
			
			if(m && (turn == TURN.PLAYER_1))
				x = minmax(s, TURN.PLAYER_1, g-1, root+1);
			else if(!m && (turn == TURN.PLAYER_1))
				x = minmax(s, TURN.PLAYER_2, g-1, root+1);
			else if(m && (turn == TURN.PLAYER_2))				
				x = minmax(s, TURN.PLAYER_2, g-1, root+1);
			else if(!m && (turn == TURN.PLAYER_2))
				x = minmax(s, TURN.PLAYER_1, g-1, root+1);
			
			
			if(turn == TURN.PLAYER_1){
				if(x > max){
					max = x;
					index = i;
				}
				
			}
			else{
				if(x < min){
					min = x;
					index = i;
				}
			}
			
			++i;
		}
		
		
		
		if(root==0) {			//gdy jest to korzen, to zwraca numer niewybranej linii
			
			return index;		
		}
		
		if(turn == TURN.PLAYER_1)
			return max;
		else
			return min;
		
	}
};