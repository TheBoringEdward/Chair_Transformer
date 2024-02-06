package de.edward;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {


    Main (){
        System.out.println("HOI!\n");
        /*
        // poof of concept
        System.out.println(Math.pow(5,2) + Math.pow(3,2)); // dist
        System.out.println((Math.atan2(5,3)) * (180/Math.PI)); // deg
        System.out.println(180 - (Math.atan2(5,3)) * (180/Math.PI) - 45); // rev deg
        //System.out.println(Math.sin(Math.toRadians(75.96)));
        System.out.println(Math.sqrt(Math.pow(5,2) + Math.pow(3,2)) * Math.sin(Math.toRadians(180 - (Math.atan2(5,3)) * (180/Math.PI) - 45))); // x pos
        System.out.println(Math.sqrt((Math.pow(5,2) + Math.pow(3,2)) - Math.pow(Math.sqrt(Math.pow(5,2) + Math.pow(3,2)) * Math.sin(Math.toRadians(180 - (Math.atan2(5,3)) * (180/Math.PI) - 45)) , 2)) * -1); // y pos
        System.out.println("");
         */

        Chair c = new Chair(-8,0,-4, 2, 16, 16, 0);

        for (int i = 0; i <= 13; i++) {
            System.out.println(i + 1 + "."); //prints no. of point
            for (int j = 0; j <= 2; j++) {
                System.out.println(c.getP(i, j)); // 13 , 2
            }
            System.out.println();
        }
    }


    private void draw() throws InterruptedException {
        Graphics g = this.getGraphics();
        g.setColor(Color.black);
        for (double l = 0 ; l <= 360*4 ; l += 0.25) {
            Chair c = new Chair(-15, 0, -4, 7, 16, 16, l);
        /*
        for (int i = 0; i <= 13; i++) {
                //System.out.println(i + 1 + "."); //prints no. of point
            for (int j = 0; j <= 2; j++) {
                    //System.out.println(c.getP(i, j)); // 13 , 2
                if (j == 2) {
                    g.fillOval((int)(( c.getP(i, j - 1) * 10) + 400), (int)( c.getP(i, j) * 10) * -1 + 400, 5, 5);
                }
            }
            System.out.println("");
        }

         */
            repaint();
            // IT WORKS!!!!! (to some degree)
            for (int i = 0; i <= 23; i++) {
                int[] px = {(int) (c.getPol(i, 0, 1) * 10)      + 400, (int) (c.getPol(i, 1, 1) * 10)      + 400, (int) (c.getPol(i, 2, 1) * 10)      + 400};
                int[] py = {(int) (c.getPol(i, 0, 2) * 10) * -1 + 400, (int) (c.getPol(i, 1, 2) * 10) * -1 + 400, (int) (c.getPol(i, 2, 2) * 10) * -1 + 400};
                g.drawPolygon(px, py, 3);
            }
            TimeUnit.MILLISECONDS.sleep(12);
            /*
            g.setColor(Color.WHITE);
            g.fillRect(0,0,800,800);
            g.setColor(Color.BLACK);

             */
        }
        // proof of concept
        /*
        int x[] = {200, 400, 200};
        int y[] = {200, 600, 800};
        g.drawPolygon(x, y, 3);
         */
    }


    @Override public void paint(Graphics g){
        setBackground(Color.white);
        try {
            draw();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.setSize(800,800);
        m.setResizable(false);
        m.setTitle("Chair");
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //m.draw();
        System.out.println("\n\n ======= This code has been provided by TheBoringEdward =======\n");
    }
}
