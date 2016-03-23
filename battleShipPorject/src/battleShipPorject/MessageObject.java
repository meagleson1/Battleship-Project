package battleShipPorject;


import java.awt.Point;
import java.io.Serializable;


public class MessageObject implements Serializable{
	int messageType;//0 = Guess, 1 = Response, 999 = End Game
	Point coord;//Point with grid x+y grid coordinates
	boolean hit;//Boolean variable to indicate if last guess was a hit

	//Default constructor
	public MessageObject(){
		messageType = -1;
		coord = new Point(-1, -1);
		hit = false;
	}//End default

	//Constructor
	public MessageObject(int t, Point p, boolean h){
		messageType = t;
		coord = p;
		hit = h;
	}//End constructor
	
	public String showMessage(){
		String t, s, h;
		//Set message type
		if(messageType == 0){
			t = "Guess: ";
		}else if(messageType == 1){
			t = "Response: ";
		}else{
			t = "::::ERROR::::";
		}
		//Set hit/miss
		if(hit == false){
			h = "  You Missed :(";
		}else{
			h = ", KABOOM!!!! IT'S A HIT";
		}
		//Construct string
		s = t + coord.x + "," + coord.y + h;
		return s;		
	}//End showMessage

	public int[] toArray(){
		int[] a = new int[4];
		a[0] = messageType;
		a[1] = (int)coord.x;
		a[2] = (int)coord.y;
		if(hit == false){
			a[3] = 0;
		}else{
			a[3] = 1;
		}
		return a;
	}//End toArray

	public void fromArray(int[] c){
		messageType = c[0];
		coord.x = c[1];
		coord.y = c[2];
		if(c[3] == 1){
			hit = true;
		}else{
			hit = false;
		}
		System.out.println(showMessage());
	}//End fromArray
}//End MessageObject