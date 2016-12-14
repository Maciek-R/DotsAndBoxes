package GAME;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;

import GAME.Line.DIR;

import javax.swing.JPanel;
import static Constans.Constans.*;



/**
 * 
 * @author Maciej Ruszczyk
 * 
 */


public class Board extends JPanel implements ActionListener{
	
	
	
	public enum TURN {PLAYER_1, PLAYER_2};
	TURN turn = TURN.PLAYER_1;
	Dot Dots[][];
	Line line = null;
	
	
	State state;
	/*Square squares[][];
	Line horLines[][];
	Line verLines[][];*/
	
	
	//Square square=null;
	int movesToEnd = (GRID)*(GRID+1)*2;
	boolean gameOver = false;
	
	Adapter adap;
	MouseAdapter MouseAdapter;
	MouseListenerAdapter MouseListenerAdapter;

	boolean GameOver = false;
	
	Timer timer;
	



	public Board(MyFrame frejm) {
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(BOARD_WIDTH_PIX, BOARD_HEIGHT_PIX));
		
		init();
		
	}
	private void init(){
		Dots = new Dot[GRID+1][GRID+1];
		setDots(Dots);
		
		
		state = new State();
		/*horLines = new Line[GRID+1][GRID];
		verLines = new Line[GRID][GRID+1];
		setLines(horLines, verLines);
		
		squares = new Square[GRID][GRID];
		setSquares(squares);*/
		
		adap = new Adapter();	
		this.addKeyListener(adap);
		MouseAdapter = new MouseAdapter();
		this.addMouseMotionListener(MouseAdapter);
		MouseListenerAdapter = new MouseListenerAdapter();
		this.addMouseListener(MouseListenerAdapter);
		
		timer = new Timer(1000, this);
		timer.start();
	}
	
	private void setDots(Dot dots[][]){
		for(int i=0; i<dots.length; ++i){
			for(int j=0; j<dots[i].length; ++j){
				dots[i][j] = new Dot(j*GRID_WIDTH + START_X, i*GRID_WIDTH + START_Y);
			}
		}
	}
		
	private void drawDots(Graphics g){
		g.setColor(Color.BLACK); 
		for(int i=0; i<Dots.length; ++i){
			for(int j=0; j<Dots[i].length; ++j){
				//drawDot(g, Dots[i][j]);
				Dot dot = Dots[i][j];
				g.fillRect(dot.getPos_x(), dot.getPos_y(), Dot.DOT_WIDTH, Dot.DOT_WIDTH);
			}
		}
	}
	private void drawTurn(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString("TURN: ", 0, 10);
		
		if(turn == TURN.PLAYER_1){
			g.setColor(Color.BLUE);
		}
		else{
			g.setColor(Color.RED);
		}
		
		g.fillOval(40, 0, 50, 50);
	}
	
	private void drawHorLines(Graphics g){
		
		Line horLines[][] = state.getHorLines();
		
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				//g.setColor(Color.BLUE);
				//g.setColor(Color.BLACK);
				switch(horLines[i][j].getSelected()){
				case NOONE:		continue;		
				case PLAYER_1:	g.setColor(Color.BLUE);			break;
				case PLAYER_2:	g.setColor(Color.RED);			break;
				}
				
				g.fillRect(horLines[i][j].getPos_x(), horLines[i][j].getPos_y(), GRID_WIDTH-Dot.DOT_WIDTH, Dot.DOT_WIDTH);
			}
		}
	}
	private void drawVerLines(Graphics g){
		
		Line verLines[][] = state.getVerLines();
		
		for(int i=0; i<verLines.length; ++i){
			for(int j=0; j<verLines[i].length; ++j){
				//g.setColor(Color.RED);
				//g.setColor(Color.BLACK);
				
				switch(verLines[i][j].getSelected()){
				case NOONE:		continue;		
				case PLAYER_1:	g.setColor(Color.BLUE);			break;
				case PLAYER_2:	g.setColor(Color.RED);			break;
				}
				
				g.fillRect(verLines[i][j].getPos_x(), verLines[i][j].getPos_y(), Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH);
			}
		}
	}
	
	private void drawSquares(Graphics g){
		
		Square squares[][] = state.getSquares();
		
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				
				if(squares[i][j].id == 0) continue;
				
				if(squares[i][j].id == 1){
					g.setColor(Color.BLUE);
				}
				else if(squares[i][j].id == 2){
					g.setColor(Color.RED);
				}
				g.fillRect(squares[i][j].getLineUp().getPos_x(), squares[i][j].getLineUp().getPos_y()+Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH);
				
			}
		}
	}
	private void drawLine(Graphics g){
		if(line!=null){
			g.setColor(Color.GREEN);
			
			if(line.getDir() == DIR.POZIOM){
				g.fillRect(line.getPos_x(), line.getPos_y(), GRID_WIDTH-Dot.DOT_WIDTH, Dot.DOT_WIDTH);
				//System.out.println("ta");
			}
				
			if(line.getDir() == DIR.PION)
				g.fillRect(line.getPos_x(), line.getPos_y(), Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH);
		}
	}

	private void drawGameOver(Graphics g){
		g.setColor(Color.RED);
		g.drawString("GAME OVER", 50, 60);
		
		Integer points_1=new Integer(0), points_2=new Integer(0);
		
		Square[][] squares = state.getSquares();
		
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				
				if(squares[i][j].id == 1) ++points_1;
				else if(squares[i][j].id == 2) ++points_2;
				
			}
		}
		
		g.setColor(Color.BLUE);
		g.drawString(points_1.toString(), 50, 70);
		g.setColor(Color.RED);
		g.drawString(points_2.toString(), 60, 70);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
			drawTurn(g);
			drawDots(g);
			
			
			drawHorLines(g);
			drawVerLines(g);
			
			drawLine(g);
			
			drawSquares(g);
			
			if(gameOver){
				drawGameOver(g);
			}
			
			/*if(square!=null){
				g.setColor(Color.GREEN);
				g.fillRect(square.getLineUp().getPos_x(), square.getLineUp().getPos_y(), GRID_WIDTH-Dot.DOT_WIDTH, Dot.DOT_WIDTH);
				g.fillRect(square.getLineDown().getPos_x(), square.getLineDown().getPos_y(), GRID_WIDTH-Dot.DOT_WIDTH, Dot.DOT_WIDTH);
				
				g.fillRect(square.getLineLeft().getPos_x(), square.getLineLeft().getPos_y(), Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH);
				g.fillRect(square.getLineRight().getPos_x(), square.getLineRight().getPos_y(), Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH);
			}*/
			
			
			//g.drawImage(Map.bg, 0, 0, null);
		
		//repaint();
}
	
	
	class Adapter implements KeyListener{
		
		
		public void keyPressed(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch(keyCode){
			
			case KeyEvent.VK_LEFT:  break;
			case KeyEvent.VK_RIGHT: break;
			case KeyEvent.VK_SPACE: break;
			case KeyEvent.VK_P:     break;
			
			case KeyEvent.VK_ESCAPE: 
				System.exit(0); 
				break;	
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch(keyCode){
			
			case KeyEvent.VK_LEFT:  break;
			case KeyEvent.VK_RIGHT: break;
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
		
			
			
		}	
	}
	
	
	
	
	class MouseAdapter implements MouseMotionListener{
		

		@Override
		public void mouseDragged(MouseEvent e) {
			
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			int X = e.getX();
			int Y = e.getY();
			
			line = null;
			
			Line[][] verLines = state.getVerLines();
			Line[][] horLines = state.getHorLines();
			
			for(int i=0;i<horLines.length; ++i){
				for(int j=0; j<horLines[i].length; ++j){
					line = horLines[i][j].checkCollisionWith(X, Y);
					
					if(line!=null) {break;}
				}
				if(line!=null) break;
			}
			
			if(line==null)
			for(int i=0;i<verLines.length; ++i){
				for(int j=0; j<verLines[i].length; ++j){
					line = verLines[i][j].checkCollisionWith(X, Y);
					
					if(line!=null) {break;}
				}
				if(line!=null) break;
			}
			
			
			//System.out.println(e.getX() + " " +e.getY());
			repaint();
		}
		
	}
	
		
	class MouseListenerAdapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int X = e.getX();
			int Y = e.getY();
			
			boolean anotherMove;
			
			if(line!=null){
				if(turn == TURN.PLAYER_1){
					line.setSelected(GAME.Line.CHOSEN.PLAYER_1);
					
					anotherMove = state.checkFullSquare(line, turn);
					
					if(!anotherMove)
						turn = TURN.PLAYER_2;
				}
				else{
					line.setSelected(GAME.Line.CHOSEN.PLAYER_2);
					
					anotherMove = state.checkFullSquare(line, turn);
					
					if(!anotherMove)
						turn = TURN.PLAYER_1;
				}
				
				line = null;
				
				movesToEnd--;
				if(movesToEnd==0){
					gameOver=true;
				}
			}
			
			repaint();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	
	

	
	
	
	
}
