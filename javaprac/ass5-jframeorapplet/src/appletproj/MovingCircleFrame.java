package appletproj;

import javax.swing.*;
import java.awt.*;

public class MovingCircleFrame extends JPanel implements Runnable {
    int x = 0;
    int direction = 1;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, 80, 50, 50);
    }

    public void run() {
        while (true) {
            x += direction * 5;
            if (x < 0 || x > getWidth() - 50) {
                direction *= -1;
            }
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Circle Animation");
        MovingCircleFrame panel = new MovingCircleFrame();
        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(panel).start();
    }
}
