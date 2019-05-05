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

    public static Clip reset(Clip c)
    {
        c.stop();
        c.setMicrosecondPosition(0L);
        return c;
    }

    public static void playClip(Clip c)
    {
        reset(c);
        c.start();
    }
}