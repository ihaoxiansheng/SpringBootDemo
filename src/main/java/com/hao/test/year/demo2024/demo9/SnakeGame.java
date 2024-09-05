package com.hao.test.year.demo2024.demo9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * GPT贪吃蛇小游戏
 *
 * @author xu.liang
 * @since 2024/9/4 10:03
 */
public class SnakeGame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int DOT_SIZE = 10;
    private ArrayList<Point> snake;
    private Point food;
    private char direction;
    private boolean running;
    private boolean paused;
    private Timer timer;

    public SnakeGame() {
        snake = new ArrayList<>();
        resetGame();

        setTitle("贪吃蛇");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        // 设置窗口在屏幕中央
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        add(panel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (running) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (direction != 'D') direction = 'U';
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') direction = 'D';
                            break;
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') direction = 'L';
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') direction = 'R';
                            break;
                        case KeyEvent.VK_SPACE:
                            // 继续/暂停游戏
                            if (paused) {
                                paused = false;
                                running = true;
                            } else if (running) {
                                paused = true;
                                running = false;
                            } else {
                                resetGame();
                            }
                            panel.repaint();
                            break;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    resetGame();
                }
            }
        });

        timer = new Timer(100, e -> {
            if (running) {
                move();
                checkCollision();
                checkFood();
                panel.repaint();
            }
        });
        timer.start();
    }

    private void resetGame() {
        running = true;
        paused = false;
        if (food == null) {
            snake.clear();
            snake.add(new Point(5, 5));
            direction = 'R';
            spawnFood();
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / DOT_SIZE);
        int y = rand.nextInt(HEIGHT / DOT_SIZE);
        food = new Point(x, y);
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);
        switch (direction) {
            case 'U':
                newHead.translate(0, -1);
                break;
            case 'D':
                newHead.translate(0, 1);
                break;
            case 'L':
                newHead.translate(-1, 0);
                break;
            case 'R':
                newHead.translate(1, 0);
                break;
        }
        snake.add(0, newHead);
        snake.remove(snake.size() - 1);
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH / DOT_SIZE || head.y < 0 || head.y >= HEIGHT / DOT_SIZE) {
            running = false;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }
    }

    private void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            snake.add(new Point(food));
            spawnFood();
        }
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (running || paused) {
                g.setColor(Color.RED);
                g.fillRect(food.x * DOT_SIZE, food.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);
                g.setColor(Color.GREEN);
                for (Point p : snake) {
                    g.fillRect(p.x * DOT_SIZE, p.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);
                }
            }
            if (paused) {
                showPauseGame(g);
            } else if (!running) {
                showGameOver(g);
            }
        }

        private void showGameOver(Graphics g) {
            String message = "游戏结束！按空格重新开始";
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);

            // 获取窗口的宽度和高度
            int windowWidth = getWidth();
            int windowHeight = getHeight();

            // 获取文字的宽度和高度
            FontMetrics metrics = g.getFontMetrics(font);
            int messageWidth = metrics.stringWidth(message);
            int messageHeight = metrics.getHeight();

            // 计算文字绘制的起始位置，使文字居中
            int x = (windowWidth - messageWidth) / 2;
            int y = (windowHeight - messageHeight) / 2 + metrics.getAscent();
            g.setColor(Color.BLACK);
            g.drawString(message, x, y);
        }

        private void showPauseGame(Graphics g) {
            String message = "游戏暂停！按空格重新开始";
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);

            // 获取窗口的宽度和高度
            int windowWidth = getWidth();
            int windowHeight = getHeight();

            // 获取文字的宽度和高度
            FontMetrics metrics = g.getFontMetrics(font);
            int messageWidth = metrics.stringWidth(message);
            int messageHeight = metrics.getHeight();

            // 计算文字绘制的起始位置，使文字居中
            int x = (windowWidth - messageWidth) / 2;
            int y = (windowHeight - messageHeight) / 2 + metrics.getAscent();
            g.setColor(Color.BLACK);
            g.drawString(message, x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
