import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip clip;
	
	public void setFile(String soundFileName){
		
		try{
			File file = new File(soundFileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);	
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e){
			
		}
	}
	
	public void play(){
		
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void playLoop() {
		clip.setFramePosition(0);
		clip.start();
		clip.loop(9);
	}
	
	public void pauseLoop() {
		clip.stop();
	}

}
