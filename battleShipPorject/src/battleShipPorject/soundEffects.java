package battleShipPorject;


import java.io.*;
import java.applet.*;
import java.net.*;
import java.util.*;

public class soundEffects {
	// random number generator for explosion sound effects
	private static Random random = new Random();
	
	private static AudioClip []bomb = new AudioClip[2];
	static AudioClip splash;
	static AudioClip yourTurn;
	
	
	static {
		try{
			splash = Applet.newAudioClip(new File("soundeffects/splashSound.wav").toURL());
			yourTurn = Applet.newAudioClip(new File("soundeffects/beep.wav").toURL());
			bomb[0] = Applet.newAudioClip(new File("soundeffects/bomb1.wav").toURL());
			bomb[1] = Applet.newAudioClip(new File("soundeffects/bomb2.wav").toURL());
		}
		catch(MalformedURLException mue){}
			
		}
	public static void playHit(){
		//select from the two explosion sound options
		bomb[random.nextInt(2)].play();	
	}
}
