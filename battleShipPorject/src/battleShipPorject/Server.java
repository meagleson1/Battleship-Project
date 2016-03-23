package battleShipPorject;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;

public class Server extends Network implements Runnable{
	ServerSocket socket;
	Socket client;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Window w;
	int[] input;
	MessageObject msg;
	Object inObject;
	
	public Server(Window win){
		myTurn = true;
		w = win;
		Thread myThread = new Thread(this);
		myThread.start();
	}//End constructor

	public void sendMessage(MessageObject o){
		try{
			oos.writeObject(o);//.toArray());
			myTurn = false;
		}catch(IOException e){
			e.printStackTrace();
		}
	}//End sendMessage	

	@Override
	public void run() {
		try{
			socket = new ServerSocket(4444);
			client = socket.accept();
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
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
	public static void main(String[]args)throws Exception{
		Window wi;
		wi = new Window();
		Server run = new Server(wi);
		run.run();
	}
}//End Server
