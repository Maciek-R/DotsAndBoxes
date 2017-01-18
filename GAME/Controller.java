package GAME;

import static Constans.Constans.GRID;
import static Constans.Constans.GRID_WIDTH;
import static Constans.Constans.START_X;
import static Constans.Constans.START_Y;

import java.util.Random;
import java.util.Scanner;

import GAME.Controller.TURN;
import GAME.Line.CHOSEN;

public class Controller {

	public enum TURN {PLAYER_1, PLAYER_2};
	private TURN turn = TURN.PLAYER_1;
	private Dot Dots[][];
	private State state;
	
	int tryb_gry = 1;			//0 - gracz-gracz, 1-gracz-komputer, 2 - komp-komp
	
	int movesToEnd = (GRID)*(GRID+1)*2;
	boolean gameOver = false;
	
	public Controller(int tryb){
		Dots = new Dot[GRID+1][GRID+1];
		setDots(Dots);
		
		this.tryb_gry = tryb;
		state = new State();
	}
	
	private void setDots(Dot dots[][]){
		for(int i=0; i<dots.length; ++i){
			for(int j=0; j<dots[i].length; ++j){
				dots[i][j] = new Dot(j*GRID_WIDTH + START_X, i*GRID_WIDTH + START_Y);
			}
		}
	}
	
	public boolean playerMove(Line line){
		
		/*State s = new State(state);
		System.out.println();
		state = new State(s);
		*/
		if(tryb_gry == 0){
			
				boolean anotherMove;
				
				if(turn == TURN.PLAYER_1){
					line.setSelected(GAME.Line.CHOSEN.PLAYER_1);
					state.markLine(line);
					
					anotherMove = state.checkFullSquare(line, turn);
					
					movesToEnd--;
					if(movesToEnd==0){
						gameOver=true;
					}
					
					if(anotherMove){
						return true;
					}
					else{
						turn = TURN.PLAYER_2;
						return false;
					}
				}
				else{
					line.setSelected(GAME.Line.CHOSEN.PLAYER_2);
					state.markLine(line);
					
					anotherMove = state.checkFullSquare(line, turn);
							
					movesToEnd--;
					if(movesToEnd==0){
						gameOver=true;
					}
					
					if(anotherMove){
						return true;
					}
					else{
						turn = TURN.PLAYER_1;
						return false;
					}
						//turn = TURN.PLAYER_1;
				}
				
				
		}
		else if(tryb_gry == 1){
			
			boolean anotherMove;
			
				line.setSelected(GAME.Line.CHOSEN.PLAYER_1);
				state.markLine(line);
				
				anotherMove = state.checkFullSquare(line, turn);
				
				movesToEnd--;
				if(movesToEnd==0){
					gameOver=true;
				}
				
				if(anotherMove){
					return true;
				}
				else{
					turn = TURN.PLAYER_2;
					return false;
				}
			
			
		}
		return false;
	}
	
	public boolean compMove(){
		if(turn == TURN.PLAYER_2){
					
			int x = Utils.minmax_alfa_beta(state, turn, 6, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		//	int x = Utils.minmax(state, turn, 6, 0);
			
			Line line = chooseLine(x);
			
			movesToEnd--;
			if(movesToEnd==0){
				gameOver=true;
			}
			
			if(line!=null){
				boolean anotherMove = false;
				
				line.setSelected(CHOSEN.PLAYER_2);
				state.markLine(line);
				
				anotherMove = state.checkFullSquare(line, turn);
				
				if(anotherMove) return true;
				else{
					turn = TURN.PLAYER_1;
					return false;
				}
			}
				
		}
		else{	//TURN.PLAYER_1
			
			int x = Utils.minmax_alfa_beta(state, turn, 5, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
			//int x = Utils.minmax(state, turn, 6, 0);
			
			Line line = chooseLine(x);
			
			movesToEnd--;
			if(movesToEnd==0){
				gameOver=true;
			}
			
			if(line!=null){
				boolean anotherMove = false;
				
				line.setSelected(CHOSEN.PLAYER_1);
				state.markLine(line);
				
				anotherMove = state.checkFullSquare(line, turn);
				
				if(anotherMove) return true;
				else{
					turn = TURN.PLAYER_2;
					return false;
				}
			}
			
		}
		
		
		return false;
	}
	
	private Line chooseLine(int nr){
		int index=0;
		//boolean flag = false;
		Line[][] verLines = state.getVerLines();
		Line[][] horLines = state.getHorLines();
		
		for(int i=0; i<verLines.length; ++i){			
			for(int j=0; j<verLines[i].length; ++j){
				
				if(!verLines[i][j].getSelectedByPlayer())	{
					if(nr == index)
						return verLines[i][j];
					
					++index;
				}				
			}
		}
		
		
		
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				if(!horLines[i][j].getSelectedByPlayer()){
				
					if(nr == index)
						return horLines[i][j];
					
					++index;
				}
			}
		}
		return null;
	}
	
	public State getState(){
		return state;
	}
	public TURN getTurn(){
		return turn;
	}
	public Dot[][] getDots(){
		return Dots;
	}
	
	
}
