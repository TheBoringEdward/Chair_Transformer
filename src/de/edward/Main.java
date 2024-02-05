package de.edward;
import java.lang.Math;
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

        Chair c = new Chair(-8,0,-4, 2, 16, 16, 45);

        for (int i = 0; i <= 13; i++) {
            System.out.println(i + 1 + "."); //prints no. of point
            for (int j = 0; j <= 2; j++) {
                System.out.println(c.getP(i, j)); // 13 , 2
            }
            System.out.println("");
        }
    }



    private Graphics g;

    /*
    private void draw() throws InterruptedException {
        g = this.getGraphics();
        g.setColor(Color.black);
        for (int k = 0 ; k < 180 ; k++) {
            Chair c = new Chair(-8,0,-4, 2, 16, 16, k);
            for (int i = 0; i <= 13; i++) {
                System.out.println(i + 1 + "."); //prints no. of point
                for (int j = 0; j <= 2; j++) {
                    System.out.println(c.getP(i, j)); // 13 , 2
                    if (j == 2) {
                        g.fillOval((((int) c.getP(i, j - 1) * 10) + 400), ((int) c.getP(i, j) * 10) * -1 + 400, 5, 5);
                    }
                }
                System.out.println("");
            }
            TimeUnit.MILLISECONDS.sleep(500);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,800,800);
            g.setColor(Color.BLACK);
        }
    }


    @Override public void paint(Graphics g){
        setBackground(Color.white);
        try {
            draw();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

     */

    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        /*
        m.setSize(800,800);
        m.setResizable(false);
        m.setTitle("Brick1");
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.draw();

         */
        System.out.println("\n\n ======= This code has been provided by TheBoringEdward =======\n");
    }
}
