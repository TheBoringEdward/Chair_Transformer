package de.edward;
import java.awt.*;

public class TransformerAndProjector {
    public Color[] polColour;
    // Origin-of-the-chair Movement
    double Xm;
    double Ym;
    double Zm;
    // Distance of the "lens" from the origin
    double Fov;
    // Screen width
    double width;
    // Screen height
    double height;
    double zRotation;
    double xRotation;
    double yRotation;
    double[][][] pol;
    Models m = new Models();

    TransformerAndProjector(double Xm, double Ym, double Zm, double Fov, double width, double height, double zRotation, double yRotation, double xRotation, String model){
        this.Xm = Xm;
        this.Ym = Ym;
        this.Zm = Zm;
        // The camera always assumes, that the chair is in front of the camera.
        // God knows what might happen, if this condition isn't met.
        this.Fov = Fov;
        // Have to adjust for the centre of the screen
        this.width = width/2;
        this.height = height/2;
        this.zRotation = zRotation - 90;
        this.xRotation = xRotation + 180;
        this.yRotation = yRotation + 180;
        this.pol = m.getPol(model);
        this.polColour = m.getColour(model);
    }
    
    // z-rotation
    private double zRotatedPolX(int i, int j){
        return Math.sqrt(Math.pow(pol[i][j][0],2) + Math.pow(pol[i][j][1],2)) * Math.cos(Math.toRadians(180 - Math.toDegrees(Math.atan2(pol[i][j][0],pol[i][j][1])) - zRotation));
    }

    private double zRotatedPolY(int i, int j){
        return Math.sqrt(Math.pow(pol[i][j][0],2) + Math.pow(pol[i][j][1],2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(pol[i][j][0],pol[i][j][1])) - zRotation));
    }

    // x-rotation
    private double xRotatedPolZ(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolY(i,j),2) + Math.pow(pol[i][j][2],2)) * Math.cos(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolY(i,j),pol[i][j][2])) - xRotation));
    }

    private double xRotatedPolY(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolY(i,j),2) + Math.pow(pol[i][j][2],2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolY(i,j),pol[i][j][2])) - xRotation));
    }

    //y-rotation
    private double yRotatedPolZ(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolX(i,j),2) + Math.pow(xRotatedPolZ(i,j),2)) * Math.cos(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolX(i,j),xRotatedPolZ(i,j))) - yRotation));
    }

    private double yRotatedPolX(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolX(i,j),2) + Math.pow(xRotatedPolZ(i,j),2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolX(i,j),xRotatedPolZ(i,j))) - yRotation));
    }

    // The way it probes the x-coordinates is seriously bad.
    // It ALWAYS assumes, the camera facing in the negative x-direction
    public double getPol(int i,int j, int k){
        if (k == 0){
            return yRotatedPolX(i,j) + Xm;
        } else if (k == 1){
            return (((xRotatedPolY(i,j) + Ym) * Fov) / (Fov + ((yRotatedPolX(i,j) + Xm) * -1))) * width;
        } else {
            return (((yRotatedPolZ(i,j) + Zm) * Fov) / (Fov + ((yRotatedPolX(i,j) + Xm) * -1))) * height;
        }
    }

}
