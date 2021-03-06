package GAME;


import static Constans.Constans.*;

import GAME.Controller.TURN;
import GAME.Line.CHOSEN;
import GAME.Line.DIR;
import GAME.Square.ID;

public class State {

	private Square squares[][];
	private Line horLines[][];
	private Line verLines[][];
	
	public State(){
		horLines = new Line[GRID+1][GRID];
		verLines = new Line[GRID][GRID+1];
		setLines(horLines, verLines);
		
		squares = new Square[GRID][GRID];
		setSquares(squares);
	}
	public State(State s){
		horLines = new Line[GRID+1][GRID];
		verLines = new Line[GRID][GRID+1];
		squares = new Square[GRID][GRID];
		
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				horLines[i][j] = new Line(s.getHorLines()[i][j]);
				//System.out.println(i + " "+j);
			}
		}
		for(int i=0; i<verLines.length; ++i){
			for(int j=0; j<verLines[i].length; ++j){
				verLines[i][j] = new Line(s.getVerLines()[i][j]);
			}
		}
		
		setSquares(squares);
		//sprawdzanie czy sa zajete przez graczy
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				squares[i][j].setID(s.getSquares()[i][j].getID());
			}
		}
		
		//
		
	}
	
	public Line[][] getHorLines(){
		return horLines;
	}
	public Line[][] getVerLines(){
		return verLines;
	}
	public Square[][] getSquares(){
		return squares;
	}
	
	public boolean checkFullSquare(Line line, TURN turn){
		
		boolean anotherMove = false;
		
		if(line.getDir() == DIR.POZIOM){
			for(int i=0; i<squares.length; ++i){
				for(int j=0; j<squares[i].length; ++j){
					if(squares[i][j].getLineUp().equals(line) || squares[i][j].getLineDown().equals(line) ){
						if(squares[i][j].checkFull()){
							squares[i][j].setToPaint(turn);
							anotherMove = true;
						}
						else{
							if(i-1>=0){
								if(squares[i-1][j].checkFull()){
									squares[i-1][j].setToPaint(turn);
									anotherMove = true;
								}
							}
							if(i+1<squares.length){
								if(squares[i+1][j].checkFull()){
									squares[i+1][j].setToPaint(turn);
									anotherMove = true;
								}
							}
						}
					}
				}
			}
		}
		else{
			for(int i=0; i<squares.length; ++i){
				for(int j=0; j<squares[i].length; ++j){
				//	System.out.println(i + " "+j);
					if(squares[i][j].getLineLeft().equals(line) || squares[i][j].getLineRight().equals(line)){
					//	System.out.println(i + " "+j);
						if(squares[i][j].checkFull()){
							squares[i][j].setToPaint(turn);
							anotherMove = true;
						}
						else{
							if(j-1>=0){
								if(squares[i][j-1].checkFull()){
									squares[i][j-1].setToPaint(turn);
									anotherMove = true;
								}
							}
							if(j+1<squares[i].length){
								if(squares[i][j+1].checkFull()){
									squares[i][j+1].setToPaint(turn);
									anotherMove = true;
								}
							}
						}
					}
				}
			}
		}
		
		
		return anotherMove;
	}

	public void markLine(Line line){
		if(line.getDir()==DIR.PION){
			verLines[line.getRow()][line.getColumn()].setSelected(line.getSelected());
			verLines[line.getRow()][line.getColumn()].setDir(DIR.PION);
		}
		else{
			horLines[line.getRow()][line.getColumn()].setSelected(line.getSelected());
			horLines[line.getRow()][line.getColumn()].setDir(DIR.POZIOM);
		}
	}
	
	
	private void setLines(Line horLines[][], Line verLines[][]){
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				horLines[i][j] = new Line(j*GRID_WIDTH + START_X+Dot.DOT_WIDTH, i*GRID_WIDTH + START_Y, DIR.POZIOM, i, j);
			}
		}
		for(int i=0; i<verLines.length; ++i){
			for(int j=0; j<verLines[i].length; ++j){
				verLines[i][j] = new Line(j*GRID_WIDTH + START_X, i*GRID_WIDTH+Dot.DOT_WIDTH + START_Y, DIR.PION, i, j);
			}
		}
	}
	
	private void setSquares(Square squares[][]){
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				squares[i][j] = new Square(horLines[i][j], horLines[i+1][j], verLines[i][j], verLines[i][j+1]);
			}
		}
	}
	
	public int h(){								//funkcja heurystyczna. Zwraca po prostu liczbe przewagi niebieskich kwadrat�w nad czerwonymi
		int blue=0;
		int red = 0;
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				if(squares[i][j].getID() == ID.PLAYER_1) blue+=10;
				else if(squares[i][j].getID() == ID.PLAYER_2) red+=10;
			}
		}
		
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				if(horLines[i][j].getSelected() == CHOSEN.PLAYER_1)
					blue++;
				else if(horLines[i][j].getSelected() == CHOSEN.PLAYER_2)
					red++;
			}
		}
		
		for(int i=0; i<verLines.length; ++i){
			for(int j=0; j<verLines[i].length; ++j){
				if(verLines[i][j].getSelected() == CHOSEN.PLAYER_1)
					blue++;
				else if(verLines[i][j].getSelected() == CHOSEN.PLAYER_2)
					red++;
			}
		}
		
		return blue-red;
	}
}
