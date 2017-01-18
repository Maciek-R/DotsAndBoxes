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

import Constans.Constans;
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
	
	boolean isRunningGame = false;
	int OptionMenu = 0;

	public Board(MyFrame frejm) {
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(BOARD_WIDTH_PIX, BOARD_HEIGHT_PIX));
		
	//	controller = new Controller();
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
		g.setColor(Color.BLACK);
		g.drawString("Press Any Key", 90, 70);
	}
	
	private void drawMenu(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString("GRACZ - GRACZ", Constans.BOARD_WIDTH_PIX/2-50, Constans.BOARD_HEIGHT_PIX/2-75);
		g.drawString("GRACZ - KOMPUTER", Constans.BOARD_WIDTH_PIX/2-60, Constans.BOARD_HEIGHT_PIX/2);
		g.drawString("KOMPUTER - KOMPUTER", Constans.BOARD_WIDTH_PIX/2-70, Constans.BOARD_HEIGHT_PIX/2+75);
		
		g.setColor(Color.RED);
		g.drawRect(Constans.BOARD_WIDTH_PIX/2-80, Constans.BOARD_HEIGHT_PIX/2-95, 150, 30);
		g.drawRect(Constans.BOARD_WIDTH_PIX/2-80, Constans.BOARD_HEIGHT_PIX/2-20, 150, 30);
		g.drawRect(Constans.BOARD_WIDTH_PIX/2-80, Constans.BOARD_HEIGHT_PIX/2+55, 160, 30);
		
		g.setColor(Color.GREEN);
		if(OptionMenu == 1){
			g.drawRect(Constans.BOARD_WIDTH_PIX/2-80, Constans.BOARD_HEIGHT_PIX/2-95, 150, 30);
		}
		else if(OptionMenu == 2){
			g.drawRect(Constans.BOARD_WIDTH_PIX/2-80, Constans.BOARD_HEIGHT_PIX/2-20, 150, 30);
		}else if(OptionMenu == 3){
			g.drawRect(Constans.BOARD_WIDTH_PIX/2-80, Constans.BOARD_HEIGHT_PIX/2+55, 160, 30);
		}
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		if(isRunningGame){
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
		else{
			drawMenu(g);
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
			
			
			if(isRunningGame){
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
			}
			else{
				if(X > BOARD_WIDTH_PIX/2-80 && X < ((BOARD_WIDTH_PIX/2-80) + 150) 
							&& Y > BOARD_HEIGHT_PIX/2-95 &&  Y < ((BOARD_HEIGHT_PIX/2-95) + 30))
					OptionMenu = 1;
				else if(X > BOARD_WIDTH_PIX/2-80 && X < ((BOARD_WIDTH_PIX/2-80) + 150) 
						&& Y > BOARD_HEIGHT_PIX/2-20 &&  Y < ((BOARD_HEIGHT_PIX/2-20) + 30)){
					OptionMenu = 2;
				}
				else if(X > BOARD_WIDTH_PIX/2-80 && X < ((BOARD_WIDTH_PIX/2-80) + 160) 
						&& Y > BOARD_HEIGHT_PIX/2+55 &&  Y < ((BOARD_HEIGHT_PIX/2+55) + 30)){
					OptionMenu = 3;
				}
				else{
					OptionMenu = 0;
				}
			}
			
			
			repaint();
		}
		
	}
	
		
	class MouseListenerAdapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int X = e.getX();
			int Y = e.getY();
		
			if(isRunningGame){
				if(controller.gameOver){
					isRunningGame = false;
				}
				
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
				
				
				
			}
			else{
				if(OptionMenu == 1){
					controller = new Controller(0);
				}
				else if(OptionMenu == 2){
					controller = new Controller(1);
				}
				else if(OptionMenu == 3){
					controller = new Controller(2);
				}
				else{
					
				}
				
				if(OptionMenu >=1 && OptionMenu <=3)
					isRunningGame = true;
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
