package com.rk;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Mesin extends JFrame {

    AudioInputStream suara;
    String eror1 = "Tidak Ada Eror";
    String eror2 = "Tidak Ada Eror";
    String eror3 = "Tidak Ada Eror";

    public URL getURL(String namaFile) {
        URL url = null;

        try {
            url = this.getClass().getResource(namaFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return url;
    }

    public Mesin() {
        try {
            suara = AudioSystem.getAudioInputStream(getURL("ContohSuara.wav"));
            Clip klip = AudioSystem.getClip();
            klip.open(suara);
            klip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            eror1 = e.toString();
        } catch (IOException e) {
            eror2 = e.toString();
        } catch (LineUnavailableException e) {
            eror3 = e.toString();
        }

        setTitle("Percobaan Suara");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(suara.getFormat().toString(), 10, 50);
        g.drawString("Rate: " + (int) suara.getFormat().getSampleRate(), 10, 100);
        g.drawString("Channels: " + suara.getFormat().getChannels(), 10, 150);
        g.drawString("Encoded format: " + suara.getFormat().getEncoding().toString(), 10, 200);
        g.drawString("Size: " + suara.getFormat().getSampleSizeInBits() + "-bit", 10, 250);
        g.drawString("Frame size: " + suara.getFormat().getFrameSize(), 10, 300);
        g.drawString("Eror 1 : " + eror1, 10, 350);
        g.drawString("Eror 2 : " + eror2, 10, 400);
        g.drawString("Eror 3 : " + eror3, 10, 450);
    }
}
