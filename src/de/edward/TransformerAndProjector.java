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

    //xRotatedPolY
    private double FinalRotatedY(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolY(i,j),2) + Math.pow(pol[i][j][2],2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolY(i,j),pol[i][j][2])) - xRotation));
    }

    //y-rotation
    //yRotatedPolZ
    private double FinalRotatedZ(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolX(i,j),2) + Math.pow(xRotatedPolZ(i,j),2)) * Math.cos(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolX(i,j),xRotatedPolZ(i,j))) - yRotation));
    }

    //yRotatedPolX
    private double FinalRotatedX(int i, int j){
        return Math.sqrt(Math.pow(zRotatedPolX(i,j),2) + Math.pow(xRotatedPolZ(i,j),2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(zRotatedPolX(i,j),xRotatedPolZ(i,j))) - yRotation));
    }

    // The way it probes the x-coordinates is seriously bad.
    // It ALWAYS assumes, the camera facing in the negative x-direction
    public double getProjectedPol(int i, int j, int k){
        if (k == 0){
            return FinalRotatedX(i,j) + Xm;
        } else if (k == 1){
            return (((FinalRotatedY(i,j) + Ym) * Fov) / (Fov + ((FinalRotatedX(i,j) + Xm) * -1))) * width;
        } else {
            return (((FinalRotatedZ(i,j) + Zm) * Fov) / (Fov + ((FinalRotatedX(i,j) + Xm) * -1))) * height;
        }
    }
    // i = polygon of model, j = point of polygon, k = x0/x1/x2 coord of point

    public double[] getMid(int i){
        //x0+x1+x2/3=midX
        double midX = (FinalRotatedX(i,0) + Xm) + (FinalRotatedX(i,1) + Xm) + (FinalRotatedX(i,2) + Xm) / 3;
        double midY = (FinalRotatedY(i,0) + Xm) + (FinalRotatedY(i,1) + Xm) + (FinalRotatedY(i,2) + Xm) / 3;
        double midZ = (FinalRotatedZ(i,0) + Xm) + (FinalRotatedZ(i,1) + Xm) + (FinalRotatedZ(i,2) + Xm) / 3;
        return new double[]{midX,midY,midZ};
    }

    public double getMidDistance(int i){
        // the square calculation might not even be necessary, as we only arbitrarily probe for distance,
        // and don't look at the actual distance
        return Math.sqrt(((Math.pow(getMid(i)[0],2))+(Math.pow(getMid(i)[0],2))+(Math.pow(getMid(i)[0],2))));
    }

}
