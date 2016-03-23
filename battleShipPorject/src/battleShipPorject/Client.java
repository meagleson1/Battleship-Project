package battleShipPorject;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.SwingUtilities;

public class Client extends Network implements Runnable{
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Window w;
	Object inObject;
	int[] input;
	MessageObject msg;

	public Client(Window win){

		myTurn = false;
		w = win;
		Thread myThread = new Thread(this);
		myThread.start();
	}

	public void sendMessage(MessageObject o){
		try{
			oos.writeObject(msg);
			oos.writeObject(o);//.toArray());
			myTurn = false;
		}catch(IOException e){
			e.printStackTrace();
		}
	}//End sendMessage

	@Override
	public void run() {
		try{
			socket = new Socket("localhost", 4444);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			while(true){
				inObject = ois.readObject();
				msg = (MessageObject)inObject;
				w.handleMessage(msg);
				w.jtaSys.setText(msg.showMessage() + '\n');
				myTurn = true;
			}
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}//End run
	public static void mian(String[]args)throws Exception{
		Window wi;
		wi = new Window();
		Client test = new Client(wi);
	}
	
}