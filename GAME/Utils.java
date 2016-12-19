package GAME;

import java.util.Vector;

import GAME.Controller.TURN;
import GAME.Line.CHOSEN;

public class Utils{

	public static int minmax(State state, TURN turn, int g, int root) throws Exception{		//root == 0 czyli korzen
		
		Line[][] verLines = state.getVerLines();
		Line[][] horLines = state.getHorLines();
		
		Vector<Line> lines = new Vector<>();
		
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
		
		if(g<=0) return state.h();
		
		if(lines.size()==0) return state.h();
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		Vector<Integer> values = new Vector<>();
		
		int x = 0;
		boolean m;
		
		int i=0;
		int index = 0;
		for(Line line:lines){
			State s = new State(state);
			Line l = new Line(line);
			//m = s.move(l, turn);	
			
			if(turn == TURN.PLAYER_1)
				l.setSelected(CHOSEN.PLAYER_1);
			else
				l.setSelected(CHOSEN.PLAYER_2);
			
			s.markLine(l);
			m = s.checkFullSquare(l, turn);
			
			
			
			
			
			//if(!m) System.out.println("asdasfsfhsdfdsf");
			
			
			if(m && (turn == TURN.PLAYER_1))
				x = minmax(s, TURN.PLAYER_1, g-1, root+1);
			else if(!m && (turn == TURN.PLAYER_1))
				x = minmax(s, TURN.PLAYER_2, g-1, root+1);
			else if(m && (turn == TURN.PLAYER_2))				
				x = minmax(s, TURN.PLAYER_2, g-1, root+1);
			else if(!m && (turn == TURN.PLAYER_2))
				x = minmax(s, TURN.PLAYER_1, g-1, root+1);
			else{
				throw new Exception();
			}
			
			values.addElement(new Integer(x));
			
			if(turn == TURN.PLAYER_1){
				if(x > max){
					max = x;
					index = i;
				//	System.out.println("aaa");
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
		
		
		
		if(root==0) {
			System.out.println("Poziom "+g);
			
			for(Integer q:values){
				System.out.println("   "+q);
			}
			
			/*for(int q=0; q<verLines.length; ++q){			
				for(int w=0; w<verLines[q].length; ++w){
					if(!verLines[q][w].getSelectedByPlayer())	//wybieranie lini niewybranych przez graczy
						System.out.println("aaa");
				}
			}
			for(int q=0; q<horLines.length; ++q){
				for(int w=0; w<horLines[q].length; ++w){
					if(!horLines[q][w].getSelectedByPlayer())
						System.out.println("aaa");
				}
			}*/
			
			
			return index;		//zwraca numer linii ktora trzeba wybrac
		}
		
		if(turn == TURN.PLAYER_1)
			return max;
		else
			return min;
		
	}
};