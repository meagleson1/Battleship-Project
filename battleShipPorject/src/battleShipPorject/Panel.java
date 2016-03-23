package battleShipPorject;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	final int size = 41;
	final int length = size*10;
	public int mouseX = -100;
	public int mouseY = -100;
	BufferedImage caImg, bImg, crImg, sImg, dImg;
	Point hitArr[], missArr[];
	int missCount, hitCount;
	boolean shipsSet;
	Ship shipArr[];
	
	public Panel(){
		//Initialize arrays
		hitArr = new Point[100];
		missArr = new Point[100];
		shipsSet = false;			
		//Add mouse listener to Panel
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				mouseX = (e.getX()/size)*size;
				mouseY = (e.getY()/size)*size;
				Point p = new Point(mouseX+1, mouseY+1);
				hitArr[hitCount] = p;
				hitCount++;
				repaint();//Calls paint function
			}
		});
	}//End constructor

	public void setShipImages(Ship s[]){
		shipArr = s;
		//Get background images
		try{
			if(!shipArr[0].isVertical){//Carrier
				caImg = ImageIO.read(new File("graphics/carrier.gif"));
				shipArr[0].sizeX = shipArr[0].size*size;
				shipArr[0].sizeY = size;
			}else{
				caImg = ImageIO.read(new File("graphics/carrierv.gif"));
				shipArr[0].sizeX = size;
				shipArr[0].sizeY = shipArr[0].size*size;
			}
			if(!shipArr[1].isVertical){//Battleship
				bImg = ImageIO.read(new File("graphics/battleship.gif"));
				shipArr[1].sizeX = shipArr[1].size*size;
				shipArr[1].sizeY = size;
			}else{
				bImg = ImageIO.read(new File("graphics/battleshipv.gif"));
				shipArr[1].sizeX = size;
				shipArr[1].sizeY = shipArr[1].size*size;
			}
			if(!shipArr[2].isVertical){//Cruiser
				crImg = ImageIO.read(new File("graphics/cruiser.gif"));
				shipArr[2].sizeX = shipArr[2].size*size;
				shipArr[2].sizeY = size;
			}else{
				crImg = ImageIO.read(new File("graphics/cruiserv.gif"));
				shipArr[2].sizeX = size;
				shipArr[2].sizeY = shipArr[2].size*size;
			}
			if(!shipArr[3].isVertical){//Submarine
				sImg = ImageIO.read(new File("graphics/submarine.gif"));
				shipArr[3].sizeX = shipArr[3].size*size;
				shipArr[3].sizeY = size;
			}else{
				sImg = ImageIO.read(new File("graphics/submarinev.gif"));
				shipArr[3].sizeX = size;
				shipArr[3].sizeY = shipArr[3].size*size;
			}
			if(!shipArr[4].isVertical){//Destroyer
				dImg = ImageIO.read(new File("graphics/destroyer.gif"));
				shipArr[4].sizeX = shipArr[4].size*size;
				shipArr[4].sizeY = size;
			}else{
				dImg = ImageIO.read(new File("graphics/destroyerv.gif"));
				shipArr[4].sizeX = size;
				shipArr[4].sizeY = shipArr[4].size*size;
			}
		}catch(IOException ex){
			System.out.println("IMAGE(S) NOT FOUND");
		}
		shipsSet = true;
		repaint();
	}//End setShipImages();
	
	public int getMouseX(){
		return mouseX;
	}//End getMouseX

	public int getMouseY(){
		return mouseY;
	}//End getMouseY
	
	//Built in function to display graphics
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Paint grid squares
		g.setColor(Color.blue);
		for(int y=0; y<10; y++){
			for(int x=0; x<10; x++){
				g.fillRect(x*41, y*41, 40, 40);
			}
		}
		//Paint ships onto grid
		if(shipsSet){
			g.drawImage(caImg, shipArr[0].location[0].x*41, shipArr[0].location[0].y*41, shipArr[0].sizeX, shipArr[0].sizeY, null);
			g.drawImage(bImg, shipArr[1].location[0].x*41, shipArr[1].location[0].y*41, shipArr[1].sizeX, shipArr[1].sizeY, null);
			g.drawImage(crImg, shipArr[2].location[0].x*41, shipArr[2].location[0].y*41, shipArr[2].sizeX, shipArr[2].sizeY, null);
			g.drawImage(sImg, shipArr[3].location[0].x*41, shipArr[3].location[0].y*41, shipArr[3].sizeX, shipArr[3].sizeY, null);
			g.drawImage(dImg, shipArr[4].location[0].x*41, shipArr[4].location[0].y*41, shipArr[4].sizeX, shipArr[4].sizeY, null);
			//Paint hits
			g.setColor(Color.red);
			//Loop through arrays to display all selected squares
			for(int i = 0; i < hitCount; i++){
				g.fillRect(hitArr[i].x, hitArr[i].y, size-1, size-1);
			}
			//Paint misses
			//Paint guess
		}
	}//End paintComponent

}//End Panel
