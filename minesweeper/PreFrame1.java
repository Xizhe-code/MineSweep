package minesweeper;

import PictureTest.SecondPage;
import components.test;

import javax.swing.*;
import java.awt.*;

public class PreFrame1 extends JFrame {

    private JButton plainJButtoneasy;
    private JButton plainJButtonmiddle;
    private JButton plainJButtonhard;
    private JButton plainJButtonselfDefined;

    private MainFrame framefor;

    private MainFrame frame;
    public PreFrame1() {
        this.setTitle("Please select the difficulty of the game");
        JPanel jp = new JPanel();

        //设置游戏背景图片
        Image image=new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\minesweeper\\Second.jpg").getImage();
        JPanel n=new test(image);
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\minesweeper\\Second.jpg");// 创建图片对象
        label.setIcon(img);
        panel.add(label);
        add(n);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// JFrame最大化
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 让JFrame的关闭按钮起作用
        setVisible(true);// 显示JFrame
        JButton start = new JButton("开始游戏");
        n.setLayout(null);
        start.setVisible(true);
        start.setBounds(80,800,500,100);
        // start.setLocation(1, 5);
        //start.setSize(10, 6);
        setVisible(true);

       // jp.setLayout(new GridLayout(4, 1));
        jp.setLayout(null);

        plainJButtoneasy = new JButton("Easy");
        jp.add(plainJButtoneasy);
       plainJButtoneasy.setBounds(150,400,500,100);
        plainJButtoneasy.setFont(new Font("宋体", Font.BOLD, 36));

        plainJButtonmiddle = new JButton("Middle");
        jp.add(plainJButtonmiddle);
        plainJButtonmiddle.setBounds(150,510,500,100);
        plainJButtonmiddle.setFont(new Font("宋体", Font.BOLD, 36));

        plainJButtonhard = new JButton("Hard");
        jp.add(plainJButtonhard);
        plainJButtonhard.setBounds(150,620,500,100);
        plainJButtonhard.setFont(new Font("宋体", Font.BOLD, 36));

        plainJButtonselfDefined = new JButton("Custom");
        jp.add(plainJButtonselfDefined);
        plainJButtonselfDefined.setBounds(150,730,500,100);
        plainJButtonselfDefined.setFont(new Font("宋体", Font.BOLD, 36));

        plainJButtoneasy.addActionListener(e -> {
            String str4 = JOptionPane.showInputDialog("enter the number of the turn");
            String str5 = JOptionPane.showInputDialog("enter the number of the people");
            cancel(10,10,15  , Integer.parseInt(str4),Integer.parseInt(str5));

        });
        plainJButtonmiddle.addActionListener( e -> {
            String str4 = JOptionPane.showInputDialog("enter the number of the turn");
            String str5 = JOptionPane.showInputDialog("enter the number of the people");
            cancel(20,15,40, Integer.parseInt(str4),Integer.parseInt(str5));
        });
        plainJButtonhard.addActionListener( e -> {
            String str4 = JOptionPane.showInputDialog("enter the number of the turn");
            String str5 = JOptionPane.showInputDialog("enter the number of the people");
            cancel(25,20,19, Integer.parseInt(str4),Integer.parseInt(str5));
        } );
        plainJButtonselfDefined.addActionListener( e -> {
            String str1 = JOptionPane.showInputDialog("PLease enter the x");
            String str2 = JOptionPane.showInputDialog("PLease enter the y");
            String str3 = JOptionPane.showInputDialog("PLease enter the number of the mines");
            String str4 = JOptionPane.showInputDialog("Please enter the number of the turn");
            String str5 = JOptionPane.showInputDialog("Please enter the number of the people");
            cancel(Integer.parseInt(str1),Integer.parseInt(str2),Integer.parseInt(str3),Integer.parseInt(str4),Integer.parseInt(str5));
        } );

        add(jp);
    }

    public void cancel(int x, int y, int mineCount, int turnnumber, int peoplenumber) {
        setVisible(false);
        frame = new MainFrame(x,y,mineCount,turnnumber,peoplenumber);
        frame.setVisible(true);
        //GamePanel panel = new GamePanel(x,y,radio);
    }
}
