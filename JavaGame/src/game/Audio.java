package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio
{
    AudioInputStream AudioIn;

    Clip c;

    public static Clip parseSound(File sound)
    {
        Clip clip = null;

        try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
        }
        catch (Exception e)
        {e.printStackTrace();}


        return clip;
    }
}