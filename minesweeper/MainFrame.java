package minesweeper;
import MusicTest.test1;
import PictureTest.FirstPage;
import components.GridComponent;
import components.test;
import controller.GameController;
import entity.GridStatus;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static test1.MusicTest musicTest;

    public static GameController controller;
    private int xCount;
    private int yCount;
    private int mineCount;
    private JButton stop;
    private JButton PLayMusic;

    public int getMineCount() {
        return mineCount;
    }

    public MainFrame(int xcount, int ycount, int minecount, int turnnumber, int peoplenumber) {
        //todo: change the count of xCount, yCount and mineCount by passing parameters from constructor


        this.xCount = xcount;//grid of row
        this.yCount = ycount;// grid of column
        this.mineCount = minecount;// mine count


        Player p1 = new Player(1);
        Player p2 = new Player(2);//add if and else
        Player p3 = new Player(3);
        Player p4 = new Player(4);





        this.setTitle("扫雷");
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 20, xCount * GridComponent.gridSize + 250);
        this.setLocationRelativeTo(null);

        JPanel jf = new JPanel();
        /*
        jf.setPreferredSize(new Dimension(700, 700));
        JLabel jl3 = new JLabel(new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\minesweeper\\picture1.jpg"));
        jf.add(jl3);
        jf.setLocation(0,0);
        jl3.setBounds(50, 150, 700, 500);
        this.setVisible(true);

         */



        ScoreBoard scoreBoard;

        if (peoplenumber == 2) {
            controller = new GameController(p1, p2, turnnumber);
            scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);
        } else if (peoplenumber == 3) {
            controller = new GameController(p1, p2, p3, turnnumber);
            scoreBoard = new ScoreBoard(p1, p2, p3, xCount, yCount);
        } else {
            controller = new GameController(p1, p2, p3, p4, turnnumber);
            scoreBoard = new ScoreBoard(p1, p2, p3, p4, xCount, yCount);
        }
        GamePanel gamePanel = new GamePanel(xCount, yCount, mineCount);
        while (!check(gamePanel.getChessboardcheck())) {
            GamePanel gamePanel1 = new GamePanel(xCount, yCount, mineCount);
            gamePanel = gamePanel1;
        }

        Mineremained mineremained = new Mineremained(xCount, yCount, mineCount);


        scoreBoard.setBounds(0,500,300,150);
        scoreBoard.setFont(new Font("宋体", Font.BOLD, 36));


        controller.setGamePanel(gamePanel);
        controller.setScoreBoard(scoreBoard);
        controller.setMineremained(mineremained);

        mineremained.setBounds(0,660,300,150);

        this.add(gamePanel);
        this.add(scoreBoard);
        this.add(mineremained);




        //设置菜单
        JMenuBar jb = new JMenuBar();
        jf.add(jb);
        jb.setBounds(0, 0, 200, 30);
        jb.setBackground(Color.white);
        // 新建一个菜单选项
        JMenu jmenu = new JMenu("返回");

        //添加返回上一级
        jmenu.setBounds(0, 0,100,30);
        jmenu.setForeground(Color.black);
        jb.add(jmenu);
        JMenuItem jm = new JMenuItem("返回上一级");
        jmenu.add(jm);

        //添加返回主菜单
        jmenu.setBounds(0, 0,100,30);
        jmenu.setForeground(Color.black);
        jb.add(jmenu);
        JMenuItem  jm2= new JMenuItem("返回主菜单");
        jmenu.add(jm2);

        //添加对应方法
        jm.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     // public class HomePanel extends JFrame{
                                     new PreFrame1();
                                     jf.setVisible(false);
                                 }
                             });


        jm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // public class HomePanel extends JFrame{
                new FirstPage();
                jf.setVisible(false);
            }
        });
        jb.setVisible(true);
        this.add(jb);



        //设置背景颜色
       // JPanel BACK = new JPanel();
        //BACK.setBackground(new Color(55, 150, 59));
        //BACK.setLocation(0,0);
        //BACK.setSize(1920, 1080);


        JButton reset = new JButton("Reset");
        reset.setSize(200, 50);
        reset.setLocation(1300, 600);
        add(reset);
        reset.addActionListener(e -> {
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    controller.getGamePanel().getGrid(i, j).setStatus(GridStatus.Covered);
                    controller.getMineremained().setNumber(minecount);
                    controller.getMineremained().newthenumber();
                    repaint();
                }
            }
        });


        //mainframe类中加入restart按钮
        JButton restart = new JButton("Restart");
        restart.setSize(200, 50);
        restart.setLocation(1300, 660);
        add(restart);
        restart.addActionListener(e -> {
            controller.getGamePanel().setVisible(false);
            GamePanel gamePanel2 = new GamePanel(xCount, yCount, mineCount);
            gamePanel2.setVisible(true);
            controller.setGamePanel(gamePanel2);
            add(gamePanel2);
            controller.getGamePanel().repaint();
        });


        //mainframe类中加入cheat按钮
        JButton cheat = new JButton("Cheat");
        cheat.setSize(200, 50);
        cheat.setLocation(1300, 720);
        add(cheat);
        cheat.addActionListener(e -> {
            String str1 = JOptionPane.showInputDialog("enter the x location");
            String str2 = JOptionPane.showInputDialog("enter the y location");
            controller.getGamePanel().getGrid(Integer.parseInt(str1) - 1, Integer.parseInt(str2) - 1).setStatus(GridStatus.Clicked);
            repaint();
        });
        //cheat.setVisible(true);


        //mainframe类中加入click按钮
        JButton clickBtn = new JButton("Click");
        clickBtn.setSize(200, 50);
        clickBtn.setLocation(1300, 780);
        add(clickBtn);
        clickBtn.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(this, "input here");
            System.out.println("fileName :" + fileName);

