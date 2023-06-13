package com.hao.test.ballgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author xu.liang
 * @since 2022/10/19 10:36
 */
public class BallGame2 extends JFrame {

    Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");
    Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");

    /**
     * 小球的横坐标
     */
    double x = 100;

    /**
     * 小球的纵坐标
     */
    double y = 100;

    /**
     * 弧度，此处就是60度
     */
    double degree = 3.14 / 3;

    /**
     * 画窗口的方法：加载小球与桌面
     */
    @Override
    public void paint(Graphics g) {
        System.out.println("窗口被画了一次! ");
        g.drawImage(desk, 0, 0, null);
        g.drawImage(ball, (int) x, (int) y, null);

        x = x + 10 * Math.cos(degree);
        y = y + 10 * Math.sin(degree);

        // 500是窗口高度, 40是桌子边框, 30是球直径, 最后一个40是标题栏的高度
        if (y > 500 - 40 - 30 || y < 40 + 40) {
            // 小球触碰上下边界时，只改变纵坐标方向
            degree = -degree;
        }

        if (x < 40 || x > 856 - 40 - 30) {
            // 小球触碰左右边界时，只改变横坐标方向
            degree = 3.14 - degree;
        }

    }

    /**
     * 窗口加载
     */
    void launchFrame() {
        // 窗口大小
        setSize(856, 500);
        // 窗口位置
        setLocation(50, 50);
        // 显示窗口
        setVisible(true);

        // 重画窗口
        while (true) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BallGame2 ballgame = new BallGame2();
        ballgame.launchFrame();
    }

}
