package minesweeper;

import javax.swing.*;

public class Mineremained extends JPanel{

    private int Number;
    JLabel minenumber = new JLabel();

    public int getNumber() {
        return Number;
    }

    public void setNumber(int mineNumber) {
        Number = mineNumber;
    }

    public Mineremained(int yCount , int xCount, int mineNumber ){
        this.Number = mineNumber;
        this.add(new JLabel("Mine Remained:"));
        this.setSize(500, 200);
        //this.setLocation(0,yCount * GridComponent.gridSize+100);
        this.add(minenumber);
        this.setVisible(true);
        newthenumber();
    }

    public void newthenumber(){
        minenumber.setText(String.format("%d",Number));
    }
}