//            controller.readFileData(fileName);
//            controller.writeDataToFile(fileName);
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JButton stop = new JButton("Stop the music");
        stop.addActionListener(e -> {
            MainFrame.musicTest.stop();
        });
        stop.setVisible(true);
        stop.setBounds(1300,190,150,100);

        //将窗口最大化
        setExtendedState(this.MAXIMIZED_BOTH);

        gamePanel.setVisible(true);
        scoreBoard.setVisible(true);


        JButton PlayMusic = new JButton("Play the music");
        jf.setLayout(null);
       PlayMusic.setVisible(true);
        PlayMusic.setBounds(1300,300,150,100);
        // start.setLocation(1, 5);
        //start.setSize(10, 6);
        setVisible(true);


        jf.setOpaque(false);
        jf.add(PlayMusic);


        PlayMusic.addActionListener(e -> {
            MainFrame.musicTest.playBackgound();
        });//此处需要写只能在stop之后play the music


        PlayMusic.setVisible(true);
        gamePanel.setVisible(true);
        scoreBoard.setVisible(true);

        this.add(mineremained);
        this.add(gamePanel);
        this.add(scoreBoard);
        this.add(jf);
        this.add(stop);
      //  this.add(BACK);
        this.add(PlayMusic, BorderLayout.LINE_START);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        //设置游戏背景图片
        Image image=new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\PictureTest\\3.jpg").getImage();
        JPanel n=new test(image);
        n.setBounds(0,0,1920,1080);

        //JPanel panel = new JPanel();
        //JLabel label = new JLabel();
        //ImageIcon img = new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\minesweeper\\封面.jpg");// 创建图片对象
        //label.setIcon(img);
        //panel.add(label);
        this.add(n);

    }




    public boolean check(int[][] chessboardCheck) {
        for (int i = 1; i < xCount + 1; i++) {
            for (int j = 1; j < yCount + 1; j++) {
                int n = 0;
                if (chessboardCheck[i - 1][j] == -1) {
                    n++;
                }
                if (chessboardCheck[i][j - 1] == -1) {
                    n++;
                }
                if (chessboardCheck[i + 1][j] == -1) {
                    n++;
                }
                if (chessboardCheck[i][j + 1] == -1) {
                    n++;
                }
                if (n == 4) {
                    return false;
                }
            }
        }
        return true;
    }
}


