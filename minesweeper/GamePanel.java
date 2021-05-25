package minesweeper;

import components.GridComponent;
import components.test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private GridComponent[][] mineField;
    private int[][] chessboard;
    private int[][] chessboardcheck;
    private final Random random = new Random();
    Image image=new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\minesweeper\\封面.jpg").getImage();
    JPanel n=new test(image);

    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("C:\\Users\\熙\\IdeaProjects\\Lab5\\project\\src\\minesweeper\\封面.jpg");// 创建图片对象



    public int[][] getChessboardcheck() {
        return chessboardcheck;
    }

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */
    public GamePanel(int xCount, int yCount, int mineCount) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        this.setLocation(600,20);
        initialGame(xCount, yCount, mineCount);

        repaint();
    }

    public void initialGame(int xCount, int yCount, int mineCount) {
        mineField = new GridComponent[xCount][yCount];
        int n=0;
        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j,false);
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
            }
        }
    }


    public void generateChessBoard(int xCount, int yCount, int mineCount) {
        //todo: generate chessboard by your own algorithm
        chessboard = new int[xCount][yCount];
        chessboardcheck = new int[xCount+2][yCount+2];
        int swaptimes = 2*xCount*yCount;

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                chessboard[i][j]=0;
            }
        }
        int a = mineCount/yCount;
        int b = mineCount - a*xCount;
        if (a != 0) {
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < a; j++) {
                    chessboard[i][j] = -1;
                }
            }
            for (int i = 0; i < b; i++) {
                for (int j = a ; j < a+1; j++) {
                    chessboard[i][j] = -1;
                }
            }
        }
        else {
            for (int i = 0; i < b; i++) {
                chessboard[i][0]=-1;
            }
        }
        for (int i = 0; i < swaptimes; i++) {
            Random random = new Random();
            int x1 = random.nextInt(xCount-1);
            int y1 = random.nextInt(yCount-1);
            int x2 = random.nextInt(xCount-1);
            int y2 = random.nextInt(yCount-1);
            int change = chessboard[x1][y1];
            chessboard[x1][y1]=chessboard[x2][y2];
            chessboard[x2][y2]=change;
        }
//        for (int i = 0; i < xCount; i++) {
//            for (int j = 0; j < yCount; j++) {
//                if (chessboard[i][j] != -1) {
//                    chessboard[i][j] = 0;
//                }
//            }
//        }
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                chessboardcheck[i+1][j+1]=chessboard[i][j];
            }
        }
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                judge(chessboard[i][j], chessboard, i , j, xCount, yCount);
            }
        }
    }



    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


private static void judge(int x, int[][] y, int i, int j, int xCount , int yCount) {
        if (x == -1) {
        if (i > 0 && j > 0 && i < xCount - 1 && j < yCount - 1) {//the center
        y[i - 1][j - 1] = judge2(y[i - 1][j - 1]);
        y[i - 1][j] = judge2(y[i - 1][j]);
        y[i - 1][j + 1] = judge2(y[i - 1][j + 1]);
        y[i][j - 1] = judge2(y[i][j - 1]);
        y[i][j + 1] = judge2(y[i][j + 1]);
        y[i + 1][j - 1] = judge2(y[i + 1][j - 1]);
        y[i + 1][j] = judge2(y[i + 1][j]);
        y[i + 1][j + 1] = judge2(y[i + 1][j + 1]);
        }
        if (i == 0 && j == 0 && i < xCount - 1 && j < yCount - 1) {//the left up corner
        y[i][j + 1] = judge2(y[i][j + 1]);
        y[i + 1][j] = judge2(y[i + 1][j]);
        y[i + 1][j + 1] = judge2(y[i + 1][j + 1]);
        }
        if (i == 0 && j > 0 && i < xCount - 1 && j < yCount - 1) {//the up edge
        y[i][j - 1] = judge2(y[i][j - 1]);
        y[i][j + 1] = judge2(y[i][j + 1]);
        y[i + 1][j - 1] = judge2(y[i + 1][j - 1]);
        y[i + 1][j] = judge2(y[i + 1][j]);
        y[i + 1][j + 1] = judge2(y[i + 1][j + 1]);
        }
        if (i > 0 && j == 0 && i < xCount - 1 && j < yCount - 1) {// the right edge
        y[i - 1][j] = judge2(y[i - 1][j]);
        y[i - 1][j + 1] = judge2(y[i - 1][j + 1]);
        y[i][j + 1] = judge2(y[i][j + 1]);
        y[i + 1][j] = judge2(y[i + 1][j]);
        y[i + 1][j + 1] = judge2(y[i + 1][j + 1]);
        }
        if (i > 0 && j > 0 && i == xCount - 1 && j == yCount - 1) {//the right low corner
        y[i - 1][j - 1] = judge2(y[i - 1][j - 1]);
        y[i - 1][j] = judge2(y[i - 1][j]);
        y[i][j - 1] = judge2(y[i][j - 1]);
        }
        if (i > 0 && j > 0 && i == xCount - 1 && j < yCount - 1) {//the low edge
        y[i - 1][j - 1] = judge2(y[i - 1][j - 1]);
        y[i - 1][j] = judge2(y[i - 1][j]);
        y[i - 1][j + 1] = judge2(y[i - 1][j + 1]);
        y[i][j - 1] = judge2(y[i][j - 1]);
        y[i][j + 1] = judge2(y[i][j + 1]);
        }
        if (i > 0 && j > 0 && i < xCount - 1 && j == yCount - 1) {//the right edge
        y[i - 1][j - 1] = judge2(y[i - 1][j - 1]);
        y[i - 1][j] = judge2(y[i - 1][j]);
        y[i][j - 1] = judge2(y[i][j - 1]);
        y[i + 1][j - 1] = judge2(y[i + 1][j - 1]);
        y[i + 1][j] = judge2(y[i + 1][j]);
        }
        if (i > 0 && j == 0 && i == xCount - 1 && j < yCount - 1) {//the left low corner
        y[i - 1][j] = judge2(y[i - 1][j]);
        y[i - 1][j + 1] = judge2(y[i - 1][j + 1]);
        y[i][j + 1] = judge2(y[i][j + 1]);
        }
        if (i == 0 && j > 0 && i < xCount - 1 && j == yCount - 1) {
        y[i][j - 1] = judge2(y[i][j - 1]);
        y[i + 1][j - 1] = judge2(y[i + 1][j - 1]);
        y[i + 1][j] = judge2(y[i + 1][j]);
        }
        }
        }

private static int judge2(int z) {
        if (z != -1) {
        return z += 1;
        }
        return z;
        }
        }

