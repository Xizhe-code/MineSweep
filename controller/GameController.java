package controller;

import minesweeper.GamePanel;
import entity.Player;
import minesweeper.Mineremained;
import minesweeper.ScoreBoard;




public class GameController {

    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private int n ;
    private int b =0;

    private Player onTurn ;

    private GamePanel gamePanel;
    private ScoreBoard scoreBoard;
    private Mineremained mineremained;

    public Mineremained getMineremained() {
        return mineremained;
    }

    public int getN() {
        return n;
    }

    public GameController(Player p1, Player p2, int n) {
        this.init(p1, p2);
        this.onTurn = p1;
        this.n = n;
    }

    public GameController(Player p1, Player p2, Player p3, int n) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.onTurn = p1;
        this.n = n;
    }

    public GameController(Player p1, Player p2, Player p3, Player p4, int n) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.onTurn = p1;
        this.n = n;
    }


    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;


        //TODO: 在初始化游戏的时候，还需要做什么？
    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {
        b++;
        if (b%n != 0){
            scoreBoard.update();
        }
        else if (onTurn == p1) {
            onTurn = p2;
        } else if (onTurn == p2) {
            if (p3!=null){
                onTurn = p3;
            }else {
                onTurn = p1;
            }
        }else if (onTurn == p3){
            if (p4!=null){
                onTurn =p4;
            }else onTurn=p1;
        }else if (onTurn==p4){
            onTurn = p1;
        }
        System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
        scoreBoard.update();
    }


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        return onTurn;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void setMineremained(Mineremained mineremained) {
        this.mineremained = mineremained;
    }

    public void readFileData(String fileName) {
        //todo: read date from file

    }

    public void writeDataToFile(String fileName){
        //todo: write data into file
    }
}
