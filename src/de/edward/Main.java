package de.edward;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.ArrayList;
// Leave this in! This will be used during debugging // well thaent sidt weways


public class Main extends JFrame {


    public Main(){

        super("Chair");
        super.setBounds(0, 0, 800, 800);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);

        System.out.println("\nHOI!\n");
    }

    public static void main(String[] args) {
        final Main m = new Main();
        final Draw draw = new Draw();
        draw.setBounds(0, 0, 800, 800);
        draw.setVisible(true);

        m.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W){
                    draw.incCurrentYDegrees(5);
                }
                if (e.getKeyCode() == KeyEvent.VK_S){
                    draw.incCurrentYDegrees(-5);
                }
                if (e.getKeyCode() == KeyEvent.VK_A){
                    draw.incCurrentZDegrees(5);
                }
                if (e.getKeyCode() == KeyEvent.VK_D){
                    draw.incCurrentZDegrees(-5);
                }
                if (e.getKeyCode() == KeyEvent.VK_E){
                    draw.incCurrentXDegrees(-5);
                }
                if (e.getKeyCode() == KeyEvent.VK_Q){
                    draw.incCurrentXDegrees(5);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        m.add(draw);
        m.setVisible(true);
        System.out.println("\n\n ======= This code has been provided by TheBoringEdward with the help of a fellow =======\n");
    }

    private static final class Draw extends JLabel implements Runnable {

        private double currentDegrees;
        private double currentXDegrees;
        private double currentYDegrees;
        private double currentZDegrees;

        public Draw() {
            // start calculating task
            final ScheduledExecutorService taskExecutor = Executors.newScheduledThreadPool(1);
            // taskExecutor.scheduleAtFixedRate(this, 0, 15, TimeUnit.MILLISECONDS);
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final TransformerAndProjector c = new TransformerAndProjector(-15, 0, -4, 7, 16, 16,currentZDegrees, currentYDegrees, currentXDegrees, "chair");
            ArrayList<int[]> visiblePolygonsX = new ArrayList<>();
            ArrayList<int[]> visiblePolygonsY = new ArrayList<>();
            ArrayList<Double> distanceOfVisible = new ArrayList<>();
            ArrayList<Integer> polyOrder = new ArrayList<>();
            for (int i = 0; i <= c.pol.length-1; i++) {
                int[] px = {(int) ((c.getProjectedPol(i, 0, 1) * 10) + 400), (int) ((c.getProjectedPol(i, 1, 1) * 10) + 400), (int) ((c.getProjectedPol(i, 2, 1) * 10) + 400)};
                int[] py = {(int) ((c.getProjectedPol(i, 0, 2) * 10) * -1 + 400), (int) ((c.getProjectedPol(i, 1, 2) * 10) * -1 + 400), (int) ((c.getProjectedPol(i, 2, 2) * 10) * -1 + 400)};
                // back-face culling / winding order
                int mass =
                        ( ((px[1]-px[0]) * ((py[1] + py[0])/2))
                                + ((px[2]-px[1]) * ((py[2] + py[1])/2))
                                + ((px[0]-px[2]) * ((py[0] + py[2])/2)))
                        ;
                if (mass > 0) {
                    //g.drawPolygon(px,py,3);
                    visiblePolygonsX.add(px);
                    visiblePolygonsY.add(py);
                    distanceOfVisible.add(c.getMidDistance(i));
                    polyOrder.add(i);
                    //g.setColor(c.polColour[i]);
                    //g.fillPolygon(px, py, 3);
                }
                //g.setColor(Color.BLACK);
            }

            // this is horrible
            // bubble sorter, to order visible polygons by distance to the camera
            int n = polyOrder.toArray().length;
            for (int i = 0; i < n - 1; i++){
                for (int j = 0; j < n - i - 1; j++){
                    if(distanceOfVisible.get(j) > distanceOfVisible.get(j + 1)){
                        double tempDist = distanceOfVisible.get(j);
                        int[] tempVPX = visiblePolygonsX.get(j);
                        int[] tempVPY = visiblePolygonsY.get(j);
                        int tempOrd = polyOrder.get(j);
                        distanceOfVisible.set(j,distanceOfVisible.get(j+1));
                        distanceOfVisible.set(j+1,tempDist);
                        visiblePolygonsX.set(j,visiblePolygonsX.get(j+1));
                        visiblePolygonsX.set(j+1,tempVPX);
                        visiblePolygonsY.set(j,visiblePolygonsY.get(j+1));
                        visiblePolygonsY.set(j+1,tempVPY);
                        polyOrder.set(j,polyOrder.get(j+1));
                        polyOrder.set(j+1,tempOrd);
                    }
                }
            }
            // TODO: Either learn how to use openGL, or make your own rasterisation-algorithm, as to actually
            //       fix the depth-issue

            for (int i = n - 1; i >= 0; i--){
                g.setColor(c.polColour[polyOrder.get(i)]);
                g.fillPolygon(visiblePolygonsX.get(i), visiblePolygonsY.get(i), 3);
                g.setColor(Color.BLACK);
                g.drawPolygon(visiblePolygonsX.get(i),visiblePolygonsY.get(i),3);
            }
            g.setColor(Color.BLUE);
            g.drawString("Visible polygons: " + n,20,20);
            g.drawString("Current degrees: " + currentDegrees, 20, 40);
            g.drawString("Current X degrees: " + currentXDegrees, 20, 60);
            g.drawString("Current Y degrees: " + currentYDegrees, 20, 80);
            g.drawString("Current Z degrees: " + currentZDegrees, 20, 100);
            repaint();
        }

        public void incCurrentDegrees(double currentDegrees){
            this.currentDegrees += currentDegrees;
        }
        public void incCurrentXDegrees(double currentXDegrees){
            this.currentXDegrees += currentXDegrees;
        }
        public void incCurrentYDegrees(double currentYDegrees){
            this.currentYDegrees += currentYDegrees;
        }
        public void incCurrentZDegrees(double currentZDegrees){
            this.currentZDegrees += currentZDegrees;
        }
        // this might fuck something up in the future
        @Override
        public void run() {
            currentDegrees += 0.25;
            if (currentDegrees > 360){
                currentDegrees = 1;
            }
            if (currentDegrees < 0){
                currentDegrees = 359;
            }
        }
    }
}