package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {

    Clip clip;
    Clip background;
    URL soundURL[] = new URL[30];

    // Constructor
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/sound.wav");
        soundURL[1] = getClass().getResource("/sound/win.wav");
        soundURL[2] = getClass().getResource("/sound/coin_pick_sound.wav");
        soundURL[3] = getClass().getResource("/sound/uh_oh.wav");
        soundURL[4] = getClass().getResource("/sound/oh_no.wav");
        soundURL[5] = getClass().getResource("/sound/fanfare.wav");
    }

    // Set sound file
    public void setFile(int i) {
        // Effect sounds
        if (i > 1) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } catch (Exception e) {

            }
        }
        // Background sound
        else {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
                background = AudioSystem.getClip();
                background.open(audioInputStream);
            } catch (Exception e) {

            }
        }
    }

    // Play back ground
    public void playBackground(){
        background.start();
    }

    // Loop the background
    public void loopBackground(){
        background.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Stop background
    public void stopBackground(){
        background.stop();
    }

    // Play effect sounds
    public void play(){
        clip.start();
    }

    // Loop effect sounds
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Stop effect sounds
    public void stop(){
        clip.stop();
    }
}
