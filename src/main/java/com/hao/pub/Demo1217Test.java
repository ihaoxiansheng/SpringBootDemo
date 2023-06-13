package com.hao.pub;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 用java实现自动控制键盘打字
 * <p>为了在 Java 中实现自动控制键盘打字，你可以使用 java.awt.Robot 类。该类提供了一系列的方法，可以用来模拟键盘和鼠标事件。
 *
 * @author xu.liang
 * @since 2022/12/17 16:54
 */
public class Demo1217Test {

    public static void main(String[] args) {
        try {
            // 创建一个 Robot 实例
            Robot robot = new Robot();
            robot.delay(2000);

            // 按下鼠标
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            // 在键盘上输入 "Hello, World!"
            robot.keyPress(KeyEvent.VK_H);
            robot.keyPress(KeyEvent.VK_E);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_L);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyPress(KeyEvent.VK_O);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_COMMA);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_W);
            robot.keyPress(KeyEvent.VK_O);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyPress(KeyEvent.VK_L);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyPress(KeyEvent.VK_EXCLAMATION_MARK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
