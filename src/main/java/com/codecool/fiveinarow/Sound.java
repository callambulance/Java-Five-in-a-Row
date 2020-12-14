package com.codecool.fiveinarow;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Sound {


    public static void playMusic(String musicLocation){

        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);

            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

