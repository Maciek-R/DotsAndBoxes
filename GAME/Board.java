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

import GAME.Controller.TURN;
import GAME.Line.DIR;
import GAME.Square.ID;

import javax.swing.JPanel;
import static Constans.Constans.*;



/**
 * 
 * @author Maciej Ruszczyk
 * 
 */


public class Board extends JPanel implements ActionListener{
	
	Controller controller;
	Line line = null;
	
	Adapter adap;
	MouseAdapter MouseAdapter;
	MouseListenerAdapter MouseListenerAdapter;
	
	Timer timer;	
	//boolean cpM=false;
	boolean plM = true;


	public Board(MyFrame frejm) {
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(BOARD_WIDTH_PIX, BOARD_HEIGHT_PIX));
		
		controller = new Controller();
		init();
		
	}
	private void init(){
		
		adap = new Adapter();	
		this.addKeyListener(adap);
		MouseAdapter = new MouseAdapter();
		this.addMouseMotionListener(MouseAdapter);
		MouseListenerAdapter = new MouseListenerAdapter();
		this.addMouseListener(MouseListenerAdapter);
		
		timer = new Timer(1000, this);
		timer.start();
	}
	
	
		
	private void drawDots(Graphics g){
		g.setColor(Color.BLACK); 
		for(int i=0; i<controller.getDots().length; ++i){
			for(int j=0; j<controller.getDots()[i].length; ++j){
				//drawDot(g, Dots[i][j]);
				Dot dot = controller.getDots()[i][j];
				g.fillRect(dot.getPos_x(), dot.getPos_y(), Dot.DOT_WIDTH, Dot.DOT_WIDTH);
			}
		}
	}
	private void drawTurn(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString("TURN: ", 0, 10);
		
		if(controller.getTurn() == TURN.PLAYER_1){
			g.setColor(Color.BLUE);
		}
		else{
			g.setColor(Color.RED);
		}
		
		g.fillOval(40, 0, 50, 50);
	}
	
	private void drawHorLines(Graphics g){
		
		Line horLines[][] = controller.getState().getHorLines();
		
		for(int i=0; i<horLines.length; ++i){
			for(int j=0; j<horLines[i].length; ++j){
				
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
		
		Line verLines[][] = controller.getState().getVerLines();
		
		for(int i=0; i<verLines.length; ++i){
			for(int j=0; j<verLines[i].length; ++j){
				
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
		
		Square squares[][] = controller.getState().getSquares();
		
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				
				if(squares[i][j].getID() == ID.NOONE) continue;
				
				if(squares[i][j].getID() == ID.PLAYER_1){
					g.setColor(Color.BLUE);
				}
				else if(squares[i][j].getID() == ID.PLAYER_2){
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
			}
				
			if(line.getDir() == DIR.PION)
				g.fillRect(line.getPos_x(), line.getPos_y(), Dot.DOT_WIDTH, GRID_WIDTH-Dot.DOT_WIDTH);
		}
	}

	private void drawGameOver(Graphics g){
		g.setColor(Color.RED);
		g.drawString("GAME OVER", 50, 60);
		
		Integer points_1=new Integer(0), points_2=new Integer(0);
		
		Square[][] squares = controller.getState().getSquares();
		
		for(int i=0; i<squares.length; ++i){
			for(int j=0; j<squares[i].length; ++j){
				
				if(squares[i][j].getID() == ID.PLAYER_1) ++points_1;
				else if(squares[i][j].getID() == ID.PLAYER_2) ++points_2;
				
			}
		}
		
		g.setColor(Color.BLUE);
		g.drawString(points_1.toString(), 50, 70);
		g.setColor(Color.RED);
		g.drawString(points_2.toString(), 70, 70);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
			drawTurn(g);
			drawDots(g);
			
			
			drawHorLines(g);
			drawVerLines(g);
			
			drawLine(g);
			
			drawSquares(g);
			
			if(controller.gameOver){
				drawGameOver(g);
			}
			
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
			
			Line[][] verLines = controller.getState().getVerLines();
			Line[][] horLines = controller.getState().getHorLines();
			
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
		
			
			if(controller.tryb_gry == 0){
				if(line!=null){
					controller.playerMove(line);
					line = null;
				}
			}
			else if(controller.tryb_gry == 1){
						
					
					if(plM){
						if(line!=null){	
							plM = controller.playerMove(line);
							line = null;
						}
					}	
					else{
						plM = !controller.compMove();
					}
				
			}
			else if(controller.tryb_gry == 2){
					
					controller.compMove();
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
