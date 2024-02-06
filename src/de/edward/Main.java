package de.edward;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {


    Main (){
        System.out.println("HOI!\n");

        Chair c = new Chair(-8,0,-4, 2, 16, 16, 45);

        for (int i = 0; i <= 13; i++) {
            System.out.println(i + 1 + "."); //prints no. of point
            for (int j = 0; j <= 2; j++) {
                System.out.println(c.getP(i, j)); // 13 , 2
            }
            System.out.println();
        }
    }

// TODO: Speed up rendering of polygons
    private void draw() throws InterruptedException {
        Graphics g = this.getGraphics();
        g.setColor(Color.black);
        // IT WORKS!!!!! (to some degree)
        for (double l = 0 ; l <= 360*4 ; l += 0.25) {
            Chair c = new Chair(-15, 0, -4, 7, 16, 16, l);
            for (int i = 0; i <= 23; i++) {
                int[] px = {(int) (c.getPol(i, 0, 1) * 10)      + 400, (int) (c.getPol(i, 1, 1) * 10)      + 400, (int) (c.getPol(i, 2, 1) * 10)      + 400};
                int[] py = {(int) (c.getPol(i, 0, 2) * 10) * -1 + 400, (int) (c.getPol(i, 1, 2) * 10) * -1 + 400, (int) (c.getPol(i, 2, 2) * 10) * -1 + 400};
                g.drawPolygon(px, py, 3);
            }
            TimeUnit.MILLISECONDS.sleep(12);
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
