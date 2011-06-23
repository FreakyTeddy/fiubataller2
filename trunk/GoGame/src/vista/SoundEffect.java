package vista;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEffect {
	LOOP(Paths.SonidoLoop),
	PIEZA1(Paths.SonidoPieza1),
	PIEZA2(Paths.SonidoPieza2);
	
	 public static enum Volume {
	      MUTE, NOT_MUTE
	   }
	   
	   public static Volume volume = Volume.NOT_MUTE;

	private Clip clip;

	SoundEffect(String soundFileName) {
		try {
				
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					this.getClass().getClassLoader().getResource(soundFileName));
           
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

		} catch (UnsupportedAudioFileException e) {
			System.out.println("Error al cargar el sonido :" + soundFileName + " -> " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al cargar el sonido :" + soundFileName + " -> " + e.getMessage());
		} catch (LineUnavailableException e) {
			System.out.println("Error al cargar el sonido :" + soundFileName + " -> " + e.getMessage());
		} catch (RuntimeException e) {
			System.out.println("Error al cargar el sonido :" + soundFileName + " -> " + e.getMessage());
		}
	}


	public void play() {
		try {
			if (volume != Volume.MUTE) {
				if (clip.isRunning())
					clip.stop();
				clip.setFramePosition(0);
				clip.start();
			}
		}catch (RuntimeException e) {
			//error al reproducir el sonido
		}
	}
	
	public void loop(){
		try {
			if (volume != Volume.MUTE) {
				if (clip.isRunning())
					clip.stop();
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}catch (RuntimeException e) {
			//error al reproducir el sonido
		}
	}
	
	public void stop(){
			if (clip.isRunning())
				clip.stop();			
	}

	static void init() {
		values();
	}
}
