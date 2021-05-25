package minesweeper;

import components.GridComponent;
import entity.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 */
public class ScoreBoard extends JPanel {


    List<Player> players= new ArrayList<>();
    Player p1 = new Player(1);
    Player p2 = new Player(2);
    Player p3 = new Player(3);
    Player p4 = new Player(4);

    JLabel score1 = new JLabel();
    JLabel score2 = new JLabel();
    JLabel score3 = new JLabel();
    JLabel score4 = new JLabel();

    /**
     * 通过进行游戏的玩家来初始化计分板。这里只考虑了两个玩家的情况。
     * 如果想要2-4人游戏甚至更多，请自行修改(建议把所有玩家存在ArrayList)~
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public ScoreBoard(Player p1, Player p2, int xCount, int yCount) {
        this.add(new JLabel("Score Board - "));
        this.setSize(yCount * GridComponent.gridSize, 80);
        this.setLocation(0, xCount * GridComponent.gridSize);

        this.p1 = p1;
        this.p2 = p2;
        this.add(score1);
        this.add(score2);

        this.setLayout(new BoxLayout(this, 1));
        update();
    }

    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public ScoreBoard(Player p1, Player p2, Player p3, int xCount, int yCount) {
        this.add(new JLabel("Score Board - "));
        this.setSize(yCount * GridComponent.gridSize, 80);
        this.setLocation(0, xCount * GridComponent.gridSize);

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.add(score1);
        this.add(score2);
        this.add(score3);

        this.setLayout(new BoxLayout(this, 1));
        update();
    }

    public ScoreBoard(Player p1, Player p2, Player p3, Player p4, int xCount, int yCount) {
        this.add(new JLabel("Score Board - "));
        this.setSize(yCount * GridComponent.gridSize, 80);
        this.setLocation(0, xCount * GridComponent.gridSize);

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.add(score1);
        this.add(score2);
        this.add(score3);
        this.add(score4);

        this.setLayout(new BoxLayout(this, 1));
        update();
    }

    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update() {
        score1.setText(String.format("%s : %d score (+ %d mistake)", p1.getUserName(), p1.getScore(), p1.getMistake()));
        score2.setText(String.format("%s : %d score (+ %d mistake)", p2.getUserName(), p2.getScore(), p2.getMistake()));
        score3.setText(String.format("%s : %d score (+ %d mistake)", p3.getUserName(), p3.getScore(), p3.getMistake()));
        score4.setText(String.format("%s : %d score (+ %d mistake)", p4.getUserName(), p4.getScore(), p4.getMistake()));
    }

}