package de.edward;

public class Chair {
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
    // Horizontal Rotation
    double horRot;

    Chair(double Xm, double Ym, double Zm, double Fov, double width, double height, double horRot){
        this.Xm = Xm;
        this.Ym = Ym;
        this.Zm = Zm;
        // The camera always assumes, that the chair is in front of the camera.
        // God knows what might happen, if this condition isn't met.
        this.Fov = Fov;
        // Have to adjust for the quarters of the screen
        this.width = width/2;
        this.height = height/2;
        this.horRot = horRot;

    }

    // some programmer be crying and pissing themself out there, if they ever get to see my code

    // 14 points
    // {x,       y,     z}
    // {towards, right, up}

    // You really shouldn't save data like this. Good thing I never intend to use this for anything else...
    // Oh yes, this array represents all the verts of the chair. Now imagine, I want to save entire polygons...
    double[][] p = {
            {3,-2.5,0},
            {3,2.5,0},
            {-3,-2.5,0},
            {-3,2.5,0},
            {-1,-1.5,1},
            {-1,1.5,1},
            {3,-2.5,3},
            {3,2.5,3},
            {-3,-2.5,4},
            {-3,2.5,4},
            {-4,-1.5,5},
            {-4,1.5,5},
            {-4,-2.5,9},
            {-4,2.5,9}
    };
    //TODO: Convert the chair to polygons

    private double rotatedX(int i, int j){
        // someone will hang me for this
        return Math.sqrt(Math.pow(p[i][j],2) + Math.pow(p[i][j+1],2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(p[i][j],p[i][j+1])) - horRot));
    }

    private  double rotatedY(int i, int j){
        // this isn't how it should work! yet it does?!
        if (p[i][j] > 0){
            return Math.sqrt(Math.pow(p[i][j-1],2) + Math.pow(p[i][j],2) - Math.pow(rotatedX(i,j-1),2));
        } else {
            return Math.sqrt(Math.pow(p[i][j - 1], 2) + Math.pow(p[i][j], 2) - Math.pow(rotatedX(i, j - 1), 2)) * -1;
        }
    }

    public double getP(int i,int j) {
        if (j == 0){
            // Not quite as important
            return rotatedX(i,j) + Xm;
        } else if (j == 1){
            // Important
            return (((rotatedY(i,j) + Ym) * Fov) / (Fov + ((p[i][j-1] + Xm) * -1))) * width;
            // The way it probes the x-coordinates is seriously bad.
            // It ALWAYS assumes, the camera facing in the negative x-direction
        } else if (j == 2){
            // Important
            return (((p[i][j] + Zm) * Fov) / (Fov + ((p[i][j-2] + Xm) * -1))) * height;
            // Ditto
        } else {
            return p[i][j];
        }
    }

    /* //god forbid, I know that arrays exist
    double[] p1 = {3,-2.5,0};
    double[] p2 = {3,2.5,0};
    double[] p3 = {-3,-2.5,0};
    double[] p4 = {-3,2.5,0};
    double[] p5 = {-1,-1.5,1};
    double[] p6 = {-1,1.5,1};
    double[] p7 = {3,-2.5,3};
    double[] p8 = {3,2.5,3};
    double[] p9 = {-3,-2.5,4};
    double[] p10 = {-3,2.5,4};
    double[] p11 = {-4,-1.5,5};
    double[] p12 = {-4,1.5,5};
    double[] p13 = {-4,-2.5,9};
    double[] p14 = {-4,2.5,9};
     */

}