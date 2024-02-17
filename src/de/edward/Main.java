package de.edward;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
// Leave this in! This will be used during debugging
import java.awt.Color;

public class Main extends JFrame {


    public Main(){

        super("Chair");
        super.setBounds(0, 0, 800, 800);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);

        System.out.println("HOI!\n");

        // This bit only exists for potential debugging purposes.
        // ATM it hasn't assisted me at all, but you may never know
        Chair c = new Chair(-8,0,-4, 2, 16, 16, 45, 0, 0);
        for (int i = 0 ; i <= c.p.length-1 ; i++) {
            // prints no. of point
            System.out.println(i + 1 + ".");
            for (int j = 0; j <= 2; j++) {
                System.out.println(c.getP(i, j)); // 13 , 2
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final Main m = new Main();
        final Draw draw = new Draw();
        draw.setBounds(0, 0, 800, 800);
        draw.setVisible(true);
        m.add(draw);
        m.setVisible(true);
        System.out.println("\n\n ======= This code has been provided by TheBoringEdward with the help of a fellow =======\n");
    }

// TODO: Speed up rendering of polygons

    private static final class Draw extends JLabel implements Runnable {

        private double currentDegrees;

        public Draw() {
            // start calculating task
            final ScheduledExecutorService taskExecutor = Executors.newScheduledThreadPool(1);
            taskExecutor.scheduleAtFixedRate(this, 0, 15, TimeUnit.MILLISECONDS);
        }


        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final Chair c = new Chair(-15, 0, -4, 7, 16, 16,currentDegrees, 0, 0);
            for (int i = 0; i <= c.pol.length-1; i++) {
                int[] px = {(int) ((c.getPol(i, 0, 1) * 10) + 400), (int) ((c.getPol(i, 1, 1) * 10) + 400), (int) ((c.getPol(i, 2, 1) * 10) + 400)};
                int[] py = {(int) ((c.getPol(i, 0, 2) * 10) * -1 + 400), (int) ((c.getPol(i, 1, 2) * 10) * -1 + 400), (int) ((c.getPol(i, 2, 2) * 10) * -1 + 400)};
                // back-face culling / winding order
                int mass =
                        ( ((px[1]-px[0]) * ((py[1] + py[0])/2))
                        + ((px[2]-px[1]) * ((py[2] + py[1])/2))
                        + ((px[0]-px[2]) * ((py[0] + py[2])/2)))
                        ;
                if (mass > 0) {
                    //g.drawPolygon(px,py,3);
                    g.setColor(c.polColour[i]);
                    g.fillPolygon(px, py, 3);
                }
                //g.setColor(Color.BLACK);
            }
            repaint();
        }

        @Override
        public void run() {
            currentDegrees += 0.25;
        }
    }
}
