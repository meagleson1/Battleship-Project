package battleShipPorject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class OpponentGrid extends JPanel{
	final int size = 41;
	final int length = size*10;
	public int mouseX = -100;
	public int mouseY = -100;
	Point hitArr[], missArr[], guess;
	int missCount, hitCount;
	Ship shipArr[];
	boolean shipsSet;
	
	public OpponentGrid(){
		//Initialize arrays
		hitArr = new Point[100];
		missArr = new Point[100];	
		missCount = 0;
		hitCount = 0;
		shipsSet = false;
		//Add mouse listener to Panel
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				mouseX = (e.getX()/size)*size;
				mouseY = (e.getY()/size)*size;
				Point p = new Point(mouseX+1, mouseY+1);
				guess = new Point(p);
				repaint();//Calls paint function
			}
		});
	}//End constructor

	public Point getGuess(){
		Point p = new Point();
		p.x = (int)(guess.x)/41;
		p.y = (int)(guess.y)/41;
		return p;
	}//End getGuess

	public void guessMissed(Point p){
		missArr[missCount] = p;
		missCount++;
		guess = null;
	}

	public void guessHit(Point p){
		hitArr[hitCount] = p;
		hitCount++;
		guess = null;
	}



	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Paint grid squares
		g.setColor(Color.blue);
		for(int y=0; y<10; y++){
			for(int x=0; x<10; x++){
				g.fillRect(x*size, y*size, size-1, size-1);
			}
		}
		if(shipsSet){
			//Paint hits
			g.setColor(Color.red);
			//Loop through arrays to display all selected squares
			for(int i = 0; i < hitCount; i++){
				g.fillRect(hitArr[i].x*size, hitArr[i].y*size, size-1, size-1);
			}
			//Paint misses
			g.setColor(Color.yellow);
			for(int t = 0; t < missCount; t++){
				g.fillRect(missArr[t].x*size, missArr[t].y*size, size-1, size-1);
			}
			//Paint guess
			g.setColor(Color.green);
			g.fillRect(guess.x, guess.y, size-3, size-3);
		}
	}//End paint

}//End OpponentGrid


