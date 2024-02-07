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
    double zRotation;
    double xRotation;
    double yRotation;

    Chair(double Xm, double Ym, double Zm, double Fov, double width, double height, double zRotation, double xRotation, double yRotation){
        this.Xm = Xm;
        this.Ym = Ym;
        this.Zm = Zm;
        // The camera always assumes, that the chair is in front of the camera.
        // God knows what might happen, if this condition isn't met.
        this.Fov = Fov;
        // Have to adjust for the quarters of the screen
        this.width = width/2;
        this.height = height/2;
        this.zRotation = zRotation - 90;
        this.xRotation = xRotation + 180;
        this.yRotation = yRotation + 180;
    }

    // some programmer be crying and pissing themself out there, if they ever get to see my code

    // 14 points
    // {x,       y,     z}
    // {towards, right, up}

    // You really shouldn't save data like this. Good thing I never intend to use this for anything else...
    // Oh yes, this array represents all the verts of the chair. Now imagine, I want to convert this to polygons...
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

    // You really shouldn't...
    // This array saves all the polygons of the chair
    double[][][] pol = {
            {
                    {3,-2.5,0},
                    {3,2.5,0},
                    {-3,-2.5,0}
            },
            {
                    {-3,-2.5,0},
                    {-3,2.5,0},
                    {3,2.5,0}
            },
            {
                    {3,-2.5,0},
                    {-3,-2.5,0},
                    {-1,-1.5,1}
            },
            {
                    {3,2.5,0},
                    {-3,2.5,0},
                    {-1,1.5,1}
            },
            {
                    {-1,-1.5,1},
                    {-1,1.5,1},
                    {3,-2.5,0}
            },
            {
                    {3,-2.5,0},
                    {3,2.5,0},
                    {-1,1.5,1}
            },
            {
                    {-1,-1.5,1},
                    {-1,1.5,1},
                    {-3,-2.5,0}
            },
            {
                    {-3,-2.5,0},
                    {-3,2.5,0},
                    {-1,1.5,1}
            },
            {
                    {-1,1.5,1},
                    {3,2.5,3},
                    {-3,2.5,4}
            },
            {
                    {-1,-1.5,1},
                    {3,-2.5,3},
                    {-3,-2.5,4}
            },
            {
                    {-1,1.5,1},
                    {-1,-1.5,1},
                    {-3,-2.5,4}
            },
            {
                    {-3,-2.5,4},
                    {-3,2.5,4},
                    {-1,1.5,1}
            },
            {
                    {3,2.5,3},
                    {3,-2.5,3},
                    {-1,-1.5,1}
            },
            {
                    {-1,1.5,1},
                    {-1,-1.5,1},
                    {3,2.5,3}
            },
            {
                    {-3,-2.5,4},
                    {-3,2.5,4},
                    {3,2.5,3}
            },
            {
                    {3,2.5,3},
                    {3,-2.5,3},
                    {-3,-2.5,4}
            },
            {
                    {-3,-2.5,4},
                    {-3,2.5,4},
                    {-4,-2.5,9}
            },
            {
                    {-4,2.5,9},
                    {-4,-2.5,9},
                    {-3,2.5,4}
            },
            {
                    {-4,2.5,9},
                    {-3,2.5,4},
                    {-4,1.5,5}
            },
            {
                    {-4,-2.5,9},
                    {-3,-2.5,4},
                    {-4,-1.5,5}
            },
            {
                    {-4,2.5,9},
                    {-4,-2.5,9},
                    {-4,-1.5,5}
            },
            {
                    {-4,2.5,9},
                    {-4,1.5,5},
                    {-4,-1.5,5}
            },
            {
                    {-3,-2.5,4},
                    {-3,2.5,4},
                    {-4,-1.5,5}
            },
            {
                    {-3,2.5,4},
                    {-4,1.5,5},
                    {-4,-1.5,5}
            }
    };

    private double rotatedPX(int i){
        return Math.sqrt(Math.pow(p[i][0],2) + Math.pow(p[i][1],2)) * Math.cos(Math.toRadians(180 - Math.toDegrees(Math.atan2(p[i][0],p[i][1])) - zRotation));
    }

    private  double rotatedPY(int i){
        return Math.sqrt(Math.pow(p[i][0],2) + Math.pow(p[i][1],2)) * Math.sin(Math.toRadians(180 - Math.toDegrees(Math.atan2(p[i][0],p[i][1])) - zRotation));
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

    public double getP(int i,int j) {
        if (j == 0){
            // Not quite as important
            return rotatedPX(i) + Xm;
        } else if (j == 1){
            // Important
            return (((rotatedPY(i) + Ym) * Fov) / (Fov + ((p[i][0] + Xm) * -1))) * width;
            // The way it probes the x-coordinates is seriously bad.
            // It ALWAYS assumes, the camera facing in the negative x-direction
        } else {
            // Important
            return (((p[i][2] + Zm) * Fov) / (Fov + ((rotatedPX(i) + Xm) * -1))) * height;
            // Ditto
        }
    }

    public double getPol(int i,int j, int k){
        if (k == 0){
            return yRotatedPolX(i,j) + Xm;
        } else if (k == 1){
            return (((xRotatedPolY(i,j) + Ym) * Fov) / (Fov + ((yRotatedPolX(i,j) + Xm) * -1))) * width;
        } else {
            return (((yRotatedPolZ(i,j) + Zm) * Fov) / (Fov + ((yRotatedPolX(i,j) + Xm) * -1))) * height;
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
