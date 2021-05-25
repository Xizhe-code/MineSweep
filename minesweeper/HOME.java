package minesweeper;

import minesweeper.MainFrame;

import javax.swing.*;
import java.awt.*;

public class HOME extends JFrame {
    public HOME(){
        this.setSize(600,800);
        this.setTitle("扫雷");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton I=new JButton("");
        I.setPreferredSize(new Dimension(60,60));

        add(I);


        /*
        JButton stop = new JButton("stop");
        stop.addActionListener(e -> {
            MainFrame.musicTest.stop();
        });
        stop.setVisible(true);
        stop.setLocation(5,gamePanel.getHeight() + scoreBoard.getHeight()+20);
        stop.setPreferredSize(new Dimension(60,60));
        add(stop);*/

    }
}
