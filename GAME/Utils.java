package GAME;

import java.util.Vector;

import GAME.Controller.TURN;

public class Utils{

	public static int minmax(State state, TURN turn){
		
		Line[][] verLines = state.getVerLines();
		Line[][] horLines = state.getHorLines();
		
		Vector<Line> lines = new Vector<>();
		
		for(int i=0; i<verLines.length; ++i){			
			for(int j=0; j<verLines[i].length; ++j){
				if(!verLines[i][j].getSelectedByPlayer())	//wybieranie lini niewybranych przez graczy
					lines.add(verLines[i][j]);
			}
		}
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				if(!horLines[i][j].getSelectedByPlayer())
					lines.add(horLines[i][j]);
			}
		}
		
		if(lines.size()==0) 
			return state.h();
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		
		/*if(turn == TURN.PLAYER_1){
		
			for(Line line:lines){
				State s = new State(state);
				boolean m = s.move(line, turn);			//return another move
			
				int x;
				
				if(m){
					x = minmax(s, TURN.PLAYER_1);
				}
				else{
					x = minmax(s, TURN.PLAYER_2);
				}
				
				min = Math.min(min, x);
			}
			
		}
		else{
			
		}*/
		int x = 0;
		
		for(Line line:lines){
			State s = new State(state);
			boolean m = s.move(line, turn);
			
			if(m && turn == TURN.PLAYER_1)
				x = minmax(s, TURN.PLAYER_1);
			else if(!m && turn == TURN.PLAYER_1)
				x = minmax(s, TURN.PLAYER_2);
			else if(m && turn == TURN.PLAYER_2)
					x = minmax(s, TURN.PLAYER_2);
			else if(!m && turn == TURN.PLAYER_2)
				x = minmax(s, TURN.PLAYER_1);
			
			
			if(turn == TURN.PLAYER_1){
				max = Math.max(max, x);
			}
			else{
				min = Math.min(min, x);
			}
			
		}
		if(turn == TURN.PLAYER_1)
			return max;
		else
			return min;
		
	}
};