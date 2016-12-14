package Constans;

import GAME.Dot;

public class Constans {

	public final static int GRID = 3;			//	n*n board, (n+1)*(n+1) dots
	
	public final static int BOARD_WIDTH_PIX = 1200;
	public final static int BOARD_HEIGHT_PIX = 800;
	
	public final static int GRID_WIDTH = 40;	//  in pixels
	
	public final static int START_X = BOARD_WIDTH_PIX/2 - (GRID*GRID_WIDTH+Dot.DOT_WIDTH)/2;
	public final static int START_Y = BOARD_HEIGHT_PIX/2 - (GRID*GRID_WIDTH+Dot.DOT_WIDTH)/2;
}
