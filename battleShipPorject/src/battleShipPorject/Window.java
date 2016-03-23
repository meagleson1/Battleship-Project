package battleShipPorject;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;


public class Window{
	//GUI members
	JFrame jfrm;
	JTextField jtfChat;
	JPanel pnlChat, pnlShips, pnlSys;
	JScrollPane jspChat, jspSys;
	JButton btnSetShips, btnSend, btnSendMessage;
	JTextArea jtaChat, jtaSys;
	JLabel MyBkgrnd, OppBkgrnd, lblMyGrid, lblOpponentGrid, lblSystemMessages, lblChatBox;
	Panel pnlMyGrid, pnlOppGrid;
	
	OpponentGrid oppGrid;
	PlayerGrid myGrid;
	Network coms;
	HostDialog hDialog;
	ShipSetter setter;
	//Variables
	int mouseX, mouseY;
	Ship shipArray[];
	boolean hostFlag;

	
	public Window(){
		
		hDialog = new HostDialog();
		hostFlag = hDialog.HostJoin();
		//Set look and feel
		try{
		    for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
		        if("Nimbus".equals(info.getName())){
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}catch(Exception e){
			
		}
		createFrame();
		createButtons();
		//createChat();
		createSys();
		createGrids();
		if(hostFlag == true){
			coms = new Server(this);
		}else{
			coms = new Client(this);
		}
		jfrm.setVisible(true);
	}//End constructor

	public void setShips(){
		shipArray = new Ship[5];
		for(int i = 0; i < 5; i++){
			shipArray[i] = new Ship(i+1);
		}
		setter = new ShipSetter(shipArray);		
		setter.jfrm.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent w){
				shipArray = setter.shipArray;
				myGrid.setShipImages(shipArray);
				oppGrid.shipsSet = true;
			}//End windowClosed
		});
	}//End setShips

	public void createGrids(){
		myGrid = new PlayerGrid();
		myGrid.setBounds(145,50,411,411);
		jfrm.getContentPane().add(myGrid);
		oppGrid = new OpponentGrid();
		oppGrid.setBounds(573, 50, 411, 411);		
		jfrm.getContentPane().add(oppGrid);
		lblMyGrid = new JLabel("My Grid");
		lblMyGrid.setForeground(Color.BLACK);
		lblMyGrid.setBackground(Color.GREEN);
		lblMyGrid.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMyGrid.setBounds(317, 17, 68, 31);
		jfrm.getContentPane().add(lblMyGrid);
		lblOpponentGrid = new JLabel("Opponent Grid");
		lblOpponentGrid.setForeground(Color.BLACK);
		lblOpponentGrid.setBackground(Color.GREEN);
		lblOpponentGrid.setFont(new Font("Dialog", Font.BOLD, 16));
		lblOpponentGrid.setBounds(712, 17, 125, 31);
		jfrm.getContentPane().add(lblOpponentGrid);
	}//End createGrids

	public void createSys(){
		pnlSys = new JPanel();
		pnlSys.setBackground(Color.RED);
		pnlSys.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlSys.setBounds(9, 315, 125, 145);
		jfrm.getContentPane().add(pnlSys);
		pnlSys.setLayout(null);
		jspSys = new JScrollPane();
		jspSys.setBounds(2,20,122,120);
		pnlSys.add(jspSys);
		jtaSys = new JTextArea();
		jspSys.setViewportView(jtaSys);
		jtaSys.setLineWrap(true);
		lblSystemMessages = new JLabel("System Messages");
		lblSystemMessages.setBounds(8, 2, 111, 16);
		pnlSys.add(lblSystemMessages);
	}//End createSys

	/*public void createChat(){
		pnlChat = new JPanel();
		pnlChat.setBackground(Color.GREEN);
		pnlChat.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlChat.setBounds(9, 50, 125, 230);
		jfrm.getContentPane().add(pnlChat);
		pnlChat.setLayout(null);
		jtfChat = new JTextField();
		jtfChat.setBounds(2, 169, 122, 29);
		pnlChat.add(jtfChat);
		jtfChat.setColumns(10);
		jspChat = new JScrollPane();
		jspChat.setBounds(2, 18, 122, 150);
		pnlChat.add(jspChat);
		jtaChat = new JTextArea();
		jtaChat.setEditable(false);
		jspChat.setViewportView(jtaChat);
		lblChatBox = new JLabel("Chat Box");
		lblChatBox.setBounds(30, 2, 55, 16);
		pnlChat.add(lblChatBox);
		btnSendMessage = new JButton("Send Message");
		btnSendMessage.setBounds(2, 200, 121, 23);
		pnlChat.add(btnSendMessage);
	}//End createChat*/

	public void createButtons(){
		//Create button to set ship locations
		btnSetShips = new JButton("Set Ship Locations");
		btnSetShips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setShips();
				btnSetShips.setEnabled(false);
			}
		});
		btnSetShips.setBounds(145, 514, 839, 31);
		jfrm.getContentPane().add(btnSetShips);
		//Create button to send guess
		btnSend = new JButton("Send Guess");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(coms.isMyTurn() == true){
					coms.sendMessage(new MessageObject(0, oppGrid.getGuess(), false));//Compose message and send guess
				}else{
					jtaSys.setText(jtaSys.getText() + "Wait your turn!" + '\n');
				}
			}
		});
		btnSend.setBounds(145, 472, 839, 31);
		jfrm.getContentPane().add(btnSend);
	}//End createButtons

	public void createFrame(){
		if(hostFlag){
			jfrm = new JFrame("Battleship: Host");
		}else{
			jfrm = new JFrame("Battleship: Client");			
		}
		jfrm.getContentPane().setBackground(Color.LIGHT_GRAY);
		jfrm.setSize(1000,650);
		jfrm.setResizable(false);
		jfrm.setLocationRelativeTo(null);
		jfrm.getContentPane().setLayout(null);		
	}//End createFrame	

	public void handleMessage(MessageObject m){
		if(m.messageType == 0){
			processGuess(m);
		}else if(m.messageType == 1){
			processResponse(m);
		}else if(m.messageType == 999){
			endGame();
		}
	}//End handleMessage

	public void processGuess(MessageObject m){
		int sunk = 0;
		MessageObject o= null;
		boolean shipHit = false;
		//Test for hit 
		for(int i = 0; i < 5; i++){
			
			if(shipArray[i].isHit(m.coord)){
				soundEffects.playHit();
				shipHit = true;
			}
			if(shipArray[i].isSunk()){
				soundEffects.playHit();
				sunk++;
			}
		}
		if(shipHit == false){
			soundEffects.splash.play();
			myGrid.addMiss(m.coord);//Update GUI
			o = new MessageObject(1, m.coord, false);//Compose response
		}
		else if(shipHit == true){
			myGrid.addHit(m.coord);//Update GUI
			//Send message for endGame if all ships are sunk
			if(sunk == 5){
				//display loosing message
				o = new MessageObject(999, m.coord, true);//Compose end game message
			}else{
				o = new MessageObject(1, m.coord, true);//Compose response				
			}
		}
		coms.sendMessage(o);//Send response	
	}//End processGuess
	
	public void processResponse(MessageObject m){
		if(m.hit == false){
			oppGrid.guessMissed(m.coord);
		}else{
			oppGrid.guessHit(m.coord);
		}
	}//End processResponse

	public void endGame(){
		Object[] options = new Object[]{"OK"};
		int x = JOptionPane.showOptionDialog(null, "YOU WON THE GAME!!!!!!!", "End Game", JOptionPane.OK_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}//End endGame

	//Main function
	public static void main(String[] args) {
		Window w = new Window();
	}

}//End Window


