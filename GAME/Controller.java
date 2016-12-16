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
	/*Square squares[][];
	Line horLines[][];
	Line verLines[][];*/
	
	int tryb_gry = 1;			//0 - gracz-gracz, 1-gracz-komputer, 2 - komp-komp
	
	//Square square=null;
	int movesToEnd = (GRID)*(GRID+1)*2;
	boolean gameOver = false;
	
	public Controller(){
		Dots = new Dot[GRID+1][GRID+1];
		setDots(Dots);
		
		
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
		
		
		if(tryb_gry == 0){
			
				boolean anotherMove;
				
				if(turn == TURN.PLAYER_1){
					line.setSelected(GAME.Line.CHOSEN.PLAYER_1);
					
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
			
			//Random random = new Random();
			
			/*boolean f = true;
			int x, y, h;
			Line line=null;
			int count=0;
			do{
				
				++count;
				if(count>1000000) break;
				f=true;
				x = random.nextInt(3);
				y = random.nextInt(3);
				h = random.nextInt(2);
				
				if(h==0){
					Line[][] horLines = state.getHorLines();
					if(!horLines[y][x].getSelectedByPlayer())
						{line = horLines[y][x]; break;}
				}
				else{
					Line[][] verLines = state.getVerLines();
					if(!verLines[y][x].getSelectedByPlayer())
						{line = verLines[y][x]; break;}
				}
				
			}while(f==true);*/
			
			int index=0;
			Line[][] verLines = state.getVerLines();
			Line[][] horLines = state.getHorLines();
			Line line=null;
			
			
			
			int x = 0;
			try {
				x = Utils.minmax(state, turn, 3, 0);
				System.out.print("X" + x+"   ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			index=0;
			boolean flag = false;
			
			//tu trzeba wybrac na podstawie x
			
			
			index=0;
			
			int q=0;
					
			
			for(int i=0; i<verLines.length; ++i){			
				for(int j=0; j<verLines[i].length; ++j){
					
					//System.out.println(verLines[i][j].getSelectedByPlayer());
					
					if(!verLines[i][j].getSelectedByPlayer())	{
						//System.out.println(index);
						if(x == index) {
						
							line = verLines[i][j];
							flag = true;
						}
						
						++index;
					}
						
					if(flag) break;
					
				}
				if(flag)break;
			}
			
			
			
			if(!flag){
				for(int i=0; i<horLines.length; ++i){
					for(int j=0; j<horLines[i].length; ++j){
						if(!horLines[i][j].getSelectedByPlayer()){
							//System.out.println("index: "+index);
							if(x == index) {
								line = horLines[i][j];
								flag = true;
							}
							++index;
						}
							
						
						
						if(flag) break;
					//	++index;
					}
					if(flag) break;
				}
			}
			//System.out.println("Q "+q);
			System.out.println(line.w +" "+ line.k);
			
			
			movesToEnd--;
			if(movesToEnd==0){
				gameOver=true;
			}
			
			boolean anotherMove = false;
			
			line.setSelected(CHOSEN.PLAYER_2);
			
			anotherMove = state.checkFullSquare(line, turn);
		
			//return false;
			
			if(anotherMove) return true;
			else{
				turn = TURN.PLAYER_1;
				return false;
			}
				
		}
		return false;
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
