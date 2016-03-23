package battleShipPorject;

 import java.awt.Point;
 import javax.swing.ImageIcon;

 public class Ship{
 	//Ship members
 	public ImageIcon img, imgv, imgh;
 	public int size, health, shipType, sizeX, sizeY;
 	public String shipName;
 	public Point location[];
 	public boolean isVertical;

 	//Constructor
 	public Ship(int type){
 		shipType = type;
 		isVertical = false;
 		switch(shipType){
 			case 1://Carrier
 				size = 5;
 				health = 5;
 				location = new Point[5];
 				shipName = "Carrier";
 				break;
 			case 2://Battleship
 				size = 4;
 				health = 4;
 				location = new Point[4];
 				shipName = "Battleship";
 				break;
 			case 3://Cruiser
 				size = 3;
 				health = 3;
 				location = new Point[3];
 				shipName = "Cruiser";;
 				break;
 			case 4://Submarine
 				size = 3;
 				health = 3;
 				location = new Point[3];
 				shipName = "Submarine";
 				break;
 			case 5://Destroyer
 				size = 2;
 				health = 2;
 				location = new Point[2];
 				shipName = "Destroyer";
 				break;
 			default:
 				System.out.println("Ship type invalid");
 		}//End switch
 		//Set ship images
 		imgh = new ImageIcon("graphics/" + shipName + ".gif");
 		imgv = new ImageIcon("graphics/" + shipName + "v.gif");
 		img = imgh;	//Horizontal image is the default 
 	}//End constructor

 	//Set location of ship given top-leftmost point
 	public void setLocation(Point p){//Set location of ship given top-leftmost point
 		for(int i = 0; i < size; i++){
 			if(isVertical){
 				location[i] = new Point(p.x, p.y+i);
 			}else{
 				location[i] = new Point(p.x + i, p.y);
 			}
 		}
 	}//End setLocation

 	public void changeOrientation(){//Change image for ship and isVertical 
 		if(isVertical){
 			makeHorizontal();
 		}else{
 			makeVertical();
 		}
 	}//End changeOrientation

 	public void makeHorizontal(){
 		img = imgh;
 		isVertical = false;
 	}//End makeHorizontal

 	public void makeVertical(){
 		img = imgv;
 		isVertical = true;
 	}//End makeVertical

 	public ImageIcon getImage(){//Return current image for ship
 		return img;
 	}//End getImage

 	public Point[] getLocation(){//Return array of Point objects 
 		return location;
 	}//End getLocation

 	public void showLocation(){
 		System.out.println(shipName);
 		for(int i = 0; i < size; i++){
 			System.out.println(location[i] + ", ");
 		}
 		System.out.println('\n');		
 	}//End showLocation

	public boolean isHit(Point p){
		for(int i = 0; i < size; i++){
			if(p.equals(location[i])){
				health--;
				return true;
			}
		}
		return false;
	}//End isHit

	public boolean isSunk(){
		return (health <= 0) ? true:false;
	}//End isSunk

 }//End Ship
