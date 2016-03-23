package battleShipPorject;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import javax.swing.JTextArea;

public class ShipSetter {
	JFrame jfrm;
	JPanel panel;
	DraggableGrid grid;
	JButton btnConfirm, btnAutoPlace, btnResetShips;
	Ship shipArray[];
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
	JTextArea txtMessage;
	
	public ShipSetter(Ship[] s){
		shipArray = s;
		jfrm = new JFrame("Set Ship Locations");
		jfrm.setSize(666,555);
		jfrm.setResizable(false);
		jfrm.setVisible(true);
		jfrm.setLocationRelativeTo(null);
		jfrm.getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(423, 0, 237, 515);
		jfrm.getContentPane().add(panel);
		panel.setLayout(null);
		createLabels();
		createButtons();
		grid = new DraggableGrid(shipArray);
		grid.setBounds(1,1, 412, 551);
		jfrm.getContentPane().add(grid);
	}

	public void createButtons(){
		btnConfirm = new JButton("Confirm Placement");
		btnConfirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(grid.anyOverlap() == true){
					txtMessage.setText(txtMessage.getText() + "Failed: Ships are overlapping" + '\n');
				}else{
					if(grid.allOnGrid() == false){
						txtMessage.setText(txtMessage.getText() + "Failed: All ships are not on the board" + '\n');
					}else{
						jfrm.dispose();						
					}
				}
			}
		});
		btnConfirm.setBounds(10, 202, 217, 50);
		panel.add(btnConfirm);
		btnAutoPlace = new JButton("Auto-Place Ships");
		btnAutoPlace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				autoplaceShips();
				jfrm.dispose();
			}
		});
		btnAutoPlace.setBounds(10, 263, 217, 50);
		panel.add(btnAutoPlace);
		btnResetShips = new JButton("Reset Ships");
		btnResetShips.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}
		});
		btnResetShips.setBounds(10, 324, 217, 50);
		panel.add(btnResetShips);
		txtMessage = new JTextArea();
		txtMessage.setText("                SYSTEM MESSAGES" + '\n');
		txtMessage.setBackground(Color.YELLOW);
		txtMessage.setBounds(10, 385, 217, 119);
		panel.add(txtMessage);
	}//End createButtons

	public void createLabels(){
		lbl1 = new JLabel("TO CHANGE SHIP ORIENTATION:");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl1.setBounds(10, 11, 217, 28);
		panel.add(lbl1);
		lbl2 = new JLabel("* Press number corresponding to");
		lbl2.setBounds(31, 38, 196, 14);
		panel.add(lbl2);
		lbl3 = new JLabel("the ship you want to re-orient");
		lbl3.setBounds(41, 52, 186, 14);
		panel.add(lbl3);
		lbl4 = new JLabel("1 = Carrier");
		lbl4.setBounds(65, 77, 162, 14);
		panel.add(lbl4);
		lbl5 = new JLabel("2 = Battleship");
		lbl5.setBounds(65, 102, 162, 14);
		panel.add(lbl5);
		lbl6 = new JLabel("3 = Cruiser");
		lbl6.setBounds(65, 127, 162, 14);
		panel.add(lbl6);
		lbl7 = new JLabel("4 = Submarine");
		lbl7.setBounds(65, 152, 162, 14);
		panel.add(lbl7);
		lbl8 = new JLabel("5 = Destroyer");
		lbl8.setBounds(65, 177, 162, 14);
		panel.add(lbl8);
	}//End createLabels
	
	public Ship[] getShips(){
		return shipArray;
	}//End getShips

	public void autoplaceShips(){//AUTOPLACE + PLACESHIP NEED TO BE FIXED!
		
		Point inPlace[] = new Point[17];
		Point current[] = new Point[4];
		boolean placed = false;
		//Place ships
		for(int i = 0; i < 5; i++){
			do{
				placed = placeShip(i+1,inPlace);				
			}while(!placed);
		}		
	}//End autoplaceShips

	public boolean placeShip(int type, Point set[]){
		Random rand = new Random();
		int x, y, v, size;
		size = shipArray[type-1].size;
		Point current[] = new Point[size];
		v = rand.nextInt(2);//randomly choose horizontal/vertical	
		if(v == 0){//Horizontal
			if(shipArray[type-1].isVertical){//If ship is vertical, make horizontal
				shipArray[type-1].makeHorizontal();				
			}
			x = rand.nextInt(10-(size-1));//Generates random number s.t. size+x < 9
			y = rand.nextInt(10);//Generate number 0-9 to place at any vertical position
			for(int i = 0; i < size; i++){//Calculate positions to be covered by this ship
				current[i] = new Point(x+i, y);
			}
		}else{//Vertical
			if(!shipArray[type-1].isVertical){
				shipArray[type-1].makeVertical();				
			}
			x = rand.nextInt(10);
			y = rand.nextInt(10-(size - 1));
			for(int i = 0; i < size; i++){
				current[i] = new Point(x, y+i);
			}
		}
		
		//Loop through current and test each element against already set ship positions
		for(int i = 0; i < size; i++){
			for(int t = 0; t < set.length; t++){
				if(current[i] == set[t]){
					return false;//Break out of function and return false if any position is found in both arrays
				}
			}
		}
		//If random location is valid, set corresponding ship's location
		shipArray[type-1].setLocation(current[0]);
		return true;//Return true to verify correct position was set
	}//End 
	
}//End ShipSetter
