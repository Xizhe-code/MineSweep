package components;
//setter,getter对数据进行过滤，选择展示给用户的
//继承提取共有的属性给父类，减少代码的重复。父类比子类更抽象，更广泛
//方法重写：method overriding 返回值类型和方法签名类型完全相同，重载的方法不能消弱父类方法的可行性
//所有类都继承自object类，所有类都有object的所有方法
//多态：父类型的引用变量可以指向子类型的引用变量 eg。 Pokemon 父类声明类型 P =new pikachu（）实际类型
// p instance of pikachu 判断p是否是pikachu的子类

//抽象类中有无抽象方法均可，不能new ，不能作为实际类型，但仍可作为声明类型 。仍然可写构造方法 super关键字调用父类的方法
//interface 接口：与类相似，但只能定义常量和抽象方法，实现这个接口必须有这些抽象方法
//先继承自extends，后继承自implements
import entity.GridStatus;
import minesweeper.MainFrame;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class GridComponent extends BasicComponent {
    public static int gridSize = 35;


    private int row;
    private int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;
    private boolean hasmine;
    private int clicktime = 0;



    public static  final ImageIcon flag =new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\flag.jpeg");
    public static  final ImageIcon bomb =new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\PictureTest\\bomb.jpeg");
    public static  final ImageIcon ball =new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\PictureTest\\ac428049cc2f1a4331a8f21096aa2e1c.jpeg");

    public void setStatus(GridStatus status) {
        this.status = status;
    }
    public GridComponent(int x, int y,boolean hasmine) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
        this.hasmine=hasmine;
        this.status = GridStatus.Covered;
    }
    public void LeftSuccess() {
        try {
            FileInputStream fileau = new FileInputStream("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\MusicTest\\leftsuccess.wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LeftFail() {
        try {
            FileInputStream fileau = new FileInputStream("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\MusicTest\\bomb.wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RightFail() {
        try {
            FileInputStream fileau = new FileInputStream("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\MusicTest\\右击错误.wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void RightSuccess() {
        try {
            FileInputStream fileau = new FileInputStream("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\MusicTest\\成功插旗.wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMouseLeftClicked() {
        //clicktime++;
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);
       //if (clicktime==1 && MainFrame.controller.getGamePanel().getGrid(row , col).content==-1) {
        //   while (MainFrame.controller.getGamePanel().getGrid(row,col).content==-1){
        //       MainFrame.controller.getGamePanel().generateChessBoard(row,col,MainFrame.controller.getMineremained().getNumber()+1);
        //   }
        //}
        if (this.status == GridStatus.Covered) {
            this.status = GridStatus.Clicked;
            repaint();

            if (this.hasmine == false) {
                MainFrame.controller.getOnTurnPlayer().addScore();
                LeftSuccess();
            } else {
                MainFrame.controller.getOnTurnPlayer().addMistake();
                MainFrame.controller.getOnTurnPlayer().costScore();
                MainFrame.controller.getMineremained().setNumber(MainFrame.controller.getMineremained().getNumber() - 1);
                MainFrame.controller.getMineremained().newthenumber();
                //System.out.println(MainFrame.controller.getMineremained().getNumber());
                Judge(MainFrame.controller.getMineremained().getNumber());
                LeftFail();
            }
        }
        if (MainFrame.controller.getMineremained().getNumber()==0){
            MainFrame.controller.getGamePanel().setVisible(false);

        }
        else MainFrame.controller.nextTurn();
        }
        //TODO: 在左键点击一个格子的时候，还需要做什么？
    @Override


    public void onMouseRightClicked() {
        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
        if (this.status == GridStatus.Covered) {
            this.status = GridStatus.Flag;
            repaint();
            if (this.hasmine==true){
                RightSuccess();
                MainFrame.controller.getOnTurnPlayer().addScore();
                MainFrame.controller.getOnTurnPlayer().addScore();
                MainFrame.controller.getMineremained().setNumber(MainFrame.controller.getMineremained().getNumber()-1);
                Judge(MainFrame.controller.getMineremained().getNumber());
            }
            else {
                RightFail();
                MainFrame.controller.getOnTurnPlayer().addMistake();
                this.status=GridStatus.Clicked;
            }
            if (MainFrame.controller.getMineremained().getNumber()==0){
                MainFrame.controller.getGamePanel().setVisible(false);

            }
            else MainFrame.controller.nextTurn();
        }

        //TODO: 在右键点击一个格子的时候，还需要做什么？
    }

    public void draw(Graphics g) {

        if (this.status == GridStatus.Covered) {

            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawImage(ball.getImage(), 0,0,getWidth(),getWidth(),ball.getImageObserver());
        }

        if (this.status == GridStatus.Clicked) {
            if (hasmine == true) {

                g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                g.drawImage(bomb.getImage(), 0, 0, getWidth(), getWidth(), bomb.getImageObserver());

            } else {
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);

            }
        }



        if (this.status == GridStatus.Flag) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.RED);
            g.drawImage(flag.getImage(), 0,0,getWidth(),getWidth(),flag.getImageObserver());
        }
    }

    public void setContent(int content) {
        this.content = content;
        if (content==-1){
            this.hasmine=true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }

    public void Judge(int number){
        if (number==0){
            MainFrame.controller.getGamePanel().setVisible(false);
        }
    }

    public void  actionPerformer(){

    }

}

