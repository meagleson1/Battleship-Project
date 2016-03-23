package battleShipPorject;


import javax.swing.JOptionPane;

public class HostDialog {
	JOptionPane jop;
	Object[] options;
	boolean host;
	int input;
	
	public HostDialog(){
		options = new Object[]{"Host","Join"};
	}
	
	public boolean HostJoin(){
		input = JOptionPane.showOptionDialog(null, "Do you want to host a game or join another game?", "Battleship", 
				 JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return (input == 0) ? true:false;
	}

}//End HostDialog
