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
import java.util.Random;
import javax.swing.Timer;

import GAME.Dot.CHOSEN;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Painter;
import static Constans.Constans.*;



/**
 * 
 * @author Maciej Ruszczyk
 * 
 */


public class Board extends JPanel implements ActionListener{
	
	
	
	public enum TURN {PLAYER_1, PLAYER_2};
	TURN turn = TURN.PLAYER_1;
	//Player player;
	Dot Dots[][];
	Dot dot = null;
	
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
		//player = new Player();
		
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
		for(int i=0; i<Dots.length; ++i){
			for(int j=0; j<Dots[i].length; ++j){
				drawDot(g, Dots[i][j]);
			}
		}
	}
	private void drawDot(Graphics g, Dot dot){
		switch(dot.getSelected()){
			case NOONE:		g.setColor(Color.BLACK);   break;
			case PLAYER_1:  g.setColor(Color.BLUE);	   break;
			case PLAYER_2:  g.setColor(Color.RED);	   break;
			case SELECTED:  g.setColor(Color.GREEN);
				//if(turn == TURN.PLAYER_1)
					//g.setColor(Color.BLUE);
				//else
				//	g.setColor(Color.RED);
				break;
		}
		
		g.fillOval(dot.getPos_x(), dot.getPos_y(), Dot.DOT_WIDTH, Dot.DOT_WIDTH);
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
		
		g.fillOval(40, 0, Dot.DOT_WIDTH, Dot.DOT_WIDTH);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
			drawTurn(g);
			drawDots(g);
			//g.drawImage(Map.bg, 0, 0, null);
		
		repaint();
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
			
			for(int i=0; i<Dots.length; ++i){
				for(int j=0; j<Dots[i].length; ++j){
					dot = Dots[i][j].checkCollisionWith(X, Y);
					if(dot!=null) break;
				}
				if(dot!=null) break;
			}
			
			System.out.println(e.getX() + " " +e.getY());
			//repaint();
		}
		
	}
	class MouseListenerAdapter implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int X = e.getX();
			int Y = e.getY();
			
			if(dot!=null){
				if(turn == TURN.PLAYER_1){
					dot.setSelected(CHOSEN.PLAYER_1);
					turn = TURN.PLAYER_2;
				}
				else{
					dot.setSelected(CHOSEN.PLAYER_2);
					turn = TURN.PLAYER_1;
				}
				
				dot = null;
			}
			
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
