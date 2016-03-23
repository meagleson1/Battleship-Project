package battleShipPorject;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DraggableGrid extends JPanel{
	public JLabel bImage, caImage, dImage, sImage, crImage;
	public MouseDragger dragger;
	public Point occupied[];
	public Ship shipArr[];

	public DraggableGrid(Ship s[]){
		//Set size and background for Grid
		this.setBounds(0, 0, 411, 550);
		this.setBackground(Color.black);
		this.setLayout(null);
		//Initialize variables
		dragger = new MouseDragger();
		occupied = new Point[17];
		shipArr = s;
		//Set image for each ship
		setImages();	
		//Add key listener to grid
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == KeyEvent.VK_1){
					shipArr[0].changeOrientation();
					caImage.setIcon(shipArr[0].getImage());
					caImage.setSize(caImage.getPreferredSize());
				}else if(k.getKeyCode() == KeyEvent.VK_2){
					shipArr[1].changeOrientation();
					bImage.setIcon(shipArr[1].getImage());
					bImage.setSize(bImage.getPreferredSize());
				}else if(k.getKeyCode() == KeyEvent.VK_3){
					shipArr[2].changeOrientation();
					crImage.setIcon(shipArr[2].getImage());
					crImage.setSize(crImage.getPreferredSize());
				}else if(k.getKeyCode() == KeyEvent.VK_4){
					shipArr[3].changeOrientation();
					sImage.setIcon(shipArr[3].getImage());
					sImage.setSize(sImage.getPreferredSize());
				}else if(k.getKeyCode() == KeyEvent.VK_5){
					shipArr[4].changeOrientation();
					dImage.setIcon(shipArr[4].getImage());
					dImage.setSize(dImage.getPreferredSize());
				}
			}
		});
		
	}//End constructor

//	public void initShips(){
//		shipArr = new Ship[5];
//		for(int i = 1; i <= 5; i++){
//			shipArr[i-1] = new Ship(i);
//		}
//	}//End initShips
	
	
	public void setImages(){//Creates JLabels for each ship's image and add to this
		//Set image for Carrier
		caImage = new JLabel(shipArr[0].getImage());
		caImage.setSize(caImage.getPreferredSize());
		dragger.makeDraggable(caImage);
		this.add(caImage);
		caImage.setLocation(0, 411);
		//Set image for Battleship
		bImage = new JLabel(shipArr[1].getImage());
		bImage.setSize(bImage.getPreferredSize());
		dragger.makeDraggable(bImage);
		this.add(bImage);
		bImage.setLocation(245, 411);
		//Set image for Cruiser
		crImage = new JLabel(shipArr[2].getImage());
		crImage.setSize(crImage.getPreferredSize());
		dragger.makeDraggable(crImage);
		this.add(crImage);
		crImage.setLocation(0,455);
		//Set image for Sub
		sImage = new JLabel(shipArr[3].getImage());
		sImage.setSize(sImage.getPreferredSize());
		dragger.makeDraggable(sImage);
		this.add(sImage);
		sImage.setLocation(162, 455);
		//Set image for Destroyer
		dImage = new JLabel(shipArr[4].getImage());
		dImage.setSize(dImage.getPreferredSize());
		dragger.makeDraggable(dImage);
		this.add(dImage);
		dImage.setLocation(327, 455);
	}//End setImages
	
	public boolean anyOverlap(){//Test to see if any ships overlap
		Point p;
		//Set locations of Ships
		p = new Point(caImage.getLocation().x/40, caImage.getLocation().y/40);
		shipArr[0].setLocation(p);
		p = new Point(bImage.getLocation().x/40, bImage.getLocation().y/40);
		shipArr[1].setLocation(p);
		p = new Point(crImage.getLocation().x/40, crImage.getLocation().y/40);
		shipArr[2].setLocation(p);
		p = new Point(sImage.getLocation().x/40, sImage.getLocation().y/40);
		shipArr[3].setLocation(p);
		p = new Point(dImage.getLocation().x/40, dImage.getLocation().y/40);
		shipArr[4].setLocation(p);
		//Populate occupied array
		for(int i = 0; i < 5; i++){
			occupied[i] = shipArr[0].location[i];
		}
		for(int q = 0; q < 4; q++){
			occupied[q+5] = shipArr[1].location[q];
		}
		for(int t = 0; t < 3; t++){
			occupied[t+9] = shipArr[2].location[t];
		}
		for(int z = 0; z < 3; z++){
			occupied[z+12] = shipArr[3].location[z];
		}
		for(int v = 0; v < 2; v++){
			occupied[v+15] = shipArr[4].location[v];
		}
		//Check if the same point is in occupied more than once
		for(int l = 0; l < 17; l++){
			for(int w = l+1; w < 17; w++){
				if(occupied[l].x == occupied[w].x && occupied[l].y == occupied[w].y){
					return true;
				}
			}
		}
		return false;
	}//End anyOverlap
	
	public boolean allOnGrid(){//Must be called after anyOverlap (to populate occupied with ship locations)
			for(int i = 0; i < 17; i++){
			if(occupied[i].x < 0 || occupied[i].x > 9){
				return false;
			}else{
				if(occupied[i].y < 0 || occupied[i].y > 9){
					return false;
				}
			}
		}
		return true;
	}//End allOnGrid

	public void paintComponent(Graphics g){//Paint graphics to screen
		super.paintComponent(g);
		for(int y=0; y<10; y++){
			for(int x=0; x<10; x++){
				g.setColor(Color.blue);
				g.fillRect(x*41, y*41, 40, 40);
			}
		}		
	}//End paintComponent


}//End DraggableGrid