package com.hao.test.ballgame;

import javax.swing.*;

/**
 * @author xu.liang
 * @since 2022/10/19 10:49
 */
public class BallGame extends JFrame {

    /** 窗口加载 */
    void launchFrame() {
        setSize(300, 300);
        setLocation(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("这个游戏项目让大家体验编程的快感，寓教于乐！");
        BallGame game = new BallGame();
        game.launchFrame();
    }
}
