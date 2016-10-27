package GAME;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
			EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				MyFrame frame = new MyFrame();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		});

	}

}

class MyFrame extends JFrame{
	
	
	public MyFrame(){
		
				setTitle("Dots And Boxes");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Board board = new Board(this);
				add(board);
				pack();
				
			
	}
	
}